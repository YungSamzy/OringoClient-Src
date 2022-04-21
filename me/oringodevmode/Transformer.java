/*    */ package me.oringodevmode;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import me.oringo.oringoclient.OringoDevMode;
/*    */ import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
/*    */ import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.MCVersion;
/*    */ import org.spongepowered.asm.launch.MixinBootstrap;
/*    */ 
/*    */ @MCVersion("1.8.9")
/*    */ public class Transformer
/*    */   implements IFMLLoadingPlugin {
/* 13 */   public static HashMap<String, byte[]> classes = (HashMap)new HashMap<>();
/*    */   
/*    */   public Transformer() {
/* 16 */     MixinBootstrap.init();
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getASMTransformerClass() {
/* 21 */     return new String[] { OringoDevMode.class.getName() };
/*    */   }
/*    */ 
/*    */   
/*    */   public String getModContainerClass() {
/* 26 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getSetupClass() {
/* 31 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void injectData(Map<String, Object> data) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public String getAccessTransformerClass() {
/* 41 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringodevmode\Transformer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */