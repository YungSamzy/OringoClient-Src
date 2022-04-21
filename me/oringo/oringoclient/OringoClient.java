/*     */ package me.oringo.oringoclient;
/*     */ import com.google.gson.Gson;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.lang.reflect.Field;
/*     */ import java.net.URL;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.file.Files;
/*     */ import java.security.KeyManagementException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.cert.CertificateException;
/*     */ import java.security.cert.X509Certificate;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.net.ssl.HostnameVerifier;
/*     */ import javax.net.ssl.HttpsURLConnection;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import javax.net.ssl.TrustManager;
/*     */ import javax.net.ssl.X509TrustManager;
/*     */ import me.oringo.oringoclient.commands.CheckNameCommand;
/*     */ import me.oringo.oringoclient.commands.FarmingMacro;
/*     */ import me.oringo.oringoclient.commands.JerryBoxMacro;
/*     */ import me.oringo.oringoclient.commands.WardrobeCommand;
/*     */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*     */ import me.oringo.oringoclient.qolfeatures.module.combat.Hitboxes;
/*     */ import me.oringo.oringoclient.qolfeatures.module.combat.KillAura;
/*     */ import me.oringo.oringoclient.qolfeatures.module.combat.NoHitDelay;
/*     */ import me.oringo.oringoclient.qolfeatures.module.combat.NoSlow;
/*     */ import me.oringo.oringoclient.qolfeatures.module.macro.AOTVReturn;
/*     */ import me.oringo.oringoclient.qolfeatures.module.macro.AutoSumoBot;
/*     */ import me.oringo.oringoclient.qolfeatures.module.macro.MithrilMacro;
/*     */ import me.oringo.oringoclient.qolfeatures.module.other.Derp;
/*     */ import me.oringo.oringoclient.qolfeatures.module.other.Modless;
/*     */ import me.oringo.oringoclient.qolfeatures.module.other.SBESharingModule;
/*     */ import me.oringo.oringoclient.qolfeatures.module.player.FastBreak;
/*     */ import me.oringo.oringoclient.qolfeatures.module.player.Speed;
/*     */ import me.oringo.oringoclient.qolfeatures.module.render.AnimationCreator;
/*     */ import me.oringo.oringoclient.qolfeatures.module.render.Camera;
/*     */ import me.oringo.oringoclient.qolfeatures.module.render.FreeCam;
/*     */ import me.oringo.oringoclient.qolfeatures.module.render.Giants;
/*     */ import me.oringo.oringoclient.qolfeatures.module.render.RichPresenceModule;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.DestroyBlockProgress;
/*     */ import net.minecraft.client.renderer.texture.DynamicTexture;
/*     */ import net.minecraft.command.ICommand;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.ClientCommandHandler;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.fml.common.Mod.EventHandler;
/*     */ 
/*     */ @Mod(modid = "examplemod", dependencies = "before:*", version = "1.7.1")
/*     */ public class OringoClient {
/*  61 */   public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<>();
/*  62 */   public static Gui clickGui = new Gui();
/*  63 */   public static KillAura killAura = new KillAura();
/*  64 */   public static Velocity velocity = new Velocity();
/*  65 */   public static Aimbot bloodAimbot = new Aimbot();
/*  66 */   public static Modless modless = new Modless();
/*  67 */   public static NoHitDelay noHitDelay = new NoHitDelay();
/*  68 */   public static NoSlow noSlow = new NoSlow();
/*  69 */   public static Sprint sprint = new Sprint();
/*  70 */   public static Reach reach = new Reach();
/*  71 */   public static AutoSumoBot autoSumo = new AutoSumoBot();
/*  72 */   public static FastBreak fastBreak = new FastBreak();
/*  73 */   public static AOTVReturn aotvReturn = new AOTVReturn();
/*  74 */   public static NickHider nickHider = new NickHider();
/*  75 */   public static Animations animations = new Animations();
/*  76 */   public static AnimationCreator animationCreator = new AnimationCreator();
/*  77 */   public static Camera camera = new Camera();
/*  78 */   public static MithrilMacro mithrilMacro = new MithrilMacro();
/*  79 */   public static Derp derp = new Derp();
/*  80 */   public static Speed speed = new Speed();
/*  81 */   public static SBESharingModule sbeShare = new SBESharingModule();
/*  82 */   public static Hitboxes hitboxes = new Hitboxes();
/*  83 */   public static NoRotate noRotate = new NoRotate();
/*  84 */   public static Phase phase = new Phase();
/*  85 */   public static FreeCam freeCam = new FreeCam();
/*  86 */   public static Giants giants = new Giants();
/*     */   
/*     */   public static boolean shouldUpdate;
/*     */   
/*     */   public static String[] vers;
/*  91 */   public static final Minecraft mc = Minecraft.func_71410_x();
/*  92 */   public static ArrayList<BlockPos> stop = new ArrayList<>();
/*     */   public static final String MODID = "examplemod";
/*     */   public static final String PREFIX = "§8[§bOringoClient§8] §7";
/*     */   public static final String VERSION = "1.7.1";
/*     */   public static boolean devMode = false;
/*  97 */   public static ArrayList<Runnable> scheduledTasks = new ArrayList<>();
/*     */   
/*  99 */   public static HashMap<String, ResourceLocation> capes = new HashMap<>();
/* 100 */   public static HashMap<File, ResourceLocation> capesLoaded = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onPre(FMLPreInitializationEvent event) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onInit(FMLPreInitializationEvent event) {
/* 117 */     (new File(mc.field_71412_D.getPath() + "/config/OringoClient")).mkdir();
/* 118 */     (new File(mc.field_71412_D.getPath() + "/config/OringoClient/capes")).mkdir();
/* 119 */     modless.setToggled(true);
/* 120 */     modules.add(new AntiVoid());
/* 121 */     modules.add(clickGui);
/* 122 */     modules.add(killAura);
/* 123 */     modules.add(noRotate);
/* 124 */     modules.add(velocity);
/* 125 */     modules.add(bloodAimbot);
/* 126 */     modules.add(sbeShare);
/* 127 */     modules.add(new AntiNicker());
/* 128 */     modules.add(new TerminalAura());
/* 129 */     modules.add(new AutoBypassRacism());
/* 130 */     modules.add(new TerminatorAura());
/* 131 */     modules.add(new SecretAura());
/* 132 */     modules.add(new DungeonESP());
/* 133 */     modules.add(new SafeWalk());
/* 134 */     modules.add(new RemoveAnnoyingMobs());
/* 135 */     modules.add(new GhostBlocks());
/* 136 */     modules.add(new SumoFences());
/* 137 */     modules.add(modless);
/* 138 */     modules.add(noHitDelay);
/* 139 */     modules.add(new TntRunPing());
/* 140 */     modules.add(noSlow);
/* 141 */     modules.add(sprint);
/* 142 */     modules.add(reach);
/* 143 */     modules.add(new AutoS1());
/* 144 */     modules.add(new InvManager());
/* 145 */     modules.add(new ChestStealer());
/* 146 */     modules.add(new PlayerESP());
/* 147 */     modules.add(autoSumo);
/* 148 */     modules.add(fastBreak);
/* 149 */     modules.add(nickHider);
/* 150 */     modules.add(new ChinaHat());
/* 151 */     modules.add(aotvReturn = new AOTVReturn());
/* 152 */     modules.add(mithrilMacro);
/* 153 */     RichPresenceModule richPresence = new RichPresenceModule();
/* 154 */     modules.add(richPresence);
/* 155 */     modules.add(new InventoryDisplay());
/* 156 */     modules.add(new CustomESP());
/* 157 */     modules.add(new TaraFly());
/* 158 */     modules.add(new AutoRogueSword());
/* 159 */     modules.add(new GuessTheBuildAFK());
/* 160 */     modules.add(new Snowballs());
/* 161 */     modules.add(new IceFillHelp());
/* 162 */     modules.add(animations);
/* 163 */     modules.add(ServerRotations.getInstance());
/* 164 */     modules.add(TargetHUD.getInstance());
/* 165 */     modules.add(new WTap());
/* 166 */     modules.add(new AutoTool());
/* 167 */     modules.add(camera);
/* 168 */     modules.add(new CustomInterfaces());
/* 169 */     modules.add(new ServerBeamer());
/* 170 */     modules.add(FastPlace.getInstance());
/* 171 */     modules.add(derp);
/* 172 */     modules.add(new Blink());
/* 173 */     modules.add(freeCam);
/* 174 */     modules.add(speed);
/* 175 */     modules.add(hitboxes);
/*     */     
/* 177 */     modules.add(new MetalDetector());
/* 178 */     modules.add(new MurdererFinder());
/*     */     
/* 180 */     modules.add(new ChestESP());
/* 181 */     modules.add(new Test());
/* 182 */     modules.add(new BoneThrower());
/* 183 */     modules.add(phase);
/* 184 */     modules.add(giants);
/*     */     
/* 186 */     loadKeybinds();
/* 187 */     BlurUtils.registerListener();
/*     */     
/* 189 */     for (Module m : modules) {
/* 190 */       MinecraftForge.EVENT_BUS.register(m);
/*     */     }
/* 192 */     JerryBoxMacro jerryBoxMacro = new JerryBoxMacro();
/* 193 */     ClientCommandHandler.instance.func_71560_a((ICommand)jerryBoxMacro);
/* 194 */     MinecraftForge.EVENT_BUS.register(jerryBoxMacro);
/* 195 */     ClientCommandHandler.instance.func_71560_a((ICommand)new CreateItem());
/* 196 */     WardrobeCommand wardrobeCommand = new WardrobeCommand();
/* 197 */     ClientCommandHandler.instance.func_71560_a((ICommand)wardrobeCommand);
/* 198 */     ClientCommandHandler.instance.func_71560_a((ICommand)new AotvTestCommand());
/* 199 */     MinecraftForge.EVENT_BUS.register(wardrobeCommand);
/* 200 */     MinecraftForge.EVENT_BUS.register(new Notifications());
/* 201 */     MinecraftForge.EVENT_BUS.register(this);
/* 202 */     ClientCommandHandler.instance.func_71560_a((ICommand)new SettingsCommand());
/* 203 */     ClientCommandHandler.instance.func_71560_a((ICommand)new DevModeCommand());
/* 204 */     ClientCommandHandler.instance.func_71560_a((ICommand)new StalkCommand());
/* 205 */     MinecraftForge.EVENT_BUS.register(new Updater());
/*     */     
/* 207 */     ClientCommandHandler.instance.func_71560_a((ICommand)new PlaceCrystalsCommand());
/* 208 */     ClientCommandHandler.instance.func_71560_a((ICommand)new BlockClickCommand());
/* 209 */     ClientCommandHandler.instance.func_71560_a((ICommand)new EntityClickCommand());
/* 210 */     ClientCommandHandler.instance.func_71560_a((ICommand)new SaveCommand());
/*     */     
/* 212 */     ClientCommandHandler.instance.func_71560_a((ICommand)new ArmorStandsCommand());
/* 213 */     CheckNameCommand checkNameCommand = new CheckNameCommand();
/* 214 */     ClientCommandHandler.instance.func_71560_a((ICommand)checkNameCommand);
/* 215 */     ClientCommandHandler.instance.func_71560_a((ICommand)new FireWork());
/* 216 */     ClientCommandHandler.instance.func_71560_a((ICommand)new ClipCommand());
/* 217 */     MinecraftForge.EVENT_BUS.register(new AttackQueue());
/* 218 */     MinecraftForge.EVENT_BUS.register(checkNameCommand);
/* 219 */     MinecraftForge.EVENT_BUS.register(new SkyblockUtils());
/*     */     FarmingMacro farmingMacro;
/* 221 */     ClientCommandHandler.instance.func_71560_a((ICommand)(farmingMacro = new FarmingMacro()));
/* 222 */     MinecraftForge.EVENT_BUS.register(farmingMacro);
/* 223 */     update();
/* 224 */     ConfigManager.loadConfig();
/* 225 */     if (richPresence.isToggled())
/* 226 */       richPresence.onEnable(); 
/* 227 */     loadCustomNames();
/* 228 */     if ((new File("OringoDev")).exists()) {
/* 229 */       OringoPacketLog.start();
/* 230 */       devMode = true;
/*     */     } 
/* 232 */     if (devMode) {
/* 233 */       ClientCommandHandler.instance.func_71560_a((ICommand)new BanCommand());
/* 234 */       ClientCommandHandler.instance.func_71560_a((ICommand)new PacketLoggerCommand());
/* 235 */       MinecraftForge.EVENT_BUS.register(new LoginWithSession());
/* 236 */       modules.add(animationCreator);
/*     */     } 
/*     */     
/* 239 */     Fonts.bootstrap();
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPost(FMLPostInitializationEvent event) {
/* 244 */     loadCapes();
/*     */   }
/*     */ 
/*     */   
/*     */   public static Map<Integer, DestroyBlockProgress> getBlockBreakProgress() {
/*     */     try {
/* 250 */       Field field_72738_e = RenderGlobal.class.getDeclaredField("field_72738_E");
/* 251 */       field_72738_e.setAccessible(true);
/* 252 */       return (Map<Integer, DestroyBlockProgress>)field_72738_e.get((Minecraft.func_71410_x()).field_71438_f);
/* 253 */     } catch (Exception exception) {
/* 254 */       return new HashMap<>();
/*     */     } 
/*     */   }
/*     */   public static void handleKeypress(int key) {
/* 258 */     if (key == 0)
/* 259 */       return;  for (Module m : modules) {
/* 260 */       if (m.getKeycode() == key && !m.isKeybind()) {
/* 261 */         m.toggle();
/* 262 */         if (!clickGui.disableNotifs.isEnabled()) Notifications.showNotification("Oringo Client", m.getName() + (m.isToggled() ? " enabled!" : " disabled!"), 1000); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void update() {
/*     */     try {
/* 269 */       vers = (new BufferedReader(new InputStreamReader((new URL("http://niger.5v.pl/version")).openStream()))).readLine().split(" ");
/* 270 */       if (!"1.7.1".equals(vers[0])) {
/* 271 */         shouldUpdate = true;
/*     */       }
/* 273 */     } catch (Exception e) {
/* 274 */       e.printStackTrace();
/* 275 */       System.out.println("Couldn't update");
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void loadCapes() {
/*     */     try {
/* 281 */       HashMap<String, String> capeData = (HashMap<String, String>)(new Gson()).fromJson(new InputStreamReader((new URL("http://niger.5v.pl/capes.txt")).openStream()), HashMap.class);
/* 282 */       capes.clear();
/* 283 */       if (capeData != null) {
/* 284 */         HashMap<String, ResourceLocation> cache = new HashMap<>();
/* 285 */         capeData.forEach((key, value) -> {
/*     */               try {
/*     */                 ResourceLocation capeFromCache = (ResourceLocation)cache.get(value);
/*     */                 if (capeFromCache == null) {
/*     */                   ResourceLocation cape;
/*     */                   File capeFile = new File(mc.field_71412_D.getPath() + "/config/OringoClient/capes/" + value + ".png");
/*     */                   if (!capeFile.exists()) {
/*     */                     InputStream in = (new URL("http://niger.5v.pl/capes/" + value + ".png")).openStream();
/*     */                     Files.copy(in, capeFile.toPath(), new java.nio.file.CopyOption[0]);
/*     */                   } 
/*     */                   if (capesLoaded.containsKey(capeFile)) {
/*     */                     cape = capesLoaded.get(capeFile);
/*     */                   } else {
/*     */                     cape = mc.func_110434_K().func_110578_a("oringoclient", new DynamicTexture(ImageIO.read(capeFile)));
/*     */                     capesLoaded.put(capeFile, cape);
/*     */                   } 
/*     */                   cache.put(value, cape);
/*     */                   capes.put(key, cape);
/*     */                 } else {
/*     */                   capes.put(key, cache.get(value));
/*     */                 } 
/* 306 */               } catch (Exception e) {
/*     */                 e.printStackTrace();
/*     */                 System.out.println("Error loading cape " + value);
/*     */               } 
/*     */             });
/* 311 */         if (devMode) {
/* 312 */           System.out.println((new Gson()).toJson(capeData));
/*     */         }
/*     */       } 
/* 315 */     } catch (Exception e) {
/* 316 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void loadCustomNames() {
/* 321 */     File killAuraNames = new File(mc.field_71412_D.getPath() + "/config/OringoClient/KillAura.cfg");
/* 322 */     File invManagerDropList = new File(mc.field_71412_D.getPath() + "/config/OringoClient/InventoryManager.cfg");
/* 323 */     File customESP = new File(mc.field_71412_D.getPath() + "config/OringoClient/CustomESP.cfg");
/*     */     try {
/* 325 */       if (!killAuraNames.exists()) { killAuraNames.createNewFile(); }
/*     */       else
/* 327 */       { DataInputStream dataInputStream = new DataInputStream(new FileInputStream(killAuraNames));
/* 328 */         int h = dataInputStream.readInt();
/* 329 */         for (int i = 0; i < h; i++) {
/* 330 */           KillAura.names.add(dataInputStream.readUTF());
/*     */         }
/* 332 */         dataInputStream.close(); }
/*     */     
/* 334 */     } catch (Exception exception) {}
/*     */     try {
/* 336 */       if (!invManagerDropList.exists()) { invManagerDropList.createNewFile(); }
/*     */       else
/* 338 */       { DataInputStream dataInputStream = new DataInputStream(new FileInputStream(invManagerDropList));
/* 339 */         int h = dataInputStream.readInt();
/* 340 */         for (int i = 0; i < h; i++) {
/* 341 */           InvManager.dropCustom.add(dataInputStream.readUTF());
/*     */         }
/* 343 */         dataInputStream.close(); }
/*     */     
/* 345 */     } catch (Exception exception) {}
/*     */     try {
/* 347 */       if (!customESP.exists()) { customESP.createNewFile(); }
/*     */       else
/* 349 */       { DataInputStream dataInputStream = new DataInputStream(new FileInputStream(customESP));
/* 350 */         int h = dataInputStream.readInt();
/* 351 */         for (int i = 0; i < h; i++) {
/* 352 */           CustomESP.names.add(dataInputStream.readUTF());
/*     */         }
/* 354 */         dataInputStream.close(); }
/*     */     
/* 356 */     } catch (Exception exception) {}
/*     */   }
/*     */   
/*     */   private static void loadKeybinds() {
/*     */     try {
/* 361 */       File oringoKeybinds = new File(mc.field_71412_D.getPath() + "/config/OringoClient/OringoKeybinds.cfg");
/* 362 */       if (!oringoKeybinds.exists()) {
/* 363 */         oringoKeybinds.createNewFile();
/*     */       } else {
/* 365 */         DataInputStream dataInputStream = new DataInputStream(new FileInputStream(mc.field_71412_D.getPath() + "/config/OringoClient/OringoKeybinds.cfg"));
/* 366 */         int h = dataInputStream.readInt();
/* 367 */         for (int i = 0; i < h; i++) {
/* 368 */           String name = dataInputStream.readUTF();
/* 369 */           modules.add(new Keybind(name));
/*     */         } 
/* 371 */         dataInputStream.close();
/*     */       } 
/* 373 */     } catch (Exception e) {
/* 374 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void disableSSLVerification() {
/* 380 */     TrustManager[] trustAllCerts = { new X509TrustManager()
/*     */         {
/*     */           public X509Certificate[] getAcceptedIssuers() {
/* 383 */             return null;
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
/*     */ 
/*     */ 
/*     */           
/*     */           public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
/*     */         } };
/* 395 */     SSLContext sc = null;
/*     */     try {
/* 397 */       sc = SSLContext.getInstance("SSL");
/* 398 */     } catch (NoSuchAlgorithmException e) {
/* 399 */       e.printStackTrace();
/*     */     } 
/*     */     try {
/* 402 */       sc.init(null, trustAllCerts, new SecureRandom());
/* 403 */     } catch (KeyManagementException e) {
/* 404 */       e.printStackTrace();
/*     */     } 
/* 406 */     HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
/*     */     
/* 408 */     HostnameVerifier validHosts = new HostnameVerifier()
/*     */       {
/*     */         public boolean verify(String arg0, SSLSession arg1) {
/* 411 */           return true;
/*     */         }
/*     */       };
/*     */     
/* 415 */     HttpsURLConnection.setDefaultHostnameVerifier(validHosts);
/*     */   }
/*     */ 
/*     */   
/*     */   private ByteBuffer readImageToBuffer(InputStream imageStream) throws IOException {
/* 420 */     BufferedImage bufferedimage = ImageIO.read(imageStream);
/* 421 */     int[] aint = bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), (int[])null, 0, bufferedimage.getWidth());
/* 422 */     ByteBuffer bytebuffer = ByteBuffer.allocate(4 * aint.length);
/*     */     
/* 424 */     for (int i : aint)
/*     */     {
/* 426 */       bytebuffer.putInt(i << 8 | i >> 24 & 0xFF);
/*     */     }
/*     */     
/* 429 */     bytebuffer.flip();
/* 430 */     return bytebuffer;
/*     */   }
/*     */   
/*     */   public static void sendMessage(String message) {
/* 434 */     (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(message));
/*     */   }
/*     */   
/*     */   public static void sendMessageWithPrefix(String message) {
/* 438 */     (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("§8[§bOringoClient§8] §7" + message));
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\OringoClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */