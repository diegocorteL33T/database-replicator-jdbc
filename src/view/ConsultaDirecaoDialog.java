package view;

import database.dao.DirecaoDAO;
import database.model.TB_REPLICACAO_DIRECAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConsultaDirecaoDialog extends JDialog {

    private JTable table;
    private JButton btnSelecionar;
    private JButton btnCancelar;

    private TB_REPLICACAO_DIRECAO selecionado;

    public ConsultaDirecaoDialog(JFrame parent, DirecaoDAO dao) throws SQLException {
        super(parent, "Consulta de Direcoes");
        setSize(1000, 400);
        setLocationRelativeTo(parent);
        setResizable(false);
        setLayout(null);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("PROCESSO_ID");
        model.addColumn("DIRECAO_ORIGEM");
        model.addColumn("DIRECAO_DESTINO");
        model.addColumn("USUARIO_ORIGEM");
        model.addColumn("USUARIO_DESTINO");
        model.addColumn("SENHA_ORIGEM");
        model.addColumn("SENHA_DESTINO");
        model.addColumn("HABILITADO");

        ArrayList<TB_REPLICACAO_DIRECAO> lista = dao.selectAll();
        for (TB_REPLICACAO_DIRECAO d : lista) {
            model.addRow(new Object[]{
                    d.getId(),
                    d.getProcesso_id(),
                    d.getDirecao_origem(),
                    d.getDirecao_destino(),
                    d.getUsuario_origem(),
                    d.getUsuario_destino(),
                    d.getSenha_origem(),
                    d.getSenha_destino(),
                    d.isHabilitado()
            });
        }

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 970, 300);
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

            TB_REPLICACAO_DIRECAO d = new TB_REPLICACAO_DIRECAO();
            d.setId(Long.parseLong(table.getValueAt(row, 0).toString()));
            d.setProcesso_id(Long.parseLong(table.getValueAt(row, 1).toString()));
            d.setDirecao_origem(table.getValueAt(row, 2).toString());
            d.setDirecao_destino(table.getValueAt(row, 3).toString());
            d.setUsuario_origem(table.getValueAt(row, 4).toString());
            d.setUsuario_destino(table.getValueAt(row, 5).toString());
            d.setSenha_origem(table.getValueAt(row, 6).toString());
            d.setSenha_destino(table.getValueAt(row, 7).toString());
            d.setHabilitado(Boolean.parseBoolean(table.getValueAt(row, 8).toString()));
            selecionado = d;
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

    public TB_REPLICACAO_DIRECAO getSelecionado() {
        return selecionado;
    }
}
