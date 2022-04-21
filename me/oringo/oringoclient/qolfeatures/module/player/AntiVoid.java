/*    */ package me.oringo.oringoclient.qolfeatures.module.player;
/*    */ 
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.events.MotionUpdateEvent;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class AntiVoid extends Module {
/*    */   private boolean tp;
/* 13 */   public NumberSetting fallDistance = new NumberSetting("Fall distance", 1.0D, 0.5D, 5.0D, 0.1D); private boolean canTp; private double x; private double y; private double z;
/*    */   
/*    */   public AntiVoid() {
/* 16 */     super("Anti Void", 0, Module.Category.PLAYER);
/*    */ 
/*    */ 
/*    */     
/* 20 */     this.canTp = true;
/*    */     addSettings(new Setting[] { (Setting)this.fallDistance });
/*    */   }
/*    */   @SubscribeEvent
/*    */   public void onMovePre(MotionUpdateEvent.Pre event) {
/* 25 */     if (!isToggled() || !this.canTp || OringoClient.mc.field_71439_g.field_70143_R < this.fallDistance.getValue() || OringoClient.mc.field_71439_g.field_71075_bZ.field_75101_c)
/* 26 */       return;  BlockPos block = new BlockPos(OringoClient.mc.field_71439_g.field_70165_t, OringoClient.mc.field_71439_g.field_70163_u, OringoClient.mc.field_71439_g.field_70161_v);
/* 27 */     for (int i = (int)OringoClient.mc.field_71439_g.field_70163_u; i > 0; i--) {
/* 28 */       if (!(OringoClient.mc.field_71441_e.func_180495_p(block).func_177230_c() instanceof net.minecraft.block.BlockAir)) {
/*    */         return;
/*    */       }
/* 31 */       block = block.func_177982_a(0, -1, 0);
/*    */     } 
/* 33 */     this.tp = true;
/* 34 */     this.canTp = false;
/* 35 */     this.x = OringoClient.mc.field_71439_g.field_70165_t;
/* 36 */     this.y = OringoClient.mc.field_71439_g.field_70163_u;
/* 37 */     this.z = OringoClient.mc.field_71439_g.field_70161_v;
/* 38 */     OringoClient.mc.field_71439_g.func_70107_b(this.x + 1000.0D, this.y, this.z + 1000.0D);
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onMovePost(MotionUpdateEvent.Post event) {
/* 43 */     if (this.tp) {
/* 44 */       OringoClient.mc.field_71439_g.func_70107_b(this.x, this.y, this.z);
/* 45 */       OringoClient.mc.field_71439_g.func_70016_h(0.0D, 0.0D, 0.0D);
/* 46 */       this.tp = false;
/* 47 */       (new Thread(() -> {
/*    */             try {
/*    */               Thread.sleep(750L);
/* 50 */             } catch (InterruptedException e) {
/*    */               e.printStackTrace();
/*    */             } 
/*    */             this.canTp = true;
/* 54 */           })).start();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\player\AntiVoid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */