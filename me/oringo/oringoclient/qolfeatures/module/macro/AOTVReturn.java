/*     */ package me.oringo.oringoclient.qolfeatures.module.macro;
/*     */ import java.util.stream.Collectors;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.ModeSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.StringSetting;
/*     */ import me.oringo.oringoclient.utils.Notifications;
/*     */ import me.oringo.oringoclient.utils.RotationUtils;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiChat;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityArmorStand;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.C0BPacketEntityAction;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*     */ import org.lwjgl.input.Mouse;
/*     */ 
/*     */ public class AOTVReturn extends Module {
/*  31 */   private StringSetting warp = new StringSetting("Warp command", "/warp forge");
/*  32 */   private StringSetting coords = new StringSetting("TP Coords", "0.5,167,-10.5;-23.5,180,-26.5;-64.5,212,-15.5;-33.5,244,-32.5");
/*  33 */   private BooleanSetting chat = new BooleanSetting("Open chat", true); private BooleanSetting middleClick = new BooleanSetting("Middle click", false);
/*  34 */   private NumberSetting delay = new NumberSetting("Delay", 2000.0D, 500.0D, 5000.0D, 1.0D);
/*  35 */   private ModeSetting mode = new ModeSetting("mode", "walk", new String[] { "jump", "walk" });
/*  36 */   private Thread instance = null;
/*  37 */   private Vec3 rotate = null;
/*     */   private boolean openChat = false;
/*     */   private boolean wasDown;
/*     */   private boolean isRunning;
/*     */   
/*     */   public AOTVReturn() {
/*  43 */     super("AOTV Return", Module.Category.MACRO);
/*  44 */     addSettings(new Setting[] { (Setting)this.warp, (Setting)this.mode, (Setting)this.coords, (Setting)this.chat, (Setting)this.middleClick, (Setting)this.delay });
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDisable() {
/*  49 */     this.isRunning = false;
/*  50 */     if (this.instance != null) this.instance.stop(); 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onTick(TickEvent.ClientTickEvent event) {
/*  55 */     if (this.openChat) {
/*  56 */       Minecraft.func_71410_x().func_147108_a((GuiScreen)new GuiChat());
/*  57 */       this.openChat = false;
/*     */     } 
/*  59 */     if (OringoClient.mc.field_71439_g == null || OringoClient.mc.field_71441_e == null || !this.middleClick.isEnabled())
/*  60 */       return;  if (Mouse.isButtonDown(2) && OringoClient.mc.field_71462_r == null) {
/*  61 */       if (!this.wasDown && OringoClient.mc.field_71476_x != null && OringoClient.mc.field_71476_x.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/*  62 */         BlockPos blockpos = OringoClient.mc.field_71476_x.func_178782_a();
/*  63 */         if (OringoClient.mc.field_71441_e.func_180495_p(blockpos).func_177230_c().func_149688_o() != Material.field_151579_a) {
/*  64 */           this.coords.setValue(this.coords.getValue() + ((this.coords.getValue().length() > 0) ? ";" : "") + (blockpos.func_177958_n() + 0.5D) + "," + (blockpos.func_177956_o() + 0.5D) + "," + (blockpos.func_177952_p() + 0.5D));
/*  65 */           Notifications.showNotification("Oringo Client", "Added " + blockpos.func_177958_n() + " " + blockpos.func_177956_o() + " " + blockpos.func_177952_p() + " to coords!", 2500);
/*     */         } 
/*     */       } 
/*  68 */       this.wasDown = true;
/*     */     } else {
/*  70 */       this.wasDown = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isRunning() {
/*  75 */     return this.isRunning;
/*     */   }
/*     */   
/*     */   public void start(Runnable onFinish, boolean stop) {
/*  79 */     if (this.instance != null) this.instance.stop(); 
/*  80 */     this.isRunning = true;
/*  81 */     Minecraft mc = Minecraft.func_71410_x();
/*  82 */     (this.instance = new Thread(() -> {
/*     */           try {
/*     */             KeyBinding.func_74510_a(mc.field_71474_y.field_74312_F.func_151463_i(), false);
/*     */             mc.field_71439_g.func_71165_d("/l");
/*     */             Thread.sleep(5000L);
/*     */             mc.field_71439_g.func_71165_d("/skyblock");
/*     */             Thread.sleep(5000L);
/*     */             mc.field_71439_g.func_71165_d("/is");
/*     */             Thread.sleep((long)this.delay.getValue() * 3L);
/*     */             for (int i = 0; i < 9; i++) {
/*     */               if (mc.field_71439_g.field_71071_by.func_70301_a(i) != null && mc.field_71439_g.field_71071_by.func_70301_a(i).func_82833_r().contains("Void")) {
/*     */                 mc.field_71439_g.field_71071_by.field_70461_c = i;
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */             mc.field_71439_g.func_71165_d(this.warp.getValue());
/*     */             Thread.sleep((long)this.delay.getValue() * 2L);
/*     */             if (OringoClient.mithrilMacro.drillnpc == null) {
/*     */               for (Entity entityArmorStand : mc.field_71441_e.func_72910_y().stream().filter(()).collect(Collectors.toList())) {
/*     */                 if (entityArmorStand.func_145748_c_().func_150254_d().contains("§e§lDRILL MECHANIC§r")) {
/*     */                   OringoClient.mithrilMacro.drillnpc = (EntityArmorStand)entityArmorStand;
/*     */                   OringoClient.sendMessageWithPrefix("Mechanic");
/*     */                   break;
/*     */                 } 
/*     */               } 
/*     */             }
/*     */             for (String str : this.coords.getValue().split(";")) {
/*     */               if (OringoClient.mithrilMacro.drillnpc == null) {
/*     */                 for (Entity entityArmorStand : mc.field_71441_e.func_72910_y().stream().filter(()).collect(Collectors.toList())) {
/*     */                   if (entityArmorStand.func_145748_c_().func_150254_d().contains("§e§lDRILL MECHANIC§r")) {
/*     */                     OringoClient.mithrilMacro.drillnpc = (EntityArmorStand)entityArmorStand;
/*     */                     OringoClient.sendMessageWithPrefix("Mechanic");
/*     */                     break;
/*     */                   } 
/*     */                 } 
/*     */               }
/*     */               Thread.sleep((long)this.delay.getValue());
/*     */               switch (this.mode.getSelected()) {
/*     */                 case "jump":
/*     */                   if (mc.field_71439_g.field_70122_E)
/*     */                     mc.field_71439_g.func_70664_aZ(); 
/*     */                   while (!mc.field_71439_g.field_70122_E)
/*     */                     Thread.sleep(1L); 
/*     */                   break;
/*     */                 case "walk":
/*     */                   KeyBinding.func_74510_a(mc.field_71474_y.field_74366_z.func_151463_i(), true);
/*     */                   Thread.sleep(50L);
/*     */                   KeyBinding.func_74510_a(mc.field_71474_y.field_74366_z.func_151463_i(), false);
/*     */                   break;
/*     */               } 
/*     */               Thread.sleep((long)this.delay.getValue());
/*     */               this.rotate = new Vec3(Double.parseDouble(str.split(",")[0]), Double.parseDouble(str.split(",")[1]), Double.parseDouble(str.split(",")[2]));
/*     */               float[] rotation = RotationUtils.getAngles(this.rotate);
/*     */               mc.field_71439_g.field_70177_z = rotation[0];
/*     */               mc.field_71439_g.field_70125_A = rotation[1];
/*     */               Thread.sleep((long)this.delay.getValue());
/*     */               mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C0BPacketEntityAction((Entity)mc.field_71439_g, C0BPacketEntityAction.Action.START_SNEAKING));
/*     */               mc.field_71442_b.func_78769_a((EntityPlayer)mc.field_71439_g, (World)mc.field_71441_e, mc.field_71439_g.func_70694_bm());
/*     */               mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C0BPacketEntityAction((Entity)mc.field_71439_g, C0BPacketEntityAction.Action.STOP_SNEAKING));
/*     */               this.rotate = null;
/*     */             } 
/*     */             Thread.sleep((long)this.delay.getValue());
/*     */             String pos = this.coords.getValue().split(";")[(this.coords.getValue().split(";")).length - 1];
/*     */             for (int j = 0; j < 9; j++) {
/*     */               if (mc.field_71439_g.field_71071_by.func_70301_a(j) != null && mc.field_71439_g.field_71071_by.func_70301_a(j).func_82840_a((EntityPlayer)mc.field_71439_g, false).stream().anyMatch(())) {
/*     */                 mc.field_71439_g.field_71071_by.field_70461_c = j;
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */             Thread.sleep((long)this.delay.getValue());
/*     */             if (mc.field_71439_g.func_70011_f(Double.parseDouble(pos.split(",")[0]), Double.parseDouble(pos.split(",")[1]), Double.parseDouble(pos.split(",")[2])) < 3.0D) {
/*     */               if (this.chat.isEnabled())
/*     */                 this.openChat = true; 
/*     */               if (onFinish != null)
/*     */                 onFinish.run(); 
/*     */             } else if (!stop) {
/*     */               (new Thread(())).start();
/*     */             } 
/* 160 */           } catch (Exception e) {
/*     */             e.printStackTrace();
/*     */           } 
/*     */           this.isRunning = false;
/* 164 */         })).start();
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\macro\AOTVReturn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */