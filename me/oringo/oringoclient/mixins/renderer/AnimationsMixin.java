/*     */ package me.oringo.oringoclient.mixins.renderer;
/*     */ 
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.qolfeatures.module.combat.KillAura;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.AbstractClientPlayer;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.ItemRenderer;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.spongepowered.asm.mixin.Final;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.Overwrite;
/*     */ import org.spongepowered.asm.mixin.Shadow;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Mixin(value = {ItemRenderer.class}, priority = 1)
/*     */ public abstract class AnimationsMixin
/*     */ {
/*     */   @Shadow
/*     */   private float field_78451_d;
/*     */   @Shadow
/*     */   private float field_78454_c;
/*     */   @Shadow
/*     */   @Final
/*     */   private Minecraft field_78455_a;
/*     */   @Shadow
/*     */   private ItemStack field_78453_b;
/*     */   
/*     */   @Shadow
/*     */   protected abstract void func_178101_a(float paramFloat1, float paramFloat2);
/*     */   
/*     */   @Shadow
/*     */   protected abstract void func_178109_a(AbstractClientPlayer paramAbstractClientPlayer);
/*     */   
/*     */   @Overwrite
/*     */   public void func_78440_a(float partialTicks) {
/*  61 */     float f = 1.0F - this.field_78451_d + (this.field_78454_c - this.field_78451_d) * partialTicks;
/*  62 */     EntityPlayerSP entityPlayerSP = this.field_78455_a.field_71439_g;
/*  63 */     float f1 = entityPlayerSP.func_70678_g(partialTicks);
/*  64 */     float f2 = ((AbstractClientPlayer)entityPlayerSP).field_70127_C + (((AbstractClientPlayer)entityPlayerSP).field_70125_A - ((AbstractClientPlayer)entityPlayerSP).field_70127_C) * partialTicks;
/*  65 */     float f3 = ((AbstractClientPlayer)entityPlayerSP).field_70126_B + (((AbstractClientPlayer)entityPlayerSP).field_70177_z - ((AbstractClientPlayer)entityPlayerSP).field_70126_B) * partialTicks;
/*  66 */     func_178101_a(f2, f3);
/*  67 */     func_178109_a((AbstractClientPlayer)entityPlayerSP);
/*  68 */     func_178110_a(entityPlayerSP, partialTicks);
/*  69 */     GlStateManager.func_179091_B();
/*  70 */     GlStateManager.func_179094_E();
/*     */     
/*  72 */     if (this.field_78453_b != null) {
/*     */       
/*  74 */       if (this.field_78453_b.func_77973_b() instanceof net.minecraft.item.ItemMap) {
/*     */         
/*  76 */         func_178097_a((AbstractClientPlayer)entityPlayerSP, f2, f, f1);
/*     */       }
/*  78 */       else if (entityPlayerSP.func_71052_bv() > 0 || (KillAura.target != null && !OringoClient.killAura.blockMode.getSelected().equals("None"))) {
/*     */         
/*  80 */         EnumAction enumaction = this.field_78453_b.func_77975_n();
/*  81 */         if (KillAura.target != null && !OringoClient.killAura.blockMode.getSelected().equals("None")) enumaction = EnumAction.BLOCK; 
/*  82 */         switch (enumaction) {
/*     */           
/*     */           case NONE:
/*  85 */             func_178096_b(f, 0.0F);
/*     */             break;
/*     */           case EAT:
/*     */           case DRINK:
/*  89 */             func_178104_a((AbstractClientPlayer)entityPlayerSP, partialTicks);
/*  90 */             func_178096_b(f, 0.0F);
/*     */             break;
/*     */           case BLOCK:
/*  93 */             if (OringoClient.animations.isToggled()) {
/*  94 */               float var19; float f16; switch (OringoClient.animations.mode.getSelected()) {
/*     */                 case "1.7":
/*  96 */                   func_178096_b(f, f1);
/*  97 */                   func_178103_d();
/*     */                   break;
/*     */                 case "spin":
/*     */                 case "vertical spin":
/* 101 */                   func_178096_b(f, OringoClient.animations.showSwing.isEnabled() ? f1 : 0.0F);
/* 102 */                   func_178103_d();
/*     */                   break;
/*     */                 case "long hit":
/* 105 */                   func_178096_b(f, 0.0F);
/* 106 */                   func_178103_d();
/* 107 */                   var19 = MathHelper.func_76126_a(MathHelper.func_76129_c(f1) * 3.1415927F);
/* 108 */                   GlStateManager.func_179109_b(-0.05F, 0.6F, 0.3F);
/* 109 */                   GlStateManager.func_179114_b(-var19 * 70.0F / 2.0F, -8.0F, -0.0F, 9.0F);
/* 110 */                   GlStateManager.func_179114_b(-var19 * 70.0F, 1.5F, -0.4F, -0.0F);
/*     */                   break;
/*     */                 case "chill":
/* 113 */                   f16 = MathHelper.func_76126_a(MathHelper.func_76129_c(f1) * 3.1415927F);
/* 114 */                   func_178096_b(f / 2.0F - 0.18F, 0.0F);
/* 115 */                   GL11.glRotatef(f16 * 60.0F / 2.0F, -f16 / 2.0F, -0.0F, -16.0F);
/* 116 */                   GL11.glRotatef(-f16 * 30.0F, 1.0F, f16 / 2.0F, -1.0F);
/* 117 */                   func_178103_d();
/*     */                   break;
/*     */                 case "push":
/* 120 */                   func_178096_b(f, -f1);
/* 121 */                   func_178103_d();
/*     */                   break;
/*     */                 case "custom":
/* 124 */                   func_178096_b(OringoClient.animationCreator.blockProgress.isEnabled() ? f : 0.0F, OringoClient.animationCreator.swingProgress.isEnabled() ? f1 : 0.0F);
/* 125 */                   func_178103_d();
/*     */                   break;
/*     */                 case "helicopter":
/* 128 */                   GlStateManager.func_179114_b((float)(System.currentTimeMillis() / 3L % 360L), 0.0F, 0.0F, -0.1F);
/* 129 */                   func_178096_b(f / 1.6F, 0.0F);
/* 130 */                   func_178103_d();
/*     */                   break;
/*     */               } 
/*     */               break;
/*     */             } 
/* 135 */             func_178096_b(f, 0.0F);
/* 136 */             func_178103_d();
/*     */             break;
/*     */           
/*     */           case BOW:
/* 140 */             func_178096_b(f, 0.0F);
/* 141 */             func_178098_a(partialTicks, (AbstractClientPlayer)entityPlayerSP);
/*     */             break;
/*     */         } 
/*     */       
/*     */       } else {
/* 146 */         func_178105_d(f1);
/* 147 */         func_178096_b(f, f1);
/*     */       } 
/*     */       
/* 150 */       func_178099_a((EntityLivingBase)entityPlayerSP, this.field_78453_b, ItemCameraTransforms.TransformType.FIRST_PERSON);
/*     */     }
/* 152 */     else if (!entityPlayerSP.func_82150_aj()) {
/*     */       
/* 154 */       func_178095_a((AbstractClientPlayer)entityPlayerSP, f, f1);
/*     */     } 
/*     */     
/* 157 */     GlStateManager.func_179121_F();
/* 158 */     GlStateManager.func_179101_C();
/* 159 */     RenderHelper.func_74518_a(); } @Shadow
/*     */   protected abstract void func_178110_a(EntityPlayerSP paramEntityPlayerSP, float paramFloat); @Shadow
/*     */   protected abstract void func_178097_a(AbstractClientPlayer paramAbstractClientPlayer, float paramFloat1, float paramFloat2, float paramFloat3); @Shadow
/*     */   protected abstract void func_178104_a(AbstractClientPlayer paramAbstractClientPlayer, float paramFloat); @Shadow
/*     */   protected abstract void func_178105_d(float paramFloat); @Shadow
/*     */   public abstract void func_178099_a(EntityLivingBase paramEntityLivingBase, ItemStack paramItemStack, ItemCameraTransforms.TransformType paramTransformType); @Shadow
/*     */   protected abstract void func_178095_a(AbstractClientPlayer paramAbstractClientPlayer, float paramFloat1, float paramFloat2); @Shadow
/*     */   protected abstract void func_178098_a(float paramFloat, AbstractClientPlayer paramAbstractClientPlayer); @Overwrite
/*     */   private void func_178096_b(float equipProgress, float swingProgress) {
/* 168 */     float size = (float)OringoClient.animations.size.getValue();
/* 169 */     float x = (float)OringoClient.animations.x.getValue();
/* 170 */     float y = (float)OringoClient.animations.y.getValue();
/* 171 */     float z = (float)OringoClient.animations.z.getValue();
/*     */     
/* 173 */     GlStateManager.func_179109_b(0.56F * x, -0.52F * y, -0.71999997F * z);
/* 174 */     GlStateManager.func_179109_b(0.0F, equipProgress * -0.6F, 0.0F);
/* 175 */     GlStateManager.func_179114_b(45.0F, 0.0F, 1.0F, 0.0F);
/* 176 */     float f = MathHelper.func_76126_a(swingProgress * swingProgress * 3.1415927F);
/* 177 */     float f1 = MathHelper.func_76126_a(MathHelper.func_76129_c(swingProgress) * 3.1415927F);
/* 178 */     GlStateManager.func_179114_b(f * -20.0F, 0.0F, 1.0F, 0.0F);
/* 179 */     GlStateManager.func_179114_b(f1 * -20.0F, 0.0F, 0.0F, 1.0F);
/* 180 */     GlStateManager.func_179114_b(f1 * -80.0F, 1.0F, 0.0F, 0.0F);
/* 181 */     GlStateManager.func_179152_a(0.4F * size, 0.4F * size, 0.4F * size);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Overwrite
/*     */   private void func_178103_d() {
/* 190 */     float angle1 = 30.0F, angle2 = -80.0F, angle3 = 60.0F;
/* 191 */     float translateX = -0.5F, translateY = 0.2F, translateZ = 0.0F;
/* 192 */     float rotation1x = 0.0F, rotation1y = 1.0F, rotation1z = 0.0F;
/* 193 */     float rotation2x = 1.0F, rotation2y = 0.0F, rotation2z = 0.0F;
/* 194 */     switch (OringoClient.animations.mode.getSelected()) {
/*     */       case "custom":
/* 196 */         angle1 = (float)OringoClient.animationCreator.angle1.getValue();
/* 197 */         angle2 = (float)OringoClient.animationCreator.angle2.getValue();
/* 198 */         angle3 = (float)OringoClient.animationCreator.angle3.getValue();
/* 199 */         translateX = (float)OringoClient.animationCreator.translateX.getValue();
/* 200 */         translateY = (float)OringoClient.animationCreator.translateY.getValue();
/* 201 */         translateZ = (float)OringoClient.animationCreator.translateZ.getValue();
/* 202 */         rotation1x = (float)OringoClient.animationCreator.rotation1x.getValue();
/* 203 */         rotation1y = (float)OringoClient.animationCreator.rotation1y.getValue();
/* 204 */         rotation1z = (float)OringoClient.animationCreator.rotation1z.getValue();
/* 205 */         rotation2x = (float)OringoClient.animationCreator.rotation2x.getValue();
/* 206 */         rotation2y = (float)OringoClient.animationCreator.rotation2y.getValue();
/* 207 */         rotation2z = (float)OringoClient.animationCreator.rotation2z.getValue();
/*     */         break;
/*     */       case "vertical spin":
/* 210 */         angle1 = (float)(System.currentTimeMillis() % 720L);
/* 211 */         angle1 /= 2.0F;
/* 212 */         rotation2x = 0.0F;
/* 213 */         angle2 = 0.0F;
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case "spin":
/* 225 */         translateY = 0.8F;
/* 226 */         angle1 = 60.0F;
/* 227 */         angle2 = (float)(-System.currentTimeMillis() % 720L);
/* 228 */         angle2 /= 2.0F;
/* 229 */         rotation2z = 0.8F;
/* 230 */         angle3 = 30.0F;
/*     */         break;
/*     */     } 
/* 233 */     GlStateManager.func_179109_b(translateX, translateY, translateZ);
/* 234 */     GlStateManager.func_179114_b(angle1, rotation1x, rotation1y, rotation1z);
/* 235 */     GlStateManager.func_179114_b(angle2, rotation2x, rotation2y, rotation2z);
/* 236 */     GlStateManager.func_179114_b(angle3, 0.0F, 1.0F, 0.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\mixins\renderer\AnimationsMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */