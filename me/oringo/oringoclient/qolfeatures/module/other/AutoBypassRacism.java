/*    */ package me.oringo.oringoclient.qolfeatures.module.other;
/*    */ 
/*    */ import com.mojang.realmsclient.gui.ChatFormatting;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.events.PacketSentEvent;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.ModeSetting;
/*    */ import net.minecraft.network.play.client.C01PacketChatMessage;
/*    */ import net.minecraftforge.client.event.ClientChatReceivedEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class AutoBypassRacism extends Module {
/* 14 */   private String prefix = "";
/* 15 */   private static String normal = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789";
/* 16 */   private static String custom = "ｑｗｅｒｔｙｕｉｏｐａｓｄｆｇｈｊｋｌｚｘｃｖｂｎｍｑｗｅｒｔｙｕｉｏｐａｓｄｆｇｈｊｋｌｚｘｃｖｂｎｍ０１２３４５６７８９";
/*    */   
/* 18 */   public ModeSetting mode = new ModeSetting("mode", "font", new String[] { "font", "dots" });
/*    */   
/*    */   public AutoBypassRacism() {
/* 21 */     super("Chat bypass", 0, Module.Category.OTHER);
/* 22 */     addSettings(new Setting[] { (Setting)this.mode });
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onPacket(PacketSentEvent event) {
/* 27 */     if (event.packet instanceof C01PacketChatMessage) {
/* 28 */       this.prefix = "";
/* 29 */       if (((C01PacketChatMessage)event.packet).func_149439_c().charAt(0) == '/') {
/* 30 */         this.prefix = ((C01PacketChatMessage)event.packet).func_149439_c().split(" ")[0];
/* 31 */         if (this.prefix.equalsIgnoreCase("/msg") || this.prefix.equalsIgnoreCase("/message") || this.prefix
/* 32 */           .equalsIgnoreCase("/t") || this.prefix.equalsIgnoreCase("/tell") || this.prefix
/* 33 */           .equalsIgnoreCase("/w")) {
/* 34 */           this.prefix += " ";
/* 35 */           if ((((C01PacketChatMessage)event.packet).func_149439_c().split(" ")).length > 1)
/* 36 */             this.prefix += ((C01PacketChatMessage)event.packet).func_149439_c().split(" ")[1]; 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onChat(ClientChatReceivedEvent event) {
/* 44 */     if (!isToggled())
/* 45 */       return;  String blockedmsg = ChatFormatting.stripFormatting(event.message.func_150254_d());
/* 46 */     if (event.message.func_150254_d().equals("§r§6§m-----------------------------------------§r")) {
/* 47 */       event.setCanceled(true);
/*    */     }
/* 49 */     if (blockedmsg.startsWith("We blocked your comment \"")) {
/* 50 */       StringBuilder msg = new StringBuilder();
/* 51 */       StringBuilder message = new StringBuilder(); int i;
/* 52 */       for (i = 1; i < (blockedmsg.split("\"")).length - 1; i++) {
/* 53 */         msg.append(blockedmsg.split("\"")[i]);
/*    */       }
/*    */       
/* 56 */       message.append(this.prefix).append(" ");
/*    */       
/* 58 */       for (i = 0; i < msg.toString().length(); i++) {
/* 59 */         char Char = msg.toString().charAt(i);
/*    */ 
/*    */         
/* 62 */         switch (this.mode.getSelected()) {
/*    */           case "dots":
/* 64 */             message.append(Char + ((Char == ' ') ? "" : "ˌ"));
/*    */             break;
/*    */           case "font":
/* 67 */             message.append(normal.contains(Char + "") ? custom.toCharArray()[normal.indexOf(Char)] : Char);
/*    */             break;
/*    */         } 
/*    */       
/*    */       } 
/* 72 */       event.setCanceled(true);
/* 73 */       (new Thread(() -> {
/*    */             try {
/*    */               Thread.sleep(550L);
/* 76 */             } catch (InterruptedException e) {
/*    */               e.printStackTrace();
/*    */             } 
/*    */             OringoClient.mc.field_71439_g.func_71165_d(message.toString());
/* 80 */           })).start();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\other\AutoBypassRacism.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */