/*    */ package me.oringo.oringoclient.commands;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.events.PacketReceivedEvent;
/*    */ import me.oringo.oringoclient.utils.MilliTimer;
/*    */ import net.minecraft.command.CommandException;
/*    */ import net.minecraft.command.ICommand;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.C01PacketChatMessage;
/*    */ import net.minecraft.network.play.client.C0DPacketCloseWindow;
/*    */ import net.minecraft.network.play.client.C0EPacketClickWindow;
/*    */ import net.minecraft.network.play.server.S2DPacketOpenWindow;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class WardrobeCommand
/*    */   implements ICommand
/*    */ {
/* 23 */   private int slot = -1;
/* 24 */   private MilliTimer timeout = new MilliTimer();
/*    */ 
/*    */   
/*    */   public String func_71517_b() {
/* 28 */     return "wd";
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71518_a(ICommandSender sender) {
/* 33 */     return "/wd";
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> func_71514_a() {
/* 38 */     return new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/* 43 */     if (args.length > 0) {
/* 44 */       this.slot = Math.min(Math.max(Integer.parseInt(args[0]), 1), 18);
/* 45 */       OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C01PacketChatMessage("/pets"));
/* 46 */       this.timeout.updateTime();
/*    */     } else {
/* 48 */       OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C01PacketChatMessage("/wd"));
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onPacket(PacketReceivedEvent event) {
/* 54 */     if (this.slot != -1 && event.packet instanceof S2DPacketOpenWindow) {
/* 55 */       if (this.timeout.hasTimePassed(2500L)) {
/* 56 */         this.slot = -1;
/*    */         return;
/*    */       } 
/* 59 */       if (((S2DPacketOpenWindow)event.packet).func_179840_c().func_150254_d().startsWith("Pets")) {
/* 60 */         int windowId = ((S2DPacketOpenWindow)event.packet).func_148901_c();
/* 61 */         OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C0EPacketClickWindow(windowId, 48, 0, 3, null, (short)0));
/* 62 */         OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C0EPacketClickWindow(windowId + 1, 32, 0, 3, null, (short)0));
/* 63 */         if (this.slot <= 9) {
/* 64 */           OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C0EPacketClickWindow(windowId + 2, 35 + this.slot, 0, 0, null, (short)0));
/* 65 */           OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C0DPacketCloseWindow(windowId + 2));
/*    */         } else {
/* 67 */           OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C0EPacketClickWindow(windowId + 2, 53, 0, 3, null, (short)0));
/* 68 */           OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C0EPacketClickWindow(windowId + 3, 35 + this.slot, 0, 0, null, (short)0));
/* 69 */           OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C0DPacketCloseWindow(windowId + 3));
/*    */         } 
/* 71 */         event.setCanceled(true);
/* 72 */       } else if (((S2DPacketOpenWindow)event.packet).func_179840_c().func_150254_d().startsWith("SkyBlock Menu")) {
/* 73 */         event.setCanceled(true);
/* 74 */       } else if (((S2DPacketOpenWindow)event.packet).func_179840_c().func_150254_d().startsWith("Wardrobe")) {
/* 75 */         event.setCanceled(true);
/* 76 */         this.slot = -1;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_71519_b(ICommandSender sender) {
/* 83 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> func_180525_a(ICommandSender sender, String[] args, BlockPos pos) {
/* 88 */     return new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_82358_a(String[] args, int index) {
/* 93 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(@NotNull ICommand o) {
/* 98 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\commands\WardrobeCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */