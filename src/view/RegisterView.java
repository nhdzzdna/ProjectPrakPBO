package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterView extends JFrame {

    public JTextField     txtNamaLengkap;
    public JTextField     txtUsername;
    public JTextField     txtEmail;
    public JPasswordField txtPassword;
    public JPasswordField txtKonfirmasi;

    public JButton btnDaftar;
    public JButton btnKembali;

    // ══════════════════════════════════════════════════════
    // INNER CLASS: HintTextField  (ikon + placeholder hint)
    // ══════════════════════════════════════════════════════
    static class HintTextField extends JTextField {

        private final String hint;
        private final String iconText;

        HintTextField(String hint, String iconText, Font font) {
            this.hint     = hint;
            this.iconText = iconText;
            setFont(font);
            setBackground(new Color(250, 250, 250));
            setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(225, 225, 225), 1),
                    BorderFactory.createEmptyBorder(14, 40, 14, 14)   // kiri 40 = ruang ikon
            ));
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 52));
            setForeground(Color.GRAY);
            setText(hint);

            addFocusListener(new FocusAdapter() {
                @Override public void focusGained(FocusEvent e) {
                    if (getText().equals(hint)) {
                        setText("");
                        setForeground(new Color(40, 40, 40));
                    }
                }
                @Override public void focusLost(FocusEvent e) {
                    if (getText().isEmpty()) {
                        setForeground(Color.GRAY);
                        setText(hint);
                    }
                }
            });
        }

        /** Kembalikan "" jika field masih berisi teks hint */
        public String getRealText() {
            String t = getText();
            return t.equals(hint) ? "" : t;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setFont(new Font("SansSerif", Font.PLAIN, 15));
            g2.setColor(new Color(100, 100, 100));
            FontMetrics fm = g2.getFontMetrics();
            int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
            g2.drawString(iconText, 12, y);
            g2.dispose();
        }
    }

    // ══════════════════════════════════════════════════════
    // INNER CLASS: HintPasswordField  (ikon + placeholder hint)
    // ══════════════════════════════════════════════════════
    static class HintPasswordField extends JPasswordField {

        private final String hint;
        private final String iconText;
        private boolean      showingHint = true;

        HintPasswordField(String hint, String iconText, Font font) {
            this.hint     = hint;
            this.iconText = iconText;
            setFont(font);
            setBackground(new Color(250, 250, 250));
            setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(225, 225, 225), 1),
                    BorderFactory.createEmptyBorder(14, 40, 14, 14)
            ));
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 52));

            // Hint tampil sebagai teks biasa (bukan titik)
            setEchoChar((char) 0);
            setForeground(Color.GRAY);
            setText(hint);

            addFocusListener(new FocusAdapter() {
                @Override public void focusGained(FocusEvent e) {
                    if (showingHint) {
                        setText("");
                        setForeground(new Color(40, 40, 40));
                        setEchoChar('●');
                        showingHint = false;
                    }
                }
                @Override public void focusLost(FocusEvent e) {
                    if (new String(getPassword()).isEmpty()) {
                        showingHint = true;
                        setEchoChar((char) 0);
                        setForeground(Color.GRAY);
                        setText(hint);
                    }
                }
            });
        }

        /** Kembalikan password asli (kosong jika masih hint) */
        public char[] getRealPassword() {
            return showingHint ? new char[0] : getPassword();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setFont(new Font("SansSerif", Font.PLAIN, 15));
            g2.setColor(new Color(100, 100, 100));
            FontMetrics fm = g2.getFontMetrics();
            int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
            g2.drawString(iconText, 12, y);
            g2.dispose();
        }
    }

    // ══════════════════════════════════════════════════════
    // KONSTRUKTOR
    // ══════════════════════════════════════════════════════
    public RegisterView() {

        setTitle("PassKeeper - Daftar Akun");
        setSize(1350, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // COLOR
        Color backgroundColor = new Color(240, 249, 248);
        Color leftColor       = new Color(209, 242, 238);
        Color tealColor       = new Color(0, 150, 136);
        Color darkTeal        = new Color(0, 121, 107);
        Color textGray        = new Color(120, 120, 120);

        // FONT
        Font titleFont    = new Font("Poppins", Font.BOLD, 40);
        Font subtitleFont = new Font("Poppins", Font.PLAIN, 16);
        Font normalFont   = new Font("Poppins", Font.PLAIN, 15);

        // MAIN PANEL
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));

        // ── LEFT PANEL ──────────────────────────────────
        JPanel leftPanel = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 255, 255, 80));
                g2.fillOval(40,  40,  160, 160);
                g2.fillOval(350, 70,  120, 120);
                g2.fillOval(120, 500, 220, 220);
                g2.fillOval(420, 420, 100, 100);
            }
        };
        leftPanel.setBackground(leftColor);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));

        JLabel shieldIcon = new JLabel("🔐");
        shieldIcon.setFont(new Font("SansSerif", Font.PLAIN, 150));
        shieldIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblBrand = new JLabel("PassKeeper");
        lblBrand.setFont(titleFont);
        lblBrand.setForeground(darkTeal);
        lblBrand.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblDescription = new JLabel(
                "<html><center>Buat akun untuk mulai mengelola<br>password dengan aman</center></html>");
        lblDescription.setFont(subtitleFont);
        lblDescription.setForeground(textGray);
        lblDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblDescription.setHorizontalAlignment(SwingConstants.CENTER);

        leftPanel.add(Box.createRigidArea(new Dimension(0, 80)));
        leftPanel.add(shieldIcon);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        leftPanel.add(lblBrand);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
        leftPanel.add(lblDescription);

        // ── RIGHT PANEL ─────────────────────────────────
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(backgroundColor);

        // REGISTER CARD
        JPanel registerCard = new JPanel();
        registerCard.setBackground(Color.WHITE);
        registerCard.setPreferredSize(new Dimension(520, 680));
        registerCard.setLayout(new BoxLayout(registerCard, BoxLayout.Y_AXIS));
        registerCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
                BorderFactory.createEmptyBorder(45, 55, 45, 55)
        ));

        // Judul
        JLabel lblTitle = new JLabel("Buat Akun Baru ✨");
        lblTitle.setFont(new Font("Poppins", Font.BOLD, 26));
        lblTitle.setForeground(new Color(40, 40, 40));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSub = new JLabel("Isi data berikut untuk mendaftar");
        lblSub.setFont(subtitleFont);
        lblSub.setForeground(textGray);
        lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ★ INPUT dengan ikon + hint ★
        txtNamaLengkap = new HintTextField("  Nama Lengkap",       "👤", normalFont);
        txtUsername    = new HintTextField("  Username",            "🔑", normalFont);
        txtEmail       = new HintTextField("  Email",               "📧", normalFont);
        txtPassword    = new HintPasswordField("  Password",        "🔒", normalFont);
        txtKonfirmasi  = new HintPasswordField("  Konfirmasi Password", "🔒", normalFont);

        // Button Daftar
        btnDaftar = new JButton("Daftar");
        btnDaftar.setFont(new Font("Poppins", Font.BOLD, 15));
        btnDaftar.setBackground(tealColor);
        btnDaftar.setForeground(Color.WHITE);
        btnDaftar.setFocusPainted(false);
        btnDaftar.setBorderPainted(false);
        btnDaftar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));
        btnDaftar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDaftar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Button Kembali
        btnKembali = new JButton("← Sudah punya akun? Login");
        btnKembali.setFont(new Font("Poppins", Font.PLAIN, 13));
        btnKembali.setBackground(Color.WHITE);
        btnKembali.setForeground(tealColor);
        btnKembali.setFocusPainted(false);
        btnKembali.setBorderPainted(false);
        btnKembali.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnKembali.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Susun komponen
        registerCard.add(lblTitle);
        registerCard.add(Box.createRigidArea(new Dimension(0, 6)));
        registerCard.add(lblSub);
        registerCard.add(Box.createRigidArea(new Dimension(0, 30)));
        registerCard.add(txtNamaLengkap);
        registerCard.add(Box.createRigidArea(new Dimension(0, 14)));
        registerCard.add(txtUsername);
        registerCard.add(Box.createRigidArea(new Dimension(0, 14)));
        registerCard.add(txtEmail);
        registerCard.add(Box.createRigidArea(new Dimension(0, 14)));
        registerCard.add(txtPassword);
        registerCard.add(Box.createRigidArea(new Dimension(0, 14)));
        registerCard.add(txtKonfirmasi);
        registerCard.add(Box.createRigidArea(new Dimension(0, 30)));
        registerCard.add(btnDaftar);
        registerCard.add(Box.createRigidArea(new Dimension(0, 14)));
        registerCard.add(btnKembali);

        rightPanel.add(registerCard);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        add(mainPanel);
        setVisible(true);
    }
}