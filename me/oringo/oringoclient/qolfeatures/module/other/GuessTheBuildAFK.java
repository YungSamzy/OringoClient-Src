/*     */ package me.oringo.oringoclient.qolfeatures.module.other;
/*     */ import com.mojang.realmsclient.gui.ChatFormatting;
/*     */ import java.io.BufferedReader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*     */ import me.oringo.oringoclient.utils.Notifications;
/*     */ import me.oringo.oringoclient.utils.SkyblockUtils;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.inventory.GuiChest;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.scoreboard.ScoreObjective;
/*     */ import net.minecraftforge.client.event.ClientChatReceivedEvent;
/*     */ import net.minecraftforge.client.event.GuiOpenEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ 
/*     */ public class GuessTheBuildAFK extends Module {
/*     */   private ArrayList<String> wordList;
/*     */   private int tips;
/*     */   
/*     */   public GuessTheBuildAFK() {
/*  24 */     super("Auto GTB", 0, Module.Category.OTHER);
/*     */ 
/*     */     
/*  27 */     this.wordList = new ArrayList<>();
/*     */     
/*  29 */     this.tips = 0;
/*  30 */     this.guesses = null;
/*  31 */     this.period = 3200;
/*  32 */     this.lastGuess = 0L;
/*     */   }
/*     */   private Thread guesses; private int period; private long lastGuess;
/*     */   @SubscribeEvent
/*     */   public void onChat(ClientChatReceivedEvent event) {
/*  37 */     if (!isToggled())
/*     */       return; 
/*     */     try {
/*  40 */       ScoreObjective o = (Minecraft.func_71410_x()).field_71439_g.func_96123_co().func_96539_a(0);
/*  41 */       if (ChatFormatting.stripFormatting(((o == null) ? (Minecraft.func_71410_x()).field_71439_g.func_96123_co().func_96539_a(1) : o).func_96678_d()).contains("GUESS THE BUILD") && 
/*  42 */         ChatFormatting.stripFormatting(event.message.func_150254_d()).startsWith("This game has been recorded")) {
/*  43 */         (Minecraft.func_71410_x()).field_71439_g.func_71165_d("/play build_battle_guess_the_build");
/*     */       }
/*     */     }
/*  46 */     catch (Exception exception) {}
/*  47 */     if (event.message.func_150254_d().startsWith("§eThe theme was") && this.guesses != null) {
/*  48 */       this.guesses.stop();
/*  49 */       this.guesses = null;
/*     */     } 
/*  51 */     if (ChatFormatting.stripFormatting(event.message.func_150254_d()).startsWith((Minecraft.func_71410_x()).field_71439_g.func_70005_c_() + " correctly guessed the theme!") && this.guesses != null) {
/*  52 */       this.guesses.stop();
/*  53 */       this.guesses = null;
/*     */     } 
/*  55 */     if (event.type == 2)
/*  56 */       if (event.message.func_150254_d().contains("The theme is") && event.message.func_150254_d().contains("_"))
/*     */       
/*  58 */       { if (this.wordList.isEmpty()) loadWords();
/*     */         
/*  60 */         int newTips = getTips(event.message.func_150254_d());
/*  61 */         if (newTips != this.tips) {
/*  62 */           this.tips = newTips;
/*     */           
/*  64 */           String tip = ChatFormatting.stripFormatting(event.message.func_150254_d()).replaceFirst("The theme is ", "");
/*  65 */           ArrayList<String> matchingWords = getMatchingWords(tip);
/*     */           
/*  67 */           if (matchingWords.size() == 1) {
/*  68 */             Notifications.showNotification("Oringo Client", "Found 1 matching word! \nSending: §f" + (String)matchingWords.get(0), 2000);
/*  69 */             if (this.guesses != null) {
/*  70 */               this.guesses.stop();
/*  71 */               this.guesses = null;
/*  72 */               (new Thread(() -> {
/*     */                     try {
/*     */                       Thread.sleep(this.period - System.currentTimeMillis() - this.lastGuess);
/*  75 */                     } catch (Exception e) {
/*     */                       e.printStackTrace();
/*     */                     } 
/*     */                     (Minecraft.func_71410_x()).field_71439_g.func_71165_d("/ac " + ((String)matchingWords.get(0)).toLowerCase());
/*  79 */                   })).start();
/*     */               return;
/*     */             } 
/*  82 */             (Minecraft.func_71410_x()).field_71439_g.func_71165_d("/ac " + ((String)matchingWords.get(0)).toLowerCase());
/*     */             return;
/*     */           } 
/*  85 */           Notifications.showNotification("Oringo Client", String.format("Found %s matching words!", new Object[] { Integer.valueOf(matchingWords.size()) }), 1500);
/*  86 */           if (matchingWords.size() <= 15) {
/*  87 */             Collections.shuffle(matchingWords);
/*  88 */             (this.guesses = new Thread(() -> {
/*     */                   for (String matchingWord : matchingWords) {
/*     */                     this.lastGuess = System.currentTimeMillis();
/*     */                     (Minecraft.func_71410_x()).field_71439_g.func_71165_d("/ac " + matchingWord.toLowerCase());
/*     */                     Notifications.showNotification("Oringo Client", "Trying: §f" + matchingWord, 2000);
/*     */                     try {
/*     */                       Thread.sleep(this.period);
/*  95 */                     } catch (Exception e) {
/*     */                       e.printStackTrace();
/*     */                     } 
/*     */                   } 
/*  99 */                 })).start();
/*     */           } 
/*     */         }  }
/* 102 */       else { this.tips = 0; }
/*     */        
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onGuiOpen(GuiOpenEvent event) {
/* 108 */     if (!isToggled())
/* 109 */       return;  (new Thread(() -> {
/*     */           try {
/*     */             Thread.sleep(1000L);
/*     */             
/*     */             if (event.gui instanceof GuiChest) {
/*     */               Minecraft mc = Minecraft.func_71410_x();
/*     */               
/*     */               ScoreObjective o = mc.field_71439_g.func_96123_co().func_96539_a(0);
/*     */               
/*     */               if (ChatFormatting.stripFormatting(((o == null) ? mc.field_71439_g.func_96123_co().func_96539_a(1) : o).func_96678_d()).contains("GUESS THE BUILD")) {
/*     */                 mc.field_71442_b.func_78753_a(((GuiChest)event.gui).field_147002_h.field_75152_c, 15, 0, 0, (EntityPlayer)mc.field_71439_g);
/*     */                 Thread.sleep(2000L);
/*     */                 KeyBinding.func_74510_a(mc.field_71474_y.field_74311_E.func_151463_i(), true);
/*     */                 Thread.sleep(2000L);
/*     */                 KeyBinding.func_74510_a(mc.field_71474_y.field_74311_E.func_151463_i(), false);
/*     */                 mc.field_71439_g.field_71071_by.field_70461_c = 1;
/*     */                 mc.field_71439_g.field_70125_A = 40.0F;
/*     */                 Thread.sleep(500L);
/*     */                 SkyblockUtils.rightClick();
/*     */               } 
/*     */             } 
/* 130 */           } catch (Exception e) {
/*     */             e.printStackTrace();
/*     */           } 
/* 133 */         })).start();
/*     */   }
/*     */   
/*     */   private int getTips(String text) {
/* 137 */     return text.replaceAll(" ", "").replaceAll("_", "").length();
/*     */   }
/*     */   
/*     */   private void loadWords() {
/*     */     try {
/* 142 */       BufferedReader br = new BufferedReader(new InputStreamReader(OringoClient.class.getClassLoader().getResourceAsStream("words.txt")));
/*     */       
/*     */       String line;
/* 145 */       while ((line = br.readLine()) != null) {
/* 146 */         this.wordList.add(line);
/*     */       }
/* 148 */       br.close();
/* 149 */     } catch (Exception e) {
/* 150 */       e.printStackTrace();
/* 151 */       OringoClient.sendMessageWithPrefix("Couldn't load word list!");
/*     */     } 
/*     */   }
/*     */   
/*     */   public ArrayList<String> getMatchingWords(String tip) {
/* 156 */     ArrayList<String> list = new ArrayList<>();
/*     */     
/* 158 */     for (String word : this.wordList) {
/* 159 */       if (word.length() == tip.length()) {
/* 160 */         boolean matching = true;
/* 161 */         for (int i = 0; i < word.length(); i++) {
/* 162 */           if (tip.charAt(i) == '_') {
/* 163 */             if (word.charAt(i) == ' ') {
/* 164 */               matching = false;
/*     */             } else {
/*     */               continue;
/*     */             } 
/*     */           }
/* 169 */           if (tip.charAt(i) != word.charAt(i)) matching = false; 
/* 170 */           if (!matching)
/*     */             break;  continue;
/* 172 */         }  if (matching) list.add(word);
/*     */       
/*     */       } 
/*     */     } 
/* 176 */     return list;
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\other\GuessTheBuildAFK.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */