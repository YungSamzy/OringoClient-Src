/*    */ package me.oringo.oringoclient.utils;
/*    */ 
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import me.oringo.oringoclient.mixins.entity.PlayerSPAccessor;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.Vec3;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RotationUtils
/*    */ {
/*    */   public static float lastReportedPitch;
/*    */   
/*    */   public static float[] getAngles(Vec3 vec) {
/* 19 */     double diffX = vec.field_72450_a - OringoClient.mc.field_71439_g.field_70165_t;
/* 20 */     double diffY = vec.field_72448_b - OringoClient.mc.field_71439_g.field_70163_u + OringoClient.mc.field_71439_g.func_70047_e();
/* 21 */     double diffZ = vec.field_72449_c - OringoClient.mc.field_71439_g.field_70161_v;
/* 22 */     double dist = MathHelper.func_76133_a(diffX * diffX + diffZ * diffZ);
/* 23 */     float yaw = (float)(Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F;
/* 24 */     float pitch = (float)-(Math.atan2(diffY, dist) * 180.0D / Math.PI);
/* 25 */     return new float[] { OringoClient.mc.field_71439_g.field_70177_z + 
/* 26 */         MathHelper.func_76142_g(yaw - OringoClient.mc.field_71439_g.field_70177_z), OringoClient.mc.field_71439_g.field_70125_A + MathHelper.func_76142_g(pitch - OringoClient.mc.field_71439_g.field_70125_A) };
/*    */   }
/*    */ 
/*    */   
/*    */   public static float[] getServerAngles(Vec3 vec) {
/* 31 */     double diffX = vec.field_72450_a - OringoClient.mc.field_71439_g.field_70165_t;
/* 32 */     double diffY = vec.field_72448_b - OringoClient.mc.field_71439_g.field_70163_u + OringoClient.mc.field_71439_g.func_70047_e();
/* 33 */     double diffZ = vec.field_72449_c - OringoClient.mc.field_71439_g.field_70161_v;
/* 34 */     float yaw = (float)(Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F;
/* 35 */     double dist = MathHelper.func_76133_a(diffX * diffX + diffZ * diffZ);
/* 36 */     float pitch = (float)-(Math.atan2(diffY, dist) * 180.0D / Math.PI);
/* 37 */     return new float[] { ((PlayerSPAccessor)OringoClient.mc.field_71439_g)
/* 38 */         .getLastReportedYaw() + MathHelper.func_76142_g(yaw - ((PlayerSPAccessor)OringoClient.mc.field_71439_g).getLastReportedYaw()), ((PlayerSPAccessor)OringoClient.mc.field_71439_g)
/* 39 */         .getLastReportedPitch() + MathHelper.func_76142_g(pitch - ((PlayerSPAccessor)OringoClient.mc.field_71439_g).getLastReportedPitch()) };
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static float[] getBowAngles(Entity entity) {
/* 45 */     double xDelta = (entity.field_70165_t - entity.field_70142_S) * 0.4D;
/* 46 */     double zDelta = (entity.field_70161_v - entity.field_70136_U) * 0.4D;
/* 47 */     double d = OringoClient.mc.field_71439_g.func_70032_d(entity);
/* 48 */     d -= d % 0.8D;
/* 49 */     double xMulti = d / 0.8D * xDelta;
/* 50 */     double zMulti = d / 0.8D * zDelta;
/* 51 */     double x = entity.field_70165_t + xMulti - OringoClient.mc.field_71439_g.field_70165_t;
/* 52 */     double z = entity.field_70161_v + zMulti - OringoClient.mc.field_71439_g.field_70161_v;
/* 53 */     double y = OringoClient.mc.field_71439_g.field_70163_u + OringoClient.mc.field_71439_g.func_70047_e() - entity.field_70163_u + entity.func_70047_e();
/* 54 */     double dist = OringoClient.mc.field_71439_g.func_70032_d(entity);
/* 55 */     float yaw = (float)Math.toDegrees(Math.atan2(z, x)) - 90.0F;
/* 56 */     double d1 = MathHelper.func_76133_a(x * x + z * z);
/* 57 */     float pitch = (float)-(Math.atan2(y, d1) * 180.0D / Math.PI) + (float)dist * 0.11F;
/* 58 */     return new float[] { yaw, -pitch };
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isWithinFOV(EntityLivingBase entity, double fov) {
/* 72 */     float yawDifference = Math.abs(getAngles((Entity)entity)[0] - OringoClient.mc.field_71439_g.field_70177_z);
/* 73 */     return (yawDifference < fov && yawDifference > -fov);
/*    */   }
/*    */   
/*    */   public static float getYawDifference(EntityLivingBase entity1, EntityLivingBase entity2) {
/* 77 */     return Math.abs(getAngles((Entity)entity1)[0] - getAngles((Entity)entity2)[0]);
/*    */   }
/*    */   
/*    */   public static float getYawDifference(EntityLivingBase entity1) {
/* 81 */     return Math.abs(OringoClient.mc.field_71439_g.field_70177_z - getAngles((Entity)entity1)[0]);
/*    */   }
/*    */   
/*    */   public static boolean isWithinPitch(EntityLivingBase entity, double pitch) {
/* 85 */     float pitchDifference = Math.abs(getAngles((Entity)entity)[1] - OringoClient.mc.field_71439_g.field_70125_A);
/* 86 */     return (pitchDifference < pitch && pitchDifference > -pitch);
/*    */   }
/*    */   
/*    */   public static float[] getAngles(Entity en) {
/* 90 */     return getAngles(new Vec3(en.field_70165_t, en.field_70163_u + en.func_70047_e() - en.field_70131_O / 1.5D + 0.5D, en.field_70161_v));
/*    */   }
/*    */   
/*    */   public static float[] getServerAngles(Entity en) {
/* 94 */     return getServerAngles(new Vec3(en.field_70165_t, en.field_70163_u + en.func_70047_e() - en.field_70131_O / 1.5D + 0.5D, en.field_70161_v));
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclien\\utils\RotationUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */