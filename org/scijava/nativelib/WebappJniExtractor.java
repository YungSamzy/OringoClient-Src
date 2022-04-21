/*     */ package org.scijava.nativelib;
/*     */ 
/*     */ import java.io.File;
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
/*     */ public class WebappJniExtractor
/*     */   extends BaseJniExtractor
/*     */ {
/*     */   private File nativeDir;
/*     */   private File jniSubDir;
/*     */   
/*     */   public WebappJniExtractor(String classloaderName) throws IOException {
/*     */     File trialJniSubDir;
/*  66 */     this.nativeDir = new File(System.getProperty("java.library.tmpdir", "tmplib"));
/*     */     
/*  68 */     this.nativeDir.mkdirs();
/*  69 */     if (!this.nativeDir.isDirectory()) {
/*  70 */       throw new IOException("Unable to create native library working directory " + this.nativeDir);
/*     */     }
/*     */     
/*  73 */     long now = System.currentTimeMillis();
/*     */     
/*  75 */     int attempt = 0;
/*     */     while (true) {
/*  77 */       trialJniSubDir = new File(this.nativeDir, classloaderName + "." + now + "." + attempt);
/*  78 */       if (trialJniSubDir.mkdir())
/*     */         break; 
/*  80 */       if (trialJniSubDir.exists()) {
/*  81 */         attempt++;
/*     */         continue;
/*     */       } 
/*  84 */       throw new IOException("Unable to create native library working directory " + trialJniSubDir);
/*     */     } 
/*  86 */     this.jniSubDir = trialJniSubDir;
/*  87 */     this.jniSubDir.deleteOnExit();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void finalize() throws Throwable {
/*  92 */     File[] files = this.jniSubDir.listFiles();
/*  93 */     for (int i = 0; i < files.length; i++) {
/*  94 */       files[i].delete();
/*     */     }
/*  96 */     this.jniSubDir.delete();
/*     */   }
/*     */ 
/*     */   
/*     */   public File getJniDir() {
/* 101 */     return this.jniSubDir;
/*     */   }
/*     */ 
/*     */   
/*     */   public File getNativeDir() {
/* 106 */     return this.nativeDir;
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\org\scijava\nativelib\WebappJniExtractor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */