package view;

import javax.swing.*;
import java.awt.*;

public class TentangView extends JPanel {

    public TentangView() {

        // ====================
        // COLOR & FONT
        // ====================

        Color tealColor  = new Color(0, 150, 136);
        Color darkTeal   = new Color(0, 121, 107);
        Color bodyColor  = new Color(245, 247, 250);
        Color white      = Color.WHITE;
        Color textGray   = new Color(100, 100, 100);

        Font titleFont   = new Font("Poppins", Font.BOLD,  22);
        Font boldFont    = new Font("Poppins", Font.BOLD,  14);
        Font normalFont  = new Font("Poppins", Font.PLAIN, 13);
        Font smallFont   = new Font("Poppins", Font.PLAIN, 12);
        Font bigFont     = new Font("Poppins", Font.BOLD,  32);

        setBackground(bodyColor);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(28, 32, 28, 32));

        // ====================
        // HEADER
        // ====================

        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel lblJudul = new JLabel("Tentang");
        lblJudul.setFont(titleFont);
        lblJudul.setForeground(new Color(30, 30, 30));

        JLabel lblSub = new JLabel("Informasi aplikasi PassKeeper");
        lblSub.setFont(smallFont);
        lblSub.setForeground(textGray);

        header.add(lblJudul);
        header.add(lblSub);
        header.add(Box.createRigidArea(new Dimension(0, 24)));

        // ====================
        // CARD UTAMA (logo + nama)
        // ====================

        JPanel cardApp = new JPanel();
        cardApp.setBackground(white);
        cardApp.setLayout(new BoxLayout(cardApp, BoxLayout.Y_AXIS));
        cardApp.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(30, 24, 30, 24)
        ));

        JLabel lblLogo = new JLabel("🔐");
        lblLogo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 52));
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblAppName = new JLabel("PassKeeper");
        lblAppName.setFont(bigFont);
        lblAppName.setForeground(tealColor);
        lblAppName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblVersi = new JLabel("Versi 1.0.0");
        lblVersi.setFont(normalFont);
        lblVersi.setForeground(textGray);
        lblVersi.setAlignmentX(Component.CENTER_ALIGNMENT);

        JSeparator sep = new JSeparator();
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        sep.setForeground(new Color(220, 220, 220));

        JLabel lblDeskripsi = new JLabel(
                "<html><div style='text-align:center;width:420px'>"
                + "PassKeeper adalah aplikasi pengelola kata sandi berbasis desktop "
                + "yang membantu Anda menyimpan dan mengakses akun-akun penting "
                + "dengan aman dan terorganisir."
                + "</div></html>"
        );
        lblDeskripsi.setFont(normalFont);
        lblDeskripsi.setForeground(textGray);
        lblDeskripsi.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblDeskripsi.setBorder(BorderFactory.createEmptyBorder(12, 0, 0, 0));

        cardApp.add(lblLogo);
        cardApp.add(Box.createRigidArea(new Dimension(0, 8)));
        cardApp.add(lblAppName);
        cardApp.add(Box.createRigidArea(new Dimension(0, 4)));
        cardApp.add(lblVersi);
        cardApp.add(Box.createRigidArea(new Dimension(0, 20)));
        cardApp.add(sep);
        cardApp.add(lblDeskripsi);

        // ====================
        // CARD FITUR
        // ====================

        JPanel cardFitur = createCard("✨  Fitur Utama", white, boldFont);

        String[] fitur = {
            "🔐  Penyimpanan password terenkripsi (Base64)",
            "👤  Manajemen akun multi-user dengan autentikasi",
            "🔍  Pencarian cepat berdasarkan platform / username",
            "✏️   CRUD lengkap: Tambah, Edit, dan Hapus data",
            "⏰  Tampilan jam real-time di dashboard"
        };

        for (String f : fitur) {
            JLabel lbl = new JLabel(f);
            lbl.setFont(normalFont);
            lbl.setForeground(new Color(50, 50, 50));
            lbl.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
            cardFitur.add(lbl);
        }

        // ====================
        // CARD TIM
        // ====================

        JPanel cardTim = createCard("👥  123240251-123240258", white, boldFont);

        String[][] anggota = {
            {"👨‍💻", "Ketua Tim",    "Bertanggung jawab atas arsitektur sistem"},
            {"👩‍💻", "Backend Dev", "Mengelola koneksi database & DAO layer"},
            {"🎨", "Frontend Dev", "Merancang tampilan antarmuka pengguna"},
        };

        for (String[] a : anggota) {
            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
            row.setOpaque(false);

            JLabel icon = new JLabel(a[0]);
            icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));

            JLabel info = new JLabel("<html><b>" + a[1] + "</b> — " + a[2] + "</html>");
            info.setFont(normalFont);
            info.setForeground(new Color(50, 50, 50));

            row.add(icon);
            row.add(info);
            cardTim.add(row);
        }

        // ====================
        // CARD TEKNOLOGI
        // ====================

        JPanel cardTek = createCard("🛠️  Teknologi", white, boldFont);

        String[][] tek = {
            {"☕", "Java SE",        "Bahasa pemrograman utama"},
            {"🖥️", "Java Swing",    "Framework GUI desktop"},
            {"🗄️", "MySQL",         "Database penyimpanan data"},
            {"🔗", "JDBC",          "Konektivitas Java ke database"},
        };

        for (String[] t : tek) {
            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
            row.setOpaque(false);

            JLabel icon = new JLabel(t[0]);
            icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));

            JLabel info = new JLabel("<html><b>" + t[1] + "</b> — " + t[2] + "</html>");
            info.setFont(normalFont);
            info.setForeground(new Color(50, 50, 50));

            row.add(icon);
            row.add(info);
            cardTek.add(row);
        }

        // ====================
        // RAKIT
        // ====================

        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(cardApp);
        content.add(Box.createRigidArea(new Dimension(0, 16)));
        content.add(cardFitur);
        content.add(Box.createRigidArea(new Dimension(0, 16)));
        content.add(cardTim);
        content.add(Box.createRigidArea(new Dimension(0, 16)));
        content.add(cardTek);
        content.add(Box.createRigidArea(new Dimension(0, 16)));

        // Footer
        JLabel lblFooter = new JLabel("© 2025 PassKeeper — Praktikum Pemrograman Berorientasi Objek");
        lblFooter.setFont(smallFont);
        lblFooter.setForeground(new Color(160, 160, 160));
        lblFooter.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(lblFooter);

        JScrollPane scroll = new JScrollPane(content);
        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        add(header, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
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
        lblCard.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0));
        card.add(lblCard);

        return card;
    }
}
