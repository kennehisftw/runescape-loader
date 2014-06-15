package io.github.kennehisftw.utils.grandexchange;

import com.google.gson.Gson;
import io.github.kennehisftw.utils.Constants;
import io.github.kennehisftw.utils.Utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kenneth on 6/14/2014.
 */
public class GEItemLookup {

    private Item[] items;
    public GEItemLookup(int... itemIds) {

        final StringBuilder request = new StringBuilder(Constants.RS_API_URL);
        for(int i = 0; i < itemIds.length; i++) {
            request.append(itemIds[i]).append(i != itemIds.length ? "," : "");
        }
        request.append(".json");

        String json = "";
        try {
            json = Utilities.downloadString(request.toString());
        } catch(IOException a) {
            a.printStackTrace();
        }
        items = new Gson().fromJson(json, Item[].class);
    }

    public Item[] getItems() {
        return items;
    }

    public static void main(String[] args) throws IOException {

        final FileWriter writer = new FileWriter("./db.txt");
        int index = 0;

        for(int i = 0; i <= 40; i++) {
            GEItemLookup lookup = new GEItemLookup(createAscendingArray(index));
            for (Item item : lookup.getItems()) {
                writer.write(item.getId() + ":" + item.getName() + "\n");
            }
            index += 1000;
            System.out.println(index);
            sleep(5000);
        }
        writer.flush();
        writer.close();
    }

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int[] createAscendingArray(int start) {
        final int[] arr = new int[1001];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = start + i;
        }
        return arr;
    }

}
