/*    */ package me.oringo.oringoclient.mixins;
/*    */ 
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.utils.MovementUtils;
/*    */ import me.oringo.oringoclient.utils.SkyblockUtils;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.multiplayer.WorldClient;
/*    */ import net.minecraft.client.network.NetHandlerPlayClient;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.NetworkManager;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketThreadUtil;
/*    */ import net.minecraft.network.play.server.S12PacketEntityVelocity;
/*    */ import net.minecraft.network.play.server.S27PacketExplosion;
/*    */ import net.minecraft.util.IThreadListener;
/*    */ import net.minecraft.world.Explosion;
/*    */ import net.minecraft.world.World;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ @Mixin({NetHandlerPlayClient.class})
/*    */ public abstract class PlayHandlerMixin
/*    */ {
/*    */   @Shadow
/*    */   private Minecraft field_147299_f;
/*    */   @Shadow
/*    */   private WorldClient field_147300_g;
/*    */   
/*    */   @Inject(method = {"handleExplosion"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void handleExplosion(S27PacketExplosion packetIn, CallbackInfo ci) {
/* 37 */     if (OringoClient.velocity.isToggled() || (OringoClient.speed.isToggled() && OringoClient.speed.velocityStrafe.isEnabled())) {
/* 38 */       PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)OringoClient.mc.func_147114_u(), (IThreadListener)this.field_147299_f);
/* 39 */       Explosion explosion = new Explosion((World)this.field_147299_f.field_71441_e, null, packetIn.func_149148_f(), packetIn.func_149143_g(), packetIn.func_149145_h(), packetIn.func_149146_i(), packetIn.func_149150_j());
/* 40 */       explosion.func_77279_a(true);
/* 41 */       boolean shouldTakeKB = (OringoClient.velocity.skyblockKB.isEnabled() && ((Minecraft.func_71410_x()).field_71439_g.func_180799_ab() || SkyblockUtils.getDisplayName((Minecraft.func_71410_x()).field_71439_g.func_70694_bm()).contains("Bonzo's Staff") || SkyblockUtils.getDisplayName((Minecraft.func_71410_x()).field_71439_g.func_70694_bm()).contains("Jerry-chine Gun")));
/* 42 */       if ((shouldTakeKB || OringoClient.velocity.hModifier.getValue() != 0.0D || OringoClient.velocity.vModifier.getValue() != 0.0D) && !OringoClient.speed.isToggled()) {
/* 43 */         this.field_147299_f.field_71439_g.field_70159_w += packetIn.func_149149_c() * (shouldTakeKB ? 1.0D : OringoClient.velocity.hModifier.getValue());
/* 44 */         this.field_147299_f.field_71439_g.field_70181_x += packetIn.func_149144_d() * (shouldTakeKB ? 1.0D : OringoClient.velocity.vModifier.getValue());
/* 45 */         this.field_147299_f.field_71439_g.field_70179_y += packetIn.func_149147_e() * (shouldTakeKB ? 1.0D : OringoClient.velocity.hModifier.getValue());
/*    */       } 
/* 47 */       if (MovementUtils.isMoving() && OringoClient.speed.velocityStrafe.isEnabled() && OringoClient.speed.isToggled() && OringoClient.speed.disable.hasTimePassed(1000L)) {
/* 48 */         if (MovementUtils.getSpeed() < MovementUtils.getSpeed(OringoClient.mc.field_71439_g.field_70159_w + packetIn.func_149149_c() * OringoClient.speed.boostSpeed.getValue(), OringoClient.mc.field_71439_g.field_70179_y + packetIn.func_149147_e() * OringoClient.speed.boostSpeed.getValue())) {
/* 49 */           OringoClient.mc.field_71439_g.field_70159_w += packetIn.func_149149_c() * OringoClient.speed.boostSpeed.getValue();
/* 50 */           OringoClient.mc.field_71439_g.field_70179_y += packetIn.func_149147_e() * OringoClient.speed.boostSpeed.getValue();
/*    */         } 
/* 52 */         MovementUtils.strafe(true);
/* 53 */         OringoClient.speed.velocityTimer.updateTime();
/*    */       } 
/* 55 */       ci.cancel();
/*    */     }  } @Shadow
/*    */   private boolean field_147309_h; @Shadow
/*    */   @Final
/*    */   private NetworkManager field_147302_e; @Inject(method = {"handleEntityVelocity"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void handleEntityVelocity(S12PacketEntityVelocity packetIn, CallbackInfo ci) {
/* 61 */     if (OringoClient.velocity.isToggled() || (OringoClient.speed.isToggled() && OringoClient.speed.velocityStrafe.isEnabled())) {
/* 62 */       PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)OringoClient.mc.func_147114_u(), (IThreadListener)this.field_147299_f);
/* 63 */       Entity entity = this.field_147300_g.func_73045_a(packetIn.func_149412_c());
/* 64 */       if (entity != null)
/* 65 */         if (entity.equals(OringoClient.mc.field_71439_g)) {
/* 66 */           boolean shouldTakeKB = (OringoClient.velocity.skyblockKB.isEnabled() && ((Minecraft.func_71410_x()).field_71439_g.func_180799_ab() || SkyblockUtils.getDisplayName((Minecraft.func_71410_x()).field_71439_g.func_70694_bm()).contains("Bonzo's Staff") || SkyblockUtils.getDisplayName((Minecraft.func_71410_x()).field_71439_g.func_70694_bm()).contains("Jerry-chine Gun")));
/* 67 */           if ((shouldTakeKB || OringoClient.velocity.hModifier.getValue() != 0.0D || OringoClient.velocity.vModifier.getValue() != 0.0D) && !OringoClient.speed.isToggled()) {
/* 68 */             entity.func_70016_h(packetIn.func_149411_d() * (shouldTakeKB ? 1.0D : OringoClient.velocity.hModifier.getValue()) / 8000.0D, packetIn
/* 69 */                 .func_149410_e() * (shouldTakeKB ? 1.0D : OringoClient.velocity.vModifier.getValue()) / 8000.0D, packetIn
/* 70 */                 .func_149409_f() * (shouldTakeKB ? 1.0D : OringoClient.velocity.hModifier.getValue()) / 8000.0D);
/*    */           }
/* 72 */           if (MovementUtils.isMoving() && OringoClient.speed.velocityStrafe.isEnabled() && OringoClient.speed.isToggled() && OringoClient.speed.disable.hasTimePassed(1000L)) {
/* 73 */             if (MovementUtils.getSpeed() < MovementUtils.getSpeed(packetIn.func_149411_d() * OringoClient.speed.boostSpeed.getValue() / 8000.0D, packetIn.func_149409_f() * OringoClient.speed.boostSpeed.getValue() / 8000.0D)) {
/* 74 */               OringoClient.mc.field_71439_g.field_70159_w = packetIn.func_149411_d() * OringoClient.speed.boostSpeed.getValue() / 8000.0D;
/* 75 */               OringoClient.mc.field_71439_g.field_70179_y = packetIn.func_149409_f() * OringoClient.speed.boostSpeed.getValue() / 8000.0D;
/*    */             } 
/* 77 */             MovementUtils.strafe(true);
/* 78 */             OringoClient.speed.velocityTimer.updateTime();
/*    */           } 
/*    */         } else {
/* 81 */           entity.func_70016_h(packetIn.func_149411_d() / 8000.0D, packetIn.func_149410_e() / 8000.0D, packetIn.func_149409_f() / 8000.0D);
/*    */         }  
/* 83 */       ci.cancel();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\mixins\PlayHandlerMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */