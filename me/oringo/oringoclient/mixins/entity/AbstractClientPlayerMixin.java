/*    */ package me.oringo.oringoclient.mixins.entity;
/*    */ 
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import net.minecraft.client.entity.AbstractClientPlayer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.commons.codec.digest.DigestUtils;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ @Mixin({AbstractClientPlayer.class})
/*    */ public abstract class AbstractClientPlayerMixin
/*    */   extends PlayerMixin
/*    */ {
/*    */   private static ResourceLocation getCape(String uuid) {
/* 17 */     return (ResourceLocation)OringoClient.capes.get(DigestUtils.sha256Hex(uuid));
/*    */   }
/*    */   
/*    */   @Inject(method = {"getLocationCape"}, at = {@At("RETURN")}, cancellable = true)
/*    */   public void getLocationCape(CallbackInfoReturnable<ResourceLocation> cir) {
/* 22 */     ResourceLocation minecons = getCape(((AbstractClientPlayer)this).func_110124_au().toString());
/* 23 */     if (minecons != null)
/* 24 */       cir.setReturnValue(minecons); 
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\mixins\entity\AbstractClientPlayerMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */