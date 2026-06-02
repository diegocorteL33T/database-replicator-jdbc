package view;

import database.dao.ProcessoTabelaDAO;
import database.model.TB_REPLICACAO_PROCESSO_TABELA;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConsultaProcessoTabelaDialog extends JDialog {

    private JTable table;
    private JButton btnSelecionar;
    private JButton btnCancelar;

    private TB_REPLICACAO_PROCESSO_TABELA selecionado;

    public ConsultaProcessoTabelaDialog(JFrame parent, ProcessoTabelaDAO dao) throws SQLException {
        super(parent, "Consulta de Tabelas");
        setSize(900, 400);
        setLocationRelativeTo(parent);
        setResizable(false);
        setLayout(null);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("PROCESSO_ID");
        model.addColumn("TABELA_ORIGEM");
        model.addColumn("TABELA_DESTINO");
        model.addColumn("ORDEM");
        model.addColumn("HABILITADO");
        model.addColumn("DS_WHERE");

        ArrayList<TB_REPLICACAO_PROCESSO_TABELA> lista = dao.selectAll();
        for (TB_REPLICACAO_PROCESSO_TABELA t : lista) {
            model.addRow(new Object[]{
                    t.getId(),
                    t.getProcesso_id(),
                    t.getTabela_origem(),
                    t.getTabela_destino(),
                    t.getOrdem(),
                    t.isHabilitado(),
                    t.getDs_where()
            });
        }

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 870, 300);
        add(scrollPane);

        btnSelecionar = new JButton("SELECIONAR");
        btnSelecionar.setBounds(10, 320, 140, 30);
        add(btnSelecionar);

        btnCancelar = new JButton("CANCELAR");
        btnCancelar.setBounds(170, 320, 140, 30);
        add(btnCancelar);

        btnSelecionar.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Selecione algum registro.");
                return;
            }

            TB_REPLICACAO_PROCESSO_TABELA t = new TB_REPLICACAO_PROCESSO_TABELA();
            t.setId(Long.parseLong(table.getValueAt(row, 0).toString()));
            t.setProcesso_id(Long.parseLong(table.getValueAt(row, 1).toString()));
            t.setTabela_origem(table.getValueAt(row, 2).toString());
            t.setTabela_destino(table.getValueAt(row, 3).toString());
            t.setOrdem(Integer.parseInt(table.getValueAt(row, 4).toString()));
            t.setHabilitado(Boolean.parseBoolean(table.getValueAt(row, 5).toString()));
            t.setDs_where(table.getValueAt(row, 6).toString());
            selecionado = t;
            dispose();
        });

        btnCancelar.addActionListener(e -> {
            selecionado = null;
            dispose();
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) btnSelecionar.doClick();
            }
        });
    }

    public TB_REPLICACAO_PROCESSO_TABELA getSelecionado() {
        return selecionado;
    }
}
