/*    */ package me.oringo.oringoclient.mixins;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraftforge.fml.common.ModContainer;
/*    */ import net.minecraftforge.fml.common.network.handshake.FMLHandshakeMessage;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ @Mixin({FMLHandshakeMessage.ModList.class})
/*    */ public class ModlessMixin
/*    */ {
/*    */   @Shadow
/*    */   private Map<String, String> modTags;
/*    */   
/*    */   @Inject(method = {"<init>(Ljava/util/List;)V"}, at = {@At("RETURN")})
/*    */   public void test(List<ModContainer> modList, CallbackInfo ci) {
/* 23 */     if (!OringoClient.modless.isToggled())
/*    */       return;  
/* 25 */     try { if (Minecraft.func_71410_x().func_71356_B())
/* 26 */         return;  } catch (Exception e)
/*    */     { return; }
/*    */     
/* 29 */     this.modTags.entrySet().removeIf(mod -> (!((String)mod.getKey()).equalsIgnoreCase("fml") && !((String)mod.getKey()).equalsIgnoreCase("forge") && !((String)mod.getKey()).equalsIgnoreCase("mcp")));
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\mixins\ModlessMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */