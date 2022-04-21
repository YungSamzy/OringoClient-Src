/*    */ package me.oringo.oringoclient.utils;
/*    */ 
/*    */ import java.lang.reflect.Field;
/*    */ import java.util.ArrayList;
/*    */ import me.oringo.oringoclient.OringoClient;
/*    */ import net.minecraft.network.Packet;
/*    */ 
/*    */ 
/*    */ public class PacketUtils
/*    */ {
/* 11 */   public static ArrayList<Packet<?>> noEvent = new ArrayList<>();
/*    */   
/*    */   public static void sendPacketNoEvent(Packet<?> packet) {
/* 14 */     noEvent.add(packet);
/* 15 */     OringoClient.mc.func_147114_u().func_147298_b().func_179290_a(packet);
/*    */   }
/*    */   
/*    */   public static String packetToString(Packet<?> packet) {
/* 19 */     StringBuilder postfix = new StringBuilder();
/* 20 */     boolean first = true;
/* 21 */     for (Field field : packet.getClass().getDeclaredFields()) {
/* 22 */       field.setAccessible(true);
/*    */       try {
/* 24 */         postfix.append(first ? "" : ", ").append(field.get(packet));
/* 25 */       } catch (IllegalAccessException e) {
/* 26 */         e.printStackTrace();
/*    */       } 
/* 28 */       first = false;
/*    */     } 
/* 30 */     return packet.getClass().getSimpleName() + String.format("{%s}", new Object[] { postfix });
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclien\\utils\PacketUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */