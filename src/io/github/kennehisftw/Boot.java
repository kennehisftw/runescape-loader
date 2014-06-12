package io.github.kennehisftw;

import com.alee.laf.WebLookAndFeel;
import io.github.kennehisftw.loader.RSApplet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kenneth on 6/12/2014.
 */
public class Boot {

    /*
        Create a new JFrame object
     */
    private final JFrame frame;

    /*
        Create an applet object
     */
    private final RSApplet applet;

    /*
        Creates a JMenuBar object
     */
    private final JMenuBar menuBar;

    /*
        Creates the JMenu objects for the JMenuBar
     */
    private final JMenu fileMenu;

    /*
        Creates the JMenuItems for the fileMenu
     */
    private final JMenuItem hiscoresButton, worldSelectButton, exitButton;

    public Boot() {
        /*
            Instantiate the frame instance with the title as an arguement
         */
        frame = new JFrame("RuneScape");

        /*
            Instantiates the MenuBar instance
         */
        menuBar = new JMenuBar();

        /*
            Attaches the menubar to the parent frame
         */
        frame.setJMenuBar(menuBar);

        /*
            Instantiate the JMenu file
         */
        fileMenu = new JMenu("File");

        /*
            Set the weight of the fileMenu to go over the applet
         */
        JPopupMenu.setDefaultLightWeightPopupEnabled(false);

        /*
            Attach the file menu to the menubar
         */
        menuBar.add(fileMenu);

        /*
            Instantiate the menu buttons
         */
        hiscoresButton = new JMenuItem("Hiscores lookup");
        worldSelectButton = new JMenuItem("World select");
        exitButton = new JMenuItem("Exit");

        /*
            Add action listener to exitButton
         */
        exitButton.addActionListener(listener -> {
            System.exit(0);
        });

        /*
            Add all JMenuItems to the JMenu
         */
        fileMenu.add(hiscoresButton);
        fileMenu.add(worldSelectButton);
        fileMenu.add(new JSeparator());
        fileMenu.add(exitButton);

        /*
            Instantiate the applet instance with the preferred parameters.
         */
        applet = new RSApplet(42, false);

        /*
            Set the preferred size of the parent frame
         */
        frame.setPreferredSize(new Dimension(1280, 720));

        /*
            Add the applet to the content pane of the parent frame
         */
        frame.getContentPane().add(applet);

        /*
            Call the pack method on the parent frame to set child components
         */
        frame.pack();

        /*
            Set the applet visible from the SwingUtils thread
         */
        SwingUtilities.invokeLater(() -> frame.setVisible(true));

        /*
            run the method that downloads the jar and creates the applet
         */
        applet.downloadAndCreate();

        /*
            Refresh all components on the parent frame
         */
        SwingUtilities.updateComponentTreeUI(frame);
    }

    public static void main(String[] args) {
        /*
            Set the default LookAndFeel for the User Interface
         */
        try {
            UIManager.setLookAndFeel(new WebLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        /*
            Create a new instance of Boot to start the program.
         */
        new Boot();
    }
}
