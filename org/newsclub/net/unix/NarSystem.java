/*     */ package org.newsclub.net.unix;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.net.URL;
/*     */ import org.scijava.nativelib.DefaultJniExtractor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class NarSystem
/*     */ {
/*     */   public static void loadLibrary() {
/*  28 */     String fileName = "junixsocket-native-2.0.4";
/*  29 */     String mapped = System.mapLibraryName("junixsocket-native-2.0.4");
/*  30 */     String[] aols = getAOLs();
/*  31 */     ClassLoader loader = NarSystem.class.getClassLoader();
/*  32 */     File unpacked = getUnpackedLibPath(loader, aols, "junixsocket-native-2.0.4", mapped);
/*  33 */     if (unpacked != null)
/*  34 */     { System.load(unpacked.getPath()); }
/*     */     else { try {
/*  36 */         String libPath = getLibPath(loader, aols, mapped);
/*  37 */         DefaultJniExtractor defaultJniExtractor = new DefaultJniExtractor(NarSystem.class, System.getProperty("java.io.tmpdir"));
/*  38 */         File extracted = defaultJniExtractor.extractJni(libPath, "junixsocket-native-2.0.4");
/*  39 */         System.load(extracted.getPath());
/*  40 */       } catch (Exception e) {
/*  41 */         e.printStackTrace();
/*  42 */         throw (e instanceof RuntimeException) ? (RuntimeException)e : new RuntimeException(e);
/*     */       }  }
/*     */   
/*     */   }
/*     */   
/*     */   public static int runUnitTests() {
/*  48 */     return (new NarSystem()).runUnitTestsNative();
/*     */   }
/*     */   
/*     */   public native int runUnitTestsNative();
/*     */   
/*     */   private static String[] getAOLs() {
/*  54 */     String ao = System.getProperty("os.arch") + "-" + System.getProperty("os.name").replaceAll(" ", "");
/*     */ 
/*     */     
/*  57 */     if (ao.startsWith("i386-Linux")) {
/*  58 */       return new String[] { "i386-Linux-ecpc", "i386-Linux-gpp", "i386-Linux-icc", "i386-Linux-ecc", "i386-Linux-icpc", "i386-Linux-linker", "i386-Linux-gcc" };
/*     */     }
/*     */     
/*  61 */     if (ao.startsWith("x86-Windows")) {
/*  62 */       return new String[] { "x86-Windows-linker", "x86-Windows-gpp", "x86-Windows-msvc", "x86-Windows-icl", "x86-Windows-gcc" };
/*     */     }
/*     */     
/*  65 */     if (ao.startsWith("amd64-Linux")) {
/*  66 */       return new String[] { "amd64-Linux-gpp", "amd64-Linux-icpc", "amd64-Linux-gcc", "amd64-Linux-linker" };
/*     */     }
/*     */     
/*  69 */     if (ao.startsWith("amd64-Windows")) {
/*  70 */       return new String[] { "amd64-Windows-gpp", "amd64-Windows-msvc", "amd64-Windows-icl", "amd64-Windows-linker", "amd64-Windows-gcc" };
/*     */     }
/*     */     
/*  73 */     if (ao.startsWith("amd64-FreeBSD")) {
/*  74 */       return new String[] { "amd64-FreeBSD-gpp", "amd64-FreeBSD-gcc", "amd64-FreeBSD-linker" };
/*     */     }
/*     */     
/*  77 */     if (ao.startsWith("ppc-MacOSX")) {
/*  78 */       return new String[] { "ppc-MacOSX-gpp", "ppc-MacOSX-linker", "ppc-MacOSX-gcc" };
/*     */     }
/*     */     
/*  81 */     if (ao.startsWith("x86_64-MacOSX")) {
/*  82 */       return new String[] { "x86_64-MacOSX-icc", "x86_64-MacOSX-icpc", "x86_64-MacOSX-gpp", "x86_64-MacOSX-linker", "x86_64-MacOSX-gcc" };
/*     */     }
/*     */     
/*  85 */     if (ao.startsWith("ppc-AIX")) {
/*  86 */       return new String[] { "ppc-AIX-gpp", "ppc-AIX-xlC", "ppc-AIX-gcc", "ppc-AIX-linker" };
/*     */     }
/*     */     
/*  89 */     if (ao.startsWith("i386-FreeBSD")) {
/*  90 */       return new String[] { "i386-FreeBSD-gpp", "i386-FreeBSD-gcc", "i386-FreeBSD-linker" };
/*     */     }
/*     */     
/*  93 */     if (ao.startsWith("sparc-SunOS")) {
/*  94 */       return new String[] { "sparc-SunOS-cc", "sparc-SunOS-CC", "sparc-SunOS-linker" };
/*     */     }
/*     */     
/*  97 */     if (ao.startsWith("arm-Linux")) {
/*  98 */       return new String[] { "arm-Linux-gpp", "arm-Linux-linker", "arm-Linux-gcc" };
/*     */     }
/*     */     
/* 101 */     if (ao.startsWith("x86-SunOS")) {
/* 102 */       return new String[] { "x86-SunOS-g++", "x86-SunOS-linker" };
/*     */     }
/*     */     
/* 105 */     if (ao.startsWith("i386-MacOSX")) {
/* 106 */       return new String[] { "i386-MacOSX-gpp", "i386-MacOSX-gcc", "i386-MacOSX-linker" };
/*     */     }
/*     */ 
/*     */     
/* 110 */     throw new RuntimeException("Unhandled architecture/OS: " + ao);
/*     */   }
/*     */ 
/*     */   
/*     */   private static File getUnpackedLibPath(ClassLoader loader, String[] aols, String fileName, String mapped) {
/* 115 */     String classPath = NarSystem.class.getName().replace('.', '/') + ".class";
/* 116 */     URL url = loader.getResource(classPath);
/* 117 */     if (url == null || !"file".equals(url.getProtocol())) return null; 
/* 118 */     String path = url.getPath();
/* 119 */     String prefix = path.substring(0, path.length() - classPath.length()) + "../nar/" + fileName + "-";
/* 120 */     for (String aol : aols) {
/* 121 */       File file = new File(prefix + aol + "-jni/lib/" + aol + "/jni/" + mapped);
/* 122 */       if (file.isFile()) return file; 
/*     */     } 
/* 124 */     return null;
/*     */   }
/*     */   
/*     */   private static String getLibPath(ClassLoader loader, String[] aols, String mapped) {
/* 128 */     for (String aol : aols) {
/* 129 */       String libPath = "lib/" + aol + "/jni/";
/* 130 */       if (loader.getResource(libPath + mapped) != null) return libPath; 
/*     */     } 
/* 132 */     throw new RuntimeException("Library '" + mapped + "' not found!");
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\org\newsclub\ne\\unix\NarSystem.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */