package view;

import component.RoundedButton;
import component.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginView extends JFrame {

    public JTextField     txtUsername;
    public JPasswordField txtPassword;
    public JButton        btnLogin;
    public JButton        btnDaftar;

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
                    BorderFactory.createEmptyBorder(18, 14, 18, 14)
            ));
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 62));
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

        /** Kembalikan null / "" jika field masih berisi teks hint */
        public String getRealText() {
            String t = getText();
            return t.equals(hint) ? "" : t;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // gambar ikon emoji di sisi kiri
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setFont(new Font("SansSerif", Font.PLAIN, 16));
            g2.setColor(new Color(100, 100, 100));
            FontMetrics fm = g2.getFontMetrics();
            int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
            g2.drawString(iconText, 14, y);
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
                    BorderFactory.createEmptyBorder(18, 14, 18, 14)
            ));
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 62));

            // Tampilkan hint sebagai teks biasa (bukan titik)
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
            g2.setFont(new Font("SansSerif", Font.PLAIN, 16));
            g2.setColor(new Color(100, 100, 100));
            FontMetrics fm = g2.getFontMetrics();
            int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
            g2.drawString(iconText, 14, y);
            g2.dispose();
        }
    }

    // ══════════════════════════════════════════════════════
    // KONSTRUKTOR
    // ══════════════════════════════════════════════════════
    public LoginView() {

        setTitle("PassKeeper");
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
                "<html><center>Kelola password dengan aman<br>dan modern</center></html>");
        lblDescription.setFont(subtitleFont);
        lblDescription.setForeground(textGray);
        lblDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblDescription.setHorizontalAlignment(SwingConstants.CENTER);

        leftPanel.add(Box.createRigidArea(new Dimension(0, 120)));
        leftPanel.add(shieldIcon);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        leftPanel.add(lblBrand);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
        leftPanel.add(lblDescription);

        // ── RIGHT PANEL ─────────────────────────────────
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(backgroundColor);

        // LOGIN CARD
        RoundedPanel loginCard = new RoundedPanel(45);
        loginCard.setBackground(Color.WHITE);
        loginCard.setPreferredSize(new Dimension(500, 660));
        loginCard.setLayout(new BoxLayout(loginCard, BoxLayout.Y_AXIS));
        loginCard.setBorder(BorderFactory.createEmptyBorder(55, 55, 55, 55));

        // Title
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        titlePanel.setOpaque(false);
        JLabel lblTitle1 = new JLabel("Selamat Datang");
        lblTitle1.setFont(new Font("Poppins", Font.BOLD, 28));
        lblTitle1.setForeground(new Color(40, 40, 40));
        JLabel lblHand = new JLabel(" !");
        lblHand.setFont(new Font("SansSerif", Font.PLAIN, 28));
        titlePanel.add(lblTitle1);
        titlePanel.add(lblHand);

        JLabel lblTitle2 = new JLabel("di PassKeeper");
        lblTitle2.setFont(new Font("Poppins", Font.BOLD, 30));
        lblTitle2.setForeground(new Color(0, 150, 136));
        lblTitle2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitle = new JLabel("Kelola semua akun Anda dengan aman");
        lblSubtitle.setFont(subtitleFont);
        lblSubtitle.setForeground(textGray);
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ★ INPUT dengan ikon + hint ★
        // Buat dengan padding kiri ekstra supaya teks tidak nabrak ikon
        txtUsername = new HintTextField("  Username", "👤", normalFont) {{
            setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(225, 225, 225), 1),
                    BorderFactory.createEmptyBorder(18, 40, 18, 14)   // kiri 40 = ruang ikon
            ));
        }};

        txtPassword = new HintPasswordField("  Password", "🔒", normalFont) {{
            setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(225, 225, 225), 1),
                    BorderFactory.createEmptyBorder(18, 40, 18, 14)
            ));
        }};

        // Button Login
        btnLogin = new RoundedButton("Login", tealColor);
        btnLogin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 65));
        btnLogin.setPreferredSize(new Dimension(340, 65));
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Button Daftar (link style)
        btnDaftar = new JButton("Belum punya akun? Daftar di sini");
        btnDaftar.setFont(new Font("Poppins", Font.PLAIN, 13));
        btnDaftar.setBackground(Color.WHITE);
        btnDaftar.setForeground(tealColor);
        btnDaftar.setFocusPainted(false);
        btnDaftar.setBorderPainted(false);
        btnDaftar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDaftar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Info
        JLabel lblInfo = new JLabel("🛡 Masukkan username dan password untuk melanjutkan");
        lblInfo.setFont(new Font("Poppins", Font.PLAIN, 12));
        lblInfo.setForeground(textGray);
        lblInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Susun komponen
        loginCard.add(titlePanel);
        loginCard.add(Box.createRigidArea(new Dimension(0, -15)));
        loginCard.add(lblTitle2);
        loginCard.add(Box.createRigidArea(new Dimension(0, 18)));
        loginCard.add(lblSubtitle);
        loginCard.add(Box.createRigidArea(new Dimension(0, 55)));
        loginCard.add(txtUsername);
        loginCard.add(Box.createRigidArea(new Dimension(0, 25)));
        loginCard.add(txtPassword);
        loginCard.add(Box.createRigidArea(new Dimension(0, 40)));
        loginCard.add(btnLogin);
        loginCard.add(Box.createRigidArea(new Dimension(0, 18)));
        loginCard.add(btnDaftar);
        loginCard.add(Box.createRigidArea(new Dimension(0, 20)));
        loginCard.add(lblInfo);

        rightPanel.add(loginCard);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        add(mainPanel);
        setVisible(true);
    }
}