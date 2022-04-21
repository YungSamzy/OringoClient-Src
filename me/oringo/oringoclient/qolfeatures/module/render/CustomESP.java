/*     */ package me.oringo.oringoclient.qolfeatures.module.render;
/*     */ 
/*     */ import com.mojang.realmsclient.gui.ChatFormatting;
/*     */ import java.awt.Color;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.regex.Pattern;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.events.RenderLayersEvent;
/*     */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.ModeSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*     */ import me.oringo.oringoclient.utils.MobRenderUtils;
/*     */ import me.oringo.oringoclient.utils.Notifications;
/*     */ import me.oringo.oringoclient.utils.OutlineUtils;
/*     */ import me.oringo.oringoclient.utils.RenderUtils;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraftforge.client.event.RenderLivingEvent;
/*     */ import net.minecraftforge.client.event.RenderWorldLastEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.EventPriority;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*     */ import org.lwjgl.input.Mouse;
/*     */ 
/*     */ public class CustomESP
/*     */   extends Module
/*     */ {
/*  33 */   public BooleanSetting middleClick = new BooleanSetting("Middle click to add", false);
/*  34 */   public static List<String> names = new ArrayList<>();
/*     */   
/*     */   private boolean wasDown;
/*  37 */   private static Pattern start = Pattern.compile("\\[Lv\\d*] ");
/*  38 */   private static Pattern end = Pattern.compile(" \\d*/\\d*$");
/*     */   
/*  40 */   public ModeSetting mode = new ModeSetting("Mode", "2D", new String[] { "Outline", "2D", "Chams", "Box", "Tracers" });
/*  41 */   public NumberSetting red = new NumberSetting("Red", 1.0D, 1.0D, 255.0D, 1.0D); public NumberSetting green = new NumberSetting("Green", 1.0D, 1.0D, 255.0D, 1.0D); public NumberSetting blue = new NumberSetting("Blue", 1.0D, 1.0D, 255.0D, 1.0D);
/*     */   
/*     */   private EntityLivingBase lastRendered;
/*     */ 
/*     */   
/*     */   public CustomESP() {
/*  47 */     super("Custom ESP", Module.Category.RENDER);
/*  48 */     addSettings(new Setting[] { (Setting)this.mode, (Setting)this.red, (Setting)this.green, (Setting)this.blue, (Setting)this.middleClick });
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onTick(TickEvent.ClientTickEvent event) {
/*  53 */     if (OringoClient.mc.field_71439_g == null || OringoClient.mc.field_71441_e == null || !this.middleClick.isEnabled())
/*  54 */       return;  if (Mouse.isButtonDown(2) && OringoClient.mc.field_71462_r == null) {
/*  55 */       if (OringoClient.mc.field_147125_j != null && !this.wasDown && !(OringoClient.mc.field_147125_j instanceof net.minecraft.entity.item.EntityArmorStand) && OringoClient.mc.field_147125_j instanceof EntityLivingBase) {
/*  56 */         String name = ChatFormatting.stripFormatting(OringoClient.mc.field_147125_j.func_145748_c_().func_150254_d());
/*  57 */         if (!names.contains(name)) {
/*  58 */           names.add(name);
/*  59 */           save();
/*  60 */           Notifications.showNotification("Oringo Client", "Added " + ChatFormatting.AQUA + name + ChatFormatting.RESET + " to customESP", 2000);
/*     */         } else {
/*  62 */           names.remove(name);
/*  63 */           save();
/*  64 */           Notifications.showNotification("Oringo Client", "Removed " + ChatFormatting.AQUA + name + ChatFormatting.RESET + " from customESP", 2000);
/*     */         } 
/*     */       } 
/*  67 */       this.wasDown = true;
/*     */     } else {
/*  69 */       this.wasDown = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRender(RenderWorldLastEvent event) {
/*  75 */     if (!isToggled() || (!this.mode.getSelected().equals("2D") && !this.mode.getSelected().equals("Box") && !this.mode.getSelected().equals("Tracers")))
/*  76 */       return;  Color color = new Color((int)this.red.getValue(), (int)this.green.getValue(), (int)this.blue.getValue(), 255);
/*  77 */     for (Entity entity : OringoClient.mc.field_71441_e.field_72996_f) {
/*  78 */       if (entity instanceof EntityLivingBase && entity != OringoClient.mc.field_71439_g && names.contains(ChatFormatting.stripFormatting(entity.func_145748_c_().func_150254_d()))) {
/*  79 */         switch (this.mode.getSelected()) {
/*     */           case "2D":
/*  81 */             RenderUtils.draw2D(entity, event.partialTicks, 1.5F, color);
/*     */           
/*     */           case "Box":
/*  84 */             RenderUtils.entityESPBox(entity, event.partialTicks, color);
/*     */           
/*     */           case "Tracers":
/*  87 */             RenderUtils.tracerLine(entity, event.partialTicks, 1.0F, color);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRender(RenderLayersEvent event) {
/*  98 */     Color color = new Color((int)this.red.getValue(), (int)this.green.getValue(), (int)this.blue.getValue(), 255);
/*  99 */     if (isToggled() && names.contains(ChatFormatting.stripFormatting(event.entity.func_145748_c_().func_150254_d())) && event.entity != OringoClient.mc.field_71439_g && this.mode.getSelected().equals("Outline")) {
/* 100 */       OutlineUtils.outlineESP(event, color);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.LOWEST)
/*     */   public void onRenderLiving(RenderLivingEvent.Pre event) {
/* 106 */     if (this.lastRendered != null) {
/* 107 */       this.lastRendered = null;
/* 108 */       RenderUtils.disableChams();
/* 109 */       MobRenderUtils.unsetColor();
/*     */     } 
/* 111 */     if (!this.mode.getSelected().equals("Chams") || !isToggled() || !names.contains(ChatFormatting.stripFormatting(event.entity.func_145748_c_().func_150254_d())))
/* 112 */       return;  Color color = new Color((int)this.red.getValue(), (int)this.green.getValue(), (int)this.blue.getValue(), 255);
/* 113 */     RenderUtils.enableChams();
/* 114 */     MobRenderUtils.setColor(color);
/* 115 */     this.lastRendered = event.entity;
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRenderLivingPost(RenderLivingEvent.Specials.Pre event) {
/* 120 */     if (event.entity == this.lastRendered) {
/* 121 */       this.lastRendered = null;
/* 122 */       RenderUtils.disableChams();
/* 123 */       MobRenderUtils.unsetColor();
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void save() {
/*     */     try {
/* 129 */       DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("config/OringoClient/CustomESP.cfg"));
/* 130 */       dataOutputStream.writeInt(names.size());
/* 131 */       for (String name : names) {
/* 132 */         dataOutputStream.writeUTF(name);
/*     */       }
/* 134 */       dataOutputStream.close();
/* 135 */     } catch (Exception e) {
/* 136 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\render\CustomESP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */