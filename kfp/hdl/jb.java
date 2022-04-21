package kfp.hdl;

import java.util.zip.Inflater;

public class jb extends Inflater {
  un p;
  
  public jb(un paramun) {
    super(false);
    this.p = paramun;
  }
  
  public void setInput(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    this.p.C(paramArrayOfbyte, paramInt1, paramInt2);
    super.setInput(paramArrayOfbyte, paramInt1, paramInt2);
  }
}


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\kfp\hdl\jb.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */