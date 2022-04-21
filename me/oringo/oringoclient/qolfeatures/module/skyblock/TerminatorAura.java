/*     */ package me.oringo.oringoclient.qolfeatures.module.skyblock;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.events.MotionUpdateEvent;
/*     */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*     */ import me.oringo.oringoclient.qolfeatures.module.combat.KillAura;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.ModeSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.StringSetting;
/*     */ import me.oringo.oringoclient.utils.RotationUtils;
/*     */ import me.oringo.oringoclient.utils.SkyblockUtils;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
/*     */ import net.minecraftforge.fml.common.eventhandler.EventPriority;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TerminatorAura
/*     */   extends Module
/*     */ {
/*  32 */   public NumberSetting range = new NumberSetting("Range", 15.0D, 5.0D, 30.0D, 1.0D); public NumberSetting delay = new NumberSetting("Use delay", 3.0D, 1.0D, 10.0D, 1.0D);
/*     */   
/*  34 */   public ModeSetting mode = new ModeSetting("Mode", "Swap", new String[] { "Swap", "Held" }); public ModeSetting button = new ModeSetting("Mouse", "Right", new String[] { "Left", "Right" });
/*     */   
/*  36 */   public BooleanSetting bossLock = new BooleanSetting("Boss Lock", true); public BooleanSetting inDungeon = new BooleanSetting("only Dungeon", true); public BooleanSetting teamCheck = new BooleanSetting("Teamcheck", false);
/*     */ 
/*     */   
/*  39 */   public StringSetting customItem = new StringSetting("Custom Item"); public static EntityLivingBase target;
/*     */   
/*     */   public TerminatorAura() {
/*  42 */     super("Terminator Aura", 0, Module.Category.SKYBLOCK);
/*  43 */     addSettings(new Setting[] { (Setting)this.delay, (Setting)this.range, (Setting)this.button, (Setting)this.mode, (Setting)this.customItem, (Setting)this.bossLock, (Setting)this.inDungeon, (Setting)this.teamCheck });
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean attack;
/*  48 */   private static ArrayList<EntityLivingBase> attackedMobs = new ArrayList<>();
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.LOWEST)
/*     */   public void onUpdate(MotionUpdateEvent.Pre event) {
/*  52 */     if (KillAura.target != null || Aimbot.attack || !isToggled() || OringoClient.mc.field_71439_g.field_70173_aa % this.delay.getValue() != 0.0D || (!SkyblockUtils.inDungeon && this.inDungeon.isEnabled()))
/*  53 */       return;  boolean hasTerm = (OringoClient.mc.field_71439_g.func_70694_bm() != null && (OringoClient.mc.field_71439_g.func_70694_bm().func_82833_r().contains("Juju") || OringoClient.mc.field_71439_g.func_70694_bm().func_82833_r().contains("Terminator") || (!this.customItem.getValue().equals("") && OringoClient.mc.field_71439_g.func_70694_bm().func_82833_r().contains(this.customItem.getValue()))));
/*  54 */     if (this.mode.getSelected().equals("Swap")) {
/*  55 */       for (int i = 0; i < 9; i++) {
/*  56 */         if (OringoClient.mc.field_71439_g.field_71071_by.func_70301_a(i) != null && (OringoClient.mc.field_71439_g.field_71071_by.func_70301_a(i).func_82833_r().contains("Juju") || OringoClient.mc.field_71439_g.field_71071_by.func_70301_a(i).func_82833_r().contains("Terminator") || (!this.customItem.is("") && OringoClient.mc.field_71439_g.field_71071_by.func_70301_a(i).func_82833_r().contains(this.customItem.getValue())))) {
/*  57 */           hasTerm = true;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/*  62 */     if (!hasTerm)
/*  63 */       return;  target = getTarget(target);
/*  64 */     if (target != null) {
/*  65 */       attack = true;
/*  66 */       float[] angles = RotationUtils.getBowAngles((Entity)target);
/*  67 */       event.yaw = angles[0];
/*  68 */       event.pitch = angles[1];
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onUpdatePost(MotionUpdateEvent.Post event) {
/*  74 */     if (!attack)
/*  75 */       return;  int held = OringoClient.mc.field_71439_g.field_71071_by.field_70461_c;
/*  76 */     if (this.mode.getSelected().equals("Swap")) {
/*  77 */       for (int i = 0; i < 9; i++) {
/*  78 */         if (OringoClient.mc.field_71439_g.field_71071_by.func_70301_a(i) != null && (OringoClient.mc.field_71439_g.field_71071_by.func_70301_a(i).func_82833_r().contains("Juju") || OringoClient.mc.field_71439_g.field_71071_by.func_70301_a(i).func_82833_r().contains("Terminator") || (!this.customItem.is("") && OringoClient.mc.field_71439_g.field_71071_by.func_70301_a(i).func_82833_r().contains(this.customItem.getValue())))) {
/*  79 */           OringoClient.mc.field_71439_g.field_71071_by.field_70461_c = i;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/*  84 */     SkyblockUtils.updateItemNoEvent();
/*  85 */     click();
/*  86 */     OringoClient.mc.field_71439_g.field_71071_by.field_70461_c = held;
/*  87 */     SkyblockUtils.updateItemNoEvent();
/*  88 */     attack = false;
/*     */   }
/*     */   
/*     */   private EntityLivingBase getTarget(EntityLivingBase lastTarget) {
/*  92 */     if (this.bossLock.isEnabled() && lastTarget != null && SkyblockUtils.isMiniboss((Entity)lastTarget) && lastTarget.func_110143_aJ() > 0.0F && !lastTarget.field_70128_L && lastTarget.func_70685_l((Entity)OringoClient.mc.field_71439_g) && lastTarget.func_70032_d((Entity)OringoClient.mc.field_71439_g) < this.range.getValue()) {
/*  93 */       return lastTarget;
/*     */     }
/*     */ 
/*     */     
/*  97 */     List<Entity> validTargets = (List<Entity>)OringoClient.mc.field_71441_e.func_72910_y().stream().filter(entity -> entity instanceof EntityLivingBase).filter(entity -> isValid((EntityLivingBase)entity)).sorted(Comparator.comparingDouble(OringoClient.mc.field_71439_g::func_70032_d)).sorted(Comparator.comparing(entity -> Float.valueOf(RotationUtils.getYawDifference((lastTarget != null) ? lastTarget : (EntityLivingBase)entity, (EntityLivingBase)entity))).reversed()).collect(Collectors.toList());
/*  98 */     Iterator<Entity> iterator = validTargets.iterator(); if (iterator.hasNext()) { Entity entity = iterator.next();
/*  99 */       attackedMobs.add((EntityLivingBase)entity);
/* 100 */       (new Thread(() -> {
/*     */             try {
/*     */               Thread.sleep(350L);
/* 103 */             } catch (InterruptedException e) {
/*     */               e.printStackTrace();
/*     */             } 
/*     */             attackedMobs.remove(entity);
/* 107 */           })).start();
/* 108 */       return (EntityLivingBase)entity; }
/*     */     
/* 110 */     return null;
/*     */   }
/*     */   
/*     */   private void click() {
/* 114 */     switch (this.button.getSelected()) {
/*     */       case "Left":
/* 116 */         OringoClient.mc.field_71439_g.func_71038_i();
/*     */         break;
/*     */       case "Right":
/* 119 */         OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C08PacketPlayerBlockPlacement(OringoClient.mc.field_71439_g.func_70694_bm()));
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isValid(EntityLivingBase entity) {
/* 125 */     if (entity == OringoClient.mc.field_71439_g || entity instanceof net.minecraft.entity.item.EntityArmorStand || !OringoClient.mc.field_71439_g.func_70685_l((Entity)entity) || entity.func_110143_aJ() <= 0.0F || entity.func_70032_d((Entity)OringoClient.mc.field_71439_g) > this.range.getValue() || ((entity instanceof net.minecraft.entity.player.EntityPlayer || entity instanceof net.minecraft.entity.passive.EntityBat || entity instanceof net.minecraft.entity.monster.EntityZombie || entity instanceof net.minecraft.entity.monster.EntityGiantZombie) && entity.func_82150_aj()) || entity.func_70005_c_().equals("Dummy") || entity.func_70005_c_().startsWith("Decoy")) {
/* 126 */       return false;
/*     */     }
/* 128 */     return (!attackedMobs.contains(entity) && !(entity instanceof net.minecraft.entity.monster.EntityBlaze) && (!SkyblockUtils.isTeam((EntityLivingBase)OringoClient.mc.field_71439_g, entity) || !this.teamCheck.isEnabled()));
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\skyblock\TerminatorAura.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */