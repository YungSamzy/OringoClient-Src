/*    */ package me.oringo.oringoclient.qolfeatures.module.other;
/*    */ 
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.File;
/*    */ import java.io.FileOutputStream;
/*    */ import me.oringo.oringoclient.qolfeatures.module.Module;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.Setting;
/*    */ import me.oringo.oringoclient.qolfeatures.module.settings.impl.StringSetting;
/*    */ 
/*    */ public class SBESharingModule
/*    */   extends Module {
/* 12 */   public StringSetting name = new StringSetting("Name");
/* 13 */   public StringSetting id = new StringSetting("UUID");
/*    */   
/*    */   public SBESharingModule() {
/* 16 */     super("SBE Sharing", Module.Category.OTHER);
/* 17 */     addSettings(new Setting[] { (Setting)this.name, (Setting)this.id });
/*    */   }
/*    */ 
/*    */   
/*    */   public void onSave() {
/* 22 */     File file = new File("sbeShare.txt");
/* 23 */     if (!file.exists()) {
/*    */       try {
/* 25 */         file.createNewFile();
/* 26 */       } catch (Exception exception) {}
/*    */     }
/*    */     try {
/* 29 */       DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
/* 30 */       out.writeBoolean(isToggled());
/* 31 */       out.writeUTF(this.name.getValue());
/* 32 */       out.writeUTF(this.id.getValue());
/* 33 */       out.close();
/* 34 */     } catch (Exception exception) {}
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\qolfeatures\module\other\SBESharingModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */