/*     */ package me.oringo.oringoclient.qolfeatures.module.skyblock;
/*     */ import com.mojang.authlib.properties.Property;
/*     */ import com.mojang.realmsclient.gui.ChatFormatting;
/*     */ import java.util.ArrayList;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.events.MotionUpdateEvent;
/*     */ import me.oringo.oringoclient.events.PacketReceivedEvent;
/*     */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.StringSetting;
/*     */ import me.oringo.oringoclient.utils.RotationUtils;
/*     */ import me.oringo.oringoclient.utils.SkyblockUtils;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.C0DPacketCloseWindow;
/*     */ import net.minecraft.network.play.server.S02PacketChat;
/*     */ import net.minecraft.network.play.server.S2DPacketOpenWindow;
/*     */ import net.minecraft.tileentity.TileEntitySkull;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.util.Vec3i;
/*     */ import net.minecraftforge.event.world.WorldEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.EventPriority;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ 
/*     */ public class SecretAura extends Module {
/*     */   public NumberSetting reach;
/*     */   public StringSetting item;
/*     */   
/*     */   public SecretAura() {
/*  35 */     super("Secret Aura", 0, Module.Category.SKYBLOCK);
/*     */ 
/*     */ 
/*     */     
/*  39 */     this.reach = new NumberSetting("Reach", 5.0D, 2.0D, 6.0D, 0.1D);
/*  40 */     this.item = new StringSetting("Item");
/*  41 */     this.cancelChest = new BooleanSetting("Cancel chests", true); this.clickedCheck = new BooleanSetting("Clicked check", true); this.rotation = new BooleanSetting("Rotation", false);
/*     */     addSettings(new Setting[] { (Setting)this.reach, (Setting)this.item, (Setting)this.rotation, (Setting)this.cancelChest, (Setting)this.clickedCheck });
/*     */   }
/*  44 */   public BooleanSetting cancelChest; public BooleanSetting clickedCheck; public BooleanSetting rotation; public static ArrayList<BlockPos> clicked = new ArrayList<>();
/*     */   public static boolean inBoss;
/*     */   private boolean sent;
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.LOWEST)
/*     */   public void onUpdatePre(MotionUpdateEvent.Pre event) {
/*  50 */     if (OringoClient.mc.field_71439_g != null && isToggled() && SkyblockUtils.inDungeon && this.rotation.isEnabled()) {
/*  51 */       Vec3i vec3i = new Vec3i(10, 10, 10);
/*  52 */       for (BlockPos blockPos : BlockPos.func_177980_a((new BlockPos((Vec3i)OringoClient.mc.field_71439_g.func_180425_c())).func_177971_a(vec3i), new BlockPos((Vec3i)OringoClient.mc.field_71439_g.func_180425_c().func_177973_b(vec3i)))) {
/*  53 */         if (isValidBlock(blockPos) && 
/*  54 */           OringoClient.mc.field_71439_g.func_70011_f(blockPos.func_177958_n(), (blockPos.func_177956_o() - OringoClient.mc.field_71439_g.func_70047_e()), blockPos.func_177952_p()) < this.reach.getValue()) {
/*  55 */           float[] floats = RotationUtils.getAngles(new Vec3(blockPos.func_177958_n() + 0.5D, blockPos.func_177956_o() + 0.5D, blockPos.func_177952_p() + 0.5D));
/*  56 */           event.pitch = floats[1];
/*  57 */           event.yaw = floats[0];
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onTick(MotionUpdateEvent.Post event) {
/*  67 */     if (OringoClient.mc.field_71439_g != null && isToggled() && SkyblockUtils.inDungeon) {
/*  68 */       Vec3i vec3i = new Vec3i(10, 10, 10);
/*  69 */       for (BlockPos blockPos : BlockPos.func_177980_a((new BlockPos((Vec3i)OringoClient.mc.field_71439_g.func_180425_c())).func_177971_a(vec3i), new BlockPos((Vec3i)OringoClient.mc.field_71439_g.func_180425_c().func_177973_b(vec3i)))) {
/*  70 */         if (isValidBlock(blockPos) && OringoClient.mc.field_71439_g.func_70011_f(blockPos.func_177958_n(), (blockPos.func_177956_o() - OringoClient.mc.field_71439_g.func_70047_e()), blockPos.func_177952_p()) < this.reach.getValue()) {
/*  71 */           interactWithBlock(blockPos);
/*  72 */           if (this.rotation.isEnabled())
/*     */             break; 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private boolean isValidBlock(BlockPos blockPos) {
/*  79 */     Block block = OringoClient.mc.field_71441_e.func_180495_p(blockPos).func_177230_c();
/*  80 */     if (block == Blocks.field_150465_bP) {
/*  81 */       TileEntitySkull tileEntity = (TileEntitySkull)OringoClient.mc.field_71441_e.func_175625_s(blockPos);
/*  82 */       if (tileEntity.func_145904_a() == 3 && tileEntity.func_152108_a() != null && tileEntity.func_152108_a().getProperties() != null) {
/*  83 */         Property property = (Property)SkyblockUtils.firstOrNull(tileEntity.func_152108_a().getProperties().get("textures"));
/*  84 */         return (property != null && property.getValue().equals("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzRkYjRhZGZhOWJmNDhmZjVkNDE3MDdhZTM0ZWE3OGJkMjM3MTY1OWZjZDhjZDg5MzQ3NDlhZjRjY2U5YiJ9fX0=") && (!clicked.contains(blockPos) || !this.clickedCheck.isEnabled()));
/*     */       } 
/*     */     } 
/*  87 */     return ((block == Blocks.field_150442_at || block == Blocks.field_150486_ae || block == Blocks.field_150447_bR) && (!clicked.contains(blockPos) || !this.clickedCheck.isEnabled()));
/*     */   }
/*     */ 
/*     */   
/*     */   private void interactWithBlock(BlockPos pos) {
/*  92 */     for (int i = 0; i < 9; i++) {
/*  93 */       if (OringoClient.mc.field_71439_g.field_71071_by.func_70301_a(i) != null && OringoClient.mc.field_71439_g.field_71071_by.func_70301_a(i).func_82833_r().toLowerCase().contains(this.item.getValue().toLowerCase())) {
/*  94 */         int holding = OringoClient.mc.field_71439_g.field_71071_by.field_70461_c;
/*  95 */         OringoClient.mc.field_71439_g.field_71071_by.field_70461_c = i;
/*  96 */         if (OringoClient.mc.field_71441_e.func_180495_p(pos).func_177230_c() == Blocks.field_150442_at && !inBoss)
/*  97 */           OringoClient.mc.field_71442_b.func_178890_a(OringoClient.mc.field_71439_g, OringoClient.mc.field_71441_e, OringoClient.mc.field_71439_g.field_71071_by.func_70448_g(), pos, EnumFacing.func_176733_a(OringoClient.mc.field_71439_g.field_70177_z), new Vec3(0.0D, 0.0D, 0.0D)); 
/*  98 */         OringoClient.mc.field_71442_b.func_178890_a(OringoClient.mc.field_71439_g, OringoClient.mc.field_71441_e, OringoClient.mc.field_71439_g.field_71071_by.func_70448_g(), pos, EnumFacing.func_176733_a(OringoClient.mc.field_71439_g.field_70177_z), new Vec3(0.0D, 0.0D, 0.0D));
/*  99 */         OringoClient.mc.field_71439_g.field_71071_by.field_70461_c = holding;
/* 100 */         clicked.add(pos);
/*     */         return;
/*     */       } 
/*     */     } 
/* 104 */     if (!this.sent) {
/* 105 */       OringoClient.sendMessageWithPrefix("You don't have a required item in your hotbar!");
/* 106 */       this.sent = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onChat(PacketReceivedEvent event) {
/* 112 */     if (event.packet instanceof S02PacketChat && ChatFormatting.stripFormatting(((S02PacketChat)event.packet).func_148915_c().func_150254_d()).startsWith("[BOSS] Necron"))
/* 113 */       inBoss = true; 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onPacket(PacketReceivedEvent event) {
/* 118 */     if (event.packet instanceof S2DPacketOpenWindow && ChatFormatting.stripFormatting(((S2DPacketOpenWindow)event.packet).func_179840_c().func_150254_d()).equals("Chest") && SkyblockUtils.inDungeon && this.cancelChest.isEnabled()) {
/* 119 */       event.setCanceled(true);
/* 120 */       OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C0DPacketCloseWindow(((S2DPacketOpenWindow)event.packet).func_148901_c()));
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void clear(WorldEvent.Load event) {
/* 126 */     inBoss = false;
/* 127 */     clicked.clear();
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\skyblock\SecretAura.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */