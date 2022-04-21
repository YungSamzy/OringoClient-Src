package me.oringo.oringoclient.mixins.packet;

import net.minecraft.network.play.client.C03PacketPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({C03PacketPlayer.class})
public interface C03Accessor {
  @Accessor("x")
  void setX(double paramDouble);
  
  @Accessor("y")
  void setY(double paramDouble);
  
  @Accessor("z")
  void setZ(double paramDouble);
  
  @Accessor
  void setYaw(float paramFloat);
  
  @Accessor
  void setPitch(float paramFloat);
  
  @Accessor
  void setOnGround(boolean paramBoolean);
}


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\mixins\packet\C03Accessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */