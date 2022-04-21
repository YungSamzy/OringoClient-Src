/*     */ package me.oringo.oringoclient.qolfeatures.module.macro;
/*     */ import com.mojang.realmsclient.gui.ChatFormatting;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import java.util.stream.Collectors;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.events.PacketReceivedEvent;
/*     */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.ModeSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*     */ import me.oringo.oringoclient.utils.RenderUtils;
/*     */ import me.oringo.oringoclient.utils.RotationUtils;
/*     */ import me.oringo.oringoclient.utils.SkyblockUtils;
/*     */ import net.minecraft.block.BlockColored;
/*     */ import net.minecraft.block.BlockStone;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiChat;
/*     */ import net.minecraft.client.gui.GuiMainMenu;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.multiplayer.GuiConnecting;
/*     */ import net.minecraft.client.multiplayer.ServerData;
/*     */ import net.minecraft.client.renderer.DestroyBlockProgress;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityArmorStand;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.ContainerChest;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.event.ClientChatReceivedEvent;
/*     */ import net.minecraftforge.client.event.RenderWorldLastEvent;
/*     */ import net.minecraftforge.event.world.WorldEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*     */ 
/*     */ public class MithrilMacro extends Module {
/*  54 */   private Minecraft mc = Minecraft.func_71410_x();
/*     */   
/*  56 */   private BlockPos target = null;
/*  57 */   private BlockPos test = null;
/*  58 */   private Vec3 targetRotation = null;
/*  59 */   private Vec3 targetRotation2 = null;
/*  60 */   private ArrayList<Float> yaw = new ArrayList<>();
/*  61 */   private ArrayList<Float> pitch = new ArrayList<>();
/*     */   private boolean stopLoop = false;
/*  63 */   private int ticksTargeting = 0;
/*  64 */   private int ticksMining = 0;
/*  65 */   private int ticks = 0;
/*  66 */   private int ticksSeen = 0;
/*     */   
/*  68 */   private int shouldReconnect = -1;
/*     */   
/*     */   public EntityArmorStand drillnpc;
/*     */   
/*  72 */   private int lastKey = -1;
/*  73 */   private int timeLeft = 0;
/*     */   
/*  75 */   private int pause = 0;
/*     */   
/*  77 */   private BooleanSetting drillRefuel = new BooleanSetting("Drill Refuel", false);
/*  78 */   private NumberSetting rotations = new NumberSetting("Rotations", 10.0D, 1.0D, 20.0D, 1.0D);
/*  79 */   private NumberSetting accuracyChecks = new NumberSetting("Accuracy", 5.0D, 3.0D, 10.0D, 1.0D);
/*  80 */   private NumberSetting maxBreakTime = new NumberSetting("Max break time", 160.0D, 40.0D, 400.0D, 1.0D);
/*  81 */   private NumberSetting quickBreak = new NumberSetting("Block skip progress", 0.9D, 0.0D, 1.0D, 0.1D);
/*  82 */   private NumberSetting panic = new NumberSetting("Auto leave", 100.0D, 0.0D, 200.0D, 1.0D);
/*  83 */   private BooleanSetting titanium = new BooleanSetting("Prioritize titanium", true);
/*  84 */   private BooleanSetting sneak = new BooleanSetting("Sneak", false);
/*  85 */   private BooleanSetting under = new BooleanSetting("Mine under", false);
/*  86 */   private BooleanSetting autoAbility = new BooleanSetting("Auto ability", true);
/*  87 */   private NumberSetting moreMovement = new NumberSetting("Head movements", 5.0D, 0.0D, 50.0D, 1.0D);
/*  88 */   private NumberSetting walking = new NumberSetting("Walking %", 0.1D, 0.0D, 5.0D, 0.1D);
/*  89 */   private NumberSetting walkingTime = new NumberSetting("Walking ticks", 5.0D, 0.0D, 60.0D, 1.0D);
/*  90 */   private ModeSetting mode = new ModeSetting("Target", "Clay", new String[] { "Clay", "Prismarine", "Wool", "Blue", "Gold" });
/*     */   
/*     */   public MithrilMacro() {
/*  93 */     super("Mithril Macro", 0, Module.Category.MACRO);
/*  94 */     addSettings(new Setting[] { (Setting)this.rotations, (Setting)this.drillRefuel, (Setting)this.accuracyChecks, (Setting)this.titanium, (Setting)this.sneak, (Setting)this.quickBreak, (Setting)this.maxBreakTime, (Setting)this.autoAbility, (Setting)this.under, (Setting)this.panic, (Setting)this.moreMovement, (Setting)this.walking, (Setting)this.walkingTime, (Setting)this.mode });
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onLoad(WorldEvent.Load event) {
/* 100 */     this.drillnpc = null;
/* 101 */     if (isToggled()) {
/* 102 */       setToggled(false);
/* 103 */       if (OringoClient.aotvReturn.isToggled()) OringoClient.aotvReturn.start(() -> setToggled(true), false);
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onWorldRender(RenderWorldLastEvent event) {
/* 110 */     if (!isToggled())
/* 111 */       return;  if (this.target != null)
/* 112 */       RenderUtils.blockBox(this.target, Color.CYAN); 
/* 113 */     if (this.targetRotation != null)
/* 114 */       RenderUtils.miniBlockBox(this.targetRotation, Color.GREEN); 
/* 115 */     if (this.targetRotation2 != null) {
/* 116 */       RenderUtils.miniBlockBox(this.targetRotation2, Color.RED);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void reconnect(TickEvent.ClientTickEvent event) {
/* 123 */     if (this.mc.field_71462_r instanceof net.minecraft.client.gui.GuiDisconnected && this.shouldReconnect < 0 && isToggled()) {
/* 124 */       this.shouldReconnect = 250;
/* 125 */       setToggled(false);
/*     */     } 
/* 127 */     if (this.shouldReconnect-- == 0) {
/* 128 */       this.mc.func_147108_a((GuiScreen)new GuiConnecting((GuiScreen)new GuiMainMenu(), this.mc, new ServerData("Hypixel", "play.Hypixel.net", false)));
/* 129 */       (new Thread(() -> {
/*     */             try {
/*     */               Thread.sleep(15000L);
/* 132 */             } catch (InterruptedException e) {
/*     */               e.printStackTrace();
/*     */             } 
/*     */             if (this.mc.field_71439_g != null && OringoClient.aotvReturn.isToggled()) {
/*     */               OringoClient.aotvReturn.start((), false);
/*     */             }
/* 138 */           })).start();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnable() {
/* 144 */     this.ticksSeen = 0;
/* 145 */     this.ticksMining = 0;
/* 146 */     this.ticksTargeting = 0;
/* 147 */     if (this.autoAbility.isEnabled() && this.mc.field_71439_g.func_70694_bm() != null)
/* 148 */       this.mc.field_71442_b.func_78769_a((EntityPlayer)this.mc.field_71439_g, (World)this.mc.field_71441_e, this.mc.field_71439_g.func_70694_bm()); 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onChat(ClientChatReceivedEvent event) {
/* 153 */     if (!isToggled())
/* 154 */       return;  String message = event.message.func_150254_d();
/* 155 */     if (this.drillRefuel.isEnabled() && ChatFormatting.stripFormatting(message).startsWith("Your") && ChatFormatting.stripFormatting(message).endsWith("Refuel it by talking to a Drill Mechanic!") && this.drillnpc != null) {
/* 156 */       setToggled(false);
/* 157 */       (new Thread(() -> {
/*     */             try {
/*     */               for (int a : new int[] { this.mc.field_71474_y.field_74351_w.func_151463_i(), this.mc.field_71474_y.field_74370_x.func_151463_i(), this.mc.field_71474_y.field_74368_y.func_151463_i(), this.mc.field_71474_y.field_74366_z.func_151463_i(), this.mc.field_71474_y.field_74311_E.func_151463_i(), this.mc.field_71474_y.field_74312_F.func_151463_i() })
/*     */                 KeyBinding.func_74510_a(a, false); 
/*     */               Thread.sleep(500L);
/*     */               this.mc.field_71442_b.func_78768_b((EntityPlayer)this.mc.field_71439_g, (Entity)this.drillnpc);
/*     */               Thread.sleep(2500L);
/*     */               if (this.mc.field_71439_g.field_71070_bA instanceof ContainerChest && ((ContainerChest)this.mc.field_71439_g.field_71070_bA).func_85151_d().func_145748_c_().func_150260_c().contains("Drill Anvil")) {
/*     */                 int i;
/*     */                 for (i = 0; i < this.mc.field_71439_g.field_71070_bA.field_75151_b.size(); i++) {
/*     */                   Slot slot = this.mc.field_71439_g.field_71070_bA.func_75139_a(i);
/*     */                   if (slot.func_75216_d() && slot.func_75211_c().func_82833_r().contains("Drill") && slot.func_75211_c().func_77973_b() == Items.field_179562_cC) {
/*     */                     this.mc.field_71442_b.func_78753_a(this.mc.field_71439_g.field_71070_bA.field_75152_c, slot.field_75222_d, 0, 1, (EntityPlayer)this.mc.field_71439_g);
/*     */                     break;
/*     */                   } 
/*     */                 } 
/*     */                 Thread.sleep(500L);
/*     */                 for (i = 0; i < this.mc.field_71439_g.field_71070_bA.field_75151_b.size(); i++) {
/*     */                   Slot slot = this.mc.field_71439_g.field_71070_bA.func_75139_a(i);
/*     */                   if (slot.func_75216_d() && (slot.func_75211_c().func_82833_r().contains("Volta") || slot.func_75211_c().func_82833_r().contains("Oil Barrel") || slot.func_75211_c().func_82833_r().contains("Biofuel"))) {
/*     */                     this.mc.field_71442_b.func_78753_a(this.mc.field_71439_g.field_71070_bA.field_75152_c, slot.field_75222_d, 0, 1, (EntityPlayer)this.mc.field_71439_g);
/*     */                     break;
/*     */                   } 
/*     */                 } 
/*     */                 Thread.sleep(500L);
/*     */                 this.mc.field_71442_b.func_78753_a(this.mc.field_71439_g.field_71070_bA.field_75152_c, 22, 0, 0, (EntityPlayer)this.mc.field_71439_g);
/*     */                 Thread.sleep(250L);
/*     */                 this.mc.field_71442_b.func_78753_a(this.mc.field_71439_g.field_71070_bA.field_75152_c, 13, 0, 1, (EntityPlayer)this.mc.field_71439_g);
/*     */                 Thread.sleep(250L);
/*     */                 this.mc.field_71439_g.func_71053_j();
/*     */               } 
/*     */               Thread.sleep(2500L);
/*     */               setToggled(true);
/*     */               KeyBinding.func_74510_a(this.mc.field_71474_y.field_74312_F.func_151463_i(), true);
/*     */               this.mc.func_147108_a((GuiScreen)new GuiChat());
/* 192 */             } catch (InterruptedException e) {
/*     */               e.printStackTrace();
/*     */             } 
/* 195 */           })).start();
/*     */     } 
/* 197 */     if (ChatFormatting.stripFormatting(event.message.func_150260_c()).equals("Mining Speed Boost is now available!") && 
/* 198 */       this.autoAbility.isEnabled() && this.mc.field_71439_g.func_70694_bm() != null) {
/* 199 */       OringoClient.sendMessageWithPrefix("Auto ability");
/* 200 */       this.mc.field_71442_b.func_78769_a((EntityPlayer)this.mc.field_71439_g, (World)this.mc.field_71441_e, this.mc.field_71439_g.func_70694_bm());
/*     */     } 
/*     */     
/* 203 */     if (ChatFormatting.stripFormatting(event.message.func_150260_c()).equals("Oh no! Your Pickonimbus 2000 broke!")) {
/* 204 */       (new Thread(() -> {
/*     */             try {
/*     */               Thread.sleep(1000L);
/* 207 */             } catch (InterruptedException e) {
/*     */               e.printStackTrace();
/*     */             } 
/*     */             for (int i = 0; i < 9; i++) {
/*     */               if (this.mc.field_71439_g.field_71071_by.func_70301_a(i) != null && this.mc.field_71439_g.field_71071_by.func_70301_a(i).func_82833_r().contains("Pickonimbus")) {
/*     */                 this.mc.field_71439_g.field_71071_by.field_70461_c = i;
/*     */                 break;
/*     */               } 
/*     */             } 
/* 216 */           })).start();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDisable() {
/* 222 */     KeyBinding.func_74510_a((Minecraft.func_71410_x()).field_71474_y.field_74312_F.func_151463_i(), false);
/* 223 */     KeyBinding.func_74510_a((Minecraft.func_71410_x()).field_71474_y.field_74311_E.func_151463_i(), false);
/*     */   }
/*     */   
/*     */   @SubscribeEvent(receiveCanceled = true)
/*     */   public void onPacket(PacketReceivedEvent event) {
/* 228 */     if (event.packet instanceof net.minecraft.network.play.server.S08PacketPlayerPosLook && isToggled()) {
/* 229 */       this.pause = 200;
/* 230 */       this.target = null;
/* 231 */       KeyBinding.func_74510_a((Minecraft.func_71410_x()).field_71474_y.field_74312_F.func_151463_i(), false);
/* 232 */       KeyBinding.func_74510_a((Minecraft.func_71410_x()).field_71474_y.field_74311_E.func_151463_i(), false);
/* 233 */       for (int a : new int[] { this.mc.field_71474_y.field_74351_w.func_151463_i(), this.mc.field_71474_y.field_74370_x.func_151463_i(), this.mc.field_71474_y.field_74368_y.func_151463_i(), this.mc.field_71474_y.field_74366_z.func_151463_i() })
/* 234 */         KeyBinding.func_74510_a(a, false); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isPickaxe(ItemStack itemStack) {
/* 239 */     return (itemStack != null && (itemStack.func_82833_r().contains("Pickaxe") || itemStack.func_77973_b() == Items.field_179562_cC || itemStack.func_82833_r().contains("Gauntlet")));
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onTick(TickEvent.ClientTickEvent event) {
/* 244 */     this.pause--;
/* 245 */     if (isToggled() && !(this.mc.field_71462_r instanceof net.minecraft.client.gui.inventory.GuiContainer) && !(this.mc.field_71462_r instanceof net.minecraft.client.gui.inventory.GuiEditSign) && this.pause < 1) {
/* 246 */       this.ticks++;
/* 247 */       if (this.mc.field_71439_g != null && this.mc.field_71439_g.func_70694_bm() != null && this.mc.field_71439_g.func_70694_bm().func_77973_b() instanceof net.minecraft.item.ItemMap) {
/* 248 */         setToggled(false);
/* 249 */         this.mc.field_71439_g.func_71165_d("/l");
/*     */       } 
/* 251 */       if (this.mc.field_71441_e != null) {
/* 252 */         if (this.drillnpc == null && this.drillRefuel.isEnabled()) {
/* 253 */           for (Entity entityArmorStand : this.mc.field_71441_e.func_72910_y().stream().filter(entity -> entity instanceof EntityArmorStand).collect(Collectors.toList())) {
/* 254 */             if (entityArmorStand.func_145748_c_().func_150254_d().contains("§e§lDRILL MECHANIC§r")) {
/* 255 */               OringoClient.mithrilMacro.drillnpc = (EntityArmorStand)entityArmorStand;
/* 256 */               OringoClient.sendMessageWithPrefix("Mechanic");
/*     */               return;
/*     */             } 
/*     */           } 
/* 260 */           setToggled(false);
/* 261 */           OringoClient.aotvReturn.start(() -> setToggled(true), false);
/*     */           
/*     */           return;
/*     */         } 
/* 265 */         if (!isPickaxe(this.mc.field_71439_g.func_70694_bm())) {
/* 266 */           for (int i = 0; i < 9; i++) {
/* 267 */             if (isPickaxe(this.mc.field_71439_g.field_71071_by.func_70301_a(i))) {
/* 268 */               this.mc.field_71439_g.field_71071_by.field_70461_c = i;
/* 269 */               SkyblockUtils.updateItemNoEvent();
/*     */             } 
/*     */           } 
/*     */         }
/* 273 */         if (this.timeLeft-- <= 0) {
/* 274 */           int[] keybinds = { this.mc.field_71474_y.field_74351_w.func_151463_i(), this.mc.field_71474_y.field_74370_x.func_151463_i(), this.mc.field_71474_y.field_74368_y.func_151463_i(), this.mc.field_71474_y.field_74366_z.func_151463_i(), this.mc.field_71474_y.field_74370_x.func_151463_i(), this.mc.field_71474_y.field_74368_y.func_151463_i(), this.mc.field_71474_y.field_74366_z.func_151463_i(), this.mc.field_71474_y.field_74368_y.func_151463_i(), this.mc.field_71474_y.field_74368_y.func_151463_i() };
/* 275 */           if (this.lastKey != -1) {
/* 276 */             KeyBinding.func_74510_a(this.lastKey, false);
/* 277 */             KeyBinding.func_74510_a((Minecraft.func_71410_x()).field_71474_y.field_74311_E.func_151463_i(), this.sneak.isEnabled());
/*     */           } 
/* 279 */           if ((new Random()).nextFloat() < this.walking.getValue() / 100.0D) {
/* 280 */             this.lastKey = keybinds[(new Random()).nextInt(keybinds.length)];
/* 281 */             KeyBinding.func_74510_a((Minecraft.func_71410_x()).field_71474_y.field_74311_E.func_151463_i(), true);
/* 282 */             KeyBinding.func_74510_a(this.lastKey, true);
/* 283 */             this.timeLeft = (int)this.walkingTime.getValue();
/*     */           } 
/*     */         } else {
/* 286 */           KeyBinding.func_74510_a(this.lastKey, true);
/* 287 */           KeyBinding.func_74510_a(this.mc.field_71474_y.field_74311_E.func_151463_i(), true);
/*     */         } 
/*     */         
/* 290 */         if (this.mc.field_71476_x != null && this.mc.field_71476_x.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) {
/* 291 */           Entity entity = this.mc.field_71476_x.field_72308_g;
/* 292 */           if (entity instanceof EntityPlayer && !SkyblockUtils.isTeam((EntityLivingBase)entity, (EntityLivingBase)this.mc.field_71439_g)) {
/* 293 */             SkyblockUtils.click();
/* 294 */             this.pause = 5;
/*     */             
/*     */             return;
/*     */           } 
/*     */         } 
/* 299 */         if (this.mc.field_71441_e.field_73010_i.stream().anyMatch(playerEntity -> (!playerEntity.equals(this.mc.field_71439_g) && playerEntity.func_70032_d((Entity)this.mc.field_71439_g) < 10.0F && SkyblockUtils.isTeam((EntityLivingBase)this.mc.field_71439_g, (EntityLivingBase)playerEntity) && (!playerEntity.func_82150_aj() || playerEntity.field_70163_u - this.mc.field_71439_g.field_70163_u <= 5.0D))))
/* 300 */         { this.ticksSeen++; }
/* 301 */         else { this.ticksSeen = 0; }
/*     */         
/* 303 */         boolean inDwarven = SkyblockUtils.anyTab("Dwarven Mines");
/* 304 */         if ((this.panic.getValue() <= this.ticksSeen && this.panic.getValue() != 0.0D) || !inDwarven) {
/* 305 */           setToggled(false);
/* 306 */           if (OringoClient.aotvReturn.isToggled()) OringoClient.aotvReturn.start(() -> setToggled(true), false); 
/* 307 */           this.ticksSeen = 0;
/* 308 */           OringoClient.sendMessageWithPrefix(!inDwarven ? "Not in dwarven" : ("You have been seen by " + ((EntityPlayer)this.mc.field_71441_e.field_73010_i.stream().filter(playerEntity -> (!playerEntity.equals(this.mc.field_71439_g) && playerEntity.func_70032_d((Entity)this.mc.field_71439_g) < 10.0F && SkyblockUtils.isTeam((EntityLivingBase)this.mc.field_71439_g, (EntityLivingBase)playerEntity))).findFirst().get()).func_70005_c_()));
/*     */           return;
/*     */         } 
/* 311 */         if (this.target == null) {
/* 312 */           if (!findTarget()) {
/* 313 */             OringoClient.sendMessageWithPrefix("No possible target found");
/*     */           }
/*     */           
/*     */           return;
/*     */         } 
/* 318 */         if (this.mc.field_71476_x != null && this.mc.field_71476_x.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) {
/* 319 */           if (this.ticksTargeting++ == 40) {
/* 320 */             setToggled(false);
/* 321 */             if (OringoClient.aotvReturn.isToggled()) OringoClient.aotvReturn.start(() -> setToggled(true), false); 
/*     */             return;
/*     */           } 
/*     */         } else {
/* 325 */           this.ticksTargeting = 0;
/*     */         } 
/* 327 */         KeyBinding.func_74510_a((Minecraft.func_71410_x()).field_71474_y.field_74312_F.func_151463_i(), true);
/* 328 */         if (this.sneak.isEnabled() || this.timeLeft != 0) {
/* 329 */           KeyBinding.func_74510_a((Minecraft.func_71410_x()).field_71474_y.field_74311_E.func_151463_i(), true);
/*     */         }
/* 331 */         if (this.mc.field_71476_x.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && 
/* 332 */           this.mc.field_71462_r != null && !(this.mc.field_71462_r instanceof net.minecraft.client.gui.inventory.GuiContainer) && this.ticks % 2 == 0) {
/* 333 */           SkyblockUtils.click();
/*     */         }
/* 335 */         if (!this.yaw.isEmpty() && (this.stopLoop || !isTitanium(this.target))) {
/* 336 */           this.mc.field_71439_g.field_70177_z = ((Float)this.yaw.get(0)).floatValue();
/* 337 */           this.mc.field_71439_g.field_70125_A = ((Float)this.pitch.get(0)).floatValue();
/* 338 */           this.yaw.remove(0);
/* 339 */           this.pitch.remove(0);
/* 340 */           if (this.yaw.isEmpty() && isBlockVisible(this.target) && this.moreMovement.getValue() != 0.0D) {
/* 341 */             this.stopLoop = false;
/* 342 */             Vec3 targetRotationTemp = this.targetRotation;
/* 343 */             this.targetRotation = getRandomVisibilityLine(this.target);
/* 344 */             this.targetRotation2 = this.targetRotation;
/* 345 */             getRotations(false);
/* 346 */             this.targetRotation = targetRotationTemp;
/*     */             return;
/*     */           } 
/* 349 */           if (this.moreMovement.getValue() == 0.0D) this.targetRotation2 = null; 
/* 350 */           if (this.stopLoop)
/*     */             return; 
/* 352 */         }  if (this.mc.field_71441_e.func_180495_p(this.target).func_177230_c().equals(Blocks.field_150357_h)) {
/* 353 */           if (!findTarget());
/*     */           
/*     */           return;
/*     */         } 
/*     */         
/* 358 */         if (this.mc.field_71476_x.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/* 359 */           BlockPos pos = this.mc.field_71476_x.func_178782_a();
/* 360 */           if (!pos.equals(this.target)) {
/* 361 */             if (!findTarget());
/*     */ 
/*     */             
/*     */             return;
/*     */           } 
/*     */         } else {
/* 367 */           if (!findTarget());
/*     */           
/*     */           return;
/*     */         } 
/*     */         
/* 372 */         if (this.quickBreak.getValue() != 0.0D && !isTitanium(this.target) && OringoClient.getBlockBreakProgress().values().stream().anyMatch(progress -> progress.func_180246_b().equals(this.target)) && OringoClient.getBlockBreakProgress().values().stream().anyMatch(progress -> (progress.func_180246_b().equals(this.target) && progress.func_73106_e() == (int)(this.quickBreak.getValue() * 10.0D)))) {
/* 373 */           findTarget();
/*     */         }
/* 375 */         if (this.ticksMining++ == this.maxBreakTime.getValue()) {
/* 376 */           OringoClient.sendMessageWithPrefix("Mining one block took too long");
/*     */           
/* 378 */           findTarget();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void getRotations(boolean stop) {
/* 385 */     Vec3 lookVec = this.mc.field_71439_g.func_70040_Z().func_178787_e(this.mc.field_71439_g.func_174824_e(0.0F));
/* 386 */     if (!this.yaw.isEmpty()) {
/* 387 */       this.yaw.clear();
/* 388 */       this.pitch.clear();
/*     */     } 
/* 390 */     double max = (this.rotations.getValue() + 1.0D) * (stop ? 1.0D : this.moreMovement.getValue());
/* 391 */     for (int i = 0; i < max; i++) {
/* 392 */       Vec3 target = new Vec3(lookVec.field_72450_a + (this.targetRotation.field_72450_a - lookVec.field_72450_a) / max * i, lookVec.field_72448_b + (this.targetRotation.field_72448_b - lookVec.field_72448_b) / max * i, lookVec.field_72449_c + (this.targetRotation.field_72449_c - lookVec.field_72449_c) / max * i);
/* 393 */       float[] rotation = RotationUtils.getAngles(target);
/* 394 */       this.yaw.add(Float.valueOf(rotation[0]));
/* 395 */       this.pitch.add(Float.valueOf(rotation[1]));
/*     */     } 
/* 397 */     this.stopLoop = stop;
/*     */   }
/*     */   
/*     */   private boolean findTarget() {
/* 401 */     ArrayList<BlockPos> blocks = new ArrayList<>();
/* 402 */     for (int x = -5; x < 6; x++) {
/* 403 */       for (int y = -5; y < 6; y++) {
/* 404 */         for (int z = -5; z < 6; z++) {
/* 405 */           blocks.add(new BlockPos(this.mc.field_71439_g.field_70165_t + x, this.mc.field_71439_g.field_70163_u + y, this.mc.field_71439_g.field_70161_v + z));
/*     */         }
/*     */       } 
/*     */     } 
/* 409 */     BlockPos sortingCenter = (this.target != null) ? this.target : this.mc.field_71439_g.func_180425_c();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 416 */     Optional<BlockPos> any = blocks.stream().filter(pos -> !pos.equals(this.target)).filter(this::matchesMode).filter(pos -> (this.mc.field_71439_g.func_70011_f(pos.func_177958_n(), (pos.func_177956_o() - this.mc.field_71439_g.func_70047_e()), pos.func_177952_p()) < 5.5D)).filter(this::isBlockVisible).min(Comparator.comparingDouble(pos -> (isTitanium(pos) && this.titanium.isEnabled()) ? 0.0D : getDistance(pos, sortingCenter, 0.6D)));
/* 417 */     if (any.isPresent()) {
/* 418 */       this.target = any.get();
/* 419 */       this.targetRotation2 = null;
/* 420 */       this.targetRotation = getRandomVisibilityLine(any.get());
/* 421 */       getRotations(true);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 429 */       any = blocks.stream().filter(pos -> !pos.equals(this.target)).filter(this::matchesAny).filter(pos -> (this.mc.field_71439_g.func_70011_f(pos.func_177958_n(), (pos.func_177956_o() - this.mc.field_71439_g.func_70047_e()), pos.func_177952_p()) < 5.5D)).filter(this::isBlockVisible).min(Comparator.comparingDouble(pos -> (isTitanium(pos) && this.titanium.isEnabled()) ? 0.0D : getDistance(pos, sortingCenter, 0.6D)));
/* 430 */       if (any.isPresent()) {
/* 431 */         this.target = any.get();
/* 432 */         this.targetRotation2 = null;
/* 433 */         this.targetRotation = getRandomVisibilityLine(any.get());
/* 434 */         getRotations(true);
/*     */       } 
/*     */     } 
/* 437 */     this.ticksMining = 0;
/* 438 */     return any.isPresent();
/*     */   }
/*     */   
/*     */   private double getDistance(BlockPos pos1, BlockPos pos2, double multiY) {
/* 442 */     double deltaX = (pos1.func_177958_n() - pos2.func_177958_n());
/* 443 */     double deltaY = (pos1.func_177956_o() - pos2.func_177956_o()) * multiY;
/* 444 */     double deltaZ = (pos1.func_177952_p() - pos2.func_177952_p());
/*     */     
/* 446 */     return Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
/*     */   }
/*     */   
/*     */   private boolean isBlockVisible(BlockPos pos) {
/* 450 */     return (getRandomVisibilityLine(pos) != null);
/*     */   }
/*     */   
/*     */   private Vec3 getRandomVisibilityLine(BlockPos pos) {
/* 454 */     List<Vec3> lines = new ArrayList<>();
/* 455 */     for (int x = 0; x < this.accuracyChecks.getValue(); x++) {
/* 456 */       for (int y = 0; y < this.accuracyChecks.getValue(); y++) {
/* 457 */         for (int z = 0; z < this.accuracyChecks.getValue(); z++) {
/* 458 */           Vec3 target = new Vec3(pos.func_177958_n() + x / this.accuracyChecks.getValue(), pos.func_177956_o() + y / this.accuracyChecks.getValue(), pos.func_177952_p() + z / this.accuracyChecks.getValue());
/* 459 */           this.test = new BlockPos(target.field_72450_a, target.field_72448_b, target.field_72449_c);
/*     */           
/* 461 */           MovingObjectPosition movingObjectPosition = this.mc.field_71441_e.func_147447_a(this.mc.field_71439_g.func_174824_e(0.0F), target, true, false, true);
/* 462 */           if (movingObjectPosition != null) {
/* 463 */             BlockPos obj = movingObjectPosition.func_178782_a();
/* 464 */             if (obj.equals(this.test) && this.mc.field_71439_g.func_70011_f(target.field_72450_a, target.field_72448_b - this.mc.field_71439_g.func_70047_e(), target.field_72449_c) < 4.5D && (
/* 465 */               this.under.isEnabled() || Math.abs(this.mc.field_71439_g.field_70163_u - target.field_72448_b) > 1.3D))
/* 466 */               lines.add(target); 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 471 */     return lines.isEmpty() ? null : lines.get((new Random()).nextInt(lines.size()));
/*     */   }
/*     */   
/*     */   private boolean isTitanium(BlockPos pos) {
/* 475 */     IBlockState state = this.mc.field_71441_e.func_180495_p(pos);
/* 476 */     return (state.func_177230_c() == Blocks.field_150348_b && ((BlockStone.EnumType)state.func_177229_b((IProperty)BlockStone.field_176247_a)).equals(BlockStone.EnumType.DIORITE_SMOOTH));
/*     */   }
/*     */   
/*     */   private boolean matchesMode(BlockPos pos) {
/* 480 */     IBlockState state = this.mc.field_71441_e.func_180495_p(pos);
/* 481 */     if (isTitanium(pos)) return true; 
/* 482 */     switch (this.mode.getSelected()) {
/*     */       case "Clay":
/* 484 */         return (state.func_177230_c().equals(Blocks.field_150406_ce) || (state.func_177230_c().equals(Blocks.field_150325_L) && ((EnumDyeColor)state.func_177229_b((IProperty)BlockColored.field_176581_a)).equals(EnumDyeColor.GRAY)));
/*     */       
/*     */       case "Prismarine":
/* 487 */         return state.func_177230_c().equals(Blocks.field_180397_cI);
/*     */       
/*     */       case "Wool":
/* 490 */         return (state.func_177230_c().equals(Blocks.field_150325_L) && ((EnumDyeColor)state.func_177229_b((IProperty)BlockColored.field_176581_a)).equals(EnumDyeColor.LIGHT_BLUE));
/*     */       
/*     */       case "Blue":
/* 493 */         return ((state.func_177230_c().equals(Blocks.field_150325_L) && ((EnumDyeColor)state.func_177229_b((IProperty)BlockColored.field_176581_a)).equals(EnumDyeColor.LIGHT_BLUE)) || state.func_177230_c().equals(Blocks.field_180397_cI));
/*     */       
/*     */       case "Gold":
/* 496 */         return state.func_177230_c().equals(Blocks.field_150340_R);
/*     */     } 
/*     */     
/* 499 */     return false;
/*     */   }
/*     */   
/*     */   private boolean matchesAny(BlockPos pos) {
/* 503 */     IBlockState state = this.mc.field_71441_e.func_180495_p(pos);
/* 504 */     return ((state.func_177230_c().equals(Blocks.field_150325_L) && state.func_177228_b().entrySet().stream().anyMatch(entry -> entry.toString().contains("lightBlue"))) || state
/* 505 */       .func_177230_c().equals(Blocks.field_180397_cI) || state
/* 506 */       .func_177230_c().equals(Blocks.field_150406_ce) || (state.func_177230_c().equals(Blocks.field_150325_L) && state.func_177228_b().entrySet().stream().anyMatch(entry -> entry.toString().contains("gray"))) || 
/* 507 */       isTitanium(pos));
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\macro\MithrilMacro.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */