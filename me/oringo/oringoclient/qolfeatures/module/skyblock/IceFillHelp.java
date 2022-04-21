/*    */ package me.oringo.oringoclient.qolfeatures.module.skyblock;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.events.MoveEvent;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ 
/*    */ public class IceFillHelp extends Module {
/*    */   public NumberSetting slowdown;
/*    */   
/*    */   public IceFillHelp() {
/* 15 */     super("Ice Fill Helper", Module.Category.SKYBLOCK);
/*    */ 
/*    */     
/* 18 */     this.slowdown = new NumberSetting("Ice slowdown", 0.15D, 0.05D, 1.0D, 0.05D);
/* 19 */     this.noIceSlip = new BooleanSetting("No ice slip", true); this.autoStop = new BooleanSetting("Auto stop", true);
/*    */     addSettings(new Setting[] { (Setting)this.autoStop, (Setting)this.slowdown, (Setting)this.noIceSlip });
/*    */   } public BooleanSetting noIceSlip; public BooleanSetting autoStop;
/*    */   @SubscribeEvent
/*    */   public void onMove(MoveEvent event) {
/* 24 */     if (!isToggled() || !OringoClient.mc.field_71439_g.field_70122_E)
/* 25 */       return;  BlockPos currentPos = new BlockPos(OringoClient.mc.field_71439_g.field_70165_t, OringoClient.mc.field_71439_g.field_70163_u - 0.4D, OringoClient.mc.field_71439_g.field_70161_v);
/* 26 */     if (OringoClient.mc.field_71441_e.func_180495_p(currentPos).func_177230_c() == Blocks.field_150432_aD) {
/* 27 */       event.z *= this.slowdown.getValue();
/* 28 */       event.x *= this.slowdown.getValue();
/* 29 */       BlockPos nextPos = new BlockPos(OringoClient.mc.field_71439_g.field_70165_t + event.x, OringoClient.mc.field_71439_g.field_70163_u - 0.4D, OringoClient.mc.field_71439_g.field_70161_v + event.z);
/* 30 */       if (this.autoStop.isEnabled() && !currentPos.equals(nextPos) && OringoClient.mc.field_71441_e.func_180495_p(nextPos).func_177230_c() == Blocks.field_150432_aD) {
/* 31 */         event.x = 0.0D;
/* 32 */         event.z = 0.0D;
/*    */       } 
/*    */     } 
/*    */     
/* 36 */     if (this.noIceSlip.isEnabled()) {
/* 37 */       Blocks.field_150403_cj.field_149765_K = 0.6F;
/* 38 */       Blocks.field_150432_aD.field_149765_K = 0.6F;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDisable() {
/* 44 */     Blocks.field_150403_cj.field_149765_K = 0.98F;
/* 45 */     Blocks.field_150432_aD.field_149765_K = 0.98F;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\skyblock\IceFillHelp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */