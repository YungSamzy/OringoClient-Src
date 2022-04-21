/*     */ package org.json;
/*     */ 
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JSONWriter
/*     */ {
/*     */   private static final int maxdepth = 200;
/*     */   private boolean comma;
/*     */   protected char mode;
/*     */   private final JSONObject[] stack;
/*     */   private int top;
/*     */   protected Appendable writer;
/*     */   
/*     */   public JSONWriter(Appendable w) {
/*  96 */     this.comma = false;
/*  97 */     this.mode = 'i';
/*  98 */     this.stack = new JSONObject[200];
/*  99 */     this.top = 0;
/* 100 */     this.writer = w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JSONWriter append(String string) throws JSONException {
/* 110 */     if (string == null) {
/* 111 */       throw new JSONException("Null pointer");
/*     */     }
/* 113 */     if (this.mode == 'o' || this.mode == 'a') {
/*     */       try {
/* 115 */         if (this.comma && this.mode == 'a') {
/* 116 */           this.writer.append(',');
/*     */         }
/* 118 */         this.writer.append(string);
/* 119 */       } catch (IOException e) {
/* 120 */         throw new JSONException(e);
/*     */       } 
/* 122 */       if (this.mode == 'o') {
/* 123 */         this.mode = 'k';
/*     */       }
/* 125 */       this.comma = true;
/* 126 */       return this;
/*     */     } 
/* 128 */     throw new JSONException("Value out of sequence.");
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
/*     */   public JSONWriter array() throws JSONException {
/* 141 */     if (this.mode == 'i' || this.mode == 'o' || this.mode == 'a') {
/* 142 */       push(null);
/* 143 */       append("[");
/* 144 */       this.comma = false;
/* 145 */       return this;
/*     */     } 
/* 147 */     throw new JSONException("Misplaced array.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JSONWriter end(char m, char c) throws JSONException {
/* 158 */     if (this.mode != m) {
/* 159 */       throw new JSONException((m == 'a') ? "Misplaced endArray." : "Misplaced endObject.");
/*     */     }
/*     */ 
/*     */     
/* 163 */     pop(m);
/*     */     try {
/* 165 */       this.writer.append(c);
/* 166 */     } catch (IOException e) {
/* 167 */       throw new JSONException(e);
/*     */     } 
/* 169 */     this.comma = true;
/* 170 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JSONWriter endArray() throws JSONException {
/* 180 */     return end('a', ']');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JSONWriter endObject() throws JSONException {
/* 190 */     return end('k', '}');
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
/*     */   public JSONWriter key(String string) throws JSONException {
/* 202 */     if (string == null) {
/* 203 */       throw new JSONException("Null key.");
/*     */     }
/* 205 */     if (this.mode == 'k') {
/*     */       try {
/* 207 */         this.stack[this.top - 1].putOnce(string, Boolean.TRUE);
/* 208 */         if (this.comma) {
/* 209 */           this.writer.append(',');
/*     */         }
/* 211 */         this.writer.append(JSONObject.quote(string));
/* 212 */         this.writer.append(':');
/* 213 */         this.comma = false;
/* 214 */         this.mode = 'o';
/* 215 */         return this;
/* 216 */       } catch (IOException e) {
/* 217 */         throw new JSONException(e);
/*     */       } 
/*     */     }
/* 220 */     throw new JSONException("Misplaced key.");
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
/*     */   public JSONWriter object() throws JSONException {
/* 234 */     if (this.mode == 'i') {
/* 235 */       this.mode = 'o';
/*     */     }
/* 237 */     if (this.mode == 'o' || this.mode == 'a') {
/* 238 */       append("{");
/* 239 */       push(new JSONObject());
/* 240 */       this.comma = false;
/* 241 */       return this;
/*     */     } 
/* 243 */     throw new JSONException("Misplaced object.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void pop(char c) throws JSONException {
/* 254 */     if (this.top <= 0) {
/* 255 */       throw new JSONException("Nesting error.");
/*     */     }
/* 257 */     char m = (this.stack[this.top - 1] == null) ? 'a' : 'k';
/* 258 */     if (m != c) {
/* 259 */       throw new JSONException("Nesting error.");
/*     */     }
/* 261 */     this.top--;
/* 262 */     this.mode = (this.top == 0) ? 'd' : ((this.stack[this.top - 1] == null) ? 'a' : 'k');
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
/*     */   private void push(JSONObject jo) throws JSONException {
/* 275 */     if (this.top >= 200) {
/* 276 */       throw new JSONException("Nesting too deep.");
/*     */     }
/* 278 */     this.stack[this.top] = jo;
/* 279 */     this.mode = (jo == null) ? 'a' : 'k';
/* 280 */     this.top++;
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
/*     */   public JSONWriter value(boolean b) throws JSONException {
/* 292 */     return append(b ? "true" : "false");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JSONWriter value(double d) throws JSONException {
/* 302 */     return value(new Double(d));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JSONWriter value(long l) throws JSONException {
/* 312 */     return append(Long.toString(l));
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
/*     */   public JSONWriter value(Object object) throws JSONException {
/* 324 */     return append(JSONObject.valueToString(object));
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\org\json\JSONWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */