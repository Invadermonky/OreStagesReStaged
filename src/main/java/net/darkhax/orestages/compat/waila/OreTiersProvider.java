package net.darkhax.orestages.compat.waila;

import mcp.mobius.waila.api.*;
import net.darkhax.gamestages.GameStageHelper;
import net.darkhax.orestages.api.OreTiersAPI;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

@WailaPlugin
public class OreTiersProvider implements IWailaPlugin, IWailaDataProvider {
    
    @Override
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
        IBlockState checkState = accessor.getBlockState();
        Tuple<String, IBlockState> stageInfo = OreTiersAPI.getStageInfo(checkState);
        while (stageInfo != null) {
            if (GameStageHelper.hasStage(accessor.getPlayer(), stageInfo.getFirst())) {
                break;
            } else {
                checkState = stageInfo.getSecond();
                stageInfo = OreTiersAPI.getStageInfo(checkState);
            }
        }
        if(checkState != accessor.getBlockState()) {
            return checkState.getBlock().getPickBlock(checkState, accessor.getMOP(), accessor.getWorld(), accessor.getPosition(), accessor.getPlayer());
        }
        return accessor.getStack();
    }
    
    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }
    
    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }
    
    @Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }
    
    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, BlockPos pos) {
        return tag;
    }
    
    @Override
    public void register(IWailaRegistrar registrar) {
        registrar.registerStackProvider(this, Block.class);
    }
}