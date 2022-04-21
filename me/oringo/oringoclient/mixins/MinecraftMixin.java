/*     */ package me.oringo.oringoclient.mixins;
/*     */ 
/*     */ import com.mojang.authlib.properties.PropertyMap;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.events.LeftClickEvent;
/*     */ import me.oringo.oringoclient.events.RightClickEvent;
/*     */ import me.oringo.oringoclient.mixins.entity.PlayerSPAccessor;
/*     */ import me.oringo.oringoclient.qolfeatures.module.combat.KillAura;
/*     */ import me.oringo.oringoclient.qolfeatures.module.player.FastPlace;
/*     */ import me.oringo.oringoclient.qolfeatures.module.render.ServerRotations;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.multiplayer.PlayerControllerMP;
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Timer;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.fml.common.eventhandler.Event;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.Shadow;
/*     */ import org.spongepowered.asm.mixin.injection.At;
/*     */ import org.spongepowered.asm.mixin.injection.Inject;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
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
/*     */ @Mixin({Minecraft.class})
/*     */ public abstract class MinecraftMixin
/*     */ {
/*     */   @Shadow
/*     */   private int field_71429_W;
/*     */   @Shadow
/*     */   public MovingObjectPosition field_71476_x;
/*     */   @Shadow
/*     */   public WorldClient field_71441_e;
/*     */   @Shadow
/*     */   public PlayerControllerMP field_71442_b;
/*     */   @Shadow
/*     */   private Entity field_175622_Z;
/*     */   @Shadow
/*     */   public EntityPlayerSP field_71439_g;
/*     */   
/*     */   @Inject(method = {"getRenderViewEntity"}, at = {@At("HEAD")})
/*     */   public void getRenderViewEntity(CallbackInfoReturnable<Entity> cir) {
/*  62 */     if (!ServerRotations.getInstance().isToggled() || this.field_175622_Z == null || this.field_175622_Z != OringoClient.mc.field_71439_g)
/*  63 */       return;  if (!(ServerRotations.getInstance()).onlyKillAura.isEnabled() || KillAura.target != null) {
/*  64 */       ((EntityLivingBase)this.field_175622_Z).field_70759_as = ((PlayerSPAccessor)this.field_175622_Z).getLastReportedYaw();
/*     */       
/*  66 */       ((EntityLivingBase)this.field_175622_Z).field_70761_aq = ((PlayerSPAccessor)this.field_175622_Z).getLastReportedYaw();
/*     */     }  } @Shadow public GuiScreen field_71462_r; @Shadow
/*     */   public GameSettings field_71474_y; @Shadow
/*     */   public boolean field_71415_G; @Shadow
/*     */   public boolean field_175612_E; @Shadow
/*     */   private Timer field_71428_T; @Shadow
/*     */   private int field_71467_ac; @Shadow
/*     */   public abstract PropertyMap func_181037_M(); @Inject(method = {"runTick"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;dispatchKeypresses()V")})
/*  74 */   public void keyPresses(CallbackInfo ci) { int k = (Keyboard.getEventKey() == 0) ? (Keyboard.getEventCharacter() + 256) : Keyboard.getEventKey();
/*  75 */     if (OringoClient.mc.field_71462_r == null && 
/*  76 */       Keyboard.getEventKeyState()) OringoClient.handleKeypress(k);
/*     */      }
/*     */ 
/*     */   
/*     */   @Inject(method = {"rightClickMouse"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void onRightClick(CallbackInfo callbackInfo) {
/*  82 */     if (MinecraftForge.EVENT_BUS.post((Event)new RightClickEvent())) callbackInfo.cancel(); 
/*     */   }
/*     */   
/*     */   @Inject(method = {"rightClickMouse"}, at = {@At("RETURN")}, cancellable = true)
/*     */   public void onRightClickPost(CallbackInfo callbackInfo) {
/*  87 */     if (FastPlace.getInstance().isToggled()) this.field_71467_ac = (int)(FastPlace.getInstance()).placeDelay.getValue(); 
/*     */   }
/*     */   
/*     */   @Inject(method = {"clickMouse"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void onClick(CallbackInfo callbackInfo) {
/*  92 */     if (MinecraftForge.EVENT_BUS.post((Event)new LeftClickEvent())) callbackInfo.cancel(); 
/*  93 */     if (OringoClient.noHitDelay.isToggled() || OringoClient.mithrilMacro.isToggled()) this.field_71429_W = 0; 
/*     */   }
/*     */   
/*     */   @Inject(method = {"sendClickBlockToController"}, at = {@At("RETURN")})
/*     */   public void sendClickBlock(CallbackInfo callbackInfo) {
/*  98 */     boolean click = (this.field_71462_r == null && this.field_71474_y.field_74312_F.func_151470_d() && this.field_71415_G);
/*  99 */     if (OringoClient.fastBreak.isToggled() && click && this.field_71476_x != null && this.field_71476_x.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK)
/* 100 */       for (int i = 0; i < OringoClient.fastBreak.maxBlocks.getValue(); ) {
/* 101 */         BlockPos prevBlockPos = this.field_71476_x.func_178782_a();
/* 102 */         this.field_71476_x = this.field_175622_Z.func_174822_a(this.field_71442_b.func_78757_d(), 1.0F);
/* 103 */         BlockPos blockpos = this.field_71476_x.func_178782_a();
/* 104 */         if (this.field_71476_x.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && blockpos != prevBlockPos && this.field_71441_e.func_180495_p(blockpos).func_177230_c().func_149688_o() != Material.field_151579_a) {
/* 105 */           this.field_71439_g.func_71038_i();
/* 106 */           this.field_71442_b.func_180511_b(blockpos, this.field_71476_x.field_178784_b);
/*     */           i++;
/*     */         } 
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\mixins\MinecraftMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */