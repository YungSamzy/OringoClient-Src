/*     */ package me.oringo.oringoclient.qolfeatures.module.skyblock;
/*     */ 
/*     */ import com.mojang.realmsclient.gui.ChatFormatting;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.events.MotionUpdateEvent;
/*     */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.ModeSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.network.play.client.C07PacketPlayerDigging;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraftforge.client.event.ClientChatReceivedEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ 
/*     */ public class MetalDetector extends Module {
/*     */   public ModeSetting mode;
/*     */   public NumberSetting tries;
/*     */   
/*  20 */   public MetalDetector() { super("Metal Detector", Module.Category.SKYBLOCK);
/*     */ 
/*     */ 
/*     */     
/*  24 */     this.mode = new ModeSetting("Mode", "Brute", new String[] { "Brute", "Smart" });
/*  25 */     this.tries = new NumberSetting("Tries", 4.0D, 1.0D, 10.0D, 1.0D)
/*     */       {
/*     */         public boolean isHidden() {
/*  28 */           return !MetalDetector.this.mode.is("Brute");
/*     */         }
/*     */       };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  75 */     this.distances = 0.0D;
/*  76 */     this.coords = null;
/*     */     
/*  78 */     this.showChestLocation = 0;
/*     */     
/*  80 */     this.searchingForBase = false;
/*  81 */     this.searchForBaseCooldown = 0; addSettings(new Setting[] { (Setting)this.mode, (Setting)this.tries }); } private static BlockPos[] chests = new BlockPos[] { 
/*     */       new BlockPos(-7, 26, -2), new BlockPos(-15, 26, 31), new BlockPos(-17, 26, 19), new BlockPos(47, 25, 33), new BlockPos(36, 26, 45), new BlockPos(48, 27, 45), new BlockPos(45, 27, -13), new BlockPos(-38, 26, 21), new BlockPos(42, 26, 27), new BlockPos(29, 27, -7), 
/*     */       new BlockPos(22, 26, -15), new BlockPos(-7, 27, -26), new BlockPos(-2, 26, -6), new BlockPos(43, 27, -21), new BlockPos(10, 26, -11), new BlockPos(17, 26, 49), new BlockPos(19, 26, -17), new BlockPos(-35, 27, 35), new BlockPos(25, 27, 5), new BlockPos(-37, 24, 46), 
/*     */       new BlockPos(-24, 26, 49), new BlockPos(-7, 26, 48), new BlockPos(-14, 27, -24), new BlockPos(-18, 27, 44), new BlockPos(-1, 26, -23), new BlockPos(41, 25, -37), new BlockPos(19, 26, -38), new BlockPos(-7, 27, 27), new BlockPos(42, 26, 19), new BlockPos(42, 26, 19), 
/*     */       new BlockPos(-33, 27, 31), new BlockPos(6, 27, 25), new BlockPos(-2, 26, -17), new BlockPos(-15, 27, 5), new BlockPos(-20, 27, -12), new BlockPos(-25, 26, 30), new BlockPos(28, 27, -35), new BlockPos(-19, 27, -22), new BlockPos(4, 26, -15), new BlockPos(36, 26, 17) }; private double distances; @SubscribeEvent public void onUpdate(MotionUpdateEvent.Pre event) { BlockPos coordinates1;
/*  86 */     if (!isToggled())
/*  87 */       return;  switch (this.mode.getSelected()) {
/*     */       case "Smart":
/*  89 */         if (this.searchForBaseCooldown > 0) this.searchForBaseCooldown--; 
/*  90 */         if (this.showChestLocation <= 0)
/*  91 */           return;  this.showChestLocation--;
/*  92 */         coordinates1 = getClosestChestLocation();
/*  93 */         if (coordinates1 == null)
/*  94 */           return;  this.bestCoordinates = coordinates1;
/*  95 */         clickBlock(this.bestCoordinates);
/*     */         break;
/*     */       case "Brute":
/*  98 */         if (this.baseCoordinates != null && OringoClient.mc.field_71439_g.func_70694_bm() != null && OringoClient.mc.field_71439_g.func_70694_bm().func_82833_r().contains("Metal Detector"))
/*  99 */           for (int i = 0; i < this.tries.getValue(); i++) {
/* 100 */             BlockPos coordinates = chests[(OringoClient.mc.field_71439_g.field_70173_aa + i) % chests.length];
/* 101 */             clickBlock(new BlockPos(this.baseCoordinates.func_177958_n() - coordinates.func_177958_n(), this.baseCoordinates.func_177956_o() - coordinates.func_177956_o(), this.baseCoordinates.func_177952_p() - coordinates.func_177952_p()));
/*     */           }  
/*     */         break;
/*     */     }  }
/*     */   
/*     */   private BlockPos coords; private BlockPos bestCoordinates; private int showChestLocation; private BlockPos baseCoordinates;
/*     */   private boolean searchingForBase;
/*     */   private int searchForBaseCooldown;
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onChat(ClientChatReceivedEvent e) {
/* 112 */     if (e.type == 2 && e.message.func_150254_d().contains("§3§lTREASURE:")) {
/* 113 */       if (this.baseCoordinates == null) findBaseCoordinates(); 
/* 114 */       if (this.mode.is("Smart")) {
/* 115 */         this.showChestLocation = 10;
/* 116 */         this.distances = Double.parseDouble(ChatFormatting.stripFormatting(e.message.func_150254_d().substring(e.message.func_150254_d().length() - 3 - 6, e.message.func_150254_d().length() - 3)));
/* 117 */         this.coords = new BlockPos(OringoClient.mc.field_71439_g.field_70165_t, OringoClient.mc.field_71439_g.field_70163_u, OringoClient.mc.field_71439_g.field_70161_v);
/*     */       } 
/* 119 */     } else if (e.type == 1 && e.message.func_150254_d().startsWith("&r&aYou found ") && e.message.func_150254_d().endsWith("with your &r&cMetal Detector&r&a!&r")) {
/* 120 */       reset();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void reset() {
/* 126 */     this.distances = 0.0D;
/* 127 */     this.coords = null;
/* 128 */     this.bestCoordinates = null;
/* 129 */     this.showChestLocation = 0;
/*     */   }
/*     */   
/*     */   private void findBaseCoordinates() {
/* 133 */     if (this.searchForBaseCooldown > 0)
/* 134 */       return;  (new Thread(() -> {
/*     */           this.searchingForBase = true;
/*     */           int x = (int)OringoClient.mc.field_71439_g.field_70165_t;
/*     */           int y = (int)OringoClient.mc.field_71439_g.field_70163_u;
/*     */           int z = (int)OringoClient.mc.field_71439_g.field_70161_v;
/*     */           for (int i = x - 50; i < x + 50; i++) {
/*     */             for (int j = y + 30; j >= y - 30; j--) {
/*     */               for (int k = z - 50; k < z + 50; k++) {
/*     */                 if (OringoClient.mc.field_71441_e.func_180495_p(new BlockPos(i, j, k)).func_177230_c() == Blocks.field_150370_cb && OringoClient.mc.field_71441_e.func_180495_p(new BlockPos(i, j + 13, k)).func_177230_c() == Blocks.field_180401_cv) {
/*     */                   this.baseCoordinates = getBaseCoordinates(i, j + 13, k);
/*     */                   break;
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           this.searchingForBase = false;
/* 150 */         })).start();
/* 151 */     this.searchForBaseCooldown = 15;
/*     */   }
/*     */   
/*     */   private BlockPos getBaseCoordinates(int x, int y, int z) {
/* 155 */     boolean loop = true;
/* 156 */     int posX = x;
/* 157 */     int posY = y;
/* 158 */     int posZ = z;
/* 159 */     if (OringoClient.mc.field_71441_e.func_180495_p(new BlockPos(x, y, z)).func_177230_c() != Blocks.field_180401_cv) return new BlockPos(x, y, z); 
/* 160 */     while (loop) {
/* 161 */       loop = false;
/* 162 */       if (OringoClient.mc.field_71441_e.func_180495_p(new BlockPos(posX + 1, posY, posZ)).func_177230_c() == Blocks.field_180401_cv) {
/* 163 */         posX++;
/* 164 */         loop = true;
/*     */       } 
/* 166 */       if (OringoClient.mc.field_71441_e.func_180495_p(new BlockPos(posX, posY - 1, posZ)).func_177230_c() == Blocks.field_180401_cv) {
/* 167 */         posY--;
/* 168 */         loop = true;
/*     */       } 
/* 170 */       if (OringoClient.mc.field_71441_e.func_180495_p(new BlockPos(posX, posY, posZ + 1)).func_177230_c() == Blocks.field_180401_cv) {
/* 171 */         posZ++;
/* 172 */         loop = true;
/*     */       } 
/*     */     } 
/* 175 */     return new BlockPos(posX, posY, posZ);
/*     */   }
/*     */   
/*     */   double getDistance(int x1, int y1, int z1, int x2, int y2, int z2) {
/* 179 */     return Math.sqrt(Math.pow((x1 - x2), 2.0D) + Math.pow((y1 - y2), 2.0D) + Math.pow((z1 - z2), 2.0D));
/*     */   }
/*     */   
/*     */   private BlockPos getClosestChestLocation() {
/* 183 */     if (this.baseCoordinates == null) return null; 
/* 184 */     BlockPos bestChestLocation = null;
/* 185 */     double minDistance = 100000.0D;
/* 186 */     if (this.distances == 0.0D) return null; 
/* 187 */     for (BlockPos coordinates : chests) {
/* 188 */       double currentDistance = Math.abs(getDistance(this.coords.func_177958_n(), this.coords.func_177956_o(), this.coords.func_177952_p(), this.baseCoordinates.func_177958_n() - coordinates.func_177958_n(), this.baseCoordinates.func_177956_o() - coordinates.func_177956_o(), this.baseCoordinates.func_177952_p() - coordinates.func_177952_p()) - this.distances);
/*     */       
/* 190 */       if (currentDistance < minDistance) {
/* 191 */         minDistance = currentDistance;
/* 192 */         bestChestLocation = new BlockPos(this.baseCoordinates.func_177958_n() - coordinates.func_177958_n(), this.baseCoordinates.func_177956_o() - coordinates.func_177956_o(), this.baseCoordinates.func_177952_p() - coordinates.func_177952_p());
/*     */       } 
/*     */     } 
/*     */     
/* 196 */     return bestChestLocation;
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onWorldLoad(WorldEvent.Load event) {
/* 201 */     reset();
/* 202 */     this.baseCoordinates = null;
/*     */   }
/*     */   
/*     */   private void clickBlock(BlockPos hitPos) {
/* 206 */     OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, hitPos, EnumFacing.func_176733_a(OringoClient.mc.field_71439_g.field_70177_z)));
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\skyblock\MetalDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */