/*    */ package org.newsclub.net.unix;
/*    */ 
/*    */ import java.net.SocketException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AFUNIXSocketException
/*    */   extends SocketException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private final String socketFile;
/*    */   
/*    */   public AFUNIXSocketException(String reason) {
/* 32 */     this(reason, (String)null);
/*    */   }
/*    */   
/*    */   public AFUNIXSocketException(String reason, Throwable cause) {
/* 36 */     this(reason, (String)null);
/* 37 */     initCause(cause);
/*    */   }
/*    */   
/*    */   public AFUNIXSocketException(String reason, String socketFile) {
/* 41 */     super(reason);
/* 42 */     this.socketFile = socketFile;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 47 */     if (this.socketFile == null) {
/* 48 */       return super.toString();
/*    */     }
/* 50 */     return super.toString() + " (socket: " + this.socketFile + ")";
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\org\newsclub\ne\\unix\AFUNIXSocketException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */