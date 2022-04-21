/*     */ package org.newsclub.net.unix;
/*     */ 
/*     */ import java.io.FileDescriptor;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.InetAddress;
/*     */ import java.net.SocketAddress;
/*     */ import java.net.SocketException;
/*     */ import java.net.SocketImpl;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ class AFUNIXSocketImpl
/*     */   extends SocketImpl
/*     */ {
/*     */   private static final int SHUT_RD = 0;
/*     */   private static final int SHUT_WR = 1;
/*     */   private static final int SHUT_RD_WR = 2;
/*     */   private String socketFile;
/*     */   private boolean closed = false;
/*     */   private boolean bound = false;
/*     */   private boolean connected = false;
/*     */   private boolean closedInputStream = false;
/*     */   private boolean closedOutputStream = false;
/*  49 */   private final AFUNIXInputStream in = new AFUNIXInputStream();
/*  50 */   private final AFUNIXOutputStream out = new AFUNIXOutputStream();
/*     */ 
/*     */   
/*     */   AFUNIXSocketImpl() {
/*  54 */     this.fd = new FileDescriptor();
/*     */   }
/*     */   
/*     */   FileDescriptor getFD() {
/*  58 */     return this.fd;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void accept(SocketImpl socket) throws IOException {
/*  63 */     AFUNIXSocketImpl si = (AFUNIXSocketImpl)socket;
/*  64 */     NativeUnixSocket.accept(this.socketFile, this.fd, si.fd);
/*  65 */     si.socketFile = this.socketFile;
/*  66 */     si.connected = true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int available() throws IOException {
/*  71 */     return NativeUnixSocket.available(this.fd);
/*     */   }
/*     */   
/*     */   protected void bind(SocketAddress addr) throws IOException {
/*  75 */     bind(0, addr);
/*     */   }
/*     */   
/*     */   protected void bind(int backlog, SocketAddress addr) throws IOException {
/*  79 */     if (!(addr instanceof AFUNIXSocketAddress)) {
/*  80 */       throw new SocketException("Cannot bind to this type of address: " + addr.getClass());
/*     */     }
/*  82 */     AFUNIXSocketAddress socketAddress = (AFUNIXSocketAddress)addr;
/*  83 */     this.socketFile = socketAddress.getSocketFile();
/*  84 */     NativeUnixSocket.bind(this.socketFile, this.fd, backlog);
/*  85 */     this.bound = true;
/*  86 */     this.localport = socketAddress.getPort();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void bind(InetAddress host, int port) throws IOException {
/*  92 */     throw new SocketException("Cannot bind to this type of address: " + InetAddress.class);
/*     */   }
/*     */   
/*     */   private void checkClose() throws IOException {
/*  96 */     if (!this.closedInputStream || this.closedOutputStream);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected synchronized void close() throws IOException {
/* 103 */     if (this.closed) {
/*     */       return;
/*     */     }
/* 106 */     this.closed = true;
/* 107 */     if (this.fd.valid()) {
/* 108 */       NativeUnixSocket.shutdown(this.fd, 2);
/* 109 */       NativeUnixSocket.close(this.fd);
/*     */     } 
/* 111 */     if (this.bound) {
/* 112 */       NativeUnixSocket.unlink(this.socketFile);
/*     */     }
/* 114 */     this.connected = false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void connect(String host, int port) throws IOException {
/* 120 */     throw new SocketException("Cannot bind to this type of address: " + InetAddress.class);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void connect(InetAddress address, int port) throws IOException {
/* 126 */     throw new SocketException("Cannot bind to this type of address: " + InetAddress.class);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void connect(SocketAddress addr, int timeout) throws IOException {
/* 131 */     if (!(addr instanceof AFUNIXSocketAddress)) {
/* 132 */       throw new SocketException("Cannot bind to this type of address: " + addr.getClass());
/*     */     }
/* 134 */     AFUNIXSocketAddress socketAddress = (AFUNIXSocketAddress)addr;
/* 135 */     this.socketFile = socketAddress.getSocketFile();
/* 136 */     NativeUnixSocket.connect(this.socketFile, this.fd);
/* 137 */     this.address = socketAddress.getAddress();
/* 138 */     this.port = socketAddress.getPort();
/* 139 */     this.localport = 0;
/* 140 */     this.connected = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void create(boolean stream) throws IOException {}
/*     */ 
/*     */   
/*     */   protected InputStream getInputStream() throws IOException {
/* 149 */     if (!this.connected && !this.bound) {
/* 150 */       throw new IOException("Not connected/not bound");
/*     */     }
/* 152 */     return this.in;
/*     */   }
/*     */ 
/*     */   
/*     */   protected OutputStream getOutputStream() throws IOException {
/* 157 */     if (!this.connected && !this.bound) {
/* 158 */       throw new IOException("Not connected/not bound");
/*     */     }
/* 160 */     return this.out;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void listen(int backlog) throws IOException {
/* 165 */     NativeUnixSocket.listen(this.fd, backlog);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void sendUrgentData(int data) throws IOException {
/* 170 */     NativeUnixSocket.write(this.fd, new byte[] { (byte)(data & 0xFF) }, 0, 1);
/*     */   }
/*     */   
/*     */   private final class AFUNIXInputStream
/*     */     extends InputStream {
/*     */     private boolean streamClosed = false;
/*     */     
/*     */     public int read(byte[] buf, int off, int len) throws IOException {
/* 178 */       if (this.streamClosed) {
/* 179 */         throw new IOException("This InputStream has already been closed.");
/*     */       }
/* 181 */       if (len == 0) {
/* 182 */         return 0;
/*     */       }
/* 184 */       int maxRead = buf.length - off;
/* 185 */       if (len > maxRead) {
/* 186 */         len = maxRead;
/*     */       }
/*     */       try {
/* 189 */         return NativeUnixSocket.read(AFUNIXSocketImpl.this.fd, buf, off, len);
/* 190 */       } catch (IOException e) {
/* 191 */         throw (IOException)(new IOException(e.getMessage() + " at " + AFUNIXSocketImpl.this
/* 192 */             .toString())).initCause(e);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public int read() throws IOException {
/* 198 */       byte[] buf1 = new byte[1];
/* 199 */       int numRead = read(buf1, 0, 1);
/* 200 */       if (numRead <= 0) {
/* 201 */         return -1;
/*     */       }
/* 203 */       return buf1[0] & 0xFF;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void close() throws IOException {
/* 209 */       if (this.streamClosed) {
/*     */         return;
/*     */       }
/* 212 */       this.streamClosed = true;
/* 213 */       if (AFUNIXSocketImpl.this.fd.valid()) {
/* 214 */         NativeUnixSocket.shutdown(AFUNIXSocketImpl.this.fd, 0);
/*     */       }
/*     */       
/* 217 */       AFUNIXSocketImpl.this.closedInputStream = true;
/* 218 */       AFUNIXSocketImpl.this.checkClose();
/*     */     }
/*     */ 
/*     */     
/*     */     public int available() throws IOException {
/* 223 */       int av = NativeUnixSocket.available(AFUNIXSocketImpl.this.fd);
/* 224 */       return av;
/*     */     }
/*     */     
/*     */     private AFUNIXInputStream() {}
/*     */   }
/*     */   
/*     */   private final class AFUNIXOutputStream
/*     */     extends OutputStream {
/*     */     public void write(int oneByte) throws IOException {
/* 233 */       byte[] buf1 = { (byte)oneByte };
/* 234 */       write(buf1, 0, 1);
/*     */     }
/*     */     private boolean streamClosed = false;
/*     */     
/*     */     public void write(byte[] buf, int off, int len) throws IOException {
/* 239 */       if (this.streamClosed) {
/* 240 */         throw new AFUNIXSocketException("This OutputStream has already been closed.");
/*     */       }
/* 242 */       if (len > buf.length - off) {
/* 243 */         throw new IndexOutOfBoundsException();
/*     */       }
/*     */       try {
/* 246 */         while (len > 0 && !Thread.interrupted()) {
/* 247 */           int written = NativeUnixSocket.write(AFUNIXSocketImpl.this.fd, buf, off, len);
/* 248 */           if (written == -1) {
/* 249 */             throw new IOException("Unspecific error while writing");
/*     */           }
/* 251 */           len -= written;
/* 252 */           off += written;
/*     */         } 
/* 254 */       } catch (IOException e) {
/* 255 */         throw (IOException)(new IOException(e.getMessage() + " at " + AFUNIXSocketImpl.this
/* 256 */             .toString())).initCause(e);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void close() throws IOException {
/* 262 */       if (this.streamClosed) {
/*     */         return;
/*     */       }
/* 265 */       this.streamClosed = true;
/* 266 */       if (AFUNIXSocketImpl.this.fd.valid()) {
/* 267 */         NativeUnixSocket.shutdown(AFUNIXSocketImpl.this.fd, 1);
/*     */       }
/* 269 */       AFUNIXSocketImpl.this.closedOutputStream = true;
/* 270 */       AFUNIXSocketImpl.this.checkClose();
/*     */     }
/*     */     
/*     */     private AFUNIXOutputStream() {} }
/*     */   
/*     */   public String toString() {
/* 276 */     return super.toString() + "[fd=" + this.fd + "; file=" + this.socketFile + "; connected=" + this.connected + "; bound=" + this.bound + "]";
/*     */   }
/*     */ 
/*     */   
/*     */   private static int expectInteger(Object value) throws SocketException {
/*     */     try {
/* 282 */       return ((Integer)value).intValue();
/* 283 */     } catch (ClassCastException e) {
/* 284 */       throw new AFUNIXSocketException("Unsupported value: " + value, e);
/* 285 */     } catch (NullPointerException e) {
/* 286 */       throw new AFUNIXSocketException("Value must not be null", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static int expectBoolean(Object value) throws SocketException {
/*     */     try {
/* 292 */       return ((Boolean)value).booleanValue() ? 1 : 0;
/* 293 */     } catch (ClassCastException e) {
/* 294 */       throw new AFUNIXSocketException("Unsupported value: " + value, e);
/* 295 */     } catch (NullPointerException e) {
/* 296 */       throw new AFUNIXSocketException("Value must not be null", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getOption(int optID) throws SocketException {
/*     */     try {
/* 303 */       switch (optID) {
/*     */         case 1:
/*     */         case 8:
/* 306 */           return Boolean.valueOf((NativeUnixSocket.getSocketOptionInt(this.fd, optID) != 0));
/*     */         case 128:
/*     */         case 4097:
/*     */         case 4098:
/*     */         case 4102:
/* 311 */           return Integer.valueOf(NativeUnixSocket.getSocketOptionInt(this.fd, optID));
/*     */       } 
/* 313 */       throw new AFUNIXSocketException("Unsupported option: " + optID);
/*     */     }
/* 315 */     catch (AFUNIXSocketException e) {
/* 316 */       throw e;
/* 317 */     } catch (Exception e) {
/* 318 */       throw new AFUNIXSocketException("Error while getting option", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOption(int optID, Object value) throws SocketException {
/*     */     try {
/* 325 */       switch (optID) {
/*     */         
/*     */         case 128:
/* 328 */           if (value instanceof Boolean) {
/* 329 */             boolean b = ((Boolean)value).booleanValue();
/* 330 */             if (b) {
/* 331 */               throw new SocketException("Only accepting Boolean.FALSE here");
/*     */             }
/* 333 */             NativeUnixSocket.setSocketOptionInt(this.fd, optID, -1);
/*     */             return;
/*     */           } 
/* 336 */           NativeUnixSocket.setSocketOptionInt(this.fd, optID, expectInteger(value));
/*     */           return;
/*     */         case 4097:
/*     */         case 4098:
/*     */         case 4102:
/* 341 */           NativeUnixSocket.setSocketOptionInt(this.fd, optID, expectInteger(value));
/*     */           return;
/*     */         case 1:
/*     */         case 8:
/* 345 */           NativeUnixSocket.setSocketOptionInt(this.fd, optID, expectBoolean(value));
/*     */           return;
/*     */       } 
/* 348 */       throw new AFUNIXSocketException("Unsupported option: " + optID);
/*     */     }
/* 350 */     catch (AFUNIXSocketException e) {
/* 351 */       throw e;
/* 352 */     } catch (Exception e) {
/* 353 */       throw new AFUNIXSocketException("Error while setting option", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void shutdownInput() throws IOException {
/* 359 */     if (!this.closed && this.fd.valid()) {
/* 360 */       NativeUnixSocket.shutdown(this.fd, 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void shutdownOutput() throws IOException {
/* 366 */     if (!this.closed && this.fd.valid()) {
/* 367 */       NativeUnixSocket.shutdown(this.fd, 1);
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
/*     */   static class Lenient
/*     */     extends AFUNIXSocketImpl
/*     */   {
/*     */     public void setOption(int optID, Object value) throws SocketException {
/*     */       try {
/* 385 */         super.setOption(optID, value);
/* 386 */       } catch (SocketException e) {
/* 387 */         switch (optID) {
/*     */           case 1:
/*     */             return;
/*     */         } 
/* 391 */         throw e;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Object getOption(int optID) throws SocketException {
/*     */       try {
/* 399 */         return super.getOption(optID);
/* 400 */       } catch (SocketException e) {
/* 401 */         switch (optID) {
/*     */           case 1:
/*     */           case 8:
/* 404 */             return Boolean.valueOf(false);
/*     */         } 
/* 406 */         throw e;
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\org\newsclub\ne\\unix\AFUNIXSocketImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */