/*    */ package org.newsclub.net.unix;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.net.InetSocketAddress;
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
/*    */ public class AFUNIXSocketAddress
/*    */   extends InetSocketAddress
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private final String socketFile;
/*    */   
/*    */   public AFUNIXSocketAddress(File socketFile) throws IOException {
/* 44 */     this(socketFile, 0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AFUNIXSocketAddress(File socketFile, int port) throws IOException {
/* 55 */     super(0);
/* 56 */     if (port != 0) {
/* 57 */       NativeUnixSocket.setPort1(this, port);
/*    */     }
/* 59 */     this.socketFile = socketFile.getCanonicalPath();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getSocketFile() {
/* 68 */     return this.socketFile;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 73 */     return getClass().getName() + "[host=" + getHostName() + ";port=" + getPort() + ";file=" + this.socketFile + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\org\newsclub\ne\\unix\AFUNIXSocketAddress.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */