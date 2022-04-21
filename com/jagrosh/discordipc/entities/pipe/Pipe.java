/*     */ package com.jagrosh.discordipc.entities.pipe;
/*     */ 
/*     */ import com.jagrosh.discordipc.IPCClient;
/*     */ import com.jagrosh.discordipc.IPCListener;
/*     */ import com.jagrosh.discordipc.entities.Callback;
/*     */ import com.jagrosh.discordipc.entities.DiscordBuild;
/*     */ import com.jagrosh.discordipc.entities.Packet;
/*     */ import com.jagrosh.discordipc.exceptions.NoDiscordClientException;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.UUID;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
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
/*     */ 
/*     */ public abstract class Pipe
/*     */ {
/*  36 */   private static final Logger LOGGER = LoggerFactory.getLogger(Pipe.class);
/*     */   private static final int VERSION = 1;
/*  38 */   PipeStatus status = PipeStatus.CONNECTING;
/*     */   
/*     */   IPCListener listener;
/*     */   private DiscordBuild build;
/*     */   final IPCClient ipcClient;
/*     */   private final HashMap<String, Callback> callbacks;
/*     */   
/*     */   Pipe(IPCClient ipcClient, HashMap<String, Callback> callbacks) {
/*  46 */     this.ipcClient = ipcClient;
/*  47 */     this.callbacks = callbacks;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Pipe openPipe(IPCClient ipcClient, long clientId, HashMap<String, Callback> callbacks, DiscordBuild... preferredOrder) throws NoDiscordClientException {
/*  54 */     if (preferredOrder == null || preferredOrder.length == 0) {
/*  55 */       preferredOrder = new DiscordBuild[] { DiscordBuild.ANY };
/*     */     }
/*  57 */     Pipe pipe = null;
/*     */ 
/*     */     
/*  60 */     Pipe[] open = new Pipe[(DiscordBuild.values()).length]; int i;
/*  61 */     for (i = 0; i < 10; i++) {
/*     */ 
/*     */       
/*     */       try {
/*  65 */         String location = getPipeLocation(i);
/*  66 */         LOGGER.debug(String.format("Searching for IPC: %s", new Object[] { location }));
/*  67 */         pipe = createPipe(ipcClient, callbacks, location);
/*     */         
/*  69 */         pipe.send(Packet.OpCode.HANDSHAKE, (new JSONObject()).put("v", 1).put("client_id", Long.toString(clientId)), null);
/*     */         
/*  71 */         Packet p = pipe.read();
/*     */         
/*  73 */         pipe.build = DiscordBuild.from(p.getJson().getJSONObject("data")
/*  74 */             .getJSONObject("config")
/*  75 */             .getString("api_endpoint"));
/*     */         
/*  77 */         LOGGER.debug(String.format("Found a valid client (%s) with packet: %s", new Object[] { pipe.build.name(), p.toString() }));
/*     */         
/*  79 */         if (pipe.build == preferredOrder[0] || DiscordBuild.ANY == preferredOrder[0]) {
/*     */           
/*  81 */           LOGGER.info(String.format("Found preferred client: %s", new Object[] { pipe.build.name() }));
/*     */           
/*     */           break;
/*     */         } 
/*  85 */         open[pipe.build.ordinal()] = pipe;
/*  86 */         open[DiscordBuild.ANY.ordinal()] = pipe;
/*     */         
/*  88 */         pipe.build = null;
/*  89 */         pipe = null;
/*     */       }
/*  91 */       catch (IOException|JSONException ex) {
/*     */         
/*  93 */         pipe = null;
/*     */       } 
/*     */     } 
/*     */     
/*  97 */     if (pipe == null) {
/*     */ 
/*     */ 
/*     */       
/* 101 */       for (i = 1; i < preferredOrder.length; i++) {
/*     */         
/* 103 */         DiscordBuild cb = preferredOrder[i];
/* 104 */         LOGGER.debug(String.format("Looking for client build: %s", new Object[] { cb.name() }));
/* 105 */         if (open[cb.ordinal()] != null) {
/*     */           
/* 107 */           pipe = open[cb.ordinal()];
/* 108 */           open[cb.ordinal()] = null;
/* 109 */           if (cb == DiscordBuild.ANY) {
/*     */             
/* 111 */             for (int k = 0; k < open.length; k++) {
/*     */               
/* 113 */               if (open[k] == pipe) {
/*     */                 
/* 115 */                 pipe.build = DiscordBuild.values()[k];
/* 116 */                 open[k] = null;
/*     */               } 
/*     */             } 
/*     */           } else {
/* 120 */             pipe.build = cb;
/*     */           } 
/* 122 */           LOGGER.info(String.format("Found preferred client: %s", new Object[] { pipe.build.name() }));
/*     */           break;
/*     */         } 
/*     */       } 
/* 126 */       if (pipe == null)
/*     */       {
/* 128 */         throw new NoDiscordClientException();
/*     */       }
/*     */     } 
/*     */     
/* 132 */     for (i = 0; i < open.length; i++) {
/*     */       
/* 134 */       if (i != DiscordBuild.ANY.ordinal())
/*     */       {
/* 136 */         if (open[i] != null) {
/*     */           
/*     */           try {
/* 139 */             open[i].close();
/* 140 */           } catch (IOException ex) {
/*     */ 
/*     */             
/* 143 */             LOGGER.debug("Failed to close an open IPC pipe!", ex);
/*     */           } 
/*     */         }
/*     */       }
/*     */     } 
/* 148 */     pipe.status = PipeStatus.CONNECTED;
/*     */     
/* 150 */     return pipe;
/*     */   }
/*     */   
/*     */   private static Pipe createPipe(IPCClient ipcClient, HashMap<String, Callback> callbacks, String location) {
/* 154 */     String osName = System.getProperty("os.name").toLowerCase();
/*     */     
/* 156 */     if (osName.contains("win"))
/*     */     {
/* 158 */       return new WindowsPipe(ipcClient, callbacks, location);
/*     */     }
/* 160 */     if (osName.contains("linux") || osName.contains("mac")) {
/*     */       
/*     */       try {
/* 163 */         return new UnixPipe(ipcClient, callbacks, location);
/*     */       }
/* 165 */       catch (IOException e) {
/*     */         
/* 167 */         throw new RuntimeException(e);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 172 */     throw new RuntimeException("Unsupported OS: " + osName);
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
/*     */   public void send(Packet.OpCode op, JSONObject data, Callback callback) {
/*     */     try {
/* 187 */       String nonce = generateNonce();
/* 188 */       Packet p = new Packet(op, data.put("nonce", nonce));
/* 189 */       if (callback != null && !callback.isEmpty())
/* 190 */         this.callbacks.put(nonce, callback); 
/* 191 */       write(p.toBytes());
/* 192 */       LOGGER.debug(String.format("Sent packet: %s", new Object[] { p.toString() }));
/* 193 */       if (this.listener != null) {
/* 194 */         this.listener.onPacketSent(this.ipcClient, p);
/*     */       }
/* 196 */     } catch (IOException ex) {
/*     */       
/* 198 */       LOGGER.error("Encountered an IOException while sending a packet and disconnected!");
/* 199 */       this.status = PipeStatus.DISCONNECTED;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract Packet read() throws IOException, JSONException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void write(byte[] paramArrayOfbyte) throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String generateNonce() {
/* 225 */     return UUID.randomUUID().toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public PipeStatus getStatus() {
/* 230 */     return this.status;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStatus(PipeStatus status) {
/* 235 */     this.status = status;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListener(IPCListener listener) {
/* 240 */     this.listener = listener;
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract void close() throws IOException;
/*     */   
/*     */   public DiscordBuild getDiscordBuild() {
/* 247 */     return this.build;
/*     */   }
/*     */ 
/*     */   
/* 251 */   private static final String[] unixPaths = new String[] { "XDG_RUNTIME_DIR", "TMPDIR", "TMP", "TEMP" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getPipeLocation(int i) {
/* 262 */     if (System.getProperty("os.name").contains("Win"))
/* 263 */       return "\\\\?\\pipe\\discord-ipc-" + i; 
/* 264 */     String tmppath = null;
/* 265 */     for (String str : unixPaths) {
/*     */       
/* 267 */       tmppath = System.getenv(str);
/* 268 */       if (tmppath != null)
/*     */         break; 
/*     */     } 
/* 271 */     if (tmppath == null)
/* 272 */       tmppath = "/tmp"; 
/* 273 */     return tmppath + "/discord-ipc-" + i;
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\com\jagrosh\discordipc\entities\pipe\Pipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */