/*    */ package me.oringo.oringoclient.qolfeatures.module.combat;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*    */ 
/*    */ public class NoSlow extends Module {
/*    */   public NoSlow() {
/*  8 */     super("NoSlow", 0, Module.Category.COMBAT);
/*    */ 
/*    */     
/* 11 */     this.eatingSlowdown = new NumberSetting("Eating slow", 0.5D, 0.2D, 1.0D, 0.1D); this.swordSlowdown = new NumberSetting("Sword slow", 1.0D, 0.2D, 1.0D, 0.1D); this.bowSlowdown = new NumberSetting("Bow slow", 1.0D, 0.2D, 1.0D, 0.1D);
/*    */     addSettings(new Setting[] { (Setting)this.swordSlowdown, (Setting)this.bowSlowdown, (Setting)this.eatingSlowdown });
/*    */   }
/*    */   
/*    */   public NumberSetting eatingSlowdown;
/*    */   public NumberSetting swordSlowdown;
/*    */   public NumberSetting bowSlowdown;
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\combat\NoSlow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */