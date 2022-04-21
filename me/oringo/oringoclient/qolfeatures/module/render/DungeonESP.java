/*     */ package me.oringo.oringoclient.qolfeatures.module.render;
/*     */ 
/*     */ import com.mojang.realmsclient.gui.ChatFormatting;
/*     */ import java.awt.Color;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.events.MotionUpdateEvent;
/*     */ import me.oringo.oringoclient.events.RenderLayersEvent;
/*     */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.ModeSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*     */ import me.oringo.oringoclient.utils.MobRenderUtils;
/*     */ import me.oringo.oringoclient.utils.OutlineUtils;
/*     */ import me.oringo.oringoclient.utils.RenderUtils;
/*     */ import me.oringo.oringoclient.utils.SkyblockUtils;
/*     */ import net.minecraft.client.entity.EntityOtherPlayerMP;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.network.play.server.S45PacketTitle;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraftforge.client.event.RenderLivingEvent;
/*     */ import net.minecraftforge.client.event.RenderWorldLastEvent;
/*     */ import net.minecraftforge.event.world.WorldEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.EventPriority;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DungeonESP
/*     */   extends Module
/*     */ {
/*  37 */   public BooleanSetting bat = new BooleanSetting("Bat ESP", true); public BooleanSetting starred = new BooleanSetting("Starred ESP", true); public BooleanSetting enderman = new BooleanSetting("Show endermen", true); public BooleanSetting miniboss = new BooleanSetting("Miniboss ESP", true); public BooleanSetting bowWarning = new BooleanSetting("Bow warning", false);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   public ModeSetting mode = new ModeSetting("Mode", "2D", new String[] { "Outline", "2D", "Chams", "Box", "Tracers" });
/*  43 */   public NumberSetting opacity = new NumberSetting("Opacity", 255.0D, 0.0D, 255.0D, 1.0D)
/*     */     {
/*     */       public boolean isHidden() {
/*  46 */         return !DungeonESP.this.mode.is("Chams");
/*     */       }
/*     */     };
/*     */   
/*  50 */   private Color starredColor = new Color(245, 81, 66); private Color batColor = new Color(139, 69, 19); private Color SAcolor = new Color(75, 0, 130); private Color LAcolor = new Color(34, 139, 34); private Color AAcolor = new Color(97, 226, 255);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   private HashMap<Entity, Color> starredMobs = new HashMap<>(); private Entity lastRendered;
/*     */   
/*     */   public DungeonESP() {
/*  59 */     super("Dungeon ESP", 0, Module.Category.RENDER);
/*  60 */     addSettings(new Setting[] { (Setting)this.mode, (Setting)this.opacity, (Setting)this.bat, (Setting)this.starred, (Setting)this.enderman, (Setting)this.miniboss });
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onUpdate(MotionUpdateEvent.Pre event) {
/*  66 */     if (OringoClient.mc.field_71439_g.field_70173_aa % 20 == 0 && SkyblockUtils.inDungeon) {
/*  67 */       this.starredMobs.clear();
/*  68 */       for (Entity entity : OringoClient.mc.field_71441_e.field_72996_f.stream().filter(entity -> entity instanceof EntityLivingBase).collect(Collectors.toList())) {
/*  69 */         if (this.starredMobs.containsKey(entity))
/*     */           continue; 
/*  71 */         if (entity instanceof net.minecraft.entity.passive.EntityBat && !entity.func_82150_aj() && this.bat.isEnabled()) {
/*  72 */           this.starredMobs.put(entity, this.batColor);
/*     */           continue;
/*     */         } 
/*  75 */         if (this.starred.isEnabled()) {
/*  76 */           if (entity instanceof net.minecraft.entity.monster.EntityEnderman && entity.func_70005_c_().equals("Dinnerbone")) {
/*  77 */             entity.func_82142_c(false);
/*  78 */             if (this.enderman.isEnabled()) this.starredMobs.put(entity, this.starredColor);  continue;
/*     */           } 
/*  80 */           if (entity instanceof net.minecraft.entity.item.EntityArmorStand && entity.func_70005_c_().contains("✯")) {
/*  81 */             List<Entity> possibleMobs = OringoClient.mc.field_71441_e.func_72839_b(entity, entity.func_174813_aQ().func_72314_b(0.1D, 3.0D, 0.1D));
/*  82 */             if (!possibleMobs.isEmpty() && !SkyblockUtils.isMiniboss(possibleMobs.get(0)) && !this.starredMobs.containsKey(possibleMobs.get(0))) {
/*  83 */               this.starredMobs.put(possibleMobs.get(0), this.starredColor);
/*     */             }
/*     */             continue;
/*     */           } 
/*     */         } 
/*  88 */         if (this.miniboss.isEnabled() && entity instanceof EntityOtherPlayerMP && SkyblockUtils.isMiniboss(entity)) {
/*  89 */           switch (entity.func_70005_c_()) {
/*     */             case "Lost Adventurer":
/*  91 */               this.starredMobs.put(entity, this.LAcolor);
/*     */               break;
/*     */             case "Shadow Assassin":
/*  94 */               entity.func_82142_c(false);
/*  95 */               this.starredMobs.put(entity, this.SAcolor);
/*     */               break;
/*     */             case "Diamond Guy":
/*  98 */               this.starredMobs.put(entity, this.AAcolor);
/*     */               break;
/*     */           } 
/* 101 */           if (this.bowWarning.isEnabled() && ((EntityOtherPlayerMP)entity).func_70694_bm() != null && ((EntityOtherPlayerMP)entity).func_70694_bm().func_77973_b() instanceof net.minecraft.item.ItemBow) {
/* 102 */             drawBowWarning();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRenderWorld(RenderWorldLastEvent event) {
/* 112 */     if (!isToggled() || !SkyblockUtils.inDungeon || (!this.mode.is("2D") && !this.mode.is("Box") && !this.mode.is("Tracers")))
/* 113 */       return;  this.starredMobs.forEach((entity, color) -> {
/*     */           switch (this.mode.getSelected()) {
/*     */             case "2D":
/*     */               RenderUtils.draw2D(entity, event.partialTicks, 1.5F, color);
/*     */               break;
/*     */             case "Box":
/*     */               RenderUtils.entityESPBox(entity, event.partialTicks, color);
/*     */               break;
/*     */             case "Tracers":
/*     */               RenderUtils.tracerLine(entity, event.partialTicks, 1.5F, color);
/*     */               break;
/*     */           } 
/*     */         });
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onEntityRender(RenderLayersEvent event) {
/* 130 */     if (!isToggled() || !SkyblockUtils.inDungeon || !this.mode.is("Outline"))
/* 131 */       return;  if (this.starredMobs.containsKey(event.entity)) {
/* 132 */       OutlineUtils.outlineESP(event, this.starredMobs.get(event.entity));
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.LOWEST)
/*     */   public void onRenderPre(RenderLivingEvent.Pre<EntityLivingBase> event) {
/* 138 */     if (!isToggled() || !SkyblockUtils.inDungeon || !this.mode.is("Chams"))
/* 139 */       return;  if (this.starredMobs.containsKey(event.entity)) {
/* 140 */       MobRenderUtils.setColor(RenderUtils.applyOpacity(this.starredMobs.get(event.entity), (int)this.opacity.getValue()));
/* 141 */       RenderUtils.enableChams();
/* 142 */       this.lastRendered = (Entity)event.entity;
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRenderPost(RenderLivingEvent.Specials.Pre<EntityLivingBase> event) {
/* 148 */     if (this.lastRendered == event.entity) {
/* 149 */       this.lastRendered = null;
/* 150 */       RenderUtils.disableChams();
/* 151 */       MobRenderUtils.unsetColor();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drawBowWarning() {
/* 156 */     S45PacketTitle p1 = new S45PacketTitle(0, 20, 0);
/* 157 */     S45PacketTitle p2 = new S45PacketTitle(S45PacketTitle.Type.SUBTITLE, (IChatComponent)new ChatComponentText(ChatFormatting.DARK_RED + "Bow"));
/* 158 */     OringoClient.mc.field_71439_g.field_71174_a.func_175099_a(p1);
/* 159 */     OringoClient.mc.field_71439_g.field_71174_a.func_175099_a(p2);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onWorldLoad(WorldEvent.Load event) {
/* 164 */     this.starredMobs.clear();
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\render\DungeonESP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */