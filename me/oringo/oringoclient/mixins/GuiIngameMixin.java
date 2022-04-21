/*    */ package me.oringo.oringoclient.mixins;
/*    */ 
/*    */ import me.oringo.oringoclient.events.ScoreboardRenderEvent;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.gui.GuiIngame;
/*    */ import net.minecraft.client.gui.ScaledResolution;
/*    */ import net.minecraft.scoreboard.ScoreObjective;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.fml.common.eventhandler.Event;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ @Mixin({GuiIngame.class})
/*    */ public abstract class GuiIngameMixin
/*    */ {
/*    */   @Inject(method = {"renderScoreboard"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void renderScoreboard(ScoreObjective s, ScaledResolution score, CallbackInfo ci) {
/* 21 */     if (MinecraftForge.EVENT_BUS.post((Event)new ScoreboardRenderEvent(s, score))) ci.cancel(); 
/*    */   }
/*    */   
/*    */   @Shadow
/*    */   public abstract FontRenderer func_175179_f();
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\mixins\GuiIngameMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */