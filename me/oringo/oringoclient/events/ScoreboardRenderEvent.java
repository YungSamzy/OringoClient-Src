/*    */ package me.oringo.oringoclient.events;
/*    */ 
/*    */ import net.minecraft.client.gui.ScaledResolution;
/*    */ import net.minecraft.scoreboard.ScoreObjective;
/*    */ import net.minecraftforge.fml.common.eventhandler.Cancelable;
/*    */ import net.minecraftforge.fml.common.eventhandler.Event;
/*    */ 
/*    */ @Cancelable
/*    */ public class ScoreboardRenderEvent
/*    */   extends Event {
/*    */   public ScoreObjective objective;
/*    */   public ScaledResolution resolution;
/*    */   
/*    */   public ScoreboardRenderEvent(ScoreObjective objective, ScaledResolution resolution) {
/* 15 */     this.objective = objective;
/* 16 */     this.resolution = resolution;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\events\ScoreboardRenderEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */