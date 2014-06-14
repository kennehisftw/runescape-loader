package io.github.kennehisftw.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


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

            final URLConnection connection = new URL(url).openConnection();
            connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:30.0) Gecko/20100101 Firefox/30.0");

            final int contentLength = connection.getContentLength();
            final File destination = new File(location);

            if(destination.exists()) {
                final URLConnection savedFileConnection = destination.toURI().toURL().openConnection();
                if(savedFileConnection.getContentLength() == contentLength) {
                    return true;
                }
            } else {
                final File parent = destination.getParentFile();
                if(!parent.exists()) parent.mkdirs();
            }

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

    /**
     * Used to change the loading of all files
     * @return the directory which files load from
     */
    public static String getContentDirectory() {
        return System.getProperty("user.home") + File.separator + "runescape-loader" + File.separator;
    }

    /**
     * Uses Toolkit.getDefaultToolkit() to load an image from the specified file location
     * @param file the absolute location of the image
     * @return the image
     */
    public static Image getImage(String file) {
        return Toolkit.getDefaultToolkit().createImage(file);
    }

    public static void writeImage(Image image) {

        File parent = new File(getContentDirectory() + "screenshots/");
        if(!parent.exists()) parent.mkdirs();
        File location = new File(parent.toString() + File.separator +  System.currentTimeMillis() + ".png");

        try {
            location.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ImageIO.write((RenderedImage) image, "png", location);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("File written: "+ location);
    }

}
