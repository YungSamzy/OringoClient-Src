/*     */ package me.oringo.oringoclient.gui;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.config.ConfigManager;
/*     */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*     */ import me.oringo.oringoclient.qolfeatures.module.keybinds.Keybind;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.ModeSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.RunnableSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.StringSetting;
/*     */ import me.oringo.oringoclient.utils.RenderUtils;
/*     */ import me.oringo.oringoclient.utils.font.Fonts;
/*     */ import me.oringo.oringoclient.utils.shader.BlurUtils;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.util.ChatAllowedCharacters;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class ClickGUI
/*     */   extends GuiScreen {
/*     */   public static ArrayList<Panel> panels;
/*  33 */   private Module binding = null;
/*     */   
/*     */   private Panel draggingPanel;
/*     */   private NumberSetting draggingSlider;
/*     */   private StringSetting settingString;
/*     */   private float startX;
/*     */   private float startY;
/*     */   public int guiScale;
/*  41 */   private static int background = Color.getHSBColor(0.0F, 0.0F, 0.1F).getRGB();
/*     */   
/*     */   public ClickGUI() {
/*  44 */     panels = new ArrayList<>();
/*  45 */     int pwidth = 100;
/*  46 */     int pheight = 20;
/*  47 */     int px = 100;
/*  48 */     int py = 10;
/*  49 */     for (Module.Category c : Module.Category.values()) {
/*  50 */       panels.add(new Panel(c, px, py, pwidth, pheight));
/*  51 */       px += pwidth + 10;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_73864_a(int mouseX, int mouseY, int mouseButton) {
/*  57 */     this.draggingSlider = null;
/*  58 */     this.draggingPanel = null;
/*  59 */     this.settingString = null;
/*  60 */     this.binding = null;
/*  61 */     handle(mouseX, mouseY, mouseButton, 0.0F, Handle.CLICK);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_146286_b(int mouseX, int mouseY, int state) {
/*  66 */     ConfigManager.saveConfig();
/*  67 */     Keybind.saveKeybinds();
/*  68 */     handle(mouseX, mouseY, state, 0.0F, Handle.RELEASE);
/*  69 */     super.func_146286_b(mouseX, mouseY, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
/*  74 */     handle(mouseX, mouseY, -1, partialTicks, Handle.DRAW);
/*  75 */     super.func_73863_a(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_73869_a(char typedChar, int keyCode) throws IOException {
/*  82 */     ConfigManager.saveConfig();
/*  83 */     Keybind.saveKeybinds();
/*  84 */     if (keyCode == 1 || keyCode == OringoClient.clickGui.getKeycode()) {
/*  85 */       if (this.binding != null) {
/*  86 */         this.binding.setKeycode(0);
/*  87 */         this.binding = null;
/*  88 */       } else if (this.settingString != null) {
/*  89 */         this.settingString = null;
/*     */       } else {
/*  91 */         this.draggingPanel = null;
/*  92 */         this.draggingSlider = null;
/*  93 */         super.func_73869_a(typedChar, keyCode);
/*     */       }
/*     */     
/*  96 */     } else if (this.binding != null) {
/*  97 */       this.binding.setKeycode(keyCode);
/*  98 */       this.binding = null;
/*  99 */     } else if (this.settingString != null) {
/* 100 */       if (keyCode == 28) {
/* 101 */         this.settingString = null;
/* 102 */       } else if (keyCode == 47 && (Keyboard.isKeyDown(157) || Keyboard.isKeyDown(29))) {
/* 103 */         this.settingString.setValue(ChatAllowedCharacters.func_71565_a(this.settingString.getValue() + func_146277_j()));
/* 104 */       } else if (keyCode != 14) {
/* 105 */         this.settingString.setValue(ChatAllowedCharacters.func_71565_a(this.settingString.getValue() + typedChar));
/*     */       } else {
/* 107 */         this.settingString.setValue(this.settingString.getValue().substring(0, Math.max(0, this.settingString.getValue().length() - 1)));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void handle(int mouseX, int mouseY, int key, float partialTicks, Handle handle) {
/* 114 */     int toggled = OringoClient.clickGui.getColor().getRGB();
/* 115 */     float scale = 2.0F / this.field_146297_k.field_71474_y.field_74335_Z;
/* 116 */     int prevScale = this.field_146297_k.field_71474_y.field_74335_Z;
/* 117 */     GL11.glPushMatrix();
/* 118 */     if (this.field_146297_k.field_71474_y.field_74335_Z > 2 && !OringoClient.clickGui.scaleGui.isEnabled()) {
/* 119 */       this.field_146297_k.field_71474_y.field_74335_Z = 2;
/* 120 */       mouseX = (int)(mouseX / scale);
/* 121 */       mouseY = (int)(mouseY / scale);
/* 122 */       GL11.glScaled(scale, scale, scale);
/*     */     } 
/* 124 */     if (handle == Handle.DRAW) {
/* 125 */       int blur = 0;
/* 126 */       switch (OringoClient.clickGui.blur.getSelected()) {
/*     */         case "Low":
/* 128 */           blur = 4;
/*     */           break;
/*     */         case "High":
/* 131 */           blur = 7;
/*     */           break;
/*     */       } 
/* 134 */       ScaledResolution resolution = new ScaledResolution(this.field_146297_k);
/* 135 */       BlurUtils.renderBlurredBackground(blur, resolution.func_78326_a(), resolution.func_78328_b(), 0.0F, 0.0F, resolution.func_78326_a(), resolution.func_78328_b());
/*     */     } 
/* 137 */     for (Panel p : panels) {
/* 138 */       switch (handle) {
/*     */         case DRAW:
/* 140 */           if (this.draggingPanel == p) {
/* 141 */             p.x = this.startX + mouseX;
/* 142 */             p.y = this.startY + mouseY;
/*     */           } 
/*     */         case CLICK:
/* 145 */           if (!isHovered(mouseX, mouseY, p.x, p.y, p.height, p.width))
/* 146 */             break;  if (key == 1) {
/* 147 */             this.startX = p.x - mouseX;
/* 148 */             this.startY = p.y - mouseY;
/* 149 */             this.draggingPanel = p;
/* 150 */             this.draggingSlider = null; break;
/* 151 */           }  if (key == 0) {
/* 152 */             p.extended = !p.extended;
/*     */           }
/*     */           break;
/*     */         case RELEASE:
/* 156 */           this.draggingPanel = null;
/* 157 */           this.draggingSlider = null;
/*     */           break;
/*     */       } 
/* 160 */       float y = p.y + p.height + 3.0F;
/* 161 */       if (p.extended) {
/* 162 */         int moduleHeight = 15;
/* 163 */         List<Module> list = (List<Module>)Module.getModulesByCategory(p.category).stream().sorted(Comparator.comparing(module -> Double.valueOf(Fonts.fontMediumBold.getStringWidth(module.getName())))).collect(Collectors.toList());
/* 164 */         Collections.reverse(list);
/* 165 */         for (Module module : list) {
/* 166 */           if (module.isDevOnly() && !OringoClient.devMode)
/* 167 */             continue;  switch (handle) {
/*     */             case DRAW:
/* 169 */               RenderUtils.drawRect(p.x, y, p.x + p.width, y + moduleHeight, module.isToggled() ? (new Color(toggled)).getRGB() : background);
/* 170 */               Fonts.fontMediumBold.drawSmoothCenteredString(module.getName(), p.x + p.width / 2.0F, y + moduleHeight / 2.0F - Fonts.fontMediumBold.getHeight() / 2.0F, Color.white.getRGB());
/*     */               break;
/*     */             case CLICK:
/* 173 */               if (!isHovered(mouseX, mouseY, p.x, y, moduleHeight, p.width))
/* 174 */                 break;  switch (key) {
/*     */                 case 1:
/* 176 */                   module.extended = !module.extended;
/*     */                   break;
/*     */                 case 0:
/* 179 */                   module.toggle();
/*     */                   break;
/*     */               } 
/*     */               break;
/*     */           } 
/* 184 */           y += moduleHeight;
/* 185 */           if (module.extended) {
/* 186 */             for (Setting setting : module.settings) {
/* 187 */               if (setting.isHidden())
/* 188 */                 continue;  if (setting instanceof ModeSetting) {
/* 189 */                 switch (handle) {
/*     */                   case DRAW:
/* 191 */                     RenderUtils.drawRect(p.x, y, p.x + p.width, y + moduleHeight, (new Color(background)).brighter().getRGB());
/* 192 */                     Fonts.fontMediumBold.drawSmoothString(setting.name, (p.x + 2.0F), y + moduleHeight / 2.0F - Fonts.fontMediumBold.getHeight() / 2.0F, Color.white.getRGB());
/* 193 */                     Fonts.fontMediumBold.drawSmoothString(((ModeSetting)setting).getSelected(), (p.x + p.width) - Fonts.fontMediumBold.getStringWidth(((ModeSetting)setting).getSelected()) - 2.0D, y + moduleHeight / 2.0F - Fonts.fontMediumBold.getHeight() / 2.0F, (new Color(143, 143, 143, 255)).getRGB());
/*     */                     break;
/*     */                   case CLICK:
/* 196 */                     if (!isHovered(mouseX, mouseY, (p.x + p.width) - Fonts.fontMediumBold.getStringWidth(((ModeSetting)setting).getSelected()) - 2.0D, y, moduleHeight, Fonts.fontMediumBold.getStringWidth(((ModeSetting)setting).getSelected())))
/* 197 */                       break;  ((ModeSetting)setting).cycle(key);
/*     */                     break;
/*     */                 } 
/*     */               
/* 201 */               } else if (setting instanceof NumberSetting) {
/* 202 */                 float percent = (float)((((NumberSetting)setting).getValue() - ((NumberSetting)setting).getMin()) / (((NumberSetting)setting).getMax() - ((NumberSetting)setting).getMin()));
/* 203 */                 float numberWidth = percent * p.width;
/*     */                 
/* 205 */                 if (this.draggingSlider != null && this.draggingSlider == setting) {
/* 206 */                   double mousePercent = Math.min(1.0F, Math.max(0.0F, (mouseX - p.x) / p.width));
/* 207 */                   double newValue = mousePercent * (((NumberSetting)setting).getMax() - ((NumberSetting)setting).getMin()) + ((NumberSetting)setting).getMin();
/* 208 */                   ((NumberSetting)setting).setValue(newValue);
/*     */                 } 
/* 210 */                 switch (handle) {
/*     */                   case DRAW:
/* 212 */                     RenderUtils.drawRect(p.x, y, p.x + p.width, y + moduleHeight, (new Color(background)).brighter().brighter().getRGB());
/* 213 */                     RenderUtils.drawRect(p.x, y, p.x + numberWidth, y + moduleHeight, (new Color(toggled)).brighter().getRGB());
/* 214 */                     Fonts.fontMediumBold.drawSmoothString(setting.name, (p.x + 2.0F), y + moduleHeight / 2.0F - Fonts.fontMediumBold.getHeight() / 2.0F, Color.white.getRGB());
/* 215 */                     Fonts.fontMediumBold.drawSmoothString(String.valueOf(((NumberSetting)setting).getValue()), (p.x + p.width - 2.0F) - Fonts.fontMediumBold.getStringWidth(String.valueOf(((NumberSetting)setting).getValue())), y + moduleHeight / 2.0F - Fonts.fontBig.getHeight() / 2.0F, Color.white.getRGB());
/*     */                     break;
/*     */                   case CLICK:
/* 218 */                     if (!isHovered(mouseX, mouseY, p.x, y, moduleHeight, p.width))
/* 219 */                       break;  this.draggingSlider = (NumberSetting)setting;
/* 220 */                     this.draggingPanel = null;
/*     */                     break;
/*     */                 } 
/* 223 */               } else if (setting instanceof BooleanSetting) {
/* 224 */                 switch (handle) {
/*     */                   case DRAW:
/* 226 */                     RenderUtils.drawRect(p.x, y, p.x + p.width, y + moduleHeight, (new Color(background)).brighter().getRGB());
/*     */                     
/* 228 */                     RenderUtils.drawBorderedRoundedRect(p.x + p.width - 12.0F, y + moduleHeight / 2.0F - 4.0F, 8.0F, 8.0F, 3.0F, 3.0F, ((BooleanSetting)setting).isEnabled() ? (new Color(toggled)).brighter().getRGB() : (new Color(background)).brighter().getRGB(), (new Color(toggled)).brighter().getRGB());
/* 229 */                     Fonts.fontMediumBold.drawSmoothString(setting.name, (p.x + 2.0F), y + moduleHeight / 2.0F - Fonts.fontMediumBold.getHeight() / 2.0F, Color.white.getRGB());
/*     */                     break;
/*     */                   case CLICK:
/* 232 */                     if (!isHovered(mouseX, mouseY, (p.x + p.width - 12.0F), (y + moduleHeight / 2.0F - 4.0F), 8.0D, 8.0D))
/* 233 */                       break;  ((BooleanSetting)setting).toggle();
/*     */                     break;
/*     */                 } 
/* 236 */               } else if (setting instanceof StringSetting) {
/* 237 */                 String value = (this.settingString == setting) ? (((StringSetting)setting).getValue() + "_") : ((((StringSetting)setting).getValue() == null || ((StringSetting)setting).getValue().equals("")) ? (setting.name + "...") : Fonts.fontMediumBold.trimStringToWidth(((StringSetting)setting).getValue(), (int)p.width));
/* 238 */                 switch (handle) {
/*     */                   case DRAW:
/* 240 */                     RenderUtils.drawRect(p.x, y, p.x + p.width, y + moduleHeight, (new Color(background)).brighter().getRGB());
/* 241 */                     Fonts.fontMediumBold.drawCenteredString(value, p.x + p.width / 2.0F, y + moduleHeight / 2.0F - Fonts.fontMediumBold.getHeight() / 2.0F, (((StringSetting)setting).getValue() == null || (((StringSetting)setting).getValue().equals("") && this.settingString != setting)) ? (new Color(143, 143, 143, 255)).getRGB() : Color.white.getRGB());
/*     */                     break;
/*     */                   case CLICK:
/* 244 */                     if (!isHovered(mouseX, mouseY, p.x, y, moduleHeight, p.width))
/* 245 */                       break;  switch (key) {
/*     */                       case 0:
/* 247 */                         this.settingString = (StringSetting)setting;
/*     */                         break;
/*     */                       case 1:
/* 250 */                         ((StringSetting)setting).setValue(""); break;
/*     */                     } 
/*     */                     break;
/*     */                 } 
/* 254 */               } else if (setting instanceof RunnableSetting) {
/* 255 */                 switch (handle) {
/*     */                   case CLICK:
/* 257 */                     if (!isHovered(mouseX, mouseY, p.x, y, moduleHeight, p.width))
/* 258 */                       break;  ((RunnableSetting)setting).execute();
/*     */                     break;
/*     */                   case DRAW:
/* 261 */                     RenderUtils.drawRect(p.x, y, p.x + p.width, y + moduleHeight, (new Color(background)).brighter().getRGB());
/* 262 */                     Fonts.fontMediumBold.drawCenteredString(setting.name, p.x + p.width / 2.0F, y + moduleHeight / 2.0F - Fonts.fontMediumBold.getHeight() / 2.0F, Color.white.getRGB());
/*     */                     break;
/*     */                 } 
/*     */               } 
/* 266 */               y += moduleHeight;
/*     */             } 
/* 268 */             String keyText = (this.binding == module) ? "[...]" : ("[" + ((module.getKeycode() >= 256) ? "  " : Keyboard.getKeyName(module.getKeycode()).replaceAll("NONE", "  ")) + "]");
/* 269 */             switch (handle) {
/*     */               case DRAW:
/* 271 */                 RenderUtils.drawRect(p.x, y, p.x + p.width, y + moduleHeight, (new Color(background)).brighter().getRGB());
/* 272 */                 Fonts.fontMediumBold.drawSmoothString("Key", (p.x + 2.0F), y + moduleHeight / 2.0F - Fonts.fontMediumBold.getHeight() / 2.0F, Color.white.getRGB());
/* 273 */                 Fonts.fontMediumBold.drawSmoothString(keyText, (p.x + p.width) - Fonts.fontMediumBold.getStringWidth(keyText) - 2.0D, y + moduleHeight / 2.0F - Fonts.fontMediumBold.getHeight() / 2.0F, (new Color(143, 143, 143, 255)).getRGB());
/*     */                 break;
/*     */               case CLICK:
/* 276 */                 if (!isHovered(mouseX, mouseY, (p.x + p.width) - Fonts.fontMediumBold.getStringWidth(keyText) - 2.0D, y, moduleHeight, Fonts.fontMediumBold.getStringWidth(keyText)))
/* 277 */                   break;  switch (key) {
/*     */                   case 0:
/* 279 */                     this.binding = module;
/*     */                     break;
/*     */                   case 1:
/* 282 */                     module.setKeycode(0); break;
/*     */                 } 
/*     */                 break;
/*     */             } 
/* 286 */             y += moduleHeight;
/*     */           } 
/*     */         } 
/*     */       } 
/* 290 */       if (p.category == Module.Category.KEYBINDS && p.extended) {
/* 291 */         Keybind keybind; switch (handle) {
/*     */           case CLICK:
/* 293 */             if (!isHovered(mouseX, mouseY, p.x, y, 15.0D, p.width))
/* 294 */               break;  keybind = new Keybind("Keybind " + (Module.getModulesByCategory(Module.Category.KEYBINDS).size() + 1));
/* 295 */             OringoClient.modules.add(keybind);
/* 296 */             MinecraftForge.EVENT_BUS.register(keybind);
/* 297 */             keybind.setToggled(true);
/*     */             break;
/*     */           case DRAW:
/* 300 */             RenderUtils.drawRect(p.x, y, p.x + p.width, y + 15.0F, toggled);
/* 301 */             Fonts.fontMediumBold.drawSmoothCenteredString("Add new keybind", p.x + p.width / 2.0F, y + 7.0F - (Fonts.fontMediumBold.getHeight() / 2), Color.white.getRGB());
/*     */             break;
/*     */         } 
/* 304 */         y += 15.0F;
/*     */       } 
/* 306 */       if (handle == Handle.DRAW) {
/* 307 */         if (p.extended) {
/* 308 */           RenderUtils.drawRect(p.x, p.y + 3.0F, p.x + p.width, p.y + p.height + 3.0F, (new Color(toggled)).getRGB());
/* 309 */           for (int i = 1; i < 4; i++)
/* 310 */             RenderUtils.drawRect(p.x, p.y + i, p.x + p.width, p.y + p.height + i, (new Color(0, 0, 0, 40)).getRGB()); 
/*     */         } else {
/* 312 */           y = p.y;
/* 313 */         }  RenderUtils.drawRect(p.x, p.y, p.x + p.width, p.y + p.height, (new Color(toggled)).darker().getRGB());
/* 314 */         Fonts.fontBig.drawSmoothCenteredString(p.category.name, p.x + p.width / 2.0F, p.y + p.height / 2.0F - Fonts.fontBig.getHeight() / 2.0F, Color.white.getRGB());
/* 315 */         RenderUtils.drawRect(p.x, y, p.x + p.width, y + 5.0F, (new Color(toggled)).darker().getRGB());
/*     */       } 
/*     */     } 
/* 318 */     GL11.glPopMatrix();
/* 319 */     this.field_146297_k.field_71474_y.field_74335_Z = prevScale;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146281_b() {
/* 324 */     this.draggingPanel = null;
/* 325 */     this.draggingSlider = null;
/* 326 */     this.binding = null;
/* 327 */     this.settingString = null;
/* 328 */     super.func_146281_b();
/*     */   }
/*     */   
/*     */   public boolean isHovered(int mouseX, int mouseY, double x, double y, double height, double width) {
/* 332 */     return (mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height);
/*     */   }
/*     */   
/*     */   enum Handle {
/* 336 */     DRAW,
/* 337 */     CLICK,
/* 338 */     RELEASE;
/*     */   }
/*     */   
/*     */   public class Panel {
/*     */     public Module.Category category;
/*     */     public float x;
/*     */     public float y;
/*     */     public float width;
/*     */     public float height;
/*     */     public boolean dragging;
/*     */     public boolean extended;
/*     */     
/*     */     public Panel(Module.Category category, float x, float y, float width, float height) {
/* 351 */       this.category = category;
/* 352 */       this.x = x;
/* 353 */       this.y = y;
/* 354 */       this.width = width;
/* 355 */       this.height = height;
/* 356 */       this.extended = true;
/* 357 */       this.dragging = false;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_73868_f() {
/* 366 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\gui\ClickGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */