package Account;

public class Account {
    // Properties
    private String username;
    private String password;
    private String type;

    // Constructors
    public Account() {
        this.username = null;
        this.password = null;
        this.type = null;
    }
    public Account(String username, String password, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
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
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    public String getType() {
        return this.type;
    }

    // Methods
    public String getInfo() {
        return this.username + " | " + this.password + " | " + this.type;
    }
}
