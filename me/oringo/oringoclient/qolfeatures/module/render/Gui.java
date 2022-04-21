/*     */ package me.oringo.oringoclient.qolfeatures.module.render;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.gui.ClickGUI;
/*     */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.ModeSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*     */ import me.oringo.oringoclient.utils.RenderUtils;
/*     */ import me.oringo.oringoclient.utils.SkyblockUtils;
/*     */ import me.oringo.oringoclient.utils.font.Fonts;
/*     */ import me.oringo.oringoclient.utils.shader.BlurUtils;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class Gui
/*     */   extends Module
/*     */ {
/*  33 */   public ClickGUI clickGUI = new ClickGUI();
/*     */   
/*  35 */   public ModeSetting colorMode = new ModeSetting("Color Mode", "Custom", new String[] { "Rainbow", "Color shift", "Custom" });
/*     */ 
/*     */   
/*  38 */   public NumberSetting redCustom = new NumberSetting("Red", 0.0D, 0.0D, 255.0D, 1.0D)
/*     */     {
/*     */       public boolean isHidden() {
/*  41 */         return !Gui.this.colorMode.is("Custom");
/*     */       }
/*     */     };
/*  44 */   public NumberSetting greenCustom = new NumberSetting("Green", 80.0D, 0.0D, 255.0D, 1.0D)
/*     */     {
/*     */       public boolean isHidden() {
/*  47 */         return !Gui.this.colorMode.is("Custom");
/*     */       }
/*     */     };
/*  50 */   public NumberSetting blueCustom = new NumberSetting("Blue", 255.0D, 0.0D, 255.0D, 1.0D)
/*     */     {
/*     */       public boolean isHidden() {
/*  53 */         return !Gui.this.colorMode.is("Custom");
/*     */       }
/*     */     };
/*     */   
/*  57 */   public NumberSetting redShift1 = new NumberSetting("Red 1", 0.0D, 0.0D, 255.0D, 1.0D)
/*     */     {
/*     */       public boolean isHidden() {
/*  60 */         return !Gui.this.colorMode.is("Color shift");
/*     */       }
/*     */     };
/*  63 */   public NumberSetting greenShift1 = new NumberSetting("Green 1", 80.0D, 0.0D, 255.0D, 1.0D)
/*     */     {
/*     */       public boolean isHidden() {
/*  66 */         return !Gui.this.colorMode.is("Color shift");
/*     */       }
/*     */     };
/*  69 */   public NumberSetting blueShift1 = new NumberSetting("Blue 1", 255.0D, 0.0D, 255.0D, 1.0D)
/*     */     {
/*     */       public boolean isHidden() {
/*  72 */         return !Gui.this.colorMode.is("Color shift");
/*     */       }
/*     */     };
/*  75 */   public NumberSetting redShift2 = new NumberSetting("Red 2", 0.0D, 0.0D, 255.0D, 1.0D)
/*     */     {
/*     */       public boolean isHidden() {
/*  78 */         return !Gui.this.colorMode.is("Color shift");
/*     */       }
/*     */     };
/*  81 */   public NumberSetting greenShift2 = new NumberSetting("Green 2", 80.0D, 0.0D, 255.0D, 1.0D)
/*     */     {
/*     */       public boolean isHidden() {
/*  84 */         return !Gui.this.colorMode.is("Color shift");
/*     */       }
/*     */     };
/*  87 */   public NumberSetting blueShift2 = new NumberSetting("Blue 2", 255.0D, 0.0D, 255.0D, 1.0D)
/*     */     {
/*     */       public boolean isHidden() {
/*  90 */         return !Gui.this.colorMode.is("Color shift");
/*     */       }
/*     */     };
/*     */   
/*  94 */   public NumberSetting shiftSpeed = new NumberSetting("Shift Speed", 2.5D, 1.0D, 5.0D, 0.1D)
/*     */     {
/*     */       public boolean isHidden() {
/*  97 */         return !Gui.this.colorMode.is("Color shift");
/*     */       }
/*     */     };
/*     */   
/* 101 */   public NumberSetting rgbSpeed = new NumberSetting("Rainbow Speed", 2.5D, 1.0D, 10.0D, 0.1D)
/*     */     {
/*     */       public boolean isHidden() {
/* 104 */         return (!Gui.this.colorMode.is("Rainbow") && !Gui.this.colorMode.is("Custom"));
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */   
/* 110 */   public ModeSetting blur = new ModeSetting("Blur strength", "Low", new String[] { "None", "Low", "High" });
/* 111 */   public BooleanSetting scaleGui = new BooleanSetting("Scale gui", false), arrayList = new BooleanSetting("ArrayList", true), disableNotifs = new BooleanSetting("Disable notifications", false), arrayBlur = new BooleanSetting("Array background", true), arrayOutline = new BooleanSetting("Array line", true);
/*     */ 
/*     */ 
/*     */   
/*     */   public Gui() {
/* 116 */     super("Click Gui", 54, Module.Category.RENDER);
/* 117 */     addSettings(new Setting[] { (Setting)this.colorMode, (Setting)this.rgbSpeed, (Setting)this.redCustom, (Setting)this.greenCustom, (Setting)this.blueCustom, (Setting)this.shiftSpeed, (Setting)this.redShift1, (Setting)this.greenShift1, (Setting)this.blueShift1, (Setting)this.redShift2, (Setting)this.greenShift2, (Setting)this.blueShift2, (Setting)this.blur, (Setting)this.arrayList, (Setting)this.arrayOutline, (Setting)this.arrayBlur, (Setting)this.disableNotifs, (Setting)this.scaleGui });
/*     */   }
/*     */   public Color getColor() {
/*     */     float location;
/* 121 */     switch (this.colorMode.getSelected()) {
/*     */       case "Color shift":
/* 123 */         location = (float)((Math.sin(System.currentTimeMillis() * this.shiftSpeed.getValue() * 0.001D) + 1.0D) * 0.5D);
/* 124 */         return new Color((int)(this.redShift1.getValue() + (this.redShift2.getValue() - this.redShift1.getValue()) * location), 
/* 125 */             (int)(this.greenShift1.getValue() + (this.greenShift2.getValue() - this.greenShift1.getValue()) * location), 
/* 126 */             (int)(this.blueShift1.getValue() + (this.blueShift2.getValue() - this.blueShift1.getValue()) * location));
/*     */       
/*     */       case "Rainbow":
/* 129 */         return SkyblockUtils.rainbow((int)(this.rgbSpeed.getValue() * 25.0D));
/*     */     } 
/* 131 */     Color baseColor = new Color((int)this.redCustom.getValue(), (int)this.greenCustom.getValue(), (int)this.blueCustom.getValue(), 255);
/* 132 */     return RenderUtils.interpolateColor(baseColor, baseColor.darker().darker(), (float)((Math.sin(System.currentTimeMillis() * this.shiftSpeed.getValue() * 0.001D) + 1.0D) * 0.5D));
/*     */   }
/*     */   
/*     */   public Color getColor(int i) {
/*     */     float location;
/* 137 */     switch (this.colorMode.getSelected()) {
/*     */       case "Color shift":
/* 139 */         location = (float)((Math.sin((i * 450.0D + System.currentTimeMillis() * this.shiftSpeed.getValue()) / 1000.0D) + 1.0D) * 0.5D);
/* 140 */         return new Color((int)(this.redShift1.getValue() + (this.redShift2.getValue() - this.redShift1.getValue()) * location), 
/* 141 */             (int)(this.greenShift1.getValue() + (this.greenShift2.getValue() - this.greenShift1.getValue()) * location), 
/* 142 */             (int)(this.blueShift1.getValue() + (this.blueShift2.getValue() - this.blueShift1.getValue()) * location));
/*     */       case "Rainbow":
/* 144 */         return Color.getHSBColor((float)((i * 100.0D + System.currentTimeMillis()) / 2500.0D % 1.0D), 1.0F, 1.0F);
/*     */     } 
/* 146 */     Color baseColor = new Color((int)this.redCustom.getValue(), (int)this.greenCustom.getValue(), (int)this.blueCustom.getValue(), 255);
/* 147 */     return RenderUtils.interpolateColor(baseColor, baseColor.darker().darker(), (float)((Math.sin((i * 450.0D + System.currentTimeMillis() * this.shiftSpeed.getValue()) / 1000.0D) + 1.0D) * 0.5D));
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRender(RenderGameOverlayEvent.Post event) {
/* 153 */     if (event.type == RenderGameOverlayEvent.ElementType.CROSSHAIRS && this.arrayList.isEnabled()) {
/* 154 */       GL11.glPushMatrix();
/* 155 */       List<Module> list = (List<Module>)OringoClient.modules.stream().filter(module -> (module.getCategory() != Module.Category.RENDER && module.getCategory() != Module.Category.KEYBINDS && (module.isToggled() || module.toggledTime.getTimePassed() <= 250L))).sorted(Comparator.comparingDouble(module -> Fonts.openSans.getStringWidth(module.getName()))).collect(Collectors.toList());
/* 156 */       Collections.reverse(list);
/* 157 */       ScaledResolution resolution = new ScaledResolution(OringoClient.mc);
/* 158 */       float y = 2.0F;
/* 159 */       int x = list.size();
/* 160 */       for (Module module : list) {
/* 161 */         x--;
/* 162 */         GL11.glPushMatrix();
/* 163 */         float width = (float)(Fonts.openSans.getStringWidth(module.getName()) + 5.0D);
/* 164 */         GL11.glTranslated((width * Math.max(Math.min(module.isToggled() ? ((250.0F - (float)module.toggledTime.getTimePassed()) / 250.0F) : ((float)module.toggledTime.getTimePassed() / 250.0F), 1.0F), 0.0F)), 0.0D, 0.0D);
/* 165 */         float height = (Fonts.openSans.getHeight() + 5);
/* 166 */         if (this.arrayBlur.isEnabled()) {
/* 167 */           float i; for (i = 0.0F; i < 3.0F; i += 0.5F) {
/* 168 */             RenderUtils.drawRect((resolution.func_78326_a() - 1) - width - i, y + i, resolution.func_78326_a(), y + (Fonts.openSans.getHeight() + 5.0F) * Math.max(Math.min(module.isToggled() ? ((float)module.toggledTime.getTimePassed() / 250.0F) : ((250.0F - (float)module.toggledTime.getTimePassed()) / 250.0F), 1.0F), 0.0F) + i, (new Color(20, 20, 20, 30)).getRGB());
/*     */           }
/* 170 */           BlurUtils.renderBlurredBackground(20.0F, resolution.func_78326_a(), resolution.func_78328_b(), (resolution.func_78326_a() - 1) - width, y, width, height);
/* 171 */           RenderUtils.drawRect((resolution.func_78326_a() - 1) - width, y, (resolution.func_78326_a() - 1), y + height, (new Color(19, 19, 19, 70)).getRGB());
/*     */         } 
/* 173 */         Fonts.openSans.drawSmoothCenteredString(module.getName(), (resolution.func_78326_a() - 1) - width / 2.0F + 0.4F, y + height / 2.0F - Fonts.openSans.getHeight() / 2.0F + 0.5F, (new Color(20, 20, 20)).getRGB());
/* 174 */         Fonts.openSans.drawSmoothCenteredString(module.getName(), (resolution.func_78326_a() - 1) - width / 2.0F, y + height / 2.0F - Fonts.openSans.getHeight() / 2.0F, getColor(x).brighter().getRGB());
/* 175 */         y += (Fonts.openSans.getHeight() + 5) * Math.max(Math.min(module.isToggled() ? ((float)module.toggledTime.getTimePassed() / 250.0F) : ((250.0F - (float)module.toggledTime.getTimePassed()) / 250.0F), 1.0F), 0.0F);
/* 176 */         GL11.glPopMatrix();
/*     */       } 
/* 178 */       x = list.size();
/* 179 */       y = 2.0F;
/* 180 */       if (this.arrayOutline.isEnabled()) {
/* 181 */         Tessellator tessellator = Tessellator.func_178181_a();
/* 182 */         WorldRenderer worldrenderer = tessellator.func_178180_c();
/* 183 */         GlStateManager.func_179147_l();
/* 184 */         GlStateManager.func_179090_x();
/* 185 */         GlStateManager.func_179120_a(770, 771, 1, 0);
/* 186 */         GlStateManager.func_179103_j(7425);
/* 187 */         worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181706_f);
/* 188 */         for (Module module : list) {
/* 189 */           x--;
/* 190 */           float height = (Fonts.openSans.getHeight() + 5.0F) * Math.max(Math.min(module.isToggled() ? ((float)module.toggledTime.getTimePassed() / 250.0F) : ((250.0F - (float)module.toggledTime.getTimePassed()) / 250.0F), 1.0F), 0.0F);
/*     */           
/* 192 */           addVertex((resolution.func_78326_a() - 1), y, resolution.func_78326_a(), y + height, getColor(x).getRGB(), getColor(x + 1).getRGB());
/* 193 */           y += height;
/*     */         } 
/* 195 */         tessellator.func_78381_a();
/* 196 */         GlStateManager.func_179103_j(7424);
/*     */       } 
/* 198 */       GlStateManager.func_179098_w();
/* 199 */       GlStateManager.func_179084_k();
/* 200 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 201 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void addVertex(float left, float top, float right, float bottom, int color, int color2) {
/* 207 */     if (left < right) {
/*     */       
/* 209 */       float i = left;
/* 210 */       left = right;
/* 211 */       right = i;
/*     */     } 
/*     */     
/* 214 */     if (top < bottom) {
/*     */       
/* 216 */       float j = top;
/* 217 */       top = bottom;
/* 218 */       bottom = j;
/*     */     } 
/*     */     
/* 221 */     float f3 = (color >> 24 & 0xFF) / 255.0F;
/* 222 */     float f = (color >> 16 & 0xFF) / 255.0F;
/* 223 */     float f1 = (color >> 8 & 0xFF) / 255.0F;
/* 224 */     float f2 = (color & 0xFF) / 255.0F;
/* 225 */     float ff3 = (color2 >> 24 & 0xFF) / 255.0F;
/* 226 */     float ff = (color2 >> 16 & 0xFF) / 255.0F;
/* 227 */     float ff1 = (color2 >> 8 & 0xFF) / 255.0F;
/* 228 */     float ff2 = (color2 & 0xFF) / 255.0F;
/* 229 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 230 */     WorldRenderer worldrenderer = tessellator.func_178180_c();
/* 231 */     worldrenderer.func_181662_b(left, bottom, 0.0D).func_181666_a(ff, ff1, ff2, ff3).func_181675_d();
/* 232 */     worldrenderer.func_181662_b(right, bottom, 0.0D).func_181666_a(ff, ff1, ff2, ff3).func_181675_d();
/* 233 */     worldrenderer.func_181662_b(right, top, 0.0D).func_181666_a(f, f1, f2, f3).func_181675_d();
/* 234 */     worldrenderer.func_181662_b(left, top, 0.0D).func_181666_a(f, f1, f2, f3).func_181675_d();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getHeight() {
/* 239 */     if (!this.arrayList.isEnabled()) return 0.0F; 
/* 240 */     List<Module> list = (List<Module>)OringoClient.modules.stream().filter(module -> (module.getCategory() != Module.Category.RENDER && module.getCategory() != Module.Category.KEYBINDS && (module.isToggled() || module.toggledTime.getTimePassed() <= 250L))).sorted(Comparator.comparingDouble(module -> Fonts.openSans.getStringWidth(module.getName()))).collect(Collectors.toList());
/* 241 */     float y = 3.0F;
/* 242 */     for (Module module : list) {
/* 243 */       y += (Fonts.openSans.getHeight() + 5.0F) * Math.max(Math.min(module.isToggled() ? ((float)module.toggledTime.getTimePassed() / 250.0F) : ((250.0F - (float)module.toggledTime.getTimePassed()) / 250.0F), 1.0F), 0.0F);
/*     */     }
/* 245 */     return y;
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onTick(TickEvent.ClientTickEvent event) {
/* 251 */     if (isToggled()) {
/* 252 */       OringoClient.mc.func_147108_a((GuiScreen)this.clickGUI);
/* 253 */       toggle();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\render\Gui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */