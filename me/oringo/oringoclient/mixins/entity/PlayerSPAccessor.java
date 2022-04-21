package me.oringo.oringoclient.mixins.entity;

import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({EntityPlayerSP.class})
public interface PlayerSPAccessor {
  @Accessor
  double getLastReportedPosX();
  
  @Accessor
  void setLastReportedPosX(double paramDouble);
  
  @Accessor
  double getLastReportedPosY();
  
  @Accessor
  void setLastReportedPosY(double paramDouble);
  
  @Accessor
  double getLastReportedPosZ();
  
  @Accessor
  void setLastReportedPosZ(double paramDouble);
  
  @Accessor
  float getLastReportedYaw();
  
  @Accessor
  void setLastReportedYaw(float paramFloat);
  
  @Accessor
  float getLastReportedPitch();
  
  @Accessor
  void setLastReportedPitch(float paramFloat);
  
  @Accessor
  void setServerSprintState(boolean paramBoolean);
  
  @Accessor
  boolean getServerSprintState();
  
  @Accessor
  void setServerSneakState(boolean paramBoolean);
  
  @Accessor
  boolean getServerSneakState();
}


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\mixins\entity\PlayerSPAccessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */