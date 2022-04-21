package kfp.hdl;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.HashMap;
import java.util.Map;

class es implements URLStreamHandlerFactory {
  private final Map<String, URLStreamHandler> T = new HashMap<String, URLStreamHandler>();
  
  public es(String paramString, URLStreamHandler paramURLStreamHandler) {
    this.T.put(paramString, paramURLStreamHandler);
  }
  
  public es() {}
  
  public void addHandler(String paramString, URLStreamHandler paramURLStreamHandler) {
    this.T.put(paramString, paramURLStreamHandler);
  }
  
  public URLStreamHandler createURLStreamHandler(String paramString) {
    return this.T.get(paramString);
  }
}


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\kfp\hdl\es.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */