/*    */ package me.oringo.oringoclient.utils;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JTabbedPane;
/*    */ import javax.swing.JTextArea;
/*    */ 
/*    */ public class OringoPacketLog {
/*  7 */   private static JFrame frame = null;
/*  8 */   private static JTextArea in = null;
/*  9 */   private static JTextArea out = null;
/*    */   
/*    */   public static void start() {
/* 12 */     frame = new JFrame("Oringo Packet Log");
/* 13 */     frame.setAlwaysOnTop(true);
/* 14 */     frame.setSize(400, 200);
/* 15 */     JTabbedPane tabbedPane = new JTabbedPane();
/* 16 */     frame.add(tabbedPane);
/* 17 */     tabbedPane.addTab("In", in = new JTextArea());
/* 18 */     tabbedPane.addTab("Out", out = new JTextArea());
/* 19 */     frame.setVisible(true);
/*    */   }
/*    */   
/*    */   public static void logIn(String text) {
/* 23 */     in.setText(in.getText() + text + "\n");
/* 24 */     if (in.getText().length() - in.getText().replaceAll("\n", "").length() > 20) {
/* 25 */       in.setText(in.getText().substring(in.getText().indexOf("\n") + 1));
/*    */     }
/*    */   }
/*    */   
/*    */   public static void logOut(String text) {
/* 30 */     out.setText(out.getText() + text + "\n");
/* 31 */     if (out.getText().length() - out.getText().replaceAll("\n", "").length() > 20) {
/* 32 */       out.setText(out.getText().substring(out.getText().indexOf("\n") + 1));
/*    */     }
/*    */   }
/*    */   
/*    */   public static boolean isEnabled() {
/* 37 */     return (frame != null && frame.isVisible());
/*    */   }
/*    */   
/*    */   public static JFrame getFrame() {
/* 41 */     return frame;
/*    */   }
/*    */   
/*    */   public static JTextArea getIn() {
/* 45 */     return in;
/*    */   }
/*    */   
/*    */   public static JTextArea getOut() {
/* 49 */     return out;
/*    */   }
/*    */ }


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclien\\utils\OringoPacketLog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */