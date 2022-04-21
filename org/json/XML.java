/*     */ package org.json;
/*     */ 
/*     */ import java.util.Iterator;
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
/*     */ public class XML
/*     */ {
/*  40 */   public static final Character AMP = Character.valueOf('&');
/*     */ 
/*     */   
/*  43 */   public static final Character APOS = Character.valueOf('\'');
/*     */ 
/*     */   
/*  46 */   public static final Character BANG = Character.valueOf('!');
/*     */ 
/*     */   
/*  49 */   public static final Character EQ = Character.valueOf('=');
/*     */ 
/*     */   
/*  52 */   public static final Character GT = Character.valueOf('>');
/*     */ 
/*     */   
/*  55 */   public static final Character LT = Character.valueOf('<');
/*     */ 
/*     */   
/*  58 */   public static final Character QUEST = Character.valueOf('?');
/*     */ 
/*     */   
/*  61 */   public static final Character QUOT = Character.valueOf('"');
/*     */ 
/*     */   
/*  64 */   public static final Character SLASH = Character.valueOf('/');
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
/*     */   private static Iterable<Integer> codePointIterator(final String string) {
/*  78 */     return new Iterable<Integer>()
/*     */       {
/*     */         public Iterator<Integer> iterator() {
/*  81 */           return new Iterator<Integer>() {
/*  82 */               private int nextIndex = 0;
/*  83 */               private int length = string.length();
/*     */ 
/*     */               
/*     */               public boolean hasNext() {
/*  87 */                 return (this.nextIndex < this.length);
/*     */               }
/*     */ 
/*     */               
/*     */               public Integer next() {
/*  92 */                 int result = string.codePointAt(this.nextIndex);
/*  93 */                 this.nextIndex += Character.charCount(result);
/*  94 */                 return Integer.valueOf(result);
/*     */               }
/*     */ 
/*     */               
/*     */               public void remove() {
/*  99 */                 throw new UnsupportedOperationException();
/*     */               }
/*     */             };
/*     */         }
/*     */       };
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
/*     */   public static String escape(String string) {
/* 122 */     StringBuilder sb = new StringBuilder(string.length());
/* 123 */     for (Iterator<Integer> iterator = codePointIterator(string).iterator(); iterator.hasNext(); ) { int cp = ((Integer)iterator.next()).intValue();
/* 124 */       switch (cp) {
/*     */         case 38:
/* 126 */           sb.append("&amp;");
/*     */           continue;
/*     */         case 60:
/* 129 */           sb.append("&lt;");
/*     */           continue;
/*     */         case 62:
/* 132 */           sb.append("&gt;");
/*     */           continue;
/*     */         case 34:
/* 135 */           sb.append("&quot;");
/*     */           continue;
/*     */         case 39:
/* 138 */           sb.append("&apos;");
/*     */           continue;
/*     */       } 
/* 141 */       if (mustEscape(cp)) {
/* 142 */         sb.append("&#x");
/* 143 */         sb.append(Integer.toHexString(cp));
/* 144 */         sb.append(';'); continue;
/*     */       } 
/* 146 */       sb.appendCodePoint(cp); }
/*     */ 
/*     */ 
/*     */     
/* 150 */     return sb.toString();
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
/*     */   private static boolean mustEscape(int cp) {
/* 166 */     return ((Character.isISOControl(cp) && cp != 9 && cp != 10 && cp != 13) || ((cp < 32 || cp > 55295) && (cp < 57344 || cp > 65533) && (cp < 65536 || cp > 1114111)));
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
/*     */   public static String unescape(String string) {
/* 187 */     StringBuilder sb = new StringBuilder(string.length());
/* 188 */     for (int i = 0, length = string.length(); i < length; i++) {
/* 189 */       char c = string.charAt(i);
/* 190 */       if (c == '&') {
/* 191 */         int semic = string.indexOf(';', i);
/* 192 */         if (semic > i) {
/* 193 */           String entity = string.substring(i + 1, semic);
/* 194 */           sb.append(XMLTokener.unescapeEntity(entity));
/*     */           
/* 196 */           i += entity.length() + 1;
/*     */         }
/*     */         else {
/*     */           
/* 200 */           sb.append(c);
/*     */         } 
/*     */       } else {
/*     */         
/* 204 */         sb.append(c);
/*     */       } 
/*     */     } 
/* 207 */     return sb.toString();
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
/*     */   public static void noSpace(String string) throws JSONException {
/* 219 */     int length = string.length();
/* 220 */     if (length == 0) {
/* 221 */       throw new JSONException("Empty string.");
/*     */     }
/* 223 */     for (int i = 0; i < length; i++) {
/* 224 */       if (Character.isWhitespace(string.charAt(i))) {
/* 225 */         throw new JSONException("'" + string + "' contains a space character.");
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
/*     */   
/*     */   private static boolean parse(XMLTokener x, JSONObject context, String name, boolean keepStrings) throws JSONException {
/* 247 */     JSONObject jsonobject = null;
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
/* 262 */     Object token = x.nextToken();
/*     */ 
/*     */ 
/*     */     
/* 266 */     if (token == BANG) {
/* 267 */       char c = x.next();
/* 268 */       if (c == '-') {
/* 269 */         if (x.next() == '-') {
/* 270 */           x.skipPast("-->");
/* 271 */           return false;
/*     */         } 
/* 273 */         x.back();
/* 274 */       } else if (c == '[') {
/* 275 */         token = x.nextToken();
/* 276 */         if ("CDATA".equals(token) && 
/* 277 */           x.next() == '[') {
/* 278 */           String string = x.nextCDATA();
/* 279 */           if (string.length() > 0) {
/* 280 */             context.accumulate("content", string);
/*     */           }
/* 282 */           return false;
/*     */         } 
/*     */         
/* 285 */         throw x.syntaxError("Expected 'CDATA['");
/*     */       } 
/* 287 */       int i = 1;
/*     */       while (true)
/* 289 */       { token = x.nextMeta();
/* 290 */         if (token == null)
/* 291 */           throw x.syntaxError("Missing '>' after '<!'."); 
/* 292 */         if (token == LT) {
/* 293 */           i++;
/* 294 */         } else if (token == GT) {
/* 295 */           i--;
/*     */         } 
/* 297 */         if (i <= 0)
/* 298 */           return false;  } 
/* 299 */     }  if (token == QUEST) {
/*     */ 
/*     */       
/* 302 */       x.skipPast("?>");
/* 303 */       return false;
/* 304 */     }  if (token == SLASH) {
/*     */ 
/*     */ 
/*     */       
/* 308 */       token = x.nextToken();
/* 309 */       if (name == null) {
/* 310 */         throw x.syntaxError("Mismatched close tag " + token);
/*     */       }
/* 312 */       if (!token.equals(name)) {
/* 313 */         throw x.syntaxError("Mismatched " + name + " and " + token);
/*     */       }
/* 315 */       if (x.nextToken() != GT) {
/* 316 */         throw x.syntaxError("Misshaped close tag");
/*     */       }
/* 318 */       return true;
/*     */     } 
/* 320 */     if (token instanceof Character) {
/* 321 */       throw x.syntaxError("Misshaped tag");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 326 */     String tagName = (String)token;
/* 327 */     token = null;
/* 328 */     jsonobject = new JSONObject();
/*     */     while (true) {
/* 330 */       if (token == null) {
/* 331 */         token = x.nextToken();
/*     */       }
/*     */       
/* 334 */       if (token instanceof String) {
/* 335 */         String string = (String)token;
/* 336 */         token = x.nextToken();
/* 337 */         if (token == EQ) {
/* 338 */           token = x.nextToken();
/* 339 */           if (!(token instanceof String)) {
/* 340 */             throw x.syntaxError("Missing value");
/*     */           }
/* 342 */           jsonobject.accumulate(string, keepStrings ? token : 
/* 343 */               stringToValue((String)token));
/* 344 */           token = null; continue;
/*     */         } 
/* 346 */         jsonobject.accumulate(string, ""); continue;
/*     */       } 
/*     */       break;
/*     */     } 
/* 350 */     if (token == SLASH) {
/*     */       
/* 352 */       if (x.nextToken() != GT) {
/* 353 */         throw x.syntaxError("Misshaped tag");
/*     */       }
/* 355 */       if (jsonobject.length() > 0) {
/* 356 */         context.accumulate(tagName, jsonobject);
/*     */       } else {
/* 358 */         context.accumulate(tagName, "");
/*     */       } 
/* 360 */       return false;
/*     */     } 
/* 362 */     if (token == GT) {
/*     */       while (true) {
/*     */         
/* 365 */         token = x.nextContent();
/* 366 */         if (token == null) {
/* 367 */           if (tagName != null) {
/* 368 */             throw x.syntaxError("Unclosed tag " + tagName);
/*     */           }
/* 370 */           return false;
/* 371 */         }  if (token instanceof String) {
/* 372 */           String string = (String)token;
/* 373 */           if (string.length() > 0)
/* 374 */             jsonobject.accumulate("content", keepStrings ? string : 
/* 375 */                 stringToValue(string)); 
/*     */           continue;
/*     */         } 
/* 378 */         if (token == LT)
/*     */         {
/* 380 */           if (parse(x, jsonobject, tagName, keepStrings)) {
/* 381 */             if (jsonobject.length() == 0) {
/* 382 */               context.accumulate(tagName, "");
/* 383 */             } else if (jsonobject.length() == 1 && jsonobject
/* 384 */               .opt("content") != null) {
/* 385 */               context.accumulate(tagName, jsonobject
/* 386 */                   .opt("content"));
/*     */             } else {
/* 388 */               context.accumulate(tagName, jsonobject);
/*     */             } 
/* 390 */             return false;
/*     */           } 
/*     */         }
/*     */       } 
/*     */     }
/* 395 */     throw x.syntaxError("Misshaped tag");
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
/*     */   public static Object stringToValue(String string) {
/* 409 */     return JSONObject.stringToValue(string);
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
/*     */   public static JSONObject toJSONObject(String string) throws JSONException {
/* 429 */     return toJSONObject(string, false);
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
/*     */   
/*     */   public static JSONObject toJSONObject(String string, boolean keepStrings) throws JSONException {
/* 455 */     JSONObject jo = new JSONObject();
/* 456 */     XMLTokener x = new XMLTokener(string);
/* 457 */     while (x.more() && x.skipPast("<")) {
/* 458 */       parse(x, jo, null, keepStrings);
/*     */     }
/* 460 */     return jo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String toString(Object object) throws JSONException {
/* 471 */     return toString(object, null);
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
/*     */   public static String toString(Object object, String tagName) throws JSONException {
/* 486 */     StringBuilder sb = new StringBuilder();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 491 */     if (object instanceof JSONObject) {
/*     */ 
/*     */       
/* 494 */       if (tagName != null) {
/* 495 */         sb.append('<');
/* 496 */         sb.append(tagName);
/* 497 */         sb.append('>');
/*     */       } 
/*     */ 
/*     */       
/* 501 */       JSONObject jo = (JSONObject)object;
/* 502 */       for (Map.Entry<String, ?> entry : jo.entrySet()) {
/* 503 */         String key = entry.getKey();
/* 504 */         Object value = entry.getValue();
/* 505 */         if (value == null) {
/* 506 */           value = "";
/* 507 */         } else if (value.getClass().isArray()) {
/* 508 */           value = new JSONArray(value);
/*     */         } 
/*     */ 
/*     */         
/* 512 */         if ("content".equals(key)) {
/* 513 */           if (value instanceof JSONArray) {
/* 514 */             JSONArray ja = (JSONArray)value;
/* 515 */             int i = 0;
/* 516 */             for (Object val : ja) {
/* 517 */               if (i > 0) {
/* 518 */                 sb.append('\n');
/*     */               }
/* 520 */               sb.append(escape(val.toString()));
/* 521 */               i++;
/*     */             }  continue;
/*     */           } 
/* 524 */           sb.append(escape(value.toString()));
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 529 */         if (value instanceof JSONArray) {
/* 530 */           JSONArray ja = (JSONArray)value;
/* 531 */           for (Object val : ja) {
/* 532 */             if (val instanceof JSONArray) {
/* 533 */               sb.append('<');
/* 534 */               sb.append(key);
/* 535 */               sb.append('>');
/* 536 */               sb.append(toString(val));
/* 537 */               sb.append("</");
/* 538 */               sb.append(key);
/* 539 */               sb.append('>'); continue;
/*     */             } 
/* 541 */             sb.append(toString(val, key));
/*     */           }  continue;
/*     */         } 
/* 544 */         if ("".equals(value)) {
/* 545 */           sb.append('<');
/* 546 */           sb.append(key);
/* 547 */           sb.append("/>");
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 552 */         sb.append(toString(value, key));
/*     */       } 
/*     */       
/* 555 */       if (tagName != null) {
/*     */ 
/*     */         
/* 558 */         sb.append("</");
/* 559 */         sb.append(tagName);
/* 560 */         sb.append('>');
/*     */       } 
/* 562 */       return sb.toString();
/*     */     } 
/*     */ 
/*     */     
/* 566 */     if (object != null && (object instanceof JSONArray || object.getClass().isArray())) {
/* 567 */       JSONArray ja; if (object.getClass().isArray()) {
/* 568 */         ja = new JSONArray(object);
/*     */       } else {
/* 570 */         ja = (JSONArray)object;
/*     */       } 
/* 572 */       for (Object val : ja)
/*     */       {
/*     */ 
/*     */         
/* 576 */         sb.append(toString(val, (tagName == null) ? "array" : tagName));
/*     */       }
/* 578 */       return sb.toString();
/*     */     } 
/*     */     
/* 581 */     String string = (object == null) ? "null" : escape(object.toString());
/* 582 */     return (tagName == null) ? ("\"" + string + "\"") : (
/* 583 */       (string.length() == 0) ? ("<" + tagName + "/>") : ("<" + tagName + ">" + string + "</" + tagName + ">"));
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\org\json\XML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */