/*     */ package me.oringo.oringoclient.qolfeatures.module.render;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.HashMap;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.events.RenderLayersEvent;
/*     */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.ModeSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*     */ import me.oringo.oringoclient.utils.MobRenderUtils;
/*     */ import me.oringo.oringoclient.utils.OutlineUtils;
/*     */ import me.oringo.oringoclient.utils.RenderUtils;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraftforge.client.event.RenderLivingEvent;
/*     */ import net.minecraftforge.client.event.RenderWorldLastEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.event.world.WorldEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ 
/*     */ public class PlayerESP
/*     */   extends Module
/*     */ {
/*  25 */   public HashMap<EntityPlayer, Integer> ticksInvisible = new HashMap<>();
/*     */   
/*  27 */   public ModeSetting mode = new ModeSetting("Mode", "2D", new String[] { "Outline", "2D", "Chams", "Box", "Tracers" });
/*     */ 
/*     */ 
/*     */   
/*  31 */   public NumberSetting opacity = new NumberSetting("Opacity", 255.0D, 0.0D, 255.0D, 1.0D)
/*     */     {
/*     */       public boolean isHidden() {
/*  34 */         return !PlayerESP.this.mode.is("Chams");
/*     */       }
/*     */     };
/*     */   
/*     */   private EntityPlayer lastRendered;
/*     */   
/*     */   public PlayerESP() {
/*  41 */     super("PlayerESP", 0, Module.Category.RENDER);
/*  42 */     addSettings(new Setting[] { (Setting)this.mode, (Setting)this.opacity });
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onUpdateEntity(LivingEvent.LivingUpdateEvent event) {
/*  47 */     if (event.entity instanceof EntityPlayer && event.entity != OringoClient.mc.field_71439_g && isToggled()) {
/*  48 */       if (this.ticksInvisible.containsKey(event.entity)) {
/*  49 */         if (event.entity.func_82150_aj())
/*  50 */           this.ticksInvisible.replace((EntityPlayer)event.entity, Integer.valueOf(((Integer)this.ticksInvisible.get(event.entity)).intValue() + 1)); 
/*     */       } else {
/*  52 */         this.ticksInvisible.put((EntityPlayer)event.entity, Integer.valueOf(0));
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRender(RenderWorldLastEvent event) {
/*  59 */     if (!isToggled() || (!this.mode.getSelected().equals("2D") && !this.mode.getSelected().equals("Box") && !this.mode.getSelected().equals("Tracers")))
/*  60 */       return;  Color color = OringoClient.clickGui.getColor();
/*  61 */     for (EntityPlayer entityPlayer : OringoClient.mc.field_71441_e.field_73010_i) {
/*  62 */       if (isValidEntity(entityPlayer) && entityPlayer != OringoClient.mc.field_71439_g) {
/*  63 */         switch (this.mode.getSelected()) {
/*     */           case "2D":
/*  65 */             RenderUtils.draw2D((Entity)entityPlayer, event.partialTicks, 1.5F, color);
/*     */           
/*     */           case "Box":
/*  68 */             RenderUtils.entityESPBox((Entity)entityPlayer, event.partialTicks, color);
/*     */           
/*     */           case "Tracers":
/*  71 */             RenderUtils.tracerLine((Entity)entityPlayer, event.partialTicks, 1.0F, color);
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
/*  82 */     Color color = OringoClient.clickGui.getColor();
/*  83 */     if (isToggled() && event.entity instanceof EntityPlayer && isValidEntity((EntityPlayer)event.entity) && event.entity != OringoClient.mc.field_71439_g && this.mode.getSelected().equals("Outline")) {
/*  84 */       OutlineUtils.outlineESP(event, color);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRenderLiving(RenderLivingEvent.Pre event) {
/*  90 */     if (this.lastRendered != null) {
/*  91 */       this.lastRendered = null;
/*  92 */       RenderUtils.disableChams();
/*  93 */       MobRenderUtils.unsetColor();
/*     */     } 
/*  95 */     if (!(event.entity instanceof net.minecraft.client.entity.EntityOtherPlayerMP) || !this.mode.getSelected().equals("Chams") || !isToggled())
/*  96 */       return;  Color color = RenderUtils.applyOpacity(OringoClient.clickGui.getColor(), (int)this.opacity.getValue());
/*  97 */     RenderUtils.enableChams();
/*  98 */     MobRenderUtils.setColor(color);
/*  99 */     this.lastRendered = (EntityPlayer)event.entity;
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRenderLivingPost(RenderLivingEvent.Specials.Pre event) {
/* 104 */     if (event.entity == this.lastRendered) {
/* 105 */       this.lastRendered = null;
/* 106 */       RenderUtils.disableChams();
/* 107 */       MobRenderUtils.unsetColor();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onWorldLoad(WorldEvent.Load event) {
/* 114 */     this.ticksInvisible.clear();
/*     */   }
/*     */   
/*     */   private boolean isValidEntity(EntityPlayer player) {
/* 118 */     if (!this.ticksInvisible.containsKey(player)) return false; 
/* 119 */     return (player.field_70173_aa - ((Integer)this.ticksInvisible.get(player)).intValue() > 20 && !player.field_70128_L && player.func_110143_aJ() > 0.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\render\PlayerESP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */