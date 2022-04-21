/*    */ package me.oringo.oringoclient.qolfeatures.module.skyblock;
/*    */ 
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.events.BlockChangeEvent;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.utils.SkyblockUtils;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraftforge.event.world.WorldEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*    */ 
/*    */ public class AutoS1 extends Module {
/*    */   private boolean clicked;
/*    */   
/*    */   public AutoS1() {
/* 21 */     super("Auto SS", 0, Module.Category.SKYBLOCK);
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean clickedButton;
/*    */   private static BlockPos clickPos;
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onTick(TickEvent.ClientTickEvent event) {
/* 30 */     if (OringoClient.mc.field_71439_g == null || !SkyblockUtils.inDungeon || !isToggled() || !SkyblockUtils.inP3)
/* 31 */       return;  if (OringoClient.mc.field_71439_g.func_174824_e(0.0F).func_72438_d(new Vec3(309.0D, 121.0D, 290.0D)) < 5.5D && !this.clicked && OringoClient.mc.field_71441_e.func_180495_p(new BlockPos(309, 121, 290)).func_177230_c() == Blocks.field_150430_aB) {
/* 32 */       clickBlock(new BlockPos(309, 121, 290));
/* 33 */       this.clicked = true;
/* 34 */       this.clickedButton = false;
/*    */     } 
/* 36 */     if (clickPos != null && OringoClient.mc.field_71439_g.func_70011_f(clickPos.func_177958_n(), (clickPos.func_177956_o() - OringoClient.mc.field_71439_g.func_70047_e()), clickPos.func_177952_p()) < 5.5D && !this.clickedButton && OringoClient.mc.field_71441_e.func_180495_p(clickPos).func_177230_c() == Blocks.field_150430_aB) {
/* 37 */       for (int i = 0; i < 20; i++)
/* 38 */         clickBlock(clickPos); 
/* 39 */       clickPos = null;
/* 40 */       this.clickedButton = true;
/* 41 */       OringoClient.sendMessageWithPrefix("Clicked!");
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onPacket(BlockChangeEvent event) {
/* 47 */     if (this.clicked && !this.clickedButton && SkyblockUtils.inP3 && event.state.func_177230_c() == Blocks.field_180398_cJ && event.pos.func_177958_n() == 310 && event.pos.func_177956_o() >= 120 && event.pos.func_177956_o() <= 123 && event.pos.func_177952_p() >= 291 && event.pos.func_177952_p() <= 294) {
/* 48 */       clickPos = new BlockPos(event.pos.func_177958_n() - 1, event.pos.func_177956_o(), event.pos.func_177952_p());
/*    */     }
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onWorldChange(WorldEvent.Load event) {
/* 54 */     this.clicked = false;
/* 55 */     clickPos = null;
/* 56 */     this.clickedButton = false;
/*    */   }
/*    */   
/*    */   private void clickBlock(BlockPos hitPos) {
/* 60 */     Vec3 hitVec = new Vec3(0.0D, 0.0D, 0.0D);
/* 61 */     float f = (float)(hitVec.field_72450_a - hitPos.func_177958_n());
/* 62 */     float f1 = (float)(hitVec.field_72448_b - hitPos.func_177956_o());
/* 63 */     float f2 = (float)(hitVec.field_72449_c - hitPos.func_177952_p());
/* 64 */     OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C08PacketPlayerBlockPlacement(hitPos, EnumFacing.func_176733_a(OringoClient.mc.field_71439_g.field_70177_z).func_176745_a(), OringoClient.mc.field_71439_g.field_71071_by.func_70448_g(), f, f1, f2));
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\skyblock\AutoS1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */