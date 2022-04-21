/*    */ package me.oringo.oringoclient.qolfeatures.module.other;
/*    */ import java.util.ArrayList;
/*    */ import me.oringo.oringoclient.events.PacketSentEvent;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*    */ import me.oringo.oringoclient.utils.MilliTimer;
/*    */ import me.oringo.oringoclient.utils.PacketUtils;
/*    */ import net.minecraft.network.Packet;
/*    */ 
/*    */ public class Blink extends Module {
/*    */   private NumberSetting autoDisable;
/*    */   
/*    */   public Blink() {
/* 15 */     super("Blink", Module.Category.OTHER);
/*    */ 
/*    */     
/* 18 */     this.autoDisable = new NumberSetting("Auto disable", 20.0D, 0.0D, 250.0D, 1.0D);
/*    */     
/* 20 */     this.packets = new ArrayList<>();
/* 21 */     this.timer = new MilliTimer();
/*    */     addSettings(new Setting[] { (Setting)this.autoDisable });
/*    */   } private ArrayList<Packet<?>> packets; private MilliTimer timer;
/*    */   public void onEnable() {
/* 25 */     this.timer.updateTime();
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onPacket(PacketSentEvent event) {
/* 30 */     if (!isToggled())
/* 31 */       return;  event.setCanceled(true);
/* 32 */     this.packets.add(event.packet);
/* 33 */     if (this.autoDisable.getValue() != 0.0D && this.timer.hasTimePassed((long)(this.autoDisable.getValue() * 50.0D))) {
/* 34 */       toggle();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDisable() {
/* 40 */     for (Packet<?> packet : this.packets) {
/* 41 */       PacketUtils.sendPacketNoEvent(packet);
/*    */     }
/* 43 */     this.packets.clear();
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\other\Blink.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */