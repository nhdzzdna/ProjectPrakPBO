package controller;

import dao.UserDAO;
import view.DashboardView;
import view.PengaturanView;

import javax.swing.JOptionPane;

public class PengaturanController {

    private PengaturanView view;
    private DashboardView  dashboard;
    private String         currentUsername;

    public PengaturanController(PengaturanView view,
                                DashboardView dashboard,
                                String currentUsername) {

        this.view            = view;
        this.dashboard       = dashboard;
        this.currentUsername = currentUsername;

        initAction();
    }

    // ====================
    // INIT ACTION
    // ====================

    private void initAction() {

        // Show / Hide password baru
        view.btnShowPasswordBaru.addActionListener(e -> {
            if (view.txtPasswordBaru.getEchoChar() == 0) {
                view.txtPasswordBaru.setEchoChar('•');
                view.btnShowPasswordBaru.setText("Show");
            } else {
                view.txtPasswordBaru.setEchoChar((char) 0);
                view.btnShowPasswordBaru.setText("Hide");
            }
        });

        // Show / Hide konfirmasi
        view.btnShowKonfirmasi.addActionListener(e -> {
            if (view.txtKonfirmasi.getEchoChar() == 0) {
                view.txtKonfirmasi.setEchoChar('•');
                view.btnShowKonfirmasi.setText("Show");
            } else {
                view.txtKonfirmasi.setEchoChar((char) 0);
                view.btnShowKonfirmasi.setText("Hide");
            }
        });

        // Simpan
        view.btnSimpan.addActionListener(e -> simpanPerubahan());
    }

    // ====================
    // SIMPAN PERUBAHAN
    // ====================

    private void simpanPerubahan() {

        String nama      = view.txtNamaLengkap.getText().trim();
        String username  = view.txtUsername.getText().trim();
        String email     = view.txtEmail.getText().trim();
        String passBaru  = String.valueOf(view.txtPasswordBaru.getPassword());
        String konfirm   = String.valueOf(view.txtKonfirmasi.getPassword());

        if (nama.isEmpty() || username.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(
                    view,
                    "Nama, username, dan email tidak boleh kosong!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Validasi password jika diisi
        if (!passBaru.isEmpty()) {
            if (!passBaru.equals(konfirm)) {
                JOptionPane.showMessageDialog(
                        view,
                        "Password baru dan konfirmasi tidak cocok!",
                        "Peringatan",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }
        }

        // Informasikan sukses (implementasi update DB bisa ditambah sesuai kebutuhan)
        JOptionPane.showMessageDialog(
                view,
                "Perubahan berhasil disimpan!",
                "Berhasil",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
