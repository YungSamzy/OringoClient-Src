/*    */ package me.oringo.oringoclient.mixins.renderer;
/*    */ 
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import net.minecraft.client.entity.AbstractClientPlayer;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.entity.RenderPlayer;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ @Mixin({RenderPlayer.class})
/*    */ public abstract class MixinRenderPlayer
/*    */   extends MixinRenderLivingEntity {
/*    */   @Inject(method = {"preRenderCallback(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V"}, at = {@At("HEAD")})
/*    */   public void onPreRenderCallback(AbstractClientPlayer entitylivingbaseIn, float partialTickTime, CallbackInfo ci) {
/* 17 */     if (OringoClient.giants.isToggled())
/* 18 */       GlStateManager.func_179139_a(OringoClient.giants.scale.getValue(), OringoClient.giants.scale.getValue(), OringoClient.giants.scale.getValue()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\mixins\renderer\MixinRenderPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */