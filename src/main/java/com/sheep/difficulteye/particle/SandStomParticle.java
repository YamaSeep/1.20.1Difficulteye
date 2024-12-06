package com.sheep.difficulteye.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;

public class SandStomParticle extends TextureSheetParticle {
    protected SandStomParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);
        this.gravity = -0.05F;
        this.friction = 0.9F;
        this.quadSize = 0.1F * (this.random.nextFloat() * this.random.nextFloat() * 6.0F + 1.0F);
        this.lifetime = (int)(16.0 / ((double)this.random.nextFloat() * 0.8 + 0.2)) + 2;
        this.xd = xd + (Math.random() * 2.0 - 1.0) * 0.05000000074505806;
        this.yd = yd + (Math.random() * 2.0 - 1.0) * 0.05000000074505806;
        this.zd = zd + (Math.random() * 2.0 - 1.0) * 0.05000000074505806;
        float $$8 = this.random.nextFloat() * 0.3F + 0.7F;
        this.rCol = $$8;
        this.gCol = $$8;
        this.bCol = $$8;
    }

    public void tick() {
        super.tick();
        if (this.lifetime < 10) {
            this.alpha = (float) this.lifetime / 10.0f;
        }
    }
    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }
}
