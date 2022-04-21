/*    */ package me.oringo.oringoclient.commands.nucleus;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import net.minecraft.command.CommandException;
/*    */ import net.minecraft.command.ICommand;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.C07PacketPlayerDigging;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class PlaceCrystalsCommand
/*    */   implements ICommand {
/* 17 */   public static BlockPos[] nucleus = new BlockPos[] { new BlockPos(543, 111, 499), new BlockPos(544, 111, 527), new BlockPos(482, 111, 525), new BlockPos(483, 111, 500), new BlockPos(513, 111, 483) };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String func_71517_b() {
/* 27 */     return "placecrystals";
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71518_a(ICommandSender iCommandSender) {
/* 32 */     return "/placecrystals";
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> func_71514_a() {
/* 37 */     return new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_71515_b(ICommandSender iCommandSender, String[] strings) throws CommandException {
/* 42 */     (new Thread(() -> {
/*    */           for (BlockPos crystal : nucleus) {
/*    */             OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, crystal, EnumFacing.func_176733_a(OringoClient.mc.field_71439_g.field_70177_z)));
/*    */             try {
/*    */               Thread.sleep(500L);
/* 47 */             } catch (InterruptedException e) {
/*    */               e.printStackTrace();
/*    */             } 
/*    */           } 
/* 51 */         })).start();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_71519_b(ICommandSender iCommandSender) {
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> func_180525_a(ICommandSender iCommandSender, String[] strings, BlockPos blockPos) {
/* 61 */     return new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_82358_a(String[] strings, int i) {
/* 66 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(@NotNull ICommand o) {
/* 71 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\commands\nucleus\PlaceCrystalsCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */