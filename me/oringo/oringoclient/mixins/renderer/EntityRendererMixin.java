/*    */ package me.oringo.oringoclient.mixins.renderer;
/*    */ 
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import net.minecraft.client.multiplayer.PlayerControllerMP;
/*    */ import net.minecraft.client.renderer.EntityRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.util.Vec3;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.Redirect;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ @Mixin({EntityRenderer.class})
/*    */ public class EntityRendererMixin
/*    */ {
/*    */   @Shadow
/*    */   private float field_78491_C;
/*    */   @Shadow
/*    */   private float field_78490_B;
/*    */   
/*    */   @Redirect(method = {"setupFog"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;isPotionActive(Lnet/minecraft/potion/Potion;)Z"))
/*    */   public boolean removeBlindness(EntityLivingBase instance, Potion potionIn) {
/* 28 */     return false;
/*    */   }
/*    */   
/*    */   @Inject(method = {"hurtCameraEffect"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void hurtCam(float entitylivingbase, CallbackInfo ci) {
/* 33 */     if (OringoClient.camera.noHurtCam.isEnabled() && OringoClient.camera.isToggled()) ci.cancel(); 
/*    */   }
/*    */   
/*    */   @Redirect(method = {"orientCamera"}, at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/EntityRenderer;thirdPersonDistanceTemp:F"))
/*    */   public float thirdPersonDistanceTemp(EntityRenderer instance) {
/* 38 */     return OringoClient.camera.isToggled() ? (float)OringoClient.camera.cameraDistance.getValue() : this.field_78491_C;
/*    */   }
/*    */   
/*    */   @Redirect(method = {"orientCamera"}, at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/EntityRenderer;thirdPersonDistance:F"))
/*    */   public float thirdPersonDistance(EntityRenderer instance) {
/* 43 */     return OringoClient.camera.isToggled() ? (float)OringoClient.camera.cameraDistance.getValue() : this.field_78490_B;
/*    */   }
/*    */ 
/*    */   
/*    */   @Redirect(method = {"orientCamera"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Vec3;distanceTo(Lnet/minecraft/util/Vec3;)D"))
/*    */   public double onCamera(Vec3 instance, Vec3 vec) {
/* 49 */     return (OringoClient.camera.isToggled() && OringoClient.camera.cameraClip.isEnabled()) ? OringoClient.camera.cameraDistance.getValue() : instance.func_72438_d(vec);
/*    */   }
/*    */ 
/*    */   
/*    */   @Redirect(method = {"getMouseOver"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;getBlockReachDistance()F"))
/*    */   private float getBlockReachDistance(PlayerControllerMP instance) {
/* 55 */     return OringoClient.reach.isToggled() ? (float)OringoClient.reach.blockReach.getValue() : OringoClient.mc.field_71442_b.func_78757_d();
/*    */   }
/*    */   
/*    */   @Redirect(method = {"getMouseOver"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Vec3;distanceTo(Lnet/minecraft/util/Vec3;)D", ordinal = 2))
/*    */   private double distanceTo(Vec3 instance, Vec3 vec) {
/* 60 */     return (OringoClient.reach.isToggled() && instance.func_72438_d(vec) + (
/* 61 */       OringoClient.hitboxes.isToggled() ? OringoClient.hitboxes.expand.getValue() : 0.0D) <= OringoClient.reach.reach.getValue()) ? 2.9000000953674316D : (instance
/* 62 */       .func_72438_d(vec) + (OringoClient.hitboxes.isToggled() ? OringoClient.hitboxes.expand.getValue() : 0.0D));
/*    */   }
/*    */   
/*    */   @Redirect(method = {"getMouseOver"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getCollisionBorderSize()F"))
/*    */   private float getCollisionBorderSize(Entity instance) {
/* 67 */     return OringoClient.hitboxes.isToggled() ? ((float)OringoClient.hitboxes.expand.getValue() + 0.1F) : 0.1F;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\mixins\renderer\EntityRendererMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */