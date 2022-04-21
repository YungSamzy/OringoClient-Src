/*    */ package me.oringo.oringoclient.qolfeatures.module.combat;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*    */ 
/*    */ public class Reach extends Module {
/*  7 */   public NumberSetting reach = new NumberSetting("Range", 3.0D, 2.0D, 4.5D, 0.1D);
/*  8 */   public NumberSetting blockReach = new NumberSetting("Block Range", 4.5D, 2.0D, 6.0D, 0.01D);
/*    */   public Reach() {
/* 10 */     super("Reach", 0, Module.Category.COMBAT);
/* 11 */     addSettings(new Setting[] { (Setting)this.reach, (Setting)this.blockReach });
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\combat\Reach.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */