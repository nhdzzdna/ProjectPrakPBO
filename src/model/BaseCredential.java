package model;

public abstract class BaseCredential {

    // Encapsulation
    private String platform;
    private String username;
    private String password;

    // Constructor
    public BaseCredential(
            String platform,
            String username,
            String password
    ) {

        this.platform = platform;
        this.username = username;
        this.password = password;
    }

    // Getter Setter
    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
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

    public void setPassword(String password) {
        this.password = password;
    }

    // Concrete method
    public String getSummary() {

        return platform + " - " + username;
    }

    // Abstract method
    public abstract String getDisplayPassword();

    public abstract String encrypt();
}