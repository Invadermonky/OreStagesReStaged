package net.darkhax.orestages.utils;

import net.darkhax.bookshelf.util.RenderUtils;
import net.darkhax.orestages.OreStages;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.fml.common.Loader;
import org.embeddedt.vintagefix.dynamicresources.IBlockModelShapes;
import org.embeddedt.vintagefix.dynamicresources.IModelHoldingBlockState;

public class RenderUtilsOS {
    private static final boolean isVintageFixLoaded = Loader.isModLoaded("vintagefix");

    public static void setModelForState(IBlockState state, IBakedModel model) {
        try {
            if(isVintageFixLoaded) {
                //With VintageFix
                if(state instanceof IModelHoldingBlockState) {
                    ((IModelHoldingBlockState) state).vfix$setModel(model);
                } else {
                    BlockModelShapes modelShapes = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes();
                    ModelResourceLocation location = ((IBlockModelShapes) modelShapes).getLocationForState(state);
                    modelShapes.getModelManager().modelRegistry.putObject(location, model);
                }
                return;
            }
        } catch (Exception e) {
            OreStages.LOG.error("Failed to register model location with VintageFix model registry.", e);
        }
        //Without VintageFix
        RenderUtils.setModelForState(state, model);
    }
}
