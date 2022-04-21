/*    */ package me.oringo.oringoclient.commands.nucleus;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.utils.ReflectionUtils;
/*    */ import net.minecraft.command.CommandException;
/*    */ import net.minecraft.command.ICommand;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.C02PacketUseEntity;
/*    */ import net.minecraft.network.play.client.C07PacketPlayerDigging;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class BlockClickCommand
/*    */   implements ICommand {
/*    */   public String func_71517_b() {
/* 21 */     return "click";
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71518_a(ICommandSender iCommandSender) {
/* 26 */     return "/click";
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> func_71514_a() {
/* 31 */     return new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_71515_b(ICommandSender iCommandSender, String[] strings) throws CommandException {
/* 36 */     if (strings.length < 3) {
/* 37 */       if (SaveCommand.idHashMap.containsKey(strings[0])) {
/* 38 */         C02PacketUseEntity packet = new C02PacketUseEntity((Entity)OringoClient.mc.field_71439_g, C02PacketUseEntity.Action.INTERACT);
/* 39 */         ReflectionUtils.setFieldByIndex(packet, 0, SaveCommand.idHashMap.get(strings[0]));
/* 40 */         OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)packet);
/* 41 */       } else if (SaveCommand.posHashMap.containsKey(strings[0])) {
/* 42 */         clickBlock(SaveCommand.posHashMap.get(strings[0]));
/*    */       } 
/*    */       return;
/*    */     } 
/* 46 */     clickBlock(new BlockPos(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2])));
/*    */   }
/*    */   
/*    */   private void clickBlock(BlockPos hitPos) {
/* 50 */     OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, hitPos, EnumFacing.func_176733_a(OringoClient.mc.field_71439_g.field_70177_z)));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_71519_b(ICommandSender iCommandSender) {
/* 55 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> func_180525_a(ICommandSender iCommandSender, String[] strings, BlockPos blockPos) {
/* 60 */     return new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_82358_a(String[] strings, int i) {
/* 65 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(@NotNull ICommand o) {
/* 70 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\commands\nucleus\BlockClickCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */