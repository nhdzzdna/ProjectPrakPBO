package controller;

import dao.UserDAO;

import model.User;

import view.LoginView;
import view.RegisterView;
import view.DashboardView;

import javax.swing.JOptionPane;

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

        String username = view.txtUsername.getText().trim();

        String password = String.valueOf(view.txtPassword.getPassword());

        // Validasi kosong
        if (username.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    view,
                    "Username dan Password harus diisi!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Cek ke database
        User user = userDAO.login(username, password);

        if (user != null) {

            JOptionPane.showMessageDialog(
                    view,
                    "Selamat datang, " + user.getNamaLengkap() + "!",
                    "Login Berhasil",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // Buka dashboard
            DashboardView dashboard = new DashboardView(
                    user.getNamaLengkap(),
                    user.getUsername(),
                    user.getEmail()
            );

            new CredentialController(dashboard);

            // Tutup login
            view.dispose();

        } else {

            JOptionPane.showMessageDialog(
                    view,
                    "Username atau Password salah!",
                    "Login Gagal",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ====================
    // BUKA REGISTER
    // ====================

    private void openRegister() {

        RegisterView registerView = new RegisterView();

        new RegisterController(registerView, view);

        view.setVisible(false);
    }
}
