package shift.sextiarysector3;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import shift.sextiarysector3.achievement.AchievementBase;
import shift.sextiarysector3.achievement.AchievementCraft;
import shift.sextiarysector3.achievement.AchievementFurnace;
import shift.sextiarysector3.achievement.AchievementPageBase;
import shift.sextiarysector3.achievement.AchievementSSOre;

/**
 * 1.12.2 port: the old net.minecraft.stats.Achievement / AchievementPage system was
 * removed in 1.12 (replaced by JSON advancements). The tree below still builds with
 * stub objects so the rest of the mod compiles and runs; advancements can be added
 * later as data files.
 */
public class SSAchievements {

    private static ArrayList<AchievementBase> core = Lists.newArrayList();
    private static ArrayList<AchievementBase> forestry = Lists.newArrayList();
    private static ArrayList<AchievementBase> mining = Lists.newArrayList();
    private static ArrayList<AchievementBase> industry = Lists.newArrayList();
    private static ArrayList<AchievementBase> economy = Lists.newArrayList();

    //Core
    public static AchievementBase blueStone;
    public static AchievementBase shield;
    public static AchievementBase sanctuary;
    public static AchievementBase depthMeter;

    //林業
    public static AchievementBase spile;
    public static AchievementBase sapBottle;
    public static AchievementBase rubberBottle;
    public static AchievementBase mapleBottle;

    //鉱業
    public static AchievementBase newOre;
    public static AchievementBase silver;

    //工業
    public static AchievementBase plastic;
    public static AchievementBase rubber;

    //Economy
    public static AchievementBase creeperFirework;
    public static AchievementBase creeperChest;

    public static void initAchievements() {

        //コア
        blueStone = new AchievementFurnace("bluestone", 0, 0, new ItemStack(SSItems.bluestone), (AchievementBase) null, core).initIndependentStat().registerStat();

        //林業
        spile = new AchievementCraft("spile", 0, 0, new ItemStack(SSBlocks.spile), (AchievementBase) null, forestry).initIndependentStat().registerStat();
        sapBottle = new AchievementBase("sap_bottle", 2, 1, new ItemStack(SSItems.sapBottle), spile, forestry).registerStat();
        rubberBottle = new AchievementBase("rubber_bottle", 2, -1, new ItemStack(SSItems.rubberBottle), spile, forestry).registerStat();
        mapleBottle = new AchievementBase("maple_bottle", -2, 1, new ItemStack(SSItems.mapleBottle), spile, forestry).registerStat();

        //鉱業
        newOre = new AchievementSSOre("new_ore", 0, 0, new ItemStack(SSItems.orichalcumGem), (AchievementBase) null, mining).initIndependentStat().registerStat();
        silver = new AchievementFurnace("silver", 2, 1, new ItemStack(SSItems.silverIngot), newOre, mining).registerStat();

        //鉱業 -> コア
        sanctuary = new AchievementCraft("sanctuary", 2, 1, new ItemStack(SSBlocks.sanctuary), silver, core).registerStat().setSpecial();
        depthMeter = new AchievementFurnace("depth_meter", 2, -1, new ItemStack(SSItems.depthMeter), silver, core).registerStat().setSpecial();

        //工業
        plastic = new AchievementFurnace("plastic", 0, 0, new ItemStack(SSItems.plastic), sapBottle, industry).registerStat();
        rubber = new AchievementFurnace("rubber", 0, 2, new ItemStack(SSItems.rubber), rubberBottle, industry).registerStat();

        //工業 -> コア
        shield = new AchievementCraft("shield", 4, -2, new ItemStack(SSItems.plasticShield), plastic, core).registerStat();

        //経済
        creeperFirework = new AchievementBase("creeper_firework", 0, 0, new ItemStack(Items.FIREWORKS), (AchievementBase) null, economy).initIndependentStat().registerStat();
        creeperChest = new AchievementBase("creeper_chest", 1, -2, new ItemStack(SSBlocks.creeperChest), creeperFirework, economy).registerStat();

        //登録 — pages are stubs now (AchievementPage removed in 1.12)
        new AchievementPageBase("achievement.ss.core", core);
        new AchievementPageBase("achievement.ss.forestry", forestry);
        new AchievementPageBase("achievement.ss.mining", mining);
        new AchievementPageBase("achievement.ss.industry", industry);
        new AchievementPageBase("achievement.ss.economy", economy);

    }

}
