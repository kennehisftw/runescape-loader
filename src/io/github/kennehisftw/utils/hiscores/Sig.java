package io.github.kennehisftw.utils.hiscores;

import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.text.ParseException;


public class Sig extends JFrame {

    public Sig() {
        super("Dyn Sig");
        try {
            UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
        } catch (UnsupportedLookAndFeelException | ParseException e) {
            e.printStackTrace();
        }
        String user = "http://wyn.x10.mx/sig/test1/rsImage.php?user=" + JOptionPane.showInputDialog(null, "User name", JOptionPane.OK_OPTION);
        EventQueue.invokeLater(() -> {
            try {
                final URL address = new URL(user);
                BufferedImage image = ImageIO.read(address);
                JLabel label = new JLabel(new ImageIcon(image));
                setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                getContentPane().add(label, BorderLayout.CENTER);
                JTextField u = new JTextField("[img]" + user + "[/img]");
                u.setEnabled(true);
                u.setEditable(false);
                getContentPane().add(u, BorderLayout.SOUTH);
                pack();
                setLocationRelativeTo(null);
                setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}