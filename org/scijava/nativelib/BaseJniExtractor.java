/*     */ package org.scijava.nativelib;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.FilenameFilter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.net.URL;
/*     */ import java.util.Enumeration;
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
/*     */ public abstract class BaseJniExtractor
/*     */   implements JniExtractor
/*     */ {
/*  63 */   private static final Logger LOGGER = Logger.getLogger("org.scijava.nativelib.BaseJniExtractor");
/*     */ 
/*     */   
/*     */   private static final String JAVA_TMPDIR = "java.io.tmpdir";
/*     */ 
/*     */   
/*     */   private Class libraryJarClass;
/*     */ 
/*     */   
/*     */   private String[] nativeResourcePaths;
/*     */ 
/*     */   
/*     */   public BaseJniExtractor() throws IOException {
/*  76 */     init(null);
/*     */   }
/*     */   
/*     */   public BaseJniExtractor(Class libraryJarClass) throws IOException {
/*  80 */     init(libraryJarClass);
/*     */   }
/*     */   
/*     */   private void init(Class libraryJarClass) throws IOException {
/*  84 */     this.libraryJarClass = libraryJarClass;
/*     */     
/*  86 */     String mxSysInfo = MxSysInfo.getMxSysInfo();
/*     */     
/*  88 */     if (mxSysInfo != null) {
/*  89 */       this.nativeResourcePaths = new String[] { "META-INF/lib/" + mxSysInfo + "/", "META-INF/lib/" };
/*     */     }
/*     */     else {
/*     */       
/*  93 */       this.nativeResourcePaths = new String[] { "META-INF/lib/" };
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract File getNativeDir();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract File getJniDir();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public File extractJni(String libPath, String libname) throws IOException {
/* 113 */     String mappedlibName = System.mapLibraryName(libname);
/* 114 */     LOGGER.log(Level.FINE, "mappedLib is " + mappedlibName);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 119 */     URL lib = null;
/*     */ 
/*     */     
/* 122 */     if (null == this.libraryJarClass) {
/* 123 */       this.libraryJarClass = getClass();
/*     */     }
/*     */     
/* 126 */     lib = this.libraryJarClass.getClassLoader().getResource(libPath + mappedlibName);
/* 127 */     if (null == lib && 
/* 128 */       mappedlibName.endsWith(".jnilib")) {
/* 129 */       lib = getClass().getClassLoader().getResource(libPath + mappedlibName.substring(0, mappedlibName.length() - 7) + ".dylib");
/*     */       
/* 131 */       if (null != lib) {
/* 132 */         mappedlibName = mappedlibName.substring(0, mappedlibName.length() - 7) + ".dylib";
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 138 */     if (null != lib) {
/* 139 */       LOGGER.log(Level.FINE, "URL is " + lib.toString());
/* 140 */       LOGGER.log(Level.FINE, "URL path is " + lib.getPath());
/* 141 */       return extractResource(getJniDir(), lib, mappedlibName);
/*     */     } 
/* 143 */     LOGGER.log(Level.INFO, "Couldn't find resource " + libPath + " " + mappedlibName);
/* 144 */     throw new IOException("Couldn't find resource " + libPath + " " + mappedlibName);
/*     */   }
/*     */ 
/*     */   
/*     */   public void extractRegistered() throws IOException {
/* 149 */     LOGGER.log(Level.FINE, "Extracting libraries registered in classloader " + getClass().getClassLoader());
/*     */ 
/*     */     
/* 152 */     for (int i = 0; i < this.nativeResourcePaths.length; i++) {
/* 153 */       Enumeration<URL> resources = getClass().getClassLoader().getResources(this.nativeResourcePaths[i] + "AUTOEXTRACT.LIST");
/*     */       
/* 155 */       while (resources.hasMoreElements()) {
/* 156 */         URL res = resources.nextElement();
/* 157 */         LOGGER.log(Level.FINE, "Extracting libraries listed in " + res);
/* 158 */         BufferedReader r = new BufferedReader(new InputStreamReader(res.openStream(), "UTF-8"));
/*     */         String line;
/* 160 */         while ((line = r.readLine()) != null) {
/* 161 */           URL lib = null;
/* 162 */           for (int j = 0; j < this.nativeResourcePaths.length; j++) {
/* 163 */             lib = getClass().getClassLoader().getResource(this.nativeResourcePaths[j] + line);
/* 164 */             if (lib != null)
/*     */               break; 
/*     */           } 
/* 167 */           if (lib != null) {
/* 168 */             extractResource(getNativeDir(), lib, line);
/*     */             continue;
/*     */           } 
/* 171 */           throw new IOException("Couldn't find native library " + line + "on the classpath");
/*     */         } 
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
/*     */   File extractResource(File dir, URL resource, String outputName) throws IOException {
/* 189 */     InputStream in = resource.openStream();
/*     */ 
/*     */     
/* 192 */     String prefix = outputName;
/* 193 */     String suffix = null;
/* 194 */     int lastDotIndex = outputName.lastIndexOf('.');
/* 195 */     if (-1 != lastDotIndex) {
/* 196 */       prefix = outputName.substring(0, lastDotIndex);
/* 197 */       suffix = outputName.substring(lastDotIndex);
/*     */     } 
/*     */ 
/*     */     
/* 201 */     deleteLeftoverFiles(prefix, suffix);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 208 */     File outfile = File.createTempFile(prefix, suffix);
/* 209 */     LOGGER.log(Level.FINE, "Extracting '" + resource + "' to '" + outfile.getAbsolutePath() + "'");
/*     */ 
/*     */     
/* 212 */     FileOutputStream out = new FileOutputStream(outfile);
/* 213 */     copy(in, out);
/* 214 */     out.close();
/* 215 */     in.close();
/*     */ 
/*     */     
/* 218 */     outfile.deleteOnExit();
/*     */     
/* 220 */     return outfile;
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
/*     */   void deleteLeftoverFiles(final String prefix, final String suffix) {
/* 241 */     File tmpDirectory = new File(System.getProperty("java.io.tmpdir"));
/* 242 */     File[] files = tmpDirectory.listFiles(new FilenameFilter() {
/*     */           public boolean accept(File dir, String name) {
/* 244 */             return (name.startsWith(prefix) && name.endsWith(suffix));
/*     */           }
/*     */         });
/* 247 */     if (files == null)
/* 248 */       return;  for (File file : files) {
/*     */       
/*     */       try {
/* 251 */         file.delete();
/*     */       }
/* 253 */       catch (SecurityException e) {}
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
/*     */   static void copy(InputStream in, OutputStream out) throws IOException {
/* 267 */     byte[] tmp = new byte[8192];
/* 268 */     int len = 0;
/*     */     while (true) {
/* 270 */       len = in.read(tmp);
/* 271 */       if (len <= 0) {
/*     */         break;
/*     */       }
/* 274 */       out.write(tmp, 0, len);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\org\scijava\nativelib\BaseJniExtractor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */