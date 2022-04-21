/*     */ package me.oringo.oringoclient.commands;
/*     */ 
/*     */ import com.mojang.realmsclient.gui.ChatFormatting;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.utils.Notifications;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.inventory.GuiChest;
/*     */ import net.minecraft.command.CommandException;
/*     */ import net.minecraft.command.ICommand;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.ContainerChest;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraftforge.client.event.GuiOpenEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ 
/*     */ public class CheckNameCommand
/*     */   implements ICommand {
/*     */   public static String profileView;
/*     */   
/*     */   public String func_71517_b() {
/*  27 */     return "checkname";
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71518_a(ICommandSender sender) {
/*  32 */     return "/checkname";
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> func_71514_a() {
/*  37 */     return new ArrayList<>();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/*  42 */     Minecraft mc = Minecraft.func_71410_x();
/*  43 */     if (args.length != 1) {
/*  44 */       OringoClient.sendMessageWithPrefix("/checkname [IGN]");
/*     */       return;
/*     */     } 
/*  47 */     for (EntityPlayer entity : mc.field_71441_e.field_73010_i) {
/*  48 */       if (entity.func_70005_c_().equalsIgnoreCase(args[0])) {
/*  49 */         if (entity.func_70032_d((Entity)mc.field_71439_g) > 6.0F) {
/*  50 */           OringoClient.sendMessageWithPrefix("You are too far away!");
/*     */           return;
/*     */         } 
/*  53 */         if (mc.field_71439_g.func_70694_bm() != null) {
/*  54 */           OringoClient.sendMessageWithPrefix("You can't hold anything in your hand!");
/*     */           return;
/*     */         } 
/*  57 */         mc.field_71442_b.func_78768_b((EntityPlayer)mc.field_71439_g, (Entity)entity);
/*  58 */         profileView = args[0];
/*     */         return;
/*     */       } 
/*     */     } 
/*  62 */     OringoClient.sendMessageWithPrefix("That Player isn't loaded!");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_71519_b(ICommandSender sender) {
/*  67 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> func_180525_a(ICommandSender sender, String[] args, BlockPos pos) {
/*  72 */     ArrayList<String> matching = new ArrayList<>();
/*  73 */     for (EntityPlayer playerEntity : (Minecraft.func_71410_x()).field_71441_e.field_73010_i) {
/*  74 */       if (playerEntity.func_70005_c_().toLowerCase().startsWith(args[0].toLowerCase())) {
/*  75 */         matching.add(playerEntity.func_70005_c_());
/*     */       }
/*     */     } 
/*  78 */     return matching;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_82358_a(String[] args, int index) {
/*  83 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(ICommand o) {
/*  88 */     return 0;
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onGui(GuiOpenEvent event) {
/*  93 */     if (event.gui instanceof GuiChest && 
/*  94 */       profileView != null && ((ContainerChest)((GuiChest)event.gui).field_147002_h).func_85151_d().func_70005_c_().toLowerCase().startsWith(profileView.toLowerCase()))
/*  95 */       (new Thread(() -> {
/*     */             try {
/*     */               Thread.sleep(100L);
/*  98 */             } catch (InterruptedException e) {
/*     */               e.printStackTrace();
/*     */             } 
/*     */             ItemStack is = (Minecraft.func_71410_x()).field_71439_g.field_71070_bA.func_75139_a(22).func_75211_c();
/*     */             if (is != null && is.func_77973_b().equals(Items.field_151144_bL)) {
/*     */               String name = is.serializeNBT().func_74775_l("tag").func_74775_l("SkullOwner").func_74779_i("Name");
/*     */               (Minecraft.func_71410_x()).field_71439_g.func_71053_j();
/*     */               Notifications.showNotification("Oringo Client", "Real name: " + ChatFormatting.GOLD + name.replaceFirst("§", ""), 4000);
/*     */             } 
/*     */             profileView = null;
/* 108 */           })).start(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\commands\CheckNameCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */