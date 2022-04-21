/*     */ package org.json;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URLDecoder;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JSONPointer
/*     */ {
/*     */   private static final String ENCODING = "utf-8";
/*     */   private final List<String> refTokens;
/*     */   
/*     */   public static class Builder
/*     */   {
/*  64 */     private final List<String> refTokens = new ArrayList<String>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public JSONPointer build() {
/*  71 */       return new JSONPointer(this.refTokens);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder append(String token) {
/*  87 */       if (token == null) {
/*  88 */         throw new NullPointerException("token cannot be null");
/*     */       }
/*  90 */       this.refTokens.add(token);
/*  91 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder append(int arrayIndex) {
/* 102 */       this.refTokens.add(String.valueOf(arrayIndex));
/* 103 */       return this;
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
/*     */   public static Builder builder() {
/* 123 */     return new Builder();
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
/*     */   public JSONPointer(String pointer) {
/*     */     String refs;
/* 138 */     if (pointer == null) {
/* 139 */       throw new NullPointerException("pointer cannot be null");
/*     */     }
/* 141 */     if (pointer.isEmpty() || pointer.equals("#")) {
/* 142 */       this.refTokens = Collections.emptyList();
/*     */       
/*     */       return;
/*     */     } 
/* 146 */     if (pointer.startsWith("#/")) {
/* 147 */       refs = pointer.substring(2);
/*     */       try {
/* 149 */         refs = URLDecoder.decode(refs, "utf-8");
/* 150 */       } catch (UnsupportedEncodingException e) {
/* 151 */         throw new RuntimeException(e);
/*     */       } 
/* 153 */     } else if (pointer.startsWith("/")) {
/* 154 */       refs = pointer.substring(1);
/*     */     } else {
/* 156 */       throw new IllegalArgumentException("a JSON pointer should start with '/' or '#/'");
/*     */     } 
/* 158 */     this.refTokens = new ArrayList<String>();
/* 159 */     for (String token : refs.split("/")) {
/* 160 */       this.refTokens.add(unescape(token));
/*     */     }
/*     */   }
/*     */   
/*     */   public JSONPointer(List<String> refTokens) {
/* 165 */     this.refTokens = new ArrayList<String>(refTokens);
/*     */   }
/*     */   
/*     */   private String unescape(String token) {
/* 169 */     return token.replace("~1", "/").replace("~0", "~")
/* 170 */       .replace("\\\"", "\"")
/* 171 */       .replace("\\\\", "\\");
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
/*     */   public Object queryFrom(Object document) {
/* 185 */     if (this.refTokens.isEmpty()) {
/* 186 */       return document;
/*     */     }
/* 188 */     Object current = document;
/* 189 */     for (String token : this.refTokens) {
/* 190 */       if (current instanceof JSONObject) {
/* 191 */         current = ((JSONObject)current).opt(unescape(token)); continue;
/* 192 */       }  if (current instanceof JSONArray) {
/* 193 */         current = readByIndexToken(current, token); continue;
/*     */       } 
/* 195 */       throw new JSONPointerException(String.format("value [%s] is not an array or object therefore its key %s cannot be resolved", new Object[] { current, token }));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 200 */     return current;
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
/*     */   private Object readByIndexToken(Object current, String indexToken) {
/*     */     try {
/* 213 */       int index = Integer.parseInt(indexToken);
/* 214 */       JSONArray currentArr = (JSONArray)current;
/* 215 */       if (index >= currentArr.length()) {
/* 216 */         throw new JSONPointerException(String.format("index %d is out of bounds - the array has %d elements", new Object[] { Integer.valueOf(index), 
/* 217 */                 Integer.valueOf(currentArr.length()) }));
/*     */       }
/* 219 */       return currentArr.get(index);
/* 220 */     } catch (NumberFormatException e) {
/* 221 */       throw new JSONPointerException(String.format("%s is not an array index", new Object[] { indexToken }), e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 231 */     StringBuilder rval = new StringBuilder("");
/* 232 */     for (String token : this.refTokens) {
/* 233 */       rval.append('/').append(escape(token));
/*     */     }
/* 235 */     return rval.toString();
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
/*     */   private String escape(String token) {
/* 247 */     return token.replace("~", "~0")
/* 248 */       .replace("/", "~1")
/* 249 */       .replace("\\", "\\\\")
/* 250 */       .replace("\"", "\\\"");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toURIFragment() {
/*     */     try {
/* 259 */       StringBuilder rval = new StringBuilder("#");
/* 260 */       for (String token : this.refTokens) {
/* 261 */         rval.append('/').append(URLEncoder.encode(token, "utf-8"));
/*     */       }
/* 263 */       return rval.toString();
/* 264 */     } catch (UnsupportedEncodingException e) {
/* 265 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\org\json\JSONPointer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */