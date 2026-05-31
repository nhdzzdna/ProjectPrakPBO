package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DashboardView extends JFrame {

    public JTable            tableCredential;
    public DefaultTableModel tableModel;
    public JTextField        txtSearch;
    public JButton           btnTambah;
    public JButton           btnUpdate;
    public JButton           btnHapus;
    public JButton           btnLogout;
    public JLabel            lblClock;
    public JLabel            lblTotal;
    public JButton           menuDashboard;
    public JButton           menuSemua;
    public JButton           menuPengaturan;
    public JButton           menuTentang;
    public SemuaAkunView     semuaAkunView;
    public PengaturanView    pengaturanView;
    public TentangView       tentangView;

    private JPanel     cardPanel;
    private CardLayout cardLayout;

    public static final String CARD_DASHBOARD  = "dashboard";
    public static final String CARD_SEMUA      = "semua";
    public static final String CARD_PENGATURAN = "pengaturan";
    public static final String CARD_TENTANG    = "tentang";

    public DashboardView(String namaUser) {
        this(namaUser, namaUser, "");
    }

    public DashboardView(String namaUser, String usernameUser, String emailUser) {

        setTitle("PassKeeper - Dashboard");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // COLOR
        Color navyColor  = new Color(0, 51, 102);
        Color yellowColor = new Color(255, 255, 192);
        Color bodyColor  = new Color(245, 247, 250);
        Color white      = Color.WHITE;
        Color textGray   = new Color(100, 100, 100);
        Color redColor   = new Color(180, 50, 50);
        Color blueColor  = new Color(30, 100, 180);

        // FONT
        Font sideFont   = new Font("Century Gothic", Font.BOLD,  14);
        Font normalFont = new Font("Century Gothic", Font.PLAIN, 12);
        Font titleFont  = new Font("Century Gothic", Font.BOLD,  22);
        Font smallFont  = new Font("Century Gothic", Font.PLAIN, 11);

        setLayout(new BorderLayout());

        // ====================
        // SIDEBAR
        // ====================
        JPanel sidebar = new JPanel();
        sidebar.setBackground(navyColor);
        sidebar.setPreferredSize(new Dimension(220, getHeight()));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

        JLabel lblLogo = new JLabel("PassKeeper");
        lblLogo.setFont(new Font("Century Gothic", Font.BOLD, 18));
        lblLogo.setForeground(yellowColor);
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblUser = new JLabel(namaUser);
        lblUser.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        lblUser.setForeground(new Color(200, 210, 230));
        lblUser.setAlignmentX(Component.CENTER_ALIGNMENT);

        JSeparator sep1 = new JSeparator();
        sep1.setForeground(new Color(255, 255, 255, 60));
        sep1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));

        menuDashboard  = createSideMenu("Dashboard",  sideFont, yellowColor);
        menuSemua      = createSideMenu("Semua Akun", sideFont, new Color(200, 210, 230));
        menuPengaturan = createSideMenu("Pengaturan", sideFont, new Color(200, 210, 230));
        menuTentang    = createSideMenu("Tentang",    sideFont, new Color(200, 210, 230));

        sidebar.add(lblLogo);
        sidebar.add(Box.createRigidArea(new Dimension(0, 6)));
        sidebar.add(lblUser);
        sidebar.add(Box.createRigidArea(new Dimension(0, 24)));
        sidebar.add(sep1);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));
        sidebar.add(menuDashboard);
        sidebar.add(Box.createRigidArea(new Dimension(0, 8)));
        sidebar.add(menuSemua);
        sidebar.add(Box.createRigidArea(new Dimension(0, 8)));
        sidebar.add(menuPengaturan);
        sidebar.add(Box.createRigidArea(new Dimension(0, 8)));
        sidebar.add(menuTentang);
        sidebar.add(Box.createVerticalGlue());

        btnLogout = new JButton("Logout");
        btnLogout.setFont(sideFont);
        btnLogout.setForeground(yellowColor);
        btnLogout.setBackground(redColor);
        btnLogout.setFocusPainted(false);
        btnLogout.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 80, 80), 1),
                BorderFactory.createEmptyBorder(10, 16, 10, 16)
        ));
        btnLogout.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btnLogout.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sidebar.add(btnLogout);

        // ====================
        // DASHBOARD PANEL
        // ====================
        JPanel dashboardPanel = new JPanel(new BorderLayout());
        dashboardPanel.setBackground(bodyColor);
        dashboardPanel.setBorder(BorderFactory.createEmptyBorder(28, 32, 28, 32));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JPanel headerLeft = new JPanel();
        headerLeft.setOpaque(false);
        headerLeft.setLayout(new BoxLayout(headerLeft, BoxLayout.Y_AXIS));

        JLabel lblJudul = new JLabel("Dashboard");
        lblJudul.setFont(titleFont);
        lblJudul.setForeground(navyColor);

        JLabel lblSub = new JLabel("Kelola dan lihat semua akun yang tersimpan");
        lblSub.setFont(smallFont);
        lblSub.setForeground(textGray);

        headerLeft.add(lblJudul);
        headerLeft.add(lblSub);

        lblClock = new JLabel();
        lblClock.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        lblClock.setForeground(textGray);

        header.add(headerLeft, BorderLayout.WEST);
        header.add(lblClock,   BorderLayout.EAST);

        // TOOLBAR
        JPanel toolbar = new JPanel(new BorderLayout(12, 0));
        toolbar.setOpaque(false);
        toolbar.setBorder(BorderFactory.createEmptyBorder(18, 0, 14, 0));

        txtSearch = new JTextField();
        txtSearch.setFont(normalFont);
        txtSearch.setPreferredSize(new Dimension(300, 40));
        txtSearch.setToolTipText("Cari platform / username...");
        txtSearch.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(navyColor, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        btnPanel.setOpaque(false);

        btnTambah = createActionButton("+ Tambah Akun", navyColor,  yellowColor, normalFont);
        btnUpdate = createActionButton("Edit",          blueColor,  white,       normalFont);
        btnHapus  = createActionButton("Hapus",         redColor,   white,       normalFont);

        btnPanel.add(btnTambah);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnHapus);

        toolbar.add(txtSearch, BorderLayout.WEST);
        toolbar.add(btnPanel,  BorderLayout.EAST);

        // TABLE
        String[] kolom = {"No", "Aplikasi / Akun", "Username", "Password", "Keterangan"};
        tableModel = new DefaultTableModel(kolom, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tableCredential = new JTable(tableModel);
        tableCredential.setFont(normalFont);
        tableCredential.setRowHeight(36);
        tableCredential.setGridColor(new Color(200, 210, 230));
        tableCredential.setSelectionBackground(new Color(200, 220, 240));
        tableCredential.setSelectionForeground(navyColor);
        tableCredential.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 12));
        tableCredential.getTableHeader().setBackground(navyColor);
        tableCredential.getTableHeader().setForeground(yellowColor);

        tableCredential.getColumnModel().getColumn(0).setPreferredWidth(50);
        tableCredential.getColumnModel().getColumn(1).setPreferredWidth(160);
        tableCredential.getColumnModel().getColumn(2).setPreferredWidth(180);
        tableCredential.getColumnModel().getColumn(3).setPreferredWidth(120);
        tableCredential.getColumnModel().getColumn(4).setPreferredWidth(280);

        JScrollPane scrollPane = new JScrollPane(tableCredential);
        scrollPane.setBorder(BorderFactory.createLineBorder(navyColor, 1));

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        footer.setOpaque(false);
        footer.setBorder(BorderFactory.createEmptyBorder(6, 0, 0, 0));

        lblTotal = new JLabel("Total 0 data");
        lblTotal.setFont(smallFont);
        lblTotal.setForeground(textGray);
        footer.add(lblTotal);

        dashboardPanel.add(header, BorderLayout.NORTH);

        JPanel centerArea = new JPanel(new BorderLayout());
        centerArea.setOpaque(false);
        centerArea.add(toolbar,    BorderLayout.NORTH);
        centerArea.add(scrollPane, BorderLayout.CENTER);
        centerArea.add(footer,     BorderLayout.SOUTH);

        dashboardPanel.add(centerArea, BorderLayout.CENTER);

        // SUB-VIEW
        semuaAkunView  = new SemuaAkunView();
        pengaturanView = new PengaturanView(namaUser, usernameUser, emailUser);
        tentangView    = new TentangView();

        // CARD LAYOUT
        cardLayout = new CardLayout();
        cardPanel  = new JPanel(cardLayout);
        cardPanel.add(dashboardPanel, CARD_DASHBOARD);
        cardPanel.add(semuaAkunView,  CARD_SEMUA);
        cardPanel.add(pengaturanView, CARD_PENGATURAN);
        cardPanel.add(tentangView,    CARD_TENTANG);

        add(sidebar,   BorderLayout.WEST);
        add(cardPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void showCard(String name) {
        cardLayout.show(cardPanel, name);
        updateMenuStyle(name);
    }

    private void updateMenuStyle(String active) {
        Color yellow = new Color(255, 255, 192);
        Color dimmed = new Color(200, 210, 230);
        menuDashboard .setForeground(active.equals(CARD_DASHBOARD)  ? yellow : dimmed);
        menuSemua     .setForeground(active.equals(CARD_SEMUA)      ? yellow : dimmed);
        menuPengaturan.setForeground(active.equals(CARD_PENGATURAN) ? yellow : dimmed);
        menuTentang   .setForeground(active.equals(CARD_TENTANG)    ? yellow : dimmed);
    }

    private JButton createSideMenu(String text, Font font, Color fg) {
        JButton btn = new JButton(text);
        btn.setFont(font);
        btn.setForeground(fg);
        btn.setBackground(new Color(0, 0, 0, 0));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JButton createActionButton(String text, Color bg, Color fg, Font font) {
        JButton btn = new JButton(text);
        btn.setFont(font);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(140, 38));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }
}