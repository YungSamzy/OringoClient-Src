/*     */ package org.newsclub.net.unix;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.Socket;
/*     */ import java.net.SocketAddress;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AFUNIXSocket
/*     */   extends Socket
/*     */ {
/*     */   protected AFUNIXSocketImpl impl;
/*     */   AFUNIXSocketAddress addr;
/*     */   
/*     */   private AFUNIXSocket(AFUNIXSocketImpl impl) throws IOException {
/*  34 */     super(impl);
/*     */     try {
/*  36 */       NativeUnixSocket.setCreated(this);
/*  37 */     } catch (UnsatisfiedLinkError e) {
/*  38 */       e.printStackTrace();
/*     */     } 
/*     */   }
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
/*     */   public static AFUNIXSocket newInstance() throws IOException {
/*  53 */     AFUNIXSocketImpl impl = new AFUNIXSocketImpl.Lenient();
/*  54 */     AFUNIXSocket instance = new AFUNIXSocket(impl);
/*  55 */     instance.impl = impl;
/*  56 */     return instance;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AFUNIXSocket newStrictInstance() throws IOException {
/*  68 */     AFUNIXSocketImpl impl = new AFUNIXSocketImpl();
/*  69 */     AFUNIXSocket instance = new AFUNIXSocket(impl);
/*  70 */     instance.impl = impl;
/*  71 */     return instance;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AFUNIXSocket connectTo(AFUNIXSocketAddress addr) throws IOException {
/*  81 */     AFUNIXSocket socket = newInstance();
/*  82 */     socket.connect(addr);
/*  83 */     return socket;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void bind(SocketAddress bindpoint) throws IOException {
/*  92 */     super.bind(bindpoint);
/*  93 */     this.addr = (AFUNIXSocketAddress)bindpoint;
/*     */   }
/*     */ 
/*     */   
/*     */   public void connect(SocketAddress endpoint) throws IOException {
/*  98 */     connect(endpoint, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void connect(SocketAddress endpoint, int timeout) throws IOException {
/* 103 */     if (!(endpoint instanceof AFUNIXSocketAddress)) {
/* 104 */       throw new IOException("Can only connect to endpoints of type " + AFUNIXSocketAddress.class
/* 105 */           .getName());
/*     */     }
/* 107 */     this.impl.connect(endpoint, timeout);
/* 108 */     this.addr = (AFUNIXSocketAddress)endpoint;
/* 109 */     NativeUnixSocket.setConnected(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 114 */     if (isConnected()) {
/* 115 */       return "AFUNIXSocket[fd=" + this.impl.getFD() + ";path=" + this.addr.getSocketFile() + "]";
/*     */     }
/* 117 */     return "AFUNIXSocket[unconnected]";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isSupported() {
/* 129 */     return NativeUnixSocket.isLoaded();
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\org\newsclub\ne\\unix\AFUNIXSocket.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */