/*      */ package org.json;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.StringWriter;
/*      */ import java.io.Writer;
/*      */ import java.lang.reflect.Array;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JSONArray
/*      */   implements Iterable<Object>
/*      */ {
/*      */   private final ArrayList<Object> myArrayList;
/*      */   
/*      */   public JSONArray() {
/*   94 */     this.myArrayList = new ArrayList();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSONArray(JSONTokener x) throws JSONException {
/*  106 */     this();
/*  107 */     if (x.nextClean() != '[') {
/*  108 */       throw x.syntaxError("A JSONArray text must start with '['");
/*      */     }
/*  110 */     if (x.nextClean() != ']') {
/*  111 */       x.back();
/*      */       while (true) {
/*  113 */         if (x.nextClean() == ',') {
/*  114 */           x.back();
/*  115 */           this.myArrayList.add(JSONObject.NULL);
/*      */         } else {
/*  117 */           x.back();
/*  118 */           this.myArrayList.add(x.nextValue());
/*      */         } 
/*  120 */         switch (x.nextClean()) {
/*      */           case ',':
/*  122 */             if (x.nextClean() == ']') {
/*      */               return;
/*      */             }
/*  125 */             x.back(); continue;
/*      */           case ']':
/*      */             return;
/*      */         }  break;
/*      */       } 
/*  130 */       throw x.syntaxError("Expected a ',' or ']'");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSONArray(String source) throws JSONException {
/*  147 */     this(new JSONTokener(source));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSONArray(Collection<?> collection) {
/*  157 */     if (collection == null) {
/*  158 */       this.myArrayList = new ArrayList();
/*      */     } else {
/*  160 */       this.myArrayList = new ArrayList(collection.size());
/*  161 */       for (Object o : collection) {
/*  162 */         this.myArrayList.add(JSONObject.wrap(o));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSONArray(Object array) throws JSONException {
/*  174 */     this();
/*  175 */     if (array.getClass().isArray()) {
/*  176 */       int length = Array.getLength(array);
/*  177 */       this.myArrayList.ensureCapacity(length);
/*  178 */       for (int i = 0; i < length; i++) {
/*  179 */         put(JSONObject.wrap(Array.get(array, i)));
/*      */       }
/*      */     } else {
/*  182 */       throw new JSONException("JSONArray initial value should be a string or collection or array.");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Iterator<Object> iterator() {
/*  189 */     return this.myArrayList.iterator();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object get(int index) throws JSONException {
/*  202 */     Object object = opt(index);
/*  203 */     if (object == null) {
/*  204 */       throw new JSONException("JSONArray[" + index + "] not found.");
/*      */     }
/*  206 */     return object;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getBoolean(int index) throws JSONException {
/*  221 */     Object object = get(index);
/*  222 */     if (object.equals(Boolean.FALSE) || (object instanceof String && ((String)object)
/*      */       
/*  224 */       .equalsIgnoreCase("false")))
/*  225 */       return false; 
/*  226 */     if (object.equals(Boolean.TRUE) || (object instanceof String && ((String)object)
/*      */       
/*  228 */       .equalsIgnoreCase("true"))) {
/*  229 */       return true;
/*      */     }
/*  231 */     throw new JSONException("JSONArray[" + index + "] is not a boolean.");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getDouble(int index) throws JSONException {
/*  245 */     Object object = get(index);
/*      */     try {
/*  247 */       return (object instanceof Number) ? ((Number)object).doubleValue() : 
/*  248 */         Double.parseDouble((String)object);
/*  249 */     } catch (Exception e) {
/*  250 */       throw new JSONException("JSONArray[" + index + "] is not a number.", e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getFloat(int index) throws JSONException {
/*  265 */     Object object = get(index);
/*      */     try {
/*  267 */       return (object instanceof Number) ? ((Number)object).floatValue() : 
/*  268 */         Float.parseFloat(object.toString());
/*  269 */     } catch (Exception e) {
/*  270 */       throw new JSONException("JSONArray[" + index + "] is not a number.", e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Number getNumber(int index) throws JSONException {
/*  286 */     Object object = get(index);
/*      */     try {
/*  288 */       if (object instanceof Number) {
/*  289 */         return (Number)object;
/*      */       }
/*  291 */       return JSONObject.stringToNumber(object.toString());
/*  292 */     } catch (Exception e) {
/*  293 */       throw new JSONException("JSONArray[" + index + "] is not a number.", e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <E extends Enum<E>> E getEnum(Class<E> clazz, int index) throws JSONException {
/*  310 */     E val = optEnum(clazz, index);
/*  311 */     if (val == null)
/*      */     {
/*      */ 
/*      */       
/*  315 */       throw new JSONException("JSONArray[" + index + "] is not an enum of type " + 
/*  316 */           JSONObject.quote(clazz.getSimpleName()) + ".");
/*      */     }
/*  318 */     return val;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BigDecimal getBigDecimal(int index) throws JSONException {
/*  332 */     Object object = get(index);
/*      */     try {
/*  334 */       return new BigDecimal(object.toString());
/*  335 */     } catch (Exception e) {
/*  336 */       throw new JSONException("JSONArray[" + index + "] could not convert to BigDecimal.", e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BigInteger getBigInteger(int index) throws JSONException {
/*  352 */     Object object = get(index);
/*      */     try {
/*  354 */       return new BigInteger(object.toString());
/*  355 */     } catch (Exception e) {
/*  356 */       throw new JSONException("JSONArray[" + index + "] could not convert to BigInteger.", e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getInt(int index) throws JSONException {
/*  371 */     Object object = get(index);
/*      */     try {
/*  373 */       return (object instanceof Number) ? ((Number)object).intValue() : 
/*  374 */         Integer.parseInt((String)object);
/*  375 */     } catch (Exception e) {
/*  376 */       throw new JSONException("JSONArray[" + index + "] is not a number.", e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSONArray getJSONArray(int index) throws JSONException {
/*  391 */     Object object = get(index);
/*  392 */     if (object instanceof JSONArray) {
/*  393 */       return (JSONArray)object;
/*      */     }
/*  395 */     throw new JSONException("JSONArray[" + index + "] is not a JSONArray.");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSONObject getJSONObject(int index) throws JSONException {
/*  409 */     Object object = get(index);
/*  410 */     if (object instanceof JSONObject) {
/*  411 */       return (JSONObject)object;
/*      */     }
/*  413 */     throw new JSONException("JSONArray[" + index + "] is not a JSONObject.");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getLong(int index) throws JSONException {
/*  427 */     Object object = get(index);
/*      */     try {
/*  429 */       return (object instanceof Number) ? ((Number)object).longValue() : 
/*  430 */         Long.parseLong((String)object);
/*  431 */     } catch (Exception e) {
/*  432 */       throw new JSONException("JSONArray[" + index + "] is not a number.", e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getString(int index) throws JSONException {
/*  446 */     Object object = get(index);
/*  447 */     if (object instanceof String) {
/*  448 */       return (String)object;
/*      */     }
/*  450 */     throw new JSONException("JSONArray[" + index + "] not a string.");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isNull(int index) {
/*  461 */     return JSONObject.NULL.equals(opt(index));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String join(String separator) throws JSONException {
/*  476 */     int len = length();
/*  477 */     StringBuilder sb = new StringBuilder();
/*      */     
/*  479 */     for (int i = 0; i < len; i++) {
/*  480 */       if (i > 0) {
/*  481 */         sb.append(separator);
/*      */       }
/*  483 */       sb.append(JSONObject.valueToString(this.myArrayList.get(i)));
/*      */     } 
/*  485 */     return sb.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int length() {
/*  494 */     return this.myArrayList.size();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object opt(int index) {
/*  505 */     return (index < 0 || index >= length()) ? null : this.myArrayList
/*  506 */       .get(index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean optBoolean(int index) {
/*  519 */     return optBoolean(index, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean optBoolean(int index, boolean defaultValue) {
/*      */     try {
/*  535 */       return getBoolean(index);
/*  536 */     } catch (Exception e) {
/*  537 */       return defaultValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double optDouble(int index) {
/*  551 */     return optDouble(index, Double.NaN);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double optDouble(int index, double defaultValue) {
/*  566 */     Object val = opt(index);
/*  567 */     if (JSONObject.NULL.equals(val)) {
/*  568 */       return defaultValue;
/*      */     }
/*  570 */     if (val instanceof Number) {
/*  571 */       return ((Number)val).doubleValue();
/*      */     }
/*  573 */     if (val instanceof String) {
/*      */       try {
/*  575 */         return Double.parseDouble((String)val);
/*  576 */       } catch (Exception e) {
/*  577 */         return defaultValue;
/*      */       } 
/*      */     }
/*  580 */     return defaultValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float optFloat(int index) {
/*  593 */     return optFloat(index, Float.NaN);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float optFloat(int index, float defaultValue) {
/*  608 */     Object val = opt(index);
/*  609 */     if (JSONObject.NULL.equals(val)) {
/*  610 */       return defaultValue;
/*      */     }
/*  612 */     if (val instanceof Number) {
/*  613 */       return ((Number)val).floatValue();
/*      */     }
/*  615 */     if (val instanceof String) {
/*      */       try {
/*  617 */         return Float.parseFloat((String)val);
/*  618 */       } catch (Exception e) {
/*  619 */         return defaultValue;
/*      */       } 
/*      */     }
/*  622 */     return defaultValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int optInt(int index) {
/*  635 */     return optInt(index, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int optInt(int index, int defaultValue) {
/*  650 */     Object val = opt(index);
/*  651 */     if (JSONObject.NULL.equals(val)) {
/*  652 */       return defaultValue;
/*      */     }
/*  654 */     if (val instanceof Number) {
/*  655 */       return ((Number)val).intValue();
/*      */     }
/*      */     
/*  658 */     if (val instanceof String) {
/*      */       try {
/*  660 */         return (new BigDecimal(val.toString())).intValue();
/*  661 */       } catch (Exception e) {
/*  662 */         return defaultValue;
/*      */       } 
/*      */     }
/*  665 */     return defaultValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <E extends Enum<E>> E optEnum(Class<E> clazz, int index) {
/*  678 */     return optEnum(clazz, index, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <E extends Enum<E>> E optEnum(Class<E> clazz, int index, E defaultValue) {
/*      */     try {
/*  695 */       Object val = opt(index);
/*  696 */       if (JSONObject.NULL.equals(val)) {
/*  697 */         return defaultValue;
/*      */       }
/*  699 */       if (clazz.isAssignableFrom(val.getClass()))
/*      */       {
/*      */         
/*  702 */         return (E)val;
/*      */       }
/*      */       
/*  705 */       return Enum.valueOf(clazz, val.toString());
/*  706 */     } catch (IllegalArgumentException e) {
/*  707 */       return defaultValue;
/*  708 */     } catch (NullPointerException e) {
/*  709 */       return defaultValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BigInteger optBigInteger(int index, BigInteger defaultValue) {
/*  726 */     Object val = opt(index);
/*  727 */     if (JSONObject.NULL.equals(val)) {
/*  728 */       return defaultValue;
/*      */     }
/*  730 */     if (val instanceof BigInteger) {
/*  731 */       return (BigInteger)val;
/*      */     }
/*  733 */     if (val instanceof BigDecimal) {
/*  734 */       return ((BigDecimal)val).toBigInteger();
/*      */     }
/*  736 */     if (val instanceof Double || val instanceof Float) {
/*  737 */       return (new BigDecimal(((Number)val).doubleValue())).toBigInteger();
/*      */     }
/*  739 */     if (val instanceof Long || val instanceof Integer || val instanceof Short || val instanceof Byte)
/*      */     {
/*  741 */       return BigInteger.valueOf(((Number)val).longValue());
/*      */     }
/*      */     try {
/*  744 */       String valStr = val.toString();
/*  745 */       if (JSONObject.isDecimalNotation(valStr)) {
/*  746 */         return (new BigDecimal(valStr)).toBigInteger();
/*      */       }
/*  748 */       return new BigInteger(valStr);
/*  749 */     } catch (Exception e) {
/*  750 */       return defaultValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BigDecimal optBigDecimal(int index, BigDecimal defaultValue) {
/*  766 */     Object val = opt(index);
/*  767 */     if (JSONObject.NULL.equals(val)) {
/*  768 */       return defaultValue;
/*      */     }
/*  770 */     if (val instanceof BigDecimal) {
/*  771 */       return (BigDecimal)val;
/*      */     }
/*  773 */     if (val instanceof BigInteger) {
/*  774 */       return new BigDecimal((BigInteger)val);
/*      */     }
/*  776 */     if (val instanceof Double || val instanceof Float) {
/*  777 */       return new BigDecimal(((Number)val).doubleValue());
/*      */     }
/*  779 */     if (val instanceof Long || val instanceof Integer || val instanceof Short || val instanceof Byte)
/*      */     {
/*  781 */       return new BigDecimal(((Number)val).longValue());
/*      */     }
/*      */     try {
/*  784 */       return new BigDecimal(val.toString());
/*  785 */     } catch (Exception e) {
/*  786 */       return defaultValue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSONArray optJSONArray(int index) {
/*  799 */     Object o = opt(index);
/*  800 */     return (o instanceof JSONArray) ? (JSONArray)o : null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSONObject optJSONObject(int index) {
/*  813 */     Object o = opt(index);
/*  814 */     return (o instanceof JSONObject) ? (JSONObject)o : null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long optLong(int index) {
/*  827 */     return optLong(index, 0L);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long optLong(int index, long defaultValue) {
/*  842 */     Object val = opt(index);
/*  843 */     if (JSONObject.NULL.equals(val)) {
/*  844 */       return defaultValue;
/*      */     }
/*  846 */     if (val instanceof Number) {
/*  847 */       return ((Number)val).longValue();
/*      */     }
/*      */     
/*  850 */     if (val instanceof String) {
/*      */       try {
/*  852 */         return (new BigDecimal(val.toString())).longValue();
/*  853 */       } catch (Exception e) {
/*  854 */         return defaultValue;
/*      */       } 
/*      */     }
/*  857 */     return defaultValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Number optNumber(int index) {
/*  871 */     return optNumber(index, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Number optNumber(int index, Number defaultValue) {
/*  887 */     Object val = opt(index);
/*  888 */     if (JSONObject.NULL.equals(val)) {
/*  889 */       return defaultValue;
/*      */     }
/*  891 */     if (val instanceof Number) {
/*  892 */       return (Number)val;
/*      */     }
/*      */     
/*  895 */     if (val instanceof String) {
/*      */       try {
/*  897 */         return JSONObject.stringToNumber((String)val);
/*  898 */       } catch (Exception e) {
/*  899 */         return defaultValue;
/*      */       } 
/*      */     }
/*  902 */     return defaultValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String optString(int index) {
/*  915 */     return optString(index, "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String optString(int index, String defaultValue) {
/*  929 */     Object object = opt(index);
/*  930 */     return JSONObject.NULL.equals(object) ? defaultValue : object
/*  931 */       .toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSONArray put(boolean value) {
/*  942 */     put(value ? Boolean.TRUE : Boolean.FALSE);
/*  943 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSONArray put(Collection<?> value) {
/*  955 */     put(new JSONArray(value));
/*  956 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSONArray put(double value) throws JSONException {
/*  969 */     Double d = new Double(value);
/*  970 */     JSONObject.testValidity(d);
/*  971 */     put(d);
/*  972 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSONArray put(int value) {
/*  983 */     put(new Integer(value));
/*  984 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSONArray put(long value) {
/*  995 */     put(new Long(value));
/*  996 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSONArray put(Map<?, ?> value) {
/* 1008 */     put(new JSONObject(value));
/* 1009 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSONArray put(Object value) {
/* 1022 */     this.myArrayList.add(value);
/* 1023 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSONArray put(int index, boolean value) throws JSONException {
/* 1040 */     put(index, value ? Boolean.TRUE : Boolean.FALSE);
/* 1041 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSONArray put(int index, Collection<?> value) throws JSONException {
/* 1057 */     put(index, new JSONArray(value));
/* 1058 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSONArray put(int index, double value) throws JSONException {
/* 1075 */     put(index, new Double(value));
/* 1076 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSONArray put(int index, int value) throws JSONException {
/* 1093 */     put(index, new Integer(value));
/* 1094 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSONArray put(int index, long value) throws JSONException {
/* 1111 */     put(index, new Long(value));
/* 1112 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSONArray put(int index, Map<?, ?> value) throws JSONException {
/* 1129 */     put(index, new JSONObject(value));
/* 1130 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSONArray put(int index, Object value) throws JSONException {
/* 1150 */     JSONObject.testValidity(value);
/* 1151 */     if (index < 0) {
/* 1152 */       throw new JSONException("JSONArray[" + index + "] not found.");
/*      */     }
/* 1154 */     if (index < length()) {
/* 1155 */       this.myArrayList.set(index, value);
/* 1156 */     } else if (index == length()) {
/*      */       
/* 1158 */       put(value);
/*      */     }
/*      */     else {
/*      */       
/* 1162 */       this.myArrayList.ensureCapacity(index + 1);
/* 1163 */       while (index != length()) {
/* 1164 */         put(JSONObject.NULL);
/*      */       }
/* 1166 */       put(value);
/*      */     } 
/* 1168 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object query(String jsonPointer) {
/* 1191 */     return query(new JSONPointer(jsonPointer));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object query(JSONPointer jsonPointer) {
/* 1214 */     return jsonPointer.queryFrom(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object optQuery(String jsonPointer) {
/* 1226 */     return optQuery(new JSONPointer(jsonPointer));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object optQuery(JSONPointer jsonPointer) {
/*      */     try {
/* 1239 */       return jsonPointer.queryFrom(this);
/* 1240 */     } catch (JSONPointerException e) {
/* 1241 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object remove(int index) {
/* 1254 */     return (index >= 0 && index < length()) ? this.myArrayList
/* 1255 */       .remove(index) : null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean similar(Object other) {
/* 1267 */     if (!(other instanceof JSONArray)) {
/* 1268 */       return false;
/*      */     }
/* 1270 */     int len = length();
/* 1271 */     if (len != ((JSONArray)other).length()) {
/* 1272 */       return false;
/*      */     }
/* 1274 */     for (int i = 0; i < len; i++) {
/* 1275 */       Object valueThis = this.myArrayList.get(i);
/* 1276 */       Object valueOther = ((JSONArray)other).myArrayList.get(i);
/* 1277 */       if (valueThis == valueOther) {
/* 1278 */         return true;
/*      */       }
/* 1280 */       if (valueThis == null) {
/* 1281 */         return false;
/*      */       }
/* 1283 */       if (valueThis instanceof JSONObject) {
/* 1284 */         if (!((JSONObject)valueThis).similar(valueOther)) {
/* 1285 */           return false;
/*      */         }
/* 1287 */       } else if (valueThis instanceof JSONArray) {
/* 1288 */         if (!((JSONArray)valueThis).similar(valueOther)) {
/* 1289 */           return false;
/*      */         }
/* 1291 */       } else if (!valueThis.equals(valueOther)) {
/* 1292 */         return false;
/*      */       } 
/*      */     } 
/* 1295 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSONObject toJSONObject(JSONArray names) throws JSONException {
/* 1311 */     if (names == null || names.length() == 0 || length() == 0) {
/* 1312 */       return null;
/*      */     }
/* 1314 */     JSONObject jo = new JSONObject(names.length());
/* 1315 */     for (int i = 0; i < names.length(); i++) {
/* 1316 */       jo.put(names.getString(i), opt(i));
/*      */     }
/* 1318 */     return jo;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/*      */     try {
/* 1336 */       return toString(0);
/* 1337 */     } catch (Exception e) {
/* 1338 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString(int indentFactor) throws JSONException {
/* 1370 */     StringWriter sw = new StringWriter();
/* 1371 */     synchronized (sw.getBuffer()) {
/* 1372 */       return write(sw, indentFactor, 0).toString();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Writer write(Writer writer) throws JSONException {
/* 1387 */     return write(writer, 0, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Writer write(Writer writer, int indentFactor, int indent) throws JSONException {
/*      */     try {
/* 1421 */       boolean commanate = false;
/* 1422 */       int length = length();
/* 1423 */       writer.write(91);
/*      */       
/* 1425 */       if (length == 1) {
/*      */         try {
/* 1427 */           JSONObject.writeValue(writer, this.myArrayList.get(0), indentFactor, indent);
/*      */         }
/* 1429 */         catch (Exception e) {
/* 1430 */           throw new JSONException("Unable to write JSONArray value at index: 0", e);
/*      */         } 
/* 1432 */       } else if (length != 0) {
/* 1433 */         int newindent = indent + indentFactor;
/*      */         
/* 1435 */         for (int i = 0; i < length; i++) {
/* 1436 */           if (commanate) {
/* 1437 */             writer.write(44);
/*      */           }
/* 1439 */           if (indentFactor > 0) {
/* 1440 */             writer.write(10);
/*      */           }
/* 1442 */           JSONObject.indent(writer, newindent);
/*      */           try {
/* 1444 */             JSONObject.writeValue(writer, this.myArrayList.get(i), indentFactor, newindent);
/*      */           }
/* 1446 */           catch (Exception e) {
/* 1447 */             throw new JSONException("Unable to write JSONArray value at index: " + i, e);
/*      */           } 
/* 1449 */           commanate = true;
/*      */         } 
/* 1451 */         if (indentFactor > 0) {
/* 1452 */           writer.write(10);
/*      */         }
/* 1454 */         JSONObject.indent(writer, indent);
/*      */       } 
/* 1456 */       writer.write(93);
/* 1457 */       return writer;
/* 1458 */     } catch (IOException e) {
/* 1459 */       throw new JSONException(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<Object> toList() {
/* 1473 */     List<Object> results = new ArrayList(this.myArrayList.size());
/* 1474 */     for (Object element : this.myArrayList) {
/* 1475 */       if (element == null || JSONObject.NULL.equals(element)) {
/* 1476 */         results.add(null); continue;
/* 1477 */       }  if (element instanceof JSONArray) {
/* 1478 */         results.add(((JSONArray)element).toList()); continue;
/* 1479 */       }  if (element instanceof JSONObject) {
/* 1480 */         results.add(((JSONObject)element).toMap()); continue;
/*      */       } 
/* 1482 */       results.add(element);
/*      */     } 
/*      */     
/* 1485 */     return results;
/*      */   }
/*      */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\org\json\JSONArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */