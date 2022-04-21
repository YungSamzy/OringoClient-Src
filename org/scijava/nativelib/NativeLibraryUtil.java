/*     */ package org.scijava.nativelib;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NativeLibraryUtil
/*     */ {
/*     */   public enum Architecture
/*     */   {
/*  76 */     UNKNOWN, LINUX_32, LINUX_64, WINDOWS_32, WINDOWS_64, OSX_32, OSX_64, OSX_PPC; }
/*     */   
/*  78 */   private enum Processor { UNKNOWN, INTEL_32, INTEL_64, PPC; }
/*  79 */   private static Architecture architecture = Architecture.UNKNOWN;
/*     */   private static final String DELIM = "/";
/*     */   private static final String JAVA_TMPDIR = "java.io.tmpdir";
/*  82 */   private static final Logger LOGGER = Logger.getLogger("org.scijava.nativelib.NativeLibraryUtil");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Architecture getArchitecture() {
/*  90 */     if (Architecture.UNKNOWN == architecture) {
/*  91 */       Processor processor = getProcessor();
/*  92 */       if (Processor.UNKNOWN != processor) {
/*  93 */         String name = System.getProperty("os.name").toLowerCase();
/*  94 */         if (name.indexOf("nix") >= 0 || name.indexOf("nux") >= 0) {
/*  95 */           if (Processor.INTEL_32 == processor) {
/*  96 */             architecture = Architecture.LINUX_32;
/*     */           }
/*  98 */           else if (Processor.INTEL_64 == processor) {
/*  99 */             architecture = Architecture.LINUX_64;
/*     */           }
/*     */         
/* 102 */         } else if (name.indexOf("win") >= 0) {
/* 103 */           if (Processor.INTEL_32 == processor) {
/* 104 */             architecture = Architecture.WINDOWS_32;
/*     */           }
/* 106 */           else if (Processor.INTEL_64 == processor) {
/* 107 */             architecture = Architecture.WINDOWS_64;
/*     */           }
/*     */         
/* 110 */         } else if (name.indexOf("mac") >= 0) {
/* 111 */           if (Processor.INTEL_32 == processor) {
/* 112 */             architecture = Architecture.OSX_32;
/*     */           }
/* 114 */           else if (Processor.INTEL_64 == processor) {
/* 115 */             architecture = Architecture.OSX_64;
/*     */           }
/* 117 */           else if (Processor.PPC == processor) {
/* 118 */             architecture = Architecture.OSX_PPC;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 123 */     LOGGER.log(Level.FINE, "architecture is " + architecture + " os.name is " + System.getProperty("os.name").toLowerCase());
/* 124 */     return architecture;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Processor getProcessor() {
/* 133 */     Processor processor = Processor.UNKNOWN;
/*     */ 
/*     */ 
/*     */     
/* 137 */     String arch = System.getProperty("os.arch").toLowerCase();
/*     */     
/* 139 */     if (arch.indexOf("ppc") >= 0) {
/* 140 */       processor = Processor.PPC;
/*     */     }
/* 142 */     else if (arch.indexOf("86") >= 0 || arch.indexOf("amd") >= 0) {
/* 143 */       int bits = 32;
/* 144 */       if (arch.indexOf("64") >= 0) {
/* 145 */         bits = 64;
/*     */       }
/* 147 */       processor = (32 == bits) ? Processor.INTEL_32 : Processor.INTEL_64;
/*     */     } 
/* 149 */     LOGGER.log(Level.FINE, "processor is " + processor + " os.arch is " + System.getProperty("os.arch").toLowerCase());
/* 150 */     return processor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getPlatformLibraryPath() {
/* 159 */     String path = "META-INF/lib/";
/* 160 */     path = path + getArchitecture().name().toLowerCase() + "/";
/* 161 */     LOGGER.log(Level.FINE, "platform specific path is " + path);
/* 162 */     return path;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getPlatformLibraryName(String libName) {
/* 172 */     String name = null;
/* 173 */     switch (getArchitecture()) {
/*     */       case LINUX_32:
/*     */       case LINUX_64:
/* 176 */         name = libName + ".so";
/*     */         break;
/*     */       case WINDOWS_32:
/*     */       case WINDOWS_64:
/* 180 */         name = libName + ".dll";
/*     */         break;
/*     */       case OSX_32:
/*     */       case OSX_64:
/* 184 */         name = "lib" + libName + ".dylib";
/*     */         break;
/*     */     } 
/* 187 */     LOGGER.log(Level.FINE, "native library name " + name);
/* 188 */     return name;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getVersionedLibraryName(Class libraryJarClass, String libName) {
/* 221 */     String version = libraryJarClass.getPackage().getImplementationVersion();
/* 222 */     if (null != version && version.length() > 0) {
/* 223 */       libName = libName + "-" + version;
/*     */     }
/* 225 */     return libName;
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
/*     */   public static boolean loadVersionedNativeLibrary(Class libraryJarClass, String libName) {
/* 238 */     libName = getVersionedLibraryName(libraryJarClass, libName);
/*     */     
/* 240 */     return loadNativeLibrary(libraryJarClass, libName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean loadNativeLibrary(Class libraryJarClass, String libName) {
/* 251 */     boolean success = false;
/*     */     
/* 253 */     if (Architecture.UNKNOWN == getArchitecture()) {
/* 254 */       LOGGER.log(Level.WARNING, "No native library available for this platform.");
/*     */     } else {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/* 260 */         String tmpDirectory = System.getProperty("java.io.tmpdir");
/* 261 */         JniExtractor jniExtractor = new DefaultJniExtractor(libraryJarClass, tmpDirectory);
/*     */ 
/*     */ 
/*     */         
/* 265 */         File extractedFile = jniExtractor.extractJni(getPlatformLibraryPath(), libName);
/*     */ 
/*     */ 
/*     */         
/* 269 */         System.load(extractedFile.getPath());
/*     */         
/* 271 */         success = true;
/*     */       }
/* 273 */       catch (IOException e) {
/*     */         
/* 275 */         LOGGER.log(Level.WARNING, "IOException creating DefaultJniExtractor", e);
/*     */       }
/* 277 */       catch (SecurityException e) {
/*     */         
/* 279 */         LOGGER.log(Level.WARNING, "Can't load dynamic library", e);
/*     */       }
/* 281 */       catch (UnsatisfiedLinkError e) {
/*     */         
/* 283 */         LOGGER.log(Level.WARNING, "Problem with library", e);
/*     */       } 
/*     */     } 
/* 286 */     return success;
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\org\scijava\nativelib\NativeLibraryUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */