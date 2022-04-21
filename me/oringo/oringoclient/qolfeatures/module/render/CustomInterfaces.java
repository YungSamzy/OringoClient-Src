/*     */ package me.oringo.oringoclient.qolfeatures.module.render;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.realmsclient.gui.ChatFormatting;
/*     */ import java.awt.Color;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.events.ScoreboardRenderEvent;
/*     */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.ModeSetting;
/*     */ import me.oringo.oringoclient.utils.RenderUtils;
/*     */ import me.oringo.oringoclient.utils.font.Fonts;
/*     */ import me.oringo.oringoclient.utils.shader.BlurUtils;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.scoreboard.Score;
/*     */ import net.minecraft.scoreboard.ScoreObjective;
/*     */ import net.minecraft.scoreboard.ScorePlayerTeam;
/*     */ import net.minecraft.scoreboard.Scoreboard;
/*     */ import net.minecraft.scoreboard.Team;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ 
/*     */ public class CustomInterfaces extends Module {
/*     */   public BooleanSetting customScoreboard;
/*     */   public BooleanSetting customFont;
/*     */   
/*     */   public CustomInterfaces() {
/*  36 */     super("Custom Interfaces", Module.Category.RENDER);
/*     */ 
/*     */ 
/*     */     
/*  40 */     this.customScoreboard = new BooleanSetting("Custom Scoreboard", true); this.customFont = new BooleanSetting("Custom Font", true); this.outline = new BooleanSetting("Outline", false); this.hideLobby = new BooleanSetting("Hide lobby", true);
/*     */ 
/*     */ 
/*     */     
/*  44 */     this.blurStrength = new ModeSetting("Blur Strength", "Low", new String[] { "None", "Low", "High" });
/*     */     setToggled(true);
/*     */     addSettings(new Setting[] { (Setting)this.customScoreboard, (Setting)this.customFont, (Setting)this.outline, (Setting)this.hideLobby, (Setting)this.blurStrength }); } public BooleanSetting outline; public BooleanSetting hideLobby; public ModeSetting blurStrength; @SubscribeEvent
/*     */   public void onDraw(ScoreboardRenderEvent event) {
/*  48 */     if (!isToggled() || !this.customScoreboard.isEnabled())
/*  49 */       return;  event.setCanceled(true);
/*  50 */     renderScoreboard(event.objective, event.resolution, this.customFont.isEnabled());
/*     */   }
/*     */ 
/*     */   
/*     */   private void renderScoreboard(ScoreObjective p_180475_1_, ScaledResolution p_180475_2_, boolean customFont) {
/*  55 */     Scoreboard scoreboard = p_180475_1_.func_96682_a();
/*  56 */     Collection<Score> collection = scoreboard.func_96534_i(p_180475_1_);
/*  57 */     List<Score> list = Lists.newArrayList(Iterables.filter(collection, new Predicate<Score>()
/*     */           {
/*     */             public boolean apply(Score p_apply_1_)
/*     */             {
/*  61 */               return (p_apply_1_.func_96653_e() != null && !p_apply_1_.func_96653_e().startsWith("#"));
/*     */             }
/*     */           }));
/*     */     
/*  65 */     if (list.size() > 15) {
/*     */       
/*  67 */       collection = Lists.newArrayList(Iterables.skip(list, collection.size() - 15));
/*     */     }
/*     */     else {
/*     */       
/*  71 */       collection = list;
/*     */     } 
/*     */     
/*  74 */     float width = getStringWidth(p_180475_1_.func_96678_d(), customFont);
/*  75 */     int fontHeight = customFont ? (Fonts.fontMedium.getHeight() + 2) : OringoClient.mc.field_71466_p.field_78288_b;
/*     */     
/*  77 */     for (Score score : collection) {
/*     */       
/*  79 */       ScorePlayerTeam scoreplayerteam = scoreboard.func_96509_i(score.func_96653_e());
/*  80 */       String s = ScorePlayerTeam.func_96667_a((Team)scoreplayerteam, score.func_96653_e()) + ": " + EnumChatFormatting.RED + score.func_96652_c();
/*  81 */       width = Math.max(width, getStringWidth(s, customFont));
/*     */     } 
/*     */     
/*  84 */     float i1 = (collection.size() * fontHeight);
/*  85 */     float arrayHeight = OringoClient.clickGui.getHeight();
/*  86 */     float j1 = p_180475_2_.func_78328_b() / 2.0F + i1 / 3.0F;
/*  87 */     if (OringoClient.clickGui.arrayList.isEnabled()) {
/*  88 */       j1 = Math.max(j1, arrayHeight + 40.0F + (collection.size() * fontHeight - fontHeight - 3));
/*     */     }
/*  90 */     float k1 = 3.0F;
/*  91 */     float l1 = p_180475_2_.func_78326_a() - width - k1;
/*  92 */     float l = p_180475_2_.func_78326_a() - k1 + 2.0F;
/*  93 */     int blur = 0;
/*  94 */     switch (this.blurStrength.getSelected()) {
/*     */       case "Low":
/*  96 */         blur = 7;
/*     */         break;
/*     */       case "High":
/*  99 */         blur = 25;
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 105 */     BlurUtils.renderBlurredBackground(blur, p_180475_2_.func_78326_a(), p_180475_2_.func_78328_b(), l1 - 2.0F, j1 - (collection.size() * fontHeight) - fontHeight - 3.0F, l - l1 - 2.0F, (fontHeight * (collection.size() + 1) + 4));
/* 106 */     if (this.outline.isEnabled())
/* 107 */     { RenderUtils.drawBorderedRoundedRect(l1 - 2.0F, j1 - (collection.size() * fontHeight) - fontHeight - 3.0F, l - l1 - 2.0F, (fontHeight * (collection.size() + 1) + 4), 3.0F, 2.5F, (new Color(21, 21, 21, 50)).getRGB(), OringoClient.clickGui.getColor().getRGB()); }
/* 108 */     else { RenderUtils.drawRoundRect2(l1 - 2.0F, j1 - (collection.size() * fontHeight) - fontHeight - 3.0F, l - l1 - 2.0F, (fontHeight * (collection.size() + 1) + 4), 3.0F, (new Color(21, 21, 21, 50)).getRGB()); }
/* 109 */      int i = 0;
/*     */     
/* 111 */     for (Score score1 : collection) {
/*     */       
/* 113 */       i++;
/* 114 */       ScorePlayerTeam scoreplayerteam1 = scoreboard.func_96509_i(score1.func_96653_e());
/* 115 */       String s1 = ScorePlayerTeam.func_96667_a((Team)scoreplayerteam1, score1.func_96653_e());
/* 116 */       if (s1.contains("§ewww.hypixel.ne🎂§et")) {
/* 117 */         s1 = s1.replaceAll("§ewww.hypixel.ne🎂§et", "Oringo Client");
/*     */       }
/* 119 */       float k = j1 - (i * fontHeight);
/* 120 */       Matcher matcher = Pattern.compile("[0-9][0-9]/[0-9][0-9]/[0-9][0-9]").matcher(s1);
/* 121 */       if (this.hideLobby.isEnabled() && matcher.find()) {
/* 122 */         s1 = ChatFormatting.GRAY + matcher.group();
/*     */       }
/* 124 */       boolean flag = s1.equals("Oringo Client");
/* 125 */       if (flag)
/* 126 */       { if (customFont) {
/* 127 */           Fonts.fontMediumBold.drawSmoothCenteredString(s1, l1 + width / 2.0F + 0.4F, k + 0.5F, (new Color(20, 20, 20)).getRGB());
/* 128 */           Fonts.fontMediumBold.drawSmoothCenteredString(s1, l1 + width / 2.0F, k, OringoClient.clickGui.getColor().brighter().getRGB());
/*     */         } else {
/* 130 */           OringoClient.mc.field_71466_p.func_78276_b(s1, (int)(l1 + width / 2.0F - (OringoClient.mc.field_71466_p.func_78256_a(s1) / 2)), (int)k, OringoClient.clickGui.getColor().brighter().brighter().getRGB());
/*     */         }  }
/* 132 */       else { drawString(s1, l1, k, 553648127, customFont); }
/*     */       
/* 134 */       if (i == collection.size()) {
/*     */         
/* 136 */         String s3 = p_180475_1_.func_96678_d();
/* 137 */         drawString(s3, l1 + width / 2.0F - getStringWidth(s3, customFont) / 2.0F, k - fontHeight, Color.white.getRGB(), customFont);
/*     */       } 
/*     */     } 
/* 140 */     GlStateManager.func_179124_c(1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   private void drawString(String s, float x, float y, int color, boolean customFont) {
/* 144 */     if (OringoClient.nickHider.isToggled() && s.contains(OringoClient.mc.func_110432_I().func_111285_a())) {
/* 145 */       s = s.replaceAll(OringoClient.mc.func_110432_I().func_111285_a(), OringoClient.nickHider.name.getValue());
/*     */     }
/* 147 */     if (customFont) {
/* 148 */       Fonts.fontMediumBold.drawSmoothString(s, (x + 0.4F), (y + 0.5F), (new Color(20, 20, 20)).getRGB(), true);
/* 149 */       Fonts.fontMediumBold.drawSmoothString(s, x, y, Color.white.getRGB());
/*     */     } else {
/* 151 */       OringoClient.mc.field_71466_p.func_78276_b(s, (int)x, (int)y, color);
/*     */     } 
/*     */   }
/*     */   
/*     */   private float getStringWidth(String s, boolean customFont) {
/* 156 */     return customFont ? (float)Fonts.fontMediumBold.getStringWidth(s) : OringoClient.mc.field_71466_p.func_78256_a(s);
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\render\CustomInterfaces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */