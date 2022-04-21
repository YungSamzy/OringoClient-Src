/*    */ package me.oringo.oringoclient.qolfeatures.module.skyblock;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.events.MotionUpdateEvent;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*    */ import me.oringo.oringoclient.utils.RotationUtils;
/*    */ import me.oringo.oringoclient.utils.SkyblockUtils;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.C0APacketAnimation;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraftforge.event.world.WorldEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.EventPriority;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class Aimbot
/*    */   extends Module
/*    */ {
/* 23 */   public NumberSetting yOffset = new NumberSetting("Y offset", 0.0D, -2.0D, 2.0D, 0.1D);
/*    */   
/*    */   public Aimbot() {
/* 26 */     super("Blood aimbot", 0, Module.Category.SKYBLOCK);
/* 27 */     addSetting((Setting)this.yOffset);
/*    */   }
/*    */   
/* 30 */   private static List<Entity> killed = new ArrayList<>();
/*    */   public static boolean attack;
/*    */   
/*    */   @SubscribeEvent(priority = EventPriority.HIGH)
/*    */   public void onMove(MotionUpdateEvent.Pre event) {
/* 35 */     if (!isToggled() || !SkyblockUtils.inDungeon || !SkyblockUtils.inBlood || OringoClient.mc.field_71441_e == null)
/* 36 */       return;  for (Entity entity : OringoClient.mc.field_71441_e.field_73010_i) {
/* 37 */       if (entity.func_70032_d((Entity)OringoClient.mc.field_71439_g) < 20.0F && entity instanceof net.minecraft.entity.player.EntityPlayer && !entity.field_70128_L && !killed.contains(entity)) {
/* 38 */         for (String name : new String[] { "Revoker", "Psycho", "Reaper", "Cannibal", "Mute", "Ooze", "Putrid", "Freak", "Leech", "Tear", "Parasite", "Flamer", "Skull", "Mr. Dead", "Vader", "Frost", "Walker", "WanderingSoul" }) {
/* 39 */           if (entity.func_70005_c_().contains(name)) {
/* 40 */             attack = true;
/* 41 */             float[] angles = RotationUtils.getAngles(new Vec3(entity.field_70165_t, entity.field_70163_u + this.yOffset.getValue(), entity.field_70161_v));
/* 42 */             event.yaw = angles[0];
/* 43 */             event.pitch = angles[1];
/* 44 */             killed.add(entity);
/*    */             break;
/*    */           } 
/*    */         } 
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onMovePost(MotionUpdateEvent.Post event) {
/* 54 */     if (!attack)
/* 55 */       return;  OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C0APacketAnimation());
/* 56 */     attack = false;
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onWorldLoad(WorldEvent.Load event) {
/* 61 */     killed.clear();
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\skyblock\Aimbot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */