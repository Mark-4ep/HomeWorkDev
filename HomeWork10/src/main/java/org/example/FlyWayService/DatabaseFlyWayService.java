package org.example.FlyWayService;

import org.example.preferences.PropertiesFileReader;
import org.flywaydb.core.Flyway;

import java.io.IOException;


public class DatabaseFlyWayService {


    public void initFlyWayDB () throws IOException {
            String connectionUrl = new PropertiesFileReader().getPath();
        Flyway flyway = Flyway
                .configure()
                .dataSource("jdbc:h2:./test", null, null)
                .load();
        flyway.migrate();
    }
}
