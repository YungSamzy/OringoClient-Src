/*    */ package me.oringo.oringoclient.qolfeatures.module.render;
/*    */ 
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.ModeSetting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*    */ 
/*    */ public class Animations extends Module {
/* 10 */   public NumberSetting x = new NumberSetting("x", 1.0D, 0.01D, 3.0D, 0.02D); public NumberSetting y = new NumberSetting("y", 1.0D, 0.01D, 3.0D, 0.02D); public NumberSetting z = new NumberSetting("z", 1.0D, 0.01D, 3.0D, 0.02D); public NumberSetting size = new NumberSetting("size", 1.0D, 0.01D, 3.0D, 0.02D);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 15 */   public ModeSetting mode = new ModeSetting("Mode", "1.7", new String[] { "1.7", "chill", "push", "spin", "vertical spin", "helicopter" });
/*    */   
/* 17 */   public BooleanSetting showSwing = new BooleanSetting("Swing progress", false);
/*    */ 
/*    */   
/*    */   public Animations() {
/* 21 */     super("Animations", Module.Category.RENDER);
/* 22 */     addSettings(new Setting[] { (Setting)this.x, (Setting)this.y, (Setting)this.z, (Setting)this.size, (Setting)this.mode, (Setting)this.showSwing });
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\render\Animations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */