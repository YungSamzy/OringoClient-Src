package org.slf4j.event;

import org.slf4j.Marker;

public interface LoggingEvent {
  Level getLevel();
  
  Marker getMarker();
  
  String getLoggerName();
  
  String getMessage();
  
  String getThreadName();
  
  Object[] getArgumentArray();
  
  long getTimeStamp();
  
  Throwable getThrowable();
}


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\org\slf4j\event\LoggingEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */