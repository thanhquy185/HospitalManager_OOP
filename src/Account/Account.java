package Account;

public class Account {
    // Properties
    private String username;
    private String password;
    private String type;
    private String objectID;

    // Constructors
    public Account() {
        this.username = null;
        this.password = null;
        this.type = null;
        this.objectID = null;
    }
    public Account(String username, String password, String type, String objectID) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.objectID = objectID;
    }

    // Setter - Getter
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    public String getType() {
        return this.type;
    }
    public String getObjectID() {
        return this.objectID;
    }

    // Methods
    public String getInfo() {
        return this.username + " | " + this.password
            + " | " + this.type + " | " + this.objectID;
    }
}
