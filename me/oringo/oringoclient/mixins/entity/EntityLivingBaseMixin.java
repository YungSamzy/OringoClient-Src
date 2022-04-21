/*    */ package me.oringo.oringoclient.mixins.entity;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.ai.attributes.IAttribute;
/*    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({EntityLivingBase.class})
/*    */ public abstract class EntityLivingBaseMixin
/*    */   extends EntityMixin
/*    */ {
/*    */   @Shadow
/*    */   public float field_70759_as;
/*    */   @Shadow
/*    */   private int field_70773_bE;
/*    */   @Shadow
/*    */   protected boolean field_70703_bu;
/*    */   @Shadow
/*    */   public float field_70747_aH;
/*    */   @Shadow
/*    */   protected int field_70716_bi;
/*    */   @Shadow
/*    */   public float field_70701_bs;
/*    */   @Shadow
/*    */   public float field_70702_br;
/*    */   @Shadow
/*    */   protected float field_70764_aw;
/*    */   @Shadow
/*    */   protected int field_70708_bq;
/*    */   @Shadow
/*    */   protected double field_70709_bj;
/*    */   
/*    */   @Shadow
/*    */   protected abstract float func_175134_bD();
/*    */   
/*    */   public void setJumpTicks(int jumpTicks) {
/* 63 */     this.field_70773_bE = jumpTicks; } @Shadow public abstract boolean func_82165_m(int paramInt); @Shadow public abstract PotionEffect func_70660_b(Potion paramPotion); @Shadow protected abstract void func_70664_aZ(); @Shadow public abstract IAttributeInstance func_110148_a(IAttribute paramIAttribute); @Shadow public abstract float func_110143_aJ(); @Shadow public abstract boolean func_70617_f_(); @Shadow public abstract boolean func_70644_a(Potion paramPotion); @Shadow
/*    */   public abstract void func_130011_c(Entity paramEntity); @Shadow
/*    */   public abstract float func_70678_g(float paramFloat); @Shadow
/*    */   protected abstract void func_180433_a(double paramDouble, boolean paramBoolean, Block paramBlock, BlockPos paramBlockPos); @Shadow
/* 67 */   protected abstract void func_175133_bi(); public int getJumpTicks() { return this.field_70773_bE; }
/*    */ 
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\mixins\entity\EntityLivingBaseMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */