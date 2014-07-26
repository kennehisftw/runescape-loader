package io.github.kennehisftw.utils.calculators.prayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kenneth on 7/25/2014.
 */
public class Hiscores {

    private final List<Skill> skills = new LinkedList<>();

    public Hiscores(String username) {
        String[] stats = null;
        try {
            stats = getStatsData(username);
        } catch(IOException a) {
            System.out.println("Error grabbing hiscores data!");
            a.printStackTrace();
        }

        for(int i = 0; i < SKILL_NAMES.length; i++) {
            final String name = SKILL_NAMES[i];
            final int rank = Integer.parseInt(stats[i].split(",")[0]);
            final int level = Integer.parseInt(stats[i].split(",")[1]);
            final long experience = Long.parseLong(stats[i].split(",")[2]);
            skills.add(new Skill(name, rank, level, experience));
        }

    }

    public List<Skill> getSkills() {
        return skills;
    }

    private String[] getStatsData(String name) throws IOException {
        final URL address = new URL("http://hiscore.runescape.com/index_lite.ws?player=".concat(name));
        final URLConnection connection = address.openConnection();
        connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:30.0) Gecko/20100101 Firefox/30.0");
        connection.setConnectTimeout(5000);

        final List<String> dataList = new LinkedList<>();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String data;
        while((data = reader.readLine()) != null) {
            dataList.add(data);
        }
        return dataList.toArray(new String[dataList.size()]);
    }

    public static String[] SKILL_NAMES = {
            "Overall", "Attack", "Defence", "Strength", "Constitution", "Ranged", "Prayer",
            "Magic", "Cooking", "Woodcutting", "Fletching", "Fishing", "Firemaking", "Crafting",
            "Smithing", "Mining", "Herblore", "Agility", "Thieving", "Slayer", "Farming",
            "Runecrafting", "Hunter", "Construction", "Summoning", "Dungeoneering", "Divination"
    };

}
