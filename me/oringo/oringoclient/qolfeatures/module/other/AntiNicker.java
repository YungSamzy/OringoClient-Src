/*    */ package me.oringo.oringoclient.qolfeatures.module.other;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParser;
/*    */ import com.mojang.realmsclient.gui.ChatFormatting;
/*    */ import java.net.HttpURLConnection;
/*    */ import java.net.URL;
/*    */ import java.nio.charset.Charset;
/*    */ import java.util.ArrayList;
/*    */ import java.util.UUID;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.utils.Notifications;
/*    */ import me.oringo.oringoclient.utils.SkyblockUtils;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import org.apache.commons.io.IOUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AntiNicker
/*    */   extends Module
/*    */ {
/*    */   public AntiNicker() {
/* 26 */     super("Anti Nicker", 0, Module.Category.OTHER);
/*    */ 
/*    */ 
/*    */     
/* 30 */     this.checked = new ArrayList<>();
/*    */   } public static ArrayList<Entity> nicked = new ArrayList<>();
/*    */   public static boolean isBot(String name) {
/*    */     try {
/* 34 */       HttpURLConnection connection = (HttpURLConnection)(new URL(String.format("https://api.sk1er.club/levelheadv5/%s/LEVEL", new Object[] { name }))).openConnection();
/* 35 */       connection.setRequestMethod("GET");
/* 36 */       connection.setUseCaches(true);
/* 37 */       connection.addRequestProperty("User-Agent", "Mozilla/4.76 (SK1ER LEVEL HEAD V7.0.2)");
/* 38 */       connection.setReadTimeout(150000);
/* 39 */       connection.setConnectTimeout(150000);
/* 40 */       connection.setDoOutput(true);
/* 41 */       JsonObject jo = (new JsonParser()).parse(IOUtils.toString(connection.getInputStream(), Charset.defaultCharset())).getAsJsonObject();
/* 42 */       return (jo.get("bot") != null);
/* 43 */     } catch (Exception exception) {
/* 44 */       return true;
/*    */     } 
/*    */   }
/*    */   private ArrayList<UUID> checked;
/*    */   @SubscribeEvent
/*    */   public void onWorldJoin(EntityJoinWorldEvent e) {
/* 50 */     if (e.entity.equals(OringoClient.mc.field_71439_g)) nicked.clear(); 
/* 51 */     if (!isToggled())
/*    */       return; 
/* 53 */     if (!this.checked.contains(e.entity.func_110124_au()) && (SkyblockUtils.onSkyblock || SkyblockUtils.isInOtherGame) && e.entity instanceof net.minecraft.entity.player.EntityPlayer && !e.entity.equals(OringoClient.mc.field_71439_g) && !e.entity.func_145748_c_().func_150254_d().replaceAll(ChatFormatting.RED.toString(), "").replaceAll(ChatFormatting.RESET.toString(), "").equals(e.entity.func_70005_c_()) && !e.entity.func_145748_c_().func_150254_d().contains(ChatFormatting.RED.toString()) && !ChatFormatting.stripFormatting(e.entity.func_145748_c_().func_150260_c()).startsWith("[NPC]")) {
/* 54 */       this.checked.add(e.entity.func_110124_au());
/* 55 */       (new Thread(() -> {
/*    */             try {
/*    */               Thread.sleep(1000L);
/* 58 */             } catch (InterruptedException interruptedException) {}
/*    */             
/*    */             if (isBot(e.entity.func_110124_au().toString()) && !nicked.contains(e.entity)) {
/*    */               nicked.add(e.entity);
/*    */               
/*    */               Notifications.showNotification("Oringo Client", (e.entity.func_145748_c_().func_150260_c().contains(ChatFormatting.OBFUSCATED.toString()) ? e.entity.func_70005_c_() : e.entity.func_145748_c_().func_150260_c()) + ChatFormatting.RESET + " is a nicker!", 4000);
/*    */             } 
/* 65 */           })).start();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\other\AntiNicker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */