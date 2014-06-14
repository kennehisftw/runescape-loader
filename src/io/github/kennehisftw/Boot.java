package io.github.kennehisftw;

import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import io.github.kennehisftw.swing.GameSelection;

import javax.swing.*;
import java.io.UnsupportedEncodingException;
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
    private final GameSelection selection;

    public Boot() {

        /*
            Instantiate the GameSelection window
         */
        selection = new GameSelection();

        /*
            Set the location of the window on screen
         */
        selection.setLocationRelativeTo(null);

        /*
            Set the selection window visible using SwingUtils
         */
        SwingUtilities.invokeLater(() -> {
            selection.setVisible(true);
        });
    }

    public static void main(String[] args) {

        /*
            Sets the LaF
         */
        try {
            UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        /*
            Create a new instance of Boot to start the program.
         */
        new Boot();
    }
}
