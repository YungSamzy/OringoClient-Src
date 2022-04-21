/*    */ package me.oringo.oringoclient.events;
/*    */ 
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraftforge.fml.common.eventhandler.Event;
/*    */ 
/*    */ public class BlockChangeEvent
/*    */   extends Event {
/*    */   public BlockPos pos;
/*    */   public IBlockState state;
/*    */   
/*    */   public BlockChangeEvent(BlockPos pos, IBlockState state) {
/* 13 */     this.pos = pos;
/* 14 */     this.state = state;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\events\BlockChangeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */