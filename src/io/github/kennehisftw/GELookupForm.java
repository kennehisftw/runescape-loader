package io.github.kennehisftw;

/**
 * Created by Kenneth on 6/14/2014.
 */

import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.ParseException;

public class GELookupForm extends JFrame {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        GELookupForm geLookupForm = new GELookupForm();
        SwingUtilities.invokeLater(() -> geLookupForm.setVisible(true));
    }

    public GELookupForm() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - asdf asdf
        textField1 = new JTextField();
        button1 = new JButton();
        scrollPane1 = new JScrollPane();
        list1 = new JList();
        panel1 = new JPanel();
        label1 = new JLabel();
        panel2 = new JPanel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        button2 = new JButton();
        label7 = new JLabel();
        label8 = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();

        //---- button1 ----
        button1.setText("Search");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(list1);
        }

        //======== panel1 ========
        {
            panel1.setBorder(new TitledBorder("Details"));

            //---- label1 ----
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            label1.setIcon(new ImageIcon("C:\\Users\\Kenneth\\Desktop\\New folder\\resources\\largepile.png"));

            //======== panel2 ========
            {
                panel2.setBorder(new TitledBorder("Price Information"));

                //---- label2 ----
                label2.setText("CurrentPrice:");
                label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 1f));

                //---- label3 ----
                label3.setText("Today's Change:");
                label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 1f));

                //---- label4 ----
                label4.setText("30 Day Change:");
                label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 1f));

                //---- label5 ----
                label5.setText("90 Day Change:");
                label5.setFont(label5.getFont().deriveFont(label5.getFont().getSize() + 1f));

                //---- label6 ----
                label6.setText("180 Day Change:");
                label6.setFont(label6.getFont().deriveFont(label6.getFont().getSize() + 1f));

                //---- button2 ----
                button2.setText("View Graph");

                GroupLayout panel2Layout = new GroupLayout(panel2);
                panel2.setLayout(panel2Layout);
                panel2Layout.setHorizontalGroup(
                        panel2Layout.createParallelGroup()
                                .addGroup(panel2Layout.createSequentialGroup()
                                        .addGroup(panel2Layout.createParallelGroup()
                                                .addComponent(label5)
                                                .addComponent(label2)
                                                .addComponent(label4)
                                                .addComponent(label3))
                                        .addContainerGap(185, Short.MAX_VALUE))
                                .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(label6)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                                        .addComponent(button2))
                );
                panel2Layout.setVerticalGroup(
                        panel2Layout.createParallelGroup()
                                .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(label2)
                                        .addGap(16, 16, 16)
                                        .addComponent(label3)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(label4)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(label5)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                                        .addComponent(label6))
                                .addGroup(panel2Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(button2))
                );
            }

            //---- label7 ----
            label7.setText("Off-hand pile of coins");
            label7.setFont(label7.getFont().deriveFont(label7.getFont().getSize() + 2f));

            //---- label8 ----
            label8.setText("A powerful pile of coins");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(panel1Layout.createParallelGroup()
                                            .addComponent(label7)
                                            .addComponent(label8))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
                            .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                    .addGroup(panel1Layout.createParallelGroup()
                                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addComponent(label7)
                                                    .addGap(21, 21, 21)
                                                    .addComponent(label8)))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(button1, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(button1)
                                                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
                                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
    }

    private JTextField textField1;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JList list1;
    private JPanel panel1;
    private JLabel label1;
    private JPanel panel2;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JButton button2;
    private JLabel label7;
    private JLabel label8;
}

