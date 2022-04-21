package me.oringo.oringoclient.mixins.renderer;

import net.minecraft.client.renderer.entity.Render;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({Render.class})
public abstract class RenderMixin {
  @Shadow
  public <T extends net.minecraft.entity.Entity> void func_76986_a(T entity, double x, double y, double z, float entityYaw, float partialTicks) {}
}


/* Location:              C:\Users\SamzyDev\Desktop\OringoClient_Suporter-1.7.1.jar!\me\oringo\oringoclient\mixins\renderer\RenderMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */