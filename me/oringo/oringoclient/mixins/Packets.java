/*    */ package me.oringo.oringoclient.mixins;
/*    */ 
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import java.io.IOException;
/*    */ import me.oringo.oringoclient.commands.DevModeCommand;
/*    */ import me.oringo.oringoclient.commands.PacketLoggerCommand;
/*    */ import me.oringo.oringoclient.events.PacketReceivedEvent;
/*    */ import me.oringo.oringoclient.events.PacketSentEvent;
/*    */ import me.oringo.oringoclient.utils.OringoPacketLog;
/*    */ import me.oringo.oringoclient.utils.PacketUtils;
/*    */ import net.minecraft.network.NetworkManager;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.fml.common.eventhandler.Event;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({NetworkManager.class})
/*    */ public abstract class Packets
/*    */ {
/*    */   @Inject(method = {"sendPacket(Lnet/minecraft/network/Packet;)V"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void onSendPacket(Packet<?> packet, CallbackInfo callbackInfo) {
/* 30 */     if (OringoPacketLog.isEnabled() && !(packet instanceof net.minecraft.network.play.client.C03PacketPlayer) && !DevModeCommand.ignoredPackets.contains(packet.getClass().getSimpleName()))
/* 31 */       OringoPacketLog.logOut(PacketUtils.packetToString(packet)); 
/* 32 */     if (!PacketUtils.noEvent.contains(packet) && 
/* 33 */       MinecraftForge.EVENT_BUS.post((Event)new PacketSentEvent(packet)))
/* 34 */       callbackInfo.cancel(); 
/*    */   }
/*    */   @Inject(method = {"sendPacket(Lnet/minecraft/network/Packet;)V"}, at = {@At("RETURN")}, cancellable = true)
/*    */   private void onSendPacketPost(Packet<?> packet, CallbackInfo callbackInfo) {
/* 38 */     if (!PacketUtils.noEvent.contains(packet) && 
/* 39 */       MinecraftForge.EVENT_BUS.post((Event)new PacketSentEvent.Post(packet)))
/* 40 */       callbackInfo.cancel(); 
/* 41 */     PacketUtils.noEvent.remove(packet);
/*    */   }
/*    */ 
/*    */   
/*    */   @Inject(method = {"channelRead0"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void onChannelReadHead(ChannelHandlerContext context, Packet<?> packet, CallbackInfo callbackInfo) {
/* 47 */     if (PacketLoggerCommand.writer != null && (
/* 48 */       packet instanceof net.minecraft.network.play.server.S13PacketDestroyEntities || packet instanceof net.minecraft.network.play.server.S3EPacketTeams || packet instanceof net.minecraft.network.play.server.S40PacketDisconnect)) {
/*    */       try {
/* 50 */         PacketLoggerCommand.writer.write(PacketUtils.packetToString(packet) + "\n");
/* 51 */       } catch (IOException e) {
/* 52 */         e.printStackTrace();
/*    */       } 
/* 54 */       if (packet instanceof net.minecraft.network.play.server.S40PacketDisconnect) {
/*    */         try {
/* 56 */           PacketLoggerCommand.writer.close();
/* 57 */         } catch (IOException e) {
/* 58 */           e.printStackTrace();
/*    */         } 
/* 60 */         PacketLoggerCommand.writer = null;
/*    */       } 
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 66 */     if (!PacketUtils.noEvent.contains(packet) && 
/* 67 */       MinecraftForge.EVENT_BUS.post((Event)new PacketReceivedEvent(packet, context))) {
/* 68 */       callbackInfo.cancel();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Inject(method = {"channelRead0"}, at = {@At("RETURN")}, cancellable = true)
/*    */   private void onPost(ChannelHandlerContext context, Packet<?> packet, CallbackInfo callbackInfo) {
/* 82 */     if (!PacketUtils.noEvent.contains(packet) && 
/* 83 */       MinecraftForge.EVENT_BUS.post((Event)new PacketReceivedEvent.Post(packet, context)))
/* 84 */       callbackInfo.cancel(); 
/* 85 */     PacketUtils.noEvent.remove(packet);
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\mixins\Packets.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */