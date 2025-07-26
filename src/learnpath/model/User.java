package learnpath.model;
import java.util.UUID;
public class User{
    private String userId;
    private String username;
    private String email;
    private String password;
    public User(String username, String email, String password) {
        this.userId = UUID.randomUUID().toString();
        this.username = username;
        this.email = email;
        this.password = password;
    }
    
    public User(String userId, String username, String email, String password) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public String getUserId() {
        return userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}