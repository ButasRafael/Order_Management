package BLL;

import DAO.ClientDAO;
import Model.Client;
import BLL.validators.ClientValidator;
import java.util.List;

/**
 * This is a ClientBLL class that handles the business logic for clients.
 * It uses the ClientDAO class to interact with the database and the ClientValidator class to validate clients.
 */
public class ClientBLL {

    private final ClientDAO clientDAO;
    private final ClientValidator clientValidator;

    /**
     * This is a constructor that initializes a ClientBLL object.
     * It initializes the clientDAO and clientValidator objects.
     */
    public ClientBLL() {
        clientDAO = new ClientDAO();
        clientValidator = new ClientValidator();
    }

    /**
     * This method inserts a client into the database.
     * It first generates a new id for the client, then validates the client using the ClientValidator class, and finally uses the ClientDAO class to insert the client into the database.
     * @param client The client to be inserted.
     */
    public void insertClient(Client client) {
        int newId = clientDAO.generateNewId();
        client.setId(newId);
        clientValidator.validate(client, true);
        clientDAO.insert(client);
    }

    /**
     * This method updates a client in the database.
     * It first validates the client using the ClientValidator class, then uses the ClientDAO class to update the client in the database.
     * @param client The client to be updated.
     */
    public void updateClient(Client client) {
        clientValidator.validate(client, false);
        clientDAO.update(client, client.getId());
    }

    /**
     * This method deletes a client from the database.
     * It uses the ClientDAO class to delete the client from the database.
     * @param client The client to be deleted.
     * @throws IllegalArgumentException if the client does not exist.
     */
    public void deleteClient(Client client) {
        if (client != null) {
            clientDAO.delete(client.getId());
        } else {
            throw new IllegalArgumentException("Client does not exist!");
        }
    }

    /**
     * This method retrieves all clients from the database.
     * It uses the ClientDAO class to retrieve all clients from the database.
     * @return A list of all clients in the database.
     */
    public List<Client> getAllClients() {
        return clientDAO.findAll();
    }

}