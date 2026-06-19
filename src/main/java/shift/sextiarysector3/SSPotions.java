package shift.sextiarysector3;

import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 1.12.2: potions are a registry now. PotionTypes register via RegistryEvent<PotionType>;
 * brewing conversions use PotionHelper.addMix(input, Ingredient, output) instead of the
 * removed registerPotionTypeConversion / ItemPredicateInstance.
 */
@Mod.EventBusSubscriber(modid = SextiarySector3.MODID)
public class SSPotions {

    public static PotionType haste;
    public static PotionType longHaste;
    public static PotionType strongHaste;

    public static PotionType glowing;
    public static PotionType longGlowing;
    public static PotionType strongGlowing;

    @SubscribeEvent
    public static void registerPotionTypes(RegistryEvent.Register<PotionType> event) {

        haste = new PotionType(new PotionEffect[] { new PotionEffect(MobEffects.HASTE, 3600) }).setRegistryName(SextiarySector3.MODID, "haste");
        longHaste = new PotionType("haste", new PotionEffect[] { new PotionEffect(MobEffects.HASTE, 9600) }).setRegistryName(SextiarySector3.MODID, "long_haste");
        strongHaste = new PotionType("haste", new PotionEffect[] { new PotionEffect(MobEffects.HASTE, 1800, 1) }).setRegistryName(SextiarySector3.MODID, "strong_haste");

        glowing = new PotionType(new PotionEffect[] { new PotionEffect(MobEffects.GLOWING, 3600) }).setRegistryName(SextiarySector3.MODID, "glowing");
        longGlowing = new PotionType("glowing", new PotionEffect[] { new PotionEffect(MobEffects.GLOWING, 9600) }).setRegistryName(SextiarySector3.MODID, "long_glowing");

        event.getRegistry().registerAll(haste, longHaste, strongHaste, glowing, longGlowing);
    }

    /** brewing recipes — run from preInit (types already registered by then). */
    public static void initPotion() {

        Ingredient redstone = Ingredient.fromItem(Items.REDSTONE);
        Ingredient glowstone = Ingredient.fromItem(Items.GLOWSTONE_DUST);
        Ingredient orichalcum = Ingredient.fromItem(SSItems.orichalcumGem);
        Ingredient shiningFlower = Ingredient.fromItem(SSItems.shiningFlower);

        PotionHelper.addMix(PotionTypes.AWKWARD, orichalcum, haste);
        PotionHelper.addMix(haste, redstone, longHaste);
        PotionHelper.addMix(haste, glowstone, strongHaste);

        PotionHelper.addMix(PotionTypes.AWKWARD, shiningFlower, glowing);
        PotionHelper.addMix(glowing, redstone, longGlowing);
    }

}
