/*    */ package me.oringo.oringoclient.qolfeatures.module.player;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.events.PacketSentEvent;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*    */ import me.oringo.oringoclient.utils.MilliTimer;
/*    */ import me.oringo.oringoclient.utils.SkyblockUtils;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.enchantment.Enchantment;
/*    */ import net.minecraft.enchantment.EnchantmentHelper;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.ItemTool;
/*    */ import net.minecraft.network.play.client.C02PacketUseEntity;
/*    */ import net.minecraft.network.play.client.C07PacketPlayerDigging;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class AutoTool extends Module {
/*    */   public BooleanSetting tools;
/*    */   
/*    */   public AutoTool() {
/* 22 */     super("Auto Tool", Module.Category.PLAYER);
/*    */ 
/*    */ 
/*    */     
/* 26 */     this.tools = new BooleanSetting("Tools", true); this.swords = new BooleanSetting("Swords", true);
/*    */ 
/*    */     
/* 29 */     this.delay = new MilliTimer();
/*    */     addSettings(new Setting[] { (Setting)this.tools, (Setting)this.swords });
/*    */   } public BooleanSetting swords; private MilliTimer delay; @SubscribeEvent
/*    */   public void onPacket(PacketSentEvent event) {
/* 33 */     if (!isToggled() || OringoClient.mc.field_71439_g == null)
/* 34 */       return;  if (this.tools.isEnabled() && !OringoClient.mc.field_71439_g.func_71039_bw() && event.packet instanceof C07PacketPlayerDigging && ((C07PacketPlayerDigging)event.packet).func_180762_c() == C07PacketPlayerDigging.Action.START_DESTROY_BLOCK) {
/* 35 */       for (int i = 0; i < 9; i++) {
/* 36 */         ItemStack stack = OringoClient.mc.field_71439_g.field_71071_by.func_70301_a(i);
/* 37 */         Block block = OringoClient.mc.field_71441_e.func_180495_p(((C07PacketPlayerDigging)event.packet).func_179715_a()).func_177230_c();
/* 38 */         if (stack != null && block != null && stack.func_150997_a(block) > ((OringoClient.mc.field_71439_g.field_71071_by.func_70448_g() == null) ? 1.0F : OringoClient.mc.field_71439_g.field_71071_by.func_70448_g().func_150997_a(block))) {
/* 39 */           OringoClient.mc.field_71439_g.field_71071_by.field_70461_c = i;
/*    */         }
/*    */       } 
/* 42 */       SkyblockUtils.updateItemNoEvent();
/* 43 */     } else if (this.delay.hasTimePassed(500L) && !OringoClient.mc.field_71439_g.func_71039_bw() && this.swords.isEnabled() && event.packet instanceof C02PacketUseEntity && ((C02PacketUseEntity)event.packet).func_149565_c() == C02PacketUseEntity.Action.ATTACK) {
/* 44 */       for (int i = 0; i < 9; i++) {
/* 45 */         ItemStack stack = OringoClient.mc.field_71439_g.field_71071_by.func_70301_a(i);
/* 46 */         if (stack != null && getToolDamage(stack) > ((OringoClient.mc.field_71439_g.field_71071_by.func_70448_g() == null) ? 0.0F : getToolDamage(OringoClient.mc.field_71439_g.field_71071_by.func_70448_g()))) {
/* 47 */           OringoClient.mc.field_71439_g.field_71071_by.field_70461_c = i;
/*    */         }
/*    */       } 
/* 50 */       SkyblockUtils.updateItemNoEvent();
/*    */     } 
/* 52 */     if ((event.packet instanceof C09PacketHeldItemChange && OringoClient.mc.field_71439_g.field_71071_by.func_70301_a(((C09PacketHeldItemChange)event.packet).func_149614_c()) != null) || (event.packet instanceof C08PacketPlayerBlockPlacement && ((C08PacketPlayerBlockPlacement)event.packet).func_149574_g() != null)) {
/* 53 */       this.delay.updateTime();
/*    */     }
/*    */   }
/*    */   
/*    */   public static float getToolDamage(ItemStack tool) {
/* 58 */     float damage = 0.0F;
/* 59 */     if (tool != null && (tool.func_77973_b() instanceof ItemTool || tool.func_77973_b() instanceof ItemSword)) {
/* 60 */       if (tool.func_77973_b() instanceof ItemSword) {
/* 61 */         damage += 4.0F;
/* 62 */       } else if (tool.func_77973_b() instanceof net.minecraft.item.ItemAxe) {
/* 63 */         damage += 3.0F;
/* 64 */       } else if (tool.func_77973_b() instanceof net.minecraft.item.ItemPickaxe) {
/* 65 */         damage += 2.0F;
/*    */       }
/* 67 */       else if (tool.func_77973_b() instanceof net.minecraft.item.ItemSpade) {
/* 68 */         damage++;
/*    */       } 
/*    */       
/* 71 */       damage += (tool.func_77973_b() instanceof ItemTool) ? ((ItemTool)tool.func_77973_b()).func_150913_i().func_78000_c() : ((ItemSword)tool.func_77973_b()).func_150931_i();
/* 72 */       damage = (float)(damage + 1.25D * EnchantmentHelper.func_77506_a(Enchantment.field_180314_l.field_77352_x, tool));
/* 73 */       damage = (float)(damage + EnchantmentHelper.func_77506_a(Enchantment.field_180314_l.field_77352_x, tool) * 0.5D);
/*    */     } 
/* 75 */     return damage;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\player\AutoTool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */