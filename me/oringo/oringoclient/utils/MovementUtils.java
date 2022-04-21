/*    */ package me.oringo.oringoclient.utils;
/*    */ 
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ 
/*    */ public class MovementUtils {
/*  6 */   public static MilliTimer strafeTimer = new MilliTimer();
/*    */   
/*    */   public static float getSpeed() {
/*  9 */     return (float)Math.sqrt(OringoClient.mc.field_71439_g.field_70159_w * OringoClient.mc.field_71439_g.field_70159_w + OringoClient.mc.field_71439_g.field_70179_y * OringoClient.mc.field_71439_g.field_70179_y);
/*    */   }
/*    */   public static float getSpeed(double x, double z) {
/* 12 */     return (float)Math.sqrt(x * x + z * z);
/*    */   }
/*    */   
/*    */   public static void strafe(boolean ignoreDelay) {
/* 16 */     strafe(getSpeed(), ignoreDelay);
/*    */   }
/*    */   
/*    */   public static boolean isMoving() {
/* 20 */     return (OringoClient.mc.field_71439_g != null && (OringoClient.mc.field_71439_g.field_70701_bs != 0.0F || OringoClient.mc.field_71439_g.field_70702_br != 0.0F));
/*    */   }
/*    */   
/*    */   public static boolean hasMotion() {
/* 24 */     return (OringoClient.mc.field_71439_g.field_70159_w != 0.0D && OringoClient.mc.field_71439_g.field_70179_y != 0.0D && OringoClient.mc.field_71439_g.field_70181_x != 0.0D);
/*    */   }
/*    */   
/*    */   public static void strafe(float speed, boolean ignoreDelay) {
/* 28 */     if (!isMoving() || (!strafeTimer.hasTimePassed(200L) && !ignoreDelay)) {
/*    */       return;
/*    */     }
/* 31 */     double yaw = getDirection();
/* 32 */     OringoClient.mc.field_71439_g.field_70159_w = -Math.sin(yaw) * speed;
/* 33 */     OringoClient.mc.field_71439_g.field_70179_y = Math.cos(yaw) * speed;
/* 34 */     strafeTimer.updateTime();
/*    */   }
/*    */   
/*    */   public static void strafe(float speed, float yaw) {
/* 38 */     if (!isMoving() || !strafeTimer.hasTimePassed(150L)) {
/*    */       return;
/*    */     }
/* 41 */     OringoClient.mc.field_71439_g.field_70159_w = -Math.sin(Math.toRadians(yaw)) * speed;
/* 42 */     OringoClient.mc.field_71439_g.field_70179_y = Math.cos(Math.toRadians(yaw)) * speed;
/* 43 */     strafeTimer.updateTime();
/*    */   }
/*    */   
/*    */   public static void forward(double length) {
/* 47 */     double yaw = Math.toRadians(OringoClient.mc.field_71439_g.field_70177_z);
/* 48 */     OringoClient.mc.field_71439_g.func_70107_b(OringoClient.mc.field_71439_g.field_70165_t + -Math.sin(yaw) * length, OringoClient.mc.field_71439_g.field_70163_u, OringoClient.mc.field_71439_g.field_70161_v + Math.cos(yaw) * length);
/*    */   }
/*    */   
/*    */   public static double getDirection() {
/* 52 */     return Math.toRadians(getYaw());
/*    */   }
/*    */   
/*    */   public static float getYaw() {
/* 56 */     float rotationYaw = OringoClient.mc.field_71439_g.field_70177_z;
/*    */     
/* 58 */     if (OringoClient.mc.field_71439_g.field_70701_bs < 0.0F) {
/* 59 */       rotationYaw += 180.0F;
/*    */     }
/* 61 */     float forward = 1.0F;
/* 62 */     if (OringoClient.mc.field_71439_g.field_70701_bs < 0.0F) {
/* 63 */       forward = -0.5F;
/* 64 */     } else if (OringoClient.mc.field_71439_g.field_70701_bs > 0.0F) {
/* 65 */       forward = 0.5F;
/*    */     } 
/* 67 */     if (OringoClient.mc.field_71439_g.field_70702_br > 0.0F) {
/* 68 */       rotationYaw -= 90.0F * forward;
/*    */     }
/* 70 */     if (OringoClient.mc.field_71439_g.field_70702_br < 0.0F) {
/* 71 */       rotationYaw += 90.0F * forward;
/*    */     }
/* 73 */     return rotationYaw;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclien\\utils\MovementUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */