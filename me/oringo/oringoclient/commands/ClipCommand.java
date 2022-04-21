/*    */ package me.oringo.oringoclient.commands;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.utils.Notifications;
/*    */ import net.minecraft.command.CommandException;
/*    */ import net.minecraft.command.ICommand;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class ClipCommand
/*    */   implements ICommand
/*    */ {
/*    */   public String func_71517_b() {
/* 19 */     return "clip";
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71518_a(ICommandSender sender) {
/* 24 */     return "/clip";
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> func_71514_a() {
/* 29 */     return new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/* 34 */     if (args.length == 1) {
/* 35 */       OringoClient.mc.field_71439_g.func_70107_b(MathHelper.func_76128_c(OringoClient.mc.field_71439_g.field_70165_t) + 0.5D, OringoClient.mc.field_71439_g.field_70163_u + Double.parseDouble(args[0]), MathHelper.func_76128_c(OringoClient.mc.field_71439_g.field_70161_v) + 0.5D);
/*    */     } else {
/* 37 */       Notifications.showNotification("Oringo Client", "/clip distance", 1500);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_71519_b(ICommandSender sender) {
/* 43 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> func_180525_a(ICommandSender sender, String[] args, BlockPos pos) {
/* 48 */     return new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_82358_a(String[] args, int index) {
/* 53 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(@NotNull ICommand o) {
/* 58 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\commands\ClipCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */