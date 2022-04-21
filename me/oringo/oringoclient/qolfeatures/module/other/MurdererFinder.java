/*     */ package me.oringo.oringoclient.qolfeatures.module.other;
/*     */ 
/*     */ import com.mojang.realmsclient.gui.ChatFormatting;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*     */ import me.oringo.oringoclient.utils.Notifications;
/*     */ import me.oringo.oringoclient.utils.RenderUtils;
/*     */ import me.oringo.oringoclient.utils.SkyblockUtils;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityArmorStand;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.scoreboard.ScoreObjective;
/*     */ import net.minecraftforge.client.event.RenderWorldLastEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*     */ 
/*     */ public class MurdererFinder
/*     */   extends Module
/*     */ {
/*  29 */   private ArrayList<Item> knives = new ArrayList<>(Arrays.asList(new Item[] { Items.field_151040_l, Items.field_151052_q, Items.field_151037_a, Items.field_151055_y, Items.field_151053_p, Items.field_151041_m, Blocks.field_150330_I
/*     */           
/*  31 */           .func_180665_b(null, null), Items.field_151051_r, Items.field_151047_v, Items.field_151128_bU, Items.field_151158_bO, Items.field_151005_D, Items.field_151034_e, Items.field_151057_cb, Blocks.field_150360_v
/*  32 */           .func_180665_b(null, null), Items.field_151146_bM, Items.field_151103_aS, Items.field_151172_bF, Items.field_151150_bK, Items.field_151106_aX, Items.field_151056_x, Blocks.field_150328_O
/*     */           
/*  34 */           .func_180665_b(null, null), Items.field_179562_cC, Items.field_151083_be, Items.field_151010_B, Items.field_151048_u, Items.field_151012_L, (Item)Items.field_151097_aZ, Items.field_151115_aP, Items.field_151100_aR, Items.field_151124_az, Items.field_151060_bw, Items.field_151072_bj, Items.field_151115_aP }));
/*     */ 
/*     */ 
/*     */   
/*  38 */   public static ArrayList<EntityPlayer> murderers = new ArrayList<>();
/*  39 */   public static ArrayList<EntityPlayer> detectives = new ArrayList<>(); private BooleanSetting autoSay; private BooleanSetting ingotESP;
/*     */   
/*     */   public MurdererFinder() {
/*  42 */     super("Murder Mystery", Module.Category.OTHER);
/*     */ 
/*     */     
/*  45 */     this.autoSay = new BooleanSetting("Say murderer", false); this.ingotESP = new BooleanSetting("Ingot ESP", true); this.bowESP = new BooleanSetting("Bow esp", true);
/*     */     addSettings(new Setting[] { (Setting)this.autoSay, (Setting)this.ingotESP, (Setting)this.bowESP });
/*     */   }
/*     */   private BooleanSetting bowESP; private boolean inMurder;
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onTick(TickEvent.ClientTickEvent event) {
/*  52 */     if (!isToggled() || OringoClient.mc.field_71439_g == null || OringoClient.mc.field_71441_e == null)
/*     */       return;  try {
/*  54 */       if (OringoClient.mc.field_71439_g.func_96123_co() != null) {
/*  55 */         ScoreObjective objective = OringoClient.mc.field_71439_g.func_96123_co().func_96539_a(1);
/*  56 */         if (objective != null && ChatFormatting.stripFormatting(objective.func_96678_d()).equals("MURDER MYSTERY") && SkyblockUtils.hasLine("Innocents Left:")) {
/*  57 */           this.inMurder = true;
/*  58 */           for (EntityPlayer player : OringoClient.mc.field_71441_e.field_73010_i) {
/*     */             
/*  60 */             player.field_70153_n = null;
/*     */             
/*  62 */             if (murderers.contains(player) || detectives.contains(player))
/*     */               continue; 
/*  64 */             if (player.func_70694_bm() != null) {
/*  65 */               if (detectives.size() < 2 && player.func_70694_bm().func_77973_b().equals(Items.field_151031_f)) {
/*  66 */                 detectives.add(player);
/*  67 */                 Notifications.showNotification("Oringo Client", String.format("§b%s is detective!", new Object[] { player.func_70005_c_() }), 2500);
/*     */               } 
/*  69 */               if (this.knives.contains(player.func_70694_bm().func_77973_b())) {
/*  70 */                 murderers.add(player);
/*  71 */                 Notifications.showNotification("Oringo Client", String.format("§c%s is murderer!", new Object[] { player.func_70005_c_() }), 2500);
/*  72 */                 if (this.autoSay.isEnabled() && player != OringoClient.mc.field_71439_g) {
/*  73 */                   OringoClient.mc.field_71439_g.func_71165_d(String.format("%s is murderer!", new Object[] { ChatFormatting.stripFormatting(player.func_70005_c_()) }));
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           return;
/*     */         } 
/*  80 */         this.inMurder = false;
/*  81 */         murderers.clear();
/*  82 */         detectives.clear();
/*     */       } 
/*  84 */     } catch (Exception exception) {}
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onWorldRender(RenderWorldLastEvent e) {
/*  89 */     if (!isToggled())
/*  90 */       return;  if (this.inMurder)
/*  91 */       for (Entity entity : OringoClient.mc.field_71441_e.field_72996_f) {
/*  92 */         if (entity instanceof EntityPlayer) {
/*  93 */           if (((EntityPlayer)entity).func_70608_bn() || entity == OringoClient.mc.field_71439_g)
/*  94 */             continue;  if (murderers.contains(entity)) {
/*  95 */             RenderUtils.draw2D(entity, e.partialTicks, 1.5F, Color.red); continue;
/*  96 */           }  if (detectives.contains(entity)) {
/*  97 */             RenderUtils.draw2D(entity, e.partialTicks, 1.5F, Color.blue); continue;
/*     */           } 
/*  99 */           RenderUtils.draw2D(entity, e.partialTicks, 1.5F, Color.gray); continue;
/*     */         } 
/* 101 */         if (entity instanceof EntityItem && ((EntityItem)entity).func_92059_d().func_77973_b() == Items.field_151043_k && this.ingotESP.isEnabled()) {
/* 102 */           RenderUtils.draw2D(entity, e.partialTicks, 1.0F, Color.yellow); continue;
/* 103 */         }  if (this.bowESP.isEnabled() && entity instanceof EntityArmorStand && ((EntityArmorStand)entity).func_71124_b(0) != null && ((EntityArmorStand)entity).func_71124_b(0).func_77973_b() == Items.field_151031_f)
/* 104 */           RenderUtils.tracerLine(entity, e.partialTicks, 1.5F, Color.CYAN); 
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\other\MurdererFinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */