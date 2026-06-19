package shift.sextiarysector3.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.registries.IForgeRegistry;
import shift.sextiarysector3.SextiarySector3;

/**
 * 1.12.2 port: blocks/items can no longer be registered during preInit (GameRegistry.register
 * is private now). This buffers everything and flushes it from RegistryEvent handlers
 * (see SSRegistry). Client model setup is deferred to ModelRegistryEvent via MODEL_TASKS.
 * All public method signatures are unchanged so existing callers don't need edits.
 */
public class UtilRegistry {

    public static File itemModel;
    public static File blockState;

    public static final List<Block> BLOCKS = new ArrayList<Block>();
    public static final List<Item> ITEMS = new ArrayList<Item>();
    public static final List<Runnable> MODEL_TASKS = new ArrayList<Runnable>();

    public static void registerNormalItem(Item item, String registryName, String resource) {
        registerNormalItem(item, registryName, resource, null);
    }

    public static void registerToolItem(Item item, String registryName, String resource) {
        registerNormalItem(item, registryName, resource, "item/handheld");
    }

    public static void registerNormalItem(final Item item, String registryName, final String resource, String parent) {

        item.setRegistryName(SextiarySector3.MODID, registryName);
        ITEMS.add(item);

        if (getSide().isClient()) {
            MODEL_TASKS.add(new Runnable() {
                public void run() {
                    SextiarySector3.proxy.setCustomModelResourceLocation(item, 0, resource);
                    if (item instanceof ISubItem) {
                        for (int i = 1; i < ((ISubItem) item).getSubSize(); i++) {
                            SextiarySector3.proxy.setCustomModelResourceLocation(item, i, ((ISubItem) item).getResourcesLocation(i));
                        }
                    }
                }
            });
        }

        File f = new File(itemModel, resource + ".json");
        if (SextiarySector3.isDebug && !f.exists() && getSide().isClient()) {
            ItemJsonUtil.generationItemGson(f, resource, parent);
        }
    }

    public static void registerAnimationItem(final Item item, String registryName, final String resource, int size) {

        item.setRegistryName(SextiarySector3.MODID, registryName);
        ITEMS.add(item);

        if (getSide().isClient()) {
            MODEL_TASKS.add(new Runnable() {
                public void run() {
                    SextiarySector3.proxy.setCustomModelResourceLocation(item, 0, resource);
                }
            });
        }

        File f = new File(itemModel, resource + ".json");
        if (SextiarySector3.isDebug && !f.exists() && getSide().isClient()) {
            ItemJsonUtil.generationAnimationItemGson(f, itemModel, resource, size);
        }
    }

    public static <T extends TileEntity> void registerCustomItem(final Item item, String registryName, final String resource,
            final Class<T> tileEntityClass) {

        item.setRegistryName(SextiarySector3.MODID, registryName);
        ITEMS.add(item);

        GameRegistry.registerTileEntity(tileEntityClass, SextiarySector3.MODID + ":" + registryName);

        if (getSide().isClient()) {
            MODEL_TASKS.add(new Runnable() {
                public void run() {
                    SextiarySector3.proxy.setCustomModelResourceLocation(item, 0, resource);
                    SextiarySector3.proxy.setCustomTileEntitySpecialRenderer(item, tileEntityClass);
                }
            });
        }

        File f = new File(itemModel, resource + ".json");
        if (SextiarySector3.isDebug && !f.exists() && getSide().isClient()) {
            ItemJsonUtil.generationItemGson(f, resource, null);
        }
    }

    public static void registerNormalBlock(Block block, String registryName, String resource) {

        ItemBlock itemBlock = new ItemBlock(block);
        registerNormalBlock(block, itemBlock, registryName, resource);

        File f = new File(blockState, resource + ".json");
        if (SextiarySector3.isDebug && !f.exists() && getSide().isClient()) {
            BlockJsonUtil.generationBlockStateGson(f, resource, null);
        }
    }

    public static void registerNormalBlock(final Block block, final Item itemBlock, String registryName, final String resource) {

        registerBlock(block, itemBlock, registryName);

        if (getSide().isClient() && block.getMaterial(null) != Material.AIR) {
            MODEL_TASKS.add(new Runnable() {
                public void run() {
                    SextiarySector3.proxy.setCustomStateMapper(block, resource);
                    if (block instanceof IMetaItem) {
                        for (int i = 0; i < ((IMetaItem) block).getMetaSize(); i++) {
                            SextiarySector3.proxy.setCustomModelResourceLocation(itemBlock, i, resource, block.getStateFromMeta(i));
                        }
                    } else {
                        SextiarySector3.proxy.setCustomModelResourceLocation(itemBlock, 0, resource);
                    }
                }
            });
        }
    }

    public static <T extends TileEntity> void registerTESRBlock(Block block, Class<T> tileEntityClass,
            String registryName, String resource) {

        ItemBlock itemBlock = new ItemBlock(block);
        registerTESRBlock(block, itemBlock, tileEntityClass, registryName, resource);
    }

    public static <T extends TileEntity> void registerTESRBlock(final Block block, final Item itemBlock,
            final Class<T> tileEntityClass, String registryName, final String resource) {

        registerBlock(block, itemBlock, registryName);

        if (getSide().isClient()) {
            MODEL_TASKS.add(new Runnable() {
                public void run() {
                    SextiarySector3.proxy.setCustomStateMapper(block, resource);
                    SextiarySector3.proxy.setCustomTileEntitySpecialRenderer(itemBlock, tileEntityClass);
                    SextiarySector3.proxy.setCustomModelResourceLocation(itemBlock, 0, resource);
                }
            });
        }
    }

    private static void registerBlock(Block block, Item itemBlock, String registryName) {
        block.setRegistryName(SextiarySector3.MODID, registryName);
        itemBlock.setRegistryName(SextiarySector3.MODID, registryName);
        BLOCKS.add(block);
        ITEMS.add(itemBlock);
    }

    // ---- flushed from RegistryEvent handlers (SSRegistry) ----
    public static void flushBlocks(IForgeRegistry<Block> registry) {
        for (Block b : BLOCKS) registry.register(b);
    }

    public static void flushItems(IForgeRegistry<Item> registry) {
        for (Item i : ITEMS) registry.register(i);
    }

    public static void runModelTasks() {
        for (Runnable t : MODEL_TASKS) t.run();
    }

    public static Side getSide() {
        return FMLCommonHandler.instance().getSide();
    }

}
