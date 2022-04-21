/*    */ package me.oringo.oringoclient.qolfeatures;
/*    */ 
/*    */ import java.awt.Desktop;
/*    */ import java.io.IOException;
/*    */ import java.net.URI;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraftforge.client.event.GuiScreenEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Updater
/*    */ {
/*    */   @SubscribeEvent
/*    */   public void onGuiCreate(GuiScreenEvent.InitGuiEvent.Post event) {
/* 18 */     if (event.gui instanceof net.minecraft.client.gui.GuiMainMenu && OringoClient.shouldUpdate) {
/* 19 */       event.buttonList.add(new GuiButton(-2137, 5, 50, 150, 20, "Update Oringo Client"));
/*    */     }
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onClick(GuiScreenEvent.ActionPerformedEvent.Post event) {
/* 25 */     if (event.gui instanceof net.minecraft.client.gui.GuiMainMenu && 
/* 26 */       event.button.field_146127_k == -2137)
/*    */       try {
/* 28 */         Desktop.getDesktop().browse(new URI(OringoClient.vers[1]));
/* 29 */         OringoClient.mc.func_71400_g();
/* 30 */       } catch (IOException|java.net.URISyntaxException e) {
/* 31 */         e.printStackTrace();
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\Updater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */