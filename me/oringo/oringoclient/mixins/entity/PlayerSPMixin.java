/*     */ package me.oringo.oringoclient.mixins.entity;
/*     */ 
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.events.MotionUpdateEvent;
/*     */ import me.oringo.oringoclient.events.MoveEvent;
/*     */ import me.oringo.oringoclient.events.PlayerUpdateEvent;
/*     */ import me.oringo.oringoclient.qolfeatures.module.combat.KillAura;
/*     */ import me.oringo.oringoclient.utils.MovementUtils;
/*     */ import me.oringo.oringoclient.utils.RotationUtils;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.network.NetHandlerPlayClient;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.entity.IEntityMultiPart;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.boss.EntityDragonPart;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.C03PacketPlayer;
/*     */ import net.minecraft.network.play.client.C0BPacketEntityAction;
/*     */ import net.minecraft.network.play.server.S12PacketEntityVelocity;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.stats.AchievementList;
/*     */ import net.minecraft.stats.StatBase;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovementInput;
/*     */ import net.minecraftforge.common.ForgeHooks;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.fml.common.eventhandler.Event;
/*     */ import org.spongepowered.asm.mixin.Final;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.Overwrite;
/*     */ import org.spongepowered.asm.mixin.Shadow;
/*     */ import org.spongepowered.asm.mixin.injection.At;
/*     */ import org.spongepowered.asm.mixin.injection.Inject;
/*     */ import org.spongepowered.asm.mixin.injection.Redirect;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
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
/*     */ @Mixin(value = {EntityPlayerSP.class}, priority = 1)
/*     */ public abstract class PlayerSPMixin
/*     */   extends AbstractClientPlayerMixin
/*     */ {
/*     */   @Shadow
/*     */   public MovementInput field_71158_b;
/*     */   @Shadow
/*     */   @Final
/*     */   public NetHandlerPlayClient field_71174_a;
/*     */   @Shadow
/*     */   public float field_71086_bY;
/*     */   @Shadow
/*     */   public float field_71164_i;
/*     */   @Shadow
/*     */   public float field_71163_h;
/*     */   @Shadow
/*     */   public float field_71155_g;
/*     */   @Shadow
/*     */   public float field_71154_f;
/*     */   @Shadow
/*     */   private boolean field_175171_bO;
/*     */   @Shadow
/*     */   private float field_175165_bM;
/*     */   @Shadow
/*     */   private double field_175172_bI;
/*     */   @Shadow
/*     */   private double field_175166_bJ;
/*     */   
/*     */   @Overwrite
/*     */   public void func_175161_p() {
/*  95 */     MotionUpdateEvent.Pre pre = new MotionUpdateEvent.Pre(this.field_70165_t, (func_174813_aQ()).field_72338_b, this.field_70161_v, this.field_70177_z, this.field_70125_A, this.field_70122_E, func_70051_ag(), func_70093_af());
/*  96 */     if (MinecraftForge.EVENT_BUS.post((Event)pre)) {
/*     */       return;
/*     */     }
/*     */     
/* 100 */     boolean flag = ((MotionUpdateEvent)pre).sprinting;
/* 101 */     if (flag != this.field_175171_bO) {
/* 102 */       if (flag) {
/* 103 */         this.field_71174_a.func_147297_a((Packet)new C0BPacketEntityAction((Entity)this, C0BPacketEntityAction.Action.START_SPRINTING));
/*     */       } else {
/* 105 */         this.field_71174_a.func_147297_a((Packet)new C0BPacketEntityAction((Entity)this, C0BPacketEntityAction.Action.STOP_SPRINTING));
/*     */       } 
/*     */       
/* 108 */       this.field_175171_bO = flag;
/*     */     } 
/*     */     
/* 111 */     boolean flag1 = ((MotionUpdateEvent)pre).sneaking;
/* 112 */     if (flag1 != this.field_175170_bN) {
/* 113 */       if (flag1) {
/* 114 */         this.field_71174_a.func_147297_a((Packet)new C0BPacketEntityAction((Entity)this, C0BPacketEntityAction.Action.START_SNEAKING));
/*     */       } else {
/* 116 */         this.field_71174_a.func_147297_a((Packet)new C0BPacketEntityAction((Entity)this, C0BPacketEntityAction.Action.STOP_SNEAKING));
/*     */       } 
/*     */       
/* 119 */       this.field_175170_bN = flag1;
/*     */     } 
/*     */     
/* 122 */     if (func_175160_A()) {
/* 123 */       double d0 = ((MotionUpdateEvent)pre).x - this.field_175172_bI;
/* 124 */       double d1 = ((MotionUpdateEvent)pre).y - this.field_175166_bJ;
/* 125 */       double d2 = ((MotionUpdateEvent)pre).z - this.field_175167_bK;
/* 126 */       double d3 = (((MotionUpdateEvent)pre).yaw - this.field_175164_bL);
/* 127 */       double d4 = (((MotionUpdateEvent)pre).pitch - this.field_175165_bM);
/* 128 */       boolean flag2 = (d0 * d0 + d1 * d1 + d2 * d2 > 9.0E-4D || this.field_175168_bP >= 20);
/* 129 */       boolean flag3 = (d3 != 0.0D || d4 != 0.0D);
/* 130 */       if (this.field_70154_o == null) {
/* 131 */         if (flag2 && flag3) {
/* 132 */           this.field_71174_a.func_147297_a((Packet)new C03PacketPlayer.C06PacketPlayerPosLook(((MotionUpdateEvent)pre).x, ((MotionUpdateEvent)pre).y, ((MotionUpdateEvent)pre).z, ((MotionUpdateEvent)pre).yaw, ((MotionUpdateEvent)pre).pitch, ((MotionUpdateEvent)pre).onGround));
/* 133 */         } else if (flag2) {
/* 134 */           this.field_71174_a.func_147297_a((Packet)new C03PacketPlayer.C04PacketPlayerPosition(((MotionUpdateEvent)pre).x, ((MotionUpdateEvent)pre).y, ((MotionUpdateEvent)pre).z, ((MotionUpdateEvent)pre).onGround));
/* 135 */         } else if (flag3) {
/* 136 */           this.field_71174_a.func_147297_a((Packet)new C03PacketPlayer.C05PacketPlayerLook(((MotionUpdateEvent)pre).yaw, ((MotionUpdateEvent)pre).pitch, ((MotionUpdateEvent)pre).onGround));
/*     */         } else {
/* 138 */           this.field_71174_a.func_147297_a((Packet)new C03PacketPlayer(((MotionUpdateEvent)pre).onGround));
/*     */         } 
/*     */       } else {
/* 141 */         this.field_71174_a.func_147297_a((Packet)new C03PacketPlayer.C06PacketPlayerPosLook(this.field_70159_w, -999.0D, this.field_70179_y, ((MotionUpdateEvent)pre).yaw, ((MotionUpdateEvent)pre).pitch, ((MotionUpdateEvent)pre).onGround));
/* 142 */         flag2 = false;
/*     */       } 
/*     */       
/* 145 */       this.field_175168_bP++;
/* 146 */       if (flag2) {
/* 147 */         this.field_175172_bI = ((MotionUpdateEvent)pre).x;
/* 148 */         this.field_175166_bJ = ((MotionUpdateEvent)pre).y;
/* 149 */         this.field_175167_bK = ((MotionUpdateEvent)pre).z;
/* 150 */         this.field_175168_bP = 0;
/*     */       } 
/*     */       
/* 153 */       RotationUtils.lastReportedPitch = this.field_175165_bM;
/* 154 */       if (flag3) {
/* 155 */         this.field_175164_bL = ((MotionUpdateEvent)pre).yaw;
/* 156 */         this.field_175165_bM = ((MotionUpdateEvent)pre).pitch;
/*     */       } 
/*     */     } 
/* 159 */     MinecraftForge.EVENT_BUS.post((Event)new MotionUpdateEvent.Post((MotionUpdateEvent)pre)); } @Shadow private double field_175167_bK; @Shadow private float field_175164_bL; @Shadow private int field_175168_bP; @Shadow private boolean field_175170_bN; @Shadow public abstract void func_70031_b(boolean paramBoolean); @Shadow
/*     */   public abstract boolean func_70093_af(); @Shadow
/*     */   public abstract void func_71009_b(Entity paramEntity); @Shadow
/*     */   public abstract void func_71047_c(Entity paramEntity); @Shadow
/*     */   public abstract void func_71064_a(StatBase paramStatBase, int paramInt); @Shadow
/*     */   protected abstract boolean func_175160_A(); @Shadow
/* 165 */   public abstract void func_85030_a(String paramString, float paramFloat1, float paramFloat2); public void func_70664_aZ() { this.field_70181_x = func_175134_bD();
/*     */     
/* 167 */     if (func_82165_m(Potion.field_76430_j.field_76415_H)) {
/* 168 */       this.field_70181_x += ((func_70660_b(Potion.field_76430_j).func_76458_c() + 1) * 0.1F);
/*     */     }
/*     */     
/* 171 */     if (func_70051_ag()) {
/* 172 */       float f = ((OringoClient.sprint.isToggled() && OringoClient.sprint.omni.isEnabled()) ? MovementUtils.getYaw() : ((OringoClient.killAura.isToggled() && KillAura.target != null && OringoClient.killAura.movementFix.isEnabled()) ? RotationUtils.getAngles((Entity)KillAura.target)[0] : this.field_70177_z)) * 0.017453292F;
/* 173 */       this.field_70159_w -= (MathHelper.func_76126_a(f) * 0.2F);
/* 174 */       this.field_70179_y += (MathHelper.func_76134_b(f) * 0.2F);
/*     */     } 
/*     */     
/* 177 */     this.field_70160_al = true;
/* 178 */     ForgeHooks.onLivingJump((EntityLivingBase)this);
/*     */     
/* 180 */     func_71029_a(StatList.field_75953_u);
/*     */     
/* 182 */     if (func_70051_ag()) {
/* 183 */       func_71020_j(0.8F);
/*     */     } else {
/* 185 */       func_71020_j(0.2F);
/*     */     }  }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70060_a(float strafe, float forward, float friction) {
/* 192 */     float f = strafe * strafe + forward * forward;
/* 193 */     if (f >= 1.0E-4F) {
/* 194 */       f = MathHelper.func_76129_c(f);
/* 195 */       if (f < 1.0F) {
/* 196 */         f = 1.0F;
/*     */       }
/*     */       
/* 199 */       f = friction / f;
/* 200 */       strafe *= f;
/* 201 */       forward *= f;
/* 202 */       float yaw = (OringoClient.killAura.isToggled() && KillAura.target != null && OringoClient.killAura.movementFix.isEnabled()) ? RotationUtils.getAngles((Entity)KillAura.target)[0] : this.field_70177_z;
/* 203 */       float f1 = MathHelper.func_76126_a(yaw * 3.1415927F / 180.0F);
/* 204 */       float f2 = MathHelper.func_76134_b(yaw * 3.1415927F / 180.0F);
/* 205 */       this.field_70159_w += (strafe * f2 - forward * f1);
/* 206 */       this.field_70179_y += (forward * f2 + strafe * f1);
/*     */     } 
/*     */   }
/*     */   
/*     */   @Inject(method = {"pushOutOfBlocks"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void pushOutOfBlocks(double d2, double f, double blockpos, CallbackInfoReturnable<Boolean> cir) {
/* 212 */     cir.setReturnValue(Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   @Redirect(method = {"onLivingUpdate"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;isUsingItem()Z"))
/*     */   public boolean isUsingItem(EntityPlayerSP instance) {
/* 217 */     if (OringoClient.noSlow.isToggled() && instance.func_71039_bw()) {
/* 218 */       return false;
/*     */     }
/* 220 */     return instance.func_71039_bw();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70094_T() {
/* 226 */     return false;
/*     */   }
/*     */   
/*     */   @Inject(method = {"onLivingUpdate"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;onLivingUpdate()V")}, cancellable = true)
/*     */   public void onLivingUpdate(CallbackInfo ci) {
/* 231 */     if ((OringoClient.sprint.omni.isEnabled() && OringoClient.sprint.isToggled()) || OringoClient.speed.isToggled()) {
/* 232 */       if (!MovementUtils.isMoving() || func_70093_af() || (func_71024_bL().func_75116_a() <= 6.0F && !this.field_71075_bZ.field_75101_c)) {
/* 233 */         if (func_70051_ag())
/* 234 */           func_70031_b(false); 
/*     */         return;
/*     */       } 
/* 237 */       if (!func_70051_ag())
/* 238 */         func_70031_b(true); 
/*     */     } 
/* 240 */     if (OringoClient.noSlow.isToggled() && func_71039_bw()) {
/* 241 */       if (func_71011_bu().func_77973_b().func_77661_b(func_71011_bu()) == EnumAction.BLOCK) {
/* 242 */         this.field_71158_b.field_78900_b = (float)(this.field_71158_b.field_78900_b * OringoClient.noSlow.swordSlowdown.getValue());
/* 243 */         this.field_71158_b.field_78902_a = (float)(this.field_71158_b.field_78902_a * OringoClient.noSlow.swordSlowdown.getValue());
/* 244 */       } else if (func_71011_bu().func_77973_b().func_77661_b(func_71011_bu()) == EnumAction.BOW) {
/* 245 */         this.field_71158_b.field_78900_b = (float)(this.field_71158_b.field_78900_b * OringoClient.noSlow.bowSlowdown.getValue());
/* 246 */         this.field_71158_b.field_78902_a = (float)(this.field_71158_b.field_78902_a * OringoClient.noSlow.bowSlowdown.getValue());
/* 247 */       } else if (func_71011_bu().func_77973_b().func_77661_b(func_71011_bu()) != EnumAction.NONE) {
/* 248 */         this.field_71158_b.field_78900_b = (float)(this.field_71158_b.field_78900_b * OringoClient.noSlow.eatingSlowdown.getValue());
/* 249 */         this.field_71158_b.field_78902_a = (float)(this.field_71158_b.field_78902_a * OringoClient.noSlow.eatingSlowdown.getValue());
/*     */       } 
/*     */     }
/* 252 */     if (OringoClient.freeCam.isToggled()) this.field_70145_X = true; 
/* 253 */     if (MinecraftForge.EVENT_BUS.post((Event)new PlayerUpdateEvent())) ci.cancel(); 
/*     */   }
/*     */   
/*     */   @Inject(method = {"updateEntityActionState"}, at = {@At("RETURN")}, cancellable = true)
/*     */   public void updateEntityActionStatePost(CallbackInfo ci) {
/* 258 */     if (OringoClient.speed.isToggled() && OringoClient.speed.autoJump.isEnabled()) {
/* 259 */       this.field_70703_bu = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70091_d(double x, double y, double z) {
/* 265 */     MoveEvent event = new MoveEvent(x, y, z);
/* 266 */     if (MinecraftForge.EVENT_BUS.post((Event)event))
/* 267 */       return;  x = event.x;
/* 268 */     y = event.y;
/* 269 */     z = event.z;
/* 270 */     super.func_70091_d(x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_71059_n(Entity targetEntity) {
/* 275 */     if (ForgeHooks.onPlayerAttackTarget((EntityPlayer)this, targetEntity) && 
/* 276 */       targetEntity.func_70075_an() && !targetEntity.func_85031_j((Entity)this)) {
/* 277 */       float f = (float)func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
/* 278 */       int i = 0;
/* 279 */       float f1 = 0.0F;
/* 280 */       if (targetEntity instanceof EntityLivingBase) {
/* 281 */         f1 = EnchantmentHelper.func_152377_a(func_70694_bm(), ((EntityLivingBase)targetEntity).func_70668_bt());
/*     */       } else {
/* 283 */         f1 = EnchantmentHelper.func_152377_a(func_70694_bm(), EnumCreatureAttribute.UNDEFINED);
/*     */       } 
/*     */       
/* 286 */       i += EnchantmentHelper.func_77501_a((EntityLivingBase)this);
/* 287 */       if (func_70051_ag()) {
/* 288 */         i++;
/*     */       }
/*     */       
/* 291 */       if (f > 0.0F || f1 > 0.0F) {
/* 292 */         boolean flag = (this.field_70143_R > 0.0F && !this.field_70122_E && !func_70617_f_() && !func_70090_H() && !func_70644_a(Potion.field_76440_q) && this.field_70154_o == null && targetEntity instanceof EntityLivingBase);
/* 293 */         if (flag && f > 0.0F) {
/* 294 */           f *= 1.5F;
/*     */         }
/*     */         
/* 297 */         f += f1;
/* 298 */         boolean flag1 = false;
/* 299 */         int j = EnchantmentHelper.func_90036_a((EntityLivingBase)this);
/* 300 */         if (targetEntity instanceof EntityLivingBase && j > 0 && !targetEntity.func_70027_ad()) {
/* 301 */           flag1 = true;
/* 302 */           targetEntity.func_70015_d(1);
/*     */         } 
/*     */         
/* 305 */         double d0 = targetEntity.field_70159_w;
/* 306 */         double d1 = targetEntity.field_70181_x;
/* 307 */         double d2 = targetEntity.field_70179_y;
/* 308 */         boolean flag2 = targetEntity.func_70097_a(DamageSource.func_76365_a((EntityPlayer)this), f);
/* 309 */         if (flag2) {
/* 310 */           EntityLivingBase entityLivingBase; if (i > 0) {
/* 311 */             targetEntity.func_70024_g((-MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F) * i * 0.5F), 0.1D, (MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F) * i * 0.5F));
/* 312 */             if (OringoClient.sprint.isToggled() && OringoClient.sprint.keep.isEnabled()) {
/* 313 */               if (func_70051_ag()) {
/* 314 */                 OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C0BPacketEntityAction((Entity)OringoClient.mc.field_71439_g, C0BPacketEntityAction.Action.STOP_SPRINTING));
/* 315 */                 OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C0BPacketEntityAction((Entity)OringoClient.mc.field_71439_g, C0BPacketEntityAction.Action.START_SPRINTING));
/*     */               } 
/*     */             } else {
/* 318 */               this.field_70159_w *= 0.6D;
/* 319 */               this.field_70179_y *= 0.6D;
/* 320 */               func_70031_b(false);
/*     */             } 
/*     */           } 
/*     */           
/* 324 */           if (targetEntity instanceof EntityPlayerMP && targetEntity.field_70133_I) {
/* 325 */             ((EntityPlayerMP)targetEntity).field_71135_a.func_147359_a((Packet)new S12PacketEntityVelocity(targetEntity));
/* 326 */             targetEntity.field_70133_I = false;
/* 327 */             targetEntity.field_70159_w = d0;
/* 328 */             targetEntity.field_70181_x = d1;
/* 329 */             targetEntity.field_70179_y = d2;
/*     */           } 
/*     */           
/* 332 */           if (flag) {
/* 333 */             func_71009_b(targetEntity);
/*     */           }
/*     */           
/* 336 */           if (f1 > 0.0F) {
/* 337 */             func_71047_c(targetEntity);
/*     */           }
/*     */           
/* 340 */           if (f >= 18.0F) {
/* 341 */             func_71029_a((StatBase)AchievementList.field_75999_E);
/*     */           }
/*     */           
/* 344 */           func_130011_c(targetEntity);
/* 345 */           if (targetEntity instanceof EntityLivingBase) {
/* 346 */             EnchantmentHelper.func_151384_a((EntityLivingBase)targetEntity, (Entity)this);
/*     */           }
/*     */           
/* 349 */           EnchantmentHelper.func_151385_b((EntityLivingBase)this, targetEntity);
/* 350 */           ItemStack itemstack = func_71045_bC();
/* 351 */           Entity entity = targetEntity;
/* 352 */           if (targetEntity instanceof EntityDragonPart) {
/* 353 */             IEntityMultiPart ientitymultipart = ((EntityDragonPart)targetEntity).field_70259_a;
/* 354 */             if (ientitymultipart instanceof EntityLivingBase) {
/* 355 */               entityLivingBase = (EntityLivingBase)ientitymultipart;
/*     */             }
/*     */           } 
/*     */           
/* 359 */           if (itemstack != null && entityLivingBase instanceof EntityLivingBase) {
/* 360 */             itemstack.func_77961_a(entityLivingBase, (EntityPlayer)this);
/* 361 */             if (itemstack.field_77994_a <= 0) {
/* 362 */               func_71028_bD();
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 367 */           if (targetEntity instanceof EntityLivingBase) {
/* 368 */             func_71064_a(StatList.field_75951_w, Math.round(f * 10.0F));
/* 369 */             if (j > 0) {
/* 370 */               targetEntity.func_70015_d(j * 4);
/*     */             }
/*     */           } 
/*     */           
/* 374 */           func_71020_j(0.3F);
/* 375 */         } else if (flag1) {
/* 376 */           targetEntity.func_70066_B();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\mixins\entity\PlayerSPMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */