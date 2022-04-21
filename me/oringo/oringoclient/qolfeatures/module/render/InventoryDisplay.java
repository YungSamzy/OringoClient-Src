/*     */ package me.oringo.oringoclient.qolfeatures.module.render;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.ModeSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*     */ import me.oringo.oringoclient.utils.RenderUtils;
/*     */ import me.oringo.oringoclient.utils.font.Fonts;
/*     */ import me.oringo.oringoclient.utils.shader.BlurUtils;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class InventoryDisplay
/*     */   extends Module
/*     */ {
/*  27 */   public NumberSetting x = new NumberSetting("X", 25.0D, 0.0D, 100.0D, 1.0D); public NumberSetting y = new NumberSetting("Y", 25.0D, 0.0D, 100.0D, 1.0D);
/*  28 */   public ModeSetting blurStrength = new ModeSetting("Blur Strength", "Low", new String[] { "None", "Low", "High" });
/*     */   public InventoryDisplay() {
/*  30 */     super("Inventory HUD", 0, Module.Category.RENDER);
/*  31 */     addSettings(new Setting[] { (Setting)this.x, (Setting)this.y, (Setting)this.blurStrength });
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRender(RenderGameOverlayEvent.Post event) {
/*  37 */     if (isToggled() && event.type.equals(RenderGameOverlayEvent.ElementType.HOTBAR) && OringoClient.mc.field_71439_g != null) {
/*  38 */       GL11.glPushMatrix();
/*  39 */       ScaledResolution scaledResolution = new ScaledResolution(OringoClient.mc);
/*  40 */       int x = (int)(scaledResolution.func_78326_a() * this.x.getValue() / 100.0D);
/*  41 */       int y = (int)(scaledResolution.func_78328_b() * this.y.getValue() / 100.0D);
/*  42 */       int blur = 0;
/*  43 */       switch (this.blurStrength.getSelected()) {
/*     */         case "Low":
/*  45 */           blur = 7;
/*     */           break;
/*     */         case "High":
/*  48 */           blur = 25;
/*     */           break;
/*     */       } 
/*  51 */       ScaledResolution resolution = new ScaledResolution(OringoClient.mc);
/*  52 */       BlurUtils.renderBlurredBackground(blur, resolution.func_78326_a(), resolution.func_78328_b(), (x - 2), (y + Fonts.fontMedium.getHeight() - 4), 182.0F, (80 - Fonts.fontMedium.getHeight() - 4));
/*  53 */       RenderUtils.drawBorderedRoundedRect((x - 2), (y + Fonts.fontMedium.getHeight() - 4), 182.5F, (80 - Fonts.fontMedium.getHeight() - 4), 3.0F, 2.5F, (new Color(19, 19, 19, 50)).getRGB(), OringoClient.clickGui.getColor().getRGB());
/*  54 */       RenderUtils.drawRect((x - 2), y + Fonts.fontMedium.getHeight() * 2.0F + 2.0F, (x + 180) + 0.5F, y + Fonts.fontMedium.getHeight() * 2.0F + 3.5F, OringoClient.clickGui.getColor().getRGB());
/*  55 */       Fonts.fontMediumBold.drawSmoothCenteredString("Inventory", x + 90.0F, (y + Fonts.fontMedium.getHeight()), Color.white.getRGB());
/*     */       
/*  57 */       GlStateManager.func_179091_B();
/*  58 */       GlStateManager.func_179147_l();
/*  59 */       GlStateManager.func_179120_a(770, 771, 1, 0);
/*  60 */       RenderHelper.func_74520_c();
/*  61 */       for (int i = 9; i < 36; i++) {
/*  62 */         if (i % 9 == 0) y += 20; 
/*  63 */         ItemStack stack = OringoClient.mc.field_71439_g.field_71071_by.func_70301_a(i);
/*  64 */         if (stack != null) {
/*     */ 
/*     */           
/*  67 */           OringoClient.mc.func_175599_af().func_180450_b(stack, x + 20 * i % 9, y);
/*  68 */           renderItemOverlayIntoGUI(stack, x + 20 * i % 9, y);
/*     */         } 
/*     */       } 
/*  71 */       RenderHelper.func_74518_a();
/*  72 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  73 */       GlStateManager.func_179101_C();
/*  74 */       GlStateManager.func_179084_k();
/*  75 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void renderItemOverlayIntoGUI(ItemStack itemStack, int x, int y) {
/*  80 */     if (itemStack != null) {
/*  81 */       if (itemStack.field_77994_a != 1) {
/*  82 */         String s = String.valueOf(itemStack.field_77994_a);
/*  83 */         if (itemStack.field_77994_a < 1) {
/*  84 */           s = EnumChatFormatting.RED + String.valueOf(itemStack.field_77994_a);
/*     */         }
/*     */         
/*  87 */         GlStateManager.func_179140_f();
/*  88 */         GlStateManager.func_179097_i();
/*  89 */         GlStateManager.func_179084_k();
/*  90 */         Fonts.fontMediumBold.drawSmoothStringWithShadow(s, (int)((x + 19 - 2) - Fonts.fontMediumBold.getStringWidth(s)), (y + 6 + 3), 16777215);
/*  91 */         GlStateManager.func_179145_e();
/*  92 */         GlStateManager.func_179126_j();
/*     */       } 
/*     */       
/*  95 */       if (itemStack.func_77973_b().showDurabilityBar(itemStack)) {
/*  96 */         double health = itemStack.func_77973_b().getDurabilityForDisplay(itemStack);
/*  97 */         int j = (int)Math.round(13.0D - health * 13.0D);
/*  98 */         int i = (int)Math.round(255.0D - health * 255.0D);
/*  99 */         GlStateManager.func_179140_f();
/* 100 */         GlStateManager.func_179097_i();
/* 101 */         GlStateManager.func_179090_x();
/* 102 */         GlStateManager.func_179118_c();
/* 103 */         GlStateManager.func_179084_k();
/* 104 */         Tessellator tessellator = Tessellator.func_178181_a();
/* 105 */         WorldRenderer worldrenderer = tessellator.func_178180_c();
/* 106 */         draw(worldrenderer, x + 2, y + 13, 13, 2, 0, 0, 0, 255);
/* 107 */         draw(worldrenderer, x + 2, y + 13, 12, 1, (255 - i) / 4, 64, 0, 255);
/* 108 */         draw(worldrenderer, x + 2, y + 13, j, 1, 255 - i, i, 0, 255);
/* 109 */         GlStateManager.func_179141_d();
/* 110 */         GlStateManager.func_179098_w();
/* 111 */         GlStateManager.func_179145_e();
/* 112 */         GlStateManager.func_179126_j();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void draw(WorldRenderer p_draw_1_, int p_draw_2_, int p_draw_3_, int p_draw_4_, int p_draw_5_, int p_draw_6_, int p_draw_7_, int p_draw_8_, int p_draw_9_) {
/* 119 */     p_draw_1_.func_181668_a(7, DefaultVertexFormats.field_181706_f);
/* 120 */     p_draw_1_.func_181662_b((p_draw_2_ + 0), (p_draw_3_ + 0), 0.0D).func_181669_b(p_draw_6_, p_draw_7_, p_draw_8_, p_draw_9_).func_181675_d();
/* 121 */     p_draw_1_.func_181662_b((p_draw_2_ + 0), (p_draw_3_ + p_draw_5_), 0.0D).func_181669_b(p_draw_6_, p_draw_7_, p_draw_8_, p_draw_9_).func_181675_d();
/* 122 */     p_draw_1_.func_181662_b((p_draw_2_ + p_draw_4_), (p_draw_3_ + p_draw_5_), 0.0D).func_181669_b(p_draw_6_, p_draw_7_, p_draw_8_, p_draw_9_).func_181675_d();
/* 123 */     p_draw_1_.func_181662_b((p_draw_2_ + p_draw_4_), (p_draw_3_ + 0), 0.0D).func_181669_b(p_draw_6_, p_draw_7_, p_draw_8_, p_draw_9_).func_181675_d();
/* 124 */     Tessellator.func_178181_a().func_78381_a();
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\render\InventoryDisplay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */