package io.github.kennehisftw.swing;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kenneth on 6/12/2014.
 */
public class WorldSelector extends JFrame {

    /*
        Creates a new JToggleButton object
     */
    private final JToggleButton toggleButton;

    public WorldSelector() {
        super("World Selector");

        /*
            Set the layout of the parent contentPane()
         */
         getContentPane().setLayout(new BorderLayout());

        /*

         */

        /*
            Instantiate the toggleButton object
         */
        toggleButton = new JToggleButton("RS3/07");
    }

    public static void main(String[] args) {
        new WorldSelector();
    }
}
