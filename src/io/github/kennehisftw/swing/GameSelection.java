package io.github.kennehisftw.swing;

import io.github.kennehisftw.GELookupForm;
import io.github.kennehisftw.loader.RSApplet;
import io.github.kennehisftw.utils.Utilities;
import io.github.kennehisftw.utils.screenshot.Imgur;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
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
    private Image rs3Image, osImage, backGround, icon;

    /*
        Creates the ImagePanel object
     */
    private final ImagePanel imagePanel;

    /*
        Create an applet object
     */
    private RSApplet applet;

    /*
        The TrayIcon object
     */
    private TrayIcon trayIcon;

    /**
     * The hide menu option
     */
    private MenuItem hide;

    /*
        Creates the ImgurUploader object
     */
    private Imgur imgur;

    /*
        Creates a new GELookup class
     */
    private GELookupForm geLookupForm;


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
        rs3Image = Utilities.getImage(Utilities.getContentDirectory() + "images/rs3.png");
        osImage = Utilities.getImage(Utilities.getContentDirectory() + "images/os.png");
        backGround = Utilities.getImage(Utilities.getContentDirectory() + "images/bg.png");
        icon = Utilities.getImage(Utilities.getContentDirectory() + "images/icon.png");


        /*
            Initializes the GELookup
         */
        geLookupForm = new GELookupForm();

        /*
            Initialize the tray icon
         */
        trayIcon = new TrayIcon(icon);
        trayIcon.setImageAutoSize(true);
        trayIcon.setPopupMenu(createMenu());
        trayIcon.setToolTip("RuneScape Loader");

        /*
            Add the icon to the system tray
         */
        try {
            SystemTray.getSystemTray().add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        /*
            Set the frame icon
         */
        setIconImage(icon);

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
            Instantiate the ImgurUploader
         */
        imgur = new Imgur();

        /*
            Sets the frame focusable for the keylistener to register
         */
        setFocusable(true);
        /*
            Adds a key adapter for screen shot functionality
         */
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println(e.getKeyCode());
                if(e.getKeyCode() == 122) {
                    screenshot();
                }
            }
        });

        /*
            Sets the frame size
         */
        setSize(1024, 600);
    }

    /**
     * Hide/show the frame
     */
    public void hideWindow() {
        setVisible(!isVisible());
        hide.setLabel(isVisible() ? "Hide" : "Show");
    }

    /**
     * Setup the menu for the tray icon
     *
     * @return The newly created menu
     */
    public PopupMenu createMenu() {
        PopupMenu menu = new PopupMenu();
        hide = new MenuItem("Hide");
        hide.addActionListener(listener ->  hideWindow());
        menu.add(hide);

        MenuItem geLookup = new MenuItem("GE Lookup");
        geLookup.addActionListener(listener -> {
            geLookupForm.setVisible(!geLookupForm.isVisible());
        });
        menu.add(geLookup);

        MenuItem screenshot = new MenuItem("ScreenShot");
        screenshot.addActionListener(listener -> screenshot());
        menu.add(screenshot);
        MenuItem item = new MenuItem("Exit");
        item.addActionListener(listener -> System.exit(0));
        menu.add(item);
        return menu;
    }

    public void screenshot() {
        System.out.println("Uploading screenshot");

        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        BufferedImage image = robot.createScreenCapture(getBounds());

        Utilities.writeImage(image);
        Thread thread = new Thread(() -> {
            String imageURL = null;
            try {
                imageURL = imgur.upload(image);
            } catch(IOException ex) {
                System.out.println("Error uploading screen shot.");
            }
            StringSelection stringSelection = new StringSelection(imageURL);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            trayIcon.displayMessage(
                    "Screen Shot Taken!",
                    "Uploaded Screen Shot to " + imageURL, TrayIcon.MessageType.INFO);
        });
        thread.start();
    }
}
