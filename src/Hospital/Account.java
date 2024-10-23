package Hospital;

public class Account {
    // Properties
    private String username;
    private String password;

    // Constructors
    public Account() {
        this.username = null;
        this.password = null;
    }
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Setter - Getter
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }

    // Methods
}
