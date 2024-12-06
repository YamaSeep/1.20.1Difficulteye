package com.sheep.difficulteye.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;

public class RangeParticle extends TextureSheetParticle {
    protected RangeParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);
        this.lifetime = 1;
        this.quadSize = 0.1F;
        this.rCol= (float) xSpeed;
        this.gCol= (float) ySpeed;
        this.bCol= (float) zSpeed;

    }

    @Override
    public void tick() {
        super.tick();
        this.alpha = 1.0F;
        this.xd = 0.0;
        this.yd = 0.0;
        this.zd = 0.0;
    }
    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }
}
