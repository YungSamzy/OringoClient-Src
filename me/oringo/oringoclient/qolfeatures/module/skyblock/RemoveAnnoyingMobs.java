/*    */ package me.oringo.oringoclient.qolfeatures.module.skyblock;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.monster.EntityCreeper;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RemoveAnnoyingMobs
/*    */   extends Module
/*    */ {
/*    */   private Entity golemEntity;
/* 26 */   public static ArrayList<Entity> seraphs = new ArrayList<>();
/*    */   
/* 28 */   public BooleanSetting hidePlayers = new BooleanSetting("Hide players", false);
/*    */   
/*    */   public RemoveAnnoyingMobs() {
/* 31 */     super("Remove Mobs", 0, Module.Category.SKYBLOCK);
/* 32 */     addSettings(new Setting[] { (Setting)this.hidePlayers });
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onUpdate(LivingEvent.LivingUpdateEvent event) {
/* 37 */     if (OringoClient.mc.field_71441_e == null || OringoClient.mc.field_71439_g == null)
/* 38 */       return;  if (isToggled()) {
/* 39 */       if (event.entity instanceof net.minecraft.entity.player.EntityPlayer && !event.entity.equals((Minecraft.func_71410_x()).field_71439_g) && this.golemEntity != null && !this.golemEntity.field_70128_L && this.golemEntity.func_70032_d(event.entity) < 9.0F) {
/* 40 */         event.entity.field_70163_u = 999999.0D;
/* 41 */         event.entity.field_70137_T = 999999.0D;
/* 42 */         event.setCanceled(true);
/*    */       } 
/* 44 */       if (!(event.entity instanceof net.minecraft.entity.item.EntityArmorStand) && !(event.entity instanceof net.minecraft.entity.monster.EntityEnderman) && !(event.entity instanceof net.minecraft.entity.monster.EntityGuardian) && !(event.entity instanceof net.minecraft.entity.item.EntityFallingBlock) && !event.entity.equals((Minecraft.func_71410_x()).field_71439_g)) {
/* 45 */         for (Entity seraph : seraphs) {
/* 46 */           if (event.entity.func_70032_d(seraph) < 5.0F) {
/* 47 */             event.entity.field_70163_u = 999999.0D;
/* 48 */             event.entity.field_70137_T = 999999.0D;
/* 49 */             event.setCanceled(true);
/*    */           } 
/*    */         } 
/*    */       }
/* 53 */       if (event.entity instanceof net.minecraft.client.entity.EntityOtherPlayerMP && this.hidePlayers.isEnabled()) {
/* 54 */         event.entity.field_70163_u = 999999.0D;
/* 55 */         event.entity.field_70137_T = 999999.0D;
/* 56 */         event.setCanceled(true);
/*    */       } 
/*    */       
/* 59 */       if (event.entity.func_145748_c_().func_150254_d().contains("Endstone Protector")) {
/* 60 */         this.golemEntity = event.entity;
/*    */       }
/* 62 */       if (event.entity instanceof EntityCreeper && event.entity.func_82150_aj() && ((EntityCreeper)event.entity).func_110143_aJ() == 20.0F) {
/* 63 */         OringoClient.mc.field_71441_e.func_72900_e(event.entity);
/*    */       }
/* 65 */       if (event.entity instanceof EntityCreeper && event.entity.func_82150_aj() && ((EntityCreeper)event.entity).func_110143_aJ() != 20.0F) {
/* 66 */         event.entity.func_82142_c(false);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onWorldRender(TickEvent.ClientTickEvent event) {
/* 73 */     seraphs.clear();
/* 74 */     if (!isToggled() || OringoClient.mc.field_71441_e == null)
/* 75 */       return;  for (Entity entity : OringoClient.mc.field_71441_e.func_72910_y()) {
/* 76 */       if (entity.func_145748_c_().func_150254_d().contains("Voidgloom Seraph")) {
/* 77 */         seraphs.add(entity);
/*    */       }
/* 79 */       if (entity instanceof net.minecraft.entity.item.EntityFireworkRocket) {
/* 80 */         OringoClient.mc.field_71441_e.func_72900_e(entity);
/*    */       }
/* 82 */       if (entity instanceof net.minecraft.entity.passive.EntityHorse)
/* 83 */         OringoClient.mc.field_71441_e.func_72900_e(entity); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\skyblock\RemoveAnnoyingMobs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */