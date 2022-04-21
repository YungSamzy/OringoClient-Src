/*    */ package me.oringo.oringoclient.mixins;
/*    */ import me.oringo.oringoclient.events.BlockChangeEvent;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.chunk.Chunk;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.fml.common.eventhandler.Event;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ @Mixin({Chunk.class})
/*    */ public class MixinChunk {
/*    */   @Inject(method = {"setBlockState"}, at = {@At("HEAD")})
/*    */   private void onBlockChange(BlockPos pos, IBlockState state, CallbackInfoReturnable<IBlockState> cir) {
/* 17 */     MinecraftForge.EVENT_BUS.post((Event)new BlockChangeEvent(pos, state));
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\mixins\MixinChunk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */