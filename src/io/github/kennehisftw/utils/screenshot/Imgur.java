package io.github.kennehisftw.utils.screenshot;

import com.google.gson.Gson;
import io.github.kennehisftw.utils.Constants;
import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Kenneth on 6/14/2014.
 */
public class Imgur {

    /**
     * Converts a BufferedImage into a Base64 string
     * @param image the image you wish to convert
     * @return the converted string
     * @throws UnsupportedEncodingException
     */
    private String toBase64(BufferedImage image) throws UnsupportedEncodingException {
        ByteArrayOutputStream output = null;
        try {
            output = new ByteArrayOutputStream();
            ImageIO.write(image, "PNG", output);
        } catch(IOException a) {
            a.printStackTrace();
        }
        return URLEncoder.encode(Base64.encodeBase64String(output.toByteArray()), "UTF-8");
    }

    /**
     * Uploads the image to imgur using their api
     * @param image the image you want to upload
     * @return the URL of the uploaded image
     * @throws IOException
     */
    public String upload(BufferedImage image) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) new URL(Constants.IMGUR_POST_URI).openConnection();

        String imageData = toBase64(image);

        connection.addRequestProperty("Authorization", "Client-ID " + Constants.CLIENT_ID);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod("POST");

        DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
        dos.writeBytes("image=" + imageData);
        dos.flush();
        dos.close();
        connection.disconnect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder builder = new StringBuilder();

        String input;
        while((input = reader.readLine()) != null) {
            builder.append(input);
        }

        String jsonData = builder.toString();
        System.out.println(jsonData);

        ImageData data = new Gson().fromJson(builder.toString(), ImageData.class);

        return data.getContents().getLink();
    }

    private class ImageData {
        ImageContents data;

        public ImageContents getContents() {
            return data;
        }
    }

    private class ImageContents {
        String link;

        public String getLink() {
            return link;
        }
    }
}
