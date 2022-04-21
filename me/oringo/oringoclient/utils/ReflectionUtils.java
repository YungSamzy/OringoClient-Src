/*    */ package me.oringo.oringoclient.utils;
/*    */ 
/*    */ import java.lang.reflect.Field;
/*    */ 
/*    */ public class ReflectionUtils
/*    */ {
/*    */   public static void setFieldByIndex(Object object, int index, Object value) {
/*    */     try {
/*  9 */       Field field = object.getClass().getDeclaredFields()[index];
/* 10 */       field.setAccessible(true);
/* 11 */       field.set(object, value);
/* 12 */     } catch (Exception e) {
/* 13 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void setFieldByIndex(Class clazz, int index, Object object, Object value) {
/*    */     try {
/* 19 */       Field field = clazz.getDeclaredFields()[index];
/* 20 */       field.setAccessible(true);
/* 21 */       field.set(object, value);
/* 22 */     } catch (Exception e) {
/* 23 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public static Object getFieldByIndex(Object object, int index) {
/*    */     try {
/* 29 */       Field field = object.getClass().getDeclaredFields()[index];
/* 30 */       field.setAccessible(true);
/* 31 */       return field.get(object);
/* 32 */     } catch (Exception e) {
/* 33 */       e.printStackTrace();
/*    */       
/* 35 */       return null;
/*    */     } 
/*    */   }
/*    */   public static Object getFieldByName(Class clazz, String name, Object object) {
/*    */     try {
/* 40 */       Field field = clazz.getDeclaredField(name);
/* 41 */       field.setAccessible(true);
/* 42 */       return field.get(object);
/* 43 */     } catch (Exception exception) {
/*    */       
/* 45 */       return null;
/*    */     } 
/*    */   }
/*    */   public static Object getFieldByName(Object obj, String name) {
/*    */     try {
/* 50 */       Field field = obj.getClass().getDeclaredField(name);
/* 51 */       field.setAccessible(true);
/* 52 */       return field.get(obj);
/* 53 */     } catch (Exception exception) {
/*    */       
/* 55 */       return null;
/*    */     } 
/*    */   }
/*    */   public static void setFieldByName(Object object, String name, Object value) {
/*    */     try {
/* 60 */       Field field = object.getClass().getDeclaredField(name);
/* 61 */       field.setAccessible(true);
/* 62 */       field.set(object, value);
/* 63 */     } catch (Exception e) {
/* 64 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclien\\utils\ReflectionUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */