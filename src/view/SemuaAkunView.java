package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SemuaAkunView extends JPanel {

    // ====================
    // KOMPONEN PUBLIK
    // ====================

    public JTable            tableAkun;
    public DefaultTableModel tableModel;
    public JTextField        txtSearch;
    public JLabel            lblTotal;

    // ====================
    // KONSTRUKTOR
    // ====================

    public SemuaAkunView() {

        // ====================
        // COLOR & FONT
        // ====================

        Color tealColor  = new Color(0, 150, 136);
        Color bodyColor  = new Color(245, 247, 250);
        Color white      = Color.WHITE;
        Color textGray   = new Color(100, 100, 100);

        Font normalFont  = new Font("Poppins", Font.PLAIN, 13);
        Font titleFont   = new Font("Poppins", Font.BOLD,  22);
        Font smallFont   = new Font("Poppins", Font.PLAIN, 12);

        setBackground(bodyColor);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(28, 32, 28, 32));

        // ====================
        // HEADER
        // ====================

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JPanel headerLeft = new JPanel();
        headerLeft.setOpaque(false);
        headerLeft.setLayout(new BoxLayout(headerLeft, BoxLayout.Y_AXIS));

        JLabel lblJudul = new JLabel("Semua Akun");
        lblJudul.setFont(titleFont);
        lblJudul.setForeground(new Color(30, 30, 30));

        JLabel lblSub = new JLabel("Daftar seluruh akun yang tersimpan di sistem");
        lblSub.setFont(smallFont);
        lblSub.setForeground(textGray);

        headerLeft.add(lblJudul);
        headerLeft.add(lblSub);
        header.add(headerLeft, BorderLayout.WEST);

        // ====================
        // TOOLBAR (search)
        // ====================

        JPanel toolbar = new JPanel(new BorderLayout(12, 0));
        toolbar.setOpaque(false);
        toolbar.setBorder(BorderFactory.createEmptyBorder(18, 0, 14, 0));

        txtSearch = new JTextField();
        txtSearch.setFont(normalFont);
        txtSearch.setPreferredSize(new Dimension(300, 40));
        txtSearch.setToolTipText("Cari platform / username...");
        txtSearch.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        toolbar.add(txtSearch, BorderLayout.WEST);

        // ====================
        // TABEL
        // ====================

        String[] kolom = {"No", "Aplikasi / Akun", "Username", "Password", "Keterangan"};

        tableModel = new DefaultTableModel(kolom, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableAkun = new JTable(tableModel);
        tableAkun.setFont(normalFont);
        tableAkun.setRowHeight(36);
        tableAkun.setGridColor(new Color(230, 230, 230));
        tableAkun.setSelectionBackground(new Color(200, 240, 238));
        tableAkun.setSelectionForeground(new Color(0, 100, 90));
        tableAkun.getTableHeader().setFont(new Font("Poppins", Font.BOLD, 13));
        tableAkun.getTableHeader().setBackground(new Color(235, 248, 246));
        tableAkun.getTableHeader().setForeground(new Color(0, 100, 90));

        tableAkun.getColumnModel().getColumn(0).setPreferredWidth(50);
        tableAkun.getColumnModel().getColumn(1).setPreferredWidth(160);
        tableAkun.getColumnModel().getColumn(2).setPreferredWidth(180);
        tableAkun.getColumnModel().getColumn(3).setPreferredWidth(120);
        tableAkun.getColumnModel().getColumn(4).setPreferredWidth(280);

        // Mask password
        tableAkun.getColumnModel().getColumn(3)
                .setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            protected void setValue(Object value) {
                setText("••••••");
            }
        });

        JScrollPane scrollPane = new JScrollPane(tableAkun);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));

        // ====================
        // FOOTER
        // ====================

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        footer.setOpaque(false);
        footer.setBorder(BorderFactory.createEmptyBorder(6, 0, 0, 0));

        lblTotal = new JLabel("Total 0 data");
        lblTotal.setFont(smallFont);
        lblTotal.setForeground(textGray);
        footer.add(lblTotal);

        // ====================
        // RAKIT
        // ====================

        JPanel centerArea = new JPanel(new BorderLayout());
        centerArea.setOpaque(false);
        centerArea.add(toolbar,    BorderLayout.NORTH);
        centerArea.add(scrollPane, BorderLayout.CENTER);
        centerArea.add(footer,     BorderLayout.SOUTH);

        add(header,     BorderLayout.NORTH);
        add(centerArea, BorderLayout.CENTER);
    }
}
