/*     */ package me.oringo.oringoclient.commands;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.utils.Notifications;
/*     */ import me.oringo.oringoclient.utils.SkyblockUtils;
/*     */ import net.minecraft.client.gui.inventory.GuiChest;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.command.CommandException;
/*     */ import net.minecraft.command.ICommand;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.ContainerChest;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraftforge.client.event.GuiOpenEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ 
/*     */ public class JerryBoxMacro
/*     */   implements ICommand
/*     */ {
/*     */   public static Thread jerrybox;
/*     */   
/*     */   public String func_71517_b() {
/*  25 */     return "jerrybox";
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71518_a(ICommandSender sender) {
/*  30 */     return "/jerrybox";
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> func_71514_a() {
/*  35 */     return new ArrayList<>();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/*  40 */     if (jerrybox != null) {
/*  41 */       jerrybox.stop();
/*  42 */       jerrybox = null;
/*  43 */       Notifications.showNotification("Oringo Client", "Auto Jerry Box disabled!", 1000);
/*     */       return;
/*     */     } 
/*  46 */     if (SkyblockUtils.getDisplayName(OringoClient.mc.field_71439_g.func_70694_bm()).endsWith(" Jerry Box")) {
/*  47 */       (jerrybox = new Thread(() -> {
/*     */             Notifications.showNotification("Oringo Client", "Auto Jerry Box enabled!", 1000);
/*     */             SkyblockUtils.rightClick();
/*     */             while (true) {
/*     */               try {
/*     */                 KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_74370_x.func_151463_i(), false);
/*     */                 KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_74351_w.func_151463_i(), false);
/*     */                 KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_74368_y.func_151463_i(), false);
/*     */                 KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_74366_z.func_151463_i(), false);
/*     */                 KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_74314_A.func_151463_i(), false);
/*     */                 KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_74311_E.func_151463_i(), false);
/*     */                 if (!SkyblockUtils.getDisplayName(OringoClient.mc.field_71439_g.func_70694_bm()).endsWith(" Jerry Box")) {
/*     */                   if (OringoClient.mc.field_71439_g.field_71070_bA instanceof ContainerChest && ((ContainerChest)OringoClient.mc.field_71439_g.field_71070_bA).func_85151_d().func_70005_c_().equalsIgnoreCase("open a jerry box"))
/*     */                     OringoClient.mc.field_71439_g.func_71053_j(); 
/*     */                   for (int i = 0; i < 45; i++) {
/*     */                     if (OringoClient.mc.field_71439_g.field_71070_bA.func_75139_a(i).func_75211_c() != null && OringoClient.mc.field_71439_g.field_71070_bA.func_75139_a(i).func_75211_c().func_82833_r().endsWith(" Jerry Box")) {
/*     */                       OringoClient.mc.field_71442_b.func_78753_a(OringoClient.mc.field_71439_g.field_71070_bA.field_75152_c, i, OringoClient.mc.field_71439_g.field_71071_by.field_70461_c, 2, (EntityPlayer)OringoClient.mc.field_71439_g);
/*     */                       Thread.sleep(50L);
/*     */                       SkyblockUtils.rightClick();
/*     */                       break;
/*     */                     } 
/*     */                   } 
/*     */                   Thread.sleep(1000L);
/*     */                 } 
/*     */                 Thread.sleep(50L);
/*  72 */               } catch (InterruptedException e) {
/*     */                 e.printStackTrace();
/*     */               } 
/*     */             } 
/*  76 */           })).start();
/*     */     } else {
/*  78 */       Notifications.showNotification("Oringo Client", "You need to hold a jerry box!", 2000);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_71519_b(ICommandSender sender) {
/*  84 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> func_180525_a(ICommandSender sender, String[] args, BlockPos pos) {
/*  89 */     return new ArrayList<>();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_82358_a(String[] args, int index) {
/*  94 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(ICommand o) {
/*  99 */     return 0;
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onGui(GuiOpenEvent event) {
/* 104 */     if (event.gui instanceof GuiChest && ((ContainerChest)((GuiChest)event.gui).field_147002_h).func_85151_d().func_70005_c_().equalsIgnoreCase("open a jerry box") && jerrybox != null)
/* 105 */       (new Thread(() -> {
/*     */             try {
/*     */               Thread.sleep(1L);
/*     */               OringoClient.mc.field_71442_b.func_78753_a(OringoClient.mc.field_71439_g.field_71070_bA.field_75152_c, 22, 0, 3, (EntityPlayer)OringoClient.mc.field_71439_g);
/*     */               Thread.sleep(1L);
/*     */               SkyblockUtils.rightClick();
/* 111 */             } catch (InterruptedException e) {
/*     */               e.printStackTrace();
/*     */             } 
/* 114 */           })).start(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\commands\JerryBoxMacro.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */