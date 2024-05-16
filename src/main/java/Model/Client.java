package Model;

/**
 * This is a Client class that represents a client in the system.
 * It contains the id, name, address, email, age, and phone number of the client.
 */
public class Client {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private int age;

    /**
     * This is a constructor that initializes a Client object with the given parameters.
     * @param id The id of the client.
     * @param name The name of the client.
     * @param address The address of the client.
     * @param email The email of the client.
     * @param age The age of the client.
     * @param phone The phone number of the client.
     */
    public Client(int id, String name, String address, String email, int age, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.age = age;
        this.phone = phone;
    }

    /**
     * This is a default constructor that initializes a Client object with no parameters.
     */
    public Client() {
    }

    /**
     * This is a constructor that initializes a Client object with the given parameters, excluding id.
     * @param name The name of the client.
     * @param address The address of the client.
     * @param email The email of the client.
     * @param age The age of the client.
     * @param phone The phone number of the client.
     */
    public Client(String name, String address, String email, int age, String phone) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.age = age;
        this.phone = phone;
    }

    /**
     * This method returns the id of the client.
     * @return The id of the client.
     */
    public int getId() {
        return id;
    }

    /**
     * This method sets the id of the client.
     * @param id The id to be set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method returns the name of the client.
     * @return The name of the client.
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets the name of the client.
     * @param name The name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method returns the address of the client.
     * @return The address of the client.
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method sets the address of the client.
     * @param address The address to be set.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method returns the age of the client.
     * @return The age of the client.
     */
    public int getAge() {
        return age;
    }

    /**
     * This method sets the age of the client.
     * @param age The age to be set.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * This method returns the email of the client.
     * @return The email of the client.
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method sets the email of the client.
     * @param email The email to be set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method returns the phone number of the client.
     * @return The phone number of the client.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method sets the phone number of the client.
     * @param phone The phone number to be set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * This method returns a string representation of the client.
     * @return A string representation of the client.
     */
    @Override
    public String toString() {
        return "Client [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + ", age=" + age
                + "]";
    }
}