/*    */ package me.oringo.oringoclient.utils.font;
/*    */ 
/*    */ import java.awt.Font;
/*    */ import java.io.InputStream;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ public class Fonts
/*    */ {
/*    */   public static MinecraftFontRenderer fontMedium;
/*    */   public static MinecraftFontRenderer fontMediumBold;
/*    */   
/*    */   private static Font getFont(Map<String, Font> locationMap, String location, int size) {
/*    */     Font font;
/*    */     try {
/* 19 */       if (locationMap.containsKey(location)) {
/* 20 */         font = ((Font)locationMap.get(location)).deriveFont(0, size);
/*    */       } else {
/*    */         
/* 23 */         InputStream is = Minecraft.func_71410_x().func_110442_L().func_110536_a(new ResourceLocation("oringoclient", "fonts/" + location)).func_110527_b();
/* 24 */         font = Font.createFont(0, is);
/* 25 */         locationMap.put(location, font);
/* 26 */         font = font.deriveFont(0, size);
/*    */       } 
/* 28 */     } catch (Exception e) {
/* 29 */       e.printStackTrace();
/* 30 */       System.out.println("Error loading font");
/* 31 */       font = new Font("default", 0, size);
/*    */     } 
/*    */     
/* 34 */     return font;
/*    */   }
/*    */   public static MinecraftFontRenderer fontBig; public static MinecraftFontRenderer fontSmall; public static MinecraftFontRenderer openSans;
/*    */   
/*    */   public static void bootstrap() {
/* 39 */     Map<String, Font> locationMap = new HashMap<>();
/* 40 */     fontSmall = new MinecraftFontRenderer(getFont(locationMap, "roboto.ttf", 17), true, false);
/* 41 */     fontMedium = new MinecraftFontRenderer(getFont(locationMap, "roboto.ttf", 19), true, false);
/* 42 */     fontBig = new MinecraftFontRenderer(getFont(locationMap, "robotoMedium.ttf", 21), true, false);
/* 43 */     fontMediumBold = new MinecraftFontRenderer(getFont(locationMap, "robotoMedium.ttf", 19), true, false);
/* 44 */     openSans = new MinecraftFontRenderer(getFont(locationMap, "OpenSans-Regular.ttf", 18), true, false);
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclien\\utils\font\Fonts.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */