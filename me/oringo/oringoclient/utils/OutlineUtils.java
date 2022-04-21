/*     */ package me.oringo.oringoclient.utils;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import me.oringo.oringoclient.events.RenderLayersEvent;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.shader.Framebuffer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import org.lwjgl.opengl.EXTFramebufferObject;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class OutlineUtils
/*     */ {
/*     */   public static void outlineESP(EntityLivingBase entity, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float scaleFactor, ModelBase modelBase, Color color) {
/*  18 */     Minecraft mc = Minecraft.func_71410_x();
/*  19 */     boolean fancyGraphics = mc.field_71474_y.field_74347_j;
/*  20 */     float gamma = mc.field_71474_y.field_74333_Y;
/*  21 */     mc.field_71474_y.field_74347_j = false;
/*  22 */     mc.field_71474_y.field_74333_Y = 100000.0F;
/*  23 */     GlStateManager.func_179117_G();
/*  24 */     setColor(color);
/*  25 */     renderOne(2.0F);
/*  26 */     modelBase.func_78088_a((Entity)entity, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
/*  27 */     setColor(color);
/*  28 */     renderTwo();
/*  29 */     modelBase.func_78088_a((Entity)entity, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
/*  30 */     setColor(color);
/*  31 */     renderThree();
/*  32 */     modelBase.func_78088_a((Entity)entity, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
/*  33 */     setColor(color);
/*  34 */     renderFour(color);
/*  35 */     modelBase.func_78088_a((Entity)entity, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
/*  36 */     setColor(color);
/*  37 */     renderFive();
/*  38 */     setColor(Color.WHITE);
/*  39 */     mc.field_71474_y.field_74347_j = fancyGraphics;
/*  40 */     mc.field_71474_y.field_74333_Y = gamma;
/*     */   }
/*     */   
/*     */   public static void outlineESP(RenderLayersEvent event, Color color) {
/*  44 */     Minecraft mc = Minecraft.func_71410_x();
/*  45 */     boolean fancyGraphics = mc.field_71474_y.field_74347_j;
/*  46 */     float gamma = mc.field_71474_y.field_74333_Y;
/*  47 */     mc.field_71474_y.field_74347_j = false;
/*  48 */     mc.field_71474_y.field_74333_Y = 100000.0F;
/*     */     
/*  50 */     GlStateManager.func_179117_G();
/*  51 */     setColor(color);
/*  52 */     renderOne(3.0F);
/*  53 */     event.modelBase.func_78088_a((Entity)event.entity, event.p_77036_2_, event.p_77036_3_, event.p_77036_4_, event.p_77036_5_, event.p_77036_6_, event.scaleFactor);
/*  54 */     setColor(color);
/*  55 */     renderTwo();
/*  56 */     event.modelBase.func_78088_a((Entity)event.entity, event.p_77036_2_, event.p_77036_3_, event.p_77036_4_, event.p_77036_5_, event.p_77036_6_, event.scaleFactor);
/*  57 */     setColor(color);
/*  58 */     renderThree();
/*  59 */     event.modelBase.func_78088_a((Entity)event.entity, event.p_77036_2_, event.p_77036_3_, event.p_77036_4_, event.p_77036_5_, event.p_77036_6_, event.scaleFactor);
/*  60 */     setColor(color);
/*  61 */     renderFour(color);
/*  62 */     event.modelBase.func_78088_a((Entity)event.entity, event.p_77036_2_, event.p_77036_3_, event.p_77036_4_, event.p_77036_5_, event.p_77036_6_, event.scaleFactor);
/*  63 */     setColor(color);
/*  64 */     renderFive();
/*  65 */     setColor(Color.WHITE);
/*     */     
/*  67 */     mc.field_71474_y.field_74347_j = fancyGraphics;
/*  68 */     mc.field_71474_y.field_74333_Y = gamma;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderOne(float lineWidth) {
/*  73 */     checkSetupFBO();
/*  74 */     GL11.glPushAttrib(1048575);
/*  75 */     GL11.glDisable(3008);
/*  76 */     GL11.glDisable(3553);
/*  77 */     GL11.glDisable(2896);
/*  78 */     GL11.glEnable(3042);
/*  79 */     GL11.glBlendFunc(770, 771);
/*  80 */     GL11.glLineWidth(lineWidth);
/*  81 */     GL11.glEnable(2848);
/*  82 */     GL11.glEnable(2960);
/*  83 */     GL11.glClear(1024);
/*  84 */     GL11.glClearStencil(15);
/*  85 */     GL11.glStencilFunc(512, 1, 15);
/*  86 */     GL11.glStencilOp(7681, 7681, 7681);
/*  87 */     GL11.glPolygonMode(1032, 6913);
/*     */   }
/*     */   
/*     */   public static void renderTwo() {
/*  91 */     GL11.glStencilFunc(512, 0, 15);
/*  92 */     GL11.glStencilOp(7681, 7681, 7681);
/*  93 */     GL11.glPolygonMode(1032, 6914);
/*     */   }
/*     */   
/*     */   public static void renderThree() {
/*  97 */     GL11.glStencilFunc(514, 1, 15);
/*  98 */     GL11.glStencilOp(7680, 7680, 7680);
/*  99 */     GL11.glPolygonMode(1032, 6913);
/*     */   }
/*     */   
/*     */   public static void renderFour(Color color) {
/* 103 */     setColor(color);
/* 104 */     GL11.glDepthMask(false);
/* 105 */     GL11.glDisable(2929);
/* 106 */     GL11.glEnable(10754);
/* 107 */     GL11.glPolygonOffset(1.0F, -2000000.0F);
/* 108 */     OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, 240.0F, 240.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void renderFive() {
/* 114 */     GL11.glPolygonOffset(1.0F, 2000000.0F);
/* 115 */     GL11.glDisable(10754);
/* 116 */     GL11.glEnable(2929);
/* 117 */     GL11.glDepthMask(true);
/* 118 */     GL11.glDisable(2960);
/* 119 */     GL11.glDisable(2848);
/* 120 */     GL11.glHint(3154, 4352);
/* 121 */     GL11.glEnable(3042);
/* 122 */     GL11.glEnable(2896);
/* 123 */     GL11.glEnable(3553);
/* 124 */     GL11.glEnable(3008);
/* 125 */     GL11.glPopAttrib();
/*     */   }
/*     */   
/*     */   public static void setColor(Color color) {
/* 129 */     GL11.glColor4d((color.getRed() / 255.0F), (color.getGreen() / 255.0F), (color.getBlue() / 255.0F), (color.getAlpha() / 255.0F));
/*     */   }
/*     */   
/*     */   public static void checkSetupFBO() {
/* 133 */     Framebuffer fbo = Minecraft.func_71410_x().func_147110_a();
/* 134 */     if (fbo != null && fbo.field_147624_h > -1) {
/*     */       
/* 136 */       setupFBO(fbo);
/* 137 */       fbo.field_147624_h = -1;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void setupFBO(Framebuffer fbo) {
/* 142 */     EXTFramebufferObject.glDeleteRenderbuffersEXT(fbo.field_147624_h);
/* 143 */     int stencil_depth_buffer_ID = EXTFramebufferObject.glGenRenderbuffersEXT();
/* 144 */     EXTFramebufferObject.glBindRenderbufferEXT(36161, stencil_depth_buffer_ID);
/* 145 */     EXTFramebufferObject.glRenderbufferStorageEXT(36161, 34041, (Minecraft.func_71410_x()).field_71443_c, (Minecraft.func_71410_x()).field_71440_d);
/* 146 */     EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36128, 36161, stencil_depth_buffer_ID);
/* 147 */     EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36096, 36161, stencil_depth_buffer_ID);
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclien\\utils\OutlineUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */