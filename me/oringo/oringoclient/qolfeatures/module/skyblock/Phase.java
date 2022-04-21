/*     */ package me.oringo.oringoclient.qolfeatures.module.skyblock;
/*     */ import java.awt.Color;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.events.BlockBoundsEvent;
/*     */ import me.oringo.oringoclient.events.MotionUpdateEvent;
/*     */ import me.oringo.oringoclient.mixins.MinecraftAccessor;
/*     */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.ModeSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*     */ import me.oringo.oringoclient.utils.font.Fonts;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ 
/*     */ public class Phase extends Module {
/*     */   private int ticks;
/*     */   public NumberSetting timer;
/*     */   public ModeSetting activate;
/*     */   
/*     */   public Phase() {
/*  27 */     super("Stair Phase", Module.Category.SKYBLOCK);
/*     */ 
/*     */ 
/*     */     
/*  31 */     this.timer = new NumberSetting("Timer", 1.0D, 0.1D, 1.0D, 0.1D);
/*  32 */     this.activate = new ModeSetting("Activate", "on Key", new String[] { "Auto", "on Key" });
/*  33 */     this.clip = new BooleanSetting("Autoclip", true);
/*     */     addSettings(new Setting[] { (Setting)this.timer, (Setting)this.clip, (Setting)this.activate });
/*     */   }
/*     */   public BooleanSetting clip; public boolean isPhasing; public boolean wasPressed; public boolean canPhase;
/*     */   public void onDisable() {
/*  38 */     this.isPhasing = false;
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onUpdate(MotionUpdateEvent.Pre event) {
/*  43 */     if (OringoClient.mc.field_71439_g == null || OringoClient.mc.field_71441_e == null)
/*  44 */       return;  this.ticks--;
/*  45 */     if (isToggled()) {
/*  46 */       this.canPhase = (OringoClient.mc.field_71439_g.field_70122_E && OringoClient.mc.field_71439_g.field_70124_G && isValidBlock(OringoClient.mc.field_71441_e.func_180495_p(new BlockPos(OringoClient.mc.field_71439_g.field_70165_t, OringoClient.mc.field_71439_g.field_70163_u, OringoClient.mc.field_71439_g.field_70161_v)).func_177230_c()));
/*  47 */       if (!this.isPhasing && (!isKeybind() || (isPressed() && !this.wasPressed)) && OringoClient.mc.field_71439_g.field_70122_E && OringoClient.mc.field_71439_g.field_70124_G && isValidBlock(OringoClient.mc.field_71441_e.func_180495_p(new BlockPos(OringoClient.mc.field_71439_g.field_70165_t, OringoClient.mc.field_71439_g.field_70163_u, OringoClient.mc.field_71439_g.field_70161_v)).func_177230_c())) {
/*  48 */         this.isPhasing = true;
/*  49 */         this.ticks = 8;
/*  50 */       } else if (this.isPhasing && ((!isInsideBlock() && this.ticks < 0) || (isPressed() && !this.wasPressed && isKeybind()))) {
/*  51 */         OringoClient.mc.field_71439_g.func_70016_h(0.0D, 0.0D, 0.0D);
/*  52 */         this.isPhasing = false;
/*     */       } 
/*  54 */       if (this.isPhasing && isInsideBlock())
/*  55 */       { (((MinecraftAccessor)OringoClient.mc).getTimer()).field_74278_d = (float)this.timer.getValue(); }
/*  56 */       else { (((MinecraftAccessor)OringoClient.mc).getTimer()).field_74278_d = 1.0F; }
/*     */     
/*  58 */     }  this.wasPressed = isPressed();
/*  59 */     if (!isToggled() || !this.isPhasing) (((MinecraftAccessor)OringoClient.mc).getTimer()).field_74278_d = 1.0F; 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onBlockBounds(BlockBoundsEvent event) {
/*  64 */     if (!this.isPhasing || OringoClient.mc.field_71439_g == null || !isToggled())
/*  65 */       return;  if (event.collidingEntity == OringoClient.mc.field_71439_g && ((event.aabb != null && event.aabb.field_72337_e > (OringoClient.mc.field_71439_g.func_174813_aQ()).field_72338_b) || OringoClient.mc.field_71474_y.field_74311_E.func_151470_d() || (this.ticks == 7 && this.clip.isEnabled()))) {
/*  66 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRender(RenderGameOverlayEvent.Post event) {
/*  72 */     if (OringoClient.mc.field_71441_e == null || OringoClient.mc.field_71439_g == null || !isToggled())
/*  73 */       return;  if (this.canPhase && this.activate.is("on Key") && event.type == RenderGameOverlayEvent.ElementType.HOTBAR) {
/*  74 */       ScaledResolution resolution = new ScaledResolution(OringoClient.mc);
/*  75 */       Fonts.fontMediumBold.drawSmoothCenteredString("Phase usage detected", resolution.func_78326_a() / 2.0F + 0.4F, resolution.func_78328_b() - resolution.func_78328_b() / 4.5F + 0.5F, (new Color(20, 20, 20)).getRGB());
/*  76 */       Fonts.fontMediumBold.drawSmoothCenteredString("Phase usage detected", resolution.func_78326_a() / 2.0F, resolution.func_78328_b() - resolution.func_78328_b() / 4.5F, OringoClient.clickGui.getColor().getRGB());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isInsideBlock() {
/*  82 */     for (int x = MathHelper.func_76128_c((OringoClient.mc.field_71439_g.func_174813_aQ()).field_72340_a); x < MathHelper.func_76128_c((OringoClient.mc.field_71439_g.func_174813_aQ()).field_72336_d) + 1; x++) {
/*  83 */       for (int y = MathHelper.func_76128_c((OringoClient.mc.field_71439_g.func_174813_aQ()).field_72338_b); y < MathHelper.func_76128_c((OringoClient.mc.field_71439_g.func_174813_aQ()).field_72337_e) + 1; y++) {
/*  84 */         for (int z = MathHelper.func_76128_c((OringoClient.mc.field_71439_g.func_174813_aQ()).field_72339_c); z < MathHelper.func_76128_c((OringoClient.mc.field_71439_g.func_174813_aQ()).field_72334_f) + 1; z++) {
/*  85 */           Block block = OringoClient.mc.field_71441_e.func_180495_p(new BlockPos(x, y, z)).func_177230_c();
/*  86 */           if (block != null && !(block instanceof net.minecraft.block.BlockAir)) {
/*  87 */             AxisAlignedBB boundingBox = block.func_180640_a((World)OringoClient.mc.field_71441_e, new BlockPos(x, y, z), OringoClient.mc.field_71441_e.func_180495_p(new BlockPos(x, y, z)));
/*  88 */             if (boundingBox != null && 
/*  89 */               OringoClient.mc.field_71439_g.func_174813_aQ().func_72326_a(boundingBox)) {
/*  90 */               return true;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  97 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isKeybind() {
/* 103 */     return this.activate.is("on Key");
/*     */   }
/*     */   
/*     */   private static boolean isValidBlock(Block block) {
/* 107 */     return (block instanceof net.minecraft.block.BlockStairs || block instanceof net.minecraft.block.BlockFence || block instanceof net.minecraft.block.BlockFenceGate || block instanceof net.minecraft.block.BlockWall || block == Blocks.field_150438_bZ || block instanceof net.minecraft.block.BlockSkull);
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\skyblock\Phase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */