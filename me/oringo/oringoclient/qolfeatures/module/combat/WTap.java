/*    */ package me.oringo.oringoclient.qolfeatures.module.combat;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.events.PacketSentEvent;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.C02PacketUseEntity;
/*    */ import net.minecraft.network.play.client.C07PacketPlayerDigging;
/*    */ import net.minecraft.network.play.client.C0BPacketEntityAction;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class WTap extends Module {
/*    */   public ModeSetting mode;
/*    */   
/*    */   public WTap() {
/* 18 */     super("WTap", Module.Category.COMBAT);
/*    */ 
/*    */ 
/*    */     
/* 22 */     this.mode = new ModeSetting("mode", "Packet", new String[] { "Packet", "Extra Packet" });
/*    */     
/* 24 */     this.bow = new BooleanSetting("Bow", true);
/*    */     addSettings(new Setting[] { (Setting)this.mode, (Setting)this.bow });
/*    */   } public BooleanSetting bow; @SubscribeEvent
/*    */   public void onPacket(PacketSentEvent event) {
/* 28 */     if (isToggled() && ((event.packet instanceof C02PacketUseEntity && ((C02PacketUseEntity)event.packet).func_149565_c() == C02PacketUseEntity.Action.ATTACK) || (this.bow
/* 29 */       .isEnabled() && event.packet instanceof C07PacketPlayerDigging && ((C07PacketPlayerDigging)event.packet).func_180762_c() == C07PacketPlayerDigging.Action.RELEASE_USE_ITEM && OringoClient.mc.field_71439_g.func_70694_bm() != null && OringoClient.mc.field_71439_g.func_70694_bm().func_77973_b() instanceof net.minecraft.item.ItemBow))) {
/* 30 */       int i; switch (this.mode.getSelected()) {
/*    */         case "Extra Packet":
/* 32 */           for (i = 0; i < 4; i++) {
/* 33 */             OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C0BPacketEntityAction((Entity)OringoClient.mc.field_71439_g, C0BPacketEntityAction.Action.STOP_SPRINTING));
/* 34 */             OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C0BPacketEntityAction((Entity)OringoClient.mc.field_71439_g, C0BPacketEntityAction.Action.START_SPRINTING));
/*    */           } 
/*    */           return;
/*    */       } 
/* 38 */       if (OringoClient.mc.field_71439_g.func_70051_ag()) OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C0BPacketEntityAction((Entity)OringoClient.mc.field_71439_g, C0BPacketEntityAction.Action.STOP_SPRINTING)); 
/* 39 */       OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C0BPacketEntityAction((Entity)OringoClient.mc.field_71439_g, C0BPacketEntityAction.Action.START_SPRINTING));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onPacket(PacketSentEvent.Post event) {
/* 47 */     if (isToggled() && ((event.packet instanceof C02PacketUseEntity && ((C02PacketUseEntity)event.packet).func_149565_c() == C02PacketUseEntity.Action.ATTACK) || (this.bow
/* 48 */       .isEnabled() && event.packet instanceof C07PacketPlayerDigging && ((C07PacketPlayerDigging)event.packet).func_180762_c() == C07PacketPlayerDigging.Action.RELEASE_USE_ITEM && OringoClient.mc.field_71439_g.func_70694_bm() != null && OringoClient.mc.field_71439_g.func_70694_bm().func_77973_b() instanceof net.minecraft.item.ItemBow)) && 
/* 49 */       !OringoClient.mc.field_71439_g.func_70051_ag()) ((PlayerSPAccessor)OringoClient.mc.field_71439_g).setServerSprintState(false); 
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\combat\WTap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */