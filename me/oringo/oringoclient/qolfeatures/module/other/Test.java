/*    */ package me.oringo.oringoclient.qolfeatures.module.other;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*    */ 
/*    */ public class Test extends Module {
/*    */   public Test() {
/*  8 */     super("Test", Module.Category.OTHER);
/*    */ 
/*    */ 
/*    */     
/* 12 */     this.speed = new NumberSetting("Speed", 1.0D, 0.7D, 2.0D, 0.1D);
/*    */     addSettings(new Setting[] { (Setting)this.speed });
/*    */   }
/*    */   public NumberSetting speed;
/*    */   public boolean isDevOnly() {
/* 17 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\other\Test.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */