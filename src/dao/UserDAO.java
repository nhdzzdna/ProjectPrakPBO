package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDAO {

    Connection conn;

    public UserDAO() {
        conn = DBConnection.getConnection();
    }

    // ====================
    // LOGIN
    // ====================

    public User login(String username, String password) {

        if (conn == null) {
            System.out.println("Koneksi ke database gagal! Periksa konfigurasi DBConnection.");
            return null;
        }

        try {

            String query =
                    "SELECT * FROM users WHERE username = ? AND password = ?";

            PreparedStatement statement =
                    conn.prepareStatement(query);

            statement.setString(1, username);
            statement.setString(2, hashPassword(password));

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                User user = new User(
                        rs.getInt("id"),
                        rs.getString("nama_lengkap"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                );

                statement.close();
                return user;
            }

            statement.close();

        } catch (SQLException e) {

            System.out.println("Login gagal!");
            System.out.println(e.getMessage());
        }

        return null;
    }

    // ====================
    // REGISTER
    // ====================

    /**
     * Register user baru.
     * @return  1  = berhasil
     *          0  = username sudah digunakan
     *         -1  = error koneksi / database
     */
    public int register(User user) {

        if (conn == null) {
            System.out.println("Koneksi ke database gagal! Periksa konfigurasi DBConnection.");
            return -1;
        }

        // Cek apakah username sudah ada
        int usernameCheck = isUsernameExist(user.getUsername());
        if (usernameCheck == 1) {
            return 0; // username sudah digunakan
        }
        if (usernameCheck == -1) {
            return -1; // error saat cek username
        }

        try {

            String query =
                    "INSERT INTO users (nama_lengkap, username, email, password) "
                    + "VALUES (?, ?, ?, ?)";

            PreparedStatement statement =
                    conn.prepareStatement(query);

            statement.setString(1, user.getNamaLengkap());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getEmail());
            statement.setString(4, hashPassword(user.getPassword()));

            statement.executeUpdate();
            statement.close();

            System.out.println("Registrasi berhasil!");
            return 1;

        } catch (SQLException e) {

            System.out.println("Registrasi gagal!");
            System.out.println(e.getMessage());
            return -1;
        }
    }

    // ====================
    // CEK USERNAME
    // ====================

    /**
     * Cek apakah username sudah ada di database.
     * @return  1  = username sudah ada
     *          0  = username belum ada
     *         -1  = error koneksi / database
     */
    public int isUsernameExist(String username) {

        if (conn == null) {
            System.out.println("Koneksi ke database gagal! Periksa konfigurasi DBConnection.");
            return -1;
        }

        try {

            String query = "SELECT id FROM users WHERE username = ?";

            PreparedStatement statement =
                    conn.prepareStatement(query);

            statement.setString(1, username);

            ResultSet rs = statement.executeQuery();

            boolean exist = rs.next();

            statement.close();

            return exist ? 1 : 0;

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return -1;
        }
    }

    // ====================
    // HASH PASSWORD (SHA-256)
    // ====================

    public static String hashPassword(String password) {

        try {

            java.security.MessageDigest md =
                    java.security.MessageDigest.getInstance("SHA-256");

            byte[] hash = md.digest(password.getBytes("UTF-8"));

            StringBuilder sb = new StringBuilder();

            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (Exception e) {

            // fallback: kembalikan password asli jika terjadi error
            return password;
        }
    }
}
