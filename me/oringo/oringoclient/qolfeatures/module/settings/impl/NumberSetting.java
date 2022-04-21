/*    */ package me.oringo.oringoclient.qolfeatures.module.settings.impl;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ 
/*    */ public class NumberSetting extends Setting {
/*    */   double min;
/*    */   double max;
/*    */   double increment;
/*    */   @Expose
/*    */   @SerializedName("value")
/*    */   private double value;
/*    */   
/*    */   public NumberSetting(String name, double defaultValue, double minimum, double maximum, double increment) {
/* 16 */     this.name = name;
/* 17 */     this.value = defaultValue;
/* 18 */     this.min = minimum;
/* 19 */     this.max = maximum;
/* 20 */     this.increment = increment;
/*    */   }
/*    */   
/*    */   public static double clamp(double value, double min, double max) {
/* 24 */     value = Math.max(min, value);
/* 25 */     value = Math.min(max, value);
/* 26 */     return value;
/*    */   }
/*    */   
/*    */   public double getValue() {
/* 30 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(double value) {
/* 34 */     value = clamp(value, getMin(), getMax());
/* 35 */     value = Math.round(value * 1.0D / getIncrement()) / 1.0D / getIncrement();
/* 36 */     this.value = value;
/*    */   }
/*    */   
/*    */   public double getMin() {
/* 40 */     return this.min;
/*    */   }
/*    */   
/*    */   public void setMin(double min) {
/* 44 */     this.min = min;
/*    */   }
/*    */   
/*    */   public double getMax() {
/* 48 */     return this.max;
/*    */   }
/*    */   
/*    */   public void setMax(double max) {
/* 52 */     this.max = max;
/*    */   }
/*    */   
/*    */   public double getIncrement() {
/* 56 */     return this.increment;
/*    */   }
/*    */   
/*    */   public void setIncrement(double increment) {
/* 60 */     this.increment = increment;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\settings\impl\NumberSetting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */