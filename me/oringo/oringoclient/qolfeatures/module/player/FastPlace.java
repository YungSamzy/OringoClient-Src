/*    */ package me.oringo.oringoclient.qolfeatures.module.player;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*    */ 
/*    */ public class FastPlace extends Module {
/*  7 */   private static FastPlace instance = new FastPlace();
/*    */   
/*    */   public static FastPlace getInstance() {
/* 10 */     return instance;
/*    */   }
/*    */   
/* 13 */   public NumberSetting placeDelay = new NumberSetting("Place delay", 2.0D, 0.0D, 4.0D, 1.0D);
/*    */   
/*    */   public FastPlace() {
/* 16 */     super("Fast Place", Module.Category.PLAYER);
/* 17 */     addSetting((Setting)this.placeDelay);
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\player\FastPlace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */