package Hospital;

public class Account {
    // Properties
    private String username;
    private String password;
    private String type;
    private String idObject;

    // Constructors
    public Account() {
        this.username = null;
        this.password = null;
        this.type = null;
        this.idObject = null;
    }
    public Account(String username, String password, String type, String idObject) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.idObject = idObject;
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
    public void setIdObject(String idObject) {
        this.idObject = idObject;
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
    public String getIdObject() {
        return this.idObject;
    }

    // Methods
    public String getInfo() {
        return this.username + " | " + this.password
            + " | " + this.type + " | " + this.idObject;
    }
}
