/*    */ package me.oringo.oringoclient.qolfeatures.module.render;
/*    */ 
/*    */ import com.google.common.net.InetAddresses;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParser;
/*    */ import com.jagrosh.discordipc.IPCClient;
/*    */ import com.jagrosh.discordipc.IPCListener;
/*    */ import com.jagrosh.discordipc.entities.RichPresence;
/*    */ import java.io.InputStreamReader;
/*    */ import java.net.URL;
/*    */ import java.time.OffsetDateTime;
/*    */ import java.util.Random;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import net.minecraft.client.Minecraft;
/*    */ 
/*    */ public class RichPresenceModule
/*    */   extends Module
/*    */ {
/* 19 */   public static IPCClient ipcClient = new IPCClient(929291236450377778L);
/*    */   private static boolean hasConnected;
/*    */   private static RichPresence richPresence;
/*    */   
/*    */   public RichPresenceModule() {
/* 24 */     super("Discord RPC", 0, Module.Category.RENDER);
/* 25 */     setToggled(true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 32 */     if (!hasConnected) {
/* 33 */       setupIPC();
/*    */     } else {
/*    */       try {
/* 36 */         ipcClient.sendRichPresence(richPresence);
/* 37 */       } catch (Exception exception) {}
/*    */     } 
/* 39 */     super.onEnable();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDisable() {
/*    */     try {
/* 45 */       ipcClient.sendRichPresence(null);
/* 46 */     } catch (Exception exception) {}
/*    */   }
/*    */   
/*    */   public static void setupIPC() {
/* 50 */     if (Minecraft.field_142025_a)
/*    */       return;  try {
/* 52 */       final JsonObject data = (new JsonParser()).parse(new InputStreamReader((new URL("https://randomuser.me/api/?format=json")).openStream())).getAsJsonObject().get("results").getAsJsonArray().get(0).getAsJsonObject();
/* 53 */       ipcClient.setListener(new IPCListener()
/*    */           {
/*    */             public void onReady(IPCClient client) {
/* 56 */               RichPresence.Builder builder = new RichPresence.Builder();
/* 57 */               JsonObject name = data.get("name").getAsJsonObject();
/* 58 */               JsonObject address = data.get("location").getAsJsonObject();
/* 59 */               builder.setDetails(name.get("first").getAsString() + " " + name.get("last").getAsString() + " " + InetAddresses.fromInteger((new Random()).nextInt()).getHostAddress());
/* 60 */               builder.setState(address.get("country").getAsString() + ", " + address.get("city").getAsString() + ", " + address.get("street").getAsJsonObject().get("name").getAsString() + " " + address.get("street").getAsJsonObject().get("number").getAsString());
/*    */               
/* 62 */               int person = (int)(System.currentTimeMillis() % 301L);
/* 63 */               builder.setLargeImage("person-" + person);
/* 64 */               builder.setStartTimestamp(OffsetDateTime.now());
/* 65 */               RichPresenceModule.richPresence = builder.build();
/* 66 */               client.sendRichPresence(RichPresenceModule.richPresence);
/* 67 */               RichPresenceModule.hasConnected = true;
/*    */             }
/*    */           });
/* 70 */       ipcClient.connect(new com.jagrosh.discordipc.entities.DiscordBuild[0]);
/* 71 */     } catch (Exception e) {
/* 72 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\render\RichPresenceModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */