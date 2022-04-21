package com.jagrosh.discordipc;

import com.jagrosh.discordipc.entities.Packet;
import com.jagrosh.discordipc.entities.User;
import org.json.JSONObject;

public interface IPCListener {
  default void onPacketSent(IPCClient client, Packet packet) {}
  
  default void onPacketReceived(IPCClient client, Packet packet) {}
  
  default void onActivityJoin(IPCClient client, String secret) {}
  
  default void onActivitySpectate(IPCClient client, String secret) {}
  
  default void onActivityJoinRequest(IPCClient client, String secret, User user) {}
  
  default void onReady(IPCClient client) {}
  
  default void onClose(IPCClient client, JSONObject json) {}
  
  default void onDisconnect(IPCClient client, Throwable t) {}
}


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\com\jagrosh\discordipc\IPCListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */