/*    */ package org.scijava.nativelib;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefaultJniExtractor
/*    */   extends BaseJniExtractor
/*    */ {
/*    */   private File nativeDir;
/*    */   
/*    */   public DefaultJniExtractor() throws IOException {
/* 62 */     super(null);
/* 63 */     init("tmplib");
/*    */   }
/*    */   
/*    */   public DefaultJniExtractor(Class libraryJarClass, String tmplib) throws IOException {
/* 67 */     super(libraryJarClass);
/* 68 */     init(tmplib);
/*    */   }
/*    */   
/*    */   void init(String tmplib) throws IOException {
/* 72 */     this.nativeDir = new File(System.getProperty("java.library.tmpdir", tmplib));
/*    */     
/* 74 */     this.nativeDir.mkdirs();
/* 75 */     if (!this.nativeDir.isDirectory()) {
/* 76 */       throw new IOException("Unable to create native library working directory " + this.nativeDir);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public File getJniDir() {
/* 82 */     return this.nativeDir;
/*    */   }
/*    */ 
/*    */   
/*    */   public File getNativeDir() {
/* 87 */     return this.nativeDir;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\org\scijava\nativelib\DefaultJniExtractor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */