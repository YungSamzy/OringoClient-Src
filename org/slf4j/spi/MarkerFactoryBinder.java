package org.slf4j.spi;

import org.slf4j.IMarkerFactory;

public interface MarkerFactoryBinder {
  IMarkerFactory getMarkerFactory();
  
  String getMarkerFactoryClassStr();
}


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\org\slf4j\spi\MarkerFactoryBinder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */