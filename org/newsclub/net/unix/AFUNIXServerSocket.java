/*     */ package org.newsclub.net.unix;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.ServerSocket;
/*     */ import java.net.Socket;
/*     */ import java.net.SocketAddress;
/*     */ import java.net.SocketException;
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
/*     */ public class AFUNIXServerSocket
/*     */   extends ServerSocket
/*     */ {
/*     */   private final AFUNIXSocketImpl implementation;
/*  33 */   private AFUNIXSocketAddress boundEndpoint = null;
/*     */   
/*  35 */   private final Thread shutdownThread = new Thread()
/*     */     {
/*     */       public void run() {
/*     */         try {
/*  39 */           if (AFUNIXServerSocket.this.boundEndpoint != null) {
/*  40 */             NativeUnixSocket.unlink(AFUNIXServerSocket.this.boundEndpoint.getSocketFile());
/*     */           }
/*  42 */         } catch (IOException e) {}
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AFUNIXServerSocket() throws IOException {
/*  50 */     this.implementation = new AFUNIXSocketImpl();
/*  51 */     NativeUnixSocket.initServerImpl(this, this.implementation);
/*     */     
/*  53 */     Runtime.getRuntime().addShutdownHook(this.shutdownThread);
/*  54 */     NativeUnixSocket.setCreatedServer(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AFUNIXServerSocket newInstance() throws IOException {
/*  63 */     AFUNIXServerSocket instance = new AFUNIXServerSocket();
/*  64 */     return instance;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AFUNIXServerSocket bindOn(AFUNIXSocketAddress addr) throws IOException {
/*  74 */     AFUNIXServerSocket socket = newInstance();
/*  75 */     socket.bind(addr);
/*  76 */     return socket;
/*     */   }
/*     */ 
/*     */   
/*     */   public void bind(SocketAddress endpoint, int backlog) throws IOException {
/*  81 */     if (isClosed()) {
/*  82 */       throw new SocketException("Socket is closed");
/*     */     }
/*  84 */     if (isBound()) {
/*  85 */       throw new SocketException("Already bound");
/*     */     }
/*  87 */     if (!(endpoint instanceof AFUNIXSocketAddress)) {
/*  88 */       throw new IOException("Can only bind to endpoints of type " + AFUNIXSocketAddress.class
/*  89 */           .getName());
/*     */     }
/*  91 */     this.implementation.bind(backlog, endpoint);
/*  92 */     this.boundEndpoint = (AFUNIXSocketAddress)endpoint;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBound() {
/*  97 */     return (this.boundEndpoint != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public Socket accept() throws IOException {
/* 102 */     if (isClosed()) {
/* 103 */       throw new SocketException("Socket is closed");
/*     */     }
/* 105 */     AFUNIXSocket as = AFUNIXSocket.newInstance();
/* 106 */     this.implementation.accept(as.impl);
/* 107 */     as.addr = this.boundEndpoint;
/* 108 */     NativeUnixSocket.setConnected(as);
/* 109 */     return as;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 114 */     if (!isBound()) {
/* 115 */       return "AFUNIXServerSocket[unbound]";
/*     */     }
/* 117 */     return "AFUNIXServerSocket[" + this.boundEndpoint.getSocketFile() + "]";
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 122 */     if (isClosed()) {
/*     */       return;
/*     */     }
/*     */     
/* 126 */     super.close();
/* 127 */     this.implementation.close();
/* 128 */     if (this.boundEndpoint != null) {
/* 129 */       NativeUnixSocket.unlink(this.boundEndpoint.getSocketFile());
/*     */     }
/*     */     try {
/* 132 */       Runtime.getRuntime().removeShutdownHook(this.shutdownThread);
/* 133 */     } catch (IllegalStateException e) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isSupported() {
/* 139 */     return NativeUnixSocket.isLoaded();
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\org\newsclub\ne\\unix\AFUNIXServerSocket.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */