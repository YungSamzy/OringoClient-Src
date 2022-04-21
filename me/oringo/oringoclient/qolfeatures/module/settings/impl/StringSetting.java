/*    */ package me.oringo.oringoclient.qolfeatures.module.settings.impl;
/*    */ 
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ 
/*    */ public class StringSetting
/*    */   extends Setting
/*    */ {
/*    */   private String value;
/*    */   
/*    */   public StringSetting(String name) {
/* 11 */     this(name, "");
/*    */   }
/*    */   
/*    */   public StringSetting(String name, String defaultValue) {
/* 15 */     this.name = name;
/* 16 */     this.value = defaultValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean is(String string) {
/* 21 */     return this.value.equals(string);
/*    */   }
/*    */   
/*    */   public String getValue() {
/* 25 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(String value) {
/* 29 */     this.value = value;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\settings\impl\StringSetting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */