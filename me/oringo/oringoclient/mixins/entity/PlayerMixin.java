/*    */ package me.oringo.oringoclient.mixins.entity;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.entity.player.PlayerCapabilities;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.stats.StatBase;
/*    */ import net.minecraft.util.FoodStats;
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
/*    */ @Mixin({EntityPlayer.class})
/*    */ public abstract class PlayerMixin
/*    */   extends EntityLivingBaseMixin
/*    */ {
/*    */   @Shadow
/*    */   public PlayerCapabilities field_71075_bZ;
/*    */   @Shadow
/*    */   private int field_71072_f;
/*    */   @Shadow
/*    */   public InventoryPlayer field_71071_by;
/*    */   @Shadow
/*    */   protected float field_71102_ce;
/*    */   @Shadow
/*    */   public float field_71106_cc;
/*    */   @Shadow
/*    */   public int field_71068_ca;
/*    */   @Shadow
/*    */   public int field_71067_cb;
/*    */   @Shadow
/*    */   public float eyeHeight;
/*    */   private boolean wasSprinting;
/*    */   
/*    */   @Shadow
/*    */   public abstract void func_71029_a(StatBase paramStatBase);
/*    */   
/*    */   @Shadow
/*    */   public boolean func_70094_T() {
/* 53 */     return false;
/*    */   }
/*    */   
/*    */   @Shadow
/*    */   public abstract void func_71020_j(float paramFloat);
/*    */   
/*    */   @Shadow
/*    */   public abstract FoodStats func_71024_bL();
/*    */   
/*    */   @Shadow
/*    */   public abstract void func_71059_n(Entity paramEntity);
/*    */   
/*    */   @Shadow
/*    */   public abstract ItemStack func_70694_bm();
/*    */   
/*    */   @Shadow
/*    */   public abstract ItemStack func_71045_bC();
/*    */   
/*    */   @Shadow
/*    */   public abstract void func_71028_bD();
/*    */   
/*    */   @Shadow
/*    */   protected void func_70626_be() {}
/*    */   
/*    */   @Shadow
/*    */   public abstract boolean func_71039_bw();
/*    */   
/*    */   @Shadow
/*    */   public abstract ItemStack func_71011_bu();
/*    */   
/*    */   @Shadow
/*    */   protected abstract String func_145776_H();
/*    */   
/*    */   @Shadow
/*    */   protected abstract boolean func_70041_e_();
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\mixins\entity\PlayerMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */