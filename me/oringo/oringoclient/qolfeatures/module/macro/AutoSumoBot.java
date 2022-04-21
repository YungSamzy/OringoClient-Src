/*     */ package me.oringo.oringoclient.qolfeatures.module.macro;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.qolfeatures.AttackQueue;
/*     */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.StringSetting;
/*     */ import me.oringo.oringoclient.utils.Notifications;
/*     */ import me.oringo.oringoclient.utils.RotationUtils;
/*     */ import me.oringo.oringoclient.utils.SkyblockUtils;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AutoSumoBot
/*     */   extends Module
/*     */ {
/*  29 */   public static ArrayList<String> playersChecked = new ArrayList<>();
/*     */   
/*  31 */   public static Thread thread = null;
/*  32 */   public static EntityPlayer target = null;
/*  33 */   private static int ticksBack = -1;
/*     */   
/*  35 */   public StringSetting webhook = new StringSetting("Webhook");
/*     */   
/*  37 */   public BooleanSetting skipNoLoses = new BooleanSetting("Skip no loses", true);
/*     */   
/*     */   public AutoSumoBot() {
/*  40 */     super("Auto Sumo", 0, Module.Category.MACRO);
/*  41 */     addSettings(new Setting[] { (Setting)this.webhook });
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  46 */     if (thread != null) {
/*  47 */       thread.stop();
/*  48 */       thread = null;
/*  49 */       Notifications.showNotification("Oringo Client", "AutoSumo has been disabled!", 1000);
/*     */     } else {
/*  51 */       if (this.webhook.getValue().length() < 5) {
/*  52 */         Notifications.showNotification("Oringo Client", "You need to set a webhook", 2500);
/*  53 */         toggle();
/*     */         return;
/*     */       } 
/*  56 */       (thread = new Thread(() -> {
/*     */             if (!SkyblockUtils.hasLine("Mode Winstreak:") && !SkyblockUtils.hasLine("Players: 2/2")) {
/*     */               (Minecraft.func_71410_x()).field_71439_g.func_71165_d("/play duels_sumo_duel");
/*     */             }
/*     */             
/*     */             int tick = 0;
/*     */             boolean wasInRange = false;
/*     */             boolean lastWait = false;
/*     */             while (true) {
/*     */               try {
/*     */                 Thread.sleep(50L);
/*     */                 tick++;
/*     */                 KeyBinding.func_74510_a(31, false);
/*     */                 if (SkyblockUtils.hasLine("Players: 2/2") || (SkyblockUtils.hasLine("Waiting..") && !SkyblockUtils.hasLine("Starting in 2s") && !SkyblockUtils.hasLine("Starting in 1s"))) {
/*     */                   target = null;
/*     */                   KeyBinding.func_74510_a(17, true);
/*     */                   KeyBinding.func_74510_a(57, true);
/*     */                   (Minecraft.func_71410_x()).field_71439_g.field_70177_z = 90.0F + 30.0F * (float)Math.sin(Math.toRadians(((int)(System.currentTimeMillis() % 1800L) / 5)));
/*     */                   (Minecraft.func_71410_x()).field_71439_g.field_70125_A = 12.3F;
/*     */                   ticksBack = -1;
/*     */                   if ((new Random()).nextInt(10) == 0) {
/*     */                     AttackQueue.attack = true;
/*     */                   }
/*     */                   lastWait = true;
/*     */                 } 
/*     */                 if (SkyblockUtils.hasLine("Starting in 2s") || SkyblockUtils.hasLine("Starting in 1s")) {
/*     */                   KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_74351_w.func_151463_i(), false);
/*     */                   KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_74314_A.func_151463_i(), false);
/*     */                 } 
/*     */                 if (ticksBack > 0) {
/*     */                   ticksBack--;
/*     */                 }
/*     */                 if (SkyblockUtils.hasLine("Mode Winstreak:")) {
/*     */                   long start = System.currentTimeMillis();
/*     */                   if (lastWait) {
/*     */                     lastWait = false;
/*     */                     KeyBinding.func_74510_a(57, false);
/*     */                   } 
/*     */                   if ((Minecraft.func_71410_x()).field_71439_g.field_71075_bZ.field_75100_b) {
/*     */                     continue;
/*     */                   }
/*     */                   KeyBinding.func_74510_a(17, true);
/*     */                   if ((new Random()).nextInt(7) == 1) {
/*     */                     int j = smartStrafeOrRandomV2((Entity)target);
/*     */                     if (j != -1) {
/*     */                       int key = (j == 0) ? 30 : 32;
/*     */                       KeyBinding.func_74510_a(key, true);
/*     */                       (new Thread(())).start();
/*     */                     } 
/*     */                   } 
/*     */                   if ((Minecraft.func_71410_x()).field_71441_e.field_73010_i.stream().filter(()).count() != 2L) {
/*     */                     continue;
/*     */                   }
/*     */                   for (EntityPlayer playerEntity : (Minecraft.func_71410_x()).field_71441_e.field_73010_i) {
/*     */                     if (Math.abs(playerEntity.field_70163_u - (Minecraft.func_71410_x()).field_71439_g.field_70163_u) < 2.0D && playerEntity.func_70032_d((Entity)(Minecraft.func_71410_x()).field_71439_g) < 12.0F && !playerEntity.func_82150_aj() && !playerEntity.equals((Minecraft.func_71410_x()).field_71439_g)) {
/*     */                       target = playerEntity;
/*     */                       break;
/*     */                     } 
/*     */                   } 
/*     */                   if (target == null) {
/*     */                     continue;
/*     */                   }
/*     */                   if (tick % 8 <= 1 && targetingPlayer()) {
/*     */                     KeyBinding.func_74510_a(17, false);
/*     */                   }
/*     */                   if ((Minecraft.func_71410_x()).field_71439_g.func_70011_f(target.field_70165_t, (Minecraft.func_71410_x()).field_71439_g.field_70163_u, target.field_70161_v) < 1.0D) {
/*     */                     KeyBinding.func_74510_a(17, false);
/*     */                   }
/*     */                   if (checkBlocksBelow(target.field_70165_t, target.field_70163_u, target.field_70161_v)) {
/*     */                     if (ticksBack == -1) {
/*     */                       ticksBack = 40;
/*     */                     }
/*     */                     KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_74370_x.func_151463_i(), false);
/*     */                     KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_74366_z.func_151463_i(), false);
/*     */                     if (target.func_70032_d((Entity)(Minecraft.func_71410_x()).field_71439_g) < 5.0F) {
/*     */                       KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_74351_w.func_151463_i(), false);
/*     */                       if (ticksBack != 0) {
/*     */                         KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_74368_y.func_151463_i(), true);
/*     */                       }
/*     */                     } 
/*     */                   } 
/*     */                   if (!(Minecraft.func_71410_x()).field_71441_e.field_73010_i.contains(target)) {
/*     */                     continue;
/*     */                   }
/*     */                   if ((tick++ % 4 != 0 && (new Random()).nextInt(5) != 0) || (!wasInRange && targetingPlayer())) {
/*     */                     AttackQueue.attack = true;
/*     */                   }
/*     */                   wasInRange = targetingPlayer();
/*     */                   if ((Minecraft.func_71410_x()).field_71439_g.func_70032_d((Entity)target) > 0.4D && !checkBlocksBelow(target.field_70165_t, target.field_70163_u, target.field_70161_v)) {
/*     */                     float[] rotation = RotationUtils.getAngles((new Vec3(target.field_70165_t - 0.33D, Math.max(Math.min(target.field_70163_u, (Minecraft.func_71410_x()).field_71439_g.field_70163_u), target.field_70163_u - target.func_70047_e()), target.field_70161_v - 0.33D)).func_178787_e(new Vec3((new Random()).nextDouble() * 0.1D - 0.05D, (new Random()).nextDouble() * 0.1D - 0.05D + target.func_70047_e() - 0.4D, (new Random()).nextDouble() * 0.1D - 0.05D)));
/*     */                     OringoClient.mc.field_71439_g.field_70177_z = rotation[0];
/*     */                     OringoClient.mc.field_71439_g.field_70125_A = rotation[1];
/*     */                   } 
/*     */                   if (start - System.currentTimeMillis() > 3L) {
/*     */                     OringoClient.sendMessageWithPrefix("Lag! " + (start - System.currentTimeMillis()));
/*     */                   }
/*     */                   continue;
/*     */                 } 
/*     */                 if (!lastWait) {
/*     */                   KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_74351_w.func_151463_i(), false);
/*     */                 }
/*     */                 KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_74370_x.func_151463_i(), false);
/*     */                 KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_74366_z.func_151463_i(), false);
/*     */                 int i = 0;
/*     */                 while (i < 100 && !SkyblockUtils.hasLine("Mode Winstreak:") && !SkyblockUtils.hasLine("Players: 2/2")) {
/*     */                   Thread.sleep(50L);
/*     */                   i++;
/*     */                 } 
/*     */                 if (!SkyblockUtils.hasLine("Mode Winstreak:") && !SkyblockUtils.hasLine("Players: 2/2")) {
/*     */                   (Minecraft.func_71410_x()).field_71439_g.func_71165_d("/play duels_sumo_duel");
/*     */                 }
/* 167 */               } catch (Exception e) {
/*     */                 e.printStackTrace();
/*     */               } 
/*     */             } 
/* 171 */           })).start();
/* 172 */       Notifications.showNotification("Oringo Client", "AutoSumo has been enabled!", 1000);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDisable() {
/* 178 */     if (thread != null) {
/* 179 */       thread.stop();
/* 180 */       thread = null;
/* 181 */       Notifications.showNotification("Oringo Client", "AutoSumo has been disabled!", 1000);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean targetingPlayer() {
/* 186 */     return ((Minecraft.func_71410_x()).field_71476_x != null && (Minecraft.func_71410_x()).field_71476_x.field_72313_a.equals(MovingObjectPosition.MovingObjectType.ENTITY));
/*     */   }
/*     */   
/*     */   public static int smartStrafeOrRandomV2(Entity target) {
/* 190 */     Minecraft mc = Minecraft.func_71410_x();
/*     */     
/* 192 */     if (target != null) {
/* 193 */       for (int i = 0; i < 100; i++) {
/*     */         
/* 195 */         int angle = 60;
/* 196 */         if (checkBlocksBelow(mc.field_71439_g.field_70165_t - Math.sin(Math.toRadians((target.field_70177_z - angle)) * 0.13D * i), mc.field_71439_g.field_70163_u, mc.field_71439_g.field_70161_v + Math.cos(Math.toRadians((target.field_70177_z - angle))) * 0.13D * i)) {
/* 197 */           if (checkBlocksBelow(mc.field_71439_g.field_70165_t - Math.sin(Math.toRadians((target.field_70177_z + angle)) * 0.13D * i), mc.field_71439_g.field_70163_u, mc.field_71439_g.field_70161_v + Math.cos(Math.toRadians((target.field_70177_z + angle))) * 0.13D * i)) return -1; 
/* 198 */           OringoClient.sendMessageWithPrefix("Smart: A");
/* 199 */           return 0;
/*     */         } 
/*     */         
/* 202 */         if (checkBlocksBelow(mc.field_71439_g.field_70165_t - Math.sin(Math.toRadians((target.field_70177_z + angle)) * 0.13D * i), mc.field_71439_g.field_70163_u, mc.field_71439_g.field_70161_v + Math.cos(Math.toRadians((target.field_70177_z + angle))) * 0.13D * i)) {
/* 203 */           OringoClient.sendMessageWithPrefix("Smart: D");
/* 204 */           return 1;
/*     */         } 
/*     */       } 
/*     */       
/* 208 */       if (checkBlocksBelow(target.field_70165_t - Math.sin(Math.toRadians(mc.field_71439_g.field_70177_z) * 3.0D), target.field_70163_u, target.field_70161_v + Math.cos(Math.toRadians(mc.field_71439_g.field_70177_z)) * 3.0D)) {
/* 209 */         OringoClient.sendMessageWithPrefix("Smart: No strafe");
/* 210 */         return -1;
/*     */       } 
/*     */     } 
/*     */     
/* 214 */     return (new Random()).nextInt(2);
/*     */   }
/*     */   
/*     */   private static boolean checkBlocksBelow(double posX, double posY, double posZ) {
/* 218 */     WorldClient theWorld = (Minecraft.func_71410_x()).field_71441_e;
/* 219 */     BlockPos bp = new BlockPos(posX, posY, posZ);
/* 220 */     for (int i = 0; i < 3; i++) {
/* 221 */       bp = bp.func_177977_b();
/* 222 */       if (!(theWorld.func_180495_p(bp).func_177230_c() instanceof net.minecraft.block.BlockAir)) return false; 
/*     */     } 
/* 224 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\macro\AutoSumoBot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */