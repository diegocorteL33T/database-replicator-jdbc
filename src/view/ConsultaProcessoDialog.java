package view;

import database.dao.ReplicacaoProcessoDAO;
import database.model.TB_REPLICACAO_PROCESSO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConsultaProcessoDialog extends JDialog {

    private JTable table;
    private JButton btnSelecionar;
    private JButton btnCancelar;

    private TB_REPLICACAO_PROCESSO selecionado;

    public ConsultaProcessoDialog(JFrame parent, ReplicacaoProcessoDAO dao) throws SQLException {
        super(parent,"Consulta de Processos");
        setSize(700,400);
        setLocationRelativeTo(parent);
        setResizable(false);
        setLayout(null);


        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("PROCESSO");
        model.addColumn("DESCRICAO");
        model.addColumn("HABILITADO");

        ArrayList<TB_REPLICACAO_PROCESSO> lista = dao.selectAll();
        for (TB_REPLICACAO_PROCESSO p : lista) {
            model.addRow(new Object[]{p.getId(), p.getProcesso(), p.getDescricao(), p.isHabilitado()});
        }


        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10,10,680,300);
        add(scrollPane);

        btnSelecionar = new JButton("SELECIONAR");
        btnSelecionar.setBounds(10,320,140,30);
        add(btnSelecionar);

        btnCancelar = new JButton("CANCELAR");
        btnCancelar.setBounds(170,320,140,30);
        add(btnCancelar);

        btnSelecionar.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1){
                JOptionPane.showMessageDialog(this,"Selecione algum registro.");
                return;
            }

            TB_REPLICACAO_PROCESSO p = new TB_REPLICACAO_PROCESSO();
            p.setId((long) Integer.parseInt(table.getValueAt(row,0).toString()));
            p.setProcesso(table.getValueAt(row,1).toString());
            p.setDescricao(table.getValueAt(row, 2).toString());
            p.setHabilitado(Boolean.parseBoolean(table.getValueAt(row,3).toString()));
            selecionado = p;
            dispose();
        });


        btnCancelar.addActionListener(e -> {
            selecionado = null;
            dispose();
        });


        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            if(e.getClickCount() == 2) btnSelecionar.doClick();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }

    public TB_REPLICACAO_PROCESSO getSelecionado() {
        return selecionado;
    }


}
