/*    */ package me.oringo.oringoclient.events;
/*    */ 
/*    */ import net.minecraftforge.fml.common.eventhandler.Cancelable;
/*    */ import net.minecraftforge.fml.common.eventhandler.Event;
/*    */ 
/*    */ @Cancelable
/*    */ public class MotionUpdateEvent extends Event {
/*    */   public float yaw;
/*    */   public float pitch;
/*    */   public double x;
/*    */   public double y;
/*    */   
/*    */   protected MotionUpdateEvent(double x, double y, double z, float yaw, float pitch, boolean onGround, boolean sprinting, boolean sneaking) {
/* 14 */     this.x = x;
/* 15 */     this.y = y;
/* 16 */     this.z = z;
/* 17 */     this.yaw = yaw;
/* 18 */     this.pitch = pitch;
/* 19 */     this.onGround = onGround;
/* 20 */     this.sneaking = sneaking;
/* 21 */     this.sprinting = sprinting;
/*    */   }
/*    */   public double z; public boolean onGround; public boolean sprinting;
/*    */   public boolean sneaking;
/*    */   
/*    */   @Cancelable
/*    */   public static class Pre extends MotionUpdateEvent { public Pre(double x, double y, double z, float yaw, float pitch, boolean onGround, boolean sprinting, boolean sneaking) {
/* 28 */       super(x, y, z, yaw, pitch, onGround, sprinting, sneaking);
/*    */     } }
/*    */ 
/*    */   
/*    */   @Cancelable
/*    */   public static class Post extends MotionUpdateEvent {
/*    */     public Post(double x, double y, double z, float yaw, float pitch, boolean onGround, boolean sprinting, boolean sneaking) {
/* 35 */       super(x, y, z, yaw, pitch, onGround, sprinting, sneaking);
/*    */     }
/*    */     
/*    */     public Post(MotionUpdateEvent event) {
/* 39 */       super(event.x, event.y, event.z, event.yaw, event.pitch, event.onGround, event.sprinting, event.sneaking);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\events\MotionUpdateEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */