/*     */ package com.jagrosh.discordipc.entities.pipe;
/*     */ 
/*     */ import com.jagrosh.discordipc.IPCClient;
/*     */ import com.jagrosh.discordipc.entities.Callback;
/*     */ import com.jagrosh.discordipc.entities.Packet;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.SocketAddress;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.HashMap;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ import org.newsclub.net.unix.AFUNIXSocket;
/*     */ import org.newsclub.net.unix.AFUNIXSocketAddress;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UnixPipe
/*     */   extends Pipe
/*     */ {
/*  38 */   private static final Logger LOGGER = LoggerFactory.getLogger(UnixPipe.class);
/*     */   
/*     */   private final AFUNIXSocket socket;
/*     */   
/*     */   UnixPipe(IPCClient ipcClient, HashMap<String, Callback> callbacks, String location) throws IOException {
/*  43 */     super(ipcClient, callbacks);
/*     */     
/*  45 */     this.socket = AFUNIXSocket.newInstance();
/*  46 */     this.socket.connect((SocketAddress)new AFUNIXSocketAddress(new File(location)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Packet read() throws IOException, JSONException {
/*  53 */     InputStream is = this.socket.getInputStream();
/*     */     
/*  55 */     while ((this.status == PipeStatus.CONNECTED || this.status == PipeStatus.CLOSING) && is.available() == 0) {
/*     */       
/*     */       try {
/*  58 */         Thread.sleep(50L);
/*  59 */       } catch (InterruptedException interruptedException) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  68 */     if (this.status == PipeStatus.DISCONNECTED) {
/*  69 */       throw new IOException("Disconnected!");
/*     */     }
/*  71 */     if (this.status == PipeStatus.CLOSED) {
/*  72 */       return new Packet(Packet.OpCode.CLOSE, null);
/*     */     }
/*     */     
/*  75 */     byte[] d = new byte[8];
/*  76 */     is.read(d);
/*  77 */     ByteBuffer bb = ByteBuffer.wrap(d);
/*     */     
/*  79 */     Packet.OpCode op = Packet.OpCode.values()[Integer.reverseBytes(bb.getInt())];
/*  80 */     d = new byte[Integer.reverseBytes(bb.getInt())];
/*     */     
/*  82 */     is.read(d);
/*  83 */     Packet p = new Packet(op, new JSONObject(new String(d)));
/*  84 */     LOGGER.debug(String.format("Received packet: %s", new Object[] { p.toString() }));
/*  85 */     if (this.listener != null)
/*  86 */       this.listener.onPacketReceived(this.ipcClient, p); 
/*  87 */     return p;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(byte[] b) throws IOException {
/*  93 */     this.socket.getOutputStream().write(b);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/*  99 */     LOGGER.debug("Closing IPC pipe...");
/* 100 */     this.status = PipeStatus.CLOSING;
/* 101 */     send(Packet.OpCode.CLOSE, new JSONObject(), null);
/* 102 */     this.status = PipeStatus.CLOSED;
/* 103 */     this.socket.close();
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\com\jagrosh\discordipc\entities\pipe\UnixPipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */