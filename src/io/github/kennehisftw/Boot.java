package io.github.kennehisftw;

import com.alee.laf.WebLookAndFeel;
import io.github.kennehisftw.loader.RSApplet;
import io.github.kennehisftw.swing.UserdataSelector;
import io.github.kennehisftw.utils.UserLoader;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kenneth on 6/12/2014.
 */
public class Boot {

    /*
        Create a new JFrame object
     */
    private final JFrame frame;

    /*
        Create a new UserLoader instance
     */
    private UserLoader userLoader;

    /*
        Create an applet object
     */
    private RSApplet applet;

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
    private final JMenuItem hiscoresButton, userDataButton, exitButton;

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
        userDataButton = new JMenuItem("Set user defaults");
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
        fileMenu.add(userDataButton);
        fileMenu.add(new JSeparator());
        fileMenu.add(exitButton);

        /*
            Add the action listener for the WorldSelection button
         */
        userDataButton.addActionListener(action -> {
            final UserdataSelector selector = new UserdataSelector(userLoader);
            selector.setVisible(true);
        });

        /*
            Instantiate the UserLoader
         */
        userLoader = new UserLoader();

        /*
            Instantiate the applet instance with the preferred parameters.
         */
        applet = new RSApplet(userLoader.getProperty("home-world").isEmpty() ? 1 :
                Integer.parseInt(userLoader.getProperty("home-world")),
                userLoader.getProperty("client-type").isEmpty() ? userLoader.getProperty("client-type").equals("rs3") :
                        userLoader.getProperty("client-type").equals("oldschool")
        );

        /*
            Set the preferred size of the parent frame
         */
        frame.setPreferredSize(new Dimension(800, 600));

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

    /**
     * Sets the RSApplet object
     * @param applet the new applet
     */
    public void setApplet(RSApplet applet) {
        this.applet = applet;
    }

    /**
     * Getter for the parent frame
     * @return JFrame parent
     */
    public JFrame getParent() {
        return frame;
    }

    /**
     * Getter for rsapplet to easily manipulate it
     * @return RSApplet object
     */
    public RSApplet getApplet() {
        return applet;
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
