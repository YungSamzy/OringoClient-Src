/*    */ package me.oringo.oringoclient.mixins;
/*    */ 
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.multiplayer.PlayerControllerMP;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Redirect;
/*    */ 
/*    */ @Mixin({PlayerControllerMP.class})
/*    */ public class PlayerControllerMixin
/*    */ {
/*    */   @Redirect(method = {"onPlayerDamageBlock"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getPlayerRelativeBlockHardness(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/world/World;Lnet/minecraft/util/BlockPos;)F"))
/*    */   public float onPlayerDamageBlock(Block instance, EntityPlayer playerIn, World worldIn, BlockPos pos) {
/* 18 */     float hardness = instance.func_180647_a(playerIn, worldIn, pos);
/* 19 */     if (OringoClient.fastBreak.isToggled()) {
/* 20 */       hardness = (float)(hardness * OringoClient.fastBreak.mineSpeed.getValue());
/*    */     }
/* 22 */     return hardness;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\mixins\PlayerControllerMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */