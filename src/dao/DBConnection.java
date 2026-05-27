package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    // =====================================================
    // SESUAIKAN 3 BARIS INI DENGAN KONFIGURASI XAMPP KAMU
    // =====================================================
    private static final String NAMA_DB  = "passkeeper_db";   // nama database tanpa (1)
    private static final String PORT     = "3306";             // port default XAMPP = 3306
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    // =====================================================

    private static final String URL =
            "jdbc:mysql://localhost:" + PORT + "/" + NAMA_DB
            + "?useSSL=false"
            + "&allowPublicKeyRetrieval=true"
            + "&serverTimezone=Asia/Jakarta"
            + "&characterEncoding=UTF-8";

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Koneksi berhasil ke database: " + NAMA_DB);
            return conn;

        } catch (ClassNotFoundException e) {
            System.out.println("Driver MySQL tidak ditemukan! Tambahkan mysql-connector-java ke Libraries.");
            System.out.println(e.getMessage());
            return null;

        } catch (Exception e) {
            System.out.println("Koneksi gagal! Pastikan XAMPP sudah berjalan.");
            System.out.println(e.getMessage());
            return null;
        }
    }
}