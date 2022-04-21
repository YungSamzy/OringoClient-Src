/*     */ package me.oringo.oringoclient.utils.shader;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import me.oringo.oringoclient.utils.RenderUtils;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.client.shader.Framebuffer;
/*     */ import net.minecraft.client.shader.Shader;
/*     */ import net.minecraft.util.Matrix4f;
/*     */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.fml.common.eventhandler.EventPriority;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.opengl.GL14;
/*     */ import org.lwjgl.opengl.GL30;
/*     */ import org.lwjgl.util.vector.Matrix4f;
/*     */ 
/*     */ public class BlurUtils
/*     */ {
/*     */   private static class OutputStuff {
/*     */     public Framebuffer framebuffer;
/*  31 */     public Shader blurShaderHorz = null;
/*  32 */     public Shader blurShaderVert = null;
/*     */     
/*     */     public OutputStuff(Framebuffer framebuffer, Shader blurShaderHorz, Shader blurShaderVert) {
/*  35 */       this.framebuffer = framebuffer;
/*  36 */       this.blurShaderHorz = blurShaderHorz;
/*  37 */       this.blurShaderVert = blurShaderVert;
/*     */     }
/*     */   }
/*     */   
/*  41 */   private static HashMap<Float, OutputStuff> blurOutput = new HashMap<>();
/*  42 */   private static HashMap<Float, Long> lastBlurUse = new HashMap<>();
/*  43 */   private static HashSet<Float> requestedBlurs = new HashSet<>();
/*     */   
/*  45 */   private static int fogColour = 0;
/*     */   private static boolean registered = false;
/*     */   
/*     */   public static void registerListener() {
/*  49 */     if (!registered) {
/*  50 */       registered = true;
/*  51 */       MinecraftForge.EVENT_BUS.register(new BlurUtils());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void processBlurs() {
/*  57 */     long currentTime = System.currentTimeMillis();
/*     */     
/*  59 */     for (Iterator<Float> iterator = requestedBlurs.iterator(); iterator.hasNext(); ) { float blur = ((Float)iterator.next()).floatValue();
/*  60 */       lastBlurUse.put(Float.valueOf(blur), Long.valueOf(currentTime));
/*     */       
/*  62 */       int width = (Minecraft.func_71410_x()).field_71443_c;
/*  63 */       int height = (Minecraft.func_71410_x()).field_71440_d;
/*     */       
/*  65 */       OutputStuff output = blurOutput.computeIfAbsent(Float.valueOf(blur), k -> {
/*     */             Framebuffer fb = new Framebuffer(width, height, false);
/*     */             
/*     */             fb.func_147607_a(9728);
/*     */             return new OutputStuff(fb, null, null);
/*     */           });
/*  71 */       if (output.framebuffer.field_147621_c != width || output.framebuffer.field_147618_d != height) {
/*  72 */         output.framebuffer.func_147613_a(width, height);
/*  73 */         if (output.blurShaderHorz != null) {
/*  74 */           output.blurShaderHorz.func_148045_a((Matrix4f)createProjectionMatrix(width, height));
/*     */         }
/*  76 */         if (output.blurShaderVert != null) {
/*  77 */           output.blurShaderVert.func_148045_a((Matrix4f)createProjectionMatrix(width, height));
/*     */         }
/*     */       } 
/*     */       
/*  81 */       blurBackground(output, blur); }
/*     */ 
/*     */     
/*  84 */     Set<Float> remove = new HashSet<>();
/*  85 */     for (Map.Entry<Float, Long> entry : lastBlurUse.entrySet()) {
/*  86 */       if (currentTime - ((Long)entry.getValue()).longValue() > 30000L) {
/*  87 */         remove.add(entry.getKey());
/*     */       }
/*     */     } 
/*     */     
/*  91 */     for (Map.Entry<Float, OutputStuff> entry : blurOutput.entrySet()) {
/*  92 */       if (remove.contains(entry.getKey())) {
/*  93 */         ((OutputStuff)entry.getValue()).framebuffer.func_147608_a();
/*  94 */         ((OutputStuff)entry.getValue()).blurShaderHorz.func_148044_b();
/*  95 */         ((OutputStuff)entry.getValue()).blurShaderVert.func_148044_b();
/*     */       } 
/*     */     } 
/*     */     
/*  99 */     lastBlurUse.keySet().removeAll(remove);
/* 100 */     blurOutput.keySet().removeAll(remove);
/*     */     
/* 102 */     requestedBlurs.clear();
/*     */   }
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.HIGHEST)
/*     */   public void onScreenRender(RenderGameOverlayEvent.Pre event) {
/* 107 */     if (event.type == RenderGameOverlayEvent.ElementType.ALL) {
/* 108 */       processBlurs();
/*     */     }
/* 110 */     Minecraft.func_71410_x().func_147110_a().func_147610_a(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   private static Framebuffer blurOutputHorz = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Matrix4f createProjectionMatrix(int width, int height) {
/* 131 */     Matrix4f projMatrix = new Matrix4f();
/* 132 */     projMatrix.setIdentity();
/* 133 */     projMatrix.m00 = 2.0F / width;
/* 134 */     projMatrix.m11 = 2.0F / -height;
/* 135 */     projMatrix.m22 = -0.0020001999F;
/* 136 */     projMatrix.m33 = 1.0F;
/* 137 */     projMatrix.m03 = -1.0F;
/* 138 */     projMatrix.m13 = 1.0F;
/* 139 */     projMatrix.m23 = -1.0001999F;
/* 140 */     return projMatrix;
/*     */   }
/*     */   
/*     */   private static void blurBackground(OutputStuff output, float blurFactor) {
/* 144 */     if (!OpenGlHelper.func_148822_b() || !OpenGlHelper.func_153193_b())
/*     */       return; 
/* 146 */     int width = (Minecraft.func_71410_x()).field_71443_c;
/* 147 */     int height = (Minecraft.func_71410_x()).field_71440_d;
/*     */     
/* 149 */     GlStateManager.func_179128_n(5889);
/* 150 */     GlStateManager.func_179096_D();
/* 151 */     GlStateManager.func_179130_a(0.0D, width, height, 0.0D, 1000.0D, 3000.0D);
/* 152 */     GlStateManager.func_179128_n(5888);
/* 153 */     GlStateManager.func_179096_D();
/* 154 */     GlStateManager.func_179109_b(0.0F, 0.0F, -2000.0F);
/*     */     
/* 156 */     if (blurOutputHorz == null) {
/* 157 */       blurOutputHorz = new Framebuffer(width, height, false);
/* 158 */       blurOutputHorz.func_147607_a(9728);
/*     */     } 
/* 160 */     if (blurOutputHorz == null || output == null) {
/*     */       return;
/*     */     }
/* 163 */     if (blurOutputHorz.field_147621_c != width || blurOutputHorz.field_147618_d != height) {
/* 164 */       blurOutputHorz.func_147613_a(width, height);
/* 165 */       Minecraft.func_71410_x().func_147110_a().func_147610_a(false);
/*     */     } 
/*     */     
/* 168 */     if (output.blurShaderHorz == null) {
/*     */       try {
/* 170 */         output.blurShaderHorz = new Shader(Minecraft.func_71410_x().func_110442_L(), "blur", output.framebuffer, blurOutputHorz);
/*     */         
/* 172 */         output.blurShaderHorz.func_148043_c().func_147991_a("BlurDir").func_148087_a(1.0F, 0.0F);
/* 173 */         output.blurShaderHorz.func_148045_a((Matrix4f)createProjectionMatrix(width, height));
/* 174 */       } catch (Exception exception) {}
/*     */     }
/* 176 */     if (output.blurShaderVert == null) {
/*     */       try {
/* 178 */         output.blurShaderVert = new Shader(Minecraft.func_71410_x().func_110442_L(), "blur", blurOutputHorz, output.framebuffer);
/*     */         
/* 180 */         output.blurShaderVert.func_148043_c().func_147991_a("BlurDir").func_148087_a(0.0F, 1.0F);
/* 181 */         output.blurShaderVert.func_148045_a((Matrix4f)createProjectionMatrix(width, height));
/* 182 */       } catch (Exception exception) {}
/*     */     }
/* 184 */     if (output.blurShaderHorz != null && output.blurShaderVert != null) {
/* 185 */       if (output.blurShaderHorz.func_148043_c().func_147991_a("Radius") == null) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 190 */       output.blurShaderHorz.func_148043_c().func_147991_a("Radius").func_148090_a(blurFactor);
/* 191 */       output.blurShaderVert.func_148043_c().func_147991_a("Radius").func_148090_a(blurFactor);
/*     */       
/* 193 */       GL11.glPushMatrix();
/* 194 */       GL30.glBindFramebuffer(36008, (Minecraft.func_71410_x().func_147110_a()).field_147616_f);
/* 195 */       GL30.glBindFramebuffer(36009, output.framebuffer.field_147616_f);
/* 196 */       GL30.glBlitFramebuffer(0, 0, width, height, 0, 0, output.framebuffer.field_147621_c, output.framebuffer.field_147618_d, 16384, 9728);
/*     */ 
/*     */ 
/*     */       
/* 200 */       output.blurShaderHorz.func_148042_a(0.0F);
/* 201 */       output.blurShaderVert.func_148042_a(0.0F);
/* 202 */       GlStateManager.func_179126_j();
/* 203 */       GL11.glPopMatrix();
/*     */     } 
/* 205 */     Minecraft.func_71410_x().func_147110_a().func_147610_a(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void renderBlurredBackground(float blurStrength, int screenWidth, int screenHeight, float x, float y, float blurWidth, float blurHeight) {
/* 214 */     if (!OpenGlHelper.func_148822_b() || !OpenGlHelper.func_153193_b())
/* 215 */       return;  if (blurStrength < 0.5D)
/* 216 */       return;  requestedBlurs.add(Float.valueOf(blurStrength));
/*     */ 
/*     */     
/* 219 */     if (blurOutput.isEmpty())
/*     */       return; 
/* 221 */     OutputStuff out = blurOutput.get(Float.valueOf(blurStrength));
/* 222 */     if (out == null) {
/* 223 */       out = blurOutput.values().iterator().next();
/*     */     }
/*     */     
/* 226 */     float uMin = x / screenWidth;
/* 227 */     float uMax = (x + blurWidth) / screenWidth;
/* 228 */     float vMin = (screenHeight - y) / screenHeight;
/* 229 */     float vMax = (screenHeight - y - blurHeight) / screenHeight;
/*     */     
/* 231 */     GlStateManager.func_179132_a(false);
/* 232 */     RenderUtils.drawRect(x, y, x + blurWidth, y + blurHeight, fogColour);
/* 233 */     out.framebuffer.func_147612_c();
/* 234 */     GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 235 */     drawTexturedRect(x, y, blurWidth, blurHeight, uMin, uMax, vMin, vMax, 9728);
/* 236 */     out.framebuffer.func_147606_d();
/* 237 */     GlStateManager.func_179132_a(true);
/* 238 */     GlStateManager.func_179117_G();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawTexturedRect(float x, float y, float width, float height, float uMin, float uMax, float vMin, float vMax, int filter) {
/* 243 */     GlStateManager.func_179147_l();
/* 244 */     GL14.glBlendFuncSeparate(770, 771, 1, 771);
/*     */     
/* 246 */     drawTexturedRectNoBlend(x, y, width, height, uMin, uMax, vMin, vMax, filter);
/*     */     
/* 248 */     GlStateManager.func_179084_k();
/*     */   }
/*     */   
/*     */   public static void drawTexturedRectNoBlend(float x, float y, float width, float height, float uMin, float uMax, float vMin, float vMax, int filter) {
/* 252 */     GlStateManager.func_179098_w();
/*     */     
/* 254 */     GL11.glTexParameteri(3553, 10241, filter);
/* 255 */     GL11.glTexParameteri(3553, 10240, filter);
/*     */     
/* 257 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 258 */     WorldRenderer worldrenderer = tessellator.func_178180_c();
/* 259 */     worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
/* 260 */     worldrenderer
/* 261 */       .func_181662_b(x, (y + height), 0.0D)
/* 262 */       .func_181673_a(uMin, vMax).func_181675_d();
/* 263 */     worldrenderer
/* 264 */       .func_181662_b((x + width), (y + height), 0.0D)
/* 265 */       .func_181673_a(uMax, vMax).func_181675_d();
/* 266 */     worldrenderer
/* 267 */       .func_181662_b((x + width), y, 0.0D)
/* 268 */       .func_181673_a(uMax, vMin).func_181675_d();
/* 269 */     worldrenderer
/* 270 */       .func_181662_b(x, y, 0.0D)
/* 271 */       .func_181673_a(uMin, vMin).func_181675_d();
/* 272 */     tessellator.func_78381_a();
/*     */     
/* 274 */     GL11.glTexParameteri(3553, 10241, 9728);
/* 275 */     GL11.glTexParameteri(3553, 10240, 9728);
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclien\\utils\shader\BlurUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */