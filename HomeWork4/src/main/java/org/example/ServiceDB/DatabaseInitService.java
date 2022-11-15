package org.example.ServiceDB;

import org.example.ReadFile.ReadFile;

public class DatabaseInitService {
    private static final String LINK_FILE = "/Users/designer/Documents/GitHub/HomeWorkDev/HomeWork4/sql/init_db.sql";


    public static void main(String[] args) {

        Database database = Database.getInstance();

        ReadFile readFile = new ReadFile();
        readFile.setSql(LINK_FILE);

       for (String sql : readFile.getSql()) {
           database.executeUpdate(sql + ";");
        }
    }


}

