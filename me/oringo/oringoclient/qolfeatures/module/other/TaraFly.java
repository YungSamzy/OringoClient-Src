/*    */ package me.oringo.oringoclient.qolfeatures.module.other;
/*    */ import com.mojang.realmsclient.gui.ChatFormatting;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.events.MotionUpdateEvent;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*    */ import me.oringo.oringoclient.utils.MilliTimer;
/*    */ import me.oringo.oringoclient.utils.MovementUtils;
/*    */ import net.minecraftforge.client.event.ClientChatReceivedEvent;
/*    */ import net.minecraftforge.event.world.WorldEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class TaraFly extends Module {
/*    */   public NumberSetting setting;
/*    */   public NumberSetting time;
/*    */   
/*    */   public TaraFly() {
/* 20 */     super("Slime fly", 0, Module.Category.OTHER);
/*    */ 
/*    */     
/* 23 */     this.setting = new NumberSetting("Speed", 1.0D, 0.1D, 3.0D, 0.01D); this.time = new NumberSetting("Disabler timer", 1200.0D, 250.0D, 2500.0D, 1.0D);
/*    */ 
/*    */     
/* 26 */     this.dev = new BooleanSetting("Dev", false)
/*    */       {
/*    */         public boolean isHidden() {
/* 29 */           return !OringoClient.devMode;
/*    */         }
/*    */       };
/*    */     
/* 33 */     this.disablerTimer = new MilliTimer();
/* 34 */     this.isFlying = false;
/*    */     addSettings(new Setting[] { (Setting)this.setting, (Setting)this.time, (Setting)this.dev });
/*    */   } private BooleanSetting dev; public MilliTimer disablerTimer; private boolean isFlying;
/*    */   public void onDisable() {
/* 38 */     OringoClient.mc.field_71439_g.func_70016_h(0.0D, 0.0D, 0.0D);
/* 39 */     this.isFlying = false;
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onWorldLoad(WorldEvent.Load event) {}
/*    */   
/*    */   @SubscribeEvent(priority = EventPriority.HIGHEST)
/*    */   public void onChat(ClientChatReceivedEvent event) {
/* 48 */     if (ChatFormatting.stripFormatting(event.message.func_150254_d()).contains("Double-jump boots are temporarily disabled!")) event.setCanceled(true); 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onUpdate(MotionUpdateEvent.Pre pre) {
/* 53 */     if (this.isFlying && this.disablerTimer.hasTimePassed((long)this.time.getValue())) {
/* 54 */       OringoClient.mc.field_71439_g.func_70016_h(0.0D, 0.0D, 0.0D);
/* 55 */       this.isFlying = false;
/*    */     } 
/* 57 */     if (!isToggled() || (!OringoClient.mc.field_71439_g.field_71075_bZ.field_75101_c && this.disablerTimer.hasTimePassed((long)this.time.getValue()) && !this.dev.isEnabled()))
/* 58 */       return;  OringoClient.mc.field_71439_g.field_70143_R = 0.0F;
/* 59 */     OringoClient.mc.field_71439_g.field_70122_E = false;
/* 60 */     OringoClient.mc.field_71439_g.field_71075_bZ.field_75100_b = false;
/* 61 */     OringoClient.mc.field_71439_g.field_70181_x = 0.0D;
/* 62 */     if (OringoClient.mc.field_71439_g.field_71075_bZ.field_75101_c && (OringoClient.mc.field_71439_g.field_70173_aa % 6 == 0 || !this.isFlying)) {
/* 63 */       OringoClient.mc.field_71439_g.field_71075_bZ.field_75100_b = true;
/* 64 */       OringoClient.mc.field_71439_g.func_71016_p();
/* 65 */       OringoClient.mc.field_71439_g.field_71075_bZ.field_75100_b = false;
/* 66 */       OringoClient.mc.field_71439_g.func_71016_p();
/* 67 */       this.isFlying = true;
/* 68 */       this.disablerTimer.updateTime();
/*    */     } 
/* 70 */     if (!MovementUtils.isMoving()) {
/* 71 */       OringoClient.mc.field_71439_g.field_70179_y = 0.0D;
/* 72 */       OringoClient.mc.field_71439_g.field_70159_w = 0.0D;
/*    */     } 
/*    */     
/* 75 */     double speed = this.setting.getValue();
/* 76 */     OringoClient.mc.field_71439_g.field_70747_aH = (float)speed;
/* 77 */     if (OringoClient.mc.field_71474_y.field_74314_A.func_151470_d())
/* 78 */       OringoClient.mc.field_71439_g.field_70181_x += speed * 3.0D; 
/* 79 */     if (OringoClient.mc.field_71474_y.field_74311_E.func_151470_d())
/* 80 */       OringoClient.mc.field_71439_g.field_70181_x -= speed * 3.0D; 
/*    */   }
/*    */   
/*    */   private boolean hasBoots() {
/* 84 */     return (OringoClient.mc.field_71439_g.func_82169_q(0) != null && (OringoClient.mc.field_71439_g.func_82169_q(0).func_82833_r().contains("Tarantula Boots") || OringoClient.mc.field_71439_g.func_82169_q(0).func_82833_r().contains("Spider's Boots")));
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\other\TaraFly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */