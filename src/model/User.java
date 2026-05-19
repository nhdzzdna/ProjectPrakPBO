package model;

public class User {

    private int id;
    private String namaLengkap;
    private String username;
    private String email;
    private String password;

    public User(int id, String namaLengkap, String username,
                String email, String password) {

        this.id = id;
        this.namaLengkap = namaLengkap;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Constructor tanpa id (untuk register)
    public User(String namaLengkap, String username,
                String email, String password) {

        this.namaLengkap = namaLengkap;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getter Setter

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNamaLengkap() { return namaLengkap; }
    public void setNamaLengkap(String namaLengkap) { this.namaLengkap = namaLengkap; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}