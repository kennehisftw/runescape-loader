package io.github.kennehisftw.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Created by Kenneth on 6/12/2014.
 */
public class Utilities {

    /**
     * Handy static method for downloading and saving files.
     * @param url the complete web URL for the file
     * @param location the complete destination including extension for the file
     * @return true if the file exists in the location, false if an exception is thrown or the file does not exist
     */
    public static boolean downloadFile(String url, String location) {
        try {

            final File destination = new File(location);
            if(destination.exists()) {
                System.out.println("File already exists, skipping download.");
                return true;
            }

            final URLConnection connection = new URL(url).openConnection();
            connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:30.0) Gecko/20100101 Firefox/30.0");

            final ReadableByteChannel rbc = Channels.newChannel(connection.getInputStream());

            final FileOutputStream fos = new FileOutputStream(destination);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();

        } catch(IOException exception) {
            exception.printStackTrace();
            return false;
        }

        System.out.println(url + "->" + location);
        return new File(location).exists();
    }

}
