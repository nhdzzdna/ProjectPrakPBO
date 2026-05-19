package controller;

import dao.CredentialDAO;
import model.Credential;
import view.SemuaAkunView;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SemuaAkunController {

    private SemuaAkunView view;
    private CredentialDAO dao;
    private TableRowSorter<DefaultTableModel> sorter;

    public SemuaAkunController(SemuaAkunView view) {
        this.view = view;
        this.dao  = new CredentialDAO();

        loadTable();
        initSearch();
    }

    // ====================
    // LOAD TABEL
    // ====================

    public void loadTable() {

        DefaultTableModel model = view.tableModel;
        model.setRowCount(0);

        List<Credential> list = dao.getAll();

        for (Credential c : list) {
            Object[] row = {
                c.getId(),
                c.getPlatform(),
                c.getUsername(),
                c.decrypt(),
                c.getKeterangan()
            };
            model.addRow(row);
        }

        // Mask kolom password
        view.tableAkun.getColumnModel().getColumn(3)
                .setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            protected void setValue(Object value) {
                setText("••••••");
            }
        });

        view.lblTotal.setText("Total " + list.size() + " data");
    }

    // ====================
    // SEARCH
    // ====================

    private void initSearch() {

        sorter = new TableRowSorter<>(view.tableModel);
        view.tableAkun.setRowSorter(sorter);

        view.txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { search(); }
            @Override public void removeUpdate(DocumentEvent e) { search(); }
            @Override public void changedUpdate(DocumentEvent e) { search(); }

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
}
