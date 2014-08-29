package io.github.kennehisftw.swing;

import io.github.kennehisftw.loader.RSApplet;
import io.github.kennehisftw.utils.Utilities;
import io.github.kennehisftw.utils.adventurerslog.AdventurersLogFrame;
import io.github.kennehisftw.utils.calculators.prayer.Prayer;
import io.github.kennehisftw.utils.grandexchange.GELookupForm;
import io.github.kennehisftw.utils.hiscores.HiscoresForm;
import io.github.kennehisftw.utils.screenshot.Imgur;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kenneth on 6/13/2014.
 */
public class GameSelection extends JFrame {

    /*
        The TrayIcon object
     */
    private final TrayIcon trayIcon;
    /*
        Creates the ImgurUploader object
     */
    private final Imgur imgur;
    /*
        Create an applet object
     */
    private RSApplet applet;
    /*
        Creates a new GELookup class
     */
    private GELookupForm geLookupForm;

    /*
        Creates a new HiscoresForm
     */
    private HiscoresForm hiscoresForm;

    /*
        Creates a new AdventurersLog form
     */
    private AdventurersLogFrame aLogForm;

    private Prayer prayerCalculator;

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
        Image rs3Image = Utilities.getImage(Utilities.getContentDirectory() + "images/rs3.png");
        Image osImage = Utilities.getImage(Utilities.getContentDirectory() + "images/os.png");
        Image backGround = Utilities.getImage(Utilities.getContentDirectory() + "images/bg.png");
        Image icon = Utilities.getImage(Utilities.getContentDirectory() + "images/icon.png");


        /*
            Initializes the GELookup on a new thread to pre-load the data.
         */
        Thread thread2 = new Thread(() -> geLookupForm = new GELookupForm());
        thread2.start();

        Thread thread3 = new Thread(() -> hiscoresForm = new HiscoresForm());
        thread3.start();

        Thread thread4 = new Thread(() -> aLogForm = new AdventurersLogFrame());
        thread4.start();

        Thread thread5 = new Thread(() -> prayerCalculator = new Prayer());
        thread5.start();


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
        ImagePanel imagePanel = new ImagePanel(backGround);

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
        JButton runeScape3 = new JButton(new ImageIcon(rs3Image));
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
            Thread thread = new Thread(applet::downloadAndCreate);
            thread.start();
        });

        JButton oldschool = new JButton(new ImageIcon(osImage));
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
            Thread thread = new Thread(applet::downloadAndCreate);
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
            Sets the frame size
         */
        setSize(1024, 600);
    }

    /**
     * Setup the menu for the tray icon
     *
     * @return The newly created menu
     */
    PopupMenu createMenu() {
        PopupMenu menu = new PopupMenu();

        Menu calculators = new Menu("Calculators");
        MenuItem prayerCalc = new MenuItem("Prayer");
        prayerCalc.addActionListener(listener -> prayerCalculator.setVisible(!prayerCalculator.isVisible()));
        calculators.add(prayerCalc);
        menu.add(calculators);

        MenuItem aLog = new MenuItem("Adventurer's Log");
        aLog.addActionListener(listener -> aLogForm.setVisible(!aLogForm.isVisible()));
        menu.add(aLog);

        MenuItem geLookup = new MenuItem("GE Lookup");
        geLookup.addActionListener(listener -> geLookupForm.setVisible(!geLookupForm.isVisible()));
        menu.add(geLookup);

        MenuItem statLookup = new MenuItem("Stat Lookup");
        statLookup.addActionListener(listener -> hiscoresForm.setVisible(!hiscoresForm.isVisible()));
        menu.add(statLookup);

        MenuItem screenshot = new MenuItem("ScreenShot");
        screenshot.addActionListener(listener -> Utilities.screenshot(this, imgur, trayIcon));
        menu.add(screenshot);

        MenuItem item = new MenuItem("Exit");
        item.addActionListener(listener -> System.exit(0));
        menu.add(item);
        return menu;
    }

    public Imgur getImgur() {
        return imgur;
    }

    public TrayIcon getTrayIcon() {
        return trayIcon;
    }
}
