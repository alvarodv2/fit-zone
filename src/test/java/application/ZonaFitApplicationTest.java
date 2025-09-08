package application;

import domain.Client;
import repository.ClientDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ZonaFitApplicationTest {

    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    private TestClientDAO testClientDAO;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        testClientDAO = new TestClientDAO();
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Should display menu correctly and return selected option")
    void testShowMenu() {
        // Arrange
        String input = "1\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        int result = ZonaFitApplication.showMenu(scanner);

        assertEquals(1, result);
        String output = outputStream.toString();
        assertTrue(output.contains("*** ZONA FIT ***"));
        assertTrue(output.contains("1. List Clients"));
        assertTrue(output.contains("2. Search Client"));
        assertTrue(output.contains("3. Add Client"));
        assertTrue(output.contains("4. Update Client"));
        assertTrue(output.contains("5. Delete Client"));
        assertTrue(output.contains("6. Exit"));
    }

    @Test
    @DisplayName("Should handle invalid menu input with NumberFormatException")
    void testShowMenuInvalidInput() {
        String input = "abc\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        assertThrows(NumberFormatException.class, () -> {
            ZonaFitApplication.showMenu(scanner);
        });
    }

    @Test
    @DisplayName("Should list all clients when option 1 is selected")
    void testExecuteOptionsListClients() {
        testClientDAO.addTestClients();
        Scanner scanner = new Scanner("");

        boolean result = ZonaFitApplication.executeOptions(scanner, 1, testClientDAO);

        assertFalse(result);
        String output = outputStream.toString();
        assertTrue(output.contains("--- List Clients ---"));
    }

    @Test
    @DisplayName("Should find existing client when option 2 is selected")
    void testExecuteOptionsSearchClientFound() {
        String input = "1\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        testClientDAO.shouldFindClient = true;

        boolean result = ZonaFitApplication.executeOptions(scanner, 2, testClientDAO);

        assertFalse(result);
        String output = outputStream.toString();
        assertTrue(output.contains("Found client"));
    }

    @Test
    @DisplayName("Should handle client not found when option 2 is selected")
    void testExecuteOptionsSearchClientNotFound() {
        String input = "999\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        testClientDAO.shouldFindClient = false;

        boolean result = ZonaFitApplication.executeOptions(scanner, 2, testClientDAO);

        assertFalse(result);
        String output = outputStream.toString();
        assertTrue(output.contains("Client not found"));
    }

    @Test
    @DisplayName("Should handle invalid ID input in search option")
    void testExecuteOptionsSearchInvalidInput() {
        String input = "abc\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        assertThrows(NumberFormatException.class, () -> {
            ZonaFitApplication.executeOptions(scanner, 2, testClientDAO);
        });
    }

    @Test
    @DisplayName("Should successfully add client when option 3 is selected")
    void testExecuteOptionsAddClientSuccess() {
        String input = "John\nDoe\n1\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        testClientDAO.shouldSucceed = true;

        boolean result = ZonaFitApplication.executeOptions(scanner, 3, testClientDAO);

        assertFalse(result);
        String output = outputStream.toString();
        assertTrue(output.contains("--- Add Client ---"));
        assertTrue(output.contains("Added Client:"));
    }

    @Test
    @DisplayName("Should handle add client failure when option 3 is selected")
    void testExecuteOptionsAddClientFailure() {
        String input = "Jane\nSmith\n2\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        testClientDAO.shouldSucceed = false;

        boolean result = ZonaFitApplication.executeOptions(scanner, 3, testClientDAO);

        assertFalse(result);
        String output = outputStream.toString();
        assertTrue(output.contains("Client not added"));
    }

    @Test
    @DisplayName("Should handle invalid membership input when adding client")
    void testExecuteOptionsAddClientInvalidMembership() {
        String input = "Name\nSurname\nabc\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        assertThrows(NumberFormatException.class, () -> {
            ZonaFitApplication.executeOptions(scanner, 3, testClientDAO);
        });
    }

    @Test
    @DisplayName("Should successfully update client when option 4 is selected")
    void testExecuteOptionsUpdateClientSuccess() {
        String input = "1\nUpdatedName\nUpdatedSurname\n3\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        testClientDAO.shouldSucceed = true;

        boolean result = ZonaFitApplication.executeOptions(scanner, 4, testClientDAO);

        assertFalse(result);
        String output = outputStream.toString();
        assertTrue(output.contains("--- Update Client ---"));
        assertTrue(output.contains("Client updated:"));
    }

    @Test
    @DisplayName("Should handle update client failure when option 4 is selected")
    void testExecuteOptionsUpdateClientFailure() {
        String input = "999\nName\nSurname\n1\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        testClientDAO.shouldSucceed = false;

        boolean result = ZonaFitApplication.executeOptions(scanner, 4, testClientDAO);

        assertFalse(result);
        String output = outputStream.toString();
        assertTrue(output.contains("Client not updated:"));
    }

    @Test
    @DisplayName("Should handle invalid input when updating client")
    void testExecuteOptionsUpdateClientInvalidInput() {
        String input = "abc\nName\nSurname\n1\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        assertThrows(NumberFormatException.class, () -> {
            ZonaFitApplication.executeOptions(scanner, 4, testClientDAO);
        });
    }

    @Test
    @DisplayName("Should successfully delete client when option 5 is selected")
    void testExecuteOptionsDeleteClientSuccess() {
        String input = "1\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        testClientDAO.shouldSucceed = true;

        boolean result = ZonaFitApplication.executeOptions(scanner, 5, testClientDAO);

        assertFalse(result);
        String output = outputStream.toString();
        assertTrue(output.contains("--- Delete Client ---"));
        assertTrue(output.contains("Client deleted:"));
    }

    @Test
    @DisplayName("Should handle delete client failure when option 5 is selected")
    void testExecuteOptionsDeleteClientFailure() {
        String input = "999\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        testClientDAO.shouldSucceed = false;

        boolean result = ZonaFitApplication.executeOptions(scanner, 5, testClientDAO);

        assertFalse(result);
        String output = outputStream.toString();
        assertTrue(output.contains("Client not deleted:"));
    }

    @Test
    @DisplayName("Should handle invalid ID input when deleting client")
    void testExecuteOptionsDeleteInvalidInput() {
        String input = "abc\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        assertThrows(NumberFormatException.class, () -> {
            ZonaFitApplication.executeOptions(scanner, 5, testClientDAO);
        });
    }

    @Test
    @DisplayName("Should exit application when option 6 is selected")
    void testExecuteOptionsExit() {
        Scanner scanner = new Scanner("");

        boolean result = ZonaFitApplication.executeOptions(scanner, 6, testClientDAO);

        assertTrue(result);
        String output = outputStream.toString();
        assertTrue(output.contains("See you soon :D"));
    }

    @Test
    @DisplayName("Should handle unknown option")
    void testExecuteOptionsUnknownOption() {
        Scanner scanner = new Scanner("");

        boolean result = ZonaFitApplication.executeOptions(scanner, 99, testClientDAO);

        assertFalse(result);
        String output = outputStream.toString();
        assertTrue(output.contains("Unknown option: 99"));
    }

    @Test
    @DisplayName("Should handle negative option numbers")
    void testExecuteOptionsNegativeOption() {
        Scanner scanner = new Scanner("");

        boolean result = ZonaFitApplication.executeOptions(scanner, -1, testClientDAO);

        assertFalse(result);
        String output = outputStream.toString();
        assertTrue(output.contains("Unknown option: -1"));
    }

    @Test
    @DisplayName("Should handle zero as option")
    void testExecuteOptionsZeroOption() {
        Scanner scanner = new Scanner("");

        boolean result = ZonaFitApplication.executeOptions(scanner, 0, testClientDAO);

        assertFalse(result);
        String output = outputStream.toString();
        assertTrue(output.contains("Unknown option: 0"));
    }

    @Test
    @DisplayName("Should handle empty strings in client fields")
    void testExecuteOptionsEmptyClientFields() {
        String input = "\n\n1\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        testClientDAO.shouldSucceed = true;

        boolean result = ZonaFitApplication.executeOptions(scanner, 3, testClientDAO);

        assertFalse(result);
        String output = outputStream.toString();
        assertTrue(output.contains("Added Client:"));
    }

    private static class TestClientDAO implements ClientDAO {
        public boolean shouldSucceed = true;
        public boolean shouldFindClient = true;
        private List<Client> clients = new ArrayList<>();

        public void addTestClients() {
            clients.add(new Client(1, "Test", "Client1", 1));
            clients.add(new Client(2, "Test", "Client2", 2));
        }

        @Override
        public List<Client> listClients() {
            return clients;
        }

        @Override
        public boolean findClientById(Client client) {
            return shouldFindClient;
        }

        @Override
        public boolean addClient(Client client) {
            if (shouldSucceed) {
                clients.add(client);
            }
            return shouldSucceed;
        }

        @Override
        public boolean updateClient(Client client) {
            return shouldSucceed;
        }

        @Override
        public boolean deleteClient(Client client) {
            return shouldSucceed;
        }
    }
}