package io.github.kennehisftw.utils.grandexchange;

import com.google.gson.Gson;
import io.github.kennehisftw.utils.Constants;
import io.github.kennehisftw.utils.Utilities;

import java.io.FileWriter;
import java.io.IOException;

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
            json = Utilities.downloadString(request.toString(), false);
        } catch(IOException a) {
            a.printStackTrace();
        }
        items = new Gson().fromJson(json, Item[].class);
    }

    public Item[] getItems() {
        return items;
    }

    public static void main(String[] args) throws IOException {

        final FileWriter writer = new FileWriter("./items.txt");
        int index = 0;

        for(int i = 0; i <= 40; i++) {
            GEItemLookup lookup = new GEItemLookup(createAscendingArray(index, 1000));
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

    public static int[] createAscendingArray(int start, int increment) {
        final int[] arr = new int[increment+1];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = start + i;
        }
        return arr;
    }

}
