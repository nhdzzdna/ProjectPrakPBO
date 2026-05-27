package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                    + "(user_id, platform, username, password, keterangan) "
                    + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement statement =
                    conn.prepareStatement(query);

            statement.setInt(
                    1,
                    credential.getUserId()
            );

            statement.setString(
                    2,
                    credential.getPlatform()
            );

            statement.setString(
                    3,
                    credential.getUsername()
            );

            statement.setString(
                    4,
                    credential.encrypt()
            );

            statement.setString(
                    5,
                    credential.getKeterangan()
            );

            statement.executeUpdate();

            statement.close();

        } catch (SQLException e) {

            System.out.println("Insert gagal!");
            System.out.println(e.getMessage());
        }
    }

    // ====================
    // READ
    // ====================

    public List<Credential> getAll(int userId) {

        List<Credential> listCredential =
                new ArrayList<>();

        try {

            String query =
                    "SELECT * FROM credentials WHERE user_id=?";

            PreparedStatement statement =
                    conn.prepareStatement(query);

            statement.setInt(1, userId);

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                Credential credential =
                        new Credential(
                                resultSet.getInt("id"),
                                resultSet.getInt("user_id"),
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
                    + "WHERE id=? AND user_id=?";

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

            statement.setInt(
                    6,
                    credential.getUserId()
            );

            statement.executeUpdate();

            statement.close();

        } catch (SQLException e) {

            System.out.println("Update gagal!");
            System.out.println(e.getMessage());
        }
    }

    // ====================
    // DELETE
    // ====================

    public void delete(int id, int userId) {

        try {

            String query =
                    "DELETE FROM credentials "
                    + "WHERE id=? AND user_id=?";

            PreparedStatement statement =
                    conn.prepareStatement(query);

            statement.setInt(1, id);

            statement.setInt(2, userId);

            statement.executeUpdate();

            statement.close();

        } catch (SQLException e) {

            System.out.println("Delete gagal!");
            System.out.println(e.getMessage());
        }
    }
}