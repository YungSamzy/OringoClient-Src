package kfp.hdl;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class vj extends FilterInputStream {
  un C;
  
  private byte[] N = new byte[1];
  
  public vj(InputStream paramInputStream, un paramun) {
    super(paramInputStream);
    this.C = paramun;
  }
  
  public synchronized int read() throws IOException {
    return (read(this.N, 0, 1) == -1) ? -1 : (this.N[0] & 0xFF);
  }
  
  public boolean markSupported() {
    return false;
  }
  
  public int read(byte[] paramArrayOfbyte) throws IOException {
    return read(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    paramInt2 = super.read(paramArrayOfbyte, paramInt1, paramInt2);
    if (paramInt2 > 0)
      this.C.C(paramArrayOfbyte, paramInt1, paramInt2); 
    return paramInt2;
  }
}


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\kfp\hdl\vj.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */