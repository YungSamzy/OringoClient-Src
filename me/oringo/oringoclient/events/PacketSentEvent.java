/*    */ package me.oringo.oringoclient.events;
/*    */ 
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraftforge.fml.common.eventhandler.Cancelable;
/*    */ import net.minecraftforge.fml.common.eventhandler.Event;
/*    */ 
/*    */ @Cancelable
/*    */ public class PacketSentEvent extends Event {
/*    */   public PacketSentEvent(Packet<?> packet) {
/* 10 */     this.packet = packet;
/*    */   }
/*    */   public Packet<?> packet;
/*    */   public static class Post extends Event { public Packet<?> packet;
/*    */     
/*    */     public Post(Packet<?> packet) {
/* 16 */       this.packet = packet;
/*    */     } }
/*    */ 
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\events\PacketSentEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */