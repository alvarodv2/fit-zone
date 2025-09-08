package application;

import domain.Client;
import repository.ClientDAO;
import repository.ClientDAOImpl;

import java.util.Scanner;

public class ZonaFitApplication {
    public static void main(String[] args) {
        zonaFitApp();
    }

    public static void zonaFitApp(){

        var exitMenu = false;
        var consoleMenu = new Scanner(System.in);

        ClientDAO clientDAO = new ClientDAOImpl();
        while (!exitMenu){
            try {
                var option = showMenu(consoleMenu);
                exitMenu = executeOptions(consoleMenu, option, clientDAO);
            } catch (Exception e){
                System.out.println("Failed to execute the options: " +e.getMessage());
            }
            System.out.println();
        }
    }

    public static int showMenu(Scanner console){
        System.out.print("""
                *** ZONA FIT ***
                1. List Clients
                2. Search Client
                3. Add Client
                4. Update Client
                5. Delete Client
                6. Exit
                Choose a option:\s""");
        return Integer.parseInt(console.nextLine());
    }

    public static boolean executeOptions(Scanner console, int option, ClientDAO clientDAO){

        var exit = false;
        switch (option){
            case 1 -> {
                System.out.println("--- List Clients ---");
                var clients = clientDAO.listClients();
                clients.forEach(System.out::println);
            }
            case 2 -> {
                System.out.print("Enter the client ID to search for: ");
                var idClient = Integer.parseInt(console.nextLine());
                var client = new Client(idClient);
                var clientFound = clientDAO.findClientById(client);
                if (clientFound){
                    System.out.println("Found client " + client);
                } else {
                    System.out.println("Client not found " + client);
                }
            }
            case 3 -> {
                System.out.println("--- Add Client ---");
                System.out.print("Name: ");
                var clientName = console.nextLine();
                System.out.print("Second Name: ");
                var clientSecondName = console.nextLine();
                System.out.print("Membership: ");
                var clientMembership = Integer.parseInt(console.nextLine());
                var client = new Client(clientName, clientSecondName, clientMembership);
                var addedClient = clientDAO.addClient(client);
                if (addedClient){
                    System.out.println("Added Client: " + client);
                } else {
                    System.out.println("Client not added " + client);
                }
            }
            case 4 -> {
                System.out.println("--- Update Client ---");
                System.out.print("Client ID to modify: ");
                var clientId = Integer.parseInt(console.nextLine());
                System.out.print("New Name: ");
                var clientName = console.nextLine();
                System.out.print("New Second Name: ");
                var clientSecondName = console.nextLine();
                System.out.print("New Membership: ");
                var clientMembership = Integer.parseInt(console.nextLine());
                var updateClient = new Client(clientId, clientName, clientSecondName, clientMembership);
                var updated = clientDAO.updateClient(updateClient);
                if (updated){
                    System.out.println("Client updated: " + updateClient);
                } else {
                    System.out.println("Client not updated: " + updateClient);
                }
            }
            case 5 -> {
                System.out.println("--- Delete Client ---");
                System.out.print("Client ID to delete: ");
                var clientId = Integer.parseInt(console.nextLine());
                var clientToDelete = new Client(clientId);
                var deleteClient = clientDAO.deleteClient(clientToDelete);
                if (deleteClient){
                    System.out.println("Client deleted: " + clientToDelete);
                } else {
                    System.out.println("Client not deleted: " + clientToDelete);
                }
            }
            case 6 -> {
                System.out.println("See you soon :D");
                exit = true;
            }
            default -> {
                System.out.println("Unknown option: " + option);
            }
        }
        return exit;
    }

}
