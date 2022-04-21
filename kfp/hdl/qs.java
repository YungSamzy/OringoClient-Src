package kfp.hdl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class qs extends URLConnection {
  URLConnection P;
  
  public qs(URL paramURL) throws IOException {
    super(paramURL);
    this.P = paramURL.openConnection();
  }
  
  public void connect() throws IOException {
    this.P.connect();
  }
  
  public InputStream getInputStream() throws IOException {
    return new un(this.P.getInputStream());
  }
}


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\kfp\hdl\qs.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */