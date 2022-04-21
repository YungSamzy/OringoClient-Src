/*    */ package me.oringo.oringoclient.utils;
/*    */ 
/*    */ import java.nio.FloatBuffer;
/*    */ import org.lwjgl.BufferUtils;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import org.lwjgl.util.vector.Matrix4f;
/*    */ import org.lwjgl.util.vector.Vector2f;
/*    */ import org.lwjgl.util.vector.Vector3f;
/*    */ import org.lwjgl.util.vector.Vector4f;
/*    */ 
/*    */ 
/*    */ public class WorldToScreen
/*    */ {
/*    */   public static Matrix4f getMatrix(int matrix) {
/* 15 */     FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(16);
/*    */     
/* 17 */     GL11.glGetFloat(matrix, floatBuffer);
/*    */     
/* 19 */     return (Matrix4f)(new Matrix4f()).load(floatBuffer);
/*    */   }
/*    */ 
/*    */   
/*    */   public static Vector2f worldToScreen(Vector3f pointInWorld, int screenWidth, int screenHeight) {
/* 24 */     return worldToScreen(pointInWorld, getMatrix(2982), getMatrix(2983), screenWidth, screenHeight);
/*    */   }
/*    */   
/*    */   public static Vector2f worldToScreen(Vector3f pointInWorld, Matrix4f view, Matrix4f projection, int screenWidth, int screenHeight) {
/* 28 */     Vector4f clipSpacePos = multiply(multiply(new Vector4f(pointInWorld.x, pointInWorld.y, pointInWorld.z, 1.0F), view), projection);
/*    */     
/* 30 */     Vector3f ndcSpacePos = new Vector3f(clipSpacePos.x / clipSpacePos.w, clipSpacePos.y / clipSpacePos.w, clipSpacePos.z / clipSpacePos.w);
/*    */ 
/*    */     
/* 33 */     float screenX = (ndcSpacePos.x + 1.0F) / 2.0F * screenWidth;
/* 34 */     float screenY = (1.0F - ndcSpacePos.y) / 2.0F * screenHeight;
/*    */ 
/*    */     
/* 37 */     if (ndcSpacePos.z < -1.0D || ndcSpacePos.z > 1.0D) {
/* 38 */       return null;
/*    */     }
/*    */     
/* 41 */     return new Vector2f(screenX, screenY);
/*    */   }
/*    */   
/*    */   public static Vector4f multiply(Vector4f vec, Matrix4f mat) {
/* 45 */     return new Vector4f(vec.x * mat.m00 + vec.y * mat.m10 + vec.z * mat.m20 + vec.w * mat.m30, vec.x * mat.m01 + vec.y * mat.m11 + vec.z * mat.m21 + vec.w * mat.m31, vec.x * mat.m02 + vec.y * mat.m12 + vec.z * mat.m22 + vec.w * mat.m32, vec.x * mat.m03 + vec.y * mat.m13 + vec.z * mat.m23 + vec.w * mat.m33);
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclien\\utils\WorldToScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */