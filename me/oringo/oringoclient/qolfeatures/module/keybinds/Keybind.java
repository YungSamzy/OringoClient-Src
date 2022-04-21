/*     */ package me.oringo.oringoclient.qolfeatures.module.keybinds;
/*     */ 
/*     */ import com.mojang.realmsclient.gui.ChatFormatting;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.util.List;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.ModeSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.RunnableSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.StringSetting;
/*     */ import me.oringo.oringoclient.utils.MilliTimer;
/*     */ import me.oringo.oringoclient.utils.SkyblockUtils;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*     */ 
/*     */ public class Keybind
/*     */   extends Module {
/*  25 */   public ModeSetting button = new ModeSetting("Button", "Right", new String[] { "Right", "Left", "Swing" }); public ModeSetting mode = new ModeSetting("Mode", "Normal", new String[] { "Normal", "Rapid", "Toggle" });
/*     */ 
/*     */   
/*  28 */   public NumberSetting delay = new NumberSetting("Delay", 50.0D, 1.0D, 5000.0D, 1.0D);
/*  29 */   public StringSetting item = new StringSetting("Item");
/*     */   
/*  31 */   public RunnableSetting remove = new RunnableSetting("Remove keybinding", () -> {
/*     */         setToggled(false);
/*     */         OringoClient.modules.remove(this);
/*     */         MinecraftForge.EVENT_BUS.unregister(this);
/*     */       });
/*     */   
/*     */   private boolean wasPressed;
/*  38 */   private MilliTimer delayTimer = new MilliTimer();
/*     */   private boolean isEnabled;
/*     */   
/*     */   public Keybind(String name) {
/*  42 */     super(name, Module.Category.KEYBINDS);
/*  43 */     addSettings(new Setting[] { (Setting)this.item, (Setting)this.button, (Setting)this.mode, (Setting)this.delay, (Setting)this.remove });
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  48 */     return this.item.getValue().equals("") ? ("Keybind " + (Module.getModulesByCategory(Module.Category.KEYBINDS).indexOf(this) + 1)) : this.item.getValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isKeybind() {
/*  53 */     return true;
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onTick(TickEvent.ClientTickEvent event) {
/*  58 */     boolean keyPressed = isPressed();
/*  59 */     System.out.println(getName());
/*  60 */     if ((keyPressed || this.isEnabled) && isToggled() && !this.item.getValue().equals("") && OringoClient.mc.field_71462_r == null && 
/*  61 */       this.delayTimer.hasTimePassed((long)this.delay.getValue()) && (this.mode.is("Rapid") || !this.wasPressed || (this.mode.is("Toggle") && this.isEnabled))) {
/*  62 */       for (int i = 0; i < 9; i++) {
/*  63 */         if (OringoClient.mc.field_71439_g.field_71071_by.func_70301_a(i) != null && ChatFormatting.stripFormatting(OringoClient.mc.field_71439_g.field_71071_by.func_70301_a(i).func_82833_r()).toLowerCase().contains(this.item.getValue().toLowerCase())) {
/*  64 */           int held = OringoClient.mc.field_71439_g.field_71071_by.field_70461_c;
/*  65 */           OringoClient.mc.field_71439_g.field_71071_by.field_70461_c = i;
/*  66 */           SkyblockUtils.updateItemNoEvent();
/*  67 */           switch (this.button.getSelected()) {
/*     */             case "Left":
/*  69 */               SkyblockUtils.click();
/*     */               break;
/*     */             case "Right":
/*  72 */               OringoClient.mc.func_147114_u().func_147298_b().func_179290_a((Packet)new C08PacketPlayerBlockPlacement(OringoClient.mc.field_71439_g.func_70694_bm()));
/*     */               break;
/*     */             case "Swing":
/*  75 */               OringoClient.mc.field_71439_g.func_71038_i();
/*     */               break;
/*     */           } 
/*  78 */           OringoClient.mc.field_71439_g.field_71071_by.field_70461_c = held;
/*  79 */           SkyblockUtils.updateItemNoEvent();
/*  80 */           this.delayTimer.updateTime();
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/*  86 */     if (this.mode.is("Toggle") && !this.wasPressed && keyPressed && isToggled()) this.isEnabled = !this.isEnabled; 
/*  87 */     this.wasPressed = keyPressed;
/*     */   }
/*     */   
/*     */   public static void saveKeybinds() {
/*     */     try {
/*  92 */       File oringoKeybinds = new File(OringoClient.mc.field_71412_D.getPath() + "/config/OringoClient/OringoKeybinds.cfg");
/*  93 */       if (!oringoKeybinds.exists()) {
/*  94 */         oringoKeybinds.createNewFile();
/*     */       } else {
/*  96 */         DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(OringoClient.mc.field_71412_D.getPath() + "/config/OringoClient/OringoKeybinds.cfg"));
/*  97 */         List<Module> keybinds = Module.getModulesByCategory(Module.Category.KEYBINDS);
/*  98 */         dataOutputStream.writeInt(keybinds.size());
/*  99 */         for (Module keybind : keybinds) {
/* 100 */           dataOutputStream.writeUTF(keybind.name);
/*     */         }
/* 102 */         dataOutputStream.close();
/*     */       } 
/* 104 */     } catch (Exception e) {
/* 105 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\keybinds\Keybind.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */