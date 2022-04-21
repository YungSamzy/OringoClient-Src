/*    */ package me.oringo.oringoclient.commands;
/*    */ 
/*    */ import java.io.BufferedWriter;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStreamWriter;
/*    */ import java.nio.charset.StandardCharsets;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import net.minecraft.command.CommandException;
/*    */ import net.minecraft.command.ICommand;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.util.BlockPos;
/*    */ 
/*    */ public class PacketLoggerCommand
/*    */   implements ICommand
/*    */ {
/* 19 */   public static BufferedWriter writer = null;
/*    */   
/*    */   public String func_71517_b() {
/* 22 */     return "packetlog";
/*    */   }
/*    */   
/*    */   public String func_71518_a(ICommandSender sender) {
/* 26 */     return "/packetlog";
/*    */   }
/*    */   
/*    */   public List<String> func_71514_a() {
/* 30 */     return new ArrayList<>();
/*    */   }
/*    */   
/*    */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/* 34 */     if (writer != null) {
/* 35 */       OringoClient.sendMessageWithPrefix("Packet logger disabled.");
/*    */       try {
/* 37 */         writer.close();
/* 38 */       } catch (IOException e) {
/* 39 */         e.printStackTrace();
/*    */       } 
/* 41 */       writer = null;
/*    */     } else {
/* 43 */       OringoClient.sendMessageWithPrefix("Packet logger enabled.");
/*    */       
/*    */       try {
/* 46 */         writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("packetlog.txt"), StandardCharsets.UTF_8));
/* 47 */       } catch (Exception e) {
/* 48 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean func_71519_b(ICommandSender sender) {
/* 54 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> func_180525_a(ICommandSender sender, String[] args, BlockPos pos) {
/* 59 */     return new ArrayList<>();
/*    */   }
/*    */   
/*    */   public boolean func_82358_a(String[] args, int index) {
/* 63 */     return false;
/*    */   }
/*    */   
/*    */   public int compareTo(ICommand o) {
/* 67 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\commands\PacketLoggerCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */