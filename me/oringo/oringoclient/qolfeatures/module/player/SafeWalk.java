/*    */ package me.oringo.oringoclient.qolfeatures.module.player;
/*    */ 
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import net.minecraft.client.settings.GameSettings;
/*    */ import net.minecraft.client.settings.KeyBinding;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*    */ 
/*    */ public class SafeWalk
/*    */   extends Module
/*    */ {
/*    */   public SafeWalk() {
/* 16 */     super("Eagle", 0, Module.Category.PLAYER);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDisable() {
/* 21 */     KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_74311_E.func_151463_i(), GameSettings.func_100015_a(OringoClient.mc.field_71474_y.field_74311_E));
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onTick(TickEvent.ClientTickEvent event) {
/* 26 */     if (OringoClient.mc.field_71439_g == null || OringoClient.mc.field_71441_e == null || !isToggled() || OringoClient.mc.field_71462_r != null)
/* 27 */       return;  BlockPos BP = new BlockPos(OringoClient.mc.field_71439_g.field_70165_t, OringoClient.mc.field_71439_g.field_70163_u - 0.5D, OringoClient.mc.field_71439_g.field_70161_v);
/* 28 */     if (OringoClient.mc.field_71441_e.func_180495_p(BP).func_177230_c() == Blocks.field_150350_a && OringoClient.mc.field_71441_e.func_180495_p(BP.func_177977_b()).func_177230_c() == Blocks.field_150350_a && OringoClient.mc.field_71439_g.field_70122_E && OringoClient.mc.field_71439_g.field_71158_b.field_78900_b < 0.1F) {
/* 29 */       KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_74311_E.func_151463_i(), true);
/*    */     } else {
/* 31 */       KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_74311_E.func_151463_i(), GameSettings.func_100015_a(OringoClient.mc.field_71474_y.field_74311_E));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\player\SafeWalk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */