package io.github.kennehisftw.utils.grandexchange;

/**
 * Created by Kenneth on 6/14/2014.
 */

import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import io.github.kennehisftw.utils.Utilities;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

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

    private Map<String, Integer> map = new LinkedHashMap<>();

    public GELookupForm() {

        Utilities.downloadFile("http://pastebin.com/raw.php?i=5M8NW38G",
                Utilities.getContentDirectory() + "data/items.txt");

        System.out.println("Loading map..");
        BufferedReader reader = null;
        File file = null;
        try {
            file = new File(Utilities.getContentDirectory() + "data/items.txt");
            reader = new BufferedReader(new InputStreamReader(file.toURI().toURL().openStream()));
            String input;
            while((input = reader.readLine()) != null) {
                String[] data = input.split(":");
                map.putIfAbsent(data[1], Integer.parseInt(data[0]));
            }
        } catch(IOException a) {
            System.out.println("Error loading map data");
        }
        initComponents();
    }

    private void initComponents() {
        setTitle("Grand Exchange Lookup");

        model = new DefaultListModel<>();

        textField1 = new JTextField();
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                model.removeAllElements();
                if(!textField1.getText().isEmpty() && textField1.getText().length() >= 2) {
                    for (String str : map.keySet()) {
                        if (str.toLowerCase().contains(textField1.getText().toLowerCase())) {
                            model.addElement(str);
                        }
                    }
                }
            }
        });
        scrollPane1 = new JScrollPane();
        list1 = new JList(model);
        list1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String selectedItem = (String) list1.getSelectedValue();
                final GEItemLookup lookup = new GEItemLookup(map.get(selectedItem));
                final Item item = lookup.getItems()[0];

                System.out.println(item.getLargeIcon().replace("4478_", "4485_"));

                Image icon = null;
                try {
                    icon = ImageIO.read(new URL(item.getLargeIcon().replace("4478_", "4485_")));
                } catch(IOException ex) {
                    ex.printStackTrace();
                }
                System.out.println(icon.getWidth(null) + ":"+ icon.getHeight(null));
                label1.setIcon(new ImageIcon(icon));
                label1.repaint();
                label1.revalidate();


                label7.setText(item.getName());

                String description = item.getDescription();

                label8.setText("<html>"+ description + "</html>");
                label2.setText("<html> Current price: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                        + (item.getPrices().getCurrent().getPrice().contains("-") ? "<font color='red'>" : "<font color='green'>")
                        + item.getPrices().getCurrent().getPrice() + "</html>"
                );
                label6.setText("<html> Today's change: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                                (item.getPrices().getToday().getPrice().contains("-") ? "<font color='red'>" : "<font color='green'>")
                                + item.getPrices().getToday().getPrice() + "</html>"
                );
                label5.setText("<html> 30 Day Change: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                                (item.getPrices().getDay30().getChange().contains("-") ? "<font color='red'>" : "<font color='green'>")
                                + item.getPrices().getDay30().getChange() + "</html>"
                );
                label4.setText("<html> 90 Day Change: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                                (item.getPrices().getDay90().getChange().contains("-") ? "<font color='red'>" : "<font color='green'>")
                                + item.getPrices().getDay90().getChange() + "</html>"
                );
                label3.setText("<html> 180 Day Change: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                                (item.getPrices().getDay180().getChange().contains("-") ? "<font color='red'>" : "<font color='green'>")
                                + item.getPrices().getDay180().getChange() + "</html>"
                );

                SwingUtilities.updateComponentTreeUI(GELookupForm.this);
            }
        });
        panel1 = new JPanel();
        label1 = new JLabel();
        panel2 = new JPanel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        button2 = new JButton();
        button2.setEnabled(false);
        label7 = new JLabel();
        label7.setFont(label7.getFont().deriveFont(Font.BOLD));
        label8 = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(list1);
        }

        //======== panel1 ========
        {
            panel1.setBorder(new TitledBorder("Details"));

            //---- label1 ----
            label1.setHorizontalAlignment(SwingConstants.CENTER);

            //======== panel2 ========
            {
                panel2.setBorder(new TitledBorder("Price Information"));

                //---- label2 ----
                label2.setText("Current Price");
                label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD));

                //---- label3 ----
                label3.setText("180 Day change:");

                //---- label4 ----
                label4.setText("90 Day change:");

                //---- label5 ----
                label5.setText("30 Day change:");

                //---- label6 ----
                label6.setText("Today's change:");

                GroupLayout panel2Layout = new GroupLayout(panel2);
                panel2.setLayout(panel2Layout);
                panel2Layout.setHorizontalGroup(
                        panel2Layout.createParallelGroup()
                                .addComponent(label3, GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                                .addComponent(label2, GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                                .addComponent(label4, GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                                .addComponent(label5, GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                                .addComponent(label6, GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                );
                panel2Layout.setVerticalGroup(
                        panel2Layout.createParallelGroup()
                                .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(label2)
                                        .addGap(18, 18, 18)
                                        .addComponent(label6)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                                        .addComponent(label5)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(label4)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(label3))
                );
            }

            //---- label7 ----
            label7.setFont(label7.getFont().deriveFont(label7.getFont().getSize() + 2f));

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
                            .addComponent(panel2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                                        .addComponent(textField1, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(scrollPane1))
                                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
    }

    private JTextField textField1;
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
    private DefaultListModel<String> model;
}

