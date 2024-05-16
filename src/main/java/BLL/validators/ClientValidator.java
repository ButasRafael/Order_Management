package BLL.validators;

import DAO.ClientDAO;
import Model.Client;

import java.util.regex.Pattern;

/**
 * This is a ClientValidator class that validates the details of a client.
 * It implements the Validator interface with Client as the type parameter.
 */
public class ClientValidator implements Validator<Client> {
    private static final int MIN_AGE = 18;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    private static final String NAME_PATTERN = "^[\\p{L} _-]+$";
    private static final String PHONE_PATTERN = "^07\\d{8}$";
    private static final String ADDRESS_PATTERN = "^[\\p{L}\\p{N} .'-,]+$";

    private final ClientDAO clientDAO;

    /**
     * This is a constructor that initializes a ClientValidator object.
     * It initializes the clientDAO object.
     */
    public ClientValidator() {
        this.clientDAO = new ClientDAO();
    }

    /**
     * This method validates the details of a client.
     * @param client The client to be validated.
     * @param isInsert A boolean value that indicates whether the operation is an insert operation.
     * @throws IllegalArgumentException if the client's age is less than MIN_AGE, or if the client's email, name, phone number, or address is not valid, or if the client's id or email or phone number already exists.
     */
    public void validate(Client client, boolean isInsert) {
        if (client.getAge() < MIN_AGE) {
            throw new IllegalArgumentException("Clients must be at least " + MIN_AGE + " years old.");
        }
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        if (!pattern.matcher(client.getEmail()).matches()) {
            throw new IllegalArgumentException("Email is not valid!");

        }
        pattern = Pattern.compile(NAME_PATTERN);
        if (!pattern.matcher(client.getName()).matches()) {
            throw new IllegalArgumentException("Name is not valid!");
        }
        pattern = Pattern.compile(PHONE_PATTERN);
        if (!pattern.matcher(client.getPhone()).matches()) {
            throw new IllegalArgumentException("Phone number is not valid!");
        }
        pattern = Pattern.compile(ADDRESS_PATTERN);
        if (!pattern.matcher(client.getAddress()).matches()) {
            throw new IllegalArgumentException("Address is not valid!");
        }
        if (isInsert&&clientDAO.doesIdExist(client.getId())) {
            throw new IllegalArgumentException("Id already exists!");
        }
        if (clientDAO.doesEmailExist(client.getEmail())) {
            if (isInsert || !clientDAO.isEmailOwnedByClientId(client.getEmail(), client.getId())) {
                throw new IllegalArgumentException("Email already exists");
            }
        }
        if (clientDAO.doesPhoneExist(client.getPhone())) {
            if (isInsert || !clientDAO.isPhoneOwnedByClientId(client.getPhone(), client.getId())) {
                throw new IllegalArgumentException("Phone number already exists");
            }
        }
    }
}