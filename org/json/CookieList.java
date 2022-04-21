/*    */ package org.json;
/*    */ 
/*    */ import java.util.Map;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CookieList
/*    */ {
/*    */   public static JSONObject toJSONObject(String string) throws JSONException {
/* 50 */     JSONObject jo = new JSONObject();
/* 51 */     JSONTokener x = new JSONTokener(string);
/* 52 */     while (x.more()) {
/* 53 */       String name = Cookie.unescape(x.nextTo('='));
/* 54 */       x.next('=');
/* 55 */       jo.put(name, Cookie.unescape(x.nextTo(';')));
/* 56 */       x.next();
/*    */     } 
/* 58 */     return jo;
/*    */   }
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
/*    */   public static String toString(JSONObject jo) throws JSONException {
/* 71 */     boolean b = false;
/* 72 */     StringBuilder sb = new StringBuilder();
/* 73 */     for (Map.Entry<String, ?> entry : jo.entrySet()) {
/* 74 */       String key = entry.getKey();
/* 75 */       Object value = entry.getValue();
/* 76 */       if (!JSONObject.NULL.equals(value)) {
/* 77 */         if (b) {
/* 78 */           sb.append(';');
/*    */         }
/* 80 */         sb.append(Cookie.escape(key));
/* 81 */         sb.append("=");
/* 82 */         sb.append(Cookie.escape(value.toString()));
/* 83 */         b = true;
/*    */       } 
/*    */     } 
/* 86 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\org\json\CookieList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */