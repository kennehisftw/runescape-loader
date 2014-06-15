package io.github.kennehisftw;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import io.github.kennehisftw.swing.GameSelection;
import io.github.kennehisftw.utils.Constants;
import io.github.kennehisftw.utils.Utilities;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
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

        final File backgroundImage = new File(Utilities.getContentDirectory() + "images/bg.png");
        if(!backgroundImage.exists()) {
            System.out.println("Downloading images..");
            Utilities.downloadFile("https://dl.dropboxusercontent.com/u/9359719/images.zip", Utilities.getContentDirectory() + "/images/images.zip");
            try {
                final ZipFile zipFile = new ZipFile(Utilities.getContentDirectory() + "/images/images.zip");
                zipFile.extractAll(Utilities.getContentDirectory());
            } catch (ZipException e) {
                e.printStackTrace();
            }
        }




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
