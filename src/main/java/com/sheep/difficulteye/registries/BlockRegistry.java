package com.sheep.difficulteye.registries;

import com.sheep.difficulteye.blockInfo.*;
import com.sheep.difficulteye.main.Difficulteye;
import com.sheep.difficulteye.worldgen.tree.TerracottaTreeLime;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Difficulteye.MODID);
    public static final RegistryObject<Block>[] EndPortals = new RegistryObject[]{
            BLOCKS.register("endframe_artifact", Endframe_Artifact::new),
            BLOCKS.register("endframe_color", Endframe_Color::new),
            BLOCKS.register("endframe_creature", Endframe_Creature::new),
            BLOCKS.register("endframe_dungeon", Endframe_Dungeon::new),
            BLOCKS.register("endframe_element", Endframe_Element::new),
            BLOCKS.register("endframe_food", Endframe_Food::new),
            BLOCKS.register("endframe_magic", Endframe_Magic::new),
            BLOCKS.register("endframe_nature", Endframe_Nature::new),
            BLOCKS.register("endframe_potion", Endframe_Potion::new),
            BLOCKS.register("endframe_sea", Endframe_Sea::new),
            BLOCKS.register("endframe_season", Endframe_Season::new),
            BLOCKS.register("endframe_technology", Endframe_Technology::new),
    };
    public static final RegistryObject<Block> UnbreakPrismarine=BLOCKS.register("unbreakprismarine",()-> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(-1,1500)));
    public static final RegistryObject<Block> UnbreakPrismarineBricks = BLOCKS.register("unbreakprismarinebricks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DIAMOND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(-1, 1500)));
    public static final RegistryObject<Block> UnbreakDarkPrismarine = BLOCKS.register("unbreakdarkprismarine", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(-1, 1500)));
    public static final RegistryObject<Block> UnbreakSeaLantern = BLOCKS.register("unbreaksealantern", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.HAT).strength(-1,1500).sound(SoundType.GLASS).lightLevel((p_50856_) -> 15).isRedstoneConductor((blockState, blockGetter, blockPos) -> false)));

    public static final RegistryObject<Block> UnbreakDarkOakWood = BLOCKS.register("unbreakdarkoakwood", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASS).strength(-1, 1500).requiresCorrectToolForDrops().sound(SoundType.WOOD)));
    public static final RegistryObject<Block> UnbreakBirchWood = BLOCKS.register("unbreakbirchwood", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(-1, 1500).requiresCorrectToolForDrops().sound(SoundType.WOOD)));
    public static final RegistryObject<Block> UnbreakCobblestone = BLOCKS.register("unbreakcobblestone", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).strength(-1, 1500).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> UnbreakDarkOakLog = BLOCKS.register("unbreakdarkoaklog", () -> log(MapColor.COLOR_BROWN, MapColor.COLOR_BROWN));
    public static final RegistryObject<Block> UnbreakGlass = BLOCKS.register("unbreakglass", Unbreakglass::new);
    public static final RegistryObject<Block> UnbreakCobblestoneStairs = BLOCKS.register("unbreakcobblestone_stairs", () -> new StairBlock(Blocks.COBBLESTONE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.COBBLESTONE).strength(-1, 1500)));
    public static final RegistryObject<Block> UnbreakRedCarpet = BLOCKS.register("unbreakredcarpet", () -> new WoolCarpetBlock(DyeColor.RED,BlockBehaviour.Properties.copy(Blocks.RED_CARPET).strength(-1, 1500).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> UnbreakWhiteCarpet = BLOCKS.register("unbreakwhitecarpet", () -> new WoolCarpetBlock(DyeColor.WHITE,BlockBehaviour.Properties.copy(Blocks.WHITE_CARPET).strength(-1, 1500).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> UnbreakNetherbrick = BLOCKS.register("unbreaknetherbrick", () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHER_BRICKS).strength(-1,1500)));

    public static final RegistryObject<Block> TerracottaTreeLimeSapling =BLOCKS.register("terracottalimetreesapling",()->new SaplingBlock(new TerracottaTreeLime(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> TerracottaTreeGreenSapling =BLOCKS.register("terracottagreentreesapling",()->new SaplingBlock(new TerracottaTreeLime(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    private static RotatedPillarBlock log(MapColor p_285370_, MapColor p_285126_) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor((p_152624_) -> p_152624_.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? p_285370_ : p_285126_).instrument(NoteBlockInstrument.BASS).strength(-1,1500).sound(SoundType.WOOD));
    }
    public static final RegistryObject<Block>[] Monument = new RegistryObject[]{
            UnbreakPrismarine,UnbreakPrismarineBricks,UnbreakDarkPrismarine,UnbreakSeaLantern
    };
    public static final RegistryObject<Block>[] Mansion = new RegistryObject[]{
            UnbreakDarkOakWood,UnbreakBirchWood,UnbreakCobblestone,UnbreakDarkOakLog,UnbreakGlass,UnbreakCobblestoneStairs,UnbreakRedCarpet,UnbreakWhiteCarpet
    };

}