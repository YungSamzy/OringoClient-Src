/*    */ package me.oringo.oringoclient.qolfeatures.module.render;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*    */ 
/*    */ public class Giants extends Module {
/*    */   public Giants() {
/*  8 */     super("Giants", Module.Category.RENDER);
/*    */ 
/*    */ 
/*    */     
/* 12 */     this.scale = new NumberSetting("Scale", 1.0D, 0.1D, 5.0D, 0.1D);
/*    */     addSettings(new Setting[] { (Setting)this.scale });
/*    */   }
/*    */   
/*    */   public NumberSetting scale;
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\render\Giants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */