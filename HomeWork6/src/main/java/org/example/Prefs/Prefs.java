package org.example.Prefs;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Prefs {
    public static final String DB_JDBC_CONNECTION_URL = "jdbcUrl";
    public static final String DB_JDBC_NAME_USER = "nameUser";
    public static final String DB_JDBC_PASSWORD = "password";

    public static final String INIT_DB_SQL_FILE_PATH = "initDbSql";
    public static final String POPULATE_DB_SQL_FILE_PATH = "populateDbSql";
    public static final String FIND_LONGEST_PROJECT_FILE_PATH = "findLongestProject";
    public static final String FIND_MAX_PROJECTS_CLIENT_FILE_PATH = "findMaxProjectsClient";
    public static final String FIND_MAX_SALARY_WORKER_FILE_PATH = "findMaxSalaryWorker";
    public static final String FIND_YOUNGEST_ELDEST_WORKERS_FILE_PATH = "findYoungestEldestWorkers";
    public static final String PRINT_PROJECT_PRICES_FILE_PATH = "printProjectPrices";



    public static final String DEFAULT_PREFS_FILENAME = "prefs.json";


    private Map<String, Objects> prefs = new HashMap<>();

    public Prefs() {
        this (DEFAULT_PREFS_FILENAME);
    }

    public Prefs(String filename) {
        try {
            String join = String.join(
                    "\n",
                    Files.readAllLines(Path.of(filename))
            );

            TypeToken<?> typeToken = TypeToken.getParameterized(
                    Map.class,
                    String.class,
                    Object.class
            );
            prefs = new Gson().fromJson(join, typeToken.getType());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getString(String key) {
        return getPref(key).toString();
    }

    public Object getPref(String key) {
        return prefs.get(key);
    }
}
