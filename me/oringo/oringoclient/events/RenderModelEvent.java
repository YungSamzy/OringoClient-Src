/*    */ package me.oringo.oringoclient.events;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraftforge.fml.common.eventhandler.Event;
/*    */ 
/*    */ public class RenderModelEvent extends Event {
/*    */   public EntityLivingBase entity;
/*    */   public float p_77036_2_;
/*    */   public float p_77036_3_;
/*    */   public float p_77036_4_;
/*    */   
/*    */   public RenderModelEvent(EntityLivingBase entity, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float scaleFactor, ModelBase modelBase) {
/* 13 */     this.entity = entity;
/* 14 */     this.p_77036_2_ = p_77036_2_;
/* 15 */     this.p_77036_3_ = p_77036_3_;
/* 16 */     this.p_77036_4_ = p_77036_4_;
/* 17 */     this.p_77036_5_ = p_77036_5_;
/* 18 */     this.p_77036_6_ = p_77036_6_;
/* 19 */     this.scaleFactor = scaleFactor;
/* 20 */     this.modelBase = modelBase;
/*    */   }
/*    */   public float p_77036_5_; public float p_77036_6_; public float scaleFactor; public ModelBase modelBase;
/*    */   public static class Post extends Event { public EntityLivingBase entity; public float p_77036_2_; public float p_77036_3_; public float p_77036_4_; public float p_77036_5_;
/*    */     public float p_77036_6_;
/*    */     public float scaleFactor;
/*    */     public ModelBase modelBase;
/*    */     
/*    */     public Post(EntityLivingBase entity, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float scaleFactor, ModelBase modelBase) {
/* 29 */       this.entity = entity;
/* 30 */       this.p_77036_2_ = p_77036_2_;
/* 31 */       this.p_77036_3_ = p_77036_3_;
/* 32 */       this.p_77036_4_ = p_77036_4_;
/* 33 */       this.p_77036_5_ = p_77036_5_;
/* 34 */       this.p_77036_6_ = p_77036_6_;
/* 35 */       this.scaleFactor = scaleFactor;
/* 36 */       this.modelBase = modelBase;
/*    */     } }
/*    */ 
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\events\RenderModelEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */