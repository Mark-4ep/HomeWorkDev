package org.example.http;


import org.example.IncorrectInputException;

import java.io.*;
import java.net.URL;

public class HttpStatusImageDownloader {
    private static final HttpStatusChecker HTTP_STATUS_CHECKER = new HttpStatusChecker();

    public void downloadStatusImage(int code) {

        try {
            String uri = HTTP_STATUS_CHECKER.getStatusImage(code);

            URL url = new URL(uri);

            File dir = new File("Images");
            dir.mkdir();

            String fileName = "Cat" + code + ".jpg";
            String fileDestinationFolder = "./Images/";

            InputStream inputStream = url.openStream();
            OutputStream outputStream = new FileOutputStream(fileDestinationFolder + fileName);

            byte[] buffer = new byte[2048];
            int length;

            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            inputStream.close();
            outputStream.close();

            System.out.println("Cat with code " + code + " was downloaded");

        } catch (IncorrectInputException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
