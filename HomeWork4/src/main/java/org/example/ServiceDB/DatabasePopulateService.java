package org.example.ServiceDB;

import org.example.Prefs.Prefs;
import org.example.ReadFile.ReadFile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class DatabasePopulateService {
    private static PreparedStatement insertWorker;
    private static PreparedStatement insertClient;
    private static PreparedStatement insertProject;
    private static PreparedStatement insertProjectWorker;
    private static final String checkLineInsertWorker = "INSERT INTO worker (id, name, birthday, level, salary) ";
    private static final String checkLineInsertClient = "INSERT INTO client (id, name)";
    private static final String checkLineInsertProject = "INSERT INTO project (id, client_id, start_date, finish_date)";
    private static final String checkLineInsertProject_worker = "INSERT INTO project_worker (project_id, worker_id)";

    private static final String checkLineUseDb = "USE";

    private static Database database;
    private Connection connection;

    public DatabasePopulateService(Database database) throws Exception {

        connection = database.getConnection();
        insertWorker = connection.prepareStatement(
                "INSERT INTO worker (id, name, birthday, level, salary) VALUES (?,?,?,?,?)"
        );
        insertClient = connection.prepareStatement(
                "INSERT INTO client (id, name) VALUES (?,?)"
        );
        insertProject = connection.prepareStatement(
                "INSERT INTO project (id, client_id, start_date, finish_date) VALUES (?,?,?,?)"
        );
        insertProjectWorker = connection.prepareStatement(
                "INSERT INTO project_worker (project_id, worker_id) VALUES (?,?)"
        );
    }

    public static void main(String[] args) throws Exception {

        database = Database.getInstance();
        new DatabasePopulateService(database);
        String filename = new Prefs().getString(Prefs.POPULATE_DB_SQL_FILE_PATH);
        ReadFile readFile = new ReadFile();
        readFile.setSql(filename);

        for (String word : readFile.getSql()) {
            if (word.contains(checkLineUseDb)) {
                database.executeUpdate(word);
                continue;
            }
            else if (word.contains(checkLineInsertWorker)){
                addWorker(word);
            } else if (word.contains(checkLineInsertClient)) {
                addClient(word);
            }else if (word.contains(checkLineInsertProject)) {
                addProject(word);
            }
            else if (word.contains(checkLineInsertProject_worker)) {
                addNewProjectWorker(word);
            } else {
                continue;
            }
        }
        database.close();
    }

    public static void addWorker(String line) throws SQLException {
        String[] lineSql2 = line.split("\n");

        for (String world : lineSql2){
            if (world.equals("") || world.startsWith("INSERT")){
                continue;
            }
            else {
                String[] strArray = world.replaceAll("[^\\da-zA-Z,\\-]", "").split(",");
                createNewWorker(
                        Integer.parseInt(strArray[0]),
                        strArray[1],
                        strArray[2],
                        strArray[3],
                        Integer.parseInt(strArray[4])
                );
            }
        }

        insertWorker.executeBatch();
    }
    public static void addClient(String line) throws SQLException {
        String[] lineSql2 = line.split("\n");

        for (String world : lineSql2){
            if (world.equals("") || world.startsWith("INSERT")){
                continue;
            }
            else {
                String[] strArray = world.replaceAll("[^\\da-zA-Z,\\-]", "").split(",");
                createNewClient(
                        Integer.parseInt(strArray[0]),
                        strArray[1]
                );
            }
        }

        insertClient.executeBatch();
    }

    public static void addProject(String line) throws SQLException {
        String[] lineSql2 = line.split("\n");

        for (String world : lineSql2){
            if (world.equals("") || world.startsWith("INSERT")){
                continue;
            }
            else {
                String[] strArray = world.replaceAll("[^\\da-zA-Z,\\-]", "").split(",");
                createNewProject(
                        Integer.parseInt(strArray[0]),
                        Integer.parseInt(strArray[1]),
                        strArray[2],
                        strArray[3]
                );
            }
        }

        insertProject.executeBatch();
    }

    public static void addNewProjectWorker(String line) throws SQLException {
        String[] lineSql2 = line.split("\n");

        for (String world : lineSql2){
            if (world.equals("") || world.startsWith("INSERT")){
                continue;
            }
            else {
                String[] strArray = world.replaceAll("[^\\da-zA-Z,\\-]", "").split(",");
                createNewProjectWorker(
                        strArray[0],
                        strArray[1]
                );
            }
        }

        insertProjectWorker.executeBatch();
    }



    public static void createNewWorker(int id, String name, String birthday, String level, int salary) {
        try {
            insertWorker.setInt(1, id);
            insertWorker.setString(2, name);
            insertWorker.setString(3, birthday);
            insertWorker.setString(4, level);
            insertWorker.setInt(5, salary);
            insertWorker.addBatch();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }



    public static void createNewClient(int id, String name) {
        try {
            insertClient.setInt(1, id);
            insertClient.setString(2, name);
            insertClient.addBatch();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public static void createNewProject(int id, int client_id, String start_date, String finish_date) {
        try {
            insertProject.setInt(1, id);
            insertProject.setInt(2, client_id);
            insertProject.setString(3, start_date);
            insertProject.setString(4, finish_date);
            insertProject.addBatch();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public static void createNewProjectWorker(String project_id, String worker_id) {
        try {
            insertProject.getConnection();
            insertProjectWorker.setString(1, project_id);
            insertProjectWorker.setString(2, worker_id);
            insertProjectWorker.addBatch();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}