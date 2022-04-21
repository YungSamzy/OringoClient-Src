/*     */ package me.oringo.oringoclient.qolfeatures.module;
/*     */ 
/*     */ import com.google.gson.annotations.Expose;
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.config.ConfigManager;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*     */ import me.oringo.oringoclient.utils.MilliTimer;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Module
/*     */ {
/*     */   @Expose
/*     */   @SerializedName("name")
/*     */   public String name;
/*     */   @Expose
/*     */   @SerializedName("toggled")
/*     */   private boolean toggled;
/*     */   @Expose
/*     */   @SerializedName("keyCode")
/*     */   private int keycode;
/*     */   private Category category;
/*     */   public boolean extended;
/*     */   @Expose
/*     */   @SerializedName("settings")
/*     */   public ConfigManager.ConfigSetting[] cfgSettings;
/*     */   private boolean devOnly;
/*  36 */   public MilliTimer toggledTime = new MilliTimer();
/*     */   
/*  38 */   public List<Setting> settings = new ArrayList<>();
/*     */   
/*     */   public Module(String name, int keycode, Category category) {
/*  41 */     this.name = name;
/*  42 */     this.keycode = keycode;
/*  43 */     this.category = category;
/*     */   }
/*     */   
/*     */   public Module(String name, Category category) {
/*  47 */     this.name = name;
/*  48 */     this.keycode = 0;
/*  49 */     this.category = category;
/*     */   }
/*     */   
/*     */   public boolean isToggled() {
/*  53 */     return this.toggled;
/*     */   }
/*     */   
/*     */   public void toggle() {
/*  57 */     this.toggled = !this.toggled;
/*  58 */     if (this.toggled) { onEnable(); }
/*  59 */     else { onDisable(); }
/*  60 */      this.toggledTime.updateTime();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEnable() {}
/*     */ 
/*     */   
/*     */   public void onSave() {}
/*     */ 
/*     */   
/*     */   public boolean isKeybind() {
/*  72 */     return false;
/*     */   }
/*     */   
/*     */   public void addSetting(Setting setting) {
/*  76 */     getSettings().add(setting);
/*     */   }
/*     */   
/*     */   public void addSettings(Setting... settings) {
/*  80 */     for (Setting setting : settings) {
/*  81 */       addSetting(setting);
/*     */     }
/*     */   }
/*     */   
/*     */   public Category getCategory() {
/*  86 */     return this.category;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  90 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean isPressed() {
/*  94 */     return (this.keycode != 0 && Keyboard.isKeyDown(this.keycode) && isKeybind());
/*     */   }
/*     */   
/*     */   public int getKeycode() {
/*  98 */     return this.keycode;
/*     */   }
/*     */   
/*     */   public void setKeycode(int keycode) {
/* 102 */     this.keycode = keycode;
/*     */   }
/*     */   
/*     */   public List<Setting> getSettings() {
/* 106 */     return this.settings;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<Module> getModulesByCategory(Category c) {
/* 111 */     List<Module> modules = new ArrayList<>();
/*     */     
/* 113 */     for (Module m : OringoClient.modules) {
/* 114 */       if (m.getCategory() == c) {
/* 115 */         modules.add(m);
/*     */       }
/*     */     } 
/*     */     
/* 119 */     return modules;
/*     */   }
/*     */   
/*     */   public static <T> T getModule(Class<T> module) {
/* 123 */     for (Module m : OringoClient.modules) {
/* 124 */       if (m.getClass().equals(module)) {
/* 125 */         return (T)m;
/*     */       }
/*     */     } 
/*     */     
/* 129 */     return null;
/*     */   }
/*     */   
/*     */   public static Module getModuleByName(String string) {
/* 133 */     for (Module m : OringoClient.modules) {
/* 134 */       if (m.getName().equalsIgnoreCase(string)) {
/* 135 */         return m;
/*     */       }
/*     */     } 
/*     */     
/* 139 */     return null;
/*     */   }
/*     */   
/*     */   public void setToggled(boolean toggled) {
/* 143 */     this.toggled = toggled;
/* 144 */     this.toggledTime.updateTime();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDisable() {}
/*     */ 
/*     */   
/*     */   public void setDevOnly(boolean devOnly) {
/* 152 */     this.devOnly = devOnly;
/*     */   }
/*     */   
/*     */   public boolean isDevOnly() {
/* 156 */     return this.devOnly;
/*     */   }
/*     */   
/*     */   public enum Category {
/* 160 */     COMBAT("Combat"),
/* 161 */     SKYBLOCK("Skyblock"),
/* 162 */     RENDER("Render"),
/* 163 */     PLAYER("Player"),
/* 164 */     MACRO("Macros"),
/* 165 */     OTHER("Other"),
/* 166 */     KEYBINDS("Keybinds");
/*     */     
/*     */     public String name;
/*     */ 
/*     */     
/*     */     Category(String name) {
/* 172 */       this.name = name;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\Module.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */