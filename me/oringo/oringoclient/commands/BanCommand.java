/*    */ package me.oringo.oringoclient.commands;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import net.minecraft.command.CommandException;
/*    */ import net.minecraft.command.ICommand;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BanCommand
/*    */   implements ICommand
/*    */ {
/*    */   public String func_71517_b() {
/* 22 */     return "selfban";
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71518_a(ICommandSender iCommandSender) {
/* 27 */     return "/selfban";
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> func_71514_a() {
/* 32 */     return new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_71515_b(ICommandSender iCommandSender, String[] strings) throws CommandException {
/* 37 */     if (strings.length == 1 && strings[0].equals("confirm")) {
/* 38 */       OringoClient.sendMessageWithPrefix("You will get banned in ~3 seconds!");
/* 39 */       for (int i = 0; i < 10; i++) {
/* 40 */         OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C08PacketPlayerBlockPlacement(new BlockPos((new Random()).nextInt(), (new Random()).nextInt(), (new Random()).nextInt()), 1, OringoClient.mc.field_71439_g.field_71071_by.func_70448_g(), 0.0F, 0.0F, 0.0F));
/*    */       }
/*    */     } else {
/*    */       
/* 44 */       OringoClient.sendMessageWithPrefix("/selfban confirm");
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_71519_b(ICommandSender iCommandSender) {
/* 50 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> func_180525_a(ICommandSender iCommandSender, String[] strings, BlockPos blockPos) {
/* 55 */     return new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_82358_a(String[] strings, int i) {
/* 60 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(@NotNull ICommand o) {
/* 65 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\commands\BanCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */