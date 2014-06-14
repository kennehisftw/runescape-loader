package io.github.kennehisftw.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Kenneth on 6/13/2014.
 */
public class ImagePanel extends JPanel {

    /*
        The background image to be set on the panel
     */
    private Image background;

    /*
        Instantiates the class and sets the image
     */
    public ImagePanel(Image image) {
        this.background = image;
    }

    /**
     * Sets the image to the panel if the image isn't null by using the panels graphics properties
     * @param graphics
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if(background != null) {
            graphics.drawImage(background, 0, 0, getWidth(), getHeight(),  null);
        }
    }

}
