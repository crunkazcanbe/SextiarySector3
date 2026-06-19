package shift.sextiarysector3;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shift.sextiarysector3.recipe.RH;
import shift.sextiarysector3.recipe.RecipesArmor;
import shift.sextiarysector3.recipe.RecipesCauldron;
import shift.sextiarysector3.recipe.RecipesCooking;
import shift.sextiarysector3.recipe.RecipesCore;
import shift.sextiarysector3.recipe.RecipesForestry;
import shift.sextiarysector3.recipe.RecipesFurnace;
import shift.sextiarysector3.recipe.RecipesIndustry;
import shift.sextiarysector3.recipe.RecipesMining;
import shift.sextiarysector3.recipe.RecipesPharmacy;
import shift.sextiarysector3.recipe.RecipesTool;
import shift.sextiarysector3.recipe.template.RecipeCauldron;

@Mod.EventBusSubscriber(modid = SextiarySector3.MODID)
public class SSRecipes {

    public static List<RecipeCauldron> cauldronRecipes = Lists.newArrayList();

    /** preInit — smelting + the mod's custom cauldron recipes. */
    public static void initRecipes() {
        RecipesFurnace.addRecipes();
        RecipesCauldron.addRecipes(cauldronRecipes);
    }

    /** 1.12: crafting recipes register here (fires before preInit). */
    @SubscribeEvent
    public static void onRegisterRecipes(RegistryEvent.Register<IRecipe> event) {
        RH.REG = event.getRegistry();
        RecipesCore.addRecipes(null);
        RecipesArmor.addRecipes(null);
        RecipesForestry.addRecipes(null);
        RecipesMining.addRecipes(null);
        RecipesIndustry.addRecipes(null);
        RecipesPharmacy.addRecipes(null);
        RecipesCooking.addRecipes(null);
        RecipesTool.addRecipes(null);
    }

}
