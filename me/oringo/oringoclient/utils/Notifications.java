/*     */ package me.oringo.oringoclient.utils;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.function.Supplier;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.utils.font.Fonts;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Notifications
/*     */ {
/*  25 */   private static ArrayList<Notification> notifications = new ArrayList<>();
/*     */   
/*     */   public static EntityLivingBase entity;
/*  28 */   protected static final ResourceLocation inventoryBackground = new ResourceLocation("textures/gui/container/inventory.png");
/*  29 */   private static Minecraft mc = Minecraft.func_71410_x();
/*  30 */   private float lastHp = 0.8F;
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRender(RenderGameOverlayEvent.Post event) {
/*  34 */     if (event.type.equals(RenderGameOverlayEvent.ElementType.ALL)) {
/*  35 */       GL11.glPushMatrix();
/*  36 */       GL11.glScaled((2.0F / mc.field_71474_y.field_74335_Z), (2.0F / mc.field_71474_y.field_74335_Z), (2.0F / mc.field_71474_y.field_74335_Z));
/*  37 */       int scale = mc.field_71474_y.field_74335_Z;
/*  38 */       mc.field_71474_y.field_74335_Z = 2;
/*  39 */       notifications.removeIf(n -> (n.end <= System.currentTimeMillis()));
/*  40 */       if (notifications.size() > 0) {
/*     */         
/*  42 */         GL11.glEnable(2848);
/*  43 */         GL11.glEnable(2832);
/*     */         
/*  45 */         int i = notifications.size() - 1;
/*  46 */         for (Notification notification : notifications.clone()) {
/*     */           
/*  48 */           int t2 = 0;
/*     */           
/*  50 */           if (notification.end - System.currentTimeMillis() < 250L) {
/*  51 */             t2 = 250 - (int)(notification.end - System.currentTimeMillis());
/*     */           }
/*  53 */           t2 *= 2;
/*     */           
/*  55 */           int translateX = (int)Math.max(Math.max(0L, notification.end - (notification.time - 250) - System.currentTimeMillis()), t2);
/*     */           
/*  57 */           GL11.glTranslated(translateX, 0.0D, 0.0D);
/*     */           
/*  59 */           ScaledResolution resolution = new ScaledResolution(mc);
/*  60 */           drawRectWithShadow((resolution.func_78326_a() - 170), resolution.func_78328_b() - 50 * (i + 1), resolution.func_78326_a() - 20, resolution.func_78328_b() - 5 - 50 * i, 3.0F, (new Color(20, 20, 20, 200)).getRGB());
/*  61 */           Fonts.fontBig.drawSmoothString(notification.getTitle(), ((resolution.func_78326_a() - 165) + 0.4F), (resolution.func_78328_b() - 45 - 50 * i) + 0.5F, (new Color(20, 20, 20)).getRGB());
/*  62 */           Fonts.fontBig.drawSmoothString(notification.getTitle(), (resolution.func_78326_a() - 165), (resolution.func_78328_b() - 45 - 50 * i), OringoClient.clickGui.getColor().brighter().getRGB());
/*  63 */           int x = 1;
/*  64 */           for (String string : Fonts.fontMedium.wrapWords(notification.getDescription(), 140.0D)) {
/*  65 */             Fonts.fontMedium.drawSmoothString(string, ((resolution.func_78326_a() - 165) + 0.4F), (resolution.func_78328_b() - 40 - 50 * i + (Fonts.fontMedium.getHeight() + 2) * x) + 0.5F, (new Color(20, 20, 20)).getRGB());
/*  66 */             Fonts.fontMedium.drawSmoothString(string, (resolution.func_78326_a() - 165), (resolution.func_78328_b() - 40 - 50 * i + (Fonts.fontMedium.getHeight() + 2) * x), (new Color(231, 231, 231)).getRGB());
/*  67 */             x++;
/*     */           } 
/*  69 */           float time = (float)(notification.getEnd() - System.currentTimeMillis()) / notification.time;
/*  70 */           drawRectWith2Colors(resolution.func_78326_a() - 170, resolution.func_78328_b() - 7 - 50 * i, (int)((resolution.func_78326_a() - 170) + 150.0F * time), resolution.func_78328_b() - 5 - 50 * i, OringoClient.clickGui.getColor().getRGB(), OringoClient.clickGui.getColor().darker().darker().getRGB());
/*  71 */           i--;
/*     */           
/*  73 */           GL11.glTranslated(-translateX, 0.0D, 0.0D);
/*     */         } 
/*     */         
/*  76 */         GL11.glDisable(2848);
/*  77 */         GL11.glDisable(2832);
/*     */       } 
/*  79 */       mc.field_71474_y.field_74335_Z = scale;
/*  80 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  81 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawRectWithShadow(float x, int y, int x2, int y2, float radius, int color) {
/*  87 */     GL11.glBlendFunc(770, 771);
/*  88 */     RenderUtils.drawRoundRect(x, y, x2, y2, radius, color);
/*  89 */     int r = color >> 16 & 0xFF;
/*  90 */     int g = color >> 8 & 0xFF;
/*  91 */     int b = color & 0xFF;
/*  92 */     for (int i = 0; i < 3; i++) {
/*  93 */       RenderUtils.drawRoundRect(x - i, (y - i), (x2 + i), (y2 + i), 3.0F, (new Color(r, g, b, 30)).getRGB());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawRectWith2Colors(int left, int top, int right, int bottom, int color, int color2) {
/*  99 */     if (left < right) {
/*     */       
/* 101 */       int i = left;
/* 102 */       left = right;
/* 103 */       right = i;
/*     */     } 
/*     */     
/* 106 */     if (top < bottom) {
/*     */       
/* 108 */       int j = top;
/* 109 */       top = bottom;
/* 110 */       bottom = j;
/*     */     } 
/*     */     
/* 113 */     float f3 = (color >> 24 & 0xFF) / 255.0F;
/* 114 */     float f = (color >> 16 & 0xFF) / 255.0F;
/* 115 */     float f1 = (color >> 8 & 0xFF) / 255.0F;
/* 116 */     float f2 = (color & 0xFF) / 255.0F;
/* 117 */     float ff3 = (color2 >> 24 & 0xFF) / 255.0F;
/* 118 */     float ff = (color2 >> 16 & 0xFF) / 255.0F;
/* 119 */     float ff1 = (color2 >> 8 & 0xFF) / 255.0F;
/* 120 */     float ff2 = (color2 & 0xFF) / 255.0F;
/* 121 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 122 */     WorldRenderer worldrenderer = tessellator.func_178180_c();
/* 123 */     GlStateManager.func_179147_l();
/* 124 */     GlStateManager.func_179090_x();
/* 125 */     GlStateManager.func_179120_a(770, 771, 1, 0);
/* 126 */     GlStateManager.func_179103_j(7425);
/* 127 */     GlStateManager.func_179131_c(f, f1, f2, f3);
/* 128 */     worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181706_f);
/* 129 */     worldrenderer.func_181662_b(left, bottom, 0.0D).func_181666_a(ff, ff1, ff2, ff3).func_181675_d();
/* 130 */     worldrenderer.func_181662_b(right, bottom, 0.0D).func_181666_a(f, f1, f2, f3).func_181675_d();
/* 131 */     worldrenderer.func_181662_b(right, top, 0.0D).func_181666_a(f, f1, f2, f3).func_181675_d();
/* 132 */     worldrenderer.func_181662_b(left, top, 0.0D).func_181666_a(ff, ff1, ff2, ff3).func_181675_d();
/* 133 */     tessellator.func_78381_a();
/* 134 */     GlStateManager.func_179103_j(7424);
/* 135 */     GlStateManager.func_179098_w();
/* 136 */     GlStateManager.func_179084_k();
/*     */   }
/*     */   
/*     */   public static void showNotification(String title, Supplier<String> description, int time) {
/* 140 */     notifications.add(new Notification(title, description, time));
/*     */   }
/*     */   
/*     */   public static void showNotification(String title, String description, int time) {
/* 144 */     notifications.add(new Notification(title, description, time));
/*     */   }
/*     */   
/*     */   private static class Notification
/*     */   {
/*     */     private String title;
/*     */     private String description;
/* 151 */     private Supplier<String> desc = null;
/*     */     private long end;
/*     */     private int time;
/*     */     
/*     */     public Notification(String title, String description, int time) {
/* 156 */       this.title = title;
/* 157 */       this.description = description;
/* 158 */       this.end = System.currentTimeMillis() + time;
/* 159 */       this.time = time;
/*     */     }
/*     */     
/*     */     public Notification(String title, Supplier<String> desc, long end) {
/* 163 */       this.title = title;
/* 164 */       this.desc = desc;
/* 165 */       this.end = end;
/*     */     }
/*     */     
/*     */     public int getTime() {
/* 169 */       return this.time;
/*     */     }
/*     */     
/*     */     public String getTitle() {
/* 173 */       return this.title;
/*     */     }
/*     */     
/*     */     public String getDescription() {
/* 177 */       return (this.desc == null) ? this.description : this.desc.get();
/*     */     }
/*     */     
/*     */     public long getEnd() {
/* 181 */       return this.end;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclien\\utils\Notifications.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */