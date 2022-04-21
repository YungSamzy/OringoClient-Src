/*    */ package me.oringo.oringoclient.qolfeatures.module.player;
/*    */ import com.mojang.realmsclient.gui.ChatFormatting;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*    */ import me.oringo.oringoclient.utils.MilliTimer;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.inventory.ContainerChest;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraftforge.client.event.GuiScreenEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class ChestStealer extends Module {
/*    */   private MilliTimer timer;
/*    */   public NumberSetting delay;
/*    */   
/*    */   public ChestStealer() {
/* 21 */     super("Chest stealer", 0, Module.Category.PLAYER);
/*    */ 
/*    */ 
/*    */     
/* 25 */     this.timer = new MilliTimer();
/*    */     
/* 27 */     this.delay = new NumberSetting("Delay", 100.0D, 30.0D, 200.0D, 1.0D);
/* 28 */     this.close = new BooleanSetting("Auto close", true); this.nameCheck = new BooleanSetting("Name check", true); this.stealTrash = new BooleanSetting("Steal trash", false);
/*    */     addSettings(new Setting[] { (Setting)this.delay, (Setting)this.nameCheck, (Setting)this.stealTrash, (Setting)this.close });
/*    */   }
/*    */   public BooleanSetting close; public BooleanSetting nameCheck; public BooleanSetting stealTrash;
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onGui(GuiScreenEvent.BackgroundDrawnEvent event) {
/* 35 */     if (event.gui instanceof GuiChest && isToggled()) {
/* 36 */       Container container = ((GuiChest)event.gui).field_147002_h;
/* 37 */       if (container instanceof ContainerChest && (!this.nameCheck.isEnabled() || ChatFormatting.stripFormatting(((ContainerChest)container).func_85151_d().func_145748_c_().func_150254_d()).equals("Chest") || ChatFormatting.stripFormatting(((ContainerChest)container).func_85151_d().func_145748_c_().func_150254_d()).equals("LOW"))) {
/* 38 */         int i; for (i = 0; i < ((ContainerChest)container).func_85151_d().func_70302_i_(); i++) {
/* 39 */           if (container.func_75139_a(i).func_75216_d() && this.timer.hasTimePassed((long)this.delay.getValue())) {
/* 40 */             Item item = container.func_75139_a(i).func_75211_c().func_77973_b();
/* 41 */             if (this.stealTrash.isEnabled() || item instanceof net.minecraft.item.ItemEnderPearl || item instanceof net.minecraft.item.ItemTool || item instanceof net.minecraft.item.ItemArmor || item instanceof net.minecraft.item.ItemBow || item instanceof net.minecraft.item.ItemPotion || item == Items.field_151032_g || item instanceof net.minecraft.item.ItemAppleGold || item instanceof net.minecraft.item.ItemSword || item instanceof net.minecraft.item.ItemBlock) {
/* 42 */               OringoClient.mc.field_71442_b.func_78753_a(container.field_75152_c, i, 0, 1, (EntityPlayer)OringoClient.mc.field_71439_g);
/* 43 */               this.timer.updateTime();
/*    */               return;
/*    */             } 
/*    */           } 
/*    */         } 
/* 48 */         for (i = 0; i < ((ContainerChest)container).func_85151_d().func_70302_i_(); i++) {
/* 49 */           if (container.func_75139_a(i).func_75216_d()) {
/* 50 */             Item item = container.func_75139_a(i).func_75211_c().func_77973_b();
/* 51 */             if (this.stealTrash.isEnabled() || item instanceof net.minecraft.item.ItemEnderPearl || item instanceof net.minecraft.item.ItemTool || item instanceof net.minecraft.item.ItemArmor || item instanceof net.minecraft.item.ItemBow || item instanceof net.minecraft.item.ItemPotion || item == Items.field_151032_g || item instanceof net.minecraft.item.ItemAppleGold || item instanceof net.minecraft.item.ItemSword || item instanceof net.minecraft.item.ItemBlock)
/*    */               return; 
/*    */           } 
/*    */         } 
/* 55 */         if (this.close.isEnabled()) OringoClient.mc.field_71439_g.func_71053_j(); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\player\ChestStealer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */