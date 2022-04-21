/*     */ package me.oringo.oringoclient.utils.font;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.texture.DynamicTexture;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class MinecraftFontRenderer
/*     */   extends CFont {
/*  12 */   CFont.CharData[] boldChars = new CFont.CharData[256]; CFont.CharData[] italicChars = new CFont.CharData[256]; CFont.CharData[] boldItalicChars = new CFont.CharData[256];
/*     */ 
/*     */   
/*  15 */   int[] colorCode = new int[32];
/*  16 */   String colorcodeIdentifiers = "0123456789abcdefklmnor";
/*     */   DynamicTexture texBold;
/*     */   
/*     */   public MinecraftFontRenderer(Font font, boolean antiAlias, boolean fractionalMetrics) {
/*  20 */     super(font, antiAlias, fractionalMetrics);
/*  21 */     setupMinecraftColorcodes();
/*  22 */     setupBoldItalicIDs();
/*     */   }
/*     */   DynamicTexture texItalic; DynamicTexture texItalicBold;
/*     */   
/*     */   public int drawStringWithShadow(String text, double x2, double y2, int color) {
/*  27 */     float shadowWidth = drawString(text, x2 + 0.8999999761581421D, y2 + 0.5D, color, true, 8.3F);
/*     */     
/*  29 */     return (int)Math.max(shadowWidth, drawString(text, x2, y2, color, false, 8.3F));
/*     */   }
/*     */ 
/*     */   
/*     */   public int drawSmoothStringWithShadow(String text, double x2, double y2, int color) {
/*  34 */     float shadowWidth = drawSmoothString(text, x2 + 0.8999999761581421D, y2 + 0.5D, color, true);
/*     */     
/*  36 */     return (int)Math.max(shadowWidth, drawSmoothString(text, x2, y2, color, false));
/*     */   }
/*     */ 
/*     */   
/*     */   public int drawSmoothCenteredStringWithShadow(String text, double x2, double y2, int color) {
/*  41 */     float shadowWidth = drawSmoothString(text, x2 + 0.8999999761581421D, y2 + 0.5D, color, true);
/*     */     
/*  43 */     return (int)Math.max(shadowWidth, drawSmoothString(text, x2, y2, color, false));
/*     */   }
/*     */   
/*     */   public int drawString(String text, double x2, double y2, int color) {
/*  47 */     return (int)drawString(text, x2, y2, color, false, 8.3F);
/*     */   }
/*     */   
/*     */   public int drawNoBSString(String text, double x2, float y2, int color) {
/*  51 */     return (int)drawNoBSString(text, x2, y2, color, false);
/*     */   }
/*     */   
/*     */   public int drawSmoothString(String text, double x2, float y2, int color) {
/*  55 */     return (int)drawSmoothString(text, x2, y2, color, false);
/*     */   }
/*     */   
/*     */   public float drawSmoothCenteredString(String text, float x2, float y2, int color) {
/*  59 */     return drawSmoothString(text, (x2 - (float)(getStringWidth(text) / 2.0D)), y2, color);
/*     */   }
/*     */ 
/*     */   
/*     */   public float drawCenteredString(String text, float x2, float y2, int color) {
/*  64 */     return drawString(text, (x2 - (float)(getStringWidth(text) / 2.0D)), y2, color);
/*     */   }
/*     */   
/*     */   public float drawNoBSCenteredString(String text, float x2, float y2, int color) {
/*  68 */     return drawNoBSString(text, (x2 - (float)(getStringWidth(text) / 2.0D)), y2, color);
/*     */   }
/*     */   
/*     */   public float drawCenteredStringWithShadow(String text, float x2, float y2, int color) {
/*  72 */     return drawStringWithShadow(text, (x2 - (float)(getStringWidth(text) / 2.0D)), y2, color);
/*     */   }
/*     */ 
/*     */   
/*     */   public float drawString(String text, double x, double y, int color, boolean shadow, float kerning) {
/*  77 */     x--;
/*     */     
/*  79 */     if (text == null) {
/*  80 */       return 0.0F;
/*     */     }
/*     */     
/*  83 */     if (color == 553648127) {
/*  84 */       color = 16777215;
/*     */     }
/*     */     
/*  87 */     if ((color & 0xFC000000) == 0) {
/*  88 */       color |= 0xFF000000;
/*     */     }
/*     */     
/*  91 */     if (shadow) {
/*  92 */       color = (color & 0xFCFCFC) >> 2 | color & 0xFF000000;
/*     */     }
/*     */     
/*  95 */     CFont.CharData[] currentData = this.charData;
/*  96 */     float alpha = (color >> 24 & 0xFF) / 255.0F;
/*  97 */     boolean randomCase = false;
/*  98 */     boolean bold = false;
/*  99 */     boolean italic = false;
/* 100 */     boolean strikethrough = false;
/* 101 */     boolean underline = false;
/* 102 */     boolean render = true;
/* 103 */     x *= 2.0D;
/* 104 */     y = (y - 3.0D) * 2.0D;
/* 105 */     GL11.glPushMatrix();
/* 106 */     GlStateManager.func_179139_a(0.5D, 0.5D, 0.5D);
/* 107 */     GlStateManager.func_179147_l();
/* 108 */     GlStateManager.func_179112_b(770, 771);
/* 109 */     GlStateManager.func_179131_c((color >> 16 & 0xFF) / 255.0F, (color >> 8 & 0xFF) / 255.0F, (color & 0xFF) / 255.0F, alpha);
/* 110 */     GlStateManager.func_179098_w();
/* 111 */     GlStateManager.func_179144_i(this.tex.func_110552_b());
/* 112 */     GL11.glBindTexture(3553, this.tex.func_110552_b());
/*     */     
/* 114 */     for (int index = 0; index < text.length(); index++) {
/* 115 */       char character = text.charAt(index);
/*     */       
/* 117 */       if (character == '§') {
/* 118 */         int colorIndex = 21;
/*     */         
/*     */         try {
/* 121 */           colorIndex = "0123456789abcdefklmnor".indexOf(text.charAt(index + 1));
/* 122 */         } catch (Exception e) {
/* 123 */           e.printStackTrace();
/*     */         } 
/*     */         
/* 126 */         if (colorIndex < 16) {
/* 127 */           bold = false;
/* 128 */           italic = false;
/* 129 */           underline = false;
/* 130 */           strikethrough = false;
/* 131 */           GlStateManager.func_179144_i(this.tex.func_110552_b());
/* 132 */           currentData = this.charData;
/*     */           
/* 134 */           if (colorIndex < 0) {
/* 135 */             colorIndex = 15;
/*     */           }
/*     */           
/* 138 */           if (shadow) {
/* 139 */             colorIndex += 16;
/*     */           }
/*     */           
/* 142 */           int colorcode = this.colorCode[colorIndex];
/* 143 */           GlStateManager.func_179131_c((colorcode >> 16 & 0xFF) / 255.0F, (colorcode >> 8 & 0xFF) / 255.0F, (colorcode & 0xFF) / 255.0F, alpha);
/* 144 */         } else if (colorIndex == 17) {
/* 145 */           bold = true;
/*     */           
/* 147 */           if (italic) {
/* 148 */             GlStateManager.func_179144_i(this.texItalicBold.func_110552_b());
/* 149 */             currentData = this.boldItalicChars;
/*     */           } else {
/* 151 */             GlStateManager.func_179144_i(this.texBold.func_110552_b());
/* 152 */             currentData = this.boldChars;
/*     */           } 
/* 154 */         } else if (colorIndex == 18) {
/* 155 */           strikethrough = true;
/* 156 */         } else if (colorIndex == 19) {
/* 157 */           underline = true;
/* 158 */         } else if (colorIndex == 20) {
/* 159 */           italic = true;
/*     */           
/* 161 */           if (bold) {
/* 162 */             GlStateManager.func_179144_i(this.texItalicBold.func_110552_b());
/* 163 */             currentData = this.boldItalicChars;
/*     */           } else {
/* 165 */             GlStateManager.func_179144_i(this.texItalic.func_110552_b());
/* 166 */             currentData = this.italicChars;
/*     */           } 
/*     */         } else {
/* 169 */           bold = false;
/* 170 */           italic = false;
/* 171 */           underline = false;
/* 172 */           strikethrough = false;
/* 173 */           GlStateManager.func_179131_c((color >> 16 & 0xFF) / 255.0F, (color >> 8 & 0xFF) / 255.0F, (color & 0xFF) / 255.0F, alpha);
/* 174 */           GlStateManager.func_179144_i(this.tex.func_110552_b());
/* 175 */           currentData = this.charData;
/*     */         } 
/*     */         
/* 178 */         index++;
/* 179 */       } else if (character < currentData.length) {
/* 180 */         GL11.glBegin(4);
/* 181 */         drawChar(currentData, character, (float)x, (float)y);
/* 182 */         GL11.glEnd();
/*     */         
/* 184 */         if (strikethrough) {
/* 185 */           drawLine(x, y + ((currentData[character]).height / 2), x + (currentData[character]).width - 8.0D, y + ((currentData[character]).height / 2), 1.0F);
/*     */         }
/*     */         
/* 188 */         if (underline) {
/* 189 */           drawLine(x, y + (currentData[character]).height - 2.0D, x + (currentData[character]).width - 8.0D, y + (currentData[character]).height - 2.0D, 1.0F);
/*     */         }
/*     */         
/* 192 */         x += ((currentData[character]).width - kerning + this.charOffset);
/*     */       } 
/*     */     } 
/*     */     
/* 196 */     GL11.glHint(3155, 4352);
/* 197 */     GL11.glPopMatrix();
/* 198 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 199 */     return (float)x / 2.0F;
/*     */   }
/*     */   
/*     */   public float drawSmoothString(String text, double x, double y, int color, boolean shadow) {
/* 203 */     x--;
/*     */     
/* 205 */     if (text == null) {
/* 206 */       return 0.0F;
/*     */     }
/*     */     
/* 209 */     CFont.CharData[] currentData = this.charData;
/* 210 */     float alpha = (color >> 24 & 0xFF) / 255.0F;
/* 211 */     boolean randomCase = false;
/* 212 */     boolean bold = false;
/* 213 */     boolean italic = false;
/* 214 */     boolean strikethrough = false;
/* 215 */     boolean underline = false;
/* 216 */     boolean render = true;
/* 217 */     x *= 2.0D;
/* 218 */     y = (y - 3.0D) * 2.0D;
/* 219 */     GL11.glPushMatrix();
/* 220 */     GlStateManager.func_179139_a(0.5D, 0.5D, 0.5D);
/* 221 */     GlStateManager.func_179147_l();
/* 222 */     GlStateManager.func_179112_b(770, 771);
/* 223 */     GlStateManager.func_179131_c((color >> 16 & 0xFF) / 255.0F, (color >> 8 & 0xFF) / 255.0F, (color & 0xFF) / 255.0F, alpha);
/* 224 */     GlStateManager.func_179098_w();
/* 225 */     GlStateManager.func_179144_i(this.tex.func_110552_b());
/* 226 */     GL11.glBindTexture(3553, this.tex.func_110552_b());
/* 227 */     GL11.glTexParameteri(3553, 10241, 9729);
/* 228 */     GL11.glTexParameteri(3553, 10240, 9729);
/*     */     
/* 230 */     for (int index = 0; index < text.length(); index++) {
/* 231 */       char character = text.charAt(index);
/*     */       
/* 233 */       if (character == '§') {
/* 234 */         int colorIndex = 21;
/*     */         
/*     */         try {
/* 237 */           colorIndex = "0123456789abcdefklmnor".indexOf(text.charAt(index + 1));
/* 238 */         } catch (Exception e) {
/* 239 */           e.printStackTrace();
/*     */         } 
/*     */         
/* 242 */         if (colorIndex < 16) {
/* 243 */           bold = false;
/* 244 */           italic = false;
/* 245 */           underline = false;
/* 246 */           strikethrough = false;
/* 247 */           GlStateManager.func_179144_i(this.tex.func_110552_b());
/* 248 */           currentData = this.charData;
/*     */           
/* 250 */           if (colorIndex < 0) {
/* 251 */             colorIndex = 15;
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 256 */           int colorcode = this.colorCode[colorIndex];
/* 257 */           if (!shadow) GlStateManager.func_179131_c((colorcode >> 16 & 0xFF) / 255.0F, (colorcode >> 8 & 0xFF) / 255.0F, (colorcode & 0xFF) / 255.0F, alpha); 
/* 258 */         } else if (colorIndex != 16) {
/* 259 */           if (colorIndex == 17) {
/* 260 */             bold = true;
/*     */             
/* 262 */             if (italic) {
/* 263 */               GlStateManager.func_179144_i(this.texItalicBold.func_110552_b());
/* 264 */               GL11.glBindTexture(3553, this.texItalicBold.func_110552_b());
/* 265 */               GL11.glTexParameteri(3553, 10241, 9729);
/* 266 */               GL11.glTexParameteri(3553, 10240, 9729);
/* 267 */               currentData = this.boldItalicChars;
/*     */             } else {
/* 269 */               GlStateManager.func_179144_i(this.texBold.func_110552_b());
/* 270 */               GL11.glBindTexture(3553, this.texBold.func_110552_b());
/* 271 */               GL11.glTexParameteri(3553, 10241, 9729);
/* 272 */               GL11.glTexParameteri(3553, 10240, 9729);
/* 273 */               currentData = this.boldChars;
/*     */             } 
/* 275 */           } else if (colorIndex == 18) {
/* 276 */             strikethrough = true;
/* 277 */           } else if (colorIndex == 19) {
/* 278 */             underline = true;
/* 279 */           } else if (colorIndex == 20) {
/* 280 */             italic = true;
/*     */             
/* 282 */             if (bold) {
/* 283 */               GlStateManager.func_179144_i(this.texItalicBold.func_110552_b());
/* 284 */               GL11.glBindTexture(3553, this.texItalicBold.func_110552_b());
/* 285 */               GL11.glTexParameteri(3553, 10241, 9729);
/* 286 */               GL11.glTexParameteri(3553, 10240, 9729);
/* 287 */               currentData = this.boldItalicChars;
/*     */             } else {
/* 289 */               GlStateManager.func_179144_i(this.texItalic.func_110552_b());
/* 290 */               GL11.glBindTexture(3553, this.texItalic.func_110552_b());
/* 291 */               GL11.glTexParameteri(3553, 10241, 9729);
/* 292 */               GL11.glTexParameteri(3553, 10240, 9729);
/* 293 */               currentData = this.italicChars;
/*     */             } 
/*     */           } else {
/* 296 */             bold = false;
/* 297 */             italic = false;
/* 298 */             underline = false;
/* 299 */             strikethrough = false;
/* 300 */             GlStateManager.func_179131_c((color >> 16 & 0xFF) / 255.0F, (color >> 8 & 0xFF) / 255.0F, (color & 0xFF) / 255.0F, alpha);
/* 301 */             GlStateManager.func_179144_i(this.tex.func_110552_b());
/* 302 */             currentData = this.charData;
/*     */           } 
/*     */         } 
/* 305 */         index++;
/* 306 */       } else if (character < currentData.length) {
/* 307 */         GL11.glBegin(4);
/* 308 */         drawChar(currentData, character, (float)x, (float)y);
/* 309 */         GL11.glEnd();
/*     */         
/* 311 */         if (strikethrough) {
/* 312 */           drawLine(x, y + ((currentData[character]).height / 2), x + (currentData[character]).width - 8.0D, y + ((currentData[character]).height / 2), 1.0F);
/*     */         }
/*     */         
/* 315 */         if (underline) {
/* 316 */           drawLine(x, y + (currentData[character]).height - 2.0D, x + (currentData[character]).width - 8.0D, y + (currentData[character]).height - 2.0D, 1.0F);
/*     */         }
/*     */         
/* 319 */         x += ((currentData[character]).width - 8.3F + this.charOffset);
/*     */       } 
/*     */     } 
/*     */     
/* 323 */     GL11.glHint(3155, 4352);
/* 324 */     GL11.glPopMatrix();
/* 325 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 326 */     return (float)x / 2.0F;
/*     */   }
/*     */   
/*     */   public float drawNoBSString(String text, double x, double y, int color, boolean shadow) {
/* 330 */     x--;
/*     */     
/* 332 */     if (text == null) {
/* 333 */       return 0.0F;
/*     */     }
/*     */     
/* 336 */     CFont.CharData[] currentData = this.charData;
/* 337 */     float alpha = (color >> 24 & 0xFF) / 255.0F;
/* 338 */     boolean randomCase = false;
/* 339 */     boolean bold = false;
/* 340 */     boolean italic = false;
/* 341 */     boolean strikethrough = false;
/* 342 */     boolean underline = false;
/* 343 */     boolean render = true;
/* 344 */     x *= 2.0D;
/* 345 */     y = (y - 3.0D) * 2.0D;
/* 346 */     GL11.glPushMatrix();
/* 347 */     GlStateManager.func_179139_a(0.5D, 0.5D, 0.5D);
/* 348 */     GlStateManager.func_179147_l();
/* 349 */     GlStateManager.func_179112_b(770, 771);
/* 350 */     GlStateManager.func_179131_c((color >> 16 & 0xFF) / 255.0F, (color >> 8 & 0xFF) / 255.0F, (color & 0xFF) / 255.0F, alpha);
/* 351 */     GlStateManager.func_179098_w();
/* 352 */     GlStateManager.func_179144_i(this.tex.func_110552_b());
/* 353 */     GL11.glBindTexture(3553, this.tex.func_110552_b());
/* 354 */     GL11.glTexParameteri(3553, 10241, 9728);
/* 355 */     GL11.glTexParameteri(3553, 10240, 9728);
/*     */     
/* 357 */     for (int index = 0; index < text.length(); index++) {
/* 358 */       char character = text.charAt(index);
/*     */       
/* 360 */       if (character == '§') {
/* 361 */         int colorIndex = 21;
/*     */         
/*     */         try {
/* 364 */           colorIndex = "0123456789abcdefklmnor".indexOf(text.charAt(index + 1));
/* 365 */         } catch (Exception e) {
/* 366 */           e.printStackTrace();
/*     */         } 
/*     */         
/* 369 */         if (colorIndex < 16) {
/* 370 */           bold = false;
/* 371 */           italic = false;
/* 372 */           underline = false;
/* 373 */           strikethrough = false;
/* 374 */           GlStateManager.func_179144_i(this.tex.func_110552_b());
/* 375 */           currentData = this.charData;
/*     */           
/* 377 */           if (colorIndex < 0) {
/* 378 */             colorIndex = 15;
/*     */           }
/*     */           
/* 381 */           if (shadow) {
/* 382 */             colorIndex += 16;
/*     */           }
/*     */           
/* 385 */           int colorcode = this.colorCode[colorIndex];
/* 386 */           GlStateManager.func_179131_c((colorcode >> 16 & 0xFF) / 255.0F, (colorcode >> 8 & 0xFF) / 255.0F, (colorcode & 0xFF) / 255.0F, alpha);
/* 387 */         } else if (colorIndex != 16) {
/* 388 */           if (colorIndex == 17) {
/* 389 */             bold = true;
/*     */             
/* 391 */             if (italic) {
/* 392 */               GlStateManager.func_179144_i(this.texItalicBold.func_110552_b());
/* 393 */               currentData = this.boldItalicChars;
/*     */             } else {
/* 395 */               GlStateManager.func_179144_i(this.texBold.func_110552_b());
/* 396 */               currentData = this.boldChars;
/*     */             } 
/* 398 */           } else if (colorIndex == 18) {
/* 399 */             strikethrough = true;
/* 400 */           } else if (colorIndex == 19) {
/* 401 */             underline = true;
/* 402 */           } else if (colorIndex == 20) {
/* 403 */             italic = true;
/*     */             
/* 405 */             if (bold) {
/* 406 */               GlStateManager.func_179144_i(this.texItalicBold.func_110552_b());
/* 407 */               currentData = this.boldItalicChars;
/*     */             } else {
/* 409 */               GlStateManager.func_179144_i(this.texItalic.func_110552_b());
/* 410 */               currentData = this.italicChars;
/*     */             } 
/*     */           } else {
/* 413 */             bold = false;
/* 414 */             italic = false;
/* 415 */             underline = false;
/* 416 */             strikethrough = false;
/* 417 */             GlStateManager.func_179131_c((color >> 16 & 0xFF) / 255.0F, (color >> 8 & 0xFF) / 255.0F, (color & 0xFF) / 255.0F, alpha);
/* 418 */             GlStateManager.func_179144_i(this.tex.func_110552_b());
/* 419 */             currentData = this.charData;
/*     */           } 
/*     */         } 
/* 422 */         index++;
/* 423 */       } else if (character < currentData.length) {
/* 424 */         GL11.glBegin(4);
/* 425 */         drawChar(currentData, character, (float)x, (float)y);
/* 426 */         GL11.glEnd();
/*     */         
/* 428 */         if (strikethrough) {
/* 429 */           drawLine(x, y + ((currentData[character]).height / 2), x + (currentData[character]).width - 8.0D, y + ((currentData[character]).height / 2), 1.0F);
/*     */         }
/*     */         
/* 432 */         if (underline) {
/* 433 */           drawLine(x, y + (currentData[character]).height - 2.0D, x + (currentData[character]).width - 8.0D, y + (currentData[character]).height - 2.0D, 1.0F);
/*     */         }
/*     */         
/* 436 */         x += ((currentData[character]).width - 8.3F + this.charOffset);
/*     */       } 
/*     */     } 
/*     */     
/* 440 */     GL11.glHint(3155, 4352);
/* 441 */     GL11.glPopMatrix();
/* 442 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 443 */     return (float)x / 2.0F;
/*     */   }
/*     */   
/*     */   public double getStringWidth(String text) {
/* 447 */     if (text == null) {
/* 448 */       return 0.0D;
/*     */     }
/*     */     
/* 451 */     float width = 0.0F;
/* 452 */     CFont.CharData[] currentData = this.charData;
/* 453 */     boolean bold = false, italic = false;
/*     */     
/* 455 */     for (int index = 0; index < text.length(); index++) {
/* 456 */       char character = text.charAt(index);
/*     */       
/* 458 */       if (character == '§') {
/* 459 */         int colorIndex = "0123456789abcdefklmnor".indexOf(character);
/*     */         
/* 461 */         index++;
/* 462 */       } else if (character < currentData.length) {
/* 463 */         width += (currentData[character]).width - 8.3F + this.charOffset;
/*     */       } 
/*     */     } 
/*     */     
/* 467 */     return (width / 2.0F);
/*     */   }
/*     */   
/*     */   public double getStringWidth(String text, float kerning) {
/* 471 */     if (text == null) {
/* 472 */       return 0.0D;
/*     */     }
/*     */     
/* 475 */     float width = 0.0F;
/* 476 */     CFont.CharData[] currentData = this.charData;
/* 477 */     boolean bold = false, italic = false;
/*     */     
/* 479 */     for (int index = 0; index < text.length(); index++) {
/* 480 */       char c = text.charAt(index);
/*     */       
/* 482 */       if (c == '§') {
/* 483 */         int colorIndex = "0123456789abcdefklmnor".indexOf(c);
/*     */         
/* 485 */         index++;
/* 486 */       } else if (c < currentData.length) {
/* 487 */         width += (currentData[c]).width - kerning + this.charOffset;
/*     */       } 
/*     */     } 
/*     */     
/* 491 */     return (width / 2.0F);
/*     */   }
/*     */   
/*     */   public int getHeight() {
/* 495 */     return (this.fontHeight - 8) / 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFont(Font font) {
/* 500 */     super.setFont(font);
/* 501 */     setupBoldItalicIDs();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAntiAlias(boolean antiAlias) {
/* 506 */     super.setAntiAlias(antiAlias);
/* 507 */     setupBoldItalicIDs();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFractionalMetrics(boolean fractionalMetrics) {
/* 512 */     super.setFractionalMetrics(fractionalMetrics);
/* 513 */     setupBoldItalicIDs();
/*     */   }
/*     */   
/*     */   private void setupBoldItalicIDs() {
/* 517 */     this.texBold = setupTexture(this.font.deriveFont(1), this.antiAlias, this.fractionalMetrics, this.boldChars);
/* 518 */     this.texItalic = setupTexture(this.font.deriveFont(2), this.antiAlias, this.fractionalMetrics, this.italicChars);
/* 519 */     this.texItalicBold = setupTexture(this.font.deriveFont(3), this.antiAlias, this.fractionalMetrics, this.boldItalicChars);
/*     */   }
/*     */   
/*     */   private void drawLine(double x2, double y2, double x1, double y1, float width) {
/* 523 */     GL11.glDisable(3553);
/* 524 */     GL11.glLineWidth(width);
/* 525 */     GL11.glBegin(1);
/* 526 */     GL11.glVertex2d(x2, y2);
/* 527 */     GL11.glVertex2d(x1, y1);
/* 528 */     GL11.glEnd();
/* 529 */     GL11.glEnable(3553);
/*     */   }
/*     */   
/*     */   public List<String> wrapWords(String text, double width) {
/* 533 */     ArrayList<String> finalWords = new ArrayList<>();
/*     */     
/* 535 */     if (getStringWidth(text) > width) {
/* 536 */       String[] words = text.split(" ");
/* 537 */       StringBuilder currentWord = new StringBuilder();
/* 538 */       char lastColorCode = Character.MAX_VALUE;
/*     */       
/* 540 */       for (String word : words) {
/* 541 */         for (int innerIndex = 0; innerIndex < (word.toCharArray()).length; innerIndex++) {
/* 542 */           char c = word.toCharArray()[innerIndex];
/*     */           
/* 544 */           if (c == '§' && innerIndex < (word.toCharArray()).length - 1) {
/* 545 */             lastColorCode = word.toCharArray()[innerIndex + 1];
/*     */           }
/*     */         } 
/*     */         
/* 549 */         if (getStringWidth(currentWord + word + " ") < width) {
/* 550 */           currentWord.append(word).append(" ");
/*     */         } else {
/* 552 */           finalWords.add(currentWord.toString());
/* 553 */           currentWord = new StringBuilder("§" + lastColorCode + word + " ");
/*     */         } 
/*     */       } 
/*     */       
/* 557 */       if (currentWord.length() > 0) {
/* 558 */         if (getStringWidth(currentWord.toString()) < width) {
/* 559 */           finalWords.add("§" + lastColorCode + currentWord + " ");
/* 560 */           currentWord = new StringBuilder();
/*     */         } else {
/* 562 */           finalWords.addAll(formatString(currentWord.toString(), width));
/*     */         } 
/*     */       }
/*     */     } else {
/* 566 */       finalWords.add(text);
/*     */     } 
/*     */     
/* 569 */     return finalWords;
/*     */   }
/*     */   
/*     */   public List<String> formatString(String string, double width) {
/* 573 */     ArrayList<String> finalWords = new ArrayList<>();
/* 574 */     StringBuilder currentWord = new StringBuilder();
/* 575 */     char lastColorCode = Character.MAX_VALUE;
/* 576 */     char[] chars = string.toCharArray();
/*     */     
/* 578 */     for (int index = 0; index < chars.length; index++) {
/* 579 */       char c = chars[index];
/*     */       
/* 581 */       if (c == '§' && index < chars.length - 1) {
/* 582 */         lastColorCode = chars[index + 1];
/*     */       }
/*     */       
/* 585 */       if (getStringWidth(currentWord.toString() + c) < width) {
/* 586 */         currentWord.append(c);
/*     */       } else {
/* 588 */         finalWords.add(currentWord.toString());
/* 589 */         currentWord = new StringBuilder("§" + lastColorCode + c);
/*     */       } 
/*     */     } 
/*     */     
/* 593 */     if (currentWord.length() > 0) {
/* 594 */       finalWords.add(currentWord.toString());
/*     */     }
/*     */     
/* 597 */     return finalWords;
/*     */   }
/*     */   
/*     */   private void setupMinecraftColorcodes() {
/* 601 */     int index = 0;
/*     */     
/* 603 */     while (index < 32) {
/* 604 */       int noClue = (index >> 3 & 0x1) * 85;
/* 605 */       int red = (index >> 2 & 0x1) * 170 + noClue;
/* 606 */       int green = (index >> 1 & 0x1) * 170 + noClue;
/* 607 */       int blue = (index & 0x1) * 170 + noClue;
/*     */       
/* 609 */       if (index == 6) {
/* 610 */         red += 85;
/*     */       }
/*     */       
/* 613 */       if (index >= 16) {
/* 614 */         red /= 4;
/* 615 */         green /= 4;
/* 616 */         blue /= 4;
/*     */       } 
/*     */       
/* 619 */       this.colorCode[index] = (red & 0xFF) << 16 | (green & 0xFF) << 8 | blue & 0xFF;
/* 620 */       index++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String trimStringToWidth(String text, int width) {
/* 625 */     return trimStringToWidth(text, width, false);
/*     */   }
/*     */   
/*     */   private float getCharWidthFloat(char c) {
/* 629 */     if (c == '§')
/* 630 */       return -1.0F; 
/* 631 */     if (c == ' ') {
/* 632 */       return 2.0F;
/*     */     }
/* 634 */     if (this.charData[c] == null) return 0.0F;
/*     */     
/* 636 */     int var2 = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\000\000\000\000\000\000\000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\000".indexOf(c);
/*     */     
/* 638 */     if (c > '\000' && var2 != -1)
/* 639 */       return (this.charData[var2]).width / 2.0F - 4.0F; 
/* 640 */     if ((this.charData[c]).width / 2.0F - 4.0F != 0.0F) {
/* 641 */       int var3 = (int)((this.charData[c]).width / 2.0F - 4.0F) >>> 4;
/* 642 */       int var4 = (int)((this.charData[c]).width / 2.0F - 4.0F) & 0xF;
/* 643 */       var3 &= 0xF;
/* 644 */       var4++;
/* 645 */       return ((var4 - var3) / 2 + 1);
/*     */     } 
/* 647 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String trimStringToWidth(String text, int width, boolean custom) {
/* 653 */     StringBuilder buffer = new StringBuilder();
/* 654 */     float lineWidth = 0.0F;
/* 655 */     int offset = custom ? (text.length() - 1) : 0;
/* 656 */     int increment = custom ? -1 : 1;
/* 657 */     boolean var8 = false;
/* 658 */     boolean var9 = false;
/*     */     int index;
/* 660 */     for (index = offset; index >= 0 && index < text.length() && lineWidth < width; index += increment) {
/* 661 */       char character = text.charAt(index);
/* 662 */       float charWidth = getCharWidthFloat(character);
/*     */       
/* 664 */       if (var8) {
/* 665 */         var8 = false;
/*     */         
/* 667 */         if (character != 'l' && character != 'L') {
/* 668 */           if (character == 'r' || character == 'R') {
/* 669 */             var9 = false;
/*     */           }
/*     */         } else {
/* 672 */           var9 = true;
/*     */         } 
/* 674 */       } else if (charWidth < 0.0F) {
/* 675 */         var8 = true;
/*     */       } else {
/* 677 */         lineWidth += charWidth;
/*     */         
/* 679 */         if (var9) {
/* 680 */           lineWidth++;
/*     */         }
/*     */       } 
/*     */       
/* 684 */       if (lineWidth > width) {
/*     */         break;
/*     */       }
/*     */       
/* 688 */       if (custom) {
/* 689 */         buffer.insert(0, character);
/*     */       } else {
/* 691 */         buffer.append(character);
/*     */       } 
/*     */     } 
/*     */     
/* 695 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclien\\utils\font\MinecraftFontRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */