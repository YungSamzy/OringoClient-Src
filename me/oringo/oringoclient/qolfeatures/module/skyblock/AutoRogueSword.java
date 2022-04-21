/*    */ package me.oringo.oringoclient.qolfeatures.module.skyblock;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*    */ import me.oringo.oringoclient.utils.MilliTimer;
/*    */ import me.oringo.oringoclient.utils.SkyblockUtils;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*    */ 
/*    */ public class AutoRogueSword extends Module {
/*    */   public NumberSetting clicks;
/*    */   
/*    */   public AutoRogueSword() {
/* 16 */     super("Auto Rogue", 0, Module.Category.SKYBLOCK);
/*    */ 
/*    */ 
/*    */     
/* 20 */     this.clicks = new NumberSetting("Clicks", 50.0D, 1.0D, 200.0D, 1.0D);
/*    */     
/* 22 */     this.time = new MilliTimer();
/*    */     addSettings(new Setting[] { (Setting)this.clicks });
/*    */   } private MilliTimer time; @SubscribeEvent
/*    */   public void onTick(TickEvent.ClientTickEvent event) {
/* 26 */     if (OringoClient.mc.field_71439_g == null || !SkyblockUtils.inDungeon || !isToggled())
/* 27 */       return;  if (this.time.hasTimePassed(30000L))
/* 28 */       for (int i = 0; i < 9; i++) {
/* 29 */         if (OringoClient.mc.field_71439_g.field_71071_by.func_70301_a(i) != null && OringoClient.mc.field_71439_g.field_71071_by.func_70301_a(i).func_82833_r().toLowerCase().contains("rogue sword")) {
/* 30 */           int held = OringoClient.mc.field_71439_g.field_71071_by.field_70461_c;
/* 31 */           OringoClient.mc.field_71439_g.field_71071_by.field_70461_c = i;
/* 32 */           SkyblockUtils.updateItemNoEvent();
/* 33 */           for (int x = 0; x < this.clicks.getValue(); x++) {
/* 34 */             OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C08PacketPlayerBlockPlacement(OringoClient.mc.field_71439_g.func_70694_bm()));
/*    */           }
/* 36 */           OringoClient.mc.field_71439_g.field_71071_by.field_70461_c = held;
/* 37 */           SkyblockUtils.updateItemNoEvent();
/* 38 */           this.time.updateTime();
/*    */           break;
/*    */         } 
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\skyblock\AutoRogueSword.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */