/*    */ package me.oringo.oringoclient.qolfeatures.module.other;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.events.MotionUpdateEvent;
/*    */ import me.oringo.oringoclient.events.PacketSentEvent;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.combat.KillAura;
/*    */ import me.oringo.oringoclient.utils.PacketUtils;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraftforge.fml.common.eventhandler.EventPriority;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class Derp
/*    */   extends Module {
/*    */   private ArrayList<Packet<?>> packets;
/*    */   
/*    */   public Derp() {
/* 20 */     super("Derp", Module.Category.OTHER);
/*    */ 
/*    */     
/* 23 */     this.packets = new ArrayList<>();
/*    */   }
/*    */   @SubscribeEvent(priority = EventPriority.HIGH)
/*    */   public void onUpdate(MotionUpdateEvent.Pre event) {
/* 27 */     if (!isToggled() || !this.packets.isEmpty() || KillAura.target != null)
/* 28 */       return;  event.yaw = ((new Random()).nextInt(181) * ((OringoClient.mc.field_71439_g.field_70173_aa % 2 == 0) ? -1 : 1));
/* 29 */     event.pitch = ((new Random()).nextInt(181) - 90);
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onUpdatePost(MotionUpdateEvent.Post e) {
/* 34 */     if (this.packets.isEmpty())
/* 35 */       return;  for (Packet<?> packet : this.packets) {
/* 36 */       PacketUtils.sendPacketNoEvent(packet);
/*    */     }
/* 38 */     this.packets.clear();
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onPacket(PacketSentEvent event) {
/* 43 */     if (!isToggled())
/* 44 */       return;  if (event.packet instanceof net.minecraft.network.play.client.C02PacketUseEntity || event.packet instanceof net.minecraft.network.play.client.C08PacketPlayerBlockPlacement || event.packet instanceof net.minecraft.network.play.client.C07PacketPlayerDigging || event.packet instanceof net.minecraft.network.play.client.C0APacketAnimation || event.packet instanceof net.minecraft.network.play.client.C01PacketChatMessage || event.packet instanceof net.minecraft.network.play.client.C09PacketHeldItemChange) {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 50 */       this.packets.add(event.packet);
/* 51 */       event.setCanceled(true);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\other\Derp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */