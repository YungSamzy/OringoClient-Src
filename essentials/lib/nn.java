package essentials.lib;

import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import kfp.hdl.kz;

public class nn {
  private static volatile Object[] k;
  
  private static volatile kz kI = new kz(96);
  
  private static Object K(MethodHandles.Lookup paramLookup, String paramString1, MethodType paramMethodType, int paramInt, String paramString2, String paramString3, String paramString4) {
    MethodHandle methodHandle = null;
    try {
      Class<?> clazz = Class.forName(paramString2);
      ClassLoader classLoader = nn.class.getClassLoader();
      MethodType methodType = MethodType.fromMethodDescriptorString(paramString4, classLoader);
      switch (paramInt) {
        case 184:
          methodHandle = paramLookup.findStatic(clazz, paramString3, methodType);
          break;
        case 182:
        case 185:
          methodHandle = paramLookup.findVirtual(clazz, paramString3, methodType);
          break;
        default:
          throw new BootstrapMethodError();
      } 
      methodHandle = methodHandle.asType(paramMethodType);
    } catch (Exception exception) {
      throw new BootstrapMethodError();
    } 
    return new ConstantCallSite(methodHandle);
  }
  
  private static final int Y(int paramInt1, int paramInt2) {
    boolean bool = false;
    int i = paramInt1;
    int j = paramInt2;
    int k = j + i >> 24;
    bool = false;
  }
  
  private static final int e(byte[] paramArrayOfbyte, int paramInt) {
    boolean bool = false;
    int i = paramArrayOfbyte[10] << 16;
    bool = false;
  }
  
  private static final void s30142() {
    // Byte code:
    //   0: sipush #256
    //   3: newarray int
    //   5: astore_0
    //   6: sipush #256
    //   9: newarray byte
    //   11: astore_1
    //   12: sipush #256
    //   15: newarray int
    //   17: astore_2
    //   18: sipush #256
    //   21: newarray int
    //   23: astore_3
    //   24: sipush #256
    //   27: newarray int
    //   29: astore #4
    //   31: sipush #256
    //   34: newarray int
    //   36: astore #5
    //   38: bipush #30
    //   40: newarray int
    //   42: astore #6
    //   44: ldc2_w 9223372036854775807
    //   47: lstore #7
    //   49: iconst_0
    //   50: istore #9
    //   52: iconst_1
    //   53: istore #10
    //   55: iload #9
    //   57: sipush #256
    //   60: if_icmpge -> 94
    //   63: aload_0
    //   64: iload #9
    //   66: iload #10
    //   68: iastore
    //   69: iload #10
    //   71: iload #10
    //   73: iconst_1
    //   74: ishl
    //   75: iload #10
    //   77: bipush #7
    //   79: iushr
    //   80: sipush #283
    //   83: imul
    //   84: ixor
    //   85: ixor
    //   86: istore #10
    //   88: iinc #9, 1
    //   91: goto -> 55
    //   94: aload_1
    //   95: iconst_0
    //   96: bipush #99
    //   98: bastore
    //   99: iconst_0
    //   100: istore #9
    //   102: iload #9
    //   104: sipush #255
    //   107: if_icmpge -> 174
    //   110: aload_0
    //   111: sipush #255
    //   114: iload #9
    //   116: isub
    //   117: iaload
    //   118: istore #10
    //   120: iload #10
    //   122: iload #10
    //   124: bipush #8
    //   126: ishl
    //   127: ior
    //   128: istore #10
    //   130: iload #10
    //   132: iload #10
    //   134: iconst_4
    //   135: ishr
    //   136: iload #10
    //   138: iconst_5
    //   139: ishr
    //   140: ixor
    //   141: iload #10
    //   143: bipush #6
    //   145: ishr
    //   146: ixor
    //   147: iload #10
    //   149: bipush #7
    //   151: ishr
    //   152: ixor
    //   153: ixor
    //   154: istore #10
    //   156: aload_1
    //   157: aload_0
    //   158: iload #9
    //   160: iaload
    //   161: iload #10
    //   163: bipush #99
    //   165: ixor
    //   166: i2b
    //   167: bastore
    //   168: iinc #9, 1
    //   171: goto -> 102
    //   174: iconst_0
    //   175: istore #9
    //   177: iload #9
    //   179: sipush #256
    //   182: if_icmpge -> 301
    //   185: aload_1
    //   186: iload #9
    //   188: baload
    //   189: sipush #255
    //   192: iand
    //   193: istore #10
    //   195: iload #10
    //   197: iconst_1
    //   198: ishl
    //   199: iload #10
    //   201: bipush #7
    //   203: iushr
    //   204: sipush #283
    //   207: imul
    //   208: ixor
    //   209: istore #11
    //   211: iload #10
    //   213: iload #11
    //   215: ixor
    //   216: bipush #24
    //   218: ishl
    //   219: iload #10
    //   221: bipush #16
    //   223: ishl
    //   224: ixor
    //   225: iload #10
    //   227: bipush #8
    //   229: ishl
    //   230: ixor
    //   231: iload #11
    //   233: ixor
    //   234: istore #12
    //   236: iload #12
    //   238: iconst_m1
    //   239: iand
    //   240: istore #12
    //   242: aload_2
    //   243: iload #9
    //   245: iload #12
    //   247: iastore
    //   248: aload_3
    //   249: iload #9
    //   251: iload #12
    //   253: bipush #8
    //   255: ishl
    //   256: iload #12
    //   258: bipush #-8
    //   260: iushr
    //   261: ior
    //   262: iastore
    //   263: aload #4
    //   265: iload #9
    //   267: iload #12
    //   269: bipush #16
    //   271: ishl
    //   272: iload #12
    //   274: bipush #-16
    //   276: iushr
    //   277: ior
    //   278: iastore
    //   279: aload #5
    //   281: iload #9
    //   283: iload #12
    //   285: bipush #24
    //   287: ishl
    //   288: iload #12
    //   290: bipush #-24
    //   292: iushr
    //   293: ior
    //   294: iastore
    //   295: iinc #9, 1
    //   298: goto -> 177
    //   301: iconst_0
    //   302: istore #9
    //   304: iconst_1
    //   305: istore #10
    //   307: iload #9
    //   309: bipush #30
    //   311: if_icmpge -> 343
    //   314: aload #6
    //   316: iload #9
    //   318: iload #10
    //   320: iastore
    //   321: iload #10
    //   323: iconst_1
    //   324: ishl
    //   325: iload #10
    //   327: bipush #7
    //   329: iushr
    //   330: sipush #283
    //   333: imul
    //   334: ixor
    //   335: istore #10
    //   337: iinc #9, 1
    //   340: goto -> 307
    //   343: goto -> 348
    //   346: astore #9
    //   348: ldc 'ሐ坔䉈ㆋ바횘ᯫ통췀녋?쭮뫦籶豇ᇙ䚄粘섚ﾛ䫻ἠ䓗ퟀ萍➈癔厹㣂햼쫞?﷪简㨿褖闝色姭鬅앨빰崃찲릲熶㢏썼ⰸ䉵멘謌ﺀ鐥늈?Ặ䢏ੳ퉙ᤝ筺鹡ᛰ㲽뼞ﻭ糖툏楟魹᣾咳䜛蒓돇봔搴珮嚫聁찈싹?敔洡ꃾ㊞ȫㆎ㥤奰疇셩脤矘ᣟⱓ秮锂遚ဃ晣婒겴㒜놫朡䇺ꝰ穱䴥蓮ꆥ'
    //   350: <illegal opcode> hhmCxixCFfmok : (Ljava/lang/Object;)Ljava/lang/Object;
    //   355: checkcast [C
    //   358: astore #9
    //   360: aload #9
    //   362: arraylength
    //   363: iconst_2
    //   364: imul
    //   365: newarray byte
    //   367: astore #10
    //   369: iconst_0
    //   370: istore #11
    //   372: iload #11
    //   374: aload #9
    //   376: arraylength
    //   377: if_icmpge -> 424
    //   380: aload #10
    //   382: iload #11
    //   384: iconst_2
    //   385: imul
    //   386: aload #9
    //   388: iload #11
    //   390: caload
    //   391: sipush #255
    //   394: iand
    //   395: i2b
    //   396: bastore
    //   397: aload #10
    //   399: iload #11
    //   401: iconst_2
    //   402: imul
    //   403: iconst_1
    //   404: iadd
    //   405: aload #9
    //   407: iload #11
    //   409: caload
    //   410: ldc 65280
    //   412: iand
    //   413: bipush #8
    //   415: ishr
    //   416: i2b
    //   417: bastore
    //   418: iinc #11, 1
    //   421: goto -> 372
    //   424: ldc '㨞뭝횤᜾穌⁙鞗쨘瞣器﨏꒩ꏋ⃻贸멊촰㩜繰醓鷂鶈깰䉧癨恗͆㏅聄㖛㤑ᣔ錞⬩焟䥑⧄痢娬䊒懩䕉䉇擻牞쳿ꞯ쐽귤쁙䍌銊趣늨韁옾慴䁎胢倘懖蜁붊錳㎾㺑듭턮?⿩ౚ胁縨䮳䞵㷧ᰕ쏫?̿?扮뼋ᩋ퐹㮭?㚖ꮚ錌š芈钍♁懥ỵ뵓匁ꋰ섐넞七훱螪虖௴媒㬔灯쒾腫ꩶ掩'
    //   426: <illegal opcode> giEshcfEyzmyj : (Ljava/lang/Object;)Ljava/lang/Object;
    //   431: checkcast [C
    //   434: astore #9
    //   436: aload #9
    //   438: arraylength
    //   439: iconst_2
    //   440: imul
    //   441: newarray byte
    //   443: astore #11
    //   445: iconst_0
    //   446: istore #12
    //   448: iload #12
    //   450: aload #9
    //   452: arraylength
    //   453: if_icmpge -> 500
    //   456: aload #11
    //   458: iload #12
    //   460: iconst_2
    //   461: imul
    //   462: aload #9
    //   464: iload #12
    //   466: caload
    //   467: sipush #255
    //   470: iand
    //   471: i2b
    //   472: bastore
    //   473: aload #11
    //   475: iload #12
    //   477: iconst_2
    //   478: imul
    //   479: iconst_1
    //   480: iadd
    //   481: aload #9
    //   483: iload #12
    //   485: caload
    //   486: ldc 65280
    //   488: iand
    //   489: bipush #8
    //   491: ishr
    //   492: i2b
    //   493: bastore
    //   494: iinc #12, 1
    //   497: goto -> 448
    //   500: bipush #16
    //   502: newarray byte
    //   504: astore #12
    //   506: aload #12
    //   508: iconst_0
    //   509: aload #10
    //   511: aload #11
    //   513: sipush #47
    //   516: baload
    //   517: sipush #255
    //   520: iand
    //   521: baload
    //   522: bastore
    //   523: aload #12
    //   525: iconst_1
    //   526: aload #10
    //   528: aload #11
    //   530: sipush #102
    //   533: baload
    //   534: sipush #255
    //   537: iand
    //   538: baload
    //   539: bastore
    //   540: aload #12
    //   542: iconst_2
    //   543: aload #10
    //   545: aload #11
    //   547: sipush #66
    //   550: baload
    //   551: sipush #255
    //   554: iand
    //   555: baload
    //   556: bastore
    //   557: aload #12
    //   559: iconst_3
    //   560: aload #10
    //   562: aload #11
    //   564: sipush #185
    //   567: baload
    //   568: sipush #255
    //   571: iand
    //   572: baload
    //   573: bastore
    //   574: aload #12
    //   576: iconst_4
    //   577: aload #10
    //   579: aload #11
    //   581: sipush #175
    //   584: baload
    //   585: sipush #255
    //   588: iand
    //   589: baload
    //   590: bastore
    //   591: aload #12
    //   593: iconst_5
    //   594: aload #10
    //   596: aload #11
    //   598: sipush #74
    //   601: baload
    //   602: sipush #255
    //   605: iand
    //   606: baload
    //   607: bastore
    //   608: aload #12
    //   610: bipush #6
    //   612: aload #10
    //   614: aload #11
    //   616: sipush #192
    //   619: baload
    //   620: sipush #255
    //   623: iand
    //   624: baload
    //   625: bastore
    //   626: aload #12
    //   628: bipush #7
    //   630: aload #10
    //   632: aload #11
    //   634: sipush #213
    //   637: baload
    //   638: sipush #255
    //   641: iand
    //   642: baload
    //   643: bastore
    //   644: aload #12
    //   646: bipush #8
    //   648: aload #10
    //   650: aload #11
    //   652: sipush #165
    //   655: baload
    //   656: sipush #255
    //   659: iand
    //   660: baload
    //   661: bastore
    //   662: aload #12
    //   664: bipush #9
    //   666: aload #10
    //   668: aload #11
    //   670: sipush #141
    //   673: baload
    //   674: sipush #255
    //   677: iand
    //   678: baload
    //   679: bastore
    //   680: aload #12
    //   682: bipush #10
    //   684: aload #10
    //   686: aload #11
    //   688: sipush #26
    //   691: baload
    //   692: sipush #255
    //   695: iand
    //   696: baload
    //   697: bastore
    //   698: aload #12
    //   700: bipush #11
    //   702: aload #10
    //   704: aload #11
    //   706: sipush #17
    //   709: baload
    //   710: sipush #255
    //   713: iand
    //   714: baload
    //   715: bastore
    //   716: aload #12
    //   718: bipush #12
    //   720: aload #10
    //   722: aload #11
    //   724: sipush #245
    //   727: baload
    //   728: sipush #255
    //   731: iand
    //   732: baload
    //   733: bastore
    //   734: aload #12
    //   736: bipush #13
    //   738: aload #10
    //   740: aload #11
    //   742: sipush #4
    //   745: baload
    //   746: sipush #255
    //   749: iand
    //   750: baload
    //   751: bastore
    //   752: aload #12
    //   754: bipush #14
    //   756: aload #10
    //   758: aload #11
    //   760: sipush #138
    //   763: baload
    //   764: sipush #255
    //   767: iand
    //   768: baload
    //   769: bastore
    //   770: aload #12
    //   772: bipush #15
    //   774: aload #10
    //   776: aload #11
    //   778: sipush #219
    //   781: baload
    //   782: sipush #255
    //   785: iand
    //   786: baload
    //   787: bastore
    //   788: goto -> 1089
    //   791: astore #13
    //   793: aload #12
    //   795: bipush #8
    //   797: aload #10
    //   799: aload #11
    //   801: sipush #165
    //   804: baload
    //   805: sipush #255
    //   808: iand
    //   809: baload
    //   810: bastore
    //   811: aload #12
    //   813: bipush #9
    //   815: aload #10
    //   817: aload #11
    //   819: sipush #141
    //   822: baload
    //   823: sipush #255
    //   826: iand
    //   827: baload
    //   828: bastore
    //   829: aload #12
    //   831: bipush #10
    //   833: aload #10
    //   835: aload #11
    //   837: sipush #26
    //   840: baload
    //   841: sipush #255
    //   844: iand
    //   845: baload
    //   846: bastore
    //   847: aload #12
    //   849: bipush #11
    //   851: aload #10
    //   853: aload #11
    //   855: sipush #17
    //   858: baload
    //   859: sipush #255
    //   862: iand
    //   863: baload
    //   864: bastore
    //   865: aload #12
    //   867: bipush #12
    //   869: aload #10
    //   871: aload #11
    //   873: sipush #245
    //   876: baload
    //   877: sipush #255
    //   880: iand
    //   881: baload
    //   882: bastore
    //   883: aload #12
    //   885: bipush #13
    //   887: aload #10
    //   889: aload #11
    //   891: sipush #4
    //   894: baload
    //   895: sipush #255
    //   898: iand
    //   899: baload
    //   900: bastore
    //   901: aload #12
    //   903: bipush #14
    //   905: aload #10
    //   907: aload #11
    //   909: sipush #138
    //   912: baload
    //   913: sipush #255
    //   916: iand
    //   917: baload
    //   918: bastore
    //   919: aload #12
    //   921: bipush #15
    //   923: aload #10
    //   925: aload #11
    //   927: sipush #219
    //   930: baload
    //   931: sipush #255
    //   934: iand
    //   935: baload
    //   936: bastore
    //   937: goto -> 1089
    //   940: astore #14
    //   942: aload #12
    //   944: bipush #8
    //   946: aload #10
    //   948: aload #11
    //   950: sipush #165
    //   953: baload
    //   954: sipush #255
    //   957: iand
    //   958: baload
    //   959: bastore
    //   960: aload #12
    //   962: bipush #9
    //   964: aload #10
    //   966: aload #11
    //   968: sipush #141
    //   971: baload
    //   972: sipush #255
    //   975: iand
    //   976: baload
    //   977: bastore
    //   978: aload #12
    //   980: bipush #10
    //   982: aload #10
    //   984: aload #11
    //   986: sipush #26
    //   989: baload
    //   990: sipush #255
    //   993: iand
    //   994: baload
    //   995: bastore
    //   996: aload #12
    //   998: bipush #11
    //   1000: aload #10
    //   1002: aload #11
    //   1004: sipush #17
    //   1007: baload
    //   1008: sipush #255
    //   1011: iand
    //   1012: baload
    //   1013: bastore
    //   1014: aload #12
    //   1016: bipush #12
    //   1018: aload #10
    //   1020: aload #11
    //   1022: sipush #245
    //   1025: baload
    //   1026: sipush #255
    //   1029: iand
    //   1030: baload
    //   1031: bastore
    //   1032: aload #12
    //   1034: bipush #13
    //   1036: aload #10
    //   1038: aload #11
    //   1040: sipush #4
    //   1043: baload
    //   1044: sipush #255
    //   1047: iand
    //   1048: baload
    //   1049: bastore
    //   1050: aload #12
    //   1052: bipush #14
    //   1054: aload #10
    //   1056: aload #11
    //   1058: sipush #138
    //   1061: baload
    //   1062: sipush #255
    //   1065: iand
    //   1066: baload
    //   1067: bastore
    //   1068: aload #12
    //   1070: bipush #15
    //   1072: aload #10
    //   1074: aload #11
    //   1076: sipush #219
    //   1079: baload
    //   1080: sipush #255
    //   1083: iand
    //   1084: baload
    //   1085: bastore
    //   1086: aload #14
    //   1088: athrow
    //   1089: iconst_4
    //   1090: istore #13
    //   1092: iload #13
    //   1094: bipush #6
    //   1096: iadd
    //   1097: istore #15
    //   1099: iload #15
    //   1101: iconst_1
    //   1102: iadd
    //   1103: iconst_4
    //   1104: imul
    //   1105: newarray int
    //   1107: astore #16
    //   1109: iconst_0
    //   1110: istore #14
    //   1112: iconst_0
    //   1113: istore #17
    //   1115: iload #17
    //   1117: bipush #16
    //   1119: if_icmpge -> 1195
    //   1122: aload #16
    //   1124: iload #14
    //   1126: iconst_2
    //   1127: ishr
    //   1128: iconst_4
    //   1129: imul
    //   1130: iload #14
    //   1132: iadd
    //   1133: iconst_3
    //   1134: iand
    //   1135: aload #12
    //   1137: iload #17
    //   1139: baload
    //   1140: sipush #255
    //   1143: iand
    //   1144: aload #12
    //   1146: iload #17
    //   1148: iconst_1
    //   1149: iadd
    //   1150: baload
    //   1151: sipush #255
    //   1154: iand
    //   1155: bipush #8
    //   1157: ishl
    //   1158: ior
    //   1159: aload #12
    //   1161: iload #17
    //   1163: iconst_2
    //   1164: iadd
    //   1165: baload
    //   1166: sipush #255
    //   1169: iand
    //   1170: bipush #16
    //   1172: ishl
    //   1173: ior
    //   1174: aload #12
    //   1176: iload #17
    //   1178: iconst_3
    //   1179: iadd
    //   1180: baload
    //   1181: bipush #24
    //   1183: ishl
    //   1184: ior
    //   1185: iastore
    //   1186: iinc #17, 4
    //   1189: iinc #14, 1
    //   1192: goto -> 1115
    //   1195: goto -> 1200
    //   1198: astore #18
    //   1200: iload #15
    //   1202: iconst_1
    //   1203: iadd
    //   1204: iconst_2
    //   1205: ishl
    //   1206: istore #18
    //   1208: iload #13
    //   1210: istore #17
    //   1212: iload #17
    //   1214: iload #18
    //   1216: if_icmpge -> 1347
    //   1219: aload #16
    //   1221: iload #17
    //   1223: iconst_1
    //   1224: isub
    //   1225: iconst_2
    //   1226: ishr
    //   1227: iconst_4
    //   1228: imul
    //   1229: iload #17
    //   1231: iconst_1
    //   1232: isub
    //   1233: iconst_3
    //   1234: iand
    //   1235: iadd
    //   1236: iaload
    //   1237: istore #19
    //   1239: iload #17
    //   1241: iload #13
    //   1243: irem
    //   1244: ifne -> 1278
    //   1247: aload_1
    //   1248: iload #19
    //   1250: bipush #8
    //   1252: <illegal opcode> fxprpsuprpdt : (II)I
    //   1257: <illegal opcode> lmmcFdtautwoC : ([BI)I
    //   1262: aload #6
    //   1264: iload #17
    //   1266: iload #13
    //   1268: idiv
    //   1269: iconst_1
    //   1270: isub
    //   1271: iaload
    //   1272: ixor
    //   1273: istore #19
    //   1275: goto -> 1304
    //   1278: iload #13
    //   1280: bipush #6
    //   1282: if_icmple -> 1304
    //   1285: iload #17
    //   1287: iload #13
    //   1289: irem
    //   1290: iconst_4
    //   1291: if_icmpne -> 1304
    //   1294: aload_1
    //   1295: iload #19
    //   1297: <illegal opcode> cttcDfDhkvFkq : ([BI)I
    //   1302: istore #19
    //   1304: aload #16
    //   1306: iload #17
    //   1308: iconst_2
    //   1309: ishr
    //   1310: iconst_4
    //   1311: imul
    //   1312: iload #17
    //   1314: iconst_3
    //   1315: iand
    //   1316: iadd
    //   1317: aload #16
    //   1319: iload #17
    //   1321: iload #13
    //   1323: isub
    //   1324: iconst_2
    //   1325: ishr
    //   1326: iconst_4
    //   1327: imul
    //   1328: iload #17
    //   1330: iload #13
    //   1332: isub
    //   1333: iconst_3
    //   1334: iand
    //   1335: iadd
    //   1336: iaload
    //   1337: iload #19
    //   1339: ixor
    //   1340: iastore
    //   1341: iinc #17, 1
    //   1344: goto -> 1212
    //   1347: goto -> 1352
    //   1350: astore #17
    //   1352: iconst_4
    //   1353: newarray int
    //   1355: astore #17
    //   1357: aload #17
    //   1359: iconst_0
    //   1360: ldc -1200814575
    //   1362: i2l
    //   1363: lload #7
    //   1365: <illegal opcode> bjvhkyFoAkgde : ()J
    //   1370: lsub
    //   1371: bipush #63
    //   1373: lshr
    //   1374: lconst_1
    //   1375: land
    //   1376: lxor
    //   1377: l2i
    //   1378: iastore
    //   1379: aload #17
    //   1381: iconst_1
    //   1382: ldc -825914515
    //   1384: iastore
    //   1385: aload #17
    //   1387: iconst_2
    //   1388: ldc 1305595058
    //   1390: iastore
    //   1391: aload #17
    //   1393: iconst_3
    //   1394: ldc 1540058051
    //   1396: iastore
    //   1397: bipush #8
    //   1399: anewarray java/lang/Object
    //   1402: dup
    //   1403: iconst_0
    //   1404: aload_1
    //   1405: aastore
    //   1406: dup
    //   1407: iconst_1
    //   1408: aload_2
    //   1409: aastore
    //   1410: dup
    //   1411: iconst_2
    //   1412: aload_3
    //   1413: aastore
    //   1414: dup
    //   1415: iconst_3
    //   1416: aload #4
    //   1418: aastore
    //   1419: dup
    //   1420: iconst_4
    //   1421: aload #5
    //   1423: aastore
    //   1424: dup
    //   1425: iconst_5
    //   1426: aload #16
    //   1428: aastore
    //   1429: dup
    //   1430: bipush #6
    //   1432: aload #17
    //   1434: aastore
    //   1435: dup
    //   1436: <illegal opcode> plxAAuBtCgnjC : ([Ljava/lang/Object;)V
    //   1441: putstatic essentials/lib/nn.k : [Ljava/lang/Object;
    //   1444: return
    // Exception table:
    //   from	to	target	type
    //   99	343	346	java/lang/Exception
    //   506	644	791	java/lang/Exception
    //   506	644	940	finally
    //   791	793	940	finally
    //   940	942	940	finally
    //   1109	1347	1350	java/lang/Exception
    //   1115	1195	1198	java/lang/Exception
  }
  
  private static final void O(Object[] paramArrayOfObject) {
    Object[] arrayOfObject = paramArrayOfObject;
    boolean bool1 = false;
    boolean bool2 = false;
    bool2 = false;
  }
  
  static final String x(Object paramObject, short paramShort1, short paramShort2, int paramInt) {
    String str2 = new String((char[])paramObject);
    if (kI.get(str2) != null)
      return (String)kI.get(str2); 
    int n = ((char[])paramObject)[((char[])paramObject).length - 1] << 16 | paramShort1;
    boolean bool1 = false;
    boolean bool2 = false;
    if (k == null)
      s30142(); 
    StringBuilder stringBuilder = new StringBuilder();
    this();
    String str1 = Thread.currentThread().getStackTrace()[((Integer)k[7]).intValue()].getClassName();
    stringBuilder = stringBuilder.append(str1);
    str1 = Thread.currentThread().getStackTrace()[((Integer)k[7]).intValue()].getMethodName();
    int i = stringBuilder.append(str1).toString().hashCode();
    int[] arrayOfInt2 = (int[])k[6];
    paramInt = i ^ arrayOfInt2[0];
    int k = i ^ arrayOfInt2[1];
    int m = i ^ arrayOfInt2[2] ^ n;
    int j = i ^ arrayOfInt2[3];
    int[] arrayOfInt1 = (int[])k[5];
    int[] arrayOfInt3 = (int[])k[1];
    int[] arrayOfInt4 = (int[])k[2];
    int[] arrayOfInt5 = (int[])k[3];
    int[] arrayOfInt6 = (int[])k[4];
    byte[] arrayOfByte = (byte[])k[0];
    paramObject = paramObject;
    bool2 = false;
  }
}


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\essentials\lib\nn.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */