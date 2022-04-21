/*    */ package me.oringo.oringoclient.qolfeatures.module.combat;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*    */ 
/*    */ public class Hitboxes extends Module {
/*    */   public Hitboxes() {
/*  9 */     super("Hitboxes", Module.Category.COMBAT);
/*    */ 
/*    */ 
/*    */     
/* 13 */     this.onlyPlayers = new BooleanSetting("Only players", false);
/* 14 */     this.expand = new NumberSetting("Expand", 0.5D, 0.1D, 1.0D, 0.1D);
/*    */     addSettings(new Setting[] { (Setting)this.expand });
/*    */   }
/*    */   
/*    */   public BooleanSetting onlyPlayers;
/*    */   public NumberSetting expand;
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\combat\Hitboxes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */