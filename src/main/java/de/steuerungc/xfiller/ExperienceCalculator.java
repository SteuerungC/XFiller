package de.steuerungc.xfiller;

import org.bukkit.entity.Player;

/**
 * Created by Martin on 30.04.2016.
 */
public class ExperienceCalculator {

    private int level;
    private float exp;
    private int offset;

    public ExperienceCalculator(Player p, int offset) {
        this(p.getLevel(), p.getExp(), offset);
    }

    public ExperienceCalculator(int level, float exp, int offset) {
        this.level = level;
        this.exp = exp;
        this.offset = offset;
    }

    public int getPossibleBottleCount() {
        return (int)((totalXP(level) + exp*xpToNextLevel(level)) / offset);
    }

    public void apply(Player p, int count) {

    }

    public boolean isPossible(int count) {
        if (count*offset <= (totalXP(level) + exp)) {
            return true;
        } else {
            return false;
        }
    }

    public int getLevel() {
        return level;
    }

    public int getTotalXP() {
        return completeXP(level, exp);
    }

    public int toNextLevel() {
        return (int)(xpToNextLevel(level) - exp*xpToNextLevel(level));
    }

    private static int xpToNextLevel(int cur_Level) throws IllegalArgumentException {
        if (cur_Level < 0) {
            throw new IllegalArgumentException("Level can't be less than 0");
        } else if (cur_Level > 0 && cur_Level < 17) {
            return 2*cur_Level+7;
        } else if (cur_Level > 16 && cur_Level < 32) {
            return 5 * cur_Level - 38;
        } else if (cur_Level > 31) {
            return 9 * cur_Level - 158;
        }
        return 0;
    }

    private static int totalXP(int cur_Level) throws IllegalArgumentException {
        if (cur_Level < 0) {
            throw new IllegalArgumentException("Level can't be less than 0");
        } else if (cur_Level > 0 && cur_Level < 17) {
            return (cur_Level ^ 2) + (6 * cur_Level);
        } else if (cur_Level > 16 && cur_Level < 32) {
            return (int) (2.5 * (cur_Level ^ 2) - (40.5 * cur_Level) + 360);
        } else if (cur_Level > 31) {
            return (int) (4.5 * (cur_Level ^ 2) - (162.5 * cur_Level) + 2220);
        }
        return 0;
    }

    private static int completeXP(int cur_Level, float exp) {
        return (int)(totalXP(cur_Level) + exp*xpToNextLevel(cur_Level));
    }
}
