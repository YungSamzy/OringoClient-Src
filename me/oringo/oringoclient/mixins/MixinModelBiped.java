/*    */ package me.oringo.oringoclient.mixins;
/*    */ 
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.mixins.entity.PlayerSPAccessor;
/*    */ import me.oringo.oringoclient.qolfeatures.module.combat.KillAura;
/*    */ import me.oringo.oringoclient.qolfeatures.module.render.ServerRotations;
/*    */ import me.oringo.oringoclient.utils.ReflectionUtils;
/*    */ import me.oringo.oringoclient.utils.RotationUtils;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.model.ModelBiped;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.Timer;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({ModelBiped.class})
/*    */ public class MixinModelBiped
/*    */ {
/*    */   @Shadow
/*    */   public ModelRenderer field_178723_h;
/*    */   @Shadow
/*    */   public int field_78120_m;
/*    */   @Shadow
/*    */   public ModelRenderer field_78116_c;
/*    */   @Shadow
/*    */   public ModelRenderer field_78115_e;
/*    */   
/*    */   @Inject(method = {"setRotationAngles"}, at = {@At(value = "FIELD", target = "Lnet/minecraft/client/model/ModelBiped;swingProgress:F")})
/*    */   private void setRotationAngles(float p_setRotationAngles_1_, float p_setRotationAngles_2_, float p_setRotationAngles_3_, float p_setRotationAngles_4_, float p_setRotationAngles_5_, float p_setRotationAngles_6_, Entity p_setRotationAngles_7_, CallbackInfo callbackInfo) {
/* 37 */     if (!ServerRotations.getInstance().isToggled())
/* 38 */       return;  if ((!(ServerRotations.getInstance()).onlyKillAura.isEnabled() || KillAura.target != null) && p_setRotationAngles_7_ != null && p_setRotationAngles_7_.equals((Minecraft.func_71410_x()).field_71439_g)) {
/* 39 */       Timer timer = (Timer)ReflectionUtils.getFieldByName(Minecraft.class, "field_71428_T", OringoClient.mc);
/* 40 */       if (timer != null)
/* 41 */         this.field_78116_c.field_78795_f = (RotationUtils.lastReportedPitch + (((PlayerSPAccessor)p_setRotationAngles_7_).getLastReportedPitch() - RotationUtils.lastReportedPitch) * (((MinecraftAccessor)OringoClient.mc).getTimer()).field_74281_c) / 57.295776F; 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\mixins\MixinModelBiped.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */