/*    */ package me.oringo.oringoclient;
/*    */ 
/*    */ import java.io.File;
/*    */ import me.oringodevmode.Transformer;
/*    */ import net.minecraft.launchwrapper.IClassTransformer;
/*    */ 
/*    */ public class OringoDevMode
/*    */   implements IClassTransformer
/*    */ {
/* 10 */   private boolean enabled = (new File("OringoDev")).exists();
/*    */ 
/*    */   
/*    */   public byte[] transform(String name, String transformedName, byte[] basicClass) {
/* 14 */     if (this.enabled && 
/* 15 */       !transformedName.startsWith("java") && !transformedName.startsWith("sun") && !transformedName.startsWith("org.lwjgl") && !transformedName.startsWith("org.apache") && !transformedName.startsWith("org.objectweb")) {
/* 16 */       Transformer.classes.remove(transformedName);
/* 17 */       Transformer.classes.put(transformedName, basicClass);
/*    */     } 
/*    */     
/* 20 */     return basicClass;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\OringoDevMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */