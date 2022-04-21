/*     */ package me.oringo.oringoclient.utils;
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.mojang.realmsclient.gui.ChatFormatting;
/*     */ import java.awt.Color;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.events.PacketReceivedEvent;
/*     */ import me.oringo.oringoclient.events.PacketSentEvent;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.network.NetworkPlayerInfo;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.C09PacketHeldItemChange;
/*     */ import net.minecraft.network.play.server.S02PacketChat;
/*     */ import net.minecraft.scoreboard.Score;
/*     */ import net.minecraft.scoreboard.ScorePlayerTeam;
/*     */ import net.minecraft.scoreboard.Scoreboard;
/*     */ import net.minecraft.scoreboard.Team;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*     */ 
/*     */ public class SkyblockUtils {
/*  28 */   private static final Minecraft mc = Minecraft.func_71410_x();
/*     */   
/*     */   public static boolean inDungeon;
/*     */   public static boolean isInOtherGame;
/*     */   public static boolean onSkyblock;
/*     */   public static boolean onBedwars;
/*     */   public static boolean onSkywars;
/*     */   public static boolean inBlood;
/*     */   public static boolean inP3;
/*     */   public static int lastReportedSlot;
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onPacketSent(PacketSentEvent event) {
/*  41 */     if (event.packet instanceof C09PacketHeldItemChange) {
/*  42 */       lastReportedSlot = ((C09PacketHeldItemChange)event.packet).func_149614_c();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void updateItem() {
/*  47 */     if (lastReportedSlot != mc.field_71439_g.field_71071_by.field_70461_c)
/*  48 */       mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C09PacketHeldItemChange(mc.field_71439_g.field_71071_by.field_70461_c)); 
/*     */   }
/*     */   public static void updateItemNoEvent() {
/*  51 */     if (lastReportedSlot != mc.field_71439_g.field_71071_by.field_70461_c) {
/*  52 */       PacketUtils.sendPacketNoEvent((Packet<?>)new C09PacketHeldItemChange(mc.field_71439_g.field_71071_by.field_70461_c));
/*  53 */       lastReportedSlot = mc.field_71439_g.field_71071_by.field_70461_c;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onChat(PacketReceivedEvent event) {
/*  60 */     if (event.packet instanceof S02PacketChat) {
/*  61 */       if (ChatFormatting.stripFormatting(((S02PacketChat)event.packet).func_148915_c().func_150254_d()).startsWith("[BOSS] The Watcher: ") && !inBlood) {
/*  62 */         inBlood = true;
/*  63 */         if (OringoClient.bloodAimbot.isToggled())
/*  64 */           Notifications.showNotification("Oringo Client", "Started Camp", 1000); 
/*     */       } 
/*  66 */       if (ChatFormatting.stripFormatting(((S02PacketChat)event.packet).func_148915_c().func_150254_d()).equals("[BOSS] The Watcher: You have proven yourself. You may pass.")) {
/*  67 */         inBlood = false;
/*  68 */         if (OringoClient.bloodAimbot.isToggled())
/*  69 */           Notifications.showNotification("Oringo Client", "Stopped camp", 1000); 
/*     */       } 
/*  71 */       if (ChatFormatting.stripFormatting(((S02PacketChat)event.packet).func_148915_c().func_150254_d()).startsWith("[BOSS] Necron: I hope you're in shape. BETTER GET RUNNING!"))
/*  72 */         inP3 = true; 
/*  73 */       if (ChatFormatting.stripFormatting(((S02PacketChat)event.packet).func_148915_c().func_150254_d()).startsWith("[BOSS] Necron: THAT'S IT YOU HAVE DONE IT! MY ENTIRE FACTORY IS RUINED! ARE YOU HAPPY?!"))
/*  74 */         inP3 = false; 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onWorldLoad(WorldEvent.Load event) {
/*  80 */     inBlood = false;
/*  81 */     inP3 = false;
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onTick(TickEvent.ClientTickEvent event) {
/*  86 */     if (mc.field_71441_e != null)
/*  87 */       inDungeon = (hasLine("Cleared:") || hasLine("Start")); 
/*  88 */     isInOtherGame = isInOtherGame();
/*  89 */     onSkyblock = isOnSkyBlock();
/*  90 */     onBedwars = hasScoreboardTitle("bed wars");
/*  91 */     onSkywars = hasScoreboardTitle("SKYWARS");
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isTeam(EntityLivingBase e, EntityLivingBase e2) {
/*  96 */     if (e.func_145748_c_().func_150260_c().length() < 4) return false; 
/*  97 */     if (e.func_145748_c_().func_150254_d().charAt(2) == '§' && e2.func_145748_c_().func_150254_d().charAt(2) == '§') {
/*  98 */       if (onSkyblock) return true; 
/*  99 */       return (e.func_145748_c_().func_150254_d().charAt(3) == e2.func_145748_c_().func_150254_d().charAt(3));
/*     */     } 
/* 101 */     return false;
/*     */   }
/*     */   
/*     */   public static <T> T firstOrNull(Iterable<T> iterable) {
/* 105 */     return (T)Iterables.getFirst(iterable, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean hasScoreboardTitle(String title) {
/* 115 */     if (mc.field_71439_g == null || mc.field_71439_g.func_96123_co() == null || mc.field_71439_g.func_96123_co().func_96539_a(1) == null) return false; 
/* 116 */     return ChatFormatting.stripFormatting(mc.field_71439_g.func_96123_co().func_96539_a(1).func_96678_d()).equalsIgnoreCase(title);
/*     */   }
/*     */   
/*     */   public static boolean isInOtherGame() {
/*     */     try {
/* 121 */       Scoreboard sb = mc.field_71439_g.func_96123_co();
/* 122 */       List<Score> list = new ArrayList<>(sb.func_96534_i(sb.func_96539_a(1)));
/* 123 */       for (Score score : list) {
/* 124 */         ScorePlayerTeam team = sb.func_96509_i(score.func_96653_e());
/* 125 */         String s = ChatFormatting.stripFormatting(ScorePlayerTeam.func_96667_a((Team)team, score.func_96653_e()));
/* 126 */         if (s.contains("Map")) {
/* 127 */           return true;
/*     */         }
/*     */       } 
/* 130 */     } catch (Exception exception) {}
/* 131 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isOnSkyBlock() {
/*     */     try {
/* 136 */       ScoreObjective titleObjective = mc.field_71439_g.func_96123_co().func_96539_a(1);
/* 137 */       if (mc.field_71439_g.func_96123_co().func_96539_a(0) != null) {
/* 138 */         return ChatFormatting.stripFormatting(mc.field_71439_g.func_96123_co().func_96539_a(0).func_96678_d()).contains("SKYBLOCK");
/*     */       }
/* 140 */       return ChatFormatting.stripFormatting(mc.field_71439_g.func_96123_co().func_96539_a(1).func_96678_d()).contains("SKYBLOCK");
/* 141 */     } catch (Exception e) {
/* 142 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean hasLine(String line) {
/*     */     try {
/* 148 */       Scoreboard sb = (Minecraft.func_71410_x()).field_71439_g.func_96123_co();
/* 149 */       List<Score> list = new ArrayList<>(sb.func_96534_i(sb.func_96539_a(1)));
/* 150 */       for (Score score : list) {
/* 151 */         String s; ScorePlayerTeam team = sb.func_96509_i(score.func_96653_e());
/*     */         
/*     */         try {
/* 154 */           s = ChatFormatting.stripFormatting(team.func_96668_e() + score.func_96653_e() + team.func_96663_f());
/* 155 */         } catch (Exception e) {
/* 156 */           return false;
/*     */         } 
/* 158 */         StringBuilder builder = new StringBuilder();
/* 159 */         for (char c : s.toCharArray()) {
/* 160 */           if (c < 'Ā') builder.append(c); 
/*     */         } 
/* 162 */         if (builder.toString().toLowerCase().contains(line.toLowerCase())) {
/* 163 */           return true;
/*     */         }
/*     */         try {
/* 166 */           s = ChatFormatting.stripFormatting(team.func_96668_e() + team.func_96663_f());
/* 167 */         } catch (Exception e) {
/* 168 */           return false;
/*     */         } 
/* 170 */         builder = new StringBuilder();
/* 171 */         for (char c : s.toCharArray()) {
/* 172 */           if (c < 'Ā') builder.append(c); 
/*     */         } 
/* 174 */         if (builder.toString().toLowerCase().contains(line.toLowerCase())) {
/* 175 */           return true;
/*     */         }
/*     */       } 
/* 178 */     } catch (Exception e) {
/* 179 */       return false;
/*     */     } 
/* 181 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isMiniboss(Entity entity) {
/* 185 */     return (entity.func_70005_c_().equals("Shadow Assassin") || entity.func_70005_c_().equals("Lost Adventurer") || entity.func_70005_c_().equals("Diamond Guy"));
/*     */   }
/*     */   
/*     */   public static void click() {
/*     */     try {
/*     */       Method clickMouse;
/*     */       try {
/* 192 */         clickMouse = Minecraft.class.getDeclaredMethod("func_147116_af", new Class[0]);
/* 193 */       } catch (NoSuchMethodException e) {
/* 194 */         clickMouse = Minecraft.class.getDeclaredMethod("clickMouse", new Class[0]);
/*     */       } 
/* 196 */       clickMouse.setAccessible(true);
/* 197 */       clickMouse.invoke(Minecraft.func_71410_x(), new Object[0]);
/* 198 */     } catch (Exception e) {
/* 199 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean anyTab(String s) {
/* 204 */     return Minecraft.func_71410_x().func_147114_u().func_175106_d().stream().anyMatch(player -> (player.func_178854_k() != null && ChatFormatting.stripFormatting(player.func_178854_k().func_150254_d()).toLowerCase().contains(s.toLowerCase(Locale.ROOT))));
/*     */   }
/*     */   
/*     */   public static boolean isNPC(Entity entity) {
/* 208 */     if (!(entity instanceof net.minecraft.client.entity.EntityOtherPlayerMP)) {
/* 209 */       return false;
/*     */     }
/*     */     
/* 212 */     EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
/*     */     
/* 214 */     return (entity.func_110124_au().version() == 2 && entityLivingBase.func_110143_aJ() == 20.0F);
/*     */   }
/*     */   
/*     */   public static void rightClick() {
/*     */     try {
/* 219 */       Method rightClickMouse = null;
/*     */       try {
/* 221 */         rightClickMouse = Minecraft.class.getDeclaredMethod("func_147121_ag", new Class[0]);
/* 222 */       } catch (NoSuchMethodException e) {
/* 223 */         rightClickMouse = Minecraft.class.getDeclaredMethod("rightClickMouse", new Class[0]);
/*     */       } 
/* 225 */       rightClickMouse.setAccessible(true);
/* 226 */       rightClickMouse.invoke(Minecraft.func_71410_x(), new Object[0]);
/* 227 */     } catch (Exception e) {
/* 228 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String getDisplayName(ItemStack item) {
/* 233 */     if (item == null) {
/* 234 */       return "null";
/*     */     }
/* 236 */     return item.func_82833_r();
/*     */   }
/*     */   
/*     */   public static Color rainbow(int delay) {
/* 240 */     double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0D);
/* 241 */     rainbowState %= 360.0D;
/* 242 */     return Color.getHSBColor((float)(rainbowState / 360.0D), 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public static int getPing() {
/* 246 */     NetworkPlayerInfo networkPlayerInfo = mc.func_147114_u().func_175102_a((Minecraft.func_71410_x()).field_71439_g.func_110124_au());
/* 247 */     return (networkPlayerInfo == null) ? 0 : networkPlayerInfo.func_178853_c();
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclien\\utils\SkyblockUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */