package org.example.flyway;

import org.example.Prefs.Prefs;
import org.flywaydb.core.Flyway;

public class StartFlyway {
    private final static String connectionUrl = new Prefs().getString(Prefs.DB_JDBC_CONNECTION_URL);
    private final static String connectionUserName = new Prefs().getString(Prefs.DB_JDBC_NAME_USER);
    private final static String connectionPassword = new Prefs().getString(Prefs.DB_JDBC_PASSWORD);

    public static void main(String[] args) {
        StartFlyway startFlyway = new StartFlyway();
        startFlyway.runMigration(connectionUrl, connectionUserName, connectionPassword);
    }

    public void runMigration(String connectionUrl, String connectionUserName, String connectionPassword) {

        Flyway flyway = org.flywaydb.core.Flyway
                .configure()
                .defaultSchema("schema2")
                .dataSource(connectionUrl, connectionUserName, connectionPassword)
                .load();

        flyway.migrate();
    }




}