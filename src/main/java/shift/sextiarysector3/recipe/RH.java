package shift.sextiarysector3.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import shift.sextiarysector3.SextiarySector3;

/**
 * 1.12.2 recipe registration helper. Crafting recipes must be registered into the
 * IRecipe registry during RegistryEvent.Register<IRecipe>; smelting goes through
 * FurnaceRecipes (the old GameRegistry.addRecipe/addSmelting were removed).
 */
public class RH {

    public static IForgeRegistry<IRecipe> REG;
    private static int n = 0;

    public static void reg(IRecipe r) {
        if (r.getRegistryName() == null) {
            r.setRegistryName(new ResourceLocation(SextiarySector3.MODID, "recipe_" + (n++)));
        }
        if (REG != null) REG.register(r);
    }

    public static void smelt(ItemStack in, ItemStack out, float xp) {
        FurnaceRecipes.instance().addSmeltingRecipe(in, out, xp);
    }
}
