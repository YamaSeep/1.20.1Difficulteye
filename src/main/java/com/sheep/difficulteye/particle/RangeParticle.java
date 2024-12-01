package com.sheep.difficulteye.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.data.models.model.TexturedModel;

public class RangeParticle extends TextureSheetParticle {
    protected RangeParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);
        this.lifetime = 1;
        this.quadSize = 0.1F; // サイズ
        this.rCol = 1.0F; // 赤
        this.gCol = 0.0F; // 緑
        this.bCol = 0.0F; // 青
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
