package essentials.lib;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

final class sv {
  private static final Object[] v;
  
  private static final Class[] N;
  
  private static final int[] B;
  
  private static final short[] e;
  
  private static final int h = Integer.parseInt(nn.x("?䰋饋慁骾橏졇䮼㩎".toCharArray(), (short)32234, (short)5, 4));
  
  private static final int G = Integer.parseInt(nn.x("혚㉴喟信鼉䚼в萢쒕㳝".toCharArray(), (short)19464, (short)0, 1));
  
  static {
    v = new Object[29];
    N = new Class[15];
    B = new int[29];
    "悤ᘧ⳴貤₷份?䝍訳뢽镢㧢솨༺颷?爊㜜㧿軂᮹顓僥㷷瑜터୤ꜫ痡眠座䫰䏾őꟀ辴䂿﹭ࡶﱐ̧䘜荔墘⬻լ뺆⌹颷᧾瓍吝帨祦".toCharArray()[29] = (char)("悤ᘧ⳴貤₷份?䝍訳뢽镢㧢솨༺颷?爊㜜㧿軂᮹顓僥㷷瑜터୤ꜫ痡眠座䫰䏾őꟀ辴䂿﹭ࡶﱐ̧䘜荔墘⬻լ뺆⌹颷᧾瓍吝帨祦".toCharArray()[29] ^ 0x18E2);
    char[] arrayOfChar = nn.x("悤ᘧ⳴貤₷份?䝍訳뢽镢㧢솨༺颷?爊㜜㧿軂᮹顓僥㷷瑜터୤ꜫ痡眠座䫰䏾őꟀ辴䂿﹭ࡶﱐ̧䘜荔墘⬻լ뺆⌹颷᧾瓍吝帨祦".toCharArray(), (short)9938, (short)4, 4).toCharArray();
    byte b;
    for (b = 0; b < 29; b++)
      B[b] = arrayOfChar[b * 2] | arrayOfChar[b * 2 + 1] << 16; 
    e = new short[29];
    "?滕랰뇝쨬㤠椷察黓鲻䃼姃૦됑洮㔑ꦉ蜠쁕虗↍ᯢ汸캸輞᷂ഌ媶".toCharArray()[25] = (char)("?滕랰뇝쨬㤠椷察黓鲻䃼姃૦됑洮㔑ꦉ蜠쁕虗↍ᯢ汸캸輞᷂ഌ媶".toCharArray()[25] ^ 0x589A);
    arrayOfChar = nn.x("?滕랰뇝쨬㤠椷察黓鲻䃼姃૦됑洮㔑ꦉ蜠쁕虗↍ᯢ汸캸輞᷂ഌ媶".toCharArray(), (short)19194, (short)1, 3).toCharArray();
    for (b = 0; b < 29; b++)
      e[b] = (short)arrayOfChar[b]; 
  }
  
  private static Class T(int paramInt1, int paramInt2) {
    int i = e[paramInt1] & 0xFFFF;
    i = (i + paramInt2) % 15;
    Class clazz = N[i];
    if (clazz != null)
      return clazz; 
    clazz = s(i);
    N[i] = clazz;
    return clazz;
  }
  
  private static Class s(int paramInt) {
    String str;
    switch (paramInt) {
      case 0:
        "݉꟠漾䥮괪駊覌੮ଳꨔ愭佶?뱡껞ⅷ".toCharArray()[9] = (char)("݉꟠漾䥮괪駊覌੮ଳꨔ愭佶?뱡껞ⅷ".toCharArray()[9] ^ 0x13B3);
        str = nn.x("݉꟠漾䥮괪駊覌੮ଳꨔ愭佶?뱡껞ⅷ".toCharArray(), (short)17520, (short)2, 2);
        return Class.forName(str);
      case 1:
        "澏ﳓꆥᨺﬁ𓲔䪨帞轏港麧軮邭ꟙ焃".toCharArray()[6] = (char)("澏ﳓꆥᨺﬁ𓲔䪨帞轏港麧軮邭ꟙ焃".toCharArray()[6] ^ 0x50BD);
        str = nn.x("澏ﳓꆥᨺﬁ𓲔䪨帞轏港麧軮邭ꟙ焃".toCharArray(), (short)7996, (short)0, 5);
        return Class.forName(str);
      case 2:
        "穊࠶顣㿜㾂䨎我ꍶ?┋㺺考礌ꢇ㪎վ呏é으྇㱯杴픷ﻞ膗 钨䣧㩘덨㷚࿖됣귢찆︖ᰦぢ㤧퐺䫗".toCharArray()[44] = (char)("穊࠶顣㿜㾂䨎我ꍶ?┋㺺考礌ꢇ㪎վ呏é으྇㱯杴픷ﻞ膗 钨䣧㩘덨㷚࿖됣귢찆︖ᰦぢ㤧퐺䫗".toCharArray()[44] ^ 0x1C4C);
        str = nn.x("穊࠶顣㿜㾂䨎我ꍶ?┋㺺考礌ꢇ㪎վ呏é으྇㱯杴픷ﻞ膗 钨䣧㩘덨㷚࿖됣귢찆︖ᰦぢ㤧퐺䫗".toCharArray(), (short)10304, (short)3, 2);
        return Class.forName(str);
      case 3:
        "﷐뫏㳀ⴵ䄣熐ர祻Ɡ຺䲌ൊ稜".toCharArray()[1] = (char)("﷐뫏㳀ⴵ䄣熐ர祻Ɡ຺䲌ൊ稜".toCharArray()[1] ^ 0x694C);
        str = nn.x("﷐뫏㳀ⴵ䄣熐ர祻Ɡ຺䲌ൊ稜".toCharArray(), (short)2239, (short)0, 0);
        return Class.forName(str);
      case 4:
        "鳇췐룷ﵮ춟좘․왈?⶙换䪬ⴚ寯圖ꫩ▨䐛⇰瘱就੶ᕣ".toCharArray()[3] = (char)("鳇췐룷ﵮ춟좘․왈?⶙换䪬ⴚ寯圖ꫩ▨䐛⇰瘱就੶ᕣ".toCharArray()[3] ^ 0x60A4);
        str = nn.x("鳇췐룷ﵮ춟좘․왈?⶙换䪬ⴚ寯圖ꫩ▨䐛⇰瘱就੶ᕣ".toCharArray(), (short)13864, (short)4, 0);
        return Class.forName(str);
      case 5:
        "탓␭㪮꽦㦂ꯩ᧱冔뀜㱮㖋ٕ".toCharArray()[0] = (char)("탓␭㪮꽦㦂ꯩ᧱冔뀜㱮㖋ٕ".toCharArray()[0] ^ 0x1CC2);
        str = nn.x("탓␭㪮꽦㦂ꯩ᧱冔뀜㱮㖋ٕ".toCharArray(), (short)2344, (short)4, 0);
        return Class.forName(str);
      case 6:
        "꜂鴇괤碽쑱浽뾫柙륹㮚⧎奾빏Ѿ跺콕腿䎦⭷ྼ練ﾊ橗潎侈".toCharArray()[9] = (char)("꜂鴇괤碽쑱浽뾫柙륹㮚⧎奾빏Ѿ跺콕腿䎦⭷ྼ練ﾊ橗潎侈".toCharArray()[9] ^ 0x2B4B);
        str = nn.x("꜂鴇괤碽쑱浽뾫柙륹㮚⧎奾빏Ѿ跺콕腿䎦⭷ྼ練ﾊ橗潎侈".toCharArray(), (short)689, (short)3, 4);
        return Class.forName(str);
      case 7:
        "镸竉꽎쑪绦刔暩柚爵趗㐕鹙䦆ࡹၚ짲怃䶏㸲廲쉰埣".toCharArray()[0] = (char)("镸竉꽎쑪绦刔暩柚爵趗㐕鹙䦆ࡹၚ짲怃䶏㸲廲쉰埣".toCharArray()[0] ^ 0x8A2);
        str = nn.x("镸竉꽎쑪绦刔暩柚爵趗㐕鹙䦆ࡹၚ짲怃䶏㸲廲쉰埣".toCharArray(), (short)12374, (short)2, 1);
        return Class.forName(str);
      case 8:
        "㔞骾䙊ᕿ嫕殼卉詜⫞쮽沎ﯔ副舃룂睶蚏꫌㽭鏀㉲".toCharArray()[5] = (char)("㔞骾䙊ᕿ嫕殼卉詜⫞쮽沎ﯔ副舃룂睶蚏꫌㽭鏀㉲".toCharArray()[5] ^ 0x2EC7);
        str = nn.x("㔞骾䙊ᕿ嫕殼卉詜⫞쮽沎ﯔ副舃룂睶蚏꫌㽭鏀㉲".toCharArray(), (short)16908, (short)4, 2);
        return Class.forName(str);
      case 9:
        "糸ᧁ폁?퀛ꯚ肚ꒃ갍?꯴뀓‭턯㖕䄧쁑쀠᰻Ἒ贾ꕜ眮蚪志硰".toCharArray()[23] = (char)("糸ᧁ폁?퀛ꯚ肚ꒃ갍?꯴뀓‭턯㖕䄧쁑쀠᰻Ἒ贾ꕜ眮蚪志硰".toCharArray()[23] ^ 0x45C2);
        str = nn.x("糸ᧁ폁?퀛ꯚ肚ꒃ갍?꯴뀓‭턯㖕䄧쁑쀠᰻Ἒ贾ꕜ眮蚪志硰".toCharArray(), (short)29267, (short)1, 3);
        return Class.forName(str);
      case 10:
        "㮜덯⟆枼?ᮚꟆ潳뀈搞?뿯ࠂ抈?裸귐駷䓝읇ꚙ?䩟⊣턙᧝".toCharArray()[18] = (char)("㮜덯⟆枼?ᮚꟆ潳뀈搞?뿯ࠂ抈?裸귐駷䓝읇ꚙ?䩟⊣턙᧝".toCharArray()[18] ^ 0x5A94);
        str = nn.x("㮜덯⟆枼?ᮚꟆ潳뀈搞?뿯ࠂ抈?裸귐駷䓝읇ꚙ?䩟⊣턙᧝".toCharArray(), (short)19333, (short)2, 5);
        return Class.forName(str);
      case 11:
        "迶葜얱푈℞희駥驙迁䠏甚ǆ奯谶⟓蘱᬴Ꮇ".toCharArray()[16] = (char)("迶葜얱푈℞희駥驙迁䠏甚ǆ奯谶⟓蘱᬴Ꮇ".toCharArray()[16] ^ 0x7857);
        str = nn.x("迶葜얱푈℞희駥驙迁䠏甚ǆ奯谶⟓蘱᬴Ꮇ".toCharArray(), (short)12886, (short)0, 0);
        return Class.forName(str);
      case 12:
        "ੋ㶲萙℺坭뀽宊䰞啭㭇բ㱨᱒喊콑뺠魴䍈".toCharArray()[9] = (char)("ੋ㶲萙℺坭뀽宊䰞啭㭇բ㱨᱒喊콑뺠魴䍈".toCharArray()[9] ^ 0x837);
        str = nn.x("ੋ㶲萙℺坭뀽宊䰞啭㭇բ㱨᱒喊콑뺠魴䍈".toCharArray(), (short)3948, (short)2, 5);
        return Class.forName(str);
      case 13:
        "㗷坙㯅聫뵉品녧㒡鱁ꇿ䎎橯戊沸㧤਻浊뵫鋼ﭠ甞廙".toCharArray()[19] = (char)("㗷坙㯅聫뵉品녧㒡鱁ꇿ䎎橯戊沸㧤਻浊뵫鋼ﭠ甞廙".toCharArray()[19] ^ 0x168);
        str = nn.x("㗷坙㯅聫뵉品녧㒡鱁ꇿ䎎橯戊沸㧤਻浊뵫鋼ﭠ甞廙".toCharArray(), (short)24889, (short)2, 3);
        return Class.forName(str);
      case 14:
        "?ᚧ옫䧵ܺ䫄ꖰ傦騁뜀㺒삗毢ቼ纖偀痙".toCharArray()[1] = (char)("?ᚧ옫䧵ܺ䫄ꖰ傦騁뜀㺒삗毢ቼ纖偀痙".toCharArray()[1] ^ 0x6A9);
        str = nn.x("?ᚧ옫䧵ܺ䫄ꖰ傦騁뜀㺒삗毢ቼ纖偀痙".toCharArray(), (short)22633, (short)2, 3);
        return Class.forName(str);
    } 
    throw new NoClassDefFoundError(Integer.toString(paramInt));
  }
  
  static Method d(int paramInt) {
    paramInt = ((paramInt + -1646288121 ^ h) - -599016815 ^ 0x4984D61E) - G;
    int i = paramInt >>> 16;
    paramInt &= 0xFFFF;
    Method method = (Method)v[paramInt];
    if (method != null)
      return method; 
    Class clazz1 = T(paramInt, i);
    Class clazz2 = clazz1;
    int j = B[paramInt];
    while (clazz1 != null) {
      Method[] arrayOfMethod = clazz1.isInterface() ? clazz1.getMethods() : clazz1.getDeclaredMethods();
      for (Method method1 : arrayOfMethod) {
        int k = i * 31 + method1.getName().hashCode();
        k = 31 * k + 40;
        Class[] arrayOfClass = method1.getParameterTypes();
        for (byte b = 0; b < arrayOfClass.length; b++) {
          Class clazz = arrayOfClass[b];
          if (b != 0)
            k = 31 * k + 44; 
          k = 31 * k + clazz.getName().hashCode();
        } 
        k = 31 * k + 41;
        k = 31 * k + method1.getReturnType().getName().hashCode();
        k = 31 * k + i;
        if (j == k) {
          method1.setAccessible(true);
          v[paramInt] = method1;
          return method1;
        } 
      } 
      clazz1 = clazz1.getSuperclass();
    } 
    for (clazz1 = clazz2; clazz1 != null; clazz1 = clazz1.getSuperclass()) {
      Class[] arrayOfClass = clazz1.getInterfaces();
      for (Class clazz : arrayOfClass) {
        Method[] arrayOfMethod = clazz.getMethods();
        for (Method method1 : arrayOfMethod) {
          int k = i * 31 + method1.getName().hashCode();
          k = 31 * k + 40;
          Class[] arrayOfClass1 = method1.getParameterTypes();
          for (byte b = 0; b < arrayOfClass1.length; b++) {
            Class clazz3 = arrayOfClass1[b];
            if (b != 0)
              k = 31 * k + 44; 
            k = 31 * k + clazz3.getName().hashCode();
          } 
          k = 31 * k + 41;
          k = 31 * k + method1.getReturnType().getName().hashCode();
          k = 31 * k + i;
          if (j == k) {
            method1.setAccessible(true);
            v[paramInt] = method1;
            return method1;
          } 
        } 
      } 
    } 
    return null;
  }
  
  static Object H(int paramInt, Object[] paramArrayOfObject) {
    try {
      Method method = d(paramInt);
      if (method == null)
        throw new NoSuchMethodError(Integer.toString(paramInt)); 
      return method.invoke((Object)null, paramArrayOfObject);
    } catch (InvocationTargetException invocationTargetException) {
      throw invocationTargetException.getTargetException();
    } 
  }
  
  static Object A(Object paramObject, int paramInt, Object[] paramArrayOfObject) {
    try {
      Method method = d(paramInt);
      if (method == null)
        throw new NoSuchMethodError(Integer.toString(paramInt)); 
      return method.invoke(paramObject, paramArrayOfObject);
    } catch (InvocationTargetException invocationTargetException) {
      throw invocationTargetException.getTargetException();
    } 
  }
  
  static Object s(int paramInt, Object[] paramArrayOfObject) {
    try {
      paramInt = ((paramInt + -1646288121 ^ h) - -599016815 ^ 0x4984D61E) - G;
      int i = paramInt >>> 16;
      paramInt &= 0xFFFF;
      Class clazz = T(paramInt, i);
      Constructor constructor = (Constructor)v[paramInt];
      if (constructor == null) {
        int j = B[paramInt];
        Constructor[] arrayOfConstructor = (Constructor[])clazz.getDeclaredConstructors();
        for (Constructor constructor1 : arrayOfConstructor) {
          int k = i * 31 + 40;
          Class[] arrayOfClass = constructor1.getParameterTypes();
          for (byte b = 0; b < arrayOfClass.length; b++) {
            Class clazz1 = arrayOfClass[b];
            if (b != 0)
              k = 31 * k + 44; 
            k = 31 * k + clazz1.getName().hashCode();
          } 
          k = 31 * k + 41;
          k = 31 * k + i;
          if (j == k) {
            constructor1.setAccessible(true);
            v[paramInt] = constructor1;
            constructor = constructor1;
            break;
          } 
        } 
      } 
      if (constructor == null)
        throw new NoSuchMethodError(Integer.toString(paramInt)); 
      return constructor.newInstance(paramArrayOfObject);
    } catch (InvocationTargetException invocationTargetException) {
      throw invocationTargetException.getTargetException();
    } 
  }
  
  private static Field r(int paramInt) throws Throwable {
    paramInt = ((paramInt + -1646288121 ^ h) - -599016815 ^ 0x4984D61E) - G;
    int i = paramInt >>> 16;
    paramInt &= 0xFFFF;
    Class clazz1 = T(paramInt, i);
    Class clazz2 = clazz1;
    Field field = (Field)v[paramInt];
    if (field != null)
      return field; 
    int j = B[paramInt];
    while (clazz1 != null) {
      Field[] arrayOfField = clazz1.getDeclaredFields();
      for (Field field1 : arrayOfField) {
        int k = 31 * i + field1.getName().hashCode();
        k = 31 * k + 58;
        k = 31 * k + field1.getType().getName().hashCode();
        k = 31 * k + i;
        if (j == k) {
          field1.setAccessible(true);
          v[paramInt] = field1;
          return field1;
        } 
      } 
      clazz1 = clazz1.getSuperclass();
    } 
    for (clazz1 = clazz2; clazz1 != null; clazz1 = clazz1.getSuperclass()) {
      Class[] arrayOfClass = clazz1.getInterfaces();
      for (Class clazz : arrayOfClass) {
        Field[] arrayOfField = clazz.getFields();
        for (Field field1 : arrayOfField) {
          int k = 31 * i + field1.getName().hashCode();
          k = 31 * k + 58;
          k = 31 * k + field1.getType().getName().hashCode();
          k = 31 * k + i;
          if (j == k) {
            field1.setAccessible(true);
            v[paramInt] = field1;
            return field1;
          } 
        } 
        clazz1 = clazz1.getSuperclass();
      } 
    } 
    return null;
  }
  
  static Object Z(Object paramObject, int paramInt) {
    Field field = r(paramInt);
    if (field == null)
      throw new NoSuchFieldError(Integer.toString(paramInt)); 
    return field.get(paramObject);
  }
  
  static void f(Object paramObject1, int paramInt, Object paramObject2) throws Throwable {
    Field field = r(paramInt);
    if (field == null)
      throw new NoSuchFieldError(Integer.toString(paramInt)); 
    field.set(paramObject1, paramObject2);
  }
  
  static Object g(int paramInt) {
    return Z(null, paramInt);
  }
  
  static void o(int paramInt, Object paramObject) {
    f(null, paramInt, paramObject);
  }
  
  static {
    "?䰋饋慁骾橏졇䮼㩎".toCharArray()[2] = (char)("?䰋饋慁骾橏졇䮼㩎".toCharArray()[2] ^ 0x296F);
  }
  
  static {
    "혚㉴喟信鼉䚼в萢쒕㳝".toCharArray()[8] = (char)("혚㉴喟信鼉䚼в萢쒕㳝".toCharArray()[8] ^ 0x3537);
  }
}


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\essentials\lib\sv.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */