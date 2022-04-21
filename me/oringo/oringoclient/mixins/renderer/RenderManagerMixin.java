/*    */ package me.oringo.oringoclient.mixins.renderer;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import me.oringo.oringoclient.commands.StalkCommand;
/*    */ import me.oringo.oringoclient.qolfeatures.module.macro.AutoSumoBot;
/*    */ import me.oringo.oringoclient.qolfeatures.module.other.AntiNicker;
/*    */ import me.oringo.oringoclient.utils.MobRenderUtils;
/*    */ import me.oringo.oringoclient.utils.RenderUtils;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({RenderManager.class})
/*    */ public abstract class RenderManagerMixin
/*    */ {
/*    */   @Inject(method = {"doRenderEntity"}, at = {@At("HEAD")})
/*    */   public void doRenderEntityPre(Entity entity, double x, double y, double z, float entityYaw, float partialTicks, boolean p_147939_10_, CallbackInfoReturnable<Boolean> cir) {
/* 25 */     if (entity.equals(AutoSumoBot.target)) {
/* 26 */       MobRenderUtils.setColor(new Color(255, 0, 0, 80));
/*    */     }
/* 28 */     if (AntiNicker.nicked.contains(entity)) {
/* 29 */       RenderUtils.enableChams();
/* 30 */       MobRenderUtils.setColor(new Color(255, 0, 0, 80));
/*    */     } 
/* 32 */     if (entity.func_110124_au().equals(StalkCommand.stalking)) {
/* 33 */       entity.func_82142_c(false);
/* 34 */       RenderUtils.enableChams();
/* 35 */       MobRenderUtils.setColor(new Color(64, 0, 255, 80));
/*    */     } 
/*    */   }
/*    */   
/*    */   @Inject(method = {"doRenderEntity"}, at = {@At("RETURN")})
/*    */   public void doRenderEntityPost(Entity entity, double x, double y, double z, float entityYaw, float partialTicks, boolean p_147939_10_, CallbackInfoReturnable<Boolean> cir) {
/* 41 */     if (entity.equals(AutoSumoBot.target)) {
/* 42 */       MobRenderUtils.unsetColor();
/*    */     }
/* 44 */     if (AntiNicker.nicked.contains(entity)) {
/* 45 */       RenderUtils.disableChams();
/* 46 */       MobRenderUtils.unsetColor();
/*    */     } 
/* 48 */     if (entity.func_110124_au().equals(StalkCommand.stalking)) {
/* 49 */       RenderUtils.disableChams();
/* 50 */       MobRenderUtils.unsetColor();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\mixins\renderer\RenderManagerMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */