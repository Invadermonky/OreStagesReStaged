package net.darkhax.orestages.compat.groovyscript;

import com.cleanroommc.groovyscript.api.IScriptReloadable;
import com.cleanroommc.groovyscript.api.documentation.annotations.Example;
import com.cleanroommc.groovyscript.api.documentation.annotations.MethodDescription;
import com.cleanroommc.groovyscript.api.documentation.annotations.RegistryDescription;
import com.cleanroommc.groovyscript.helper.Alias;
import com.cleanroommc.groovyscript.registry.NamedRegistry;
import net.darkhax.orestages.OreStages;
import net.darkhax.orestages.api.OreTiersAPI;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

@RegistryDescription(
        linkGenerator = OreStages.MOD_ID,
        reloadability = RegistryDescription.Reloadability.FLAWED
)
public class GSOreStages extends NamedRegistry implements IScriptReloadable {

    public GSOreStages() {
        super(Alias.generateOf("OreStages"));
    }

    @Override
    public void onReload() {
        OreTiersAPI.getRelevantStates().clear();
        OreTiersAPI.STATE_MAP.clear();
        OreTiersAPI.REPLACEMENT_IDS.clear();
        OreTiersAPI.NON_DEFAULTING.clear();
    }

    @Override
    public void afterScriptLoad() {}

    @MethodDescription(
            type = MethodDescription.Type.ADDITION,
            example = @Example("'stageIron', block('minecraft:iron_ore'), false"),
            description = "groovyscript.wiki.orestages.ore_stages.addReplacement.0"
    )
    public void addReplacement(String stage, Block original, boolean isDefaulting) {
        this.addReplacement(stage, original, Blocks.STONE, isDefaulting);
    }

    @MethodDescription(
            type = MethodDescription.Type.ADDITION,
            example = @Example("'stageGranite', blockstate('minecraft:stone:1'), false"),
            description = "groovyscript.wiki.orestages.ore_stages.addReplacement.1",
            priority = 1001
    )
    public void addReplacement(String stage, IBlockState original, boolean isDefaulting) {
        this.addReplacement(stage, original, Blocks.STONE.getDefaultState(), isDefaulting);
    }

    @MethodDescription(
            type = MethodDescription.Type.ADDITION,
            example = @Example("'stageQuartz', block('minecraft:quartz_ore'), block('minecraft:netherrack'), false"),
            description = "groovyscript.wiki.orestages.ore_stages.addReplacement.2",
            priority = 1002
    )
    public void addReplacement(String stage, Block original, Block replacement, boolean isDefaulting) {
        OreTiersAPI.addReplacement(stage, original, replacement, isDefaulting);
    }

    @MethodDescription(
            type = MethodDescription.Type.ADDITION,
            example = {
                    @Example("'stageDiorite', blockstate('minecraft:stone:3'), blockstate('minecraft:stone:0'), false"),
                    @Example("'stageDiamond', blockstate('minecraft:diamond_ore'), blockstate('minecraft:gold_ore'), false"),
                    @Example("'stageFlower', blockstate('minecraft:red_flower:0'), blockstate('minecraft:tallgrass:0'), true")
            },
            description = "groovyscript.wiki.orestages.ore_stages.addReplacement.3",
            priority = 1003
    )
    public void addReplacement(String stage, IBlockState original, IBlockState replacement, boolean isDefaulting) {
        OreTiersAPI.addReplacement(stage, original, replacement, isDefaulting);
    }
}
