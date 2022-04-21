/*     */ package me.oringo.oringoclient.config;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.annotations.Expose;
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.nio.file.Files;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.ModeSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.StringSetting;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConfigManager
/*     */ {
/*     */   public static void loadConfig() {
/*     */     try {
/*  28 */       String configString = new String(Files.readAllBytes((new File(OringoClient.mc.field_71412_D.getPath() + "/config/OringoClient/OringoClient.json")).toPath()));
/*  29 */       Gson gson = (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
/*  30 */       Module[] modules = (Module[])gson.fromJson(configString, Module[].class);
/*     */       
/*  32 */       for (Module module : OringoClient.modules) {
/*  33 */         for (Module configModule : modules) {
/*  34 */           if (module.getName().equals(configModule.getName())) {
/*     */             try {
/*  36 */               module.setToggled(configModule.isToggled());
/*  37 */               module.setKeycode(configModule.getKeycode());
/*     */               
/*  39 */               for (Setting setting : module.settings) {
/*  40 */                 for (ConfigSetting cfgSetting : configModule.cfgSettings) {
/*  41 */                   if (setting.name.equals(cfgSetting.name)) {
/*  42 */                     if (setting instanceof BooleanSetting) {
/*  43 */                       ((BooleanSetting)setting).setEnabled(((Boolean)cfgSetting.value).booleanValue());
/*  44 */                     } else if (setting instanceof ModeSetting) {
/*  45 */                       ((ModeSetting)setting).setSelected((String)cfgSetting.value);
/*  46 */                     } else if (setting instanceof NumberSetting) {
/*  47 */                       ((NumberSetting)setting).setValue(((Double)cfgSetting.value).doubleValue());
/*  48 */                     } else if (setting instanceof StringSetting) {
/*  49 */                       ((StringSetting)setting).setValue((String)cfgSetting.value);
/*     */                     } 
/*     */                   }
/*     */                 } 
/*     */               } 
/*  54 */             } catch (Exception e) {
/*  55 */               e.printStackTrace();
/*  56 */               System.out.println("Config Issue");
/*     */             }
/*     */           
/*     */           }
/*     */         } 
/*     */       } 
/*  62 */     } catch (Exception e) {
/*  63 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void saveConfig() {
/*  68 */     Gson gson = (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
/*  69 */     for (Module module : OringoClient.modules) {
/*  70 */       module.onSave();
/*  71 */       List<ConfigSetting> settings = new ArrayList<>();
/*  72 */       for (Setting setting : module.settings) {
/*  73 */         ConfigSetting cfgSetting = new ConfigSetting(null, null);
/*  74 */         cfgSetting.name = setting.name;
/*  75 */         if (setting instanceof BooleanSetting) {
/*  76 */           cfgSetting.value = Boolean.valueOf(((BooleanSetting)setting).isEnabled());
/*  77 */         } else if (setting instanceof ModeSetting) {
/*  78 */           cfgSetting.value = ((ModeSetting)setting).getSelected();
/*  79 */         } else if (setting instanceof NumberSetting) {
/*  80 */           cfgSetting.value = Double.valueOf(((NumberSetting)setting).getValue());
/*  81 */         } else if (setting instanceof StringSetting) {
/*  82 */           cfgSetting.value = ((StringSetting)setting).getValue();
/*     */         } 
/*  84 */         settings.add(cfgSetting);
/*     */       } 
/*  86 */       module.cfgSettings = settings.<ConfigSetting>toArray(new ConfigSetting[0]);
/*     */     } 
/*     */     try {
/*  89 */       Files.write((new File(OringoClient.mc.field_71412_D.getPath() + "/config/OringoClient/OringoClient.json")).toPath(), gson.toJson(OringoClient.modules).getBytes(StandardCharsets.UTF_8), new java.nio.file.OpenOption[0]);
/*  90 */     } catch (IOException e) {
/*  91 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static class ConfigSetting
/*     */   {
/*     */     @Expose
/*     */     @SerializedName("name")
/*     */     public String name;
/*     */     @Expose
/*     */     @SerializedName("value")
/*     */     public Object value;
/*     */     
/*     */     public ConfigSetting(String name, Object value) {
/* 106 */       this.name = name;
/* 107 */       this.value = value;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\config\ConfigManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */