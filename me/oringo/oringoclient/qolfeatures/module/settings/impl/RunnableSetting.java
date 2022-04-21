/*    */ package me.oringo.oringoclient.qolfeatures.module.settings.impl;
/*    */ 
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ 
/*    */ public class RunnableSetting extends Setting {
/*    */   private final Runnable runnable;
/*    */   
/*    */   public RunnableSetting(String name, Runnable runnable) {
/*  9 */     this.runnable = runnable;
/* 10 */     this.name = name;
/*    */   }
/*    */   
/*    */   public void execute() {
/* 14 */     this.runnable.run();
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\settings\impl\RunnableSetting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */