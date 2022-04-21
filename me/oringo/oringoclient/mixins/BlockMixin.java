/*    */ package me.oringo.oringoclient.mixins;
/*    */ 
/*    */ import java.util.List;
/*    */ import me.oringo.oringoclient.events.BlockBoundsEvent;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.fml.common.eventhandler.Event;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Overwrite;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ 
/*    */ @Mixin(value = {Block.class}, priority = 1)
/*    */ public abstract class BlockMixin
/*    */ {
/*    */   @Shadow
/*    */   public abstract void func_149676_a(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6);
/*    */   
/*    */   @Shadow
/*    */   public abstract AxisAlignedBB func_180640_a(World paramWorld, BlockPos paramBlockPos, IBlockState paramIBlockState);
/*    */   
/*    */   @Overwrite
/*    */   public void func_180638_a(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List<AxisAlignedBB> list, Entity collidingEntity) {
/* 28 */     BlockBoundsEvent event = new BlockBoundsEvent((Block)this, func_180640_a(worldIn, pos, state), pos, collidingEntity);
/* 29 */     if (MinecraftForge.EVENT_BUS.post((Event)event))
/* 30 */       return;  if (event.aabb != null && mask.func_72326_a(event.aabb))
/* 31 */       list.add(event.aabb); 
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\mixins\BlockMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */