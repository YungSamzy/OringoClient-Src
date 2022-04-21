/*    */ package me.oringo.oringoclient.qolfeatures.module.settings.impl;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ 
/*    */ public class ModeSetting
/*    */   extends Setting
/*    */ {
/*    */   @Expose
/*    */   @SerializedName("value")
/*    */   private String selected;
/*    */   private int index;
/*    */   private List<String> modes;
/*    */   private String defaultSelected;
/*    */   
/*    */   public ModeSetting(String name, String defaultSelected, String... options) {
/* 20 */     this.name = name;
/* 21 */     this.defaultSelected = defaultSelected;
/* 22 */     this.modes = Arrays.asList(options);
/* 23 */     this.index = this.modes.indexOf(defaultSelected);
/* 24 */     this.selected = this.modes.get(this.index);
/*    */   }
/*    */   
/*    */   public String getSelected() {
/* 28 */     return this.selected;
/*    */   }
/*    */   
/*    */   public void setSelected(String selected) {
/* 32 */     this.selected = selected;
/* 33 */     this.index = this.modes.indexOf(selected);
/*    */   }
/*    */   
/*    */   public boolean is(String mode) {
/* 37 */     return mode.equals(this.selected);
/*    */   }
/*    */   
/*    */   public int getIndex() {
/* 41 */     return this.index;
/*    */   }
/*    */   
/*    */   public void setIndex(int index) {
/* 45 */     this.index = index;
/* 46 */     this.selected = this.modes.get(index);
/*    */   }
/*    */   
/*    */   public List<String> getModes() {
/* 50 */     return this.modes;
/*    */   }
/*    */   
/*    */   public void setModes(List<String> modes) {
/* 54 */     this.modes = modes;
/*    */   }
/*    */   
/*    */   public void cycle(int key) {
/* 58 */     switch (key) {
/*    */       case 0:
/* 60 */         if (this.index < this.modes.size() - 1) {
/* 61 */           this.index++;
/* 62 */           this.selected = this.modes.get(this.index);
/* 63 */         } else if (this.index >= this.modes.size() - 1) {
/* 64 */           this.index = 0;
/* 65 */           this.selected = this.modes.get(0);
/*    */         } 
/*    */         return;
/*    */       case 1:
/* 69 */         if (this.index > 0) {
/* 70 */           this.index--;
/* 71 */           this.selected = this.modes.get(this.index);
/*    */         } else {
/* 73 */           this.index = this.modes.size() - 1;
/* 74 */           this.selected = this.modes.get(this.index);
/*    */         } 
/*    */         return;
/*    */     } 
/* 78 */     this.index = this.modes.indexOf(this.defaultSelected);
/* 79 */     this.selected = this.modes.get(this.index);
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\settings\impl\ModeSetting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */