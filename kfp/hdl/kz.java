package kfp.hdl;

import java.util.LinkedHashMap;
import java.util.Map;

public class kz<K, V> {
  private final LinkedHashMap<K, V> map;
  
  private int size;
  
  private int maxSize;
  
  private int putCount;
  
  private int createCount;
  
  private int evictionCount;
  
  private int hitCount;
  
  private int missCount;
  
  public kz(int paramInt) {
    if (paramInt <= 0)
      throw new IllegalArgumentException("maxSize <= 0"); 
    this.maxSize = paramInt;
    this.map = new LinkedHashMap<K, V>(0, 0.75F, true);
  }
  
  public final synchronized V get(K paramK) {
    if (paramK == null)
      throw new NullPointerException("key == null"); 
    V v = this.map.get(paramK);
    if (v != null) {
      this.hitCount++;
      return v;
    } 
    this.missCount++;
    v = create(paramK);
    if (v != null) {
      this.createCount++;
      this.size += safeSizeOf(paramK, v);
      this.map.put(paramK, v);
      trimToSize(this.maxSize);
    } 
    return v;
  }
  
  public final synchronized void put(K paramK, V paramV) {
    if (paramK == null || paramV == null)
      throw new NullPointerException("key == null || value == null"); 
    this.putCount++;
    this.size += safeSizeOf(paramK, paramV);
    V v = this.map.put(paramK, paramV);
    if (v != null)
      this.size -= safeSizeOf(paramK, v); 
    trimToSize(this.maxSize);
  }
  
  private void trimToSize(int paramInt) {
    while (this.size > paramInt && !this.map.isEmpty()) {
      Map.Entry entry = this.map.entrySet().iterator().next();
      if (entry == null)
        break; 
      Object object1 = entry.getKey();
      Object object2 = entry.getValue();
      this.map.remove(object1);
      this.size -= safeSizeOf((K)object1, (V)object2);
      this.evictionCount++;
      entryEvicted((K)object1, (V)object2);
    } 
    if (this.size < 0 || (this.map.isEmpty() && this.size != 0))
      throw new IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!"); 
  }
  
  public final synchronized V remove(K paramK) {
    if (paramK == null)
      throw new NullPointerException("key == null"); 
    V v = this.map.remove(paramK);
    if (v != null)
      this.size -= safeSizeOf(paramK, v); 
    return v;
  }
  
  protected void entryEvicted(K paramK, V paramV) {}
  
  protected V create(K paramK) {
    return null;
  }
  
  private int safeSizeOf(K paramK, V paramV) {
    int i = sizeOf(paramK, paramV);
    if (i < 0)
      throw new IllegalStateException("Negative size: " + paramK + "=" + paramV); 
    return i;
  }
  
  protected int sizeOf(K paramK, V paramV) {
    return 1;
  }
  
  public final synchronized void evictAll() {
    trimToSize(-1);
  }
  
  public final synchronized int size() {
    return this.size;
  }
  
  public final synchronized int maxSize() {
    return this.maxSize;
  }
  
  public final synchronized int hitCount() {
    return this.hitCount;
  }
  
  public final synchronized int missCount() {
    return this.missCount;
  }
  
  public final synchronized int createCount() {
    return this.createCount;
  }
  
  public final synchronized int putCount() {
    return this.putCount;
  }
  
  public final synchronized int evictionCount() {
    return this.evictionCount;
  }
  
  public final synchronized Map<K, V> snapshot() {
    return new LinkedHashMap<K, V>(this.map);
  }
}


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\kfp\hdl\kz.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */