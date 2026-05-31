package main;

import controller.LoginController;

import view.LoginView;

public class Main {
    public static void main(String[] args) {
        // Pastikan aplikasi tidak mati saat window ditutup
        javax.swing.SwingUtilities.invokeLater(() -> {
            LoginView view = new LoginView();
            view.setVisible(true);
            new LoginController(view);
        });
    }
}
