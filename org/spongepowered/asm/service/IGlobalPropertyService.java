package org.spongepowered.asm.service;

public interface IGlobalPropertyService {
  <T> T getProperty(String paramString);
  
  void setProperty(String paramString, Object paramObject);
  
  <T> T getProperty(String paramString, T paramT);
  
  String getPropertyString(String paramString1, String paramString2);
}


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\org\spongepowered\asm\service\IGlobalPropertyService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */