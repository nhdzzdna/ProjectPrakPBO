package controller;

import dao.CredentialDAO;

import model.Credential;
import model.User;

import view.CredentialFormDialog;
import view.DashboardView;
import view.LoginView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class CredentialController {

    private DashboardView view;

    private CredentialDAO dao;
    
    private User currentUser;

    private int selectedId = -1;

    private TableRowSorter<DefaultTableModel> sorter;

    private SemuaAkunController  semuaAkunController;
    private PengaturanController pengaturanController;

    public CredentialController(DashboardView view, User user) {

        this.view = view;
        this.currentUser = user;

        dao = new CredentialDAO();

        semuaAkunController  = new SemuaAkunController(
                view.semuaAkunView,
                currentUser
        );

        pengaturanController = new PengaturanController(
                view.pengaturanView,
                view,
                view.pengaturanView.txtUsername.getText()
        );

        loadTable();

        initAction();

        startClock();

        initSearch();
    }

    // ====================
    // EVENT
    // ====================

    private void initAction() {

        // TAMBAH
        view.btnTambah.addActionListener(e -> {
            openInsertDialog();
        });

        // EDIT
        view.btnUpdate.addActionListener(e -> {
            openUpdateDialog();
        });

        // HAPUS
        view.btnHapus.addActionListener(e -> {
            deleteCredential();
        });

        // LOGOUT
        view.btnLogout.addActionListener(e -> {
            logout();
        });

        // NAVIGASI MENU SIDEBAR
        view.menuDashboard.addActionListener(e -> {
            view.showCard(DashboardView.CARD_DASHBOARD);
        });

        view.menuSemua.addActionListener(e -> {
            semuaAkunController.loadTable(); // refresh data setiap buka
            view.showCard(DashboardView.CARD_SEMUA);
        });

        view.menuPengaturan.addActionListener(e -> {
            view.showCard(DashboardView.CARD_PENGATURAN);
        });

        view.menuTentang.addActionListener(e -> {
            view.showCard(DashboardView.CARD_TENTANG);
        });

        // CLICK TABLE
        view.tableCredential.getSelectionModel()
                .addListSelectionListener(e -> {
            getSelectedId();
        });

        // DOUBLE CLICK TABLE -> buka detail / edit
        view.tableCredential.addMouseListener(
                new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2) {
                    openUpdateDialog();
                }
            }
        });
    }

    // ====================
    // LOAD TABLE
    // ====================

    private void loadTable() {

        DefaultTableModel model = view.tableModel;

        model.setRowCount(0);

        List<Credential> list = dao.getAll(currentUser.getId());

        for (Credential c : list) {

            Object[] row = {
                c.getId(),
                c.getPlatform(),
                c.getUsername(),
                c.decrypt(),       // decrypt untuk ditampilkan (di-mask di renderer)
                c.getKeterangan()
            };

            model.addRow(row);
        }

        // MASK PASSWORD di kolom index 3
        view.tableCredential.getColumnModel()
                .getColumn(3)
                .setCellRenderer(
                        new javax.swing.table.DefaultTableCellRenderer() {

                    @Override
                    protected void setValue(Object value) {
                        setText("••••••");
                    }
                });

        // Update label total data
        view.lblTotal.setText("Total " + list.size() + " data");
    }

    // ====================
    // INSERT DIALOG
    // ====================

    private void openInsertDialog() {

        CredentialFormDialog dialog =
                new CredentialFormDialog(view, "Tambah Akun");

        dialog.btnSimpan.addActionListener(e -> {

            String platform   = dialog.txtPlatform.getText().trim();
            String username   = dialog.txtUsername.getText().trim();
            String password   = String.valueOf(dialog.txtPassword.getPassword());
            String keterangan = dialog.txtKeterangan.getText().trim();

            if (platform.isEmpty() || username.isEmpty()
                    || password.isEmpty() || keterangan.isEmpty()) {

                JOptionPane.showMessageDialog(
                        dialog,
                        "Semua field harus diisi!",
                        "Peringatan",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            Credential credential =
                    new Credential(
                            0,
                            currentUser.getId(),
                            platform,
                            username,
                            password,
                            keterangan
                    );

            dao.insert(credential);

            JOptionPane.showMessageDialog(
                    dialog,
                    "Data berhasil ditambahkan!",
                    "Berhasil",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadTable();

            dialog.dispose();
        });

        // BATAL
        dialog.btnBatal.addActionListener(e -> dialog.dispose());

        // SHOW / HIDE PASSWORD
        dialog.btnShowPassword.addActionListener(e -> togglePassword(dialog));

        dialog.setVisible(true);
    }

    // ====================
    // UPDATE DIALOG
    // ====================

    private void openUpdateDialog() {

        int selectedRow = view.tableCredential.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    view,
                    "Pilih data terlebih dahulu!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        CredentialFormDialog dialog =
                new CredentialFormDialog(view, "Edit Akun");

        // Isi form dari tabel
        dialog.txtPlatform.setText(
                view.tableModel.getValueAt(selectedRow, 1).toString());

        dialog.txtUsername.setText(
                view.tableModel.getValueAt(selectedRow, 2).toString());

        // Kolom 3 = password yang sudah di-decrypt sebelumnya
        dialog.txtPassword.setText(
                view.tableModel.getValueAt(selectedRow, 3).toString());

        dialog.txtKeterangan.setText(
                view.tableModel.getValueAt(selectedRow, 4).toString());

        // UPDATE
        dialog.btnSimpan.addActionListener(e -> {

            String platform   = dialog.txtPlatform.getText().trim();
            String username   = dialog.txtUsername.getText().trim();
            String password   = String.valueOf(dialog.txtPassword.getPassword());
            String keterangan = dialog.txtKeterangan.getText().trim();

            if (platform.isEmpty() || username.isEmpty()
                    || password.isEmpty() || keterangan.isEmpty()) {

                JOptionPane.showMessageDialog(
                        dialog,
                        "Semua field harus diisi!",
                        "Peringatan",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            Credential credential =
                    new Credential(
                            selectedId,
                            currentUser.getId(),
                            platform,
                            username,
                            password,
                            keterangan
                    );

            dao.update(credential);

            JOptionPane.showMessageDialog(
                    dialog,
                    "Data berhasil diupdate!",
                    "Berhasil",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadTable();

            dialog.dispose();
        });

        // BATAL
        dialog.btnBatal.addActionListener(e -> dialog.dispose());

        // SHOW / HIDE PASSWORD
        dialog.btnShowPassword.addActionListener(e -> togglePassword(dialog));

        dialog.setVisible(true);
    }

    // ====================
    // GET SELECTED ID
    // ====================

    private void getSelectedId() {

        int selectedRow = view.tableCredential.getSelectedRow();

        if (selectedRow == -1) return;

        selectedId = Integer.parseInt(
                view.tableModel.getValueAt(selectedRow, 0).toString());
    }

    // ====================
    // DELETE
    // ====================

    private void deleteCredential() {

        int selectedRow = view.tableCredential.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    view,
                    "Pilih data terlebih dahulu!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                view,
                "Yakin ingin menghapus data ini?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (confirm != JOptionPane.YES_OPTION) return;

        dao.delete(selectedId, currentUser.getId());

        loadTable();

        JOptionPane.showMessageDialog(
                view,
                "Data berhasil dihapus!",
                "Berhasil",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ====================
    // SEARCH
    // ====================

    private void initSearch() {

        sorter = new TableRowSorter<>(view.tableModel);

        view.tableCredential.setRowSorter(sorter);

        view.txtSearch.getDocument()
                .addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) { search(); }

            @Override
            public void removeUpdate(DocumentEvent e) { search(); }

            @Override
            public void changedUpdate(DocumentEvent e) { search(); }

            private void search() {

                String text = view.txtSearch.getText();

                if (text.trim().isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(
                            javax.swing.RowFilter.regexFilter("(?i)" + text));
                }
            }
        });
    }

    // ====================
    // CLOCK
    // ====================

    private void startClock() {

        Thread clockThread = new Thread(() -> {

            while (true) {

                try {

                    String waktu = new SimpleDateFormat("dd MMM yyyy | HH:mm:ss")
                            .format(new Date());

                    view.lblClock.setText(waktu);

                    Thread.sleep(1000);

                } catch (Exception e) {

                    System.out.println(e.getMessage());
                }
            }
        });

        clockThread.setDaemon(true);
        clockThread.start();
    }

    // ====================
    // LOGOUT
    // ====================

    private void logout() {

        int confirm = JOptionPane.showConfirmDialog(
                view,
                "Yakin ingin logout?",
                "Konfirmasi Logout",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (confirm != JOptionPane.YES_OPTION) return;

        // Buka kembali halaman login
        LoginView loginView = new LoginView();

        new LoginController(loginView);

        view.dispose();
    }

    // ====================
    // HELPER SHOW/HIDE PASSWORD
    // ====================

    private void togglePassword(CredentialFormDialog dialog) {

        if (dialog.txtPassword.getEchoChar() == 0) {

            dialog.txtPassword.setEchoChar('•');
            dialog.btnShowPassword.setText("Show");

        } else {

            dialog.txtPassword.setEchoChar((char) 0);
            dialog.btnShowPassword.setText("Hide");
        }
    }
}
