/*     */ package me.oringo.oringoclient.utils;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.RenderGlobal;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.Vec3;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.util.vector.Matrix4f;
/*     */ import org.lwjgl.util.vector.Vector2f;
/*     */ import org.lwjgl.util.vector.Vector3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderUtils
/*     */ {
/*     */   public static void drawRect(float left, float top, float right, float bottom, int color) {
/*  32 */     if (left < right) {
/*     */       
/*  34 */       float i = left;
/*  35 */       left = right;
/*  36 */       right = i;
/*     */     } 
/*     */     
/*  39 */     if (top < bottom) {
/*     */       
/*  41 */       float j = top;
/*  42 */       top = bottom;
/*  43 */       bottom = j;
/*     */     } 
/*     */     
/*  46 */     float f3 = (color >> 24 & 0xFF) / 255.0F;
/*  47 */     float f = (color >> 16 & 0xFF) / 255.0F;
/*  48 */     float f1 = (color >> 8 & 0xFF) / 255.0F;
/*  49 */     float f2 = (color & 0xFF) / 255.0F;
/*  50 */     Tessellator tessellator = Tessellator.func_178181_a();
/*  51 */     WorldRenderer worldrenderer = tessellator.func_178180_c();
/*  52 */     GlStateManager.func_179147_l();
/*  53 */     GlStateManager.func_179090_x();
/*  54 */     GlStateManager.func_179120_a(770, 771, 1, 0);
/*  55 */     GlStateManager.func_179131_c(f, f1, f2, f3);
/*  56 */     worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
/*  57 */     worldrenderer.func_181662_b(left, bottom, 0.0D).func_181675_d();
/*  58 */     worldrenderer.func_181662_b(right, bottom, 0.0D).func_181675_d();
/*  59 */     worldrenderer.func_181662_b(right, top, 0.0D).func_181675_d();
/*  60 */     worldrenderer.func_181662_b(left, top, 0.0D).func_181675_d();
/*  61 */     tessellator.func_78381_a();
/*  62 */     GlStateManager.func_179124_c(1.0F, 1.0F, 1.0F);
/*  63 */     GlStateManager.func_179098_w();
/*  64 */     GlStateManager.func_179084_k();
/*     */   }
/*     */   
/*     */   public static void drawBorderedRect(float x, float y, float x1, float y1, float width, int internalColor, int borderColor) {
/*  68 */     enableGL2D();
/*  69 */     drawRect(x + width, y + width, x1 - width, y1 - width, internalColor);
/*  70 */     GL11.glPushMatrix();
/*  71 */     drawRect(x + width, y, x1 - width, y + width, borderColor);
/*  72 */     drawRect(x, y, x + width, y1, borderColor);
/*  73 */     drawRect(x1 - width, y, x1, y1, borderColor);
/*  74 */     drawRect(x + width, y1 - width, x1 - width, y1, borderColor);
/*  75 */     GL11.glPopMatrix();
/*  76 */     disableGL2D();
/*     */   }
/*     */   
/*     */   public static void draw2D(Entity entityLiving, float partialTicks, float lineWidth, Color color) {
/*  80 */     Matrix4f mvMatrix = WorldToScreen.getMatrix(2982);
/*  81 */     Matrix4f projectionMatrix = WorldToScreen.getMatrix(2983);
/*  82 */     GL11.glPushAttrib(8192);
/*     */     
/*  84 */     GL11.glEnable(3042);
/*     */     
/*  86 */     GL11.glDisable(3553);
/*     */     
/*  88 */     GL11.glDisable(2929);
/*     */     
/*  90 */     GL11.glMatrixMode(5889);
/*  91 */     GL11.glPushMatrix();
/*  92 */     GL11.glLoadIdentity();
/*  93 */     GL11.glOrtho(0.0D, OringoClient.mc.field_71443_c, OringoClient.mc.field_71440_d, 0.0D, -1.0D, 1.0D);
/*  94 */     GL11.glMatrixMode(5888);
/*  95 */     GL11.glPushMatrix();
/*  96 */     GL11.glLoadIdentity();
/*     */     
/*  98 */     GL11.glDisable(2929);
/*     */     
/* 100 */     GL11.glBlendFunc(770, 771);
/* 101 */     GlStateManager.func_179098_w();
/* 102 */     GlStateManager.func_179132_a(true);
/*     */     
/* 104 */     GL11.glLineWidth(lineWidth);
/* 105 */     RenderManager renderManager = OringoClient.mc.func_175598_ae();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 111 */     AxisAlignedBB bb = entityLiving.func_174813_aQ().func_72317_d(-entityLiving.field_70165_t, -entityLiving.field_70163_u, -entityLiving.field_70161_v).func_72317_d(entityLiving.field_70142_S + (entityLiving.field_70165_t - entityLiving.field_70142_S) * partialTicks, entityLiving.field_70137_T + (entityLiving.field_70163_u - entityLiving.field_70137_T) * partialTicks, entityLiving.field_70136_U + (entityLiving.field_70161_v - entityLiving.field_70136_U) * partialTicks).func_72317_d(-renderManager.field_78730_l, -renderManager.field_78731_m, -renderManager.field_78728_n);
/*     */     
/* 113 */     GL11.glColor4f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, 1.0F);
/*     */     
/* 115 */     double[][] boxVertices = { { bb.field_72340_a, bb.field_72338_b, bb.field_72339_c }, { bb.field_72340_a, bb.field_72337_e, bb.field_72339_c }, { bb.field_72336_d, bb.field_72337_e, bb.field_72339_c }, { bb.field_72336_d, bb.field_72338_b, bb.field_72339_c }, { bb.field_72340_a, bb.field_72338_b, bb.field_72334_f }, { bb.field_72340_a, bb.field_72337_e, bb.field_72334_f }, { bb.field_72336_d, bb.field_72337_e, bb.field_72334_f }, { bb.field_72336_d, bb.field_72338_b, bb.field_72334_f } };
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
/* 126 */     float minX = Float.MAX_VALUE;
/* 127 */     float minY = Float.MAX_VALUE;
/*     */     
/* 129 */     float maxX = -1.0F;
/* 130 */     float maxY = -1.0F;
/*     */     
/* 132 */     for (double[] boxVertex : boxVertices) {
/* 133 */       Vector2f screenPos = WorldToScreen.worldToScreen(new Vector3f((float)boxVertex[0], (float)boxVertex[1], (float)boxVertex[2]), mvMatrix, projectionMatrix, OringoClient.mc.field_71443_c, OringoClient.mc.field_71440_d);
/*     */       
/* 135 */       if (screenPos != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 140 */         minX = Math.min(screenPos.x, minX);
/* 141 */         minY = Math.min(screenPos.y, minY);
/*     */         
/* 143 */         maxX = Math.max(screenPos.x, maxX);
/* 144 */         maxY = Math.max(screenPos.y, maxY);
/*     */       } 
/*     */     } 
/* 147 */     if (minX > 0.0F || minY > 0.0F || maxX <= OringoClient.mc.field_71443_c || maxY <= OringoClient.mc.field_71443_c) {
/* 148 */       GL11.glColor4f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, 1.0F);
/*     */       
/* 150 */       GL11.glBegin(2);
/*     */       
/* 152 */       GL11.glVertex2f(minX, minY);
/* 153 */       GL11.glVertex2f(minX, maxY);
/* 154 */       GL11.glVertex2f(maxX, maxY);
/* 155 */       GL11.glVertex2f(maxX, minY);
/*     */       
/* 157 */       GL11.glEnd();
/*     */     } 
/*     */     
/* 160 */     GL11.glEnable(2929);
/*     */     
/* 162 */     GL11.glMatrixMode(5889);
/* 163 */     GL11.glPopMatrix();
/*     */     
/* 165 */     GL11.glMatrixMode(5888);
/* 166 */     GL11.glPopMatrix();
/*     */     
/* 168 */     GL11.glPopAttrib();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void tracerLine(Entity entity, float partialTicks, float lineWidth, Color color) {
/* 174 */     double x = entity.field_70169_q + (entity.field_70165_t - entity.field_70169_q) * partialTicks - (Minecraft.func_71410_x().func_175598_ae()).field_78730_l;
/*     */     
/* 176 */     double y = entity.field_70167_r + (entity.field_70163_u - entity.field_70167_r) * partialTicks + (entity.field_70131_O / 2.0F) - (Minecraft.func_71410_x().func_175598_ae()).field_78731_m;
/*     */     
/* 178 */     double z = entity.field_70166_s + (entity.field_70161_v - entity.field_70166_s) * partialTicks - (Minecraft.func_71410_x().func_175598_ae()).field_78728_n;
/* 179 */     GL11.glBlendFunc(770, 771);
/* 180 */     GL11.glEnable(3042);
/* 181 */     GL11.glDisable(3553);
/* 182 */     GL11.glDisable(2929);
/* 183 */     GL11.glLineWidth(lineWidth);
/* 184 */     GL11.glDepthMask(false);
/* 185 */     setColor(color);
/* 186 */     GL11.glBegin(2);
/*     */     
/* 188 */     GL11.glVertex3d(0.0D, (Minecraft.func_71410_x()).field_71439_g.func_70047_e(), 0.0D);
/* 189 */     GL11.glVertex3d(x, y, z);
/*     */     
/* 191 */     GL11.glEnd();
/* 192 */     GL11.glEnable(3553);
/* 193 */     GL11.glEnable(2929);
/* 194 */     GL11.glDepthMask(true);
/* 195 */     GL11.glDisable(3042);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawTargetESP(EntityLivingBase target, Color color, float partialTicks) {
/* 201 */     GL11.glPushMatrix();
/* 202 */     float location = (float)((Math.sin(System.currentTimeMillis() * 0.005D) + 1.0D) * 0.5D);
/* 203 */     GlStateManager.func_179137_b(target.field_70142_S + (target.field_70165_t - target.field_70142_S) * partialTicks - (OringoClient.mc.func_175598_ae()).field_78730_l, target.field_70137_T + (target.field_70163_u - target.field_70137_T) * partialTicks - 
/* 204 */         (OringoClient.mc.func_175598_ae()).field_78731_m + (target.field_70131_O * location), target.field_70136_U + (target.field_70161_v - target.field_70136_U) * partialTicks - 
/* 205 */         (OringoClient.mc.func_175598_ae()).field_78728_n);
/* 206 */     enableGL2D();
/* 207 */     GL11.glShadeModel(7425);
/* 208 */     GL11.glDisable(2884);
/* 209 */     GL11.glLineWidth(3.0F);
/* 210 */     GL11.glBegin(3);
/* 211 */     double cos = Math.cos(System.currentTimeMillis() * 0.005D); int i;
/* 212 */     for (i = 0; i <= 120; i++) {
/* 213 */       GL11.glColor4f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, 1.0F);
/*     */       
/* 215 */       double x = Math.cos(i * Math.PI / 60.0D) * target.field_70130_N;
/* 216 */       double z = Math.sin(i * Math.PI / 60.0D) * target.field_70130_N;
/* 217 */       GL11.glVertex3d(x, 0.15000000596046448D * cos, z);
/*     */     } 
/* 219 */     GL11.glEnd();
/* 220 */     GL11.glBegin(5);
/* 221 */     for (i = 0; i <= 120; i++) {
/* 222 */       GL11.glColor4f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, 0.5F);
/*     */       
/* 224 */       double x = Math.cos(i * Math.PI / 60.0D) * target.field_70130_N;
/* 225 */       double z = Math.sin(i * Math.PI / 60.0D) * target.field_70130_N;
/* 226 */       GL11.glVertex3d(x, 0.15000000596046448D * cos, z);
/*     */       
/* 228 */       GL11.glColor4f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, 0.2F);
/*     */       
/* 230 */       GL11.glVertex3d(x, -0.15000000596046448D * cos, z);
/*     */     } 
/* 232 */     GL11.glEnd();
/* 233 */     GL11.glShadeModel(7424);
/* 234 */     GL11.glEnable(2884);
/* 235 */     disableGL2D();
/* 236 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawRoundRect(float left, float top, float right, float bottom, float radius, int color) {
/* 241 */     left += radius;
/* 242 */     right -= radius;
/* 243 */     if (left < right) {
/*     */       
/* 245 */       float i = left;
/* 246 */       left = right;
/* 247 */       right = i;
/*     */     } 
/*     */     
/* 250 */     if (top < bottom) {
/*     */       
/* 252 */       float j = top;
/* 253 */       top = bottom;
/* 254 */       bottom = j;
/*     */     } 
/*     */     
/* 257 */     float f3 = (color >> 24 & 0xFF) / 255.0F;
/* 258 */     float f = (color >> 16 & 0xFF) / 255.0F;
/* 259 */     float f1 = (color >> 8 & 0xFF) / 255.0F;
/* 260 */     float f2 = (color & 0xFF) / 255.0F;
/* 261 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 262 */     WorldRenderer worldrenderer = tessellator.func_178180_c();
/* 263 */     GlStateManager.func_179147_l();
/* 264 */     GlStateManager.func_179090_x();
/* 265 */     GlStateManager.func_179120_a(770, 771, 1, 0);
/* 266 */     GlStateManager.func_179131_c(f, f1, f2, f3);
/*     */ 
/*     */     
/* 269 */     worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
/* 270 */     worldrenderer.func_181662_b(left, bottom, 0.0D).func_181675_d();
/* 271 */     worldrenderer.func_181662_b(right, bottom, 0.0D).func_181675_d();
/* 272 */     worldrenderer.func_181662_b(right, top, 0.0D).func_181675_d();
/* 273 */     worldrenderer.func_181662_b(left, top, 0.0D).func_181675_d();
/* 274 */     tessellator.func_78381_a();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 279 */     worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
/* 280 */     worldrenderer.func_181662_b((right - radius), (top - radius), 0.0D).func_181675_d();
/* 281 */     worldrenderer.func_181662_b(right, (top - radius), 0.0D).func_181675_d();
/* 282 */     worldrenderer.func_181662_b(right, (bottom + radius), 0.0D).func_181675_d();
/* 283 */     worldrenderer.func_181662_b((right - radius), (bottom + radius), 0.0D).func_181675_d();
/* 284 */     tessellator.func_78381_a();
/*     */ 
/*     */     
/* 287 */     worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
/* 288 */     worldrenderer.func_181662_b(left, (top - radius), 0.0D).func_181675_d();
/* 289 */     worldrenderer.func_181662_b((left + radius), (top - radius), 0.0D).func_181675_d();
/* 290 */     worldrenderer.func_181662_b((left + radius), (bottom + radius), 0.0D).func_181675_d();
/* 291 */     worldrenderer.func_181662_b(left, (bottom + radius), 0.0D).func_181675_d();
/* 292 */     tessellator.func_78381_a();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 297 */     drawArc(right, bottom + radius, radius, 180);
/* 298 */     drawArc(left, bottom + radius, radius, 90);
/* 299 */     drawArc(right, top - radius, radius, 270);
/* 300 */     drawArc(left, top - radius, radius, 0);
/*     */ 
/*     */     
/* 303 */     GlStateManager.func_179098_w();
/* 304 */     GlStateManager.func_179084_k();
/*     */   }
/*     */   
/*     */   public static Color interpolateColor(Color color1, Color color2, float value) {
/* 308 */     return new Color((int)(color1.getRed() + (color2.getRed() - color1.getRed()) * value), 
/* 309 */         (int)(color1.getGreen() + (color2.getGreen() - color1.getGreen()) * value), 
/* 310 */         (int)(color1.getBlue() + (color2.getBlue() - color1.getBlue()) * value));
/*     */   }
/*     */   
/*     */   public static Color applyOpacity(Color color, int opacity) {
/* 314 */     return new Color(color.getRed(), color.getGreen(), color.getBlue(), opacity);
/*     */   }
/*     */   
/*     */   public static void drawRoundRect2(float x, float y, float width, float height, float radius, int color) {
/* 318 */     width += x;
/* 319 */     x += radius;
/* 320 */     width -= radius;
/* 321 */     if (x < width) {
/*     */       
/* 323 */       float i = x;
/* 324 */       x = width;
/* 325 */       width = i;
/*     */     } 
/*     */     
/* 328 */     height += y;
/* 329 */     if (y < height) {
/*     */       
/* 331 */       float j = y;
/* 332 */       y = height;
/* 333 */       height = j;
/*     */     } 
/*     */     
/* 336 */     float f3 = (color >> 24 & 0xFF) / 255.0F;
/* 337 */     float f = (color >> 16 & 0xFF) / 255.0F;
/* 338 */     float f1 = (color >> 8 & 0xFF) / 255.0F;
/* 339 */     float f2 = (color & 0xFF) / 255.0F;
/* 340 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 341 */     WorldRenderer worldrenderer = tessellator.func_178180_c();
/* 342 */     GlStateManager.func_179147_l();
/* 343 */     GlStateManager.func_179090_x();
/* 344 */     GlStateManager.func_179120_a(770, 771, 1, 0);
/* 345 */     GlStateManager.func_179131_c(f, f1, f2, f3);
/*     */ 
/*     */     
/* 348 */     worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
/* 349 */     worldrenderer.func_181662_b(x, height, 0.0D).func_181675_d();
/* 350 */     worldrenderer.func_181662_b(width, height, 0.0D).func_181675_d();
/* 351 */     worldrenderer.func_181662_b(width, y, 0.0D).func_181675_d();
/* 352 */     worldrenderer.func_181662_b(x, y, 0.0D).func_181675_d();
/* 353 */     tessellator.func_78381_a();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 358 */     worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
/* 359 */     worldrenderer.func_181662_b((width - radius), (y - radius), 0.0D).func_181675_d();
/* 360 */     worldrenderer.func_181662_b(width, (y - radius), 0.0D).func_181675_d();
/* 361 */     worldrenderer.func_181662_b(width, (height + radius), 0.0D).func_181675_d();
/* 362 */     worldrenderer.func_181662_b((width - radius), (height + radius), 0.0D).func_181675_d();
/* 363 */     tessellator.func_78381_a();
/*     */ 
/*     */     
/* 366 */     worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
/* 367 */     worldrenderer.func_181662_b(x, (y - radius), 0.0D).func_181675_d();
/* 368 */     worldrenderer.func_181662_b((x + radius), (y - radius), 0.0D).func_181675_d();
/* 369 */     worldrenderer.func_181662_b((x + radius), (height + radius), 0.0D).func_181675_d();
/* 370 */     worldrenderer.func_181662_b(x, (height + radius), 0.0D).func_181675_d();
/* 371 */     tessellator.func_78381_a();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 376 */     drawArc(width, height + radius, radius, 180);
/* 377 */     drawArc(x, height + radius, radius, 90);
/* 378 */     drawArc(width, y - radius, radius, 270);
/* 379 */     drawArc(x, y - radius, radius, 0);
/*     */ 
/*     */     
/* 382 */     GlStateManager.func_179098_w();
/* 383 */     GlStateManager.func_179084_k();
/*     */   }
/*     */   
/*     */   public static void enableGL2D() {
/* 387 */     GL11.glDisable(2929);
/* 388 */     GL11.glEnable(3042);
/* 389 */     GL11.glDisable(3553);
/* 390 */     GL11.glBlendFunc(770, 771);
/* 391 */     GL11.glDepthMask(true);
/* 392 */     GL11.glEnable(2848);
/* 393 */     GL11.glHint(3154, 4354);
/* 394 */     GL11.glHint(3155, 4354);
/*     */   }
/*     */   
/*     */   public static void disableGL2D() {
/* 398 */     GL11.glEnable(3553);
/* 399 */     GL11.glDisable(3042);
/* 400 */     GL11.glEnable(2929);
/* 401 */     GL11.glDisable(2848);
/* 402 */     GL11.glHint(3154, 4352);
/* 403 */     GL11.glHint(3155, 4352);
/*     */   }
/*     */   
/*     */   public static void drawArc(float x, float y, float radius, int angleStart) {
/* 407 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 408 */     WorldRenderer worldrenderer = tessellator.func_178180_c();
/*     */     
/* 410 */     worldrenderer.func_181668_a(6, DefaultVertexFormats.field_181705_e);
/*     */     
/* 412 */     GlStateManager.func_179137_b(x, y, 0.0D);
/* 413 */     worldrenderer.func_181662_b(0.0D, 0.0D, 0.0D).func_181675_d();
/* 414 */     int points = 21; double i;
/* 415 */     for (i = 0.0D; i < points; i++) {
/* 416 */       double radians = Math.toRadians(i / points * 90.0D + angleStart);
/* 417 */       worldrenderer.func_181662_b(radius * Math.sin(radians), radius * Math.cos(radians), 0.0D).func_181675_d();
/*     */     } 
/* 419 */     tessellator.func_78381_a();
/* 420 */     GlStateManager.func_179137_b(-x, -y, 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void tracerLine(double x, double y, double z, Color color) {
/* 425 */     x -= (Minecraft.func_71410_x().func_175598_ae()).field_78730_l;
/* 426 */     y -= (Minecraft.func_71410_x().func_175598_ae()).field_78731_m;
/* 427 */     z -= (Minecraft.func_71410_x().func_175598_ae()).field_78728_n;
/* 428 */     GL11.glBlendFunc(770, 771);
/* 429 */     GL11.glEnable(3042);
/* 430 */     GL11.glLineWidth(2.5F);
/* 431 */     GL11.glDisable(3553);
/* 432 */     GL11.glDisable(2929);
/* 433 */     GL11.glDepthMask(false);
/* 434 */     setColor(color);
/* 435 */     GL11.glBegin(1);
/*     */     
/* 437 */     GL11.glVertex3d(0.0D, (Minecraft.func_71410_x()).field_71439_g.func_70047_e(), 0.0D);
/* 438 */     GL11.glVertex3d(x, y, z);
/*     */     
/* 440 */     GL11.glEnd();
/* 441 */     GL11.glEnable(3553);
/* 442 */     GL11.glEnable(2929);
/* 443 */     GL11.glDepthMask(true);
/* 444 */     GL11.glDisable(3042);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawLine(Vec3 pos1, Vec3 pos2, Color color) {
/* 449 */     GL11.glPushMatrix();
/* 450 */     GL11.glBlendFunc(770, 771);
/* 451 */     GL11.glEnable(3042);
/* 452 */     GL11.glLineWidth(2.5F);
/* 453 */     GL11.glDisable(3553);
/* 454 */     GL11.glDisable(2929);
/* 455 */     GL11.glDepthMask(false);
/* 456 */     setColor(color);
/* 457 */     GL11.glTranslated(-(Minecraft.func_71410_x().func_175598_ae()).field_78730_l, -(Minecraft.func_71410_x().func_175598_ae()).field_78731_m, -(Minecraft.func_71410_x().func_175598_ae()).field_78728_n);
/* 458 */     GL11.glBegin(1);
/*     */     
/* 460 */     GL11.glVertex3d(pos1.field_72450_a, pos1.field_72448_b, pos1.field_72449_c);
/* 461 */     GL11.glVertex3d(pos2.field_72450_a, pos2.field_72448_b, pos2.field_72449_c);
/*     */     
/* 463 */     GL11.glEnd();
/* 464 */     GL11.glEnable(3553);
/* 465 */     GL11.glEnable(2929);
/* 466 */     GL11.glDepthMask(true);
/* 467 */     GL11.glDisable(3042);
/* 468 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setColor(Color c) {
/* 473 */     GL11.glColor4f(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, c
/* 474 */         .getAlpha() / 255.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void entityESPBox(Entity entity, float partialTicks, Color color) {
/* 479 */     GL11.glBlendFunc(770, 771);
/* 480 */     GL11.glEnable(3042);
/* 481 */     GL11.glLineWidth(1.5F);
/* 482 */     GL11.glDisable(3553);
/* 483 */     GL11.glDisable(2929);
/* 484 */     GL11.glDepthMask(false);
/* 485 */     setColor(color);
/* 486 */     RenderGlobal.func_181561_a(new AxisAlignedBB(
/*     */           
/* 488 */           (entity.func_174813_aQ()).field_72340_a - entity.field_70165_t + entity.field_70169_q + (entity.field_70165_t - entity.field_70169_q) * partialTicks - 
/*     */ 
/*     */           
/* 491 */           (Minecraft.func_71410_x().func_175598_ae()).field_78730_l, 
/* 492 */           (entity.func_174813_aQ()).field_72338_b - entity.field_70163_u + entity.field_70167_r + (entity.field_70163_u - entity.field_70167_r) * partialTicks - 
/*     */ 
/*     */           
/* 495 */           (Minecraft.func_71410_x().func_175598_ae()).field_78731_m, 
/* 496 */           (entity.func_174813_aQ()).field_72339_c - entity.field_70161_v + entity.field_70166_s + (entity.field_70161_v - entity.field_70166_s) * partialTicks - 
/*     */ 
/*     */           
/* 499 */           (Minecraft.func_71410_x().func_175598_ae()).field_78728_n, 
/* 500 */           (entity.func_174813_aQ()).field_72336_d - entity.field_70165_t + entity.field_70169_q + (entity.field_70165_t - entity.field_70169_q) * partialTicks - 
/*     */ 
/*     */           
/* 503 */           (Minecraft.func_71410_x().func_175598_ae()).field_78730_l, 
/* 504 */           (entity.func_174813_aQ()).field_72337_e - entity.field_70163_u + entity.field_70167_r + (entity.field_70163_u - entity.field_70167_r) * partialTicks - 
/*     */ 
/*     */           
/* 507 */           (Minecraft.func_71410_x().func_175598_ae()).field_78731_m, 
/* 508 */           (entity.func_174813_aQ()).field_72334_f - entity.field_70161_v + entity.field_70166_s + (entity.field_70161_v - entity.field_70166_s) * partialTicks - 
/*     */ 
/*     */           
/* 511 */           (Minecraft.func_71410_x().func_175598_ae()).field_78728_n));
/* 512 */     GL11.glEnable(3553);
/* 513 */     GL11.glEnable(2929);
/* 514 */     GL11.glDepthMask(true);
/* 515 */     GL11.glDisable(3042);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void blockBox(TileEntity block, Color color) {
/* 520 */     GL11.glBlendFunc(770, 771);
/* 521 */     GL11.glEnable(3042);
/* 522 */     GL11.glLineWidth(2.0F);
/* 523 */     GL11.glDisable(3553);
/* 524 */     GL11.glDisable(2929);
/* 525 */     GL11.glDepthMask(false);
/* 526 */     setColor(color);
/* 527 */     RenderGlobal.func_181561_a(block.getRenderBoundingBox().func_72317_d(-(OringoClient.mc.func_175598_ae()).field_78730_l, -(OringoClient.mc.func_175598_ae()).field_78731_m, -(OringoClient.mc.func_175598_ae()).field_78728_n));
/* 528 */     GL11.glEnable(3553);
/* 529 */     GL11.glEnable(2929);
/* 530 */     GL11.glDepthMask(true);
/* 531 */     GL11.glDisable(3042);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void blockBox(BlockPos block, Color color) {
/* 536 */     GL11.glBlendFunc(770, 771);
/* 537 */     GL11.glEnable(3042);
/* 538 */     GL11.glLineWidth(2.0F);
/* 539 */     GL11.glDisable(3553);
/* 540 */     GL11.glDisable(2929);
/* 541 */     GL11.glDepthMask(false);
/* 542 */     setColor(color);
/* 543 */     RenderGlobal.func_181561_a(new AxisAlignedBB(block
/*     */           
/* 545 */           .func_177958_n() - 
/*     */           
/* 547 */           (Minecraft.func_71410_x().func_175598_ae()).field_78730_l, block
/* 548 */           .func_177956_o() - 
/*     */           
/* 550 */           (Minecraft.func_71410_x().func_175598_ae()).field_78731_m, block
/* 551 */           .func_177952_p() - 
/*     */           
/* 553 */           (Minecraft.func_71410_x().func_175598_ae()).field_78728_n, (block
/* 554 */           .func_177958_n() + 1) - 
/*     */           
/* 556 */           (Minecraft.func_71410_x().func_175598_ae()).field_78730_l, (block
/* 557 */           .func_177956_o() + 1) - 
/*     */           
/* 559 */           (Minecraft.func_71410_x().func_175598_ae()).field_78731_m, (block
/* 560 */           .func_177952_p() + 1) - 
/*     */           
/* 562 */           (Minecraft.func_71410_x().func_175598_ae()).field_78728_n));
/* 563 */     GL11.glEnable(3553);
/* 564 */     GL11.glEnable(2929);
/* 565 */     GL11.glDepthMask(true);
/* 566 */     GL11.glDisable(3042);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void miniBlockBox(Vec3 block, Color color) {
/* 571 */     GL11.glBlendFunc(770, 771);
/* 572 */     GL11.glEnable(3042);
/* 573 */     GL11.glLineWidth(2.0F);
/* 574 */     GL11.glDisable(3553);
/* 575 */     GL11.glDisable(2929);
/* 576 */     GL11.glDepthMask(false);
/* 577 */     setColor(color);
/* 578 */     Minecraft.func_71410_x().func_175598_ae();
/* 579 */     RenderGlobal.func_181561_a(new AxisAlignedBB(block.field_72450_a - 0.05D - 
/*     */ 
/*     */ 
/*     */           
/* 583 */           (Minecraft.func_71410_x().func_175598_ae()).field_78730_l, block.field_72448_b - 0.05D - 
/*     */ 
/*     */           
/* 586 */           (Minecraft.func_71410_x().func_175598_ae()).field_78731_m, block.field_72449_c - 0.05D - 
/*     */ 
/*     */           
/* 589 */           (Minecraft.func_71410_x().func_175598_ae()).field_78728_n, block.field_72450_a + 0.05D - 
/*     */ 
/*     */           
/* 592 */           (Minecraft.func_71410_x().func_175598_ae()).field_78730_l, block.field_72448_b + 0.05D - 
/*     */ 
/*     */           
/* 595 */           (Minecraft.func_71410_x().func_175598_ae()).field_78731_m, block.field_72449_c + 0.05D - 
/*     */ 
/*     */           
/* 598 */           (Minecraft.func_71410_x().func_175598_ae()).field_78728_n));
/* 599 */     GL11.glEnable(3553);
/* 600 */     GL11.glEnable(2929);
/* 601 */     GL11.glDepthMask(true);
/* 602 */     GL11.glDisable(3042);
/*     */   }
/*     */   
/*     */   public static void drawBorderedRoundedRect(float x, float y, float width, float height, float radius, float linewidth, int insideC, int borderC) {
/* 606 */     drawRoundRect(x, y, x + width, y + height, radius, insideC);
/* 607 */     drawOutlinedRoundedRect(x, y, width, height, radius, linewidth, borderC);
/*     */   }
/*     */   
/*     */   public static void drawOutlinedRoundedRect(float x, float y, float width, float height, float radius, float linewidth, int color) {
/* 611 */     GlStateManager.func_179147_l();
/* 612 */     GlStateManager.func_179090_x();
/* 613 */     GlStateManager.func_179120_a(770, 771, 1, 0);
/* 614 */     double x1 = (x + width);
/* 615 */     double y1 = (y + height);
/* 616 */     float f = (color >> 24 & 0xFF) / 255.0F;
/* 617 */     float f1 = (color >> 16 & 0xFF) / 255.0F;
/* 618 */     float f2 = (color >> 8 & 0xFF) / 255.0F;
/* 619 */     float f3 = (color & 0xFF) / 255.0F;
/* 620 */     GL11.glPushAttrib(0);
/* 621 */     GL11.glScaled(0.5D, 0.5D, 0.5D);
/*     */     
/* 623 */     x *= 2.0F;
/* 624 */     y *= 2.0F;
/* 625 */     x1 *= 2.0D;
/* 626 */     y1 *= 2.0D;
/* 627 */     GL11.glLineWidth(linewidth);
/*     */     
/* 629 */     GL11.glDisable(3553);
/* 630 */     GL11.glColor4f(f1, f2, f3, f);
/* 631 */     GL11.glEnable(2848);
/* 632 */     GL11.glBegin(2);
/*     */     int i;
/* 634 */     for (i = 0; i <= 90; i += 3) {
/* 635 */       GL11.glVertex2d((x + radius) + Math.sin(i * Math.PI / 180.0D) * (radius * -1.0F), (y + radius) + Math.cos(i * Math.PI / 180.0D) * (radius * -1.0F));
/*     */     }
/*     */     
/* 638 */     for (i = 90; i <= 180; i += 3) {
/* 639 */       GL11.glVertex2d((x + radius) + Math.sin(i * Math.PI / 180.0D) * (radius * -1.0F), y1 - radius + Math.cos(i * Math.PI / 180.0D) * (radius * -1.0F));
/*     */     }
/*     */     
/* 642 */     for (i = 0; i <= 90; i += 3) {
/* 643 */       GL11.glVertex2d(x1 - radius + Math.sin(i * Math.PI / 180.0D) * radius, y1 - radius + Math.cos(i * Math.PI / 180.0D) * radius);
/*     */     }
/*     */     
/* 646 */     for (i = 90; i <= 180; i += 3) {
/* 647 */       GL11.glVertex2d(x1 - radius + Math.sin(i * Math.PI / 180.0D) * radius, (y + radius) + Math.cos(i * Math.PI / 180.0D) * radius);
/*     */     }
/*     */     
/* 650 */     GL11.glEnd();
/*     */     
/* 652 */     GL11.glEnable(3553);
/* 653 */     GL11.glDisable(2848);
/* 654 */     GL11.glEnable(3553);
/*     */     
/* 656 */     GL11.glScaled(2.0D, 2.0D, 2.0D);
/*     */     
/* 658 */     GL11.glPopAttrib();
/* 659 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 660 */     GlStateManager.func_179098_w();
/* 661 */     GlStateManager.func_179084_k();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void enableChams() {
/* 666 */     GL11.glEnable(32823);
/* 667 */     GlStateManager.func_179088_q();
/* 668 */     GlStateManager.func_179136_a(1.0F, -1000000.0F);
/*     */   }
/*     */   
/*     */   public static void disableChams() {
/* 672 */     GL11.glDisable(32823);
/* 673 */     GlStateManager.func_179136_a(1.0F, 1000000.0F);
/* 674 */     GlStateManager.func_179113_r();
/*     */   }
/*     */   
/*     */   public static void unForceColor() {
/* 678 */     MobRenderUtils.unsetColor();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderStarredNametag(Entity entityIn, String str, double x, double y, double z, int maxDistance) {
/* 683 */     double d0 = entityIn.func_70068_e((OringoClient.mc.func_175598_ae()).field_78734_h);
/*     */     
/* 685 */     if (d0 <= (maxDistance * maxDistance)) {
/*     */       
/* 687 */       FontRenderer fontrenderer = OringoClient.mc.func_175598_ae().func_78716_a();
/* 688 */       float f = 1.6F;
/* 689 */       float f1 = 0.016666668F * f;
/* 690 */       GlStateManager.func_179094_E();
/* 691 */       GlStateManager.func_179109_b((float)x + 0.0F, (float)y + entityIn.field_70131_O + 0.5F, (float)z);
/* 692 */       GL11.glNormal3f(0.0F, 1.0F, 0.0F);
/* 693 */       GlStateManager.func_179114_b(-(OringoClient.mc.func_175598_ae()).field_78735_i, 0.0F, 1.0F, 0.0F);
/* 694 */       GlStateManager.func_179114_b((OringoClient.mc.func_175598_ae()).field_78732_j, 1.0F, 0.0F, 0.0F);
/* 695 */       GlStateManager.func_179152_a(-f1, -f1, f1);
/* 696 */       GlStateManager.func_179140_f();
/* 697 */       GlStateManager.func_179132_a(false);
/* 698 */       GlStateManager.func_179097_i();
/* 699 */       GlStateManager.func_179147_l();
/* 700 */       GlStateManager.func_179120_a(770, 771, 1, 0);
/* 701 */       int i = 0;
/* 702 */       fontrenderer.func_78276_b(str, -fontrenderer.func_78256_a(str) / 2, i, -1);
/* 703 */       GlStateManager.func_179126_j();
/* 704 */       GlStateManager.func_179132_a(true);
/* 705 */       fontrenderer.func_78276_b(str, -fontrenderer.func_78256_a(str) / 2, i, -1);
/* 706 */       GlStateManager.func_179145_e();
/* 707 */       GlStateManager.func_179084_k();
/* 708 */       GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 709 */       GlStateManager.func_179121_F();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderLivingLabel(Entity entityIn, String str, double x, double y, double z, int maxDistance) {
/* 715 */     double d0 = entityIn.func_70068_e((OringoClient.mc.func_175598_ae()).field_78734_h);
/*     */     
/* 717 */     if (d0 <= (maxDistance * maxDistance)) {
/*     */       
/* 719 */       FontRenderer fontrenderer = OringoClient.mc.func_175598_ae().func_78716_a();
/* 720 */       float f = 1.6F;
/* 721 */       float f1 = 0.016666668F * f;
/* 722 */       GlStateManager.func_179094_E();
/* 723 */       GlStateManager.func_179109_b((float)x + 0.0F, (float)y + entityIn.field_70131_O + 0.5F, (float)z);
/* 724 */       GL11.glNormal3f(0.0F, 1.0F, 0.0F);
/* 725 */       GlStateManager.func_179114_b(-(OringoClient.mc.func_175598_ae()).field_78735_i, 0.0F, 1.0F, 0.0F);
/* 726 */       GlStateManager.func_179114_b((OringoClient.mc.func_175598_ae()).field_78732_j, 1.0F, 0.0F, 0.0F);
/* 727 */       GlStateManager.func_179152_a(-f1, -f1, f1);
/* 728 */       GlStateManager.func_179140_f();
/* 729 */       GlStateManager.func_179132_a(false);
/* 730 */       GlStateManager.func_179097_i();
/* 731 */       GlStateManager.func_179147_l();
/* 732 */       GlStateManager.func_179120_a(770, 771, 1, 0);
/* 733 */       Tessellator tessellator = Tessellator.func_178181_a();
/* 734 */       WorldRenderer worldrenderer = tessellator.func_178180_c();
/* 735 */       int i = 0;
/*     */       
/* 737 */       if (str.equals("deadmau5"))
/*     */       {
/* 739 */         i = -10;
/*     */       }
/*     */       
/* 742 */       int j = fontrenderer.func_78256_a(str) / 2;
/* 743 */       GlStateManager.func_179090_x();
/* 744 */       worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181706_f);
/* 745 */       worldrenderer.func_181662_b((-j - 1), (-1 + i), 0.0D).func_181666_a(0.0F, 0.0F, 0.0F, 0.25F).func_181675_d();
/* 746 */       worldrenderer.func_181662_b((-j - 1), (8 + i), 0.0D).func_181666_a(0.0F, 0.0F, 0.0F, 0.25F).func_181675_d();
/* 747 */       worldrenderer.func_181662_b((j + 1), (8 + i), 0.0D).func_181666_a(0.0F, 0.0F, 0.0F, 0.25F).func_181675_d();
/* 748 */       worldrenderer.func_181662_b((j + 1), (-1 + i), 0.0D).func_181666_a(0.0F, 0.0F, 0.0F, 0.25F).func_181675_d();
/* 749 */       tessellator.func_78381_a();
/* 750 */       GlStateManager.func_179098_w();
/* 751 */       fontrenderer.func_78276_b(str, -fontrenderer.func_78256_a(str) / 2, i, 553648127);
/* 752 */       GlStateManager.func_179126_j();
/* 753 */       GlStateManager.func_179132_a(true);
/* 754 */       fontrenderer.func_78276_b(str, -fontrenderer.func_78256_a(str) / 2, i, -1);
/* 755 */       GlStateManager.func_179145_e();
/* 756 */       GlStateManager.func_179084_k();
/* 757 */       GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 758 */       GlStateManager.func_179121_F();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Color glColor(int hex) {
/* 763 */     float alpha = (hex >> 24 & 0xFF) / 256.0F;
/* 764 */     float red = (hex >> 16 & 0xFF) / 255.0F;
/* 765 */     float green = (hex >> 8 & 0xFF) / 255.0F;
/* 766 */     float blue = (hex & 0xFF) / 255.0F;
/* 767 */     GL11.glColor4f(red, green, blue, alpha);
/* 768 */     return new Color(red, green, blue, alpha);
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclien\\utils\RenderUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */