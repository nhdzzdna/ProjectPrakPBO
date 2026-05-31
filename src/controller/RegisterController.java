package controller;

import dao.UserDAO;

import model.User;

import view.RegisterView;
import view.LoginView;

import javax.swing.JOptionPane;

public class RegisterController {

    private RegisterView view;

    private LoginView loginView;

    private UserDAO userDAO;

    public RegisterController(RegisterView view, LoginView loginView) {

        this.view = view;
        this.loginView = loginView;

        userDAO = new UserDAO();

        initAction();
    }

    // ====================
    // EVENT
    // ====================

    private void initAction() {

        // TOMBOL DAFTAR
        view.btnDaftar.addActionListener(e -> {
            register();
        });

        // TOMBOL KEMBALI KE LOGIN
        view.btnKembali.addActionListener(e -> {
            goToLogin();
        });
        
        setupHint(view.txtNamaLengkap, "Nama Lengkap");
        setupHint(view.txtUsername, "Username");
        setupHint(view.txtEmail, "Email");
        setupHintPassword(view.txtPassword, "Password");
        setupHintPassword(view.txtKonfirmasi, "Konfirmasi Password");

        view.btnDaftar.addActionListener(e -> register());
        view.btnKembali.addActionListener(e -> goToLogin());
    }

    private void setupHint(javax.swing.JTextField field, String hint) {
        field.setForeground(java.awt.Color.GRAY);
        field.setText(hint);
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (field.getText().equals(hint)) {
                    field.setText("");
                    field.setForeground(java.awt.Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setForeground(java.awt.Color.GRAY);
                    field.setText(hint);
                }
            }
        });
    }

    private void setupHintPassword(javax.swing.JPasswordField field, String hint) {
        field.setEchoChar((char) 0);
        field.setForeground(java.awt.Color.GRAY);
        field.setText(hint);
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (String.valueOf(field.getPassword()).equals(hint)) {
                    field.setText("");
                    field.setForeground(java.awt.Color.BLACK);
                    field.setEchoChar('●');
                }
            }
            public void focusLost(java.awt.event.FocusEvent e) {
                if (String.valueOf(field.getPassword()).isEmpty()) {
                    field.setEchoChar((char) 0);
                    field.setForeground(java.awt.Color.GRAY);
                    field.setText(hint);
                }
            }
        });
    }

    // ====================
    // REGISTER
    // ====================

    private void register() {

        String namaLengkap  = view.txtNamaLengkap.getText().trim();
        String username     = view.txtUsername.getText().trim();
        String email        = view.txtEmail.getText().trim();
        String password     = String.valueOf(view.txtPassword.getPassword());
        String konfirmasi   = String.valueOf(view.txtKonfirmasi.getPassword());

        // Validasi kosong
        if (namaLengkap.isEmpty() || username.isEmpty()
                || email.isEmpty() || password.isEmpty()
                || konfirmasi.isEmpty()) {

            JOptionPane.showMessageDialog(
                    view,
                    "Semua field harus diisi!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Validasi email
        if (!email.contains("@") || !email.contains(".")) {

            JOptionPane.showMessageDialog(
                    view,
                    "Format email tidak valid!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Validasi panjang password
        if (password.length() < 6) {

            JOptionPane.showMessageDialog(
                    view,
                    "Password minimal 6 karakter!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Validasi konfirmasi password
        if (!password.equals(konfirmasi)) {

            JOptionPane.showMessageDialog(
                    view,
                    "Password dan Konfirmasi Password tidak cocok!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Simpan ke database
        User user = new User(namaLengkap, username, email, password);

        int result = userDAO.register(user);

        if (result == 1) {

            JOptionPane.showMessageDialog(
                    view,
                    "Registrasi berhasil! Silakan login.",
                    "Berhasil",
                    JOptionPane.INFORMATION_MESSAGE
            );

            goToLogin();

        } else if (result == 0) {

            JOptionPane.showMessageDialog(
                    view,
                    "Username sudah digunakan! Pilih username lain.",
                    "Gagal",
                    JOptionPane.ERROR_MESSAGE
            );

        } else {

            JOptionPane.showMessageDialog(
                    view,
                    "Registrasi gagal! Tidak dapat terhubung ke database.\n"
                    + "Pastikan XAMPP sudah berjalan dan konfigurasi database sudah benar.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ====================
    // KEMBALI KE LOGIN
    // ====================

    private void goToLogin() {

        loginView.setVisible(true);
        view.dispose();
    }
}
