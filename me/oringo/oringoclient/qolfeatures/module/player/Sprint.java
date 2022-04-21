/*    */ package me.oringo.oringoclient.qolfeatures.module.player;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.C0BPacketEntityAction;
/*    */ 
/*    */ public class Sprint extends Module {
/*    */   public BooleanSetting omni;
/*    */   
/*    */   public Sprint() {
/* 14 */     super("Sprint", 0, Module.Category.COMBAT);
/*    */ 
/*    */     
/* 17 */     this.omni = new BooleanSetting("OmniSprint", false);
/* 18 */     this.keep = new BooleanSetting("KeepSprint", true);
/*    */     addSettings(new Setting[] { (Setting)this.keep, (Setting)this.omni });
/*    */   } public BooleanSetting keep; @SubscribeEvent(priority = EventPriority.LOWEST)
/*    */   public void niceOmniSprintCheck(MotionUpdateEvent.Pre event) {
/* 22 */     if (((this.omni.isEnabled() && isToggled()) || OringoClient.derp.isToggled() || OringoClient.speed.isToggled()) && !OringoClient.mc.field_71439_g.func_70093_af()) {
/* 23 */       OringoClient.mc.func_147114_u().func_147297_a((Packet)new C0BPacketEntityAction((Entity)OringoClient.mc.field_71439_g, C0BPacketEntityAction.Action.START_SNEAKING));
/* 24 */       OringoClient.mc.func_147114_u().func_147297_a((Packet)new C0BPacketEntityAction((Entity)OringoClient.mc.field_71439_g, C0BPacketEntityAction.Action.STOP_SNEAKING));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\player\Sprint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */