package io.github.kennehisftw.utils.calculators.prayer;

/**
 * Created by Kenneth on 7/25/2014.
 */
class Skill {

    private final String skillName;
    private final int rank;
    private final int level;
    private final long experience;

    public Skill(String skillName, int rank, int level, long experience) {
        this.rank = rank;
        this.level = level;
        this.experience = experience;
        this.skillName = skillName;
    }

    public String getName() {
        return skillName;
    }

    public int getRank() {
        return rank;
    }

    public int getLevel() {
        return level;
    }

    public long getExperience() {
        return experience;
    }

    @Override
    public String toString() {
        return "Skill{" + "skillName='" + skillName + '\'' + ", rank=" + rank + ", level=" + level + ", experience=" + experience + '}';
    }
}