/*    */ package me.oringo.oringoclient.qolfeatures.module.player;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*    */ 
/*    */ public class Velocity extends Module {
/*  8 */   public NumberSetting vModifier = new NumberSetting("Vertical", 0.0D, -2.0D, 2.0D, 0.05D); public NumberSetting hModifier = new NumberSetting("Horizontal", 0.0D, -2.0D, 2.0D, 0.05D);
/*    */   
/* 10 */   public BooleanSetting skyblockKB = new BooleanSetting("Skyblock kb", true);
/*    */   
/*    */   public Velocity() {
/* 13 */     super("Velocity", 0, Module.Category.PLAYER);
/* 14 */     addSettings(new Setting[] { (Setting)this.hModifier, (Setting)this.vModifier, (Setting)this.skyblockKB });
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\player\Velocity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */