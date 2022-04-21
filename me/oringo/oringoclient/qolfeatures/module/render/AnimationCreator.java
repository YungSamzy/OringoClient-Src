/*    */ package me.oringo.oringoclient.qolfeatures.module.render;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*    */ 
/*    */ public class AnimationCreator extends Module {
/*  8 */   public BooleanSetting swingProgress = new BooleanSetting("Swing Progress", false); public BooleanSetting blockProgress = new BooleanSetting("Block Progress", true);
/*    */ 
/*    */   
/* 11 */   public NumberSetting angle1 = new NumberSetting("angle1", 30.0D, -180.0D, 180.0D, 1.0D), angle2 = new NumberSetting("angle2", -80.0D, -180.0D, 180.0D, 1.0D), angle3 = new NumberSetting("angle3", 60.0D, -180.0D, 180.0D, 1.0D);
/*    */   
/* 13 */   public NumberSetting translateX = new NumberSetting("x1", -0.5D, -5.0D, 5.0D, 0.1D), translateY = new NumberSetting("y1", 0.2D, -5.0D, 5.0D, 0.1D), translateZ = new NumberSetting("z1", 0.0D, -5.0D, 5.0D, 0.1D);
/*    */ 
/*    */ 
/*    */   
/* 17 */   public NumberSetting rotation1x = new NumberSetting("x1", 0.0D, -5.0D, 5.0D, 0.1D), rotation1y = new NumberSetting("y1", 1.0D, -5.0D, 5.0D, 0.1D), rotation1z = new NumberSetting("z1", 0.0D, -5.0D, 5.0D, 0.1D);
/*    */ 
/*    */ 
/*    */   
/* 21 */   public NumberSetting rotation2x = new NumberSetting("x2", 1.0D, -5.0D, 5.0D, 0.1D), rotation2y = new NumberSetting("y2", 0.0D, -5.0D, 5.0D, 0.1D), rotation2z = new NumberSetting("z2", 0.0D, -5.0D, 5.0D, 0.1D);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AnimationCreator() {
/* 27 */     super("Animation helper", Module.Category.RENDER);
/* 28 */     addSettings(new Setting[] { (Setting)this.swingProgress, (Setting)this.blockProgress, (Setting)this.translateX, (Setting)this.translateY, (Setting)this.translateZ, (Setting)this.angle1, (Setting)this.rotation1x, (Setting)this.rotation1y, (Setting)this.rotation1z, (Setting)this.angle2, (Setting)this.rotation2x, (Setting)this.rotation2y, (Setting)this.rotation2z, (Setting)this.angle3 });
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\render\AnimationCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */