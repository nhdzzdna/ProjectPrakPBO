package model;

public class Credential extends BaseCredential {

    private int id;
    private String keterangan;

    // Constructor
    public Credential(
            int id,
            String platform,
            String username,
            String password,
            String keterangan
    ) {

        super(platform, username, password);

        this.id = id;
        this.keterangan = keterangan;
    }

    // Getter Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
    
    public String decrypt() {

        String password = getPassword();

        String hasil = "";

        for (int i = 0; i < password.length(); i++) {

            char c = password.charAt(i);

            hasil += (char) (c - 3);
        }

        return hasil;
    }

    // Polymorphism
    @Override
    public String getDisplayPassword() {

        return "••••••";
    }

    @Override
    public String encrypt() {

        String password = getPassword();

        String hasil = "";

        for (int i = 0; i < password.length(); i++) {

            char c = password.charAt(i);

            hasil += (char) (c + 3);
        }

        return hasil;
    }
}