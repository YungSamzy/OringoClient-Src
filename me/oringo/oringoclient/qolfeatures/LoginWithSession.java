/*    */ package me.oringo.oringoclient.qolfeatures;
/*    */ 
/*    */ import com.google.gson.JsonParser;
/*    */ import java.io.InputStreamReader;
/*    */ import java.lang.reflect.Field;
/*    */ import java.net.URL;
/*    */ import javax.swing.JOptionPane;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.util.Session;
/*    */ import net.minecraftforge.client.event.GuiScreenEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ 
/*    */ public class LoginWithSession
/*    */ {
/* 18 */   private Session original = null;
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onGuiCreate(GuiScreenEvent.InitGuiEvent.Post event) {
/* 22 */     if (OringoClient.devMode && 
/* 23 */       event.gui instanceof net.minecraft.client.gui.GuiMainMenu) {
/* 24 */       event.buttonList.add(new GuiButton(2137, 5, 5, 100, 20, "Login"));
/*    */     }
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onClick(GuiScreenEvent.ActionPerformedEvent.Post event) {
/* 30 */     if (event.gui instanceof net.minecraft.client.gui.GuiMainMenu && 
/* 31 */       event.button.field_146127_k == 2137) {
/* 32 */       if (this.original == null)
/* 33 */         this.original = Minecraft.func_71410_x().func_110432_I(); 
/* 34 */       String login = JOptionPane.showInputDialog("Login");
/* 35 */       if (login == null || login.isEmpty())
/* 36 */         return;  if (login.equalsIgnoreCase("reset")) {
/*    */         try {
/* 38 */           Field field_71449_j = Minecraft.class.getDeclaredField("field_71449_j");
/* 39 */           field_71449_j.setAccessible(true);
/* 40 */           field_71449_j.set(Minecraft.func_71410_x(), this.original);
/* 41 */         } catch (Exception e) {
/* 42 */           e.printStackTrace();
/*    */         } 
/*    */         return;
/*    */       } 
/*    */       try {
/* 47 */         Field field_71449_j = Minecraft.class.getDeclaredField("field_71449_j");
/* 48 */         field_71449_j.setAccessible(true);
/*    */         
/* 50 */         String username = (new JsonParser()).parse(new InputStreamReader((new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + login.split(": ")[1])).openStream())).getAsJsonObject().get("name").getAsString();
/*    */         
/* 52 */         field_71449_j.set(Minecraft.func_71410_x(), new Session(username, login.split(": ")[1], login.split(": ")[0], "mojang"));
/* 53 */       } catch (Exception e) {
/* 54 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\LoginWithSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */