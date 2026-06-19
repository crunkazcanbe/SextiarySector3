package shift.sextiarysector3.achievement;

import java.util.ArrayList;

/** 1.12 removed net.minecraftforge.common.AchievementPage. Stub holder. */
public class AchievementPageBase {

    private final String name;

    public AchievementPageBase(String name, ArrayList<AchievementBase> achievements) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
