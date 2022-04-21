/*    */ package me.oringo.oringoclient.qolfeatures.module.skyblock;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*    */ import me.oringo.oringoclient.utils.SkyblockUtils;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*    */ 
/*    */ public class Snowballs extends Module {
/*    */   private boolean wasPressed;
/*    */   public BooleanSetting pickupstash;
/*    */   
/*    */   public Snowballs() {
/* 17 */     super("Snowballs", Module.Category.SKYBLOCK);
/*    */ 
/*    */ 
/*    */     
/* 21 */     this.pickupstash = new BooleanSetting("Pick up stash", true);
/*    */     addSettings(new Setting[] { (Setting)this.pickupstash });
/*    */   } @SubscribeEvent
/*    */   public void onTick(TickEvent.ClientTickEvent event) {
/* 25 */     if (OringoClient.mc.field_71462_r != null)
/* 26 */       return;  if (isPressed() && !this.wasPressed) {
/* 27 */       int holding = OringoClient.mc.field_71439_g.field_71071_by.field_70461_c;
/* 28 */       for (int x = 0; x < 9; x++) {
/* 29 */         if (OringoClient.mc.field_71439_g.field_71071_by.func_70301_a(x) != null && (OringoClient.mc.field_71439_g.field_71071_by.func_70301_a(x).func_77973_b() instanceof net.minecraft.item.ItemSnowball || OringoClient.mc.field_71439_g.field_71071_by.func_70301_a(x).func_77973_b() instanceof net.minecraft.item.ItemEgg || OringoClient.mc.field_71439_g.field_71071_by.func_70301_a(x).func_77973_b() instanceof net.minecraft.item.ItemEnderPearl || OringoClient.mc.field_71439_g.field_71071_by.func_70301_a(x).func_82833_r().contains("Bonemerang"))) {
/* 30 */           OringoClient.mc.field_71439_g.field_71071_by.field_70461_c = x;
/* 31 */           SkyblockUtils.updateItem();
/* 32 */           for (int e = 0; e < 16; e++) {
/* 33 */             OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C08PacketPlayerBlockPlacement(OringoClient.mc.field_71439_g.func_70694_bm()));
/*    */           }
/*    */         } 
/*    */       } 
/* 37 */       OringoClient.mc.field_71439_g.field_71071_by.field_70461_c = holding;
/* 38 */       SkyblockUtils.updateItem();
/* 39 */       if (this.pickupstash.isEnabled())
/* 40 */         OringoClient.mc.field_71439_g.func_71165_d("/pickupstash"); 
/*    */     } 
/* 42 */     this.wasPressed = isPressed();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isKeybind() {
/* 47 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\skyblock\Snowballs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */