package io.github.kennehisftw.utils.hiscores;

import io.github.kennehisftw.swing.VerticalFlowLayout;
import io.github.kennehisftw.utils.Utilities;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

/**
 * Created by Kenneth on 6/15/2014.
 */
public class HiscoresForm extends JFrame {

    private final DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###");

    private HiscoresLookup lookup;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - asdf asdf
    private JTextField textField1;
    private JCheckBox checkBox1;
    private JLabel label1;
    private JLabel label2;
    private JButton button1;
    private JPanel panel1;
    private JLabel label3;
    public HiscoresForm() {
        super("Player Stats Lookup");
        getContentPane().setLayout(new VerticalFlowLayout());
        initComponents();
        //setVisible(true);
    }

    private void btnActionEvent() {
        panel1.removeAll();
        lookup = new HiscoresLookup(textField1.getText(), !checkBox1.isSelected());
        label1.setText(textField1.getText());
        setTitle("Player Stats Lookup - " + textField1.getText());
        panel1.setLayout(new VerticalFlowLayout());
        for (Skill skill : lookup.getSkillMap().values()) {
            panel1.add(createSkillPanel(skill));
        }
        this.label2.setIcon(new ImageIcon(Utilities.downloadImage("http://services.runescape.com/m=avatar-rs/" + textField1.getText() + "/chat.png")));
        this.label2.repaint();
        this.label2.revalidate();

        this.label3.setIcon(new ImageIcon(Utilities.downloadImage("http://services.runescape.com/m=avatar-rs/" + textField1.getText() + "/ownclan.png")));
        this.label3.repaint();
        this.label3.revalidate();

        panel1.repaint();
        panel1.revalidate();
    }

    private JPanel createSkillPanel(Skill skill) {
        final JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setBackground(Color.WHITE);
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        panel.setToolTipText("<html>Experience: " + decimalFormat.format(skill.getExperience())
                + " <br>Rank: " + decimalFormat.format(skill.getRank()) + "</html>");
        label1.setIcon(new ImageIcon(Utilities.getImage(Utilities.getContentDirectory() + "images/skills/" + skill.getSkillName() + ".png")));

        label2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        label2.setText(skill.getSkillName());

        label3.setFont(new Font("Tahoma", Font.PLAIN, 11));
        label3.setForeground(Color.GRAY);
        label3.setText(skill.getLevel() == 1 ? "Unranked" : String.valueOf(skill.getLevel()));

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(label1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(label3)
                                                .addContainerGap(78, Short.MAX_VALUE))
                                        .addComponent(label2, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(label2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        return panel;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - asdf asdf
        textField1 = new JTextField();
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnActionEvent();
                }
            }
        });
        checkBox1 = new JCheckBox();
        label1 = new JLabel();
        label2 = new JLabel();
        button1 = new JButton();
        button1.addActionListener(listener -> btnActionEvent());
        panel1 = new JPanel();
        label3 = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();

        //---- checkBox1 ----
        checkBox1.setText("Oldschool");

        //---- label1 ----
        label1.setText("Username");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("Tahoma", Font.BOLD, 11));

        //---- label2 ----
        label2.setIcon(new ImageIcon(Utilities.getImage(Utilities.getContentDirectory() + "images/skills/chat head.png")));

        //---- button1 ----
        button1.setText("Search");

        //======== panel1 ========
        {
            panel1.setBackground(Color.white);
            panel1.setBorder(new EtchedBorder());

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
            );
            panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                            .addGap(0, 318, Short.MAX_VALUE)
            );
        }

        //---- label3 ----
        label3.setIcon(new ImageIcon(Utilities.getImage(Utilities.getContentDirectory() + "images/skills/default banner.png")));
        label3.setHorizontalAlignment(SwingConstants.CENTER);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addGap(9, 9, 9)
                                                                .addComponent(checkBox1, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(button1, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addComponent(label2)
                                                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                                                .addGap(53, 53, 53)
                                                .addComponent(label3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(17, 17, 17)
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addComponent(label1)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(label2))
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(checkBox1)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(button1))))
                                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(4, 4, 4))
        );
        setSize(585, 480);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}
