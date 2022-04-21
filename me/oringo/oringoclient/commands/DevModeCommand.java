/*     */ package me.oringo.oringoclient.commands;
/*     */ 
/*     */ import com.mojang.realmsclient.gui.ChatFormatting;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringodevmode.Transformer;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.command.CommandException;
/*     */ import net.minecraft.command.ICommand;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.world.IWorldAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class DevModeCommand
/*     */   implements ICommand {
/*  24 */   public static ArrayList<String> ignoredPackets = new ArrayList<>();
/*     */   
/*     */   public String func_71517_b() {
/*  27 */     return "oringodev";
/*     */   }
/*     */   
/*     */   public String func_71518_a(ICommandSender sender) {
/*  31 */     return "/oringodev";
/*     */   }
/*     */   
/*     */   public List<String> func_71514_a() {
/*  35 */     return new ArrayList<>();
/*     */   }
/*     */   
/*     */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/*     */     try {
/*  40 */       Field field_73021_x = World.class.getDeclaredField("field_73021_x");
/*  41 */       field_73021_x.setAccessible(true);
/*  42 */       List<IWorldAccess> accesses = (List<IWorldAccess>)field_73021_x.get((Minecraft.func_71410_x()).field_71441_e);
/*  43 */       for (IWorldAccess access : accesses) {
/*  44 */         OringoClient.sendMessageWithPrefix(access.getClass().getSimpleName());
/*     */       }
/*  46 */     } catch (Exception exception) {}
/*  47 */     if (args.length == 1) {
/*  48 */       File oringoDev = new File("OringoDev");
/*  49 */       OringoClient.sendMessage("§fPath: §a" + oringoDev.getAbsolutePath());
/*  50 */       if (args[0].equalsIgnoreCase("toggle")) {
/*  51 */         if (oringoDev.exists()) {
/*  52 */           oringoDev.delete();
/*     */         } else {
/*     */           try {
/*  55 */             oringoDev.createNewFile();
/*  56 */           } catch (IOException e) {
/*  57 */             e.printStackTrace();
/*     */           } 
/*     */         } 
/*  60 */         (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(ChatFormatting.RED + "Dev mod is: " + (oringoDev.exists() ? "enabled" : "disabled")));
/*  61 */         OringoClient.sendMessage("§cYou need to restart you game to apply the changes.");
/*     */       }
/*  63 */       else if (Transformer.classes.containsKey(args[0])) {
/*  64 */         String[] split = args[0].split("[.]");
/*  65 */         File file = new File(split[split.length - 1] + ".class");
/*     */         try {
/*  67 */           FileOutputStream fw = new FileOutputStream(file);
/*  68 */           fw.write((byte[])Transformer.classes.get(args[0]));
/*  69 */           fw.close();
/*  70 */         } catch (Exception e) {
/*  71 */           e.printStackTrace();
/*     */         } 
/*  73 */         OringoClient.sendMessage(String.format("§aSaving class as %s", new Object[] { file.getAbsolutePath() }));
/*     */       } else {
/*  75 */         OringoClient.sendMessage("§cClass not found.");
/*     */       } 
/*     */     } else {
/*     */       
/*  79 */       OringoClient.sendMessage("§c/oringodev <toggle/class name>");
/*     */     } 
/*  81 */     if (args.length == 2 && 
/*  82 */       args[0].equalsIgnoreCase("ignore")) {
/*  83 */       ignoredPackets.add(args[1]);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_71519_b(ICommandSender sender) {
/*  89 */     return true;
/*     */   }
/*     */   
/*     */   public List<String> func_180525_a(ICommandSender sender, String[] args, BlockPos pos) {
/*  93 */     return new ArrayList<>();
/*     */   }
/*     */   
/*     */   public boolean func_82358_a(String[] args, int index) {
/*  97 */     return false;
/*     */   }
/*     */   
/*     */   public int compareTo(ICommand o) {
/* 101 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\commands\DevModeCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */