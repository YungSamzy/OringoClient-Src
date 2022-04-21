/*     */ package org.newsclub.net.unix;
/*     */ 
/*     */ import java.io.FileDescriptor;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Field;
/*     */ import java.net.InetSocketAddress;
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
/*     */ 
/*     */ final class NativeUnixSocket
/*     */ {
/*     */   private static boolean loaded = false;
/*     */   
/*     */   static {
/*     */     try {
/*  35 */       Class.forName("org.newsclub.net.unix.NarSystem").getMethod("loadLibrary", new Class[0]).invoke(null, new Object[0]);
/*  36 */     } catch (ClassNotFoundException e) {
/*  37 */       throw new IllegalStateException("Could not find NarSystem class.\n\n*** ECLIPSE USERS ***\nIf you're running from within Eclipse, please try closing the \"junixsocket-native-common\" project\n", e);
/*     */ 
/*     */     
/*     */     }
/*  41 */     catch (Exception e) {
/*  42 */       throw new IllegalStateException(e);
/*     */     } 
/*  44 */     loaded = true;
/*     */   }
/*     */   
/*     */   static boolean isLoaded() {
/*  48 */     return loaded;
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
/*     */   static void checkSupported() {}
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
/*     */   
/*     */   static void setPort1(AFUNIXSocketAddress addr, int port) throws AFUNIXSocketException {
/*  97 */     if (port < 0) {
/*  98 */       throw new IllegalArgumentException("port out of range:" + port);
/*     */     }
/*     */     
/* 101 */     boolean setOk = false;
/*     */     try {
/* 103 */       Field holderField = InetSocketAddress.class.getDeclaredField("holder");
/* 104 */       if (holderField != null) {
/* 105 */         holderField.setAccessible(true);
/*     */         
/* 107 */         Object holder = holderField.get(addr);
/* 108 */         if (holder != null) {
/* 109 */           Field portField = holder.getClass().getDeclaredField("port");
/* 110 */           if (portField != null) {
/* 111 */             portField.setAccessible(true);
/* 112 */             portField.set(holder, Integer.valueOf(port));
/* 113 */             setOk = true;
/*     */           } 
/*     */         } 
/*     */       } else {
/* 117 */         setPort(addr, port);
/*     */       } 
/* 119 */     } catch (RuntimeException e) {
/* 120 */       throw e;
/* 121 */     } catch (Exception e) {
/* 122 */       if (e instanceof AFUNIXSocketException) {
/* 123 */         throw (AFUNIXSocketException)e;
/*     */       }
/* 125 */       throw new AFUNIXSocketException("Could not set port", e);
/*     */     } 
/* 127 */     if (!setOk)
/* 128 */       throw new AFUNIXSocketException("Could not set port"); 
/*     */   }
/*     */   
/*     */   static native void bind(String paramString, FileDescriptor paramFileDescriptor, int paramInt) throws IOException;
/*     */   
/*     */   static native void listen(FileDescriptor paramFileDescriptor, int paramInt) throws IOException;
/*     */   
/*     */   static native void accept(String paramString, FileDescriptor paramFileDescriptor1, FileDescriptor paramFileDescriptor2) throws IOException;
/*     */   
/*     */   static native void connect(String paramString, FileDescriptor paramFileDescriptor) throws IOException;
/*     */   
/*     */   static native int read(FileDescriptor paramFileDescriptor, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
/*     */   
/*     */   static native int write(FileDescriptor paramFileDescriptor, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
/*     */   
/*     */   static native void close(FileDescriptor paramFileDescriptor) throws IOException;
/*     */   
/*     */   static native void shutdown(FileDescriptor paramFileDescriptor, int paramInt) throws IOException;
/*     */   
/*     */   static native int getSocketOptionInt(FileDescriptor paramFileDescriptor, int paramInt) throws IOException;
/*     */   
/*     */   static native void setSocketOptionInt(FileDescriptor paramFileDescriptor, int paramInt1, int paramInt2) throws IOException;
/*     */   
/*     */   static native void unlink(String paramString) throws IOException;
/*     */   
/*     */   static native int available(FileDescriptor paramFileDescriptor) throws IOException;
/*     */   
/*     */   static native void initServerImpl(AFUNIXServerSocket paramAFUNIXServerSocket, AFUNIXSocketImpl paramAFUNIXSocketImpl);
/*     */   
/*     */   static native void setCreated(AFUNIXSocket paramAFUNIXSocket);
/*     */   
/*     */   static native void setConnected(AFUNIXSocket paramAFUNIXSocket);
/*     */   
/*     */   static native void setBound(AFUNIXSocket paramAFUNIXSocket);
/*     */   
/*     */   static native void setCreatedServer(AFUNIXServerSocket paramAFUNIXServerSocket);
/*     */   
/*     */   static native void setBoundServer(AFUNIXServerSocket paramAFUNIXServerSocket);
/*     */   
/*     */   static native void setPort(AFUNIXSocketAddress paramAFUNIXSocketAddress, int paramInt);
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\org\newsclub\ne\\unix\NativeUnixSocket.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */