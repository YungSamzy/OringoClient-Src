/*    */ package me.oringo.oringoclient.events;
/*    */ 
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraftforge.fml.common.eventhandler.Cancelable;
/*    */ import net.minecraftforge.fml.common.eventhandler.Event;
/*    */ 
/*    */ @Cancelable
/*    */ public class PacketReceivedEvent extends Event {
/*    */   public Packet<?> packet;
/*    */   public ChannelHandlerContext context;
/*    */   
/*    */   public PacketReceivedEvent(Packet<?> packet, ChannelHandlerContext context) {
/* 14 */     this.packet = packet;
/* 15 */     this.context = context;
/*    */   }
/*    */   
/*    */   public static class Post extends Event {
/*    */     public Packet<?> packet;
/*    */     
/*    */     public Post(Packet<?> packet, ChannelHandlerContext context) {
/* 22 */       this.packet = packet;
/* 23 */       this.context = context;
/*    */     }
/*    */     
/*    */     public ChannelHandlerContext context;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\events\PacketReceivedEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */