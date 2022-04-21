/*    */ package me.oringo.oringoclient.qolfeatures.module.player;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*    */ 
/*    */ public class FastBreak extends Module {
/*  7 */   public NumberSetting mineSpeed = new NumberSetting("Mining speed", 1.4D, 1.0D, 1.6D, 0.1D), maxBlocks = new NumberSetting("Additional blocks", 0.0D, 0.0D, 1.0D, 1.0D);
/*    */ 
/*    */   
/*    */   public FastBreak() {
/* 11 */     super("Fast break", 0, Module.Category.PLAYER);
/* 12 */     addSettings(new Setting[] { (Setting)this.maxBlocks, (Setting)this.mineSpeed });
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\player\FastBreak.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */