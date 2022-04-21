/*     */ package org.json;
/*     */ 
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HTTP
/*     */ {
/*     */   public static final String CRLF = "\r\n";
/*     */   
/*     */   public static JSONObject toJSONObject(String string) throws JSONException {
/*  73 */     JSONObject jo = new JSONObject();
/*  74 */     HTTPTokener x = new HTTPTokener(string);
/*     */ 
/*     */     
/*  77 */     String token = x.nextToken();
/*  78 */     if (token.toUpperCase(Locale.ROOT).startsWith("HTTP")) {
/*     */ 
/*     */ 
/*     */       
/*  82 */       jo.put("HTTP-Version", token);
/*  83 */       jo.put("Status-Code", x.nextToken());
/*  84 */       jo.put("Reason-Phrase", x.nextTo(false));
/*  85 */       x.next();
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/*  91 */       jo.put("Method", token);
/*  92 */       jo.put("Request-URI", x.nextToken());
/*  93 */       jo.put("HTTP-Version", x.nextToken());
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  98 */     while (x.more()) {
/*  99 */       String name = x.nextTo(':');
/* 100 */       x.next(':');
/* 101 */       jo.put(name, x.nextTo(false));
/* 102 */       x.next();
/*     */     } 
/* 104 */     return jo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String toString(JSONObject jo) throws JSONException {
/* 129 */     StringBuilder sb = new StringBuilder();
/* 130 */     if (jo.has("Status-Code") && jo.has("Reason-Phrase")) {
/* 131 */       sb.append(jo.getString("HTTP-Version"));
/* 132 */       sb.append(' ');
/* 133 */       sb.append(jo.getString("Status-Code"));
/* 134 */       sb.append(' ');
/* 135 */       sb.append(jo.getString("Reason-Phrase"));
/* 136 */     } else if (jo.has("Method") && jo.has("Request-URI")) {
/* 137 */       sb.append(jo.getString("Method"));
/* 138 */       sb.append(' ');
/* 139 */       sb.append('"');
/* 140 */       sb.append(jo.getString("Request-URI"));
/* 141 */       sb.append('"');
/* 142 */       sb.append(' ');
/* 143 */       sb.append(jo.getString("HTTP-Version"));
/*     */     } else {
/* 145 */       throw new JSONException("Not enough material for an HTTP header.");
/*     */     } 
/* 147 */     sb.append("\r\n");
/* 148 */     for (Map.Entry<String, ?> entry : jo.entrySet()) {
/* 149 */       String key = entry.getKey();
/* 150 */       if (!"HTTP-Version".equals(key) && !"Status-Code".equals(key) && 
/* 151 */         !"Reason-Phrase".equals(key) && !"Method".equals(key) && 
/* 152 */         !"Request-URI".equals(key) && !JSONObject.NULL.equals(entry.getValue())) {
/* 153 */         sb.append(key);
/* 154 */         sb.append(": ");
/* 155 */         sb.append(jo.optString(key));
/* 156 */         sb.append("\r\n");
/*     */       } 
/*     */     } 
/* 159 */     sb.append("\r\n");
/* 160 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\org\json\HTTP.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */