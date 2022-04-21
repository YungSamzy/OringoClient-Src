/*    */ package org.json;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ import java.util.Map;
/*    */ import java.util.Properties;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Property
/*    */ {
/*    */   public static JSONObject toJSONObject(Properties properties) throws JSONException {
/* 44 */     JSONObject jo = new JSONObject((properties == null) ? 0 : properties.size());
/* 45 */     if (properties != null && !properties.isEmpty()) {
/* 46 */       Enumeration<?> enumProperties = properties.propertyNames();
/* 47 */       while (enumProperties.hasMoreElements()) {
/* 48 */         String name = (String)enumProperties.nextElement();
/* 49 */         jo.put(name, properties.getProperty(name));
/*    */       } 
/*    */     } 
/* 52 */     return jo;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Properties toProperties(JSONObject jo) throws JSONException {
/* 62 */     Properties properties = new Properties();
/* 63 */     if (jo != null) {
/* 64 */       for (Map.Entry<String, ?> entry : jo.entrySet()) {
/* 65 */         Object value = entry.getValue();
/* 66 */         if (!JSONObject.NULL.equals(value)) {
/* 67 */           properties.put(entry.getKey(), value.toString());
/*    */         }
/*    */       } 
/*    */     }
/* 71 */     return properties;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\org\json\Property.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */