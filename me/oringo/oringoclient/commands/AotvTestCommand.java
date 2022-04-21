/*    */ package me.oringo.oringoclient.commands;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import net.minecraft.command.CommandException;
/*    */ import net.minecraft.command.ICommand;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class AotvTestCommand
/*    */   implements ICommand
/*    */ {
/*    */   public String func_71517_b() {
/* 16 */     return "testreturn";
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71518_a(ICommandSender sender) {
/* 21 */     return "/testreturn";
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> func_71514_a() {
/* 26 */     return new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/* 31 */     OringoClient.aotvReturn.start(null, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_71519_b(ICommandSender sender) {
/* 36 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> func_180525_a(ICommandSender sender, String[] args, BlockPos pos) {
/* 41 */     return new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_82358_a(String[] args, int index) {
/* 46 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(@NotNull ICommand o) {
/* 51 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\commands\AotvTestCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */