/*     */ package com.jagrosh.discordipc.entities;
/*     */ 
/*     */ import java.util.function.Consumer;
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
/*     */ public class Callback
/*     */ {
/*     */   private final Consumer<Packet> success;
/*     */   private final Consumer<String> failure;
/*     */   
/*     */   public Callback() {
/*  38 */     this((Consumer<Packet>)null, (Consumer<String>)null);
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
/*     */   public Callback(Consumer<Packet> success) {
/*  50 */     this(success, (Consumer<String>)null);
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
/*     */   public Callback(Consumer<Packet> success, Consumer<String> failure) {
/*  63 */     this.success = success;
/*  64 */     this.failure = failure;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Callback(Runnable success, Consumer<String> failure) {
/*  74 */     this(p -> success.run(), failure);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Callback(Runnable success) {
/*  83 */     this(p -> success.run(), (Consumer<String>)null);
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
/*     */   public boolean isEmpty() {
/*  98 */     return (this.success == null && this.failure == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void succeed(Packet packet) {
/* 106 */     if (this.success != null) {
/* 107 */       this.success.accept(packet);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fail(String message) {
/* 118 */     if (this.failure != null)
/* 119 */       this.failure.accept(message); 
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\com\jagrosh\discordipc\entities\Callback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */