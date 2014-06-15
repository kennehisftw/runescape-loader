package io.github.kennehisftw;

import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import io.github.kennehisftw.swing.GameSelection;
import io.github.kennehisftw.utils.Constants;
import io.github.kennehisftw.utils.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;

/**
 * Created by Kenneth on 6/12/2014.
 */
public class Boot {
    /*
        This class is kinda redundant now.
     */

    /*
        Create the GameSelection Window
     */
    private GameSelection selection;

    public Boot() {

        /*
            Download the required files
         */
        Utilities.downloadFile(Constants.CLIENT_ICON_URL, Utilities.getContentDirectory() + "images/icon.png");
        Utilities.downloadFile(Constants.BACKGROUND_URL, Utilities.getContentDirectory() + "images/bg.png");
        Utilities.downloadFile(Constants.OS_LOGO_URL, Utilities.getContentDirectory() + "images/os.png");
        Utilities.downloadFile(Constants.RS3_LOGO_URL, Utilities.getContentDirectory() + "images/rs3.png");
        Utilities.downloadFile(Constants.LOADING_IMAGE_URL, Utilities.getContentDirectory() + "images/loading.gif");

        Thread thread = new Thread(() -> Utilities.downloadFile("http://pastebin.com/raw.php?i=5M8NW38G",
                Utilities.getContentDirectory() + "data/items.txt"));
        thread.start();

        /*
            Set the selection window visible using SwingUtils
         */
        SwingUtilities.invokeLater(() -> {
            /*
            Instantiate the GameSelection window
         */
            selection = new GameSelection();

            /*
                Setup the listener.
             */
            selection.setFocusable(true);
            KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
                if(e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == KeyEvent.VK_F12) {
                    Utilities.screenshot(selection, selection.getImgur(), selection.getTrayIcon());
                }
                System.out.println(e.getKeyCode());
                return false;
            });
        /*
            Set the location of the window on screen
         */
            selection.setLocationRelativeTo(null);
            selection.setVisible(true);
        });



    }

    public static void main(String[] args) {
        /*
            Set the frame able to be decorated by look and feels
         */
        JFrame.setDefaultLookAndFeelDecorated(true);

        /*
            Sets the weight of the menus to prevent overlapping
         */
        JPopupMenu.setDefaultLightWeightPopupEnabled(false);

        /*
            Sets the LaF
         */
        try {
            UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
        } catch (UnsupportedLookAndFeelException | ParseException e) {
            e.printStackTrace();
        }

        /*
            Create a new instance of Boot to start the program.
         */
        new Boot();
    }
}
