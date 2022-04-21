/*    */ package me.oringo.oringoclient.qolfeatures.module.settings.impl;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ 
/*    */ public class BooleanSetting
/*    */   extends Setting {
/*    */   @Expose
/*    */   @SerializedName("value")
/*    */   private boolean enabled;
/*    */   
/*    */   public BooleanSetting(String name, boolean enabled) {
/* 14 */     this.name = name;
/* 15 */     this.enabled = enabled;
/*    */   }
/*    */   
/*    */   public boolean isEnabled() {
/* 19 */     return this.enabled;
/*    */   }
/*    */   
/*    */   public void setEnabled(boolean enabled) {
/* 23 */     this.enabled = enabled;
/*    */   }
/*    */   
/*    */   public void toggle() {
/* 27 */     setEnabled(!isEnabled());
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\settings\impl\BooleanSetting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */