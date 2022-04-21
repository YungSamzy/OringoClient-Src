/*    */ package me.oringo.oringoclient.mixins;
/*    */ 
/*    */ import net.minecraft.client.gui.GuiRepair;
/*    */ import net.minecraft.client.gui.GuiTextField;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ @Mixin({GuiRepair.class})
/*    */ public class AnvilMixin
/*    */ {
/*    */   @Shadow
/*    */   private GuiTextField field_147091_w;
/*    */   
/*    */   @Inject(method = {"initGui"}, at = {@At("RETURN")})
/*    */   private void initGui(CallbackInfo callbackInfo) {
/* 19 */     this.field_147091_w.func_146203_f(32767);
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\mixins\AnvilMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */