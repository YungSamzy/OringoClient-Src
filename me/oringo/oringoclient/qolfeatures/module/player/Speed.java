/*    */ package me.oringo.oringoclient.qolfeatures.module.player;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.events.MotionUpdateEvent;
/*    */ import me.oringo.oringoclient.events.PacketReceivedEvent;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.combat.KillAura;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*    */ import me.oringo.oringoclient.utils.MilliTimer;
/*    */ import me.oringo.oringoclient.utils.MovementUtils;
/*    */ import me.oringo.oringoclient.utils.Notifications;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.stats.StatList;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraftforge.fml.common.eventhandler.EventPriority;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*    */ 
/*    */ public class Speed extends Module {
/*    */   public BooleanSetting autoJump;
/*    */   public BooleanSetting velocityStrafe;
/*    */   
/*    */   public Speed() {
/* 25 */     super("Speed", Module.Category.PLAYER);
/*    */ 
/*    */ 
/*    */     
/* 29 */     this.autoJump = new BooleanSetting("Jump", true); this.velocityStrafe = new BooleanSetting("Velocity strafe", true);
/*    */     
/* 31 */     this.boostSpeed = new NumberSetting("Boost speed", 0.8D, 0.1D, 1.0D, 0.1D); this.strafeTimer = new NumberSetting("Strafe timer", 1000.0D, 100.0D, 2500.0D, 1.0D);
/*    */ 
/*    */     
/* 34 */     this.disable = new MilliTimer(); this.velocityTimer = new MilliTimer();
/*    */     addSettings(new Setting[] { (Setting)this.autoJump, (Setting)this.velocityStrafe, (Setting)this.strafeTimer, (Setting)this.boostSpeed });
/*    */   }
/*    */   public NumberSetting boostSpeed; public NumberSetting strafeTimer; public MilliTimer disable; public MilliTimer velocityTimer; private float lastForward;
/*    */   private float lastStrafing;
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onTick(TickEvent.PlayerTickEvent event) {
/* 42 */     if (isToggled() && event.player == OringoClient.mc.field_71439_g && event.phase == TickEvent.Phase.END && this.disable.hasTimePassed(1000L)) {
/* 43 */       if (MovementUtils.isMoving()) {
/* 44 */         if (OringoClient.mc.field_71439_g.field_70122_E) {
/* 45 */           if (this.autoJump.isEnabled()) {
/* 46 */             OringoClient.mc.field_71439_g.field_70181_x = 0.41999998688697815D;
/* 47 */             if (OringoClient.mc.field_71439_g.func_70051_ag()) {
/* 48 */               float f = MovementUtils.getYaw() * 0.017453292F;
/* 49 */               OringoClient.mc.field_71439_g.field_70159_w -= (MathHelper.func_76126_a(f) * 0.2F);
/* 50 */               OringoClient.mc.field_71439_g.field_70179_y += (MathHelper.func_76134_b(f) * 0.2F);
/*    */             } 
/* 52 */             OringoClient.mc.field_71439_g.field_70160_al = true;
/* 53 */             OringoClient.mc.field_71439_g.func_71029_a(StatList.field_75953_u);
/* 54 */             if (OringoClient.mc.field_71439_g.func_70051_ag()) {
/* 55 */               OringoClient.mc.field_71439_g.func_71020_j(0.8F);
/*    */             } else {
/* 57 */               OringoClient.mc.field_71439_g.func_71020_j(0.2F);
/*    */             } 
/*    */           } 
/* 60 */           MovementUtils.strafe(true);
/*    */         } else {
/* 62 */           OringoClient.mc.field_71439_g.field_70159_w *= (OringoClient.mc.field_71439_g.func_70660_b(Potion.field_76424_c) != null) ? (1.0F + 0.01F * OringoClient.mc.field_71439_g.func_70660_b(Potion.field_76424_c).func_76458_c()) : 1.0D;
/* 63 */           OringoClient.mc.field_71439_g.field_70179_y *= (OringoClient.mc.field_71439_g.func_70660_b(Potion.field_76424_c) != null) ? (1.0F + 0.01F * OringoClient.mc.field_71439_g.func_70660_b(Potion.field_76424_c).func_76458_c()) : 1.0D;
/* 64 */           if (!this.velocityTimer.hasTimePassed((long)this.strafeTimer.getValue()) && (this.lastForward != OringoClient.mc.field_71439_g.field_70701_bs || this.lastStrafing != OringoClient.mc.field_71439_g.field_70702_br)) {
/* 65 */             MovementUtils.strafe(false);
/* 66 */             this.lastStrafing = OringoClient.mc.field_71439_g.field_70702_br;
/* 67 */             this.lastForward = OringoClient.mc.field_71439_g.field_70701_bs;
/*    */           
/*    */           }
/*    */         
/*    */         }
/*    */       
/*    */       }
/* 74 */       else if (this.autoJump.isEnabled()) {
/* 75 */         OringoClient.mc.field_71439_g.field_70179_y = 0.0D;
/* 76 */         OringoClient.mc.field_71439_g.field_70159_w = 0.0D;
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   @SubscribeEvent(priority = EventPriority.LOWEST)
/*    */   public void onUpdate(MotionUpdateEvent.Pre event) {
/* 83 */     if (!this.velocityTimer.hasTimePassed((long)this.strafeTimer.getValue()) && (KillAura.target == null || OringoClient.mc.field_71439_g.field_70173_aa % 2 != 0)) {
/* 84 */       event.yaw = MovementUtils.getYaw();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent(receiveCanceled = true)
/*    */   public void onPacket(PacketReceivedEvent event) {
/* 91 */     if (event.packet instanceof net.minecraft.network.play.server.S08PacketPlayerPosLook) {
/* 92 */       if (this.disable.hasTimePassed(1000L) && isToggled()) {
/* 93 */         Notifications.showNotification("Oringo Client", "Disabled strafe due to a flag", 1500);
/*    */       }
/* 95 */       this.disable.updateTime();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\player\Speed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */