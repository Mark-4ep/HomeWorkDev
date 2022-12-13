package org.example.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private final PreparedStatement createSt;
    private final PreparedStatement getByIdSt;
    private final PreparedStatement selectMaxIdSt;
    private final PreparedStatement getAllSt;
    private final PreparedStatement setNameSt;
    private final PreparedStatement deleteByIdSt;

    public ClientService(Connection connection) throws SQLException {
        createSt = connection.prepareStatement(
                "INSERT INTO client (name) VALUES(?)"
        );

        getByIdSt = connection.prepareStatement(
                "SELECT name FROM client WHERE id = ?"
        );

        selectMaxIdSt = connection.prepareStatement(
                "SELECT max(id) AS maxId FROM client"
        );
        getAllSt = connection.prepareStatement(
                "SELECT id, name FROM client"
        );
        setNameSt = connection.prepareStatement(
                "UPDATE client SET name = ? WHERE id = ?"
        );
        deleteByIdSt = connection.prepareStatement(
                "DELETE FROM client WHERE id = ?"

        );
    }

    public long create(String  name) throws SQLException {

            createSt.setString(1, name);
            createSt.executeUpdate();

            long id;

        try (ResultSet rs = selectMaxIdSt.executeQuery()) {
            rs.next();
            id = rs.getLong("maxId");
        }

        return id;
    }

    public String getById(long id) throws SQLException {
        getByIdSt.setLong(1, id);

        try (ResultSet rs = getByIdSt.executeQuery()){
            if (!rs.next()){
                return null;
            }
            return rs.getString("name"); //Це вказує що саме з строчки по айді взяти значення найма
        }
    }

    public List<Client> getAll() throws SQLException {
        try (ResultSet rs = getAllSt.executeQuery()){
            List<Client> result = new ArrayList<>();

            while (rs.next()) {

                Client client = new Client();
                client.setId(rs.getLong("id"));
                client.setName(rs.getString("name"));
                result.add(client);
            }
            return result;
        }
    }

    public void setName(long id, String name) throws SQLException {
        if(getById(id) != null) {
            setNameSt.setString(1, name);
            setNameSt.setLong(2, id);

            setNameSt.executeUpdate();
        }
    }

    public void deleteById(long id) throws SQLException {
        deleteByIdSt.setLong(1,id);
        deleteByIdSt.executeUpdate();
    }
}
