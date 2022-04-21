/*     */ package org.scijava.nativelib;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MxSysInfo
/*     */ {
/*     */   public static String getMxSysInfo() {
/*  51 */     String mxSysInfo = System.getProperty("mx.sysinfo");
/*  52 */     if (mxSysInfo != null) {
/*  53 */       return mxSysInfo;
/*     */     }
/*     */     
/*  56 */     return guessMxSysInfo();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String guessMxSysInfo() {
/*  64 */     String arch = System.getProperty("os.arch");
/*  65 */     String os = System.getProperty("os.name");
/*  66 */     String extra = "unknown";
/*     */     
/*  68 */     if ("Linux".equals(os)) {
/*     */       try {
/*  70 */         String cxxver, libc_dest = (new File("/lib/libc.so.6")).getCanonicalPath();
/*  71 */         Matcher libc_m = Pattern.compile(".*/libc-(\\d+)\\.(\\d+)\\..*").matcher(libc_dest);
/*  72 */         if (!libc_m.matches()) throw new IOException("libc symlink contains unexpected destination: " + libc_dest);
/*     */ 
/*     */         
/*  75 */         File libstdcxx_file = new File("/usr/lib/libstdc++.so.6");
/*  76 */         if (!libstdcxx_file.exists()) libstdcxx_file = new File("/usr/lib/libstdc++.so.5");
/*     */         
/*  78 */         String libstdcxx_dest = libstdcxx_file.getCanonicalPath();
/*  79 */         Matcher libstdcxx_m = Pattern.compile(".*/libstdc\\+\\+\\.so\\.(\\d+)\\.0\\.(\\d+)").matcher(libstdcxx_dest);
/*     */         
/*  81 */         if (!libstdcxx_m.matches()) throw new IOException("libstdc++ symlink contains unexpected destination: " + libstdcxx_dest);
/*     */ 
/*     */         
/*  84 */         if ("5".equals(libstdcxx_m.group(1))) {
/*  85 */           cxxver = "5";
/*     */         }
/*  87 */         else if ("6".equals(libstdcxx_m.group(1))) {
/*  88 */           int minor_ver = Integer.parseInt(libstdcxx_m.group(2));
/*  89 */           if (minor_ver < 9) {
/*  90 */             cxxver = "6";
/*     */           } else {
/*     */             
/*  93 */             cxxver = "6" + libstdcxx_m.group(2);
/*     */           } 
/*     */         } else {
/*     */           
/*  97 */           cxxver = libstdcxx_m.group(1) + libstdcxx_m.group(2);
/*     */         } 
/*     */         
/* 100 */         extra = "c" + libc_m.group(1) + libc_m.group(2) + "cxx" + cxxver;
/*     */       }
/* 102 */       catch (IOException e) {
/* 103 */         extra = "unknown";
/*     */       } finally {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 110 */     return arch + "-" + os + "-" + extra;
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\org\scijava\nativelib\MxSysInfo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */