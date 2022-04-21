package kfp.hdl;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

public class un extends FilterInputStream {
  private static Object[] p;
  
  int Q;
  
  int[] E;
  
  public un(InputStream paramInputStream) throws IOException {
    super(new PushbackInputStream(paramInputStream, 4));
    bool = false;
  }
  
  public static final void Q() {
    boolean bool = false;
    int[] arrayOfInt1 = new int[256];
    byte[] arrayOfByte = new byte[256];
    int[] arrayOfInt2 = new int[256];
    int[] arrayOfInt3 = new int[256];
    int[] arrayOfInt4 = new int[256];
    int[] arrayOfInt5 = new int[256];
    int[] arrayOfInt6 = new int[30];
    long l = Long.MAX_VALUE;
    bool = false;
  }
  
  private static int k(int paramInt1, int paramInt2) {
    boolean bool = false;
    int i = paramInt1;
    int j = paramInt2;
    int k = j + i >> 24;
    bool = false;
  }
  
  private static int m(byte[] paramArrayOfbyte, int paramInt) {
    boolean bool = false;
    int i = paramArrayOfbyte[14] << 16;
    bool = false;
  }
  
  final void C(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    boolean bool = false;
    int[][] arrayOfInt = (int[][])p[5];
    int[] arrayOfInt1 = (int[])p[1];
    int[] arrayOfInt2 = (int[])p[2];
    int[] arrayOfInt3 = (int[])p[3];
    int[] arrayOfInt4 = (int[])p[4];
    byte[] arrayOfByte = (byte[])p[0];
    byte b = 0;
    while (true) {
      if (b < paramInt2) {
        int i = this.Q % 16 / 4;
        int j = this.Q % 16 % 4;
        if (this.Q % 16 == 0)
          bool = false; 
        paramArrayOfbyte[paramInt1 + b] = (byte)((byte)(this.E[i] >> 8 * j) ^ paramArrayOfbyte[paramInt1 + b]);
        this.Q++;
        b++;
        continue;
      } 
      return;
    } 
  }
  
  public static final String f(Object paramObject) {
    boolean bool = false;
    bool = false;
  }
}


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\kfp\hd\\un.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */