package io.github.kennehisftw.utils.adventurerslog;

import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import io.github.kennehisftw.utils.Utilities;
import it.sauronsoftware.feed4j.bean.FeedItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;

public class AdventurersLogFrame extends JFrame {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
        } catch (UnsupportedLookAndFeelException | ParseException e) {
            e.printStackTrace();
        }
        AdventurersLogFrame frame = new AdventurersLogFrame();
        frame.setVisible(true);
    }

    public AdventurersLogFrame() {
        initComponents();
    }

    private void initComponents() {

        label1 = new JLabel();
        textField1 = new JTextField();
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnActionEvent();
                }
            }
        });
        button1 = new JButton();
        button1.addActionListener(l -> btnActionEvent());
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        scrollPane1 = new JScrollPane();
        model = new DefaultListModel<>();
        list1 = new JList<>(model);
        list1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        //======== this ========
        setFont(new Font("Tahoma", Font.PLAIN, 12));
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setIcon(new ImageIcon(Utilities.getImage(Utilities.getContentDirectory() + "images/skills/chat head.png")));

        //---- button1 ----
        button1.setText("Search");

        //---- label2 ----
        label2.setIcon(new ImageIcon(Utilities.getImage(Utilities.getContentDirectory() + "images/skills/full.png")));

        //---- label3 ----
        label3.setText("Username");
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setFont(label3.getFont().deriveFont(label3.getFont().getStyle() | Font.BOLD, label3.getFont().getSize() + 1f));

        //---- label4 ----
        label4.setIcon(new ImageIcon(Utilities.getImage(Utilities.getContentDirectory() + "images/skills/default banner.png")));

        //======== scrollPane1 ========
        {

            //---- list1 ----
            list1.setModel(model);
            scrollPane1.setViewportView(list1);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(scrollPane1)
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(button1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(label4, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(label4, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                        .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                                                .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(button1)
                                                .addGap(38, 38, 38))
                                        .addComponent(label2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(label1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
    }

    public void btnActionEvent() {
        String username = textField1.getText();
        model.clear();
        RSSLoader loader = new RSSLoader(username);

        File image = new File(Utilities.getContentDirectory() + "images/skills/Attack.png");

        for(FeedItem item : loader.getElements()) {
            try {
                model.addElement(
                        "<html>" +
                                "<img src=\""+getIconURL(item.getDescriptionAsText())+"\" alt=\"some_text\">" +
                                "&nbsp;&nbsp;&nbsp;&nbsp;" + item.getDescriptionAsText() +
                                "</html>"
                );
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        label1.setIcon(new ImageIcon(Utilities.downloadImage("http://services.runescape.com/m=avatar-rs/" + textField1.getText() + "/chat.png")));
        label1.repaint();
        label1.revalidate();

        label4.setIcon(new ImageIcon(Utilities.downloadImage("http://services.runescape.com/m=avatar-rs/" + textField1.getText() + "/ownclan.png")));
        label4.repaint();
        label4.revalidate();

        label2.setIcon(new ImageIcon(Utilities.downloadImage("http://services.runescape.com/m=avatar-rs/" + textField1.getText() + "/full.png")));
        label2.repaint();
        label2.revalidate();

        label3.setText(textField1.getText());
        label3.repaint();
        label3.revalidate();

        list1.repaint();
        list1.revalidate();
    }

    public URL getIconURL(String element) throws MalformedURLException {

        File image = new File(Utilities.getContentDirectory() + "images/skills/skillbar.png");

        if(element.contains("Daemonheim")) {
            image = new File(Utilities.getContentDirectory() + "images/skills/Dungeoneering.png");
        } else if(element.contains("treasure trail")) {
            image = new File(Utilities.getContentDirectory() + "images/skills/clue.png");
        } else {
            File parent = new File(Utilities.getContentDirectory() + "images/skills/");
            for (File child : parent.listFiles()) {
                if (element.contains(child.getName().substring(0, child.getName().lastIndexOf(".")))) {
                    return child.toURI().toURL();
                }
            }
        }
        return image.toURI().toURL();
    }

    private JLabel label1;
    private JTextField textField1;
    private JButton button1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JScrollPane scrollPane1;
    private JList<String> list1;
    private DefaultListModel<String> model;
}

