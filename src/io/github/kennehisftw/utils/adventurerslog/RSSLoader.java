package io.github.kennehisftw.utils.adventurerslog;

import it.sauronsoftware.feed4j.FeedIOException;
import it.sauronsoftware.feed4j.FeedParser;
import it.sauronsoftware.feed4j.FeedXMLParseException;
import it.sauronsoftware.feed4j.UnsupportedFeedException;
import it.sauronsoftware.feed4j.bean.Feed;
import it.sauronsoftware.feed4j.bean.FeedItem;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Kenneth on 6/15/2014.
 */
public class RSSLoader {

    private FeedItem[] elements;

    public RSSLoader(String username) {

        URL url = null;

        try {
            url = new URL("http://services.runescape.com/m=adventurers-log/rssfeed?searchName=" + username);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        Feed feed = null;

        try {
            feed = FeedParser.parse(url);
        } catch (FeedXMLParseException | UnsupportedFeedException | FeedIOException e) {
            e.printStackTrace();

            String[] options = new String[]{"OK"};
            JOptionPane.showOptionDialog(null,
                    "The player you searched does not exist or is free to play!", "Error looking up player",
                    JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
        }

        int itemCount = feed.getItemCount();
        elements = new FeedItem[itemCount];
        for (int i = 0; i < itemCount; i++) {
            elements[i] = feed.getItem(i);
        }
    }

    public FeedItem[] getElements() {
        return elements;
    }
}
