package io.github.kennehisftw.utils;

import io.github.kennehisftw.utils.screenshot.Imgur;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by Kenneth on 6/12/2014.
 */
public class Utilities {

    /**
     * Handy static method for downloading and saving files.
     *
     * @param site     the complete web URL for the file
     * @param filename the complete destination including extension for the file
     * @return true if the file exists in the location, false if an exception is thrown or the file does not exist
     */
    public static boolean downloadFile(String site, String filename) {
        if (!new File(filename).exists()) {
            JFrame frm = new JFrame();
            JProgressBar current = new JProgressBar(0, 100);
            current.setSize(50, 65);
            current.setValue(0);
            current.setStringPainted(true);
            current.setBackground(Color.BLUE);
            frm.add(current);
            frm.setVisible(true);
            frm.setLayout(new FlowLayout());
            frm.setSize(300, 70);
            frm.setLocationRelativeTo(null);
            frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(site).openConnection();
                connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:30.0) Gecko/20100101 Firefox/30.0");
                int filesize = connection.getContentLength();
                final File destination = new File(filename);
                if (destination.exists()) {
                    final URLConnection savedFileConnection = destination.toURI().toURL().openConnection();
                    if (savedFileConnection.getContentLength() == filesize) {
                        frm.dispose();
                        return true;
                    }
                } else {
                    final File parent = destination.getParentFile();
                    if (!parent.exists()) parent.mkdirs();
                }
                float totalDataRead = 0;
                java.io.BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
                java.io.FileOutputStream fos = new FileOutputStream(filename);
                java.io.BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
                byte[] data = new byte[1024];
                int i;
                while ((i = in.read(data, 0, 1024)) >= 0) {
                    totalDataRead = totalDataRead + i;
                    bout.write(data, 0, i);
                    float Percent = (totalDataRead * 100) / filesize;
                    current.setValue((int) Percent);
                }
                bout.close();
                in.close();
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(null, e.getMessage(), "Error", JOptionPane.DEFAULT_OPTION);
            }
            System.out.println(site + "->" + filename);
            frm.dispose();
            return new File(filename).exists();
        }
        return false;
    }

    /**
     * Used to change the loading of all files
     *
     * @return the directory which files load from
     */
    public static String getContentDirectory() {
        return System.getProperty("user.home") + File.separator + "runescape-loader" + File.separator;
    }

    /**
     * Uses Toolkit.getDefaultToolkit() to load an image from the specified file location
     *
     * @param file the absolute location of the image
     * @return the image
     */
    public static Image getImage(String file) {
        return Toolkit.getDefaultToolkit().createImage(file);
    }

    public static Image downloadImage(String link) {
        Image image;
        URL url;
        try {
            url = new URL(link);
            image = ImageIO.read(url);
        } catch (IOException ex) {
            return null;
        }
        return image;
    }

    private static void writeImage(Image image) {
        File parent = new File(getContentDirectory() + "screenshots/");
        if (!parent.exists()) parent.mkdirs();
        File location = new File(parent.toString() + File.separator + System.currentTimeMillis() + ".png");
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
        System.out.println("File written: " + location);
    }

    public static String downloadString(String url, boolean splitLines) throws IOException {
        final URLConnection connection = new URL(url).openConnection();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        final StringBuilder builder = new StringBuilder();

        String input;
        while ((input = reader.readLine()) != null) {
            builder.append(input).append(splitLines ? "\n" : "");
        }

        return builder.toString();
    }

    public static void screenshot(Window parent, Imgur imgur, TrayIcon trayIcon) {
        trayIcon.displayMessage("Screenshot Captured", "Please wait while the screenshot uploads.", TrayIcon.MessageType.INFO);
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        BufferedImage image = robot.createScreenCapture(parent.getBounds());
        Utilities.writeImage(image);
        Thread thread = new Thread(() -> {
            String imageURL = null;
            try {
                imageURL = imgur.upload(image);
            } catch (IOException ex) {
                System.out.println("Error uploading screen shot.");
            }
            StringSelection stringSelection = new StringSelection(imageURL);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            trayIcon.displayMessage("Screen Shot Taken!", "Uploaded Screen Shot to " + imageURL, TrayIcon.MessageType.INFO);
            try {
                Desktop.getDesktop().browse(new URL(imageURL).toURI());
            } catch (IOException | URISyntaxException e) {
                trayIcon.displayMessage("Error opening browser!", "The screenshot is not lost, we just couldn't open it.", TrayIcon.MessageType.ERROR);
            }
        });
        thread.start();
    }
}
