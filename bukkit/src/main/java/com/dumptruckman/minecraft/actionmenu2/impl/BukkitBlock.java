package com.dumptruckman.minecraft.actionmenu2.impl;

import com.dumptruckman.minecraft.actionmenu2.api.MenuBlock;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BukkitBlock implements MenuBlock, Block {

    public static Map<Block, BukkitBlock> blockMap = new HashMap<Block, BukkitBlock>();

    public static BukkitBlock get(Block block) {
        if (!blockMap.containsKey(block)) {
            blockMap.put(block, new BukkitBlock(block));
        }
        return blockMap.get(block);
    }
    
    private Block block;
    
    private BukkitBlock(Block block) {
        this.block = block;
    }

    @Override
    public byte getData() {
        return block.getData();
    }

    @Override
    public Block getRelative(int i, int i1, int i2) {
        return block.getRelative(i, i1, i2);
    }

    @Override
    public Block getRelative(BlockFace blockFace) {
        return block.getRelative(blockFace);
    }

    @Override
    public Block getRelative(BlockFace blockFace, int i) {
        return block.getRelative(blockFace, i);
    }

    @Override
    public Material getType() {
        return block.getType();
    }

    @Override
    public int getTypeId() {
        return block.getTypeId();
    }

    @Override
    public byte getLightLevel() {
        return block.getLightLevel();
    }

    @Override
    public byte getLightFromSky() {
        return block.getLightFromSky();
    }

    @Override
    public byte getLightFromBlocks() {
        return block.getLightFromBlocks();
    }

    @Override
    public World getWorld() {
        return block.getWorld();
    }

    @Override
    public int getX() {
        return block.getX();
    }

    @Override
    public int getY() {
        return block.getY();
    }

    @Override
    public int getZ() {
        return block.getZ();
    }

    @Override
    public Location getLocation() {
        return block.getLocation();
    }

    @Override
    public Chunk getChunk() {
        return block.getChunk();
    }

    @Override
    public void setData(byte b) {
        block.setData(b);
    }

    @Override
    public void setData(byte b, boolean bool) {
        block.setData(b, bool);
    }

    @Override
    public void setType(Material material) {
        block.setType(material);
    }

    @Override
    public boolean setTypeId(int i) {
        return block.setTypeId(i);
    }

    @Override
    public boolean setTypeId(int i, boolean b) {
        return block.setTypeId(i, b);
    }

    @Override
    public boolean setTypeIdAndData(int i, byte b, boolean bool) {
        return block.setTypeIdAndData(i, b, bool);
    }

    @Override
    public BlockFace getFace(Block block) {
        return this.block.getFace(block);
    }

    @Override
    public BlockState getState() {
        return block.getState();
    }

    @Override
    public Biome getBiome() {
        return block.getBiome();
    }

    @Override
    public void setBiome(Biome biome) {
        block.setBiome(biome);
    }

    @Override
    public boolean isBlockPowered() {
        return block.isBlockPowered();
    }

    @Override
    public boolean isBlockIndirectlyPowered() {
        return block.isBlockIndirectlyPowered();
    }

    @Override
    public boolean isBlockFacePowered(BlockFace blockFace) {
        return block.isBlockFacePowered(blockFace);
    }

    @Override
    public boolean isBlockFaceIndirectlyPowered(BlockFace blockFace) {
        return block.isBlockFaceIndirectlyPowered(blockFace);
    }

    @Override
    public int getBlockPower(BlockFace blockFace) {
        return block.getBlockPower(blockFace);
    }

    @Override
    public int getBlockPower() {
        return block.getBlockPower();
    }

    @Override
    public boolean isEmpty() {
        return block.isEmpty();
    }

    @Override
    public boolean isLiquid() {
        return block.isLiquid();
    }

    @Override
    public double getTemperature() {
        return block.getTemperature();
    }

    @Override
    public double getHumidity() {
        return block.getHumidity();
    }

    @Override
    public PistonMoveReaction getPistonMoveReaction() {
        return block.getPistonMoveReaction();
    }

    @Override
    public boolean breakNaturally() {
        return block.breakNaturally();
    }

    @Override
    public boolean breakNaturally(ItemStack itemStack) {
        return block.breakNaturally(itemStack);
    }

    @Override
    public Collection<ItemStack> getDrops() {
        return block.getDrops();
    }

    @Override
    public Collection<ItemStack> getDrops(ItemStack itemStack) {
        return block.getDrops(itemStack);
    }

    @Override
    public void setMetadata(String s, MetadataValue metadataValue) {
        block.setMetadata(s, metadataValue);
    }

    @Override
    public List<MetadataValue> getMetadata(String s) {
        return block.getMetadata(s);
    }

    @Override
    public boolean hasMetadata(String s) {
        return block.hasMetadata(s);
    }

    @Override
    public void removeMetadata(String s, Plugin plugin) {
        block.removeMetadata(s, plugin);
    }
}
