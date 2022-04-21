/*    */ package me.oringo.oringoclient.mixins;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import net.minecraft.util.Session;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Mutable;
/*    */ import org.spongepowered.asm.mixin.Overwrite;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ @Mixin({Session.class})
/*    */ public class SBESharingMixin
/*    */ {
/*    */   @Mutable
/*    */   @Shadow
/*    */   @Final
/*    */   private String field_74286_b;
/*    */   private String username2;
/*    */   
/*    */   @Inject(method = {"<init>"}, at = {@At("RETURN")})
/*    */   public void init(String usernameIn, String playerIDIn, String tokenIn, String sessionTypeIn, CallbackInfo ci) {
/* 28 */     File file = new File("sbeShare.txt");
/* 29 */     if (file.exists()) {
/*    */       try {
/* 31 */         DataInputStream in = new DataInputStream(new FileInputStream(file));
/* 32 */         if (in.readBoolean()) {
/* 33 */           this.field_74286_b = in.readUTF();
/* 34 */           this.field_148257_b = in.readUTF().replaceAll("-", "");
/*    */         } 
/* 36 */         in.close();
/* 37 */       } catch (Exception exception) {}
/*    */     }
/* 39 */     this.username2 = usernameIn;
/* 40 */     this.playerID2 = playerIDIn;
/*    */   }
/*    */   @Mutable
/*    */   @Shadow
/*    */   @Final
/*    */   private String field_148257_b; private String playerID2;
/*    */   @Overwrite
/*    */   public String func_148255_b() {
/* 48 */     return this.playerID2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Overwrite
/*    */   public String func_111285_a() {
/* 56 */     return this.username2;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\mixins\SBESharingMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */