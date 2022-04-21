/*     */ package me.oringo.oringoclient.commands;
/*     */ 
/*     */ import com.mojang.realmsclient.gui.ChatFormatting;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.events.BlockChangeEvent;
/*     */ import me.oringo.oringoclient.events.PacketReceivedEvent;
/*     */ import me.oringo.oringoclient.utils.Notifications;
/*     */ import net.minecraft.block.BlockCrops;
/*     */ import net.minecraft.block.BlockNetherWart;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.command.CommandException;
/*     */ import net.minecraft.command.ICommand;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
/*     */ import net.minecraft.network.play.server.S02PacketChat;
/*     */ import net.minecraft.network.play.server.S12PacketEntityVelocity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraftforge.event.world.WorldEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class FarmingMacro
/*     */   implements ICommand
/*     */ {
/*     */   private boolean toggled;
/*     */   private boolean left;
/*     */   private boolean direction;
/*     */   private boolean cage;
/*  37 */   private int ticksStanding = 0, susPackets = 0, pause = 0, nukerCheck = 0;
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onTick(TickEvent.ClientTickEvent e) {
/*  42 */     if (OringoClient.mc.field_71439_g == null || OringoClient.mc.field_71441_e == null || !this.toggled || e.phase == TickEvent.Phase.END || this.cage)
/*  43 */       return;  this.pause--;
/*  44 */     this.nukerCheck--;
/*  45 */     KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_74351_w.func_151463_i(), false);
/*  46 */     KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_74370_x.func_151463_i(), false);
/*  47 */     KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_74366_z.func_151463_i(), false);
/*  48 */     KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_151444_V.func_151463_i(), false);
/*  49 */     if (OringoClient.mc.field_71439_g.field_70173_aa % 600 == 0) {
/*  50 */       this.susPackets = 0;
/*  51 */       OringoClient.mc.field_71439_g.func_71165_d("/setspawn");
/*     */     } 
/*     */     
/*  54 */     if (OringoClient.mc.field_71439_g.func_70694_bm() == null || !(OringoClient.mc.field_71439_g.func_70694_bm().func_77973_b() instanceof net.minecraft.item.ItemHoe))
/*  55 */       if (this.pause < 5) {
/*  56 */         this.pause = 10;
/*     */       } else {
/*  58 */         if (OringoClient.mc.field_71439_g.func_70694_bm() != null && OringoClient.mc.field_71439_g.func_70694_bm().func_77973_b() instanceof net.minecraft.item.ItemMap)
/*     */           return; 
/*  60 */         if (this.pause == 5) {
/*  61 */           for (Slot slot : OringoClient.mc.field_71439_g.field_71070_bA.field_75151_b) {
/*  62 */             if (slot.func_75216_d() && slot.func_75211_c().func_77973_b() instanceof net.minecraft.item.ItemHoe) {
/*  63 */               numberClick(slot.field_75222_d, OringoClient.mc.field_71439_g.field_71071_by.field_70461_c);
/*  64 */               OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C08PacketPlayerBlockPlacement(OringoClient.mc.field_71439_g.func_70694_bm()));
/*     */               return;
/*     */             } 
/*     */           } 
/*     */         }
/*     */         return;
/*     */       }  
/*  71 */     if (OringoClient.mc.field_71441_e.func_180495_p(new BlockPos(OringoClient.mc.field_71439_g.field_70165_t, OringoClient.mc.field_71439_g.field_70163_u - 0.5D, OringoClient.mc.field_71439_g.field_70161_v)).func_177230_c() == Blocks.field_150357_h && this.pause < 1) {
/*  72 */       this.pause = 600;
/*  73 */       OringoClient.mc.field_71439_g.func_71165_d("/setguestspawn");
/*     */       
/*  75 */       (new Thread(() -> {
/*     */             try {
/*     */               Thread.sleep(1800L);
/*     */               OringoClient.mc.field_71439_g.func_71165_d("/ac wtf");
/*  79 */             } catch (InterruptedException ex) {
/*     */               ex.printStackTrace();
/*     */             } 
/*  82 */           })).start();
/*     */       
/*     */       return;
/*     */     } 
/*  86 */     if (this.pause > 0 || this.susPackets > 7 || OringoClient.mc.field_71462_r instanceof net.minecraft.client.gui.inventory.GuiContainer)
/*  87 */       return;  if (Math.abs(OringoClient.mc.field_71439_g.field_70165_t - OringoClient.mc.field_71439_g.field_70142_S) < 0.15D && Math.abs(OringoClient.mc.field_71439_g.field_70161_v - OringoClient.mc.field_71439_g.field_70136_U) < 0.15D) {
/*  88 */       this.ticksStanding++;
/*  89 */       if (this.ticksStanding > 10) {
/*  90 */         this.left = !this.left;
/*  91 */         this.ticksStanding = 0;
/*     */       } 
/*  93 */     } else if (this.ticksStanding > 0) {
/*  94 */       this.ticksStanding--;
/*     */     } 
/*  96 */     if (OringoClient.mc.field_71476_x != null && OringoClient.mc.field_71476_x.func_178782_a() != null && (OringoClient.mc.field_71441_e.func_180495_p(OringoClient.mc.field_71476_x.func_178782_a()).func_177230_c() instanceof BlockCrops || OringoClient.mc.field_71441_e.func_180495_p(OringoClient.mc.field_71476_x.func_178782_a()).func_177230_c() instanceof BlockNetherWart)) {
/*  97 */       OringoClient.mc.field_71439_g.func_71038_i();
/*  98 */       OringoClient.mc.field_71442_b.func_180511_b(OringoClient.mc.field_71476_x.func_178782_a(), OringoClient.mc.field_71476_x.field_178784_b);
/*     */     } 
/*     */     
/* 101 */     KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_74351_w.func_151463_i(), true);
/* 102 */     KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_74370_x.func_151463_i(), this.left);
/* 103 */     KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_74366_z.func_151463_i(), !this.left);
/* 104 */     KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_151444_V.func_151463_i(), true);
/*     */   }
/*     */   
/*     */   public void numberClick(int slot, int button) {
/* 108 */     OringoClient.mc.field_71442_b.func_78753_a(OringoClient.mc.field_71439_g.field_71069_bz.field_75152_c, slot, button, 2, (EntityPlayer)OringoClient.mc.field_71439_g);
/*     */   }
/*     */   
/*     */   @SubscribeEvent(receiveCanceled = true)
/*     */   public void onPacket(PacketReceivedEvent event) {
/* 113 */     if (this.toggled) {
/* 114 */       if (event.packet instanceof S02PacketChat && ChatFormatting.stripFormatting(((S02PacketChat)event.packet).func_148915_c().func_150254_d()).startsWith("Warped from the ")) {
/* 115 */         this.direction = !this.direction;
/*     */       }
/* 117 */       if (event.packet instanceof net.minecraft.network.play.server.S27PacketExplosion || (event.packet instanceof S12PacketEntityVelocity && OringoClient.mc.field_71441_e.func_73045_a(((S12PacketEntityVelocity)event.packet).func_149412_c()) == OringoClient.mc.field_71439_g)) {
/* 118 */         this.susPackets++;
/*     */       }
/* 120 */       if (event.packet instanceof net.minecraft.network.play.server.S08PacketPlayerPosLook) this.pause = 20; 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onBlock(BlockChangeEvent event) {
/* 126 */     if (this.toggled && this.pause < 1 && OringoClient.mc.field_71441_e.func_175726_f(event.pos) != null && !(OringoClient.mc.field_71441_e.func_180495_p(event.pos).func_177230_c() instanceof BlockCrops) && !(OringoClient.mc.field_71441_e.func_180495_p(event.pos).func_177230_c() instanceof BlockNetherWart) && OringoClient.mc.field_71439_g.func_70011_f(event.pos.func_177958_n(), event.pos.func_177956_o(), event.pos.func_177952_p()) < 7.0D && (event.state.func_177230_c() instanceof BlockCrops || event.state.func_177230_c() instanceof BlockNetherWart) && ((event.state.func_177230_c() instanceof BlockCrops) ? (Integer)event.state.func_177229_b((IProperty)BlockCrops.field_176488_a) : (Integer)event.state.func_177229_b((IProperty)BlockNetherWart.field_176486_a)).intValue() == 7 && 
/* 127 */       this.nukerCheck++ > 20) {
/* 128 */       OringoClient.sendMessageWithPrefix("Nuker check");
/* 129 */       this.pause = 60;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onWorldLoad(WorldEvent.Load e) {
/* 136 */     if (!this.toggled)
/* 137 */       return;  this.toggled = false;
/* 138 */     KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_74351_w.func_151463_i(), false);
/* 139 */     KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_74370_x.func_151463_i(), false);
/* 140 */     KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_74366_z.func_151463_i(), false);
/* 141 */     KeyBinding.func_74510_a(OringoClient.mc.field_71474_y.field_151444_V.func_151463_i(), false);
/* 142 */     (new Thread(() -> {
/*     */           try {
/*     */             for (int i = 1; i < 4; i++) {
/*     */               Thread.sleep(5000L);
/*     */               if (OringoClient.mc.field_71439_g != null) {
/*     */                 OringoClient.mc.field_71439_g.func_71165_d((i == 1) ? "/l" : ((i == 2) ? "/play skyblock" : "/is"));
/*     */               }
/*     */             } 
/*     */             Thread.sleep(10000L);
/*     */             this.toggled = true;
/* 152 */           } catch (InterruptedException ex) {
/*     */             
/*     */             ex.printStackTrace();
/*     */           } 
/* 156 */         })).start();
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71517_b() {
/* 161 */     return "farm";
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71518_a(ICommandSender sender) {
/* 166 */     return "/farm";
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> func_71514_a() {
/* 171 */     return new ArrayList<>();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/* 176 */     this.toggled = !this.toggled;
/* 177 */     Notifications.showNotification("Oringo Client", this.toggled ? "Started farm!" : "Stopped farm!", 1500);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_71519_b(ICommandSender sender) {
/* 182 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> func_180525_a(ICommandSender sender, String[] args, BlockPos pos) {
/* 187 */     return new ArrayList<>();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_82358_a(String[] args, int index) {
/* 192 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(@NotNull ICommand o) {
/* 197 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\commands\FarmingMacro.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */