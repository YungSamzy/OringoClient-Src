/*     */ package me.oringo.oringoclient.qolfeatures.module.player;
/*     */ 
/*     */ import com.mojang.realmsclient.gui.ChatFormatting;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.ModeSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*     */ import me.oringo.oringoclient.utils.MilliTimer;
/*     */ import me.oringo.oringoclient.utils.Notifications;
/*     */ import me.oringo.oringoclient.utils.SkyblockUtils;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.ItemSword;
/*     */ import net.minecraft.item.ItemTool;
/*     */ import net.minecraftforge.client.event.GuiScreenEvent;
/*     */ import net.minecraftforge.event.entity.player.ItemTooltipEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import org.lwjgl.input.Mouse;
/*     */ 
/*     */ public class InvManager extends Module {
/*  33 */   public NumberSetting delay = new NumberSetting("Delay", 30.0D, 0.0D, 300.0D, 1.0D);
/*  34 */   public BooleanSetting dropTrash = new BooleanSetting("Drop trash", true); public BooleanSetting autoArmor = new BooleanSetting("Auto Armor", false); public BooleanSetting middleClick = new BooleanSetting("Middle click to drop", false);
/*     */   
/*  36 */   public ModeSetting mode = new ModeSetting("Trash items", "Skyblock", new String[] { "Skyblock", "Skywars", "Custom" });
/*     */ 
/*     */   
/*  39 */   private MilliTimer timer = new MilliTimer();
/*     */   
/*     */   private boolean wasPressed;
/*     */   
/*  43 */   public NumberSetting swordSlot = new NumberSetting("Sword slot", 0.0D, 0.0D, 9.0D, 1.0D); public NumberSetting blockSlot = new NumberSetting("Block slot", 0.0D, 0.0D, 9.0D, 1.0D); public NumberSetting gappleSlot = new NumberSetting("Gapple slot", 0.0D, 0.0D, 9.0D, 1.0D); public NumberSetting pickaxeSlot = new NumberSetting("Pickaxe slot", 0.0D, 0.0D, 9.0D, 1.0D); public NumberSetting axeSlot = new NumberSetting("Axe slot", 0.0D, 0.0D, 9.0D, 1.0D); public NumberSetting shovelSlot = new NumberSetting("Shovel slot", 0.0D, 0.0D, 9.0D, 1.0D); public NumberSetting bowSlot = new NumberSetting("Bow slot", 0.0D, 0.0D, 9.0D, 1.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   private List<String> dropSkyblock = Arrays.asList(new String[] { "Training Weight", "Healing Potion", "Beating Heart", "Premium Flesh", "Mimic Fragment", "Enchanted Rotten Flesh", "Machine Gun Bow", "Enchanted Bone", "Defuse Kit", "Enchanted Ice", "Diamond Atom", "Silent Death", "Cutlass", "Soulstealer Bow", "Sniper Bow", "Optical Lens", "Tripwire Hook", "Button", "Carpet", "Lever", "Journal Entry", "Sign", "Zombie Commander", "Zombie Lord", "Skeleton Master, Skeleton Grunt, Skeleton Lord, Zombie Soldier", "Zombie Knight", "Heavy", "Super Heavy", "Undead", "Bouncy", "Skeletor", "Trap", "Inflatable Jerry" });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   private List<String> dropSkywars = Arrays.asList(new String[] { "Egg", "Snowball", "Poison", "Lava", "Steak", "Enchanting" });
/*     */   
/*  60 */   public static List<String> dropCustom = new ArrayList<>();
/*     */   
/*     */   public InvManager() {
/*  63 */     super("Inventory Manager", 0, Module.Category.PLAYER);
/*  64 */     addSettings(new Setting[] { (Setting)this.delay, (Setting)this.dropTrash, (Setting)this.mode, (Setting)this.middleClick, (Setting)this.autoArmor });
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onGui(GuiScreenEvent.BackgroundDrawnEvent event) {
/*  69 */     if (event.gui instanceof net.minecraft.client.gui.inventory.GuiInventory && isToggled()) {
/*  70 */       if (this.autoArmor.isEnabled() && !SkyblockUtils.onSkyblock)
/*  71 */         getBestArmor(); 
/*  72 */       if (this.dropTrash.isEnabled())
/*  73 */         dropTrash(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onTooltip(ItemTooltipEvent event) {
/*  79 */     if (Mouse.isButtonDown(2) && OringoClient.mc.field_71462_r instanceof net.minecraft.client.gui.inventory.GuiInventory && this.middleClick.isEnabled()) {
/*  80 */       if (!this.wasPressed) {
/*  81 */         this.wasPressed = true;
/*  82 */         String name = ChatFormatting.stripFormatting(event.itemStack.func_82833_r());
/*  83 */         if (dropCustom.contains(name)) {
/*  84 */           dropCustom.remove(name);
/*  85 */           Notifications.showNotification("Oringo Client", "Removed " + name + " from custom drop list", 2000);
/*     */         } else {
/*  87 */           dropCustom.add(name);
/*  88 */           Notifications.showNotification("Oringo Client", "Added " + ChatFormatting.AQUA + name + ChatFormatting.RESET + " to custom drop list", 2000);
/*     */         } 
/*  90 */         save();
/*     */       } 
/*     */     } else {
/*  93 */       this.wasPressed = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void dropTrash() {
/*  98 */     for (Iterator<Slot> iterator = OringoClient.mc.field_71439_g.field_71069_bz.field_75151_b.iterator(); iterator.hasNext(); ) { Slot slot = iterator.next();
/*  99 */       if (slot.func_75216_d() && canInteract()) {
/* 100 */         if (this.mode.getSelected().equals("Custom")) {
/* 101 */           if (dropCustom.contains(ChatFormatting.stripFormatting(slot.func_75211_c().func_82833_r()))) {
/* 102 */             drop(slot.field_75222_d);
/* 103 */             pause();
/*     */           }  continue;
/*     */         } 
/* 106 */         if (this.mode.getSelected().equals("Skyblock") && this.dropSkyblock.stream().anyMatch(a -> a.contains(ChatFormatting.stripFormatting(slot.func_75211_c().func_82833_r())))) {
/* 107 */           drop(slot.field_75222_d);
/* 108 */           pause(); continue;
/* 109 */         }  if (this.mode.getSelected().equals("Skywars") && this.dropSkywars.stream().anyMatch(a -> a.contains(ChatFormatting.stripFormatting(slot.func_75211_c().func_82833_r())))) {
/* 110 */           drop(slot.field_75222_d);
/* 111 */           pause();
/*     */         } 
/*     */       }  }
/*     */   
/*     */   }
/*     */   
/*     */   public void getBestArmor() {
/*     */     int i;
/* 119 */     for (i = 5; i < 9; i++) {
/* 120 */       if (OringoClient.mc.field_71439_g.field_71069_bz.func_75139_a(i).func_75216_d() && canInteract()) {
/* 121 */         ItemStack armor = OringoClient.mc.field_71439_g.field_71069_bz.func_75139_a(i).func_75211_c();
/* 122 */         if (!isBestArmor(armor, i)) {
/* 123 */           drop(i);
/* 124 */           pause();
/*     */         } 
/*     */       } 
/*     */     } 
/* 128 */     for (i = 9; i < 45; i++) {
/* 129 */       if (OringoClient.mc.field_71439_g.field_71069_bz.func_75139_a(i).func_75216_d() && canInteract()) {
/* 130 */         ItemStack stack = OringoClient.mc.field_71439_g.field_71069_bz.func_75139_a(i).func_75211_c();
/* 131 */         if (stack.func_77973_b() instanceof ItemArmor) {
/* 132 */           if (isBestArmor(stack, i)) {
/* 133 */             shiftClick(i);
/*     */           } else {
/* 135 */             drop(i);
/*     */           } 
/* 137 */           pause();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean isBestArmor(ItemStack armor, int slot) {
/* 144 */     if (!(armor.func_77973_b() instanceof ItemArmor)) return false; 
/* 145 */     for (int i = 5; i < 45; i++) {
/* 146 */       if (OringoClient.mc.field_71439_g.field_71069_bz.func_75139_a(i).func_75216_d()) {
/* 147 */         ItemStack is = OringoClient.mc.field_71439_g.field_71069_bz.func_75139_a(i).func_75211_c();
/* 148 */         if (is.func_77973_b() instanceof ItemArmor && ((getProtection(is) > getProtection(armor) && slot < 9) || (slot >= 9 && getProtection(is) >= getProtection(armor) && slot != i)) && ((ItemArmor)is.func_77973_b()).field_77881_a == ((ItemArmor)armor.func_77973_b()).field_77881_a) {
/* 149 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/* 153 */     return true;
/*     */   }
/*     */   
/*     */   public static float getProtection(ItemStack stack) {
/* 157 */     float prot = 0.0F;
/* 158 */     if (stack.func_77973_b() instanceof ItemArmor) {
/* 159 */       ItemArmor armor = (ItemArmor)stack.func_77973_b();
/* 160 */       prot = (float)(prot + armor.field_77879_b + ((100 - armor.field_77879_b) * EnchantmentHelper.func_77506_a(Enchantment.field_180310_c.field_77352_x, stack)) * 0.0075D);
/* 161 */       prot = (float)(prot + EnchantmentHelper.func_77506_a(Enchantment.field_77327_f.field_77352_x, stack) / 100.0D);
/* 162 */       prot = (float)(prot + EnchantmentHelper.func_77506_a(Enchantment.field_77329_d.field_77352_x, stack) / 100.0D);
/* 163 */       prot = (float)(prot + EnchantmentHelper.func_77506_a(Enchantment.field_92091_k.field_77352_x, stack) / 100.0D);
/* 164 */       prot = (float)(prot + EnchantmentHelper.func_77506_a(Enchantment.field_77347_r.field_77352_x, stack) / 50.0D);
/* 165 */       prot = (float)(prot + EnchantmentHelper.func_77506_a(Enchantment.field_180308_g.field_77352_x, stack) / 100.0D);
/* 166 */       prot = (float)(prot + stack.func_77958_k() / 1000.0D);
/*     */     } 
/* 168 */     return prot;
/*     */   }
/*     */   
/*     */   public static float getMaterial(ItemStack item) {
/* 172 */     if (item.func_77973_b() instanceof ItemTool)
/* 173 */       return (float)(((ItemTool)item.func_77973_b()).func_150913_i().func_77996_d() + EnchantmentHelper.func_77506_a(Enchantment.field_77349_p.field_77352_x, item) * 0.75D); 
/* 174 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public static int getBowDamage(ItemStack bow) {
/* 178 */     return EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, bow) + EnchantmentHelper.func_77506_a(Enchantment.field_77343_v.field_77352_x, bow) * 2;
/*     */   }
/*     */   
/*     */   public static float getSwordDamage(ItemStack sword) {
/* 182 */     float damage = 0.0F;
/* 183 */     if (sword.func_77973_b() instanceof ItemSword) {
/* 184 */       damage += 4.0F;
/* 185 */       damage += ((ItemSword)sword.func_77973_b()).func_150931_i();
/* 186 */       damage = (float)(damage + 1.25D * EnchantmentHelper.func_77506_a(Enchantment.field_180314_l.field_77352_x, sword));
/*     */     } 
/* 188 */     return damage;
/*     */   }
/*     */   
/*     */   private void pause() {
/* 192 */     this.timer.updateTime();
/*     */   }
/*     */   
/*     */   private boolean canInteract() {
/* 196 */     return this.timer.hasTimePassed((long)this.delay.getValue());
/*     */   }
/*     */   
/*     */   private static void save() {
/*     */     try {
/* 201 */       DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("config/OringoClient/InventoryManager.cfg"));
/* 202 */       dataOutputStream.writeInt(dropCustom.size());
/* 203 */       for (String s : dropCustom) {
/* 204 */         dataOutputStream.writeUTF(s);
/*     */       }
/* 206 */       dataOutputStream.close();
/* 207 */     } catch (Exception e) {
/* 208 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void shiftClick(int slot) {
/* 213 */     OringoClient.mc.field_71442_b.func_78753_a(OringoClient.mc.field_71439_g.field_71069_bz.field_75152_c, slot, 0, 1, (EntityPlayer)OringoClient.mc.field_71439_g);
/*     */   }
/*     */   
/*     */   public void numberClick(int slot, int button) {
/* 217 */     OringoClient.mc.field_71442_b.func_78753_a(OringoClient.mc.field_71439_g.field_71069_bz.field_75152_c, slot, button, 2, (EntityPlayer)OringoClient.mc.field_71439_g);
/*     */   }
/*     */   
/*     */   public void drop(int slot) {
/* 221 */     OringoClient.mc.field_71442_b.func_78753_a(OringoClient.mc.field_71439_g.field_71069_bz.field_75152_c, slot, 1, 4, (EntityPlayer)OringoClient.mc.field_71439_g);
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\player\InvManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */