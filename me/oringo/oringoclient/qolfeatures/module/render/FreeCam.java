/*    */ package me.oringo.oringoclient.qolfeatures.module.render;
/*    */ 
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.events.PacketSentEvent;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*    */ import me.oringo.oringoclient.utils.MovementUtils;
/*    */ import me.oringo.oringoclient.utils.PacketUtils;
/*    */ import me.oringo.oringoclient.utils.RenderUtils;
/*    */ import net.minecraft.client.entity.EntityOtherPlayerMP;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.C03PacketPlayer;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.client.event.RenderWorldLastEvent;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import net.minecraftforge.event.world.WorldEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class FreeCam extends Module {
/* 23 */   public NumberSetting speed = new NumberSetting("Speed", 3.0D, 0.1D, 5.0D, 0.1D); private EntityOtherPlayerMP playerEntity;
/* 24 */   public BooleanSetting tracer = new BooleanSetting("Show tracer", false);
/*    */   
/*    */   public FreeCam() {
/* 27 */     super("FreeCam", Module.Category.RENDER);
/* 28 */     addSettings(new Setting[] { (Setting)this.speed, (Setting)this.tracer });
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 33 */     this.playerEntity = new EntityOtherPlayerMP((World)OringoClient.mc.field_71441_e, OringoClient.mc.field_71439_g.func_146103_bH());
/* 34 */     this.playerEntity.func_82149_j((Entity)OringoClient.mc.field_71439_g);
/* 35 */     this.playerEntity.field_70122_E = OringoClient.mc.field_71439_g.field_70122_E;
/* 36 */     OringoClient.mc.field_71441_e.func_73027_a(-2137, (Entity)this.playerEntity);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDisable() {
/* 41 */     if (OringoClient.mc.field_71439_g == null || OringoClient.mc.field_71441_e == null || this.playerEntity == null)
/* 42 */       return;  OringoClient.mc.field_71439_g.field_70145_X = false;
/* 43 */     OringoClient.mc.field_71439_g.func_70107_b(this.playerEntity.field_70165_t, this.playerEntity.field_70163_u, this.playerEntity.field_70161_v);
/* 44 */     OringoClient.mc.field_71441_e.func_73028_b(-2137);
/* 45 */     this.playerEntity = null;
/* 46 */     OringoClient.mc.field_71439_g.func_70016_h(0.0D, 0.0D, 0.0D);
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
/* 51 */     if (isToggled()) {
/* 52 */       OringoClient.mc.field_71439_g.field_70145_X = true;
/* 53 */       OringoClient.mc.field_71439_g.field_70143_R = 0.0F;
/* 54 */       OringoClient.mc.field_71439_g.field_70122_E = false;
/* 55 */       OringoClient.mc.field_71439_g.field_71075_bZ.field_75100_b = false;
/* 56 */       OringoClient.mc.field_71439_g.field_70181_x = 0.0D;
/* 57 */       if (!MovementUtils.isMoving()) {
/* 58 */         OringoClient.mc.field_71439_g.field_70179_y = 0.0D;
/* 59 */         OringoClient.mc.field_71439_g.field_70159_w = 0.0D;
/*    */       } 
/*    */       
/* 62 */       double speed = this.speed.getValue() * 0.1D;
/* 63 */       OringoClient.mc.field_71439_g.field_70747_aH = (float)speed;
/* 64 */       if (OringoClient.mc.field_71474_y.field_74314_A.func_151470_d())
/* 65 */         OringoClient.mc.field_71439_g.field_70181_x += speed * 3.0D; 
/* 66 */       if (OringoClient.mc.field_71474_y.field_74311_E.func_151470_d())
/* 67 */         OringoClient.mc.field_71439_g.field_70181_x -= speed * 3.0D; 
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onRenderWorld(RenderWorldLastEvent event) {
/* 73 */     if (isToggled() && this.playerEntity != null && this.tracer.isEnabled()) {
/* 74 */       RenderUtils.tracerLine((Entity)this.playerEntity, event.partialTicks, 1.0F, OringoClient.clickGui.getColor());
/*    */     }
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onWorldChange(WorldEvent.Load event) {
/* 80 */     if (isToggled()) toggle();
/*    */   
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onPacket(PacketSentEvent event) {
/* 86 */     if (isToggled() && event.packet instanceof C03PacketPlayer) {
/* 87 */       event.setCanceled(true);
/* 88 */       PacketUtils.sendPacketNoEvent((Packet)new C03PacketPlayer(this.playerEntity.field_70122_E));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\render\FreeCam.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */