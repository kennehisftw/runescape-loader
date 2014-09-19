package io.github.kennehisftw.utils.calculators.prayer;

import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * Created by Kenneth on 7/25/2014.
 */
public class Prayer extends JFrame {
    private static final int[] XP_TABLE = new int[120 + 2];

    static {
        int i = 0;
        for (int j = 0; j < 120; j++) {
            int k = j + 1;
            int l = (int) ((double) k + 300D * Math.pow(2D, (double) k / 7D));
            i += l;
            XP_TABLE[j + 2] = i / 4;
        }
    }


    private final DecimalFormat format = new DecimalFormat("###,###,###");
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JSpinner spinner1;
    private JComboBox<PrayerMethod> comboBox1;
    private JLabel label6;

    public Prayer() {
        super("Prayer Calculator");
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
        } catch (UnsupportedLookAndFeelException | ParseException e) {
            e.printStackTrace();
        }
        final Prayer prayer = new Prayer();
        prayer.pack();
        prayer.setVisible(true);
    }

    private long getExperienceAt(int level) {
        return XP_TABLE[level];
    }

    String html(String text) {
        return "<html>" + text + "</html>";
    }

    private void initComponents() {
        textField1 = new JTextField();
        JLabel label1 = new JLabel();
        textField2 = new JTextField();
        JLabel label2 = new JLabel();
        textField3 = new JTextField();
        JLabel label3 = new JLabel();
        spinner1 = new JSpinner();
        JLabel label4 = new JLabel();
        JButton button1 = new JButton();
        JLabel label5 = new JLabel();
        comboBox1 = new JComboBox<>(PrayerMethod.values());
        JPanel panel1 = new JPanel();
        label6 = new JLabel();
        JButton button2 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();

        //---- textField1 ----
        textField1.setText("Username");

        //---- label1 ----
        label1.setText("Display Name");
        label1.setHorizontalAlignment(SwingConstants.CENTER);

        //---- label2 ----
        label2.setText("Current XP");
        label2.setHorizontalAlignment(SwingConstants.CENTER);

        //---- label3 ----
        label3.setText("Target XP");
        label3.setHorizontalAlignment(SwingConstants.CENTER);

        //---- label4 ----
        label4.setText("Target Level");

        //---- button1 ----
        button1.setText("Calculate");
        button1.addActionListener(listener -> {
            final String currentXPString = textField2.getText();
            int currentXP = 0;
            if (currentXPString.isEmpty()) {
                label6.setText(html("Current Experience CANNOT be empty!"));
                return;
            }
            try {
                currentXP = Integer.parseInt(currentXPString);
            } catch (NumberFormatException exception) {
                label6.setText(html("Please enter only numbers into the experience boxes!"));
                return;
            }

            final String targetXPString = textField3.getText();
            int targetXP = 0;
            int targetLevel = 0;
            try {
                if (targetXPString.isEmpty()) {
                    targetLevel = (int) spinner1.getValue();
                    if (targetLevel == 0) {
                        label6.setText(html("You need to enter either a target experience or select a level!"));
                        return;
                    } else {
                        targetXP = (int) getExperienceAt(targetLevel);
                    }
                } else {
                    targetXP = Integer.parseInt(targetXPString);
                }
            } catch (NumberFormatException exception) {
                label6.setText(html("Please enter only numbers into the experience boxes!"));
                return;
            }
            if (currentXP > targetXP) {
                label6.setText(html("Current cannot be greater than target!"));
            } else {
                PrayerMethod method = (PrayerMethod) comboBox1.getSelectedItem();
                int difference = (int) ((targetXP - currentXP) / method.experience);
                label6.setText(html("You would need " + format.format(difference) + " " + method + (targetLevel == 0 ? " to reach " + format.format(targetXP) + " experience!" : " to reach level " + targetLevel + "!")));
            }

        });

        //---- button2 ----
        button2.setText("Load Hiscores");
        button2.addActionListener(listener -> {
            String username = textField1.getText();
            if (!username.isEmpty()) {
                final Hiscores hiscores = new Hiscores(username);
                final Skill prayer = hiscores.getSkills().get(6);
                textField2.setText(String.valueOf(prayer.getExperience()));
            }
        });

        //---- label5 ----
        label5.setText("Training Method");
        label5.setHorizontalAlignment(SwingConstants.CENTER);

        //======== panel1 ========
        {
            panel1.setBorder(new EtchedBorder());

            //---- label6 ----
            label6.setText("<html>You need 123,456 DRAGON_BONES to advance to level 99.</html>");
            label6.setHorizontalAlignment(SwingConstants.CENTER);
            label6.setFont(new Font("Tahoma", Font.PLAIN, 13));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(label6, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                                    .addContainerGap())
            );
            panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(label6, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                                    .addContainerGap())
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
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                                .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                                                                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(button2))
                                                                .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                                                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(label4))
                                                                .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                                                                        .addComponent(textField2, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(textField3, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(spinner1))))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addComponent(label5, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                                                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                                                .addGap(0, 188, Short.MAX_VALUE)
                                                                .addComponent(button1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(comboBox1, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                                                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addContainerGap())))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(label1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button2))
                                .addGap(32, 32, 32)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label2)
                                        .addComponent(label3)
                                        .addComponent(label4))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(textField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(spinner1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(label5)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button1)
                                .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private enum PrayerMethod {
        IMPIOUS_ASHES(1, 4),
        BONES(1, 4.5),
        WOLF_BONES(1, 4.5),
        BURNT_BONES(1, 4.5),
        MONKEY_BONES(1, 5),
        BAT_BONES(1, 5.33),
        ACCURSED_ASHES(1, 12.5),
        BIG_BONES(1, 15),
        CURVED_BONES(1, 15),
        JOGRE_BONES(1, 15),
        LONG_BONES(1, 15),
        ZOGRE_BONES(1, 22.5),
        SHAIKAHAN_BONES(1, 25),
        BABYDRAGON_BONES(1, 30),
        LOAR_REMAINS(1, 33),
        PHRIN_REMAINS(1, 46.5),
        WYVERN_BONES(1, 50),
        RIYL_REMAINS(1, 59.5),
        INFERNAL_ASHES(1, 62.5),
        DRAGON_BONES(1, 72),
        BURN_VYREWATCH_CORPSE(1, 80),
        ASYN_REMAINS(1, 82.5),
        FAYRG_BONES(1, 84),
        RAURG_BONES(1, 96),
        FIYR_REMAINS(1, 84),
        IMPIUS_URN(1, 120),
        DAGANNOTH_BONES(1, 125),
        AIRUT_BONES(1, 132.5),
        OURG_BONES(1, 140),
        FROST_DRAGON_BONES(1, 180),
        ACCURSED_URN(1, 375),
        INFERNAL_URN(1, 1875),
        ACCURSED_ASHES_ECTO(1, 50),
        BIG_BONES_ECTO(1, 60),
        ZOGRE_BONES_ECTO(1, 90),
        DRAGON_BONES_ALTER(1, 252);

        private final int requiredLevel;
        private final double experience;

        private PrayerMethod(int requiredLevel, double experience) {
            this.requiredLevel = requiredLevel;
            this.experience = experience;
        }

        @Override
        public String toString() {
            return name().charAt(0) + name().substring(1).toLowerCase().replaceAll("_", " ");
        }

    }

}