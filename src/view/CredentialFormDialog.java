package view;

import javax.swing.*;
import java.awt.*;

public class CredentialFormDialog extends JDialog {

    public JTextField     txtPlatform;
    public JTextField     txtUsername;
    public JPasswordField txtPassword;
    public JTextField     txtKeterangan;

    public JButton btnSimpan;
    public JButton btnBatal;
    public JButton btnShowPassword;

    public CredentialFormDialog(JFrame parent, String judul) {

        super(parent, judul, true);

        setSize(480, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        Color navyColor  = new Color(0, 51, 102);
        Color yellowColor = new Color(255, 255, 192);
        Color redColor   = new Color(180, 50, 50);

        Font labelFont = new Font("Century Gothic", Font.BOLD,  13);
        Font inputFont = new Font("Century Gothic", Font.PLAIN, 13);

        // PANEL FORM
        JPanel form = new JPanel();
        form.setBackground(Color.WHITE);
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(BorderFactory.createEmptyBorder(30, 35, 20, 35));

        // JUDUL
        JLabel lblJudul = new JLabel(judul);
        lblJudul.setFont(new Font("Century Gothic", Font.BOLD, 18));
        lblJudul.setForeground(navyColor);
        lblJudul.setAlignmentX(Component.LEFT_ALIGNMENT);

        form.add(lblJudul);
        form.add(Box.createRigidArea(new Dimension(0, 22)));

        // Aplikasi / Akun
        form.add(createLabel("Aplikasi / Akun", labelFont, navyColor));
        form.add(Box.createRigidArea(new Dimension(0, 5)));
        txtPlatform = new JTextField();
        styleField(txtPlatform, inputFont, navyColor);
        form.add(txtPlatform);
        form.add(Box.createRigidArea(new Dimension(0, 14)));

        // Username
        form.add(createLabel("Username", labelFont, navyColor));
        form.add(Box.createRigidArea(new Dimension(0, 5)));
        txtUsername = new JTextField();
        styleField(txtUsername, inputFont, navyColor);
        form.add(txtUsername);
        form.add(Box.createRigidArea(new Dimension(0, 14)));

        // Password
        form.add(createLabel("Password", labelFont, navyColor));
        form.add(Box.createRigidArea(new Dimension(0, 5)));

        JPanel passPanel = new JPanel(new BorderLayout(6, 0));
        passPanel.setOpaque(false);
        passPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        passPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtPassword = new JPasswordField();
        txtPassword.setFont(inputFont);
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(navyColor, 1),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));

        btnShowPassword = new JButton("Show");
        btnShowPassword.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        btnShowPassword.setBackground(navyColor);
        btnShowPassword.setForeground(yellowColor);
        btnShowPassword.setFocusPainted(false);
        btnShowPassword.setBorderPainted(false);
        btnShowPassword.setPreferredSize(new Dimension(70, 45));

        passPanel.add(txtPassword,     BorderLayout.CENTER);
        passPanel.add(btnShowPassword, BorderLayout.EAST);

        form.add(passPanel);
        form.add(Box.createRigidArea(new Dimension(0, 14)));

        // Keterangan
        form.add(createLabel("Keterangan", labelFont, navyColor));
        form.add(Box.createRigidArea(new Dimension(0, 5)));
        txtKeterangan = new JTextField();
        styleField(txtKeterangan, inputFont, navyColor);
        form.add(txtKeterangan);
        form.add(Box.createRigidArea(new Dimension(0, 26)));

        // TOMBOL
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        btnPanel.setOpaque(false);

        btnBatal = new JButton("Batal");
        btnBatal.setFont(new Font("Century Gothic", Font.PLAIN, 13));
        btnBatal.setBackground(new Color(200, 210, 230));
        btnBatal.setForeground(navyColor);
        btnBatal.setFocusPainted(false);
        btnBatal.setBorderPainted(false);
        btnBatal.setPreferredSize(new Dimension(110, 40));
        btnBatal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnSimpan = new JButton("Simpan");
        btnSimpan.setFont(new Font("Century Gothic", Font.BOLD, 13));
        btnSimpan.setBackground(navyColor);
        btnSimpan.setForeground(yellowColor);
        btnSimpan.setFocusPainted(false);
        btnSimpan.setBorderPainted(false);
        btnSimpan.setPreferredSize(new Dimension(120, 40));
        btnSimpan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnPanel.add(btnBatal);
        btnPanel.add(btnSimpan);

        form.add(btnPanel);
        add(form, BorderLayout.CENTER);
    }

    private JLabel createLabel(String text, Font font, Color color) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(font);
        lbl.setForeground(color);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    private void styleField(JTextField field, Font font, Color borderColor) {
        field.setFont(font);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderColor, 1),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
    }
}