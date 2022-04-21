/*    */ package me.oringo.oringoclient.events;
/*    */ 
/*    */ import net.minecraftforge.fml.common.eventhandler.Cancelable;
/*    */ import net.minecraftforge.fml.common.eventhandler.Event;
/*    */ 
/*    */ @Cancelable
/*    */ public class MoveEvent extends Event {
/*    */   public double x;
/*    */   
/*    */   public MoveEvent(double x, double y, double z) {
/* 11 */     this.x = x;
/* 12 */     this.z = z;
/* 13 */     this.y = y;
/*    */   }
/*    */   
/*    */   public double y;
/*    */   public double z;
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\events\MoveEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */