package services;

import database.ClientCRUD;
import model.Client;

public class ClientService {

    private ClientCRUD clientCRUD = new ClientCRUD();

    public void callInsertRecord(Client client) throws Exception {
        if (client.getName() == null || client.getPhoneNumber() == null || client.getPassportNumber() == null) {
            throw new Exception("Нет имени! || нет номера телефона! || нет номера паспорта!");
        }

    }
}
