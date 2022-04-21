/*    */ package me.oringo.oringoclient.qolfeatures.module.other;
/*    */ 
/*    */ import java.util.Random;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.events.PacketSentEvent;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*    */ import net.minecraft.network.play.client.C07PacketPlayerDigging;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class ServerBeamer extends Module {
/*    */   public NumberSetting beamer;
/*    */   public NumberSetting randomSend;
/*    */   public BooleanSetting start;
/*    */   public ModeSetting mode;
/*    */   private int i;
/*    */   
/* 21 */   public ServerBeamer() { super("Server Beamer", Module.Category.OTHER);
/*    */ 
/*    */ 
/*    */     
/* 25 */     this.beamer = new NumberSetting("Packets", 10.0D, 1.0D, 50.0D, 1.0D);
/* 26 */     this.randomSend = new NumberSetting("Send ticks", 0.0D, 0.0D, 100.0D, 1.0D);
/* 27 */     this.start = new BooleanSetting("Start Breaking", true);
/* 28 */     this.mode = new ModeSetting("Mode", "Sync", new String[] { "Sync", "Async" });
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 36 */     this.i = 0;
/*    */     addSettings(new Setting[] { (Setting)this.beamer, (Setting)this.randomSend, (Setting)this.mode, (Setting)this.start }); }
/*    */   
/*    */   @SubscribeEvent
/* 40 */   public void onMotion(MotionUpdateEvent event) { if (!isToggled())
/* 41 */       return;  if (this.randomSend.getValue() != 0.0D && this.i++ % this.randomSend.getValue() == 0.0D) {
/* 42 */       beam();
/*    */     }
/* 44 */     if (!this.mode.is("Async"))
/* 45 */       return;  beam(); }
/*    */   @SubscribeEvent
/*    */   public void onUpdate(PacketSentEvent event) { if (!isToggled() || !(event.packet instanceof net.minecraft.network.play.client.C0FPacketConfirmTransaction) || !this.mode.is("Sync"))
/*    */       return; 
/* 49 */     beam(); } private void beam() { for (int i = 0; i < this.beamer.getValue(); i++) {
/* 50 */       BlockPos pos = new BlockPos((new Random()).nextInt(10000) * 16, 255, (new Random()).nextInt(10000) * 16);
/* 51 */       OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C07PacketPlayerDigging(this.start.isEnabled() ? C07PacketPlayerDigging.Action.START_DESTROY_BLOCK : C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, pos, EnumFacing.func_176733_a(OringoClient.mc.field_71439_g.field_70177_z)));
/*    */     }  }
/*    */ 
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\other\ServerBeamer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */