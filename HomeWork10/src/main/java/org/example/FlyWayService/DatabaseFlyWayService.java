package org.example.FlyWayService;

import org.flywaydb.core.Flyway;


public class DatabaseFlyWayService {

    public static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/";
    public static final String CONNECTION_USERNAME = "root";
    public static final String CONNECTION_PASS = "Karden_97";
    public void initFlyWayDB () {
        Flyway flyway = Flyway
                .configure()
                .defaultSchema("schema3")
                .dataSource(CONNECTION_URL, CONNECTION_USERNAME, CONNECTION_PASS)
                .load();
        flyway.migrate();
    }
}
