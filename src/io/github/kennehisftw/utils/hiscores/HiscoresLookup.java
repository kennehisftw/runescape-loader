package io.github.kennehisftw.utils.hiscores;

import io.github.kennehisftw.utils.Constants;
import io.github.kennehisftw.utils.Utilities;

import javax.swing.*;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Kenneth on 6/15/2014.
 */
class HiscoresLookup {

    private static final String[] SKILL_NAMES = {
            "Overall", "Attack", "Defence", "Strength", "Constitution", "Ranged", "Prayer",
            "Magic", "Cooking", "Woodcutting", "Fletching", "Fishing", "Firemaking", "Crafting",
            "Smithing", "Mining", "Herblore", "Agility", "Thieving", "Slayer", "Farming",
            "Runecrafting", "Hunter", "Construction", "Summoning", "Dungeoneering", "Divination"
    };
    private final Map<Integer, Skill> skillMap = new LinkedHashMap<>();

    public HiscoresLookup(String username, boolean isRS3) {
        String username1 = username;
        boolean isRS31 = isRS3;

        String page = "";
        try {
            page = Utilities.downloadString(isRS31 ? Constants.RS_HISCORES_URL + username1
                    : Constants.OLDSCHOOL_HISCORES_URL + username1, true);
        } catch (IOException exception) {
            exception.printStackTrace();

            String[] options = new String[]{"OK"};
            JOptionPane.showOptionDialog(null,
                    "The player you searched does not exist or is free to play!", "Error looking up player",
                    JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
        }

        int totalSkills = isRS3 ? 26 : 23;
        for (int i = 0; i <= totalSkills; i++) {
            String skillName = SKILL_NAMES[i];
            String skillData = page.split("\n")[i];
            skillMap.putIfAbsent(i, new Skill(skillName, Integer.parseInt(skillData.split(",")[0]),
                    Integer.parseInt(skillData.split(",")[1]), Long.parseLong(skillData.split(",")[2])));
        }
    }

    public Map<Integer, Skill> getSkillMap() {
        return skillMap;
    }
}
