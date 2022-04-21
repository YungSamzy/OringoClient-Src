/*    */ package me.oringo.oringoclient.utils;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MilliTimer
/*    */ {
/*  7 */   private long time = System.currentTimeMillis();
/*    */ 
/*    */   
/*    */   public long getTime() {
/* 11 */     return this.time;
/*    */   }
/*    */   
/*    */   public long getTimePassed() {
/* 15 */     return System.currentTimeMillis() - this.time;
/*    */   }
/*    */   
/*    */   public boolean hasTimePassed(long milliseconds) {
/* 19 */     return (System.currentTimeMillis() - this.time >= milliseconds);
/*    */   }
/*    */   
/*    */   public void updateTime() {
/* 23 */     this.time = System.currentTimeMillis();
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclien\\utils\MilliTimer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */