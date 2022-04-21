/*    */ package me.oringo.oringoclient.utils;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParser;
/*    */ import java.io.InputStreamReader;
/*    */ import java.net.URL;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HypixelAPI
/*    */ {
/*    */   public static JsonObject getPlayer(String uuid, String apiKey) {
/*    */     try {
/* 15 */       JsonObject d = (new JsonParser()).parse(new InputStreamReader((new URL(String.format("https://api.hypixel.net/player?uuid=%s&key=%s", new Object[] { uuid, apiKey }))).openStream())).getAsJsonObject();
/* 16 */       if (d.get("player") instanceof com.google.gson.JsonNull) return null; 
/* 17 */       return d.getAsJsonObject("player");
/* 18 */     } catch (Exception e) {
/* 19 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static double getSumoWLR(JsonObject player) {
/*    */     try {
/* 25 */       if (player.getAsJsonObject("stats").getAsJsonObject("Duels").get("sumo_duel_wins").getAsInt() == 0) return 0.0D; 
/* 26 */       if (player.getAsJsonObject("stats").getAsJsonObject("Duels").get("sumo_duel_losses").getAsInt() == 0) return -1.0D; 
/* 27 */       return player.getAsJsonObject("stats").getAsJsonObject("Duels").get("sumo_duel_wins").getAsInt() / player.getAsJsonObject("stats").getAsJsonObject("Duels").get("sumo_duel_losses").getAsInt();
/* 28 */     } catch (Exception e) {
/* 29 */       return -1.0D;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static String getName(JsonObject player) {
/*    */     try {
/* 35 */       return player.get("displayname").getAsString();
/* 36 */     } catch (Exception e) {
/* 37 */       return "";
/*    */     } 
/*    */   }
/*    */   
/*    */   public static int getSumoWins(JsonObject player) {
/*    */     try {
/* 43 */       return player.getAsJsonObject("stats").getAsJsonObject("Duels").get("sumo_duel_wins").getAsInt();
/* 44 */     } catch (Exception e) {
/* 45 */       return 10000000;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static int getSumoLosses(JsonObject player) {
/*    */     try {
/* 51 */       return player.getAsJsonObject("stats").getAsJsonObject("Duels").get("sumo_duel_losses").getAsInt();
/* 52 */     } catch (Exception e) {
/* 53 */       return 0;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static int getSumoStreak(JsonObject player) {
/*    */     try {
/* 59 */       return player.getAsJsonObject("stats").getAsJsonObject("Duels").get("current_sumo_winstreak").getAsInt();
/* 60 */     } catch (Exception e) {
/* 61 */       return 0;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclien\\utils\HypixelAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */