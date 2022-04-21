/*    */ package me.oringo.oringoclient.qolfeatures;
/*    */ 
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.settings.GameSettings;
/*    */ import net.minecraft.client.settings.KeyBinding;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*    */ 
/*    */ public class AttackQueue
/*    */ {
/*    */   public static boolean attack = false;
/* 16 */   private static int ticks = 0;
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onTick(TickEvent.ClientTickEvent event) {
/* 20 */     if (ticks != 0) ticks--; 
/* 21 */     Minecraft mc = Minecraft.func_71410_x();
/* 22 */     if (mc.field_71439_g != null && attack && (ticks == 0 || (mc.field_71476_x != null && mc.field_71476_x.field_72313_a.equals(MovingObjectPosition.MovingObjectType.ENTITY)))) {
/* 23 */       mc.field_71439_g.func_71038_i();
/* 24 */       if (mc.field_71476_x != null) {
/*    */         BlockPos blockpos;
/* 26 */         switch (mc.field_71476_x.field_72313_a) {
/*    */           
/*    */           case ENTITY:
/* 29 */             mc.field_71442_b.func_78764_a((EntityPlayer)mc.field_71439_g, mc.field_71476_x.field_72308_g);
/*    */             break;
/*    */           case BLOCK:
/* 32 */             blockpos = mc.field_71476_x.func_178782_a();
/*    */             
/* 34 */             if (mc.field_71441_e.func_180495_p(blockpos).func_177230_c().func_149688_o() != Material.field_151579_a) {
/*    */               
/* 36 */               mc.field_71442_b.func_180511_b(blockpos, mc.field_71476_x.field_178784_b);
/*    */               break;
/*    */             } 
/*    */ 
/*    */ 
/*    */           
/*    */           default:
/* 43 */             if (mc.field_71442_b.func_78762_g())
/*    */             {
/* 45 */               ticks = 10; } 
/*    */             break;
/*    */         } 
/*    */       } 
/* 49 */       attack = false;
/*    */     } 
/*    */     
/* 52 */     if (mc.field_71462_r instanceof me.oringo.oringoclient.gui.ClickGUI) {
/* 53 */       KeyBinding.func_74510_a(mc.field_71474_y.field_74351_w.func_151463_i(), GameSettings.func_100015_a(mc.field_71474_y.field_74351_w));
/* 54 */       KeyBinding.func_74510_a(mc.field_71474_y.field_74368_y.func_151463_i(), GameSettings.func_100015_a(mc.field_71474_y.field_74368_y));
/* 55 */       KeyBinding.func_74510_a(mc.field_71474_y.field_74370_x.func_151463_i(), GameSettings.func_100015_a(mc.field_71474_y.field_74370_x));
/* 56 */       KeyBinding.func_74510_a(mc.field_71474_y.field_74366_z.func_151463_i(), GameSettings.func_100015_a(mc.field_71474_y.field_74366_z));
/* 57 */       KeyBinding.func_74510_a(mc.field_71474_y.field_74314_A.func_151463_i(), GameSettings.func_100015_a(mc.field_71474_y.field_74314_A));
/* 58 */       KeyBinding.func_74510_a(mc.field_71474_y.field_151444_V.func_151463_i(), GameSettings.func_100015_a(mc.field_71474_y.field_151444_V));
/* 59 */       KeyBinding.func_74510_a(mc.field_71474_y.field_74311_E.func_151463_i(), GameSettings.func_100015_a(mc.field_71474_y.field_74311_E));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\AttackQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */