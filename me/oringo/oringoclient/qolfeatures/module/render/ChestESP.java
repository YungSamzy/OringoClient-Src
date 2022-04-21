/*    */ package me.oringo.oringoclient.qolfeatures.module.render;
/*    */ 
/*    */ import java.util.stream.Collectors;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
/*    */ import me.oringo.oringoclient.utils.RenderUtils;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraftforge.client.event.RenderWorldLastEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class ChestESP
/*    */   extends Module {
/*    */   public BooleanSetting tracer;
/*    */   
/*    */   public ChestESP() {
/* 18 */     super("ChestESP", Module.Category.RENDER);
/*    */ 
/*    */     
/* 21 */     this.tracer = new BooleanSetting("Tracer", true);
/*    */     addSettings(new Setting[] { (Setting)this.tracer });
/*    */   } @SubscribeEvent
/*    */   public void onRender(RenderWorldLastEvent event) {
/* 25 */     if (!isToggled())
/* 26 */       return;  for (TileEntity tileEntityChest : OringoClient.mc.field_71441_e.field_147482_g.stream().filter(tileEntity -> tileEntity instanceof net.minecraft.tileentity.TileEntityChest).collect(Collectors.toList())) {
/* 27 */       RenderUtils.blockBox(tileEntityChest.func_174877_v(), OringoClient.clickGui.getColor());
/* 28 */       if (this.tracer.isEnabled()) RenderUtils.tracerLine(tileEntityChest.func_174877_v().func_177958_n() + 0.5D, tileEntityChest.func_174877_v().func_177956_o() + 0.5D, tileEntityChest.func_174877_v().func_177952_p() + 0.5D, OringoClient.clickGui.getColor()); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\render\ChestESP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */