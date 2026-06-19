package shift.sextiarysector3;

import net.minecraft.block.Block;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shift.sextiarysector3.util.UtilRegistry;

/**
 * 1.12.2 registration: content is built + flushed inside the registry events
 * (preInit registration is no longer allowed). Creative tabs are created first
 * because items/blocks reference them in their constructors.
 */
@Mod.EventBusSubscriber(modid = SextiarySector3.MODID)
public class SSRegistry {

    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        SSCreativeTabs.initCreativeTabs();
        SSBlocks.initBlock();
        UtilRegistry.flushBlocks(event.getRegistry());
    }

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        SSItems.initItem();
        UtilRegistry.flushItems(event.getRegistry());
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onRegisterModels(ModelRegistryEvent event) {
        UtilRegistry.runModelTasks();
    }

}
