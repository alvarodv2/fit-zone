package repository;

import domain.Client;

import java.util.List;

public interface ClientDAO {

    List<Client> listClients();
    boolean findClientById(Client client);
    boolean addClient(Client client);
    boolean updateClient(Client client);
    boolean deleteClient(Client client);

}
