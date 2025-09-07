package repository;

import domain.Client;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ClientDAOImplTest {

    /**
     * Integration test for ClientDAOImpl.listClients().
     * Requires XAMPP MySQL database "zona_fit" to be running.
     */
    @Test
    void testListClients() {
        ClientDAOImpl clientDAO = new ClientDAOImpl();

        List<Client> clients = clientDAO.listClients();

        assertNotNull(clients, "The clients list should not be null");

        assertFalse(clients.isEmpty());

        for (Client client : clients) {
            assertTrue(client.getClientId() > 0, "Client ID should be greater than 0");
            assertNotNull(client.getClientName(), "Client name should not be null");

            // membership can be 0 if not memembership
            assertTrue(client.getMembership() >= 0, "Membership should be >= 0");
        }
    }

    /**
     * Integration test for ClientDAOImpl.findClientById().
     */
    @Test
    void testFindClientById() {
        ClientDAOImpl clientDAO = new ClientDAOImpl();

        Client client = new Client();
        client.setClientId(1);

        boolean found = clientDAO.findClientById(client);

        assertTrue(found, "Client with ID 1 should exist in the database");
        assertNotNull(client.getClientName(), "Client name should not be null when found");
        assertNotNull(client.getClientSecondName(), "Client second name should not be null when found");
        assertTrue(client.getMembership() >= 0, "Membership should be >= 0");
    }

    /**
     * Integration test for ClientDAOImpl.findClientById().
     */
    @Test
    void testFindClientById_NotFound() {
        ClientDAOImpl clientDAO = new ClientDAOImpl();

        Client client = new Client();
        client.setClientId(-999);

        boolean found = clientDAO.findClientById(client);

        assertFalse(found, "Non-existing client should not be found");
        assertNull(client.getClientName(), "Client name should remain null if not found");
    }

    /**
     * Integration test for ClientDAOImpl.addClient().
     */
    @Test
    void testAddClient() {
        ClientDAOImpl clientDAO = new ClientDAOImpl();

        Client client = new Client();
        client.setClientName("Test");
        client.setClientSecondName("User");
        client.setMembership(100);

        boolean added = clientDAO.addClient(client);

        assertTrue(added, "Client should be added sucessfully");
    }

    /**
     * Integration test for ClientDAOImpl.updateClient().
     */
    @Test
    void testUpdateClient() {
        ClientDAOImpl clientDAO = new ClientDAOImpl();

        Client client = new Client();
        client.setClientName("Original");
        client.setClientSecondName("Name");
        client.setMembership(1);
        clientDAO.addClient(client);

        client.setClientName("Updated");
        client.setClientSecondName("User");
        client.setMembership(2);

        boolean updated = clientDAO.updateClient(client);
        assertTrue(updated, "Client should be updated successfully");

        Client updatedClient = new Client();
        updatedClient.setClientId(client.getClientId());
        clientDAO.findClientById(updatedClient);

        assertEquals("Updated", updatedClient.getClientName());
        assertEquals("User", updatedClient.getClientSecondName());
        assertEquals(2, updatedClient.getMembership());
    }

    /**
     * Integration test for ClientDAOImpl.deleteClient().
     */
    @Test
    void testDeleteClient() {
        ClientDAOImpl clientDAO = new ClientDAOImpl();

        Client client = new Client();
        client.setClientName("ToDelete");
        client.setClientSecondName("User");
        client.setMembership(0);
        clientDAO.addClient(client);

        boolean deleted = clientDAO.deleteClient(client);
        assertTrue(deleted, "Client should be deleted successfully");

        Client deletedClient = new Client();
        deletedClient.setClientId(client.getClientId());
        boolean found = clientDAO.findClientById(deletedClient);
        assertFalse(found, "Deleted client should not be found");
    }

}
