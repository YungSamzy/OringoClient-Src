/*    */ package me.oringo.oringoclient.qolfeatures.module.skyblock;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.events.PlayerUpdateEvent;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.ModeSetting;
/*    */ import me.oringo.oringoclient.utils.SkyblockUtils;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.network.Packet;
/*    */ 
/*    */ public class BoneThrower extends Module {
/*    */   public ModeSetting mode;
/*    */   
/*    */   public BoneThrower() {
/* 16 */     super("BoneThrower", Module.Category.SKYBLOCK);
/*    */ 
/*    */ 
/*    */     
/* 20 */     this.mode = new ModeSetting("Mode", "Hotbar", new String[] { "Hotbar" });
/* 21 */     this.autoDisable = new BooleanSetting("Disable", true);
/*    */     addSettings(new Setting[] { (Setting)this.mode, (Setting)this.autoDisable });
/*    */   }
/*    */   public BooleanSetting autoDisable; private int ticks;
/*    */   
/*    */   public void onEnable() {
/* 27 */     this.ticks = 6;
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onUpdate(PlayerUpdateEvent event) {
/* 32 */     this.ticks--;
/* 33 */     if (isToggled()) {
/* 34 */       int last; int i; switch (this.mode.getSelected()) {
/*    */         case "Hotbar":
/* 36 */           last = OringoClient.mc.field_71439_g.field_71071_by.field_70461_c;
/* 37 */           for (i = 0; i < 9; i++) {
/* 38 */             ItemStack stack = OringoClient.mc.field_71439_g.field_71071_by.func_70301_a(i);
/* 39 */             if (stack != null && stack.func_82833_r().contains("Bonemerang")) {
/* 40 */               OringoClient.mc.field_71439_g.field_71071_by.field_70461_c = i;
/* 41 */               SkyblockUtils.updateItem();
/* 42 */               OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C08PacketPlayerBlockPlacement(OringoClient.mc.field_71439_g.func_70694_bm()));
/*    */             } 
/*    */           } 
/* 45 */           OringoClient.mc.field_71439_g.field_71071_by.field_70461_c = last;
/* 46 */           SkyblockUtils.updateItem();
/* 47 */           if (this.autoDisable.isEnabled()) toggle(); 
/*    */           break;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\skyblock\BoneThrower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */