/*     */ package me.oringo.oringoclient.qolfeatures.module.skyblock;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.events.MotionUpdateEvent;
/*     */ import me.oringo.oringoclient.events.PacketReceivedEvent;
/*     */ import me.oringo.oringoclient.events.PacketSentEvent;
/*     */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*     */ import me.oringo.oringoclient.utils.SkyblockUtils;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityArmorStand;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.ContainerChest;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.C16PacketClientStatus;
/*     */ import net.minecraftforge.event.world.WorldEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ 
/*     */ public class TerminalAura
/*     */   extends Module
/*     */ {
/*  30 */   public static ArrayList<Entity> finishedTerms = new ArrayList<>();
/*     */   public static EntityArmorStand currentTerminal;
/*  32 */   public static long termTime = -1L;
/*  33 */   public static long ping = 300L;
/*  34 */   public static long pingAt = -1L;
/*     */   
/*     */   public static boolean pinged;
/*  37 */   public BooleanSetting onGroud = new BooleanSetting("Only ground", true);
/*  38 */   public NumberSetting reach = new NumberSetting("Terminal Reach", 6.0D, 2.0D, 6.0D, 0.1D);
/*  39 */   public NumberSetting crystalReach = new NumberSetting("Crystal Reach", 64.0D, 3.0D, 64.0D, 0.1D);
/*     */   
/*     */   public TerminalAura() {
/*  42 */     super("Terminal Aura", 0, Module.Category.SKYBLOCK);
/*  43 */     addSettings(new Setting[] { (Setting)this.reach, (Setting)this.crystalReach, (Setting)this.onGroud });
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onTick(MotionUpdateEvent.Post event) {
/*  48 */     if (OringoClient.mc.field_71439_g == null || !isToggled() || !SkyblockUtils.inDungeon)
/*  49 */       return;  if (currentTerminal != null && !isInTerminal() && System.currentTimeMillis() - termTime > ping * 2L) {
/*  50 */       finishedTerms.add(currentTerminal);
/*  51 */       currentTerminal = null;
/*     */     } 
/*     */     
/*  54 */     if (OringoClient.mc.field_71439_g.field_70173_aa % 20 == 0 && !pinged) {
/*  55 */       OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C16PacketClientStatus(C16PacketClientStatus.EnumState.REQUEST_STATS));
/*  56 */       pinged = true;
/*  57 */       pingAt = System.currentTimeMillis();
/*     */     } 
/*     */     
/*  60 */     if (currentTerminal == null && (OringoClient.mc.field_71439_g.field_70122_E || !this.onGroud.isEnabled()) && !isInTerminal() && !OringoClient.mc.field_71439_g.func_180799_ab()) {
/*  61 */       Iterator<Entity> iterator = getValidTerminals().iterator(); if (iterator.hasNext()) { Entity entity = iterator.next();
/*  62 */         openTerminal((EntityArmorStand)entity); }
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onPacket(PacketReceivedEvent event) {
/*  70 */     if (event.packet instanceof net.minecraft.network.play.server.S2EPacketCloseWindow && isInTerminal() && currentTerminal != null) {
/*  71 */       openTerminal(currentTerminal);
/*     */     }
/*     */     
/*  74 */     if (event.packet instanceof net.minecraft.network.play.server.S37PacketStatistics && pinged) {
/*  75 */       pinged = false;
/*  76 */       ping = System.currentTimeMillis() - pingAt;
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onSent(PacketSentEvent.Post event) {
/*  82 */     if (event.packet instanceof net.minecraft.network.play.client.C0DPacketCloseWindow && isInTerminal() && currentTerminal != null) {
/*  83 */       openTerminal(currentTerminal);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onWorldChange(WorldEvent.Load event) {
/*  89 */     finishedTerms.clear();
/*  90 */     currentTerminal = null;
/*  91 */     pinged = false;
/*  92 */     termTime = System.currentTimeMillis();
/*  93 */     ping = 300L;
/*  94 */     pingAt = -1L;
/*     */   }
/*     */   
/*     */   private List<Entity> getValidTerminals() {
/*  98 */     return (List<Entity>)OringoClient.mc.field_71441_e.func_72910_y().stream().filter(entity -> entity instanceof EntityArmorStand).filter(entity -> entity.func_70005_c_().contains("CLICK HERE")).filter(entity -> (entity.func_70032_d((Entity)OringoClient.mc.field_71439_g) < (OringoClient.mc.field_71441_e.func_72839_b(entity, entity.func_174813_aQ().func_72314_b(1.0D, 2.0D, 1.0D)).stream().anyMatch(()) ? this.crystalReach.getValue() : this.reach.getValue()))).filter(entity -> !finishedTerms.contains(entity)).sorted(Comparator.comparingDouble(OringoClient.mc.field_71439_g::func_70032_d)).collect(Collectors.toList());
/*     */   }
/*     */   
/*     */   private void openTerminal(EntityArmorStand entity) {
/* 102 */     OringoClient.mc.field_71442_b.func_78768_b((EntityPlayer)OringoClient.mc.field_71439_g, (Entity)entity);
/* 103 */     currentTerminal = entity;
/* 104 */     termTime = System.currentTimeMillis();
/*     */   }
/*     */   
/*     */   private boolean isInTerminal() {
/* 108 */     Container container = OringoClient.mc.field_71439_g.field_71070_bA;
/* 109 */     String name = "";
/* 110 */     if (container instanceof ContainerChest) {
/* 111 */       name = ((ContainerChest)container).func_85151_d().func_70005_c_();
/*     */     }
/* 113 */     return (container instanceof ContainerChest && (name.contains("Correct all the panes!") || name.contains("Navigate the maze!") || name.contains("Click in order!") || name.contains("What starts with:") || name.contains("Select all the")));
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\skyblock\TerminalAura.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */