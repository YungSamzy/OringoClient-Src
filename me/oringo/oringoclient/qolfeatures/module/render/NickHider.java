/*    */ package me.oringo.oringoclient.qolfeatures.module.render;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.StringSetting;
/*    */ 
/*    */ public class NickHider extends Module {
/*  7 */   public StringSetting name = new StringSetting("Name");
/*    */   
/*    */   public NickHider() {
/* 10 */     super("Nick Hider", 0, Module.Category.RENDER);
/* 11 */     addSettings(new Setting[] { (Setting)this.name });
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\render\NickHider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */