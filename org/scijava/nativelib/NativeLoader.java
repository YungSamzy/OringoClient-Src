/*     */ package org.scijava.nativelib;
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
/*     */ public class NativeLoader
/*     */ {
/*  75 */   private static JniExtractor jniExtractor = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*     */     try {
/*  91 */       if (NativeLoader.class.getClassLoader() == ClassLoader.getSystemClassLoader()) {
/*  92 */         jniExtractor = new DefaultJniExtractor();
/*     */       } else {
/*  94 */         jniExtractor = new WebappJniExtractor("Classloader");
/*     */       }
/*     */     
/*  97 */     } catch (IOException e) {
/*  98 */       throw new ExceptionInInitializerError(e);
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
/*     */   public static void loadLibrary(String libname) throws IOException {
/* 114 */     System.load(jniExtractor.extractJni("", libname).getAbsolutePath());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void extractRegistered() throws IOException {
/* 125 */     jniExtractor.extractRegistered();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JniExtractor getJniExtractor() {
/* 132 */     return jniExtractor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setJniExtractor(JniExtractor jniExtractor) {
/* 139 */     NativeLoader.jniExtractor = jniExtractor;
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\org\scijava\nativelib\NativeLoader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */