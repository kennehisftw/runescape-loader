package io.github.kennehisftw.loader;

import io.github.kennehisftw.utils.Utilities;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by Kenneth on 6/12/2014.
 */
public class RSApplet extends JPanel implements AppletStub {

    /*
        Parameters object
     */
    private final Parameters parameters;

    /*
        JLabel object for the loading image icon
     */
    private final JLabel imageLabel;

    /*
        Image object for the loading image
     */
    private Image loadingImage;

    /*
        The applet instance
     */
    private Applet applet;

    /**
     * Instantiates the RSApplet class
     * @param worldId the world you wish to load in to
     * @param oldschool true for 07, false for rs3
     */
    public RSApplet(final int worldId, final boolean oldschool) {

        /*
            Set panel layout to BorderLayout
         */
        setLayout(new BorderLayout());
        /*
            Load and create the loading image using the Toolkit class
         */
        try {
            loadingImage = Toolkit.getDefaultToolkit().createImage(new URL("http://i.imgur.com/z1C9qFC.gif"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        /*
            Set the background color of the parent JPanel to black
         */
        setBackground(Color.BLACK);

        /*
            Initiate the JLabel with a new ImageIcon that contains the loadingImage
         */
        imageLabel = new JLabel(new ImageIcon(loadingImage));

        /*
            Attach the image to the parent panel to set it available for display
         */
        add(imageLabel, BorderLayout.CENTER);

        /*
            Instantiate the parameters class. This will also start the parsing.
         */
        parameters = new Parameters(worldId, oldschool);

    }

    /**
     * downloads the gamepack, then creates the applet.
     */
    public void downloadAndCreate() {
        /*
            Downloads the gamepack from the website using parameters
         */
        Utilities.downloadFile(parameters.getParameter("codebase") + parameters.getParameter("initial_jar"),
                System.getProperty("java.io.tmpdir") + parameters.getParameter("initial_jar"));

        /*
            Create a new file with the location of the gamepack we had just downloaded.
         */
        final File jar = new File(System.getProperty("java.io.tmpdir") + parameters.getParameter("initial_jar"));

        /*
            Create a new URLClassLoader using the jar
         */
        URLClassLoader classLoader = null;
        try {
            classLoader = new URLClassLoader(new URL[] { jar.toURI().toURL() });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        /*
            Gets the main class of the applet
         */
        final String mainClass = parameters.getParameter("initial_class").replaceAll(".class", "");

        /*
            Initialize applet
         */
        try {
            applet = (Applet) classLoader.loadClass(mainClass).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException a) {
            a.printStackTrace();
        }

        /*
            Set the applet stub
         */
        applet.setStub(this);

        /*
            Initialize the applet
         */
        applet.init();

        /*
            Start the applet
         */
        applet.start();

        /*
            Remove the imageLabel from the panel
         */
        remove(imageLabel);

        /*
            Resize the applet
         */
        applet.setPreferredSize(new Dimension(765, 503));

        /*
            Add the applet to the panel
         */
        add(applet, BorderLayout.CENTER);

        /*
            Set the color back to white
         */
        setBackground(Color.CYAN);

        /*
            Refresh the panel
         */
        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public URL getDocumentBase() {
        try {
            final URL documentBase = new URL(parameters.getParameter("codebase"));
            return documentBase;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public URL getCodeBase() {
        try {
            final URL documentBase = new URL(parameters.getParameter("codebase"));
            return documentBase;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getParameter(String name) {
        return parameters.getParameter(name);
    }

    @Override
    public AppletContext getAppletContext() {
        return null;
    }

    @Override
    public void appletResize(int width, int height) {

    }
}
