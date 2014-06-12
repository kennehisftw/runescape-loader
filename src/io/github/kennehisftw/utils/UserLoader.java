package io.github.kennehisftw.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.util.Properties;

/**
 * Created by Kenneth on 6/12/2014.
 */
public class UserLoader {

    /*
        Create new Properties object
     */
    private final Properties properties;

    /*
        Constructor for UserLoader to help instantiation
     */
    public UserLoader() {
        /*
            Instantiate properties object
         */
        properties = new Properties();

        /*
            Load the user data
         */
        load();

        /*
            Prints all the stored data
         */
        for(Object key : properties.keySet()) {
            System.out.println(key + "->" + properties.get(key));
        }

    }

    /**
     * Loads the userdata from the properties file stored locally
     */
    public void load() {
        /*
            Create a new file with the preferred location of the properties file
         */
        final File propsFile = new File(System.getProperty("user.home") + "/rs-loader/props.properties");

        /*
            If the file doesn't exist, create a new blank file
         */
        if(!propsFile.exists()) {
            try {
                propsFile.getParentFile().mkdirs();
                propsFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /*
            Create a URLConnection based off of the properties file
         */
        URLConnection connection = null;
        try {
            connection = propsFile.toURI().toURL().openConnection();
        } catch(IOException ex) {
            ex.printStackTrace();
        }

        /*
            Load our files input stream into the properties object
         */
        try {
            properties.load(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the current user data
     */
    public void save() {
        /*
            Create a new FileWriter instance with the location of our properties file
         */
        FileWriter fileWriter = null;
        try {
           fileWriter = new FileWriter(System.getProperty("user.home") + "/rs-loader/props.properties");
        } catch(IOException ex) {
            ex.printStackTrace();
        }

        /*
            Save the properties file
         */
        try {
            properties.store(fileWriter, "User specific data for the RSLoader");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the selected property if it exists within the map
     * @param key the property identifier
     * @return the propterty value
     */
    public String getProperty(String key) {
        return properties.containsKey(key) ? properties.getProperty(key) : "";
    }

    /**
     * Stores properties to be saved in the loader properties file
     * @param key The identifier of the property
     * @param value The value of the property
     */
    public void setProperty(String key, String value) {
        properties.put(key, value);
    }

    public static void main(String[] args) {
        final UserLoader loader = new UserLoader();
        loader.setProperty("user", "Tiny Miaow");
        loader.setProperty("home-world", "42");
        loader.save();
    }

}
