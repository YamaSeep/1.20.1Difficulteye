package com.sheep.difficulteye.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sheep.difficulteye.entity.custom.HylightBlock;
import com.sheep.difficulteye.entity.model.HylightBlockmodel;
import com.sheep.difficulteye.main.Difficulteye;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Slime;

public class HylightBlockRenderer extends MobRenderer<HylightBlock, HylightBlockmodel<HylightBlock>> {
    private static final ResourceLocation HYLIGTBLOCK_LOCATION=new ResourceLocation(Difficulteye.MODID,"textures/entity/hylightblock.png");
    public HylightBlockRenderer(EntityRendererProvider.Context pcontext) {
        super(pcontext,
                new HylightBlockmodel<>(pcontext.bakeLayer(HylightBlockmodel.LAYER_LOCATION))
                ,0.25f);
    }
    public void render(HylightBlock p_115976_, float p_115977_, float p_115978_, PoseStack p_115979_, MultiBufferSource p_115980_, int p_115981_) {
        this.shadowRadius = 0.25F * (float)p_115976_.getSize();
        super.render(p_115976_, p_115977_, p_115978_, p_115979_, p_115980_, p_115981_);
    }

    protected void scale(HylightBlock p_115983_, PoseStack p_115984_, float p_115985_) {
        float $$3 = 0.999F;
        p_115984_.scale(0.999F, 0.999F, 0.999F);
        p_115984_.translate(0.0F, 0.001F, 0.0F);
        float $$4 = (float)p_115983_.getSize();
        float $$5 = Mth.lerp(p_115985_, p_115983_.oSquish, p_115983_.squish) / ($$4 * 0.5F + 1.0F);
        float $$6 = 1.0F / ($$5 + 1.0F);
        p_115984_.scale($$6 * $$4, 1.0F / $$6 * $$4, $$6 * $$4);
    }
    @Override
    public ResourceLocation getTextureLocation(HylightBlock hylightBlock) {
        return HYLIGTBLOCK_LOCATION;
    }
}
