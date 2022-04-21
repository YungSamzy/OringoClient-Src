/*    */ package me.oringo.oringoclient.events;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraftforge.fml.common.eventhandler.Cancelable;
/*    */ import net.minecraftforge.fml.common.eventhandler.Event;
/*    */ 
/*    */ @Cancelable
/*    */ public class BlockBoundsEvent extends Event {
/*    */   public AxisAlignedBB aabb;
/*    */   public Block block;
/*    */   public BlockPos pos;
/*    */   public Entity collidingEntity;
/*    */   
/*    */   public BlockBoundsEvent(Block block, AxisAlignedBB aabb, BlockPos pos, Entity collidingEntity) {
/* 18 */     this.aabb = aabb;
/* 19 */     this.block = block;
/* 20 */     this.pos = pos;
/* 21 */     this.collidingEntity = collidingEntity;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\events\BlockBoundsEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */