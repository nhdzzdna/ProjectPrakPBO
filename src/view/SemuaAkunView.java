package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SemuaAkunView extends JPanel {

    public JTable            tableAkun;
    public DefaultTableModel tableModel;
    public JTextField        txtSearch;
    public JLabel            lblTotal;

    public SemuaAkunView() {

        Color navyColor   = new Color(0, 51, 102);
        Color yellowColor = new Color(255, 255, 192);
        Color bodyColor   = new Color(245, 247, 250);
        Color textGray    = new Color(100, 100, 100);

        Font normalFont = new Font("Century Gothic", Font.PLAIN, 13);
        Font titleFont  = new Font("Century Gothic", Font.BOLD,  22);
        Font smallFont  = new Font("Century Gothic", Font.PLAIN, 12);

        setBackground(bodyColor);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(28, 32, 28, 32));

        // HEADER
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JPanel headerLeft = new JPanel();
        headerLeft.setOpaque(false);
        headerLeft.setLayout(new BoxLayout(headerLeft, BoxLayout.Y_AXIS));

        JLabel lblJudul = new JLabel("Semua Akun");
        lblJudul.setFont(titleFont);
        lblJudul.setForeground(navyColor);

        JLabel lblSub = new JLabel("Daftar seluruh akun yang tersimpan di sistem");
        lblSub.setFont(smallFont);
        lblSub.setForeground(textGray);

        headerLeft.add(lblJudul);
        headerLeft.add(lblSub);
        header.add(headerLeft, BorderLayout.WEST);

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

        toolbar.add(txtSearch, BorderLayout.WEST);

        // TABEL
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
        tableAkun.setGridColor(new Color(200, 210, 230));
        tableAkun.setSelectionBackground(new Color(200, 220, 240));
        tableAkun.setSelectionForeground(navyColor);
        tableAkun.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 13));
        tableAkun.getTableHeader().setBackground(navyColor);
        tableAkun.getTableHeader().setForeground(yellowColor);

        tableAkun.getColumnModel().getColumn(0).setPreferredWidth(50);
        tableAkun.getColumnModel().getColumn(1).setPreferredWidth(160);
        tableAkun.getColumnModel().getColumn(2).setPreferredWidth(180);
        tableAkun.getColumnModel().getColumn(3).setPreferredWidth(120);
        tableAkun.getColumnModel().getColumn(4).setPreferredWidth(280);

        tableAkun.getColumnModel().getColumn(3)
                .setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            protected void setValue(Object value) {
                setText("••••••");
            }
        });

        JScrollPane scrollPane = new JScrollPane(tableAkun);
        scrollPane.setBorder(BorderFactory.createLineBorder(navyColor, 1));

        // FOOTER
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        footer.setOpaque(false);
        footer.setBorder(BorderFactory.createEmptyBorder(6, 0, 0, 0));

        lblTotal = new JLabel("Total 0 data");
        lblTotal.setFont(smallFont);
        lblTotal.setForeground(textGray);
        footer.add(lblTotal);

        // RAKIT
        JPanel centerArea = new JPanel(new BorderLayout());
        centerArea.setOpaque(false);
        centerArea.add(toolbar,    BorderLayout.NORTH);
        centerArea.add(scrollPane, BorderLayout.CENTER);
        centerArea.add(footer,     BorderLayout.SOUTH);

        add(header,     BorderLayout.NORTH);
        add(centerArea, BorderLayout.CENTER);
    }
}