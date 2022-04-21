/*    */ package me.oringo.oringoclient.qolfeatures.module.render;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*    */ 
/*    */ public class Camera extends Module {
/*    */   public Camera() {
/*  9 */     super("Camera", Module.Category.RENDER);
/*    */ 
/*    */     
/* 12 */     this.cameraDistance = new NumberSetting("Camera Distance", 4.0D, 2.0D, 10.0D, 0.1D);
/* 13 */     this.cameraClip = new BooleanSetting("Camera Clip", true); this.noHurtCam = new BooleanSetting("No hurt cam", false);
/*    */     addSettings(new Setting[] { (Setting)this.cameraDistance, (Setting)this.cameraClip, (Setting)this.noHurtCam });
/*    */   }
/*    */   
/*    */   public NumberSetting cameraDistance;
/*    */   public BooleanSetting cameraClip;
/*    */   public BooleanSetting noHurtCam;
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\render\Camera.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */