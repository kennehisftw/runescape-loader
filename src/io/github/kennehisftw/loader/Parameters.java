package io.github.kennehisftw.loader;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kenneth on 6/11/2014.
 */
class Parameters {

    /*
        Generic map for holding all the parameters we're going to parse
     */
    private static final Map<String, String> PARAMETER_MAP = new HashMap<>();

    /**
     * Creates a new instance of the parameters class.
     *
     * @param oldschool true for 07 false for rs3
     */
    public Parameters(final boolean oldschool) {
        /*
             Creates a new String with the arguments we defined to set the game and world we wish to load into
         */
        final String parameterURL = oldschool ? "http://oldschool42.runescape.com/l=0/jav_config.ws" : "http://www.runescape.com/k=3/l=0/jav_config.ws";

        /*
            Creates a null urlConnection object to be instantiated later.
         */
        URLConnection urlConnection = null;

        /*
            Initiate the URLConnection within a try/catch block for error handling.
         */
        try {
            urlConnection = new URL(parameterURL).openConnection();
        } catch (IOException exception) {
            exception.printStackTrace();

            String[] options = new String[]{"OK"};
            JOptionPane.showOptionDialog(null, "Please ensure your internet connection is working properly and restart the client.", "Error connecting to website", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
        }

         /*
            Set the User Agent for the URLConnection
         */
        urlConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:30.0) Gecko/20100101 Firefox/30.0");

        /*
            Set the default timeout for the connection
         */
        urlConnection.setConnectTimeout(8000);

        /*
            Create a null BufferedReader object to be instantiated later.
         */
        BufferedReader reader = null;

        /*
            Instantiate the BufferedReader with the input stream from the URLConnection.
         */
        try {
            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        } catch (IOException exception) {
            exception.printStackTrace();

            String[] options = new String[]{"OK"};
            JOptionPane.showOptionDialog(null, "Could not initialize reader, please restart the client.", "Error reading stream", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
        }

        /*
            Temporary line for storing the contents of the BufferedReader as a string
         */
        String input;

        /*
            Loop through all lines in the BufferedReader and assign @input to the line.
         */
        try {
            while ((input = reader.readLine()) != null) {

                /*
                    Check if the line contains an identifier, in this case, an = sign. Parse it accordingly.
                 */
                if (input.contains("=")) {

                    /*
                        Strip all formatting from the input line.
                     */
                    input = input.replaceAll("param=", "");

                    /*
                        Create a String array with the parts split at =
                     */
                    final String[] parts = input.split("=");


                    /*
                        Handle each amount of parts within the if/else statement
                     */
                    if (parts.length == 1) {
                        /*
                            If the length is 1, store the key with a blank parameter
                         */
                        addParam(parts[0], "");
                    } else if (parts.length == 2) {
                        /*
                            If the length is two, add 0 for the key and 1 for the value
                         */
                        addParam(parts[0], parts[1]);
                    } else if (parts.length == 3) {
                        /*
                            If the length is three, add 0 for the key and 1 and 2 combined with an = sign for the value
                         */
                        addParam(parts[0], parts[1] + "=" + parts[2]);
                    } else if (parts.length == 4) {
                        /*
                            If the length is four, add 0 for the key, and 1, 2 and 3 combined with an = sign for the value
                         */
                        addParam(parts[0], parts[1] + "=" + parts[2] + "=" + parts[3]);
                    }
                }
            }
            /*
                Close the BufferedReader
             */
            reader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
            String[] options = new String[]{"OK"};
            JOptionPane.showOptionDialog(null, "Please check your internet connection and restar the client!", "Error loading data", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
        }

        System.out.println(getParameter("initial_jar"));
    }

    /**
     * Stores the given data in the Parameter map if it does not already exist.
     *
     * @param key The identification key
     * @param val The parameter data
     */
    private void addParam(String key, String val) {
        PARAMETER_MAP.putIfAbsent(key, val);
    }

    /**
     * Returns the value based on the key. If the key isn't found, it returns a blank string.
     *
     * @param key
     * @return the retrieved parameter
     */
    public String getParameter(String key) {
        return PARAMETER_MAP.containsKey(key) ? PARAMETER_MAP.get(key) : "";
    }

}
