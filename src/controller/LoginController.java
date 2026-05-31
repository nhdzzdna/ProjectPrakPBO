package controller;

import dao.UserDAO;
import model.User;
import view.LoginView;
import view.RegisterView;
import view.DashboardView;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginController {

    private LoginView view;
    private UserDAO userDAO;

    public LoginController(LoginView view) {
        this.view = view;
        userDAO = new UserDAO();
        initAction();
    }

    // ====================
    // EVENT
    // ====================
    private void initAction() {
        // HINT PLACEHOLDER
        setupHint(view.txtUsername, "Username");
        setupHintPassword(view.txtPassword, "Password");

        // TOMBOL LOGIN
        view.btnLogin.addActionListener(e -> {
            login();
        });

        // TOMBOL DAFTAR
        view.btnDaftar.addActionListener(e -> {
            openRegister();
        });
    }

    // ====================
    // LOGIN
    // ====================
    private void login() {
        String username = view.txtUsername.getText().equals("Username") ? "" : view.txtUsername.getText().trim();
        String password = String.valueOf(view.txtPassword.getPassword()).equals("Password") ? "" : String.valueOf(view.txtPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Username dan Password harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        User user = userDAO.login(username, password);

        if (user != null) {
            JOptionPane.showMessageDialog(view, "Selamat datang, " + user.getNamaLengkap() + "!", "Login Berhasil", JOptionPane.INFORMATION_MESSAGE);
            DashboardView dashboard = new DashboardView(user.getNamaLengkap(), user.getUsername(), user.getEmail());
            new CredentialController(dashboard, user);
            view.dispose();
        } else {
            JOptionPane.showMessageDialog(view, "Username atau Password salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ====================
    // BUKA REGISTER
    // ====================
    private void openRegister() {
        RegisterView registerView = new RegisterView();
        registerView.setVisible(true);
        new RegisterController(registerView, view);
        view.setVisible(false);
    }

    // ====================
    // HINT USERNAME
    // ====================
    private void setupHint(JTextField field, String hint) {
        field.setForeground(Color.GRAY);
        field.setText(hint);
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(hint)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setForeground(Color.GRAY);
                    field.setText(hint);
                }
            }
        });
    }

    // ====================
    // HINT PASSWORD
    // ====================
    private void setupHintPassword(JPasswordField field, String hint) {
        field.setEchoChar((char) 0);
        field.setForeground(Color.GRAY);
        field.setText(hint);
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (String.valueOf(field.getPassword()).equals(hint)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                    field.setEchoChar('●');
                }
            }
            public void focusLost(FocusEvent e) {
                if (String.valueOf(field.getPassword()).isEmpty()) {
                    field.setEchoChar((char) 0);
                    field.setForeground(Color.GRAY);
                    field.setText(hint);
                }
            }
        });
    }
}