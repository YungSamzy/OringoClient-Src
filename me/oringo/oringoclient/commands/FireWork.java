/*    */ package me.oringo.oringoclient.commands;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.utils.Notifications;
/*    */ import net.minecraft.command.CommandException;
/*    */ import net.minecraft.command.ICommand;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTBase;
/*    */ import net.minecraft.nbt.NBTTagByte;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.nbt.NBTTagList;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
/*    */ import net.minecraft.util.BlockPos;
/*    */ 
/*    */ public class FireWork
/*    */   implements ICommand {
/*    */   public String func_71517_b() {
/* 23 */     return "firework";
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71518_a(ICommandSender sender) {
/* 28 */     return "/firework";
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> func_71514_a() {
/* 33 */     return new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/* 38 */     if (args.length == 2) {
/* 39 */       ItemStack item = new ItemStack(Items.field_151152_bP);
/* 40 */       item.field_77994_a = 64;
/* 41 */       item.func_151001_c("crash");
/* 42 */       NBTTagList value = new NBTTagList();
/* 43 */       NBTTagCompound nbtTagCompound = item.serializeNBT();
/* 44 */       NBTTagCompound display = nbtTagCompound.func_74775_l("tag").func_74775_l("Fireworks");
/*    */       
/* 46 */       NBTTagList explosions = new NBTTagList();
/* 47 */       NBTTagCompound exp1 = new NBTTagCompound();
/*    */       
/* 49 */       exp1.func_74782_a("Type", (NBTBase)new NBTTagByte((byte)1));
/* 50 */       exp1.func_74782_a("Flicker", (NBTBase)new NBTTagByte((byte)1));
/* 51 */       exp1.func_74782_a("Trail", (NBTBase)new NBTTagByte((byte)3));
/*    */       
/* 53 */       int[] colors = new int[Integer.parseInt(args[1])]; int i;
/* 54 */       for (i = 0; i < Integer.parseInt(args[1]); i++) {
/* 55 */         colors[i] = 261799 + i;
/*    */       }
/* 57 */       exp1.func_74783_a("Colors", colors);
/* 58 */       colors = new int[100];
/* 59 */       for (i = 0; i < 100; i++) {
/* 60 */         colors[i] = 11250603 + i;
/*    */       }
/* 62 */       exp1.func_74783_a("FadeColors", colors);
/* 63 */       for (int x = 0; x < Integer.parseInt(args[0]); x++) {
/* 64 */         explosions.func_74742_a((NBTBase)exp1);
/*    */       }
/* 66 */       display.func_74782_a("Explosions", (NBTBase)explosions);
/*    */       
/* 68 */       nbtTagCompound.func_74775_l("tag").func_74782_a("Fireworks", (NBTBase)display);
/* 69 */       Notifications.showNotification("Oringo Client", "NBT Size: " + nbtTagCompound.toString().length(), 2000);
/* 70 */       item.deserializeNBT(nbtTagCompound);
/* 71 */       OringoClient.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new C10PacketCreativeInventoryAction(36, item));
/*    */     } else {
/* 73 */       Notifications.showNotification("Oringo Client", "/firework explosions colors", 1000);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_71519_b(ICommandSender sender) {
/* 80 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> func_180525_a(ICommandSender sender, String[] args, BlockPos pos) {
/* 85 */     return new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_82358_a(String[] args, int index) {
/* 90 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(ICommand o) {
/* 95 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\commands\FireWork.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */