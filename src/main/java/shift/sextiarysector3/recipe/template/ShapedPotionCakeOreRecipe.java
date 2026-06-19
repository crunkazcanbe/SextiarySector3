package shift.sextiarysector3.recipe.template;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * 1.12.2: ShapedOreRecipe's matching internals (checkMatch/input/width) are gone.
 * Reduced to a plain shaped ore recipe for now; potion-specific NBT matching is a TODO.
 */
public class ShapedPotionCakeOreRecipe extends ShapedOreRecipe {

    public ShapedPotionCakeOreRecipe(ItemStack result, Object[] recipe) {
        super((ResourceLocation) null, result, recipe);
    }
}
