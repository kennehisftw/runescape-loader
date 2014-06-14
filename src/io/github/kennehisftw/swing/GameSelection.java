package io.github.kennehisftw.swing;

import io.github.kennehisftw.loader.RSApplet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Kenneth on 6/13/2014.
 */
public class GameSelection extends JFrame {

    /*
        Creates the button objects
     */
    private final JButton runeScape3, oldschool;

    /*
        Creates the image objects
     */
    private Image rs3Image, osImage, backGround;

    /*
        Creates the ImagePanel object
     */
    private final ImagePanel imagePanel;

    /*
        Create an applet object
     */
    private RSApplet applet;

    /*
        Instantiates the class
     */
    public GameSelection() {
        /*
            Set the frame title
         */
        super("Please select the game mode you would like to load");

        /*
            Set the default close operation
         */
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        /*
            Initialize the images with ImageIO
         */
        try {
            rs3Image = ImageIO.read(GameSelection.class.getResource("rs3.png"));
            osImage = ImageIO.read(GameSelection.class.getResource("os.png"));
            backGround = ImageIO.read(GameSelection.class.getResource("bg.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
                Instantiate the ImagePanel
         */
        imagePanel = new ImagePanel(backGround);

        /*
            Set the content pane to the ImagePanel
         */
        setContentPane(imagePanel);

        /*
            Set the layout of the parent contentpane
         */
        getContentPane().setLayout(new GridBagLayout());

        /*
            Instantiate the JButtons and set the defaults
         */
        runeScape3 = new JButton(new ImageIcon(rs3Image));
        runeScape3.setBorder(null);
        runeScape3.setSelectedIcon(null);
        runeScape3.setToolTipText("Load RuneScape 3");
        runeScape3.addActionListener(listener -> {
            /*
                Create the applet instance
             */
            applet = new RSApplet(42, false);

            /*
                Remove all components from the content pane
             */
            getContentPane().removeAll();

            /*
                Change the frame title
             */
            setTitle("RuneScape");

            /*
                Change the layout of the content pane
             */
            getContentPane().setLayout(new BorderLayout());

            /*
                Add the applet to the content pane
             */
            getContentPane().add(applet, BorderLayout.CENTER);

            /*
                Update the frame
             */
            getContentPane().revalidate();

            /*
                Start the applet from a new thread
             */
            Thread thread = new Thread(() -> applet.downloadAndCreate());
            thread.start();
        });

        oldschool = new JButton(new ImageIcon(osImage));
        oldschool.setBorder(null);
        oldschool.setToolTipText("Load OldSchool RuneScape");
        oldschool.addActionListener(listener -> {
            /*
                Create the applet instance
             */
            applet = new RSApplet(42, true);

            /*
                Remove all components from the content pane
             */
            getContentPane().removeAll();

            /*
                Change the frame title
             */
            setTitle("OldSchool RuneScape");

            /*
                Change the layout of the content pane
             */
            getContentPane().setLayout(new BorderLayout());

            /*
                Add the applet to the content pane
             */
            getContentPane().add(applet, BorderLayout.CENTER);

            /*
                Update the frame
             */
            getContentPane().revalidate();

            /*
                Resize the frame to fit OldSchool
             */
            getContentPane().setPreferredSize(new Dimension(765, 503));
            setResizable(false);
            pack();

            /*
                Start the applet from a new thread
             */
            Thread thread = new Thread(() -> applet.downloadAndCreate());
            thread.start();
        });

        /*
            Add the components to the content pane
         */
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 1;
        c.gridy = 1;
        getContentPane().add(Box.createRigidArea(new Dimension(0, 300)), c);

        c.gridx = 1;
        c.gridy = 2;
        getContentPane().add(runeScape3, c);

        c.gridx = 1;
        c.gridy = 3;
        getContentPane().add(Box.createRigidArea(new Dimension(0, 20)), c);

        c.gridx = 1;
        c.gridy = 4;
        getContentPane().add(oldschool, c);

        /*
            Sets the frame size
         */
        setSize(1024, 600);

    }
}
