/*     */ package me.oringo.oringoclient.qolfeatures.module.render;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import me.oringo.oringoclient.OringoClient;
/*     */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.ModeSetting;
/*     */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraftforge.client.event.RenderWorldLastEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class ChinaHat
/*     */   extends Module {
/*  18 */   public NumberSetting radius = new NumberSetting("Radius", 0.7D, 0.5D, 1.0D, 0.01D); public NumberSetting height = new NumberSetting("Height", 0.3D, 0.10000000149011612D, 0.699999988079071D, 0.01D); public NumberSetting pos = new NumberSetting("Position", 0.1D, -0.5D, 0.5D, 0.01D); public NumberSetting rotation = new NumberSetting("Rotation", 5.0D, 0.0D, 5.0D, 0.1D); public NumberSetting angles = new NumberSetting("Angles", 8.0D, 4.0D, 90.0D, 1.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  23 */   public BooleanSetting firstPerson = new BooleanSetting("Show in first person", false);
/*     */   
/*  25 */   public ModeSetting colorMode = new ModeSetting("Color mode", "Rainbow", new String[] { "Rainbow", "Gradient", "Single" });
/*     */   
/*  27 */   public NumberSetting red = new NumberSetting("Red", 0.0D, 0.0D, 255.0D, 1.0D)
/*     */     {
/*     */       public boolean isHidden() {
/*  30 */         return !ChinaHat.this.colorMode.is("Single");
/*     */       }
/*     */     };
/*  33 */   public NumberSetting green = new NumberSetting("Green", 80.0D, 0.0D, 255.0D, 1.0D)
/*     */     {
/*     */       public boolean isHidden() {
/*  36 */         return !ChinaHat.this.colorMode.is("Single");
/*     */       }
/*     */     };
/*  39 */   public NumberSetting blue = new NumberSetting("Blue", 255.0D, 0.0D, 255.0D, 1.0D)
/*     */     {
/*     */       public boolean isHidden() {
/*  42 */         return !ChinaHat.this.colorMode.is("Single");
/*     */       }
/*     */     };
/*     */   
/*  46 */   public NumberSetting redGradient1 = new NumberSetting("Red 1", 255.0D, 0.0D, 255.0D, 1.0D)
/*     */     {
/*     */       public boolean isHidden() {
/*  49 */         return !ChinaHat.this.colorMode.is("Gradient");
/*     */       }
/*     */     };
/*  52 */   public NumberSetting greenGradient1 = new NumberSetting("Green 1", 0.0D, 0.0D, 255.0D, 1.0D)
/*     */     {
/*     */       public boolean isHidden() {
/*  55 */         return !ChinaHat.this.colorMode.is("Gradient");
/*     */       }
/*     */     };
/*  58 */   public NumberSetting blueGradient1 = new NumberSetting("Blue 1", 255.0D, 0.0D, 255.0D, 1.0D)
/*     */     {
/*     */       public boolean isHidden() {
/*  61 */         return !ChinaHat.this.colorMode.is("Gradient");
/*     */       }
/*     */     };
/*  64 */   public NumberSetting redGradient2 = new NumberSetting("Red 2", 90.0D, 0.0D, 255.0D, 1.0D)
/*     */     {
/*     */       public boolean isHidden() {
/*  67 */         return !ChinaHat.this.colorMode.is("Gradient");
/*     */       }
/*     */     };
/*  70 */   public NumberSetting greenGradient2 = new NumberSetting("Green 2", 10.0D, 0.0D, 255.0D, 1.0D)
/*     */     {
/*     */       public boolean isHidden() {
/*  73 */         return !ChinaHat.this.colorMode.is("Gradient");
/*     */       }
/*     */     };
/*  76 */   public NumberSetting blueGradient2 = new NumberSetting("Blue 2", 255.0D, 0.0D, 255.0D, 1.0D)
/*     */     {
/*     */       public boolean isHidden() {
/*  79 */         return !ChinaHat.this.colorMode.is("Gradient");
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */   
/*     */   public ChinaHat() {
/*  86 */     super("China hat", 0, Module.Category.RENDER);
/*  87 */     addSettings(new Setting[] { (Setting)this.radius, (Setting)this.height, (Setting)this.firstPerson, (Setting)this.rotation, (Setting)this.pos, (Setting)this.angles, (Setting)this.colorMode, (Setting)this.red, (Setting)this.green, (Setting)this.blue, (Setting)this.redGradient1, (Setting)this.greenGradient1, (Setting)this.blueGradient1, (Setting)this.redGradient2, (Setting)this.greenGradient2, (Setting)this.blueGradient2 });
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRender(RenderWorldLastEvent event) {
/*  92 */     if (isToggled() && (OringoClient.mc.field_71474_y.field_74320_O != 0 || this.firstPerson.isEnabled())) {
/*  93 */       drawChinaHat((EntityLivingBase)OringoClient.mc.field_71439_g, event.partialTicks);
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawChinaHat(EntityLivingBase entity, float partialTicks) {
/*  98 */     GL11.glPushMatrix();
/*  99 */     GL11.glBlendFunc(770, 771);
/* 100 */     GL11.glEnable(3042);
/* 101 */     GL11.glShadeModel(7425);
/* 102 */     GL11.glDisable(3553);
/* 103 */     GL11.glDisable(2884);
/* 104 */     GL11.glTranslated(entity.field_70142_S + (entity.field_70165_t - entity.field_70142_S) * partialTicks - (OringoClient.mc.func_175598_ae()).field_78730_l, entity.field_70137_T + (entity.field_70163_u - entity.field_70137_T) * partialTicks - 
/* 105 */         (OringoClient.mc.func_175598_ae()).field_78731_m + entity.field_70131_O + (entity.func_70093_af() ? (this.pos.getValue() - 0.2D) : this.pos.getValue()), entity.field_70136_U + (entity.field_70161_v - entity.field_70136_U) * partialTicks - 
/* 106 */         (OringoClient.mc.func_175598_ae()).field_78728_n);
/* 107 */     GL11.glRotatef((float)((entity.field_70173_aa + partialTicks) * this.rotation.getValue()), 0.0F, 1.0F, 0.0F);
/*     */     
/* 109 */     double radius = this.radius.getValue();
/* 110 */     GL11.glBegin(3);
/* 111 */     for (int i = 0; i <= this.angles.getValue(); i++) {
/* 112 */       Color color = getColor(i, (int)this.angles.getValue(), false);
/* 113 */       GL11.glColor4f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, 1.0F);
/*     */       
/* 115 */       GL11.glVertex3d(Math.cos(i * Math.PI / this.angles.getValue() / 2.0D) * radius, 0.0D, Math.sin(i * Math.PI / this.angles.getValue() / 2.0D) * radius);
/*     */     } 
/* 117 */     GL11.glEnd();
/*     */     
/* 119 */     GL11.glBegin(6);
/* 120 */     Color c1 = getColor(0.0D, this.angles.getValue(), true);
/* 121 */     GL11.glColor4f(c1.getRed() / 255.0F, c1.getGreen() / 255.0F, c1.getBlue() / 255.0F, 0.5F);
/* 122 */     GL11.glVertex3d(0.0D, this.height.getValue(), 0.0D);
/* 123 */     for (int j = 0; j <= this.angles.getValue(); j++) {
/* 124 */       Color color = getColor(j, (int)this.angles.getValue(), false);
/* 125 */       GL11.glColor4f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, 0.5F);
/*     */       
/* 127 */       GL11.glVertex3d(Math.cos(j * Math.PI / this.angles.getValue() / 2.0D) * radius, 0.0D, Math.sin(j * Math.PI / this.angles.getValue() / 2.0D) * radius);
/*     */     } 
/* 129 */     GL11.glVertex3d(0.0D, this.height.getValue(), 0.0D);
/* 130 */     GL11.glEnd();
/* 131 */     GL11.glLineWidth(2.0F);
/* 132 */     GL11.glShadeModel(7424);
/* 133 */     GL11.glEnable(2884);
/* 134 */     GlStateManager.func_179117_G();
/* 135 */     GL11.glEnable(3553);
/* 136 */     GL11.glDisable(3042);
/* 137 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public Color getColor(double i, double max, boolean first) {
/* 141 */     switch (this.colorMode.getSelected()) {
/*     */       case "Rainbow":
/* 143 */         return Color.getHSBColor((float)(i / max), 1.0F, 1.0F);
/*     */       case "Gradient":
/* 145 */         if (first) return new Color((int)this.redGradient1.getValue(), (int)this.greenGradient1.getValue(), (int)this.blueGradient1.getValue()); 
/* 146 */         return new Color((int)this.redGradient2.getValue(), (int)this.greenGradient2.getValue(), (int)this.blueGradient2.getValue());
/*     */     } 
/* 148 */     if (first) return (new Color((int)this.red.getValue(), (int)this.green.getValue(), (int)this.blue.getValue())).darker().darker(); 
/* 149 */     return new Color((int)this.red.getValue(), (int)this.green.getValue(), (int)this.blue.getValue());
/*     */   }
/*     */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\render\ChinaHat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */