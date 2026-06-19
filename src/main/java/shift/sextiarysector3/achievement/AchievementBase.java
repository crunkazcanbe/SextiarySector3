package shift.sextiarysector3.achievement;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

/**
 * 1.12 removed net.minecraft.stats.Achievement (replaced by JSON Advancements).
 * This is a lightweight stand-in so the mod's achievement tree still constructs;
 * it no longer drives in-game popups. (1.12.2 port stub.)
 */
public class AchievementBase {

    public final String id;
    public ItemStack theItemStack;

    public AchievementBase(String id, int x, int y, ItemStack icon, AchievementBase parent, ArrayList<AchievementBase> a) {
        this.id = id;
        this.theItemStack = icon;
        if (a != null) a.add(this);
    }

    public AchievementBase initIndependentStat() { return this; }

    public AchievementBase registerStat() { return this; }

    public AchievementBase setSpecial() { return this; }
}
