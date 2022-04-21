/*     */ package me.oringo.oringoclient.qolfeatures.module.combat;
/*     */ 
/*     */ import com.mojang.realmsclient.gui.ChatFormatting;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.events.MotionUpdateEvent;
/*     */ import me.oringo.oringoclient.mixins.entity.PlayerSPAccessor;
/*     */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.ModeSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.skyblock.Aimbot;
/*     */ import me.oringo.oringoclient.utils.Notifications;
/*     */ import me.oringo.oringoclient.utils.RotationUtils;
/*     */ import me.oringo.oringoclient.utils.SkyblockUtils;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.event.world.WorldEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.EventPriority;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*     */ import org.lwjgl.input.Mouse;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KillAura
/*     */   extends Module
/*     */ {
/*     */   public static EntityLivingBase target;
/*  43 */   public BooleanSetting namesOnly = new BooleanSetting("Names only", false); public BooleanSetting middleClick = new BooleanSetting("Middle click to add", false); public BooleanSetting players = new BooleanSetting("Players", false); public BooleanSetting mobs = new BooleanSetting("Mobs", true); public BooleanSetting walls = new BooleanSetting("Through walls", true); public BooleanSetting teams = new BooleanSetting("Teams", true); public BooleanSetting toggleOnLoad = new BooleanSetting("Disable on join", true); public BooleanSetting toggleInGui = new BooleanSetting("No containers", true); public BooleanSetting onlySword = new BooleanSetting("Only swords", false); public BooleanSetting antiNPC = new BooleanSetting("Anti npc", true); public BooleanSetting movementFix = new BooleanSetting("Movement fix", false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   public NumberSetting range = new NumberSetting("Range", 4.2D, 2.0D, 6.0D, 0.01D); public NumberSetting rotationRange = new NumberSetting("Rotation Range", 6.0D, 2.0D, 12.0D, 0.01D); public NumberSetting fov = new NumberSetting("Fov", 180.0D, 30.0D, 180.0D, 0.1D); public NumberSetting maxRotationPitch = new NumberSetting("Max pitch", 60.0D, 10.0D, 180.0D, 0.1D); public NumberSetting maxRotationYaw = new NumberSetting("Max Yaw", 80.0D, 10.0D, 180.0D, 0.1D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   public ModeSetting mode = new ModeSetting("Sorting", "Distance", new String[] { "Distance", "Health", "Hurt", "Hp reverse" }); public ModeSetting blockMode = new ModeSetting("Autoblock", "None", new String[] { "Default", "Fake", "None" }); public ModeSetting namesonlyMode = new ModeSetting("Names mode", "Enemies", new String[] { "Friends", "Enemies" });
/*     */ 
/*     */ 
/*     */   
/*  66 */   public static List<String> names = new ArrayList<>();
/*     */   
/*     */   private boolean wasDown;
/*     */   
/*     */   public KillAura() {
/*  71 */     super("Kill Aura", 0, Module.Category.COMBAT);
/*  72 */     addSettings(new Setting[] { (Setting)this.range, (Setting)this.rotationRange, (Setting)this.mode, (Setting)this.maxRotationYaw, (Setting)this.maxRotationPitch, (Setting)this.fov, (Setting)this.blockMode, (Setting)this.players, (Setting)this.mobs, (Setting)this.teams, (Setting)this.movementFix, (Setting)this.namesOnly, (Setting)this.namesonlyMode, (Setting)this.middleClick, (Setting)this.walls, (Setting)this.toggleInGui, (Setting)this.toggleOnLoad, (Setting)this.onlySword, (Setting)this.antiNPC });
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onTick(TickEvent.ClientTickEvent event) {
/*  77 */     if (OringoClient.mc.field_71439_g == null || OringoClient.mc.field_71441_e == null || !this.middleClick.isEnabled())
/*  78 */       return;  if (Mouse.isButtonDown(2) && OringoClient.mc.field_71462_r == null) {
/*  79 */       if (OringoClient.mc.field_147125_j != null && !this.wasDown && !(OringoClient.mc.field_147125_j instanceof net.minecraft.entity.item.EntityArmorStand) && OringoClient.mc.field_147125_j instanceof EntityLivingBase) {
/*  80 */         String name = ChatFormatting.stripFormatting(OringoClient.mc.field_147125_j.func_145748_c_().func_150254_d());
/*  81 */         if (!names.contains(name)) {
/*  82 */           names.add(name);
/*  83 */           save();
/*  84 */           Notifications.showNotification("Oringo Client", "Added " + ChatFormatting.AQUA + name + ChatFormatting.RESET + " to name sorting", 2000);
/*     */         } else {
/*  86 */           names.remove(name);
/*  87 */           save();
/*  88 */           Notifications.showNotification("Oringo Client", "Removed " + ChatFormatting.AQUA + name + ChatFormatting.RESET + " from name sorting", 2000);
/*     */         } 
/*     */       } 
/*  91 */       this.wasDown = true;
/*     */     } else {
/*     */       
/*  94 */       this.wasDown = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDisable() {
/* 100 */     target = null;
/*     */   }
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.NORMAL)
/*     */   public void onMovePre(MotionUpdateEvent.Pre event) {
/* 105 */     if (!isToggled() || Aimbot.attack || (this.onlySword.isEnabled() && (OringoClient.mc.field_71439_g.func_70694_bm() == null || !(OringoClient.mc.field_71439_g.func_70694_bm().func_77973_b() instanceof net.minecraft.item.ItemSword)))) {
/* 106 */       target = null;
/*     */       
/*     */       return;
/*     */     } 
/* 110 */     target = getTarget();
/* 111 */     if (target != null) {
/* 112 */       float[] angles = RotationUtils.getServerAngles((Entity)target);
/* 113 */       event.yaw = ((PlayerSPAccessor)OringoClient.mc.field_71439_g).getLastReportedYaw() - MathHelper.func_76142_g((float)Math.max(-this.maxRotationYaw.getValue(), Math.min((((PlayerSPAccessor)OringoClient.mc.field_71439_g).getLastReportedYaw() - angles[0]), this.maxRotationYaw.getValue())));
/* 114 */       event.pitch = ((PlayerSPAccessor)OringoClient.mc.field_71439_g).getLastReportedPitch() - MathHelper.func_76142_g((float)Math.max(-this.maxRotationPitch.getValue(), Math.min((((PlayerSPAccessor)OringoClient.mc.field_71439_g).getLastReportedPitch() - angles[1]), this.maxRotationPitch.getValue())));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.HIGHEST)
/*     */   public void onMovePost(MotionUpdateEvent.Post event) {
/* 121 */     if (target != null && OringoClient.mc.field_71439_g.field_70173_aa % 2 == 0) {
/* 122 */       SkyblockUtils.updateItemNoEvent();
/* 123 */       if (OringoClient.mc.field_71439_g.func_70032_d((Entity)target) < this.range.getValue()) {
/* 124 */         if (OringoClient.mc.field_71439_g.func_71039_bw()) {
/* 125 */           OringoClient.mc.field_71442_b.func_78766_c((EntityPlayer)OringoClient.mc.field_71439_g);
/*     */         }
/* 127 */         OringoClient.mc.field_71439_g.func_71038_i();
/* 128 */         float[] angles = RotationUtils.getServerAngles((Entity)target);
/* 129 */         if (Math.abs(((PlayerSPAccessor)OringoClient.mc.field_71439_g).getLastReportedPitch() - angles[1]) < 25.0F && Math.abs(((PlayerSPAccessor)OringoClient.mc.field_71439_g).getLastReportedYaw() - angles[0]) < 15.0F) {
/* 130 */           OringoClient.mc.field_71442_b.func_78764_a((EntityPlayer)OringoClient.mc.field_71439_g, (Entity)target);
/*     */         }
/*     */         
/* 133 */         if (!OringoClient.mc.field_71439_g.func_71039_bw() && OringoClient.mc.field_71439_g.func_70694_bm() != null && OringoClient.mc.field_71439_g.func_70694_bm().func_77973_b() instanceof net.minecraft.item.ItemSword && this.blockMode.getSelected().equals("Default")) {
/* 134 */           OringoClient.mc.field_71442_b.func_78769_a((EntityPlayer)OringoClient.mc.field_71439_g, (World)OringoClient.mc.field_71441_e, OringoClient.mc.field_71439_g.func_70694_bm());
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private EntityLivingBase getTarget() {
/* 142 */     if ((OringoClient.mc.field_71462_r instanceof net.minecraft.client.gui.inventory.GuiContainer && this.toggleInGui.isEnabled()) || OringoClient.mc.field_71441_e == null) return null;
/*     */     
/* 144 */     List<Entity> validTargets = (List<Entity>)OringoClient.mc.field_71441_e.func_72910_y().stream().filter(entity -> entity instanceof EntityLivingBase).filter(entity -> isValid((EntityLivingBase)entity)).sorted(Comparator.comparingDouble(e -> e.func_70032_d((Entity)OringoClient.mc.field_71439_g))).collect(Collectors.toList());
/* 145 */     switch (this.mode.getSelected()) {
/*     */       case "Health":
/* 147 */         validTargets.sort(Comparator.comparingDouble(e -> ((EntityLivingBase)e).func_110143_aJ()));
/*     */         break;
/*     */       case "Hurt":
/* 150 */         validTargets.sort(Comparator.comparing(e -> Integer.valueOf(((EntityLivingBase)e).field_70737_aN)));
/*     */         break;
/*     */       case "Hp reverse":
/* 153 */         validTargets.sort(Comparator.<Entity>comparingDouble(e -> ((EntityLivingBase)e).func_110143_aJ()).reversed());
/*     */         break;
/*     */     } 
/* 156 */     Iterator<Entity> iterator = validTargets.iterator(); if (iterator.hasNext()) { Entity entity = iterator.next();
/* 157 */       return (EntityLivingBase)entity; }
/*     */     
/* 159 */     return null;
/*     */   }
/*     */   
/*     */   private boolean isValid(EntityLivingBase entity) {
/* 163 */     if (entity == OringoClient.mc.field_71439_g || ((entity instanceof EntityPlayer || entity instanceof net.minecraft.entity.boss.EntityWither || entity instanceof net.minecraft.entity.passive.EntityBat) && entity.func_82150_aj()) || entity instanceof net.minecraft.entity.item.EntityArmorStand || (!OringoClient.mc.field_71439_g.func_70685_l((Entity)entity) && !this.walls.isEnabled()) || entity.func_110143_aJ() <= 0.0F || entity.func_70032_d((Entity)OringoClient.mc.field_71439_g) > ((target != null && target != entity) ? this.range.getValue() : Math.max(this.rotationRange.getValue(), this.range.getValue())) || !RotationUtils.isWithinFOV(entity, this.fov.getValue() + 5.0D) || !RotationUtils.isWithinPitch(entity, this.fov.getValue() + 5.0D)) {
/* 164 */       return false;
/*     */     }
/* 166 */     if (this.namesOnly.isEnabled()) {
/* 167 */       boolean flag = names.contains(ChatFormatting.stripFormatting(entity.func_145748_c_().func_150254_d()));
/* 168 */       if (this.namesonlyMode.is("Enemies") || flag) {
/* 169 */         return (this.namesonlyMode.is("Enemies") && flag);
/*     */       }
/*     */     } 
/* 172 */     if ((entity instanceof net.minecraft.entity.monster.EntityMob || entity instanceof net.minecraft.entity.passive.EntityAmbientCreature || entity instanceof net.minecraft.entity.passive.EntityWaterMob || entity instanceof net.minecraft.entity.passive.EntityAnimal || entity instanceof net.minecraft.entity.monster.EntitySlime) && !this.mobs.isEnabled()) {
/* 173 */       return false;
/*     */     }
/* 175 */     if (entity instanceof EntityPlayer && ((SkyblockUtils.isTeam(entity, (EntityLivingBase)OringoClient.mc.field_71439_g) && this.teams.isEnabled()) || (SkyblockUtils.isNPC((Entity)entity) && this.antiNPC.isEnabled()) || !this.players.isEnabled())) {
/* 176 */       return false;
/*     */     }
/* 178 */     return !(entity instanceof net.minecraft.entity.passive.EntityVillager);
/*     */   }
/*     */   
/*     */   private static void save() {
/*     */     try {
/* 183 */       DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("config/OringoClient/KillAura.cfg"));
/* 184 */       dataOutputStream.writeInt(names.size());
/* 185 */       for (String name : names) {
/* 186 */         dataOutputStream.writeUTF(name);
/*     */       }
/* 188 */       dataOutputStream.close();
/* 189 */     } catch (Exception e) {
/* 190 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onWorldLoad(WorldEvent.Load event) {
/* 196 */     if (isToggled() && this.toggleOnLoad.isEnabled())
/* 197 */       toggle(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\combat\KillAura.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */