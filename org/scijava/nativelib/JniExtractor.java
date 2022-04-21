package org.scijava.nativelib;

import java.io.File;
import java.io.IOException;

public interface JniExtractor {
  File extractJni(String paramString1, String paramString2) throws IOException;
  
  void extractRegistered() throws IOException;
}


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\org\scijava\nativelib\JniExtractor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */