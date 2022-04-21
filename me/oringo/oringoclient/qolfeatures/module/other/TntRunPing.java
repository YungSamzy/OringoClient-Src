/*    */ package me.oringo.oringoclient.qolfeatures.module.other;
/*    */ 
/*    */ import com.mojang.realmsclient.gui.ChatFormatting;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import java.util.Arrays;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.events.PacketReceivedEvent;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.network.play.server.S22PacketMultiBlockChange;
/*    */ import net.minecraft.network.play.server.S23PacketBlockChange;
/*    */ import net.minecraft.network.play.server.S25PacketBlockBreakAnim;
/*    */ import net.minecraft.scoreboard.ScoreObjective;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class TntRunPing
/*    */   extends Module
/*    */ {
/* 24 */   NumberSetting ping = new NumberSetting("Ping", 2000.0D, 1.0D, 2000.0D, 1.0D);
/*    */   
/*    */   public TntRunPing() {
/* 27 */     super("TNT Run ping", 0, Module.Category.OTHER);
/* 28 */     addSettings(new Setting[] { (Setting)this.ping });
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onPacket(PacketReceivedEvent event) {
/* 33 */     if (isToggled())
/*    */       return;  try {
/* 35 */       ScoreObjective objective = OringoClient.mc.field_71439_g.func_96123_co().func_96539_a(1);
/* 36 */       if (!Arrays.<String>asList(new String[] { "TNT RUN", "PVP RUN" }).contains(ChatFormatting.stripFormatting(objective.func_96678_d())))
/*    */         return; 
/* 38 */     } catch (Exception e) {
/*    */       return;
/*    */     } 
/* 41 */     if (event.packet instanceof S22PacketMultiBlockChange && (((S22PacketMultiBlockChange)event.packet).func_179844_a()).length <= 10) {
/* 42 */       event.setCanceled(true);
/* 43 */       for (S22PacketMultiBlockChange.BlockUpdateData changedBlock : ((S22PacketMultiBlockChange)event.packet).func_179844_a()) {
/* 44 */         threadBreak(event.context, changedBlock.func_180090_a(), changedBlock.func_180088_c());
/*    */       }
/*    */     } 
/* 47 */     if (event.packet instanceof S23PacketBlockChange) {
/*    */       
/* 49 */       if (OringoClient.stop.contains(((S23PacketBlockChange)event.packet).func_179827_b())) {
/* 50 */         event.setCanceled(true);
/*    */       }
/* 52 */       if (!(Minecraft.func_71410_x()).field_71441_e.func_180495_p(((S23PacketBlockChange)event.packet).func_179827_b()).func_177230_c().equals(Blocks.field_150325_L) && ((S23PacketBlockChange)event.packet).func_180728_a().func_177230_c().equals(Blocks.field_150350_a)) {
/* 53 */         event.setCanceled(true);
/* 54 */         threadBreak(event.context, ((S23PacketBlockChange)event.packet).func_179827_b(), ((S23PacketBlockChange)event.packet).func_180728_a());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void threadBreak(ChannelHandlerContext context, BlockPos pos, IBlockState state) {
/* 61 */     if (isToggled())
/* 62 */       return;  (Minecraft.func_71410_x()).field_71441_e.func_175656_a(pos, Blocks.field_150325_L.func_176223_P());
/*    */     
/* 64 */     (new Thread(() -> {
/*    */           OringoClient.stop.add(pos);
/*    */ 
/*    */           
/*    */           for (int i = 0; i < 10; i++) {
/*    */             try {
/*    */               Thread.sleep((long)((long)this.ping.getValue() / 10.0D));
/* 71 */             } catch (InterruptedException e) {
/*    */               e.printStackTrace();
/*    */             } 
/*    */             
/*    */             try {
/*    */               OringoClient.mc.func_147114_u().func_147294_a(new S25PacketBlockBreakAnim(pos.hashCode(), pos, i));
/* 77 */             } catch (Exception e) {
/*    */               e.printStackTrace();
/*    */             } 
/*    */           } 
/*    */           
/*    */           OringoClient.stop.remove(pos);
/*    */           
/*    */           (Minecraft.func_71410_x()).field_71441_e.func_175656_a(pos, state);
/* 85 */         })).start();
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\other\TntRunPing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */