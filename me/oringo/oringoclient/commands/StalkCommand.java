/*    */ package me.oringo.oringoclient.commands;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import me.oringo.oringoclient.utils.Notifications;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.command.CommandException;
/*    */ import net.minecraft.command.ICommand;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.BlockPos;
/*    */ 
/*    */ public class StalkCommand
/*    */   implements ICommand {
/*    */   public static UUID stalking;
/*    */   
/*    */   public String func_71517_b() {
/* 19 */     return "stalk";
/*    */   }
/*    */   
/*    */   public String func_71518_a(ICommandSender sender) {
/* 23 */     return "/stalk";
/*    */   }
/*    */   
/*    */   public List<String> func_71514_a() {
/* 27 */     return new ArrayList<>();
/*    */   }
/*    */   
/*    */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/* 31 */     stalking = null;
/* 32 */     if (args.length == 1) {
/* 33 */       for (EntityPlayer player : (Minecraft.func_71410_x()).field_71441_e.field_73010_i) {
/* 34 */         if (player.func_70005_c_().equalsIgnoreCase(args[0])) {
/* 35 */           stalking = player.func_110124_au();
/* 36 */           Notifications.showNotification("Oringo Client", "Enabled stalking!", 1000);
/*    */           return;
/*    */         } 
/*    */       } 
/* 40 */       Notifications.showNotification("Oringo Client", "Player not found!", 1000);
/*    */       return;
/*    */     } 
/* 43 */     Notifications.showNotification("Oringo Client", "Disabled Stalking!", 1000);
/*    */   }
/*    */   
/*    */   public boolean func_71519_b(ICommandSender sender) {
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> func_180525_a(ICommandSender sender, String[] args, BlockPos pos) {
/* 52 */     return new ArrayList<>();
/*    */   }
/*    */   
/*    */   public boolean func_82358_a(String[] args, int index) {
/* 56 */     return false;
/*    */   }
/*    */   
/*    */   public int compareTo(ICommand o) {
/* 60 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\commands\StalkCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */