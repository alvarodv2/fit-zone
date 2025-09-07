package domain;

import java.util.Objects;

public class Client {

    private int clientId;
    private String clientName;
    private String clientSecondName;
    private int membership;

    public Client(){}

    public Client(int clientId){this.clientId = clientId;}

    public Client(String clientName, String clientSecondName, int membership){
        this.clientName = clientName;
        this.clientSecondName = clientSecondName;
        this.membership = membership;
    }

    public Client(int clientId, String clientName, String clientSecondName, int membership){
        this(clientName, clientSecondName, membership);
        this.clientId = clientId;
    }

    public int getClientId(){return clientId;}
    public void setClientId(int clientId){this.clientId = clientId;}

    public String getClientName() {return clientName;}
    public void setClientName(String clientName){this.clientName = clientName;}

    public String getClientSecondName() {return clientSecondName;}
    public void setClientSecondName(String clientSecondName){this.clientSecondName = clientSecondName;}

    public int getMembership() {return membership;}
    public void setMembership(int membership) {this.membership = membership;}

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", clientName='" + clientName + '\'' +
                ", clientSecondName='" + clientSecondName + '\'' +
                ", membership=" + membership +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return clientId == client.clientId && membership == client.membership && Objects.equals(clientName, client.clientName) && Objects.equals(clientSecondName, client.clientSecondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, clientName, clientSecondName, membership);
    }

}
