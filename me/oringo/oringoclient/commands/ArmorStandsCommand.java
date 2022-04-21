/*    */ package me.oringo.oringoclient.commands;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Comparator;
/*    */ import java.util.List;
/*    */ import me.oringo.oringoclient.utils.Notifications;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.command.CommandException;
/*    */ import net.minecraft.command.ICommand;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.event.ClickEvent;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.ChatComponentText;
/*    */ import net.minecraft.util.ChatStyle;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ 
/*    */ public class ArmorStandsCommand implements ICommand {
/*    */   public String func_71517_b() {
/* 21 */     return "armorstands";
/*    */   }
/*    */   
/*    */   public String func_71518_a(ICommandSender sender) {
/* 25 */     return "/armorstands";
/*    */   }
/*    */   
/*    */   public List<String> func_71514_a() {
/* 29 */     return new ArrayList<>();
/*    */   }
/*    */   
/*    */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/* 33 */     if (args.length == 1) {
/*    */       try {
/* 35 */         Entity entityByID = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(Integer.parseInt(args[0]));
/* 36 */         (Minecraft.func_71410_x()).field_71442_b.func_78768_b((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g, entityByID);
/* 37 */       } catch (Exception e) {
/* 38 */         Notifications.showNotification("Oringo Client", "This armor stand is too far away!", 1000);
/*    */       } 
/*    */       return;
/*    */     } 
/* 42 */     (Minecraft.func_71410_x()).field_71441_e.field_72996_f.stream()
/* 43 */       .filter(entity -> entity instanceof net.minecraft.entity.item.EntityArmorStand)
/* 44 */       .filter(entity -> entity.func_145818_k_())
/* 45 */       .filter(entity -> (entity.func_145748_c_().func_150254_d().length() > 5))
/* 46 */       .sorted(Comparator.comparingDouble(entity -> entity.func_70032_d((Entity)(Minecraft.func_71410_x()).field_71439_g)))
/* 47 */       .forEach(ArmorStandsCommand::sendEntityInteract);
/*    */   }
/*    */   
/*    */   private static void sendEntityInteract(Entity entity) {
/* 51 */     ChatComponentText chatComponentText = new ChatComponentText("Name: " + entity.func_145748_c_().func_150254_d());
/* 52 */     ChatStyle style = new ChatStyle();
/* 53 */     style.func_150241_a(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/armorstands " + entity.func_145782_y()));
/* 54 */     chatComponentText.func_150255_a(style);
/* 55 */     (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)chatComponentText);
/*    */   }
/*    */   
/*    */   public boolean func_71519_b(ICommandSender sender) {
/* 59 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> func_180525_a(ICommandSender sender, String[] args, BlockPos pos) {
/* 64 */     return new ArrayList<>();
/*    */   }
/*    */   
/*    */   public boolean func_82358_a(String[] args, int index) {
/* 68 */     return false;
/*    */   }
/*    */   
/*    */   public int compareTo(ICommand o) {
/* 72 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\commands\ArmorStandsCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */