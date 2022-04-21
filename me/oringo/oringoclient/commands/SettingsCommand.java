/*    */ package me.oringo.oringoclient.commands;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.command.CommandException;
/*    */ import net.minecraft.command.ICommand;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.scoreboard.Score;
/*    */ import net.minecraft.scoreboard.ScorePlayerTeam;
/*    */ import net.minecraft.scoreboard.Scoreboard;
/*    */ import net.minecraft.util.BlockPos;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SettingsCommand
/*    */   implements ICommand
/*    */ {
/*    */   public static boolean openSettings = false;
/*    */   
/*    */   public String func_71517_b() {
/* 23 */     return "oringo";
/*    */   }
/*    */   
/*    */   public String func_71518_a(ICommandSender sender) {
/* 27 */     return "/oringo";
/*    */   }
/*    */   
/*    */   public List<String> func_71514_a() {
/* 31 */     return new ArrayList<>();
/*    */   }
/*    */   
/*    */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/* 35 */     if (args.length != 0 && 
/* 36 */       args[0].equalsIgnoreCase("scoreboard") && OringoClient.mc.field_71439_g.func_96123_co() != null) {
/* 37 */       StringBuilder builder = new StringBuilder();
/*    */       
/* 39 */       Scoreboard sb = (Minecraft.func_71410_x()).field_71439_g.func_96123_co();
/* 40 */       List<Score> list = new ArrayList<>(sb.func_96534_i(sb.func_96539_a(1)));
/* 41 */       for (Score score : list) {
/* 42 */         ScorePlayerTeam team = sb.func_96509_i(score.func_96653_e());
/* 43 */         String s = team.func_96668_e() + score.func_96653_e() + team.func_96663_f();
/* 44 */         for (char c : s.toCharArray()) {
/* 45 */           if (c < 'Ā') builder.append(c); 
/*    */         } 
/* 47 */         builder.append("\n");
/*    */       } 
/* 49 */       builder.append(OringoClient.mc.field_71439_g.func_96123_co().func_96539_a(1).func_96678_d());
/* 50 */       System.out.println(builder);
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 55 */     OringoClient.clickGui.toggle();
/*    */   }
/*    */   
/*    */   public boolean func_71519_b(ICommandSender sender) {
/* 59 */     return true;
/*    */   }
/*    */   
/*    */   public List<String> func_180525_a(ICommandSender sender, String[] args, BlockPos pos) {
/* 63 */     return new ArrayList<>();
/*    */   }
/*    */   
/*    */   public boolean func_82358_a(String[] args, int index) {
/* 67 */     return false;
/*    */   }
/*    */   
/*    */   public int compareTo(ICommand o) {
/* 71 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\commands\SettingsCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */