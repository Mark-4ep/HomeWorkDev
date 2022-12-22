package org.example;

import org.example.http.HttpImageStatusCli;
import org.example.http.HttpStatusImageDownloader;

public class Main {
    public static void main(String[] args) {
        HttpStatusImageDownloader httpStatusImageDownloader = new HttpStatusImageDownloader();
        httpStatusImageDownloader.downloadStatusImage(200);

        HttpImageStatusCli statusCli = new HttpImageStatusCli();
        statusCli.askStatus();

    }
}