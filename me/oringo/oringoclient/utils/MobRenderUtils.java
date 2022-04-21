/*     */ package me.oringo.oringoclient.utils;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.nio.FloatBuffer;
/*     */ import net.minecraft.client.renderer.GLAllocation;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.texture.DynamicTexture;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MobRenderUtils
/*     */ {
/*  20 */   private static final DynamicTexture empty = new DynamicTexture(16, 16);
/*  21 */   protected static final FloatBuffer brightnessBuffer = GLAllocation.func_74529_h(4);
/*     */ 
/*     */   
/*     */   static {
/*  25 */     int[] aint = empty.func_110565_c();
/*     */     
/*  27 */     for (int i = 0; i < 256; i++)
/*     */     {
/*  29 */       aint[i] = -1;
/*     */     }
/*     */     
/*  32 */     empty.func_110564_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setColor(Color color) {
/*  37 */     GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
/*  38 */     GlStateManager.func_179098_w();
/*  39 */     GL11.glTexEnvi(8960, 8704, OpenGlHelper.field_176095_s);
/*  40 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176099_x, 8448);
/*  41 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176098_y, OpenGlHelper.field_77478_a);
/*  42 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176097_z, OpenGlHelper.field_176093_u);
/*  43 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176081_B, 768);
/*  44 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176082_C, 768);
/*  45 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176077_E, 7681);
/*  46 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176078_F, OpenGlHelper.field_77478_a);
/*  47 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176085_I, 770);
/*  48 */     GlStateManager.func_179138_g(OpenGlHelper.field_77476_b);
/*  49 */     GlStateManager.func_179098_w();
/*  50 */     GL11.glTexEnvi(8960, 8704, OpenGlHelper.field_176095_s);
/*  51 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176099_x, OpenGlHelper.field_176094_t);
/*  52 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176098_y, OpenGlHelper.field_176092_v);
/*  53 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176097_z, OpenGlHelper.field_176091_w);
/*  54 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176080_A, OpenGlHelper.field_176092_v);
/*  55 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176081_B, 768);
/*  56 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176082_C, 768);
/*  57 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176076_D, 770);
/*  58 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176077_E, 7681);
/*  59 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176078_F, OpenGlHelper.field_176091_w);
/*  60 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176085_I, 770);
/*  61 */     brightnessBuffer.position(0);
/*     */     
/*  63 */     brightnessBuffer.put(color.getRed() / 255.0F);
/*  64 */     brightnessBuffer.put(color.getGreen() / 255.0F);
/*  65 */     brightnessBuffer.put(color.getBlue() / 255.0F);
/*  66 */     brightnessBuffer.put(color.getAlpha() / 255.0F);
/*     */     
/*  68 */     brightnessBuffer.flip();
/*  69 */     GL11.glTexEnv(8960, 8705, brightnessBuffer);
/*  70 */     GlStateManager.func_179138_g(OpenGlHelper.field_176096_r);
/*  71 */     GlStateManager.func_179098_w();
/*  72 */     GlStateManager.func_179144_i(empty.func_110552_b());
/*  73 */     GL11.glTexEnvi(8960, 8704, OpenGlHelper.field_176095_s);
/*  74 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176099_x, 8448);
/*  75 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176098_y, OpenGlHelper.field_176091_w);
/*  76 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176097_z, OpenGlHelper.field_77476_b);
/*  77 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176081_B, 768);
/*  78 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176082_C, 768);
/*  79 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176077_E, 7681);
/*  80 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176078_F, OpenGlHelper.field_176091_w);
/*  81 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176085_I, 770);
/*  82 */     GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
/*     */   }
/*     */   
/*     */   public static void unsetColor() {
/*  86 */     GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
/*  87 */     GlStateManager.func_179098_w();
/*  88 */     GL11.glTexEnvi(8960, 8704, OpenGlHelper.field_176095_s);
/*  89 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176099_x, 8448);
/*  90 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176098_y, OpenGlHelper.field_77478_a);
/*  91 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176097_z, OpenGlHelper.field_176093_u);
/*  92 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176081_B, 768);
/*  93 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176082_C, 768);
/*  94 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176077_E, 8448);
/*  95 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176078_F, OpenGlHelper.field_77478_a);
/*  96 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176079_G, OpenGlHelper.field_176093_u);
/*  97 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176085_I, 770);
/*  98 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176086_J, 770);
/*  99 */     GlStateManager.func_179138_g(OpenGlHelper.field_77476_b);
/* 100 */     GL11.glTexEnvi(8960, 8704, OpenGlHelper.field_176095_s);
/* 101 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176099_x, 8448);
/* 102 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176081_B, 768);
/* 103 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176082_C, 768);
/* 104 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176098_y, 5890);
/* 105 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176097_z, OpenGlHelper.field_176091_w);
/* 106 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176077_E, 8448);
/* 107 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176085_I, 770);
/* 108 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176078_F, 5890);
/* 109 */     GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 110 */     GlStateManager.func_179138_g(OpenGlHelper.field_176096_r);
/* 111 */     GlStateManager.func_179090_x();
/* 112 */     GlStateManager.func_179144_i(0);
/* 113 */     GL11.glTexEnvi(8960, 8704, OpenGlHelper.field_176095_s);
/* 114 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176099_x, 8448);
/* 115 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176081_B, 768);
/* 116 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176082_C, 768);
/* 117 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176098_y, 5890);
/* 118 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176097_z, OpenGlHelper.field_176091_w);
/* 119 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176077_E, 8448);
/* 120 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176085_I, 770);
/* 121 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176078_F, 5890);
/* 122 */     GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclien\\utils\MobRenderUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */