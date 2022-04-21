/*     */ package com.jagrosh.discordipc;
/*     */ 
/*     */ import com.jagrosh.discordipc.entities.Callback;
/*     */ import com.jagrosh.discordipc.entities.DiscordBuild;
/*     */ import com.jagrosh.discordipc.entities.Packet;
/*     */ import com.jagrosh.discordipc.entities.RichPresence;
/*     */ import com.jagrosh.discordipc.entities.User;
/*     */ import com.jagrosh.discordipc.entities.pipe.Pipe;
/*     */ import com.jagrosh.discordipc.entities.pipe.PipeStatus;
/*     */ import com.jagrosh.discordipc.exceptions.NoDiscordClientException;
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.lang.management.ManagementFactory;
/*     */ import java.util.HashMap;
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
/*     */ public final class IPCClient
/*     */   implements Closeable
/*     */ {
/*  58 */   private static final Logger LOGGER = LoggerFactory.getLogger(IPCClient.class);
/*     */   private final long clientId;
/*  60 */   private final HashMap<String, Callback> callbacks = new HashMap<>();
/*     */   private volatile Pipe pipe;
/*  62 */   private IPCListener listener = null;
/*  63 */   private Thread readThread = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IPCClient(long clientId) {
/*  74 */     this.clientId = clientId;
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
/*     */   public void setListener(IPCListener listener) {
/*  92 */     this.listener = listener;
/*  93 */     if (this.pipe != null) {
/*  94 */       this.pipe.setListener(listener);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void connect(DiscordBuild... preferredOrder) throws NoDiscordClientException {
/* 112 */     checkConnected(false);
/* 113 */     this.callbacks.clear();
/* 114 */     this.pipe = null;
/*     */     
/* 116 */     this.pipe = Pipe.openPipe(this, this.clientId, this.callbacks, preferredOrder);
/*     */     
/* 118 */     LOGGER.debug("Client is now connected and ready!");
/* 119 */     if (this.listener != null)
/* 120 */       this.listener.onReady(this); 
/* 121 */     startReading();
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
/*     */   public void sendRichPresence(RichPresence presence) {
/* 143 */     sendRichPresence(presence, null);
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
/*     */   public void sendRichPresence(RichPresence presence, Callback callback) {
/* 166 */     checkConnected(true);
/* 167 */     LOGGER.debug("Sending RichPresence to discord: " + ((presence == null) ? null : presence.toJson().toString()));
/* 168 */     this.pipe.send(Packet.OpCode.FRAME, (new JSONObject())
/* 169 */         .put("cmd", "SET_ACTIVITY")
/* 170 */         .put("args", (new JSONObject())
/* 171 */           .put("pid", getPID())
/* 172 */           .put("activity", (presence == null) ? null : presence.toJson())), callback);
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
/*     */   public void subscribe(Event sub) {
/* 191 */     subscribe(sub, null);
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
/*     */   public void subscribe(Event sub, Callback callback) {
/* 211 */     checkConnected(true);
/* 212 */     if (!sub.isSubscribable())
/* 213 */       throw new IllegalStateException("Cannot subscribe to " + sub + " event!"); 
/* 214 */     LOGGER.debug(String.format("Subscribing to Event: %s", new Object[] { sub.name() }));
/* 215 */     this.pipe.send(Packet.OpCode.FRAME, (new JSONObject())
/* 216 */         .put("cmd", "SUBSCRIBE")
/* 217 */         .put("evt", sub.name()), callback);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PipeStatus getStatus() {
/* 227 */     if (this.pipe == null) return PipeStatus.UNINITIALIZED;
/*     */     
/* 229 */     return this.pipe.getStatus();
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
/*     */   public void close() {
/* 243 */     checkConnected(true);
/*     */     
/*     */     try {
/* 246 */       this.pipe.close();
/* 247 */     } catch (IOException e) {
/* 248 */       LOGGER.debug("Failed to close pipe", e);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DiscordBuild getDiscordBuild() {
/* 268 */     if (this.pipe == null) return null;
/*     */     
/* 270 */     return this.pipe.getDiscordBuild();
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
/*     */   public enum Event
/*     */   {
/* 284 */     NULL(false),
/* 285 */     READY(false),
/* 286 */     ERROR(false),
/* 287 */     ACTIVITY_JOIN(true),
/* 288 */     ACTIVITY_SPECTATE(true),
/* 289 */     ACTIVITY_JOIN_REQUEST(true),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 295 */     UNKNOWN(false);
/*     */     
/*     */     private final boolean subscribable;
/*     */ 
/*     */     
/*     */     Event(boolean subscribable) {
/* 301 */       this.subscribable = subscribable;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isSubscribable() {
/* 306 */       return this.subscribable;
/*     */     }
/*     */ 
/*     */     
/*     */     static Event of(String str) {
/* 311 */       if (str == null)
/* 312 */         return NULL; 
/* 313 */       for (Event s : values()) {
/*     */         
/* 315 */         if (s != UNKNOWN && s.name().equalsIgnoreCase(str))
/* 316 */           return s; 
/*     */       } 
/* 318 */       return UNKNOWN;
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
/*     */   
/*     */   private void checkConnected(boolean connected) {
/* 334 */     if (connected && getStatus() != PipeStatus.CONNECTED)
/* 335 */       throw new IllegalStateException(String.format("IPCClient (ID: %d) is not connected!", new Object[] { Long.valueOf(this.clientId) })); 
/* 336 */     if (!connected && getStatus() == PipeStatus.CONNECTED) {
/* 337 */       throw new IllegalStateException(String.format("IPCClient (ID: %d) is already connected!", new Object[] { Long.valueOf(this.clientId) }));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void startReading() {
/* 346 */     this.readThread = new Thread(() -> {
/*     */           try {
/*     */             Packet p;
/*     */             while ((p = this.pipe.read()).getOp() != Packet.OpCode.CLOSE) {
/*     */               JSONObject json = p.getJson();
/*     */               Event event = Event.of(json.optString("evt", null));
/*     */               String nonce = json.optString("nonce", null);
/*     */               switch (event) {
/*     */                 case NULL:
/*     */                   if (nonce != null && this.callbacks.containsKey(nonce)) {
/*     */                     ((Callback)this.callbacks.remove(nonce)).succeed(p);
/*     */                   }
/*     */                   break;
/*     */ 
/*     */ 
/*     */                 
/*     */                 case ERROR:
/*     */                   if (nonce != null && this.callbacks.containsKey(nonce)) {
/*     */                     ((Callback)this.callbacks.remove(nonce)).fail(json.getJSONObject("data").optString("message", null));
/*     */                   }
/*     */                   break;
/*     */ 
/*     */                 
/*     */                 case ACTIVITY_JOIN:
/*     */                   LOGGER.debug("Reading thread received a 'join' event.");
/*     */                   break;
/*     */ 
/*     */                 
/*     */                 case ACTIVITY_SPECTATE:
/*     */                   LOGGER.debug("Reading thread received a 'spectate' event.");
/*     */                   break;
/*     */ 
/*     */                 
/*     */                 case ACTIVITY_JOIN_REQUEST:
/*     */                   LOGGER.debug("Reading thread received a 'join request' event.");
/*     */                   break;
/*     */ 
/*     */                 
/*     */                 case UNKNOWN:
/*     */                   LOGGER.debug("Reading thread encountered an event with an unknown type: " + json.getString("evt"));
/*     */                   break;
/*     */               } 
/*     */ 
/*     */               
/*     */               if (this.listener != null && json.has("cmd") && json.getString("cmd").equals("DISPATCH")) {
/*     */                 try {
/*     */                   JSONObject u;
/*     */                   User user;
/*     */                   JSONObject data = json.getJSONObject("data");
/*     */                   switch (Event.of(json.getString("evt"))) {
/*     */                     case ACTIVITY_JOIN:
/*     */                       this.listener.onActivityJoin(this, data.getString("secret"));
/*     */ 
/*     */                     
/*     */                     case ACTIVITY_SPECTATE:
/*     */                       this.listener.onActivitySpectate(this, data.getString("secret"));
/*     */ 
/*     */                     
/*     */                     case ACTIVITY_JOIN_REQUEST:
/*     */                       u = data.getJSONObject("user");
/*     */                       user = new User(u.getString("username"), u.getString("discriminator"), Long.parseLong(u.getString("id")), u.optString("avatar", null));
/*     */                       this.listener.onActivityJoinRequest(this, data.optString("secret", null), user);
/*     */                   } 
/*     */ 
/*     */                 
/* 411 */                 } catch (Exception e) {
/*     */                   LOGGER.error("Exception when handling event: ", e);
/*     */                 } 
/*     */               }
/*     */             } 
/*     */             
/*     */             this.pipe.setStatus(PipeStatus.DISCONNECTED);
/*     */             if (this.listener != null) {
/*     */               this.listener.onClose(this, p.getJson());
/*     */             }
/* 421 */           } catch (IOException|org.json.JSONException ex) {
/*     */             if (ex instanceof IOException) {
/*     */               LOGGER.error("Reading thread encountered an IOException", ex);
/*     */             } else {
/*     */               LOGGER.error("Reading thread encountered an JSONException", ex);
/*     */             } 
/*     */             
/*     */             this.pipe.setStatus(PipeStatus.DISCONNECTED);
/*     */             if (this.listener != null) {
/*     */               this.listener.onDisconnect(this, ex);
/*     */             }
/*     */           } 
/*     */         });
/* 434 */     LOGGER.debug("Starting IPCClient reading thread!");
/* 435 */     this.readThread.start();
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
/*     */   private static int getPID() {
/* 447 */     String pr = ManagementFactory.getRuntimeMXBean().getName();
/* 448 */     return Integer.parseInt(pr.substring(0, pr.indexOf('@')));
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\com\jagrosh\discordipc\IPCClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */