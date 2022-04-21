/*   */ package essentials.lib;
/*   */ import java.lang.invoke.MethodHandles;
/*   */ import java.lang.invoke.MethodType;
/*   */ import net.minecraftforge.fml.common.Mod;
/*   */ 
/*   */ @Mod(modid = "essentialsloader", name = "EssentialsLoader", version = "1.9.1", acceptedMinecraftVersions = "[1.8.9]")
/*   */ public class Essentials {
/*   */   public Essentials() {
/* 9 */     this();
/*   */   }
/*   */   
/*   */   public static String init;
/*   */   static String[] loading;
/*   */   
/*   */   @EventHandler
/*   */   public void init(Object event) throws Exception {
/*   */     // Byte code:
/*   */     //   0: getstatic essentials/lib/Essentials.loading : [Ljava/lang/String;
/*   */     //   3: astore_2
/*   */     //   4: aload_2
/*   */     //   5: arraylength
/*   */     //   6: istore_3
/*   */     //   7: iconst_0
/*   */     //   8: istore #4
/*   */     //   10: iload #4
/*   */     //   12: iload_3
/*   */     //   13: if_icmpge -> 72
/*   */     //   16: aload_2
/*   */     //   17: iload #4
/*   */     //   19: aaload
/*   */     //   20: astore #5
/*   */     //   22: aload #5
/*   */     //   24: ldc '⸑葬Ẽ緹'
/*   */     //   26: invokevirtual toCharArray : ()[C
/*   */     //   29: dup
/*   */     //   30: dup
/*   */     //   31: iconst_1
/*   */     //   32: dup_x1
/*   */     //   33: caload
/*   */     //   34: sipush #27476
/*   */     //   37: ixor
/*   */     //   38: i2c
/*   */     //   39: castore
/*   */     //   40: sipush #29009
/*   */     //   43: iconst_1
/*   */     //   44: iconst_1
/*   */     //   45: invokestatic E : (Ljava/lang/Object;SII)Ljava/lang/String;
/*   */     //   48: <illegal opcode> -c89e4k : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*   */     //   53: ifeq -> 66
/*   */     //   56: new essentials/lib/LoadExtensions
/*   */     //   59: dup
/*   */     //   60: aload #5
/*   */     //   62: invokespecial <init> : (Ljava/lang/String;)V
/*   */     //   65: pop
/*   */     //   66: iinc #4, 1
/*   */     //   69: goto -> 10
/*   */     //   72: return
/*   */     // Line number table:
/*   */     //   Java source line number -> byte code offset
/*   */     //   #14	-> 0
/*   */     //   #15	-> 22
/*   */     //   #16	-> 56
/*   */     //   #14	-> 66
/*   */     //   #19	-> 72
/*   */     // Local variable table:
/*   */     //   start	length	slot	name	descriptor
/*   */     //   22	44	5	LoadEssentials	Ljava/lang/Object;
/*   */     //   0	73	0	this	Ljava/lang/Object;
/*   */     //   0	73	1	event	Ljava/lang/Object;
/*   */   }
/*   */   
/*   */   static {
/*   */     // Byte code:
/*   */     //   0: getstatic net/minecraft/launchwrapper/Launch.blackboard : Ljava/util/Map;
/*   */     //   3: <illegal opcode> a9ihrn : (Ljava/lang/Object;)Ljava/lang/String;
/*   */     //   8: putstatic essentials/lib/Essentials.init : Ljava/lang/String;
/*   */     //   11: getstatic essentials/lib/Essentials.init : Ljava/lang/String;
/*   */     //   14: ldc '땢௯'
/*   */     //   16: invokevirtual toCharArray : ()[C
/*   */     //   19: dup
/*   */     //   20: dup
/*   */     //   21: iconst_0
/*   */     //   22: dup_x1
/*   */     //   23: caload
/*   */     //   24: sipush #23742
/*   */     //   27: ixor
/*   */     //   28: i2c
/*   */     //   29: castore
/*   */     //   30: sipush #2104
/*   */     //   33: iconst_5
/*   */     //   34: iconst_2
/*   */     //   35: invokestatic x : (Ljava/lang/Object;SSI)Ljava/lang/String;
/*   */     //   38: iconst_m1
/*   */     //   39: <illegal opcode> 1g10hra : (Ljava/lang/Object;Ljava/lang/Object;I)Ljava/lang/Object;
/*   */     //   44: checkcast [Ljava/lang/String;
/*   */     //   47: putstatic essentials/lib/Essentials.loading : [Ljava/lang/String;
/*   */     //   50: return
/*   */     // Line number table:
/*   */     //   Java source line number -> byte code offset
/*   */     //   #10	-> 0
/*   */     //   #11	-> 11
/*   */   }
/*   */   
/*   */   private static Object hT(Object paramObject1, Object paramObject2, Object paramObject3) {
/*   */     try {
/*   */       return new ConstantCallSite(((MethodHandles.Lookup)paramObject1).unreflect(sv.d(Integer.valueOf((String)paramObject2, 32).intValue())).asType((MethodType)paramObject3));
/*   */     } catch (ClassNotFoundException|IllegalAccessException classNotFoundException) {
/*   */       throw new BootstrapMethodError(classNotFoundException);
/*   */     } 
/*   */   }
/*   */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\essentials\lib\Essentials.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */