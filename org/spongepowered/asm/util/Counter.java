/*    */ package org.spongepowered.asm.util;
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
/*    */ public final class Counter
/*    */ {
/*    */   public int value;
/*    */   
/*    */   public boolean equals(Object obj) {
/* 39 */     return (obj != null && obj.getClass() == Counter.class && ((Counter)obj).value == this.value);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 44 */     return this.value;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\org\spongepowered\as\\util\Counter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */