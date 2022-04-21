/*     */ package me.oringo.oringoclient.qolfeatures.module.combat;
/*     */ 
/*     */ import com.mojang.realmsclient.gui.ChatFormatting;
/*     */ import java.awt.Color;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*     */ import me.oringo.oringoclient.qolfeatures.module.macro.AutoSumoBot;
/*     */ import me.oringo.oringoclient.utils.DiscordWebhook;
/*     */ import me.oringo.oringoclient.utils.ReflectionUtils;
/*     */ import me.oringo.oringoclient.utils.SkyblockUtils;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiMainMenu;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.multiplayer.GuiConnecting;
/*     */ import net.minecraft.client.multiplayer.ServerData;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraftforge.client.event.ClientChatReceivedEvent;
/*     */ import net.minecraftforge.client.event.GuiOpenEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SumoFences
/*     */   extends Module
/*     */ {
/*     */   private boolean sumo = false;
/*  34 */   private ArrayList<BlockPos> posList = new ArrayList<>();
/*  35 */   private List<String> win = Arrays.asList(new String[] { "gg", "gf" });
/*  36 */   private List<String> lose = Arrays.asList(new String[] { "gg", "gf", "how" });
/*  37 */   private String winTime = "";
/*  38 */   private static int shouldReconnect = -1;
/*  39 */   private static int wins = -1;
/*  40 */   private static int winstreak = 0;
/*  41 */   private static int lost = 0;
/*     */ 
/*     */   
/*     */   public SumoFences() {
/*  45 */     super("Sumo Fences", 0, Module.Category.COMBAT);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onSumoStart(TickEvent.ClientTickEvent event) {
/*     */     try {
/*  52 */       if (isToggled()) {
/*  53 */         if (SkyblockUtils.hasLine("Mode: Sumo") && SkyblockUtils.hasLine("Opponent:")) {
/*  54 */           if (!this.sumo) {
/*  55 */             this.sumo = true;
/*  56 */             Minecraft mc = Minecraft.func_71410_x();
/*     */             
/*  58 */             BlockPos pos = mc.field_71439_g.func_180425_c();
/*  59 */             int i = 0;
/*     */             
/*  61 */             while (i++ != 10) {
/*  62 */               if ((Minecraft.func_71410_x()).field_71441_e.func_180495_p(pos = pos.func_177977_b()).func_177230_c().equals(Blocks.field_150350_a))
/*     */                 continue; 
/*  64 */               BlockPos finalPos = pos;
/*  65 */               (new Thread(() -> {
/*     */                     try {
/*     */                       Thread.sleep(1000L);
/*  68 */                     } catch (InterruptedException e) {
/*     */                       e.printStackTrace();
/*     */                     } 
/*     */                     this.sumo = false;
/*     */                     this.posList.clear();
/*     */                     checkBlock(finalPos.func_177977_b());
/*  74 */                   })).start();
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } else {
/*  79 */           if (this.sumo) this.posList.clear(); 
/*  80 */           this.sumo = false;
/*     */         } 
/*     */       }
/*  83 */     } catch (Exception exception) {}
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onChat(ClientChatReceivedEvent event) {
/*  88 */     if (AutoSumoBot.thread == null)
/*  89 */       return;  if (ChatFormatting.stripFormatting(event.message.func_150254_d()).contains("                           Sumo Duel - ")) {
/*  90 */       this.winTime = ChatFormatting.stripFormatting(event.message.func_150254_d()).replaceFirst("                           Sumo Duel - ", "");
/*     */     }
/*     */     
/*  93 */     if (event.message.func_150254_d().contains("§f§lMelee Accuracy")) {
/*  94 */       (new Thread(() -> {
/*     */             try {
/*     */               Thread.sleep(250L);
/*     */               if ((Minecraft.func_71410_x()).field_71439_g.field_71075_bZ.field_75100_b) {
/*     */                 String title = (String)ReflectionUtils.getFieldByName((Minecraft.func_71410_x()).field_71456_v, "field_175201_x");
/*     */                 if (title != null && title.toUpperCase().contains("DRAW")) {
/*     */                   DiscordWebhook discordWebhook = new DiscordWebhook(OringoClient.autoSumo.webhook.getValue());
/*     */                   discordWebhook.setUsername("AutoSumo bot");
/*     */                   discordWebhook.setAvatarUrl("https://cdn.discordapp.com/icons/913088401262137424/496d604510a63242db77526d8bfab9fa.png");
/*     */                   DiscordWebhook.EmbedObject embedObject = new DiscordWebhook.EmbedObject();
/*     */                   embedObject.setTitle("Draw").setDescription("Opponent: " + AutoSumoBot.target.func_70005_c_() + " Time: " + this.winTime).setColor(Color.ORANGE);
/*     */                   discordWebhook.addEmbed(embedObject);
/*     */                   discordWebhook.execute();
/*     */                   return;
/*     */                 } 
/*     */                 (Minecraft.func_71410_x()).field_71439_g.func_71165_d("/ac " + (String)this.lose.get((new Random()).nextInt(this.lose.size())));
/*     */                 DiscordWebhook webhook = new DiscordWebhook(OringoClient.autoSumo.webhook.getValue());
/*     */                 webhook.setUsername("AutoSumo bot");
/*     */                 webhook.setAvatarUrl("https://cdn.discordapp.com/icons/913088401262137424/496d604510a63242db77526d8bfab9fa.png");
/*     */                 DiscordWebhook.EmbedObject embed = new DiscordWebhook.EmbedObject();
/*     */                 winstreak = 0;
/*     */                 lost++;
/*     */                 embed.setTitle("Lost").setDescription("Opponent: " + AutoSumoBot.target.func_70005_c_() + " Time: " + this.winTime + " Loses this session: " + lost).addField("Blacklist", " Added " + AutoSumoBot.target.func_110124_au().toString() + " to blacklist!", false).setColor(Color.RED);
/*     */                 webhook.addEmbed(embed);
/*     */                 webhook.execute();
/*     */               } else {
/*     */                 (Minecraft.func_71410_x()).field_71439_g.func_71165_d("/ac " + (String)this.win.get((new Random()).nextInt(this.win.size())));
/*     */                 DiscordWebhook webhook = new DiscordWebhook(OringoClient.autoSumo.webhook.getValue());
/*     */                 webhook.setUsername("AutoSumo bot");
/*     */                 webhook.setAvatarUrl("https://cdn.discordapp.com/icons/913088401262137424/496d604510a63242db77526d8bfab9fa.png");
/*     */                 DiscordWebhook.EmbedObject embed = new DiscordWebhook.EmbedObject();
/*     */                 wins++;
/*     */                 winstreak++;
/*     */                 embed.setTitle("WIN").setDescription("Opponent: " + AutoSumoBot.target.func_70005_c_() + " Time: " + this.winTime + " Wins: " + wins + " winstreak: " + winstreak).setColor(Color.GREEN);
/*     */                 webhook.addEmbed(embed);
/*     */                 webhook.execute();
/*     */               } 
/*     */               Thread.sleep(100L);
/* 132 */             } catch (InterruptedException|IOException e) {
/*     */               e.printStackTrace();
/*     */             } 
/*     */             (Minecraft.func_71410_x()).field_71439_g.func_71165_d("/play duels_sumo_duel");
/* 136 */           })).start();
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onDisconnect(GuiOpenEvent event) {
/* 142 */     if (event.gui instanceof net.minecraft.client.gui.GuiDisconnected && AutoSumoBot.thread != null) {
/* 143 */       DiscordWebhook webhook = new DiscordWebhook(OringoClient.autoSumo.webhook.getValue());
/* 144 */       webhook.setUsername("AutoSumo bot");
/* 145 */       webhook.setAvatarUrl("https://cdn.discordapp.com/icons/913088401262137424/496d604510a63242db77526d8bfab9fa.png");
/* 146 */       webhook.setContent("Bot Disconnected! <@884509916868517898>");
/* 147 */       shouldReconnect = 2000;
/*     */       try {
/* 149 */         webhook.execute();
/* 150 */       } catch (IOException e) {
/* 151 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void reconnect(TickEvent.ClientTickEvent event) {
/* 158 */     if (shouldReconnect-- == 0 && AutoSumoBot.thread != null) {
/* 159 */       OringoClient.mc.func_147108_a((GuiScreen)new GuiConnecting((GuiScreen)new GuiMainMenu(), OringoClient.mc, new ServerData("Hypixel", "play.Hypixel.net", false)));
/*     */     }
/*     */   }
/*     */   
/*     */   private void checkBlock(BlockPos pos) {
/* 164 */     for (BlockPos blockPos : Arrays.<BlockPos>asList(new BlockPos[] { pos.func_177976_e(), pos.func_177978_c().func_177974_f(), pos.func_177978_c().func_177976_e(), pos.func_177968_d().func_177976_e(), pos.func_177968_d().func_177974_f(), pos.func_177974_f(), pos.func_177978_c(), pos.func_177968_d() })) {
/* 165 */       if ((Minecraft.func_71410_x()).field_71441_e.func_180495_p(blockPos).func_177230_c().equals(Blocks.field_150350_a)) {
/* 166 */         (Minecraft.func_71410_x()).field_71441_e.func_175656_a(blockPos.func_177984_a().func_177984_a().func_177984_a(), Blocks.field_150350_a.func_176223_P());
/* 167 */         (Minecraft.func_71410_x()).field_71441_e.func_175656_a(blockPos.func_177984_a().func_177984_a().func_177984_a(), Blocks.field_180407_aO.func_176223_P());
/* 168 */         (Minecraft.func_71410_x()).field_71441_e.func_175656_a(blockPos.func_177984_a().func_177984_a().func_177984_a().func_177984_a(), Blocks.field_180407_aO.func_176223_P());
/*     */         continue;
/*     */       } 
/* 171 */       if (!this.posList.contains(blockPos)) {
/* 172 */         this.posList.add(blockPos);
/* 173 */         checkBlock(blockPos);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\combat\SumoFences.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */