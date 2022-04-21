/*    */ package com.jagrosh.discordipc.entities.pipe;
/*    */ 
/*    */ import com.jagrosh.discordipc.IPCClient;
/*    */ import com.jagrosh.discordipc.entities.Callback;
/*    */ import com.jagrosh.discordipc.entities.Packet;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.IOException;
/*    */ import java.io.RandomAccessFile;
/*    */ import java.util.HashMap;
/*    */ import org.json.JSONException;
/*    */ import org.json.JSONObject;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WindowsPipe
/*    */   extends Pipe
/*    */ {
/* 35 */   private static final Logger LOGGER = LoggerFactory.getLogger(WindowsPipe.class);
/*    */   
/*    */   private final RandomAccessFile file;
/*    */ 
/*    */   
/*    */   WindowsPipe(IPCClient ipcClient, HashMap<String, Callback> callbacks, String location) {
/* 41 */     super(ipcClient, callbacks);
/*    */     try {
/* 43 */       this.file = new RandomAccessFile(location, "rw");
/* 44 */     } catch (FileNotFoundException e) {
/* 45 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(byte[] b) throws IOException {
/* 51 */     this.file.write(b);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Packet read() throws IOException, JSONException {
/* 59 */     while ((this.status == PipeStatus.CONNECTED || this.status == PipeStatus.CLOSING) && this.file.length() == 0L) {
/*    */       
/*    */       try {
/* 62 */         Thread.sleep(50L);
/* 63 */       } catch (InterruptedException interruptedException) {}
/*    */     } 
/*    */     
/* 66 */     if (this.status == PipeStatus.DISCONNECTED) {
/* 67 */       throw new IOException("Disconnected!");
/*    */     }
/* 69 */     if (this.status == PipeStatus.CLOSED) {
/* 70 */       return new Packet(Packet.OpCode.CLOSE, null);
/*    */     }
/* 72 */     Packet.OpCode op = Packet.OpCode.values()[Integer.reverseBytes(this.file.readInt())];
/* 73 */     int len = Integer.reverseBytes(this.file.readInt());
/* 74 */     byte[] d = new byte[len];
/*    */     
/* 76 */     this.file.readFully(d);
/* 77 */     Packet p = new Packet(op, new JSONObject(new String(d)));
/* 78 */     LOGGER.debug(String.format("Received packet: %s", new Object[] { p.toString() }));
/* 79 */     if (this.listener != null)
/* 80 */       this.listener.onPacketReceived(this.ipcClient, p); 
/* 81 */     return p;
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() throws IOException {
/* 86 */     LOGGER.debug("Closing IPC pipe...");
/* 87 */     this.status = PipeStatus.CLOSING;
/* 88 */     send(Packet.OpCode.CLOSE, new JSONObject(), null);
/* 89 */     this.status = PipeStatus.CLOSED;
/* 90 */     this.file.close();
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\com\jagrosh\discordipc\entities\pipe\WindowsPipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */