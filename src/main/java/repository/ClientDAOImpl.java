package repository;

import config.DatabaseConfig;
import connection.DatabaseConnection;
import domain.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static connection.DatabaseConnection.getConnection;

public class ClientDAOImpl implements ClientDAO {

    /**
     * Retrieves all clients from the database ordered by client_id.
     *
     * @return a List of Client objects; empty if no clients found
     * @throws SQLException if a database access error occurs
     */
    @Override
    public List<Client> listClients() {
        List<Client> clients = new ArrayList<>();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Connection dbConnection = getConnection();

        String selectClientsQuery = "SELECT * FROM client ORDER BY client_id";

        try {
            preparedStatement = dbConnection.prepareStatement(selectClientsQuery);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Client client = new Client();
                client.setClientId(resultSet.getInt("client_id"));
                client.setClientName(resultSet.getString("client_name"));
                client.setClientSecondName(resultSet.getString("client_second_name"));
                client.setMembership(resultSet.getInt("membership"));

                clients.add(client);
            }

        } catch (SQLException s) {
            System.out.println("Failed to list clients: " + s.getMessage());
        } finally {
            try {
                dbConnection.close();
            } catch (SQLException e) {
                System.out.println("Failed to close database connection: " + e.getMessage());
            }
        }

        return clients;
    }


    @Override
    public boolean findClientById(Client client) {
        return false;
    }

    @Override
    public boolean addClient(Client client) {
        return false;
    }

    @Override
    public boolean updateClient(Client client) {
        return false;
    }

    @Override
    public boolean deleteClient(Client client) {
        return false;
    }
}
