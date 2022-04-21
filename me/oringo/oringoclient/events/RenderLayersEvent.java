/*    */ package me.oringo.oringoclient.events;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraftforge.fml.common.eventhandler.Cancelable;
/*    */ import net.minecraftforge.fml.common.eventhandler.Event;
/*    */ 
/*    */ @Cancelable
/*    */ public class RenderLayersEvent extends Event {
/*    */   public EntityLivingBase entity;
/*    */   public float p_77036_2_;
/*    */   public float p_77036_3_;
/*    */   public float p_77036_4_;
/*    */   
/*    */   public RenderLayersEvent(EntityLivingBase entity, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float scaleFactor, ModelBase modelBase) {
/* 16 */     this.entity = entity;
/* 17 */     this.p_77036_2_ = p_77036_2_;
/* 18 */     this.p_77036_3_ = p_77036_3_;
/* 19 */     this.p_77036_4_ = p_77036_4_;
/* 20 */     this.p_77036_5_ = p_77036_5_;
/* 21 */     this.p_77036_6_ = p_77036_6_;
/* 22 */     this.scaleFactor = scaleFactor;
/* 23 */     this.modelBase = modelBase;
/*    */   }
/*    */   
/*    */   public float p_77036_5_;
/*    */   public float p_77036_6_;
/*    */   public float scaleFactor;
/*    */   public ModelBase modelBase;
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\events\RenderLayersEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */