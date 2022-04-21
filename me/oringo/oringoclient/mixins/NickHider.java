/*    */ package me.oringo.oringoclient.mixins;
/*    */ 
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({FontRenderer.class})
/*    */ public abstract class NickHider
/*    */ {
/*    */   @Shadow
/*    */   protected abstract void func_78255_a(String paramString, boolean paramBoolean);
/*    */   
/*    */   @Inject(method = {"renderStringAtPos"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void renderString(String text, boolean shadow, CallbackInfo ci) {
/* 24 */     if (OringoClient.nickHider.isToggled() && text.contains(OringoClient.mc.func_110432_I().func_111285_a())) {
/* 25 */       ci.cancel();
/* 26 */       func_78255_a(text.replaceAll(OringoClient.mc.func_110432_I().func_111285_a(), OringoClient.nickHider.name.getValue()), shadow);
/*    */     } 
/*    */   } @Shadow
/*    */   public abstract int func_78256_a(String paramString); @Shadow
/*    */   public abstract int func_78263_a(char paramChar); @Inject(method = {"getStringWidth"}, at = {@At("RETURN")}, cancellable = true)
/*    */   private void getStringWidth(String text, CallbackInfoReturnable<Integer> cir) {
/* 32 */     if (text != null && OringoClient.nickHider.isToggled() && text.contains(OringoClient.mc.func_110432_I().func_111285_a()))
/* 33 */       cir.setReturnValue(Integer.valueOf(func_78256_a(text.replaceAll(OringoClient.mc.func_110432_I().func_111285_a(), OringoClient.nickHider.name.getValue())))); 
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\mixins\NickHider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */