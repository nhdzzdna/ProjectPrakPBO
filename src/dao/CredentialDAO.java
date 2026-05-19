package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import model.Credential;

public class CredentialDAO {

    Connection conn;

    public CredentialDAO() {

        conn = DBConnection.getConnection();
    }

    // ====================
    // CREATE
    // ====================

    public void insert(Credential credential) {

        try {

            String query =
                    "INSERT INTO credentials "
                    + "(platform, username, password, keterangan) "
                    + "VALUES (?, ?, ?, ?)";

            PreparedStatement statement =
                    conn.prepareStatement(query);

            statement.setString(
                    1,
                    credential.getPlatform()
            );

            statement.setString(
                    2,
                    credential.getUsername()
            );

            statement.setString(
                    3,
                    credential.encrypt()
            );

            statement.setString(
                    4,
                    credential.getKeterangan()
            );

            statement.executeUpdate();

            statement.close();

            System.out.println("Data berhasil ditambah!");

        } catch (SQLException e) {

            System.out.println("Insert gagal!");
            System.out.println(e.getMessage());
        }
    }

    // ====================
    // READ
    // ====================

    public List<Credential> getAll() {

        List<Credential> listCredential =
                new ArrayList<>();

        try {

            Statement statement =
                    conn.createStatement();

            String query =
                    "SELECT * FROM credentials";

            ResultSet resultSet =
                    statement.executeQuery(query);

            while (resultSet.next()) {

                Credential credential =
                        new Credential(
                                resultSet.getInt("id"),
                                resultSet.getString("platform"),
                                resultSet.getString("username"),
                                resultSet.getString("password"),
                                resultSet.getString("keterangan")
                        );

                listCredential.add(credential);
            }

            statement.close();

        } catch (SQLException e) {

            System.out.println("Get data gagal!");
            System.out.println(e.getMessage());
        }

        return listCredential;
    }

    // ====================
    // UPDATE
    // ====================

    public void update(Credential credential) {

        try {

            String query =
                    "UPDATE credentials SET "
                    + "platform=?, "
                    + "username=?, "
                    + "password=?, "
                    + "keterangan=? "
                    + "WHERE id=?";

            PreparedStatement statement =
                    conn.prepareStatement(query);

            statement.setString(
                    1,
                    credential.getPlatform()
            );

            statement.setString(
                    2,
                    credential.getUsername()
            );

            statement.setString(
                    3,
                    credential.encrypt()
            );

            statement.setString(
                    4,
                    credential.getKeterangan()
            );

            statement.setInt(
                    5,
                    credential.getId()
            );

            statement.executeUpdate();

            statement.close();

            System.out.println("Data berhasil diupdate!");

        } catch (SQLException e) {

            System.out.println("Update gagal!");
            System.out.println(e.getMessage());
        }
    }

    // ====================
    // DELETE
    // ====================

    public void delete(int id) {

        try {

            String query =
                    "DELETE FROM credentials WHERE id=?";

            PreparedStatement statement =
                    conn.prepareStatement(query);

            statement.setInt(1, id);

            statement.executeUpdate();

            statement.close();

            System.out.println("Data berhasil dihapus!");

        } catch (SQLException e) {

            System.out.println("Delete gagal!");
            System.out.println(e.getMessage());
        }
    }
}