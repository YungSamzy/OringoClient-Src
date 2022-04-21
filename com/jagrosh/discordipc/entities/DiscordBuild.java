/*    */ package com.jagrosh.discordipc.entities;
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
/*    */ public enum DiscordBuild
/*    */ {
/* 27 */   CANARY("//canary.discordapp.com/api"),
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 32 */   PTB("//ptb.discordapp.com/api"),
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 37 */   STABLE("//discordapp.com/api"),
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 46 */   ANY;
/*    */   
/*    */   private final String endpoint;
/*    */ 
/*    */   
/*    */   DiscordBuild(String endpoint) {
/* 52 */     this.endpoint = endpoint;
/*    */   }
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
/*    */   public static DiscordBuild from(String endpoint) {
/* 72 */     for (DiscordBuild value : values()) {
/*    */       
/* 74 */       if (value.endpoint != null && value.endpoint.equals(endpoint))
/*    */       {
/* 76 */         return value;
/*    */       }
/*    */     } 
/* 79 */     return ANY;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\com\jagrosh\discordipc\entities\DiscordBuild.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */