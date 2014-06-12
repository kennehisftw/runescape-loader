package io.github.kennehisftw.swing;

import com.alee.laf.WebLookAndFeel;
import io.github.kennehisftw.Boot;
import io.github.kennehisftw.utils.UserLoader;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kenneth on 6/12/2014.
 */
public class UserdataSelector extends JFrame {

    /*
        Creates a new JRadioButton object for rs3 selection
     */
    private final JRadioButton rs3Button;


    /*
        Creates a new UserLoader instance to store our settings
     */
    private final UserLoader loader;

    /*
        Creates a new JRadioButton object for 07 selection
     */
    private final JRadioButton oldschoolButton;

    /*
        Creates a new JtextField to store the account name
     */
    private final JTextField accountName;

    /*
        Creates a new JSpinner object for world selection
     */
    private final JSpinner spinner;

    /*
        Creates a new JButton for applying the selections
     */
    private final JButton applyButton;

    /*
        Create a JPanel for the butons/spinners to be placed on for easier swing management
     */
    private final JPanel buttonPanel;

    /*
        Creates a new SpinnerModel object
     */
    private final SpinnerModel spinnerModel;

    public UserdataSelector(final UserLoader loader) {
        super("Default Information");

        /*
            Assign the UserLoader object
         */
        this.loader = loader;

        /*
            Set the layout of the parent contentPane()
         */
        getContentPane().setLayout(new BorderLayout());

        /*
            Instantiate the textArea
         */
        accountName = new JTextField(20);
        accountName.setText(loader.getProperty("user"));

        /*
            Add a tooltip to explain what it is
         */
        accountName.setToolTipText("Your IGN to pull Hiscore data");

        /*
            Instantiate the toggleButton objects
         */
        rs3Button = new JRadioButton("RuneScape 3");
        oldschoolButton = new JRadioButton("OldSchool Runescape");

        /*
            Set the correct button selected based on user data
         */
        final String clientType = loader.getProperty("client-type");
        if(!clientType.isEmpty()) {
            if(clientType.equals("rs3")) {
                rs3Button.setSelected(true);
            } else {
                oldschoolButton.setSelected(true);
            }
        }

        /*
            Creates a spinner model to better control the spinner
         */
        spinnerModel = new SpinnerNumberModel(loader.getProperty("home-world").isEmpty() ? 1 :
                Integer.parseInt(loader.getProperty("home-world")), 0, 200, 1); //default value,lower bound,upper bound,increment by
        /*
            Initialize the JSpinner
         */
        spinner = new JSpinner(spinnerModel);

        /*
            Set the default width of the JSpinner
         */
        final Dimension dims = spinner.getSize();
        dims.width += 75;
        spinner.setSize(dims);

         /*
            Add action listeners to rs3Button to set 07 button to unselected
          */
        rs3Button.addActionListener(listener -> oldschoolButton.setSelected(false));

        /*
            Add action listeners to oldschoolButton to set rs3 button to unselected
         */
        oldschoolButton.addActionListener(listener -> rs3Button.setSelected(false));

        /*
            Initialize the buttonPanel
         */
        buttonPanel = new JPanel();

        /*
            Set the Layout of the ButtonPanel
         */
        buttonPanel.setLayout(new FlowLayout());
        /*
            Add the componants to the buttonPanel
         */
        buttonPanel.add(accountName);
        buttonPanel.add(spinner);
        buttonPanel.add(rs3Button);
        buttonPanel.add(oldschoolButton);

        /*
            Initiate the applyButton
         */
        applyButton = new JButton("Save!");
        /*
            Add an action listener to the button to close the window
         */
        applyButton.addActionListener(listener -> {
            dispose();
            System.out.println(isRS3() ? "RS3 Selected" : "Oldschool Selected");
            System.out.println("Selected world: "+ getWorld());
            System.out.println("IGN: "+ accountName.getText());
            loader.setProperty("user", accountName.getText());
            loader.setProperty("home-world", String.valueOf(getWorld()));
            loader.setProperty("client-type", isRS3() ? "rs3" : "oldschool");
            loader.save();
        });


        /*
            Add components to the parent frame
         */
        add(buttonPanel, BorderLayout.CENTER);
        add(applyButton, BorderLayout.SOUTH);
        /*
            Pack the parent frame
         */
        pack();

        /*
            Sets the default close operation
         */
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Gets the selected world
     * @return the value of the spinner model
     */
    public int getWorld() {
        return (int) spinnerModel.getValue();
    }

    /**
     * Determines what game mode should be opened.
     * @return true if rs3 should be the loaded game mode, false for oldschool
     */
    public boolean isRS3() {
        return rs3Button.isSelected();
    }

    public static void main(String[] args) {

        /*
            Set the default LaF
         */
        try {
            UIManager.setLookAndFeel(new WebLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        /*
            Create the WorldSelector object and set it visible
         */
        final UserdataSelector worldSelector = new UserdataSelector(null);
        worldSelector.setVisible(true);
    }
}
