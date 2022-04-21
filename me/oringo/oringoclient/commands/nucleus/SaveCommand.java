/*    */ package me.oringo.oringoclient.commands.nucleus;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.utils.Notifications;
/*    */ import net.minecraft.command.CommandException;
/*    */ import net.minecraft.command.ICommand;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class SaveCommand
/*    */   implements ICommand
/*    */ {
/* 19 */   public static HashMap<String, BlockPos> posHashMap = new HashMap<>();
/* 20 */   public static HashMap<String, Integer> idHashMap = new HashMap<>();
/*    */ 
/*    */   
/*    */   public String func_71517_b() {
/* 24 */     return "save";
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71518_a(ICommandSender iCommandSender) {
/* 29 */     return "/save";
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> func_71514_a() {
/* 34 */     return new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_71515_b(ICommandSender iCommandSender, String[] args) throws CommandException {
/* 39 */     if (args.length < 1) {
/* 40 */       Notifications.showNotification("Oringo Client", "/save name", 2500);
/*    */       return;
/*    */     } 
/* 43 */     if (args[0].toLowerCase().equals("list")) {
/* 44 */       idHashMap.forEach((key, value) -> OringoClient.sendMessageWithPrefix(key + " " + value.toString()));
/*    */ 
/*    */ 
/*    */       
/* 48 */       posHashMap.forEach((key, value) -> OringoClient.sendMessageWithPrefix(key + " " + value.toString()));
/*    */ 
/*    */ 
/*    */     
/*    */     }
/* 53 */     else if (OringoClient.mc.field_71476_x != null) {
/* 54 */       switch (OringoClient.mc.field_71476_x.field_72313_a) {
/*    */         case ENTITY:
/* 56 */           if (OringoClient.mc.field_71476_x.field_72308_g != null) {
/* 57 */             if (!posHashMap.containsKey(args[0])) {
/* 58 */               idHashMap.put(args[0], Integer.valueOf(OringoClient.mc.field_71476_x.field_72308_g.func_145782_y()));
/*    */             } else {
/* 60 */               idHashMap.replace(args[0], Integer.valueOf(OringoClient.mc.field_71476_x.field_72308_g.func_145782_y()));
/*    */             } 
/* 62 */             Notifications.showNotification("Oringo Client", "Added " + OringoClient.mc.field_71476_x.field_72308_g.func_145782_y() + " to list", 2000);
/*    */           } 
/*    */           break;
/*    */         case BLOCK:
/* 66 */           if (OringoClient.mc.field_71476_x.func_178782_a() != null && OringoClient.mc.field_71441_e.func_180495_p(OringoClient.mc.field_71476_x.func_178782_a()).func_177230_c() != Blocks.field_150350_a) {
/* 67 */             if (!posHashMap.containsKey(args[0])) {
/* 68 */               posHashMap.put(args[0], OringoClient.mc.field_71476_x.func_178782_a());
/*    */             } else {
/* 70 */               posHashMap.replace(args[0], OringoClient.mc.field_71476_x.func_178782_a());
/*    */             } 
/* 72 */             Notifications.showNotification("Oringo Client", "Added " + OringoClient.mc.field_71476_x.func_178782_a() + " to list", 2000);
/*    */           } 
/*    */           break;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_71519_b(ICommandSender iCommandSender) {
/* 82 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> func_180525_a(ICommandSender iCommandSender, String[] strings, BlockPos blockPos) {
/* 87 */     return new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_82358_a(String[] strings, int i) {
/* 92 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(@NotNull ICommand o) {
/* 97 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\commands\nucleus\SaveCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */