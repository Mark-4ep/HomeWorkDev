package org.example.ServiceDB;

import org.example.Prefs.Prefs;
import org.example.ReadFile.ReadFile;

public class DatabaseInitService {

    public static void main(String[] args) {

        Database database = Database.getInstance();
        DatabaseInitService.InitDb(database);

    }

    public static void InitDb(Database database) {
        String initDbFileName = new Prefs().getString(Prefs.INIT_DB_SQL_FILE_PATH);

        ReadFile readFile = new ReadFile();
        readFile.setSql(initDbFileName);

        for (String sql : readFile.getSql()) {
            database.executeUpdate(sql + ";");
        }
    }


}

