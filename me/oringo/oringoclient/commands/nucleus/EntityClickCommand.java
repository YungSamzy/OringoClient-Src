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
/*    */ import net.minecraft.util.BlockPos;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class EntityClickCommand
/*    */   implements ICommand {
/*    */   public String func_71517_b() {
/* 19 */     return "entity";
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71518_a(ICommandSender iCommandSender) {
/* 24 */     return "/entity";
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> func_71514_a() {
/* 29 */     return new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_71515_b(ICommandSender iCommandSender, String[] strings) throws CommandException {
/* 34 */     C02PacketUseEntity packet = new C02PacketUseEntity((Entity)OringoClient.mc.field_71439_g, C02PacketUseEntity.Action.INTERACT);
/* 35 */     ReflectionUtils.setFieldByIndex(packet, 0, Integer.valueOf(Integer.parseInt(strings[0])));
/* 36 */     OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)packet);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_71519_b(ICommandSender iCommandSender) {
/* 41 */     return true;
/*    */   }
/*    */   
/*    */   public List<String> func_180525_a(ICommandSender iCommandSender, String[] strings, BlockPos blockPos) {
/* 45 */     return new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_82358_a(String[] strings, int i) {
/* 50 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(@NotNull ICommand o) {
/* 55 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\commands\nucleus\EntityClickCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */