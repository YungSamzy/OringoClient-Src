/*    */ package me.oringo.oringoclient.qolfeatures.module.player;
/*    */ 
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.events.PacketReceivedEvent;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*    */ import net.minecraft.client.entity.EntityPlayerSP;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.C03PacketPlayer;
/*    */ import net.minecraft.network.play.server.S08PacketPlayerPosLook;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class NoRotate extends Module {
/* 17 */   public BooleanSetting keepMotion = new BooleanSetting("Keep motion", true); public BooleanSetting pitch = new BooleanSetting("0 pitch", false);
/*    */   
/*    */   private boolean doneLoadingTerrain;
/*    */ 
/*    */   
/*    */   public NoRotate() {
/* 23 */     super("No Rotate", 0, Module.Category.PLAYER);
/* 24 */     addSettings(new Setting[] { (Setting)this.keepMotion, (Setting)this.pitch });
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onPacket(PacketReceivedEvent event) {
/* 29 */     if (event.packet instanceof S08PacketPlayerPosLook) {
/* 30 */       if (isToggled() && OringoClient.mc.field_71439_g != null && (((S08PacketPlayerPosLook)event.packet).func_148930_g() != 0.0D || this.pitch.isEnabled())) {
/* 31 */         event.setCanceled(true);
/* 32 */         EntityPlayerSP entityPlayerSP = OringoClient.mc.field_71439_g;
/* 33 */         double d0 = ((S08PacketPlayerPosLook)event.packet).func_148932_c();
/* 34 */         double d1 = ((S08PacketPlayerPosLook)event.packet).func_148928_d();
/* 35 */         double d2 = ((S08PacketPlayerPosLook)event.packet).func_148933_e();
/* 36 */         float f = ((S08PacketPlayerPosLook)event.packet).func_148931_f();
/* 37 */         float f1 = ((S08PacketPlayerPosLook)event.packet).func_148930_g();
/*    */         
/* 39 */         if (((S08PacketPlayerPosLook)event.packet).func_179834_f().contains(S08PacketPlayerPosLook.EnumFlags.X))
/* 40 */         { d0 += ((EntityPlayer)entityPlayerSP).field_70165_t; }
/*    */         
/* 42 */         else if (!this.keepMotion.isEnabled()) { ((EntityPlayer)entityPlayerSP).field_70159_w = 0.0D; }
/*    */         
/* 44 */         if (((S08PacketPlayerPosLook)event.packet).func_179834_f().contains(S08PacketPlayerPosLook.EnumFlags.Y)) {
/* 45 */           d1 += ((EntityPlayer)entityPlayerSP).field_70163_u;
/*    */         } else {
/* 47 */           ((EntityPlayer)entityPlayerSP).field_70181_x = 0.0D;
/*    */         } 
/*    */         
/* 50 */         if (((S08PacketPlayerPosLook)event.packet).func_179834_f().contains(S08PacketPlayerPosLook.EnumFlags.Z))
/* 51 */         { d2 += ((EntityPlayer)entityPlayerSP).field_70161_v; }
/*    */         
/* 53 */         else if (!this.keepMotion.isEnabled()) { ((EntityPlayer)entityPlayerSP).field_70179_y = 0.0D; }
/*    */ 
/*    */         
/* 56 */         if (((S08PacketPlayerPosLook)event.packet).func_179834_f().contains(S08PacketPlayerPosLook.EnumFlags.X_ROT)) {
/* 57 */           f1 += ((EntityPlayer)entityPlayerSP).field_70125_A;
/*    */         }
/*    */         
/* 60 */         if (((S08PacketPlayerPosLook)event.packet).func_179834_f().contains(S08PacketPlayerPosLook.EnumFlags.Y_ROT)) {
/* 61 */           f += ((EntityPlayer)entityPlayerSP).field_70177_z;
/*    */         }
/*    */         
/* 64 */         entityPlayerSP.func_70080_a(d0, d1, d2, ((EntityPlayer)entityPlayerSP).field_70177_z, ((EntityPlayer)entityPlayerSP).field_70125_A);
/*    */         
/* 66 */         OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C03PacketPlayer.C06PacketPlayerPosLook(((EntityPlayer)entityPlayerSP).field_70165_t, (entityPlayerSP.func_174813_aQ()).field_72338_b, ((EntityPlayer)entityPlayerSP).field_70161_v, f, f1, false));
/*    */         
/* 68 */         if (!this.doneLoadingTerrain) {
/* 69 */           OringoClient.mc.field_71439_g.field_70169_q = OringoClient.mc.field_71439_g.field_70165_t;
/* 70 */           OringoClient.mc.field_71439_g.field_70167_r = OringoClient.mc.field_71439_g.field_70163_u;
/* 71 */           OringoClient.mc.field_71439_g.field_70166_s = OringoClient.mc.field_71439_g.field_70161_v;
/* 72 */           OringoClient.mc.func_147108_a((GuiScreen)null);
/*    */         } 
/*    */       } 
/* 75 */       this.doneLoadingTerrain = true;
/*    */     } 
/* 77 */     if (event.packet instanceof net.minecraft.network.play.server.S07PacketRespawn)
/* 78 */       this.doneLoadingTerrain = false; 
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\player\NoRotate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */