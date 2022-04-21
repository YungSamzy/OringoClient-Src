/*     */ package me.oringo.oringoclient.qolfeatures.module.render;
/*     */ 
/*     */ import com.mojang.realmsclient.gui.ChatFormatting;
/*     */ import java.awt.Color;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.events.PacketSentEvent;
/*     */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*     */ import me.oringo.oringoclient.qolfeatures.module.combat.KillAura;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.ModeSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*     */ import me.oringo.oringoclient.utils.MilliTimer;
/*     */ import me.oringo.oringoclient.utils.RenderUtils;
/*     */ import me.oringo.oringoclient.utils.font.Fonts;
/*     */ import me.oringo.oringoclient.utils.shader.BlurUtils;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.gui.inventory.GuiInventory;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.network.play.client.C02PacketUseEntity;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*     */ import net.minecraftforge.client.event.RenderWorldLastEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class TargetHUD extends Module {
/*  34 */   public static TargetHUD instance = new TargetHUD();
/*     */   public static TargetHUD getInstance() {
/*  36 */     return instance;
/*     */   }
/*     */   
/*  39 */   private MilliTimer timer = new MilliTimer();
/*     */   private EntityLivingBase entity;
/*  41 */   protected static final ResourceLocation inventoryBackground = new ResourceLocation("textures/gui/container/inventory.png");
/*  42 */   private static Minecraft mc = Minecraft.func_71410_x();
/*  43 */   private float lastHp = 0.8F;
/*     */   
/*  45 */   public BooleanSetting onlyAura = new BooleanSetting("Only kill aura", false);
/*  46 */   public ModeSetting blurStrength = new ModeSetting("Blur Strength", "Low", new String[] { "None", "Low", "High" });
/*  47 */   public NumberSetting x = new NumberSetting("X", 25.0D, 0.0D, 100.0D, 1.0D); public NumberSetting y = new NumberSetting("Y", 25.0D, 0.0D, 100.0D, 1.0D); public BooleanSetting targetESP;
/*     */   
/*     */   public TargetHUD() {
/*  50 */     super("Target HUD", Module.Category.RENDER);
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
/* 138 */     this.targetESP = new BooleanSetting("Target ESP", true);
/*     */     setToggled(true);
/*     */     addSettings(new Setting[] { (Setting)this.targetESP, (Setting)this.onlyAura, (Setting)this.x, (Setting)this.y, (Setting)this.blurStrength });
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRender(RenderWorldLastEvent event) {
/*     */     if (this.entity != null && this.entity.func_110143_aJ() > 0.0F && this.targetESP.isEnabled() && isToggled())
/*     */       RenderUtils.drawTargetESP(this.entity, OringoClient.clickGui.getColor(), event.partialTicks); 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onPacket(PacketSentEvent e) {
/*     */     if (!this.onlyAura.isEnabled() && e.packet instanceof C02PacketUseEntity && ((C02PacketUseEntity)e.packet).func_149565_c() == C02PacketUseEntity.Action.ATTACK && ((C02PacketUseEntity)e.packet).func_149564_a((World)mc.field_71441_e) != null && ((C02PacketUseEntity)e.packet).func_149564_a((World)mc.field_71441_e) instanceof EntityLivingBase) {
/*     */       this.timer.updateTime();
/*     */       this.entity = (EntityLivingBase)((C02PacketUseEntity)e.packet).func_149564_a((World)mc.field_71441_e);
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRender(RenderGameOverlayEvent.Post event) {
/*     */     if (this.timer.hasTimePassed(1500L))
/*     */       this.entity = null; 
/*     */     if (this.onlyAura.isEnabled() || KillAura.target != null)
/*     */       this.entity = KillAura.target; 
/*     */     if (event.type == RenderGameOverlayEvent.ElementType.ALL && this.entity != null && this.entity.func_110143_aJ() > 0.0F && isToggled() && !(this.entity instanceof net.minecraft.entity.item.EntityArmorStand)) {
/*     */       int scale = mc.field_71474_y.field_74335_Z;
/*     */       GL11.glPushMatrix();
/*     */       int blur = 0;
/*     */       switch (this.blurStrength.getSelected()) {
/*     */         case "Low":
/*     */           blur = 7;
/*     */           break;
/*     */         case "High":
/*     */           blur = 25;
/*     */           break;
/*     */       } 
/*     */       ScaledResolution resolution = new ScaledResolution(mc);
/*     */       int x = (int)(-resolution.func_78326_a() * this.x.getValue() / 100.0D);
/*     */       int y = (int)(-resolution.func_78328_b() * this.y.getValue() / 100.0D);
/*     */       BlurUtils.renderBlurredBackground(blur, resolution.func_78326_a(), resolution.func_78328_b(), (resolution.func_78326_a() - 170 - resolution.func_78326_a() / 4), (resolution.func_78328_b() - 70 - resolution.func_78328_b() / 4), 150.0F, 50.0F);
/*     */       GL11.glTranslatef(x, y, 0.0F);
/*     */       RenderUtils.drawRoundRect2((resolution.func_78326_a() - 170), (resolution.func_78328_b() - 70), 150.0F, 50.0F, 3.0F, (new Color(21, 21, 21, 52)).getRGB());
/*     */       Fonts.fontBig.drawSmoothString(ChatFormatting.stripFormatting(this.entity.func_70005_c_()), ((resolution.func_78326_a() - 165) + 0.4F), (resolution.func_78328_b() - 64) + 0.5F, (new Color(20, 20, 20)).getRGB());
/*     */       Fonts.fontBig.drawSmoothString(ChatFormatting.stripFormatting(this.entity.func_70005_c_()), (resolution.func_78326_a() - 165), (resolution.func_78328_b() - 64), OringoClient.clickGui.getColor().brighter().getRGB());
/*     */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */       int i = 0;
/*     */       mc.func_110434_K().func_110577_a(inventoryBackground);
/*     */       GL11.glTranslatef((resolution.func_78326_a() - 167), (resolution.func_78328_b() - 63 + Fonts.fontMediumBold.getHeight()), 0.0F);
/*     */       GL11.glScalef(0.5F, 0.5F, 0.5F);
/*     */       for (PotionEffect effect : this.entity.func_70651_bq()) {
/*     */         Potion potion = Potion.field_76425_a[effect.func_76456_a()];
/*     */         if (potion.func_76400_d()) {
/*     */           int i1 = potion.func_76392_e();
/*     */           (new Gui()).func_73729_b(i++ * 20, 0, i1 % 8 * 18, 198 + i1 / 8 * 18, 18, 18);
/*     */         } 
/*     */       } 
/*     */       GL11.glScalef(2.0F, 2.0F, 2.0F);
/*     */       GL11.glTranslatef((-resolution.func_78326_a() + 167), (-resolution.func_78328_b() + 63 - Fonts.fontMediumBold.getHeight()), 0.0F);
/*     */       try {
/*     */         this.entity.field_70163_u += 1000.0D;
/*     */         GuiInventory.func_147046_a(resolution.func_78326_a() - 40, resolution.func_78328_b() - 33, (int)(35.0D / Math.max(this.entity.field_70131_O, 1.5D)), 20.0F, 10.0F, this.entity);
/*     */         this.entity.field_70163_u -= 1000.0D;
/*     */       } catch (Exception exception) {}
/*     */       Fonts.fontMediumBold.drawSmoothString(((int)(this.entity.func_70032_d((Entity)mc.field_71439_g) * 10.0F) / 10.0D) + "m", ((resolution.func_78326_a() - 165) + 0.4F), (resolution.func_78328_b() - 59 + Fonts.fontMediumBold.getHeight() * 2) + 0.5F, (new Color(20, 20, 20)).getRGB());
/*     */       Fonts.fontMediumBold.drawSmoothString(((int)(this.entity.func_70032_d((Entity)mc.field_71439_g) * 10.0F) / 10.0D) + "m", (resolution.func_78326_a() - 165), (resolution.func_78328_b() - 59 + Fonts.fontMediumBold.getHeight() * 2), (new Color(231, 231, 231)).getRGB());
/*     */       float hp = (Math.abs(this.entity.func_110143_aJ() / this.entity.func_110138_aP() - this.lastHp) < 0.01D) ? (this.entity.func_110143_aJ() / this.entity.func_110138_aP()) : (float)Math.min(this.lastHp + ((this.entity.func_110143_aJ() / this.entity.func_110138_aP() > this.lastHp) ? 0.01D : -0.01D), 1.0D);
/*     */       String text = String.format("%s", new Object[] { (int)(Math.min(this.entity.func_110143_aJ() / this.entity.func_110138_aP(), 1.0F) * 100.0F) + "%" });
/*     */       RenderUtils.drawRoundRect((resolution.func_78326_a() - 160 + 130), (resolution.func_78328_b() - 30), (resolution.func_78326_a() - 160), (resolution.func_78328_b() - 26), 1.0F, Color.HSBtoRGB(0.0F, 0.0F, 0.1F));
/*     */       RenderUtils.drawRoundRect((resolution.func_78326_a() - 160) + 130.0F * hp, (resolution.func_78328_b() - 30), (resolution.func_78326_a() - 160), (resolution.func_78328_b() - 26), 1.0F, OringoClient.clickGui.getColor().getRGB());
/*     */       Fonts.fontMediumBold.drawSmoothString(text, (int)((resolution.func_78326_a() - 170) + 75.0D) - Fonts.fontMediumBold.getStringWidth(text) / 2.0D + 0.4000000059604645D, (resolution.func_78328_b() - 31 - Fonts.fontMediumBold.getHeight()) + 0.5F, (new Color(20, 20, 20)).getRGB());
/*     */       Fonts.fontMediumBold.drawSmoothString(text, (int)((resolution.func_78326_a() - 170) + 75.0D) - Fonts.fontMediumBold.getStringWidth(text) / 2.0D, (resolution.func_78328_b() - 31 - Fonts.fontMediumBold.getHeight()), (new Color(231, 231, 231)).getRGB());
/*     */       this.lastHp = hp;
/*     */       mc.field_71474_y.field_74335_Z = scale;
/*     */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\render\TargetHUD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */