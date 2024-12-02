package com.sheep.difficulteye.entity.model;

import com.sheep.difficulteye.entity.custom.SummonersZombie;
import com.sheep.difficulteye.main.Difficulteye;
import net.minecraft.client.model.AbstractZombieModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.resources.ResourceLocation;

public class SummonersZombieModel extends AbstractZombieModel<SummonersZombie> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            new ResourceLocation(Difficulteye.MODID, "summoners_zombie"), "main");

    public SummonersZombieModel(ModelPart root) {
        super(root);
    }

    @Override
    public boolean isAggressive(SummonersZombie summonersZombie) {
        return summonersZombie.isAggressive();
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(new CubeDeformation(0.0F), 0.0F);
        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}