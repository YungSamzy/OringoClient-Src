/*    */ package me.oringo.oringoclient.qolfeatures.module.settings;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ 
/*    */ public class Setting
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("name")
/*    */   public String name;
/*    */   private boolean hidden;
/*    */   
/*    */   public void setHidden(boolean hidden) {
/* 14 */     this.hidden = hidden;
/*    */   }
/*    */   
/*    */   public boolean isHidden() {
/* 18 */     return this.hidden;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\settings\Setting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */