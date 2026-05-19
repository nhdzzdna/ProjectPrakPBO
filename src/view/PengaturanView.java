package view;

import javax.swing.*;
import java.awt.*;

public class PengaturanView extends JPanel {

    // ====================
    // KOMPONEN PUBLIK
    // ====================

    public JTextField     txtNamaLengkap;
    public JTextField     txtUsername;
    public JTextField     txtEmail;
    public JPasswordField txtPasswordBaru;
    public JPasswordField txtKonfirmasi;
    public JButton        btnShowPasswordBaru;
    public JButton        btnShowKonfirmasi;
    public JButton        btnSimpan;

    // ====================
    // KONSTRUKTOR
    // ====================

    public PengaturanView(String namaUser, String usernameUser, String emailUser) {

        // ====================
        // COLOR & FONT
        // ====================

        Color tealColor  = new Color(0, 150, 136);
        Color bodyColor  = new Color(245, 247, 250);
        Color white      = Color.WHITE;
        Color textGray   = new Color(100, 100, 100);
        Color borderColor= new Color(200, 200, 200);

        Font normalFont  = new Font("Poppins", Font.PLAIN, 13);
        Font titleFont   = new Font("Poppins", Font.BOLD,  22);
        Font smallFont   = new Font("Poppins", Font.PLAIN, 12);
        Font boldFont    = new Font("Poppins", Font.BOLD,  13);

        setBackground(bodyColor);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(28, 32, 28, 32));

        // ====================
        // HEADER
        // ====================

        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel lblJudul = new JLabel("Pengaturan");
        lblJudul.setFont(titleFont);
        lblJudul.setForeground(new Color(30, 30, 30));

        JLabel lblSub = new JLabel("Kelola informasi profil dan keamanan akun Anda");
        lblSub.setFont(smallFont);
        lblSub.setForeground(textGray);

        header.add(lblJudul);
        header.add(lblSub);
        header.add(Box.createRigidArea(new Dimension(0, 24)));

        // ====================
        // CARD PROFIL
        // ====================

        JPanel cardProfil = createCard("👤  Informasi Profil", white, boldFont);

        // Avatar placeholder
        JLabel avatar = new JLabel("👤");
        avatar.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        avatar.setHorizontalAlignment(SwingConstants.CENTER);
        avatar.setBorder(BorderFactory.createEmptyBorder(0, 0, 16, 0));
        cardProfil.add(avatar);

        // Form fields
        cardProfil.add(createLabel("Nama Lengkap", smallFont, textGray));
        txtNamaLengkap = createTextField(namaUser, normalFont, borderColor);
        cardProfil.add(txtNamaLengkap);
        cardProfil.add(Box.createRigidArea(new Dimension(0, 10)));

        cardProfil.add(createLabel("Username", smallFont, textGray));
        txtUsername = createTextField(usernameUser, normalFont, borderColor);
        cardProfil.add(txtUsername);
        cardProfil.add(Box.createRigidArea(new Dimension(0, 10)));

        cardProfil.add(createLabel("Email", smallFont, textGray));
        txtEmail = createTextField(emailUser, normalFont, borderColor);
        cardProfil.add(txtEmail);
        cardProfil.add(Box.createRigidArea(new Dimension(0, 10)));

        // ====================
        // CARD PASSWORD
        // ====================

        JPanel cardPassword = createCard("🔒  Ubah Password", white, boldFont);

        cardPassword.add(createLabel("Password Baru", smallFont, textGray));
        JPanel panelPassBaru = createPasswordRow(normalFont, borderColor);
        txtPasswordBaru     = (JPasswordField) panelPassBaru.getComponent(0);
        btnShowPasswordBaru = (JButton)        panelPassBaru.getComponent(1);
        cardPassword.add(panelPassBaru);
        cardPassword.add(Box.createRigidArea(new Dimension(0, 10)));

        cardPassword.add(createLabel("Konfirmasi Password", smallFont, textGray));
        JPanel panelKonfirmasi = createPasswordRow(normalFont, borderColor);
        txtKonfirmasi     = (JPasswordField) panelKonfirmasi.getComponent(0);
        btnShowKonfirmasi = (JButton)        panelKonfirmasi.getComponent(1);
        cardPassword.add(panelKonfirmasi);
        cardPassword.add(Box.createRigidArea(new Dimension(0, 16)));

        JLabel lblInfo = new JLabel("* Kosongkan jika tidak ingin mengubah password");
        lblInfo.setFont(smallFont);
        lblInfo.setForeground(textGray);
        cardPassword.add(lblInfo);

        // ====================
        // TOMBOL SIMPAN
        // ====================

        btnSimpan = new JButton("💾  Simpan Perubahan");
        btnSimpan.setFont(boldFont);
        btnSimpan.setBackground(tealColor);
        btnSimpan.setForeground(white);
        btnSimpan.setFocusPainted(false);
        btnSimpan.setBorderPainted(false);
        btnSimpan.setPreferredSize(new Dimension(200, 42));
        btnSimpan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setOpaque(false);
        btnPanel.add(btnSimpan);

        // ====================
        // RAKIT
        // ====================

        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(cardProfil);
        content.add(Box.createRigidArea(new Dimension(0, 16)));
        content.add(cardPassword);
        content.add(Box.createRigidArea(new Dimension(0, 16)));
        content.add(btnPanel);

        JScrollPane scroll = new JScrollPane(content);
        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        add(header,  BorderLayout.NORTH);
        add(scroll,  BorderLayout.CENTER);
    }

    // ====================
    // HELPER: buat card
    // ====================

    private JPanel createCard(String judul, Color bg, Font boldFont) {
        JPanel card = new JPanel();
        card.setBackground(bg);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(20, 24, 20, 24)
        ));

        JLabel lblCard = new JLabel(judul);
        lblCard.setFont(boldFont);
        lblCard.setForeground(new Color(30, 30, 30));
        lblCard.setBorder(BorderFactory.createEmptyBorder(0, 0, 14, 0));
        card.add(lblCard);

        return card;
    }

    // ====================
    // HELPER: label
    // ====================

    private JLabel createLabel(String text, Font font, Color color) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(font);
        lbl.setForeground(color);
        lbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 4, 0));
        return lbl;
    }

    // ====================
    // HELPER: text field
    // ====================

    private JTextField createTextField(String value, Font font, Color border) {
        JTextField tf = new JTextField(value);
        tf.setFont(font);
        tf.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        tf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(border, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        return tf;
    }

    // ====================
    // HELPER: password row
    // ====================

    private JPanel createPasswordRow(Font font, Color border) {
        JPanel row = new JPanel(new BorderLayout(6, 0));
        row.setOpaque(false);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        JPasswordField pf = new JPasswordField();
        pf.setFont(font);
        pf.setEchoChar('•');
        pf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(border, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        JButton btn = new JButton("Show");
        btn.setFont(new Font("Poppins", Font.PLAIN, 11));
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(60, 40));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        row.add(pf,  BorderLayout.CENTER);
        row.add(btn, BorderLayout.EAST);

        return row;
    }
}
