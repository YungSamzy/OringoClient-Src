/*    */ package me.oringo.oringoclient.mixins.renderer;
/*    */ 
/*    */ import me.oringo.oringoclient.events.RenderLayersEvent;
/*    */ import me.oringo.oringoclient.events.RenderModelEvent;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.RendererLivingEntity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.fml.common.eventhandler.Event;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({RendererLivingEntity.class})
/*    */ public abstract class MixinRenderLivingEntity
/*    */   extends RenderMixin
/*    */ {
/*    */   @Shadow
/*    */   protected ModelBase field_77045_g;
/*    */   @Shadow
/*    */   protected boolean field_177098_i;
/*    */   @Shadow
/*    */   @Final
/*    */   private static Logger field_147923_a;
/*    */   
/*    */   @Shadow
/*    */   protected abstract <T extends EntityLivingBase> float func_77040_d(T paramT, float paramFloat);
/*    */   
/*    */   @Shadow
/*    */   protected abstract float func_77034_a(float paramFloat1, float paramFloat2, float paramFloat3);
/*    */   
/*    */   @Shadow
/*    */   protected abstract <T extends EntityLivingBase> void func_77039_a(T paramT, double paramDouble1, double paramDouble2, double paramDouble3);
/*    */   
/*    */   @Shadow
/*    */   protected abstract <T extends EntityLivingBase> float func_77044_a(T paramT, float paramFloat);
/*    */   
/*    */   @Shadow
/*    */   protected abstract <T extends EntityLivingBase> void func_77043_a(T paramT, float paramFloat1, float paramFloat2, float paramFloat3);
/*    */   
/*    */   @Inject(method = {"renderModel"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private <T extends EntityLivingBase> void renderModelPre(T entity, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float scaleFactor, CallbackInfo ci) {
/* 56 */     if (MinecraftForge.EVENT_BUS.post((Event)new RenderModelEvent((EntityLivingBase)entity, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor, this.field_77045_g)))
/* 57 */       ci.cancel();  } @Shadow protected abstract <T extends EntityLivingBase> void func_77041_b(T paramT, float paramFloat); @Shadow protected abstract <T extends EntityLivingBase> boolean func_177088_c(T paramT); @Shadow protected abstract void func_180565_e(); @Shadow protected abstract <T extends EntityLivingBase> boolean func_177090_c(T paramT, float paramFloat); @Shadow
/*    */   public abstract void func_76986_a(Entity paramEntity, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2); @Shadow
/*    */   protected abstract void func_177091_f(); @Shadow
/*    */   protected abstract <T extends EntityLivingBase> void func_77036_a(T paramT, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6); @Shadow
/*    */   protected abstract <T extends EntityLivingBase> void func_177093_a(T paramT, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7); @Inject(method = {"renderLayers"}, at = {@At("RETURN")}, cancellable = true)
/* 62 */   protected void renderLayersPost(EntityLivingBase entitylivingbaseIn, float p_177093_2_, float p_177093_3_, float partialTicks, float p_177093_5_, float p_177093_6_, float p_177093_7_, float p_177093_8_, CallbackInfo ci) { if (MinecraftForge.EVENT_BUS.post((Event)new RenderLayersEvent(entitylivingbaseIn, p_177093_2_, p_177093_3_, p_177093_5_, p_177093_6_, p_177093_7_, p_177093_8_, this.field_77045_g)))
/* 63 */       ci.cancel();  }
/*    */ 
/*    */   
/*    */   @Inject(method = {"renderModel"}, at = {@At("RETURN")}, cancellable = true)
/*    */   private <T extends EntityLivingBase> void renderModelPost(T entity, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float scaleFactor, CallbackInfo ci) {
/* 68 */     if (MinecraftForge.EVENT_BUS.post((Event)new RenderModelEvent.Post((EntityLivingBase)entity, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor, this.field_77045_g)))
/* 69 */       ci.cancel(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\mixins\renderer\MixinRenderLivingEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */