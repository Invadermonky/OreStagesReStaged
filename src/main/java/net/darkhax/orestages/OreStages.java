package net.darkhax.orestages;

import java.util.Map.Entry;

import net.darkhax.bookshelf.lib.LoggingHelper;
import net.darkhax.bookshelf.util.RenderUtils;
import net.darkhax.orestages.api.OreTiersAPI;
import net.darkhax.orestages.client.renderer.block.model.BakedModelTiered;
import net.darkhax.orestages.compat.theoneprobe.TOPCompatibility;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.util.Tuple;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(
        modid = OreStages.MOD_ID,
        name = OreStages.MOD_NAME,
        version = OreStages.MOD_VERSION,
        dependencies = OreStages.DEPENDENCIES,
        certificateFingerprint = "@FINGERPRINT@"
)
public class OreStages {
    public static final String MOD_ID = Tags.MOD_ID;
    public static final String MOD_NAME = Tags.MOD_NAME;
    public static final String MOD_VERSION = Tags.VERSION;
    public static final String DEPENDENCIES = "required-after:bookshelf" +
            ";required-after:gamestages@[2.0.89,)" +
            ";required-after:crafttweaker";
    
    public static final LoggingHelper LOG = new LoggingHelper("Ore Stages");
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent ev) {
        MinecraftForge.EVENT_BUS.register(new OreTiersEventHandler());
        if (Loader.isModLoaded("theoneprobe")) {
            TOPCompatibility.load();
        }
    }
    
    @EventHandler
    @SideOnly(Side.CLIENT)
    public void onLoadComplete(FMLLoadCompleteEvent event) {
        // Add a resource reload listener to refresh with texture packs.
        ((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(listener -> replaceModels());
    }
    
    @SideOnly(Side.CLIENT)
    private static void replaceModels() {
        LOG.info("Starting model replacement for {} blocks.", OreTiersAPI.STATE_MAP.size());
        final long time = System.currentTimeMillis();
        if (!OreTiersAPI.STATE_MAP.isEmpty()) {
            for (final Entry<IBlockState, Tuple<String, IBlockState>> entry : OreTiersAPI.STATE_MAP.entrySet()) {
                RenderUtils.setModelForState(entry.getKey(), new BakedModelTiered(entry.getValue().getFirst(), entry.getKey(), entry.getValue().getSecond()));
            }
        } else {
            LOG.info("There are no block replacements. Has the mod been configured?");
        }
        LOG.info("Model replacement finished. Took {}ms", System.currentTimeMillis() - time);
    }
}
