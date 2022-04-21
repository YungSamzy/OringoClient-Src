/*    */ package me.oringo.oringoclient.qolfeatures.module.render;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*    */ 
/*    */ public class ServerRotations extends Module {
/*  7 */   private static ServerRotations instance = new ServerRotations();
/*    */   
/*    */   public static ServerRotations getInstance() {
/* 10 */     return instance;
/*    */   }
/*    */   
/* 13 */   public BooleanSetting onlyKillAura = new BooleanSetting("Only aura rotations", false);
/*    */   
/*    */   public ServerRotations() {
/* 16 */     super("Server Rotations", Module.Category.RENDER);
/* 17 */     setToggled(true);
/* 18 */     addSettings(new Setting[] { (Setting)this.onlyKillAura });
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\render\ServerRotations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */