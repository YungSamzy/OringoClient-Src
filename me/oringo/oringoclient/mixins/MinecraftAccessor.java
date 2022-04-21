package me.oringo.oringoclient.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Timer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({Minecraft.class})
public interface MinecraftAccessor {
  @Accessor
  Timer getTimer();
}


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\mixins\MinecraftAccessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */