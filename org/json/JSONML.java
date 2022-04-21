/*     */ package org.json;
/*     */ 
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
/*     */ public class JSONML
/*     */ {
/*     */   private static Object parse(XMLTokener x, boolean arrayForm, JSONArray ja, boolean keepStrings) throws JSONException {
/*  56 */     String closeTag = null;
/*     */     
/*  58 */     JSONArray newja = null;
/*  59 */     JSONObject newjo = null;
/*     */     
/*  61 */     String tagName = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     label116: while (true) {
/*  70 */       if (!x.more()) {
/*  71 */         throw x.syntaxError("Bad XML");
/*     */       }
/*  73 */       Object token = x.nextContent();
/*  74 */       if (token == XML.LT) {
/*  75 */         token = x.nextToken();
/*  76 */         if (token instanceof Character) {
/*  77 */           if (token == XML.SLASH) {
/*     */ 
/*     */ 
/*     */             
/*  81 */             token = x.nextToken();
/*  82 */             if (!(token instanceof String)) {
/*  83 */               throw new JSONException("Expected a closing name instead of '" + token + "'.");
/*     */             }
/*     */ 
/*     */             
/*  87 */             if (x.nextToken() != XML.GT) {
/*  88 */               throw x.syntaxError("Misshaped close tag");
/*     */             }
/*  90 */             return token;
/*  91 */           }  if (token == XML.BANG) {
/*     */ 
/*     */ 
/*     */             
/*  95 */             char c = x.next();
/*  96 */             if (c == '-') {
/*  97 */               if (x.next() == '-') {
/*  98 */                 x.skipPast("-->"); continue;
/*     */               } 
/* 100 */               x.back(); continue;
/*     */             } 
/* 102 */             if (c == '[') {
/* 103 */               token = x.nextToken();
/* 104 */               if (token.equals("CDATA") && x.next() == '[') {
/* 105 */                 if (ja != null)
/* 106 */                   ja.put(x.nextCDATA()); 
/*     */                 continue;
/*     */               } 
/* 109 */               throw x.syntaxError("Expected 'CDATA['");
/*     */             } 
/*     */             
/* 112 */             int i = 1;
/*     */             while (true)
/* 114 */             { token = x.nextMeta();
/* 115 */               if (token == null)
/* 116 */                 throw x.syntaxError("Missing '>' after '<!'."); 
/* 117 */               if (token == XML.LT) {
/* 118 */                 i++;
/* 119 */               } else if (token == XML.GT) {
/* 120 */                 i--;
/*     */               } 
/* 122 */               if (i <= 0)
/*     */                 continue label116;  }  break;
/* 124 */           }  if (token == XML.QUEST) {
/*     */ 
/*     */ 
/*     */             
/* 128 */             x.skipPast("?>"); continue;
/*     */           } 
/* 130 */           throw x.syntaxError("Misshaped tag");
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 136 */         if (!(token instanceof String)) {
/* 137 */           throw x.syntaxError("Bad tagName '" + token + "'.");
/*     */         }
/* 139 */         tagName = (String)token;
/* 140 */         newja = new JSONArray();
/* 141 */         newjo = new JSONObject();
/* 142 */         if (arrayForm) {
/* 143 */           newja.put(tagName);
/* 144 */           if (ja != null) {
/* 145 */             ja.put(newja);
/*     */           }
/*     */         } else {
/* 148 */           newjo.put("tagName", tagName);
/* 149 */           if (ja != null) {
/* 150 */             ja.put(newjo);
/*     */           }
/*     */         } 
/* 153 */         token = null;
/*     */         while (true) {
/* 155 */           if (token == null) {
/* 156 */             token = x.nextToken();
/*     */           }
/* 158 */           if (token == null) {
/* 159 */             throw x.syntaxError("Misshaped tag");
/*     */           }
/* 161 */           if (!(token instanceof String)) {
/*     */             break;
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 167 */           String attribute = (String)token;
/* 168 */           if (!arrayForm && ("tagName".equals(attribute) || "childNode".equals(attribute))) {
/* 169 */             throw x.syntaxError("Reserved attribute.");
/*     */           }
/* 171 */           token = x.nextToken();
/* 172 */           if (token == XML.EQ) {
/* 173 */             token = x.nextToken();
/* 174 */             if (!(token instanceof String)) {
/* 175 */               throw x.syntaxError("Missing value");
/*     */             }
/* 177 */             newjo.accumulate(attribute, keepStrings ? token : XML.stringToValue((String)token));
/* 178 */             token = null; continue;
/*     */           } 
/* 180 */           newjo.accumulate(attribute, "");
/*     */         } 
/*     */         
/* 183 */         if (arrayForm && newjo.length() > 0) {
/* 184 */           newja.put(newjo);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 189 */         if (token == XML.SLASH) {
/* 190 */           if (x.nextToken() != XML.GT) {
/* 191 */             throw x.syntaxError("Misshaped tag");
/*     */           }
/* 193 */           if (ja == null) {
/* 194 */             if (arrayForm) {
/* 195 */               return newja;
/*     */             }
/* 197 */             return newjo;
/*     */           } 
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 203 */         if (token != XML.GT) {
/* 204 */           throw x.syntaxError("Misshaped tag");
/*     */         }
/* 206 */         closeTag = (String)parse(x, arrayForm, newja, keepStrings);
/* 207 */         if (closeTag != null) {
/* 208 */           if (!closeTag.equals(tagName)) {
/* 209 */             throw x.syntaxError("Mismatched '" + tagName + "' and '" + closeTag + "'");
/*     */           }
/*     */           
/* 212 */           tagName = null;
/* 213 */           if (!arrayForm && newja.length() > 0) {
/* 214 */             newjo.put("childNodes", newja);
/*     */           }
/* 216 */           if (ja == null) {
/* 217 */             if (arrayForm) {
/* 218 */               return newja;
/*     */             }
/* 220 */             return newjo;
/*     */           } 
/*     */         } 
/*     */         
/*     */         continue;
/*     */       } 
/* 226 */       if (ja != null) {
/* 227 */         ja.put((token instanceof String) ? (keepStrings ? 
/* 228 */             XML.unescape((String)token) : XML.stringToValue((String)token)) : token);
/*     */       }
/*     */     } 
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
/*     */   public static JSONArray toJSONArray(String string) throws JSONException {
/* 249 */     return (JSONArray)parse(new XMLTokener(string), true, null, false);
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
/*     */   public static JSONArray toJSONArray(String string, boolean keepStrings) throws JSONException {
/* 271 */     return (JSONArray)parse(new XMLTokener(string), true, null, keepStrings);
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
/*     */   public static JSONArray toJSONArray(XMLTokener x, boolean keepStrings) throws JSONException {
/* 293 */     return (JSONArray)parse(x, true, null, keepStrings);
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
/*     */   public static JSONArray toJSONArray(XMLTokener x) throws JSONException {
/* 310 */     return (JSONArray)parse(x, true, null, false);
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
/*     */   public static JSONObject toJSONObject(String string) throws JSONException {
/* 328 */     return (JSONObject)parse(new XMLTokener(string), false, null, false);
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
/*     */   public static JSONObject toJSONObject(String string, boolean keepStrings) throws JSONException {
/* 348 */     return (JSONObject)parse(new XMLTokener(string), false, null, keepStrings);
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
/*     */   public static JSONObject toJSONObject(XMLTokener x) throws JSONException {
/* 366 */     return (JSONObject)parse(x, false, null, false);
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
/*     */   public static JSONObject toJSONObject(XMLTokener x, boolean keepStrings) throws JSONException {
/* 386 */     return (JSONObject)parse(x, false, null, keepStrings);
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
/*     */   public static String toString(JSONArray ja) throws JSONException {
/*     */     int i;
/* 401 */     StringBuilder sb = new StringBuilder();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 406 */     String tagName = ja.getString(0);
/* 407 */     XML.noSpace(tagName);
/* 408 */     tagName = XML.escape(tagName);
/* 409 */     sb.append('<');
/* 410 */     sb.append(tagName);
/*     */     
/* 412 */     Object object = ja.opt(1);
/* 413 */     if (object instanceof JSONObject) {
/* 414 */       i = 2;
/* 415 */       JSONObject jo = (JSONObject)object;
/*     */ 
/*     */ 
/*     */       
/* 419 */       for (Map.Entry<String, ?> entry : jo.entrySet()) {
/* 420 */         String key = entry.getKey();
/* 421 */         XML.noSpace(key);
/* 422 */         Object value = entry.getValue();
/* 423 */         if (value != null) {
/* 424 */           sb.append(' ');
/* 425 */           sb.append(XML.escape(key));
/* 426 */           sb.append('=');
/* 427 */           sb.append('"');
/* 428 */           sb.append(XML.escape(value.toString()));
/* 429 */           sb.append('"');
/*     */         } 
/*     */       } 
/*     */     } else {
/* 433 */       i = 1;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 438 */     int length = ja.length();
/* 439 */     if (i >= length)
/* 440 */     { sb.append('/');
/* 441 */       sb.append('>'); }
/*     */     else
/* 443 */     { sb.append('>');
/*     */       while (true)
/* 445 */       { object = ja.get(i);
/* 446 */         i++;
/* 447 */         if (object != null) {
/* 448 */           if (object instanceof String) {
/* 449 */             sb.append(XML.escape(object.toString()));
/* 450 */           } else if (object instanceof JSONObject) {
/* 451 */             sb.append(toString((JSONObject)object));
/* 452 */           } else if (object instanceof JSONArray) {
/* 453 */             sb.append(toString((JSONArray)object));
/*     */           } else {
/* 455 */             sb.append(object.toString());
/*     */           } 
/*     */         }
/* 458 */         if (i >= length)
/* 459 */         { sb.append('<');
/* 460 */           sb.append('/');
/* 461 */           sb.append(tagName);
/* 462 */           sb.append('>');
/*     */           
/* 464 */           return sb.toString(); }  }  }  return sb.toString();
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
/*     */   public static String toString(JSONObject jo) throws JSONException {
/* 477 */     StringBuilder sb = new StringBuilder();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 487 */     String tagName = jo.optString("tagName");
/* 488 */     if (tagName == null) {
/* 489 */       return XML.escape(jo.toString());
/*     */     }
/* 491 */     XML.noSpace(tagName);
/* 492 */     tagName = XML.escape(tagName);
/* 493 */     sb.append('<');
/* 494 */     sb.append(tagName);
/*     */ 
/*     */ 
/*     */     
/* 498 */     for (Map.Entry<String, ?> entry : jo.entrySet()) {
/* 499 */       String key = entry.getKey();
/* 500 */       if (!"tagName".equals(key) && !"childNodes".equals(key)) {
/* 501 */         XML.noSpace(key);
/* 502 */         Object value = entry.getValue();
/* 503 */         if (value != null) {
/* 504 */           sb.append(' ');
/* 505 */           sb.append(XML.escape(key));
/* 506 */           sb.append('=');
/* 507 */           sb.append('"');
/* 508 */           sb.append(XML.escape(value.toString()));
/* 509 */           sb.append('"');
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 516 */     JSONArray ja = jo.optJSONArray("childNodes");
/* 517 */     if (ja == null) {
/* 518 */       sb.append('/');
/* 519 */       sb.append('>');
/*     */     } else {
/* 521 */       sb.append('>');
/* 522 */       int length = ja.length();
/* 523 */       for (int i = 0; i < length; i++) {
/* 524 */         Object object = ja.get(i);
/* 525 */         if (object != null) {
/* 526 */           if (object instanceof String) {
/* 527 */             sb.append(XML.escape(object.toString()));
/* 528 */           } else if (object instanceof JSONObject) {
/* 529 */             sb.append(toString((JSONObject)object));
/* 530 */           } else if (object instanceof JSONArray) {
/* 531 */             sb.append(toString((JSONArray)object));
/*     */           } else {
/* 533 */             sb.append(object.toString());
/*     */           } 
/*     */         }
/*     */       } 
/* 537 */       sb.append('<');
/* 538 */       sb.append('/');
/* 539 */       sb.append(tagName);
/* 540 */       sb.append('>');
/*     */     } 
/* 542 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\org\json\JSONML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */