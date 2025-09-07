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
    void testListClients(){
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

}
