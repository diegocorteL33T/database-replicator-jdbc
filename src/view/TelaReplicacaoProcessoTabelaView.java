package view;

import database.dao.ProcessoTabelaDAO;
import database.dao.ReplicacaoProcessoDAO;
import database.model.TB_REPLICACAO_PROCESSO;
import database.model.TB_REPLICACAO_PROCESSO_TABELA;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class TelaReplicacaoProcessoTabelaView extends JFrame {

    private enum ModoTela{NENHUM, INSERT, UPDATE}
    private ModoTela modoTela = ModoTela.NENHUM;

    private final Connection conn;
    private final ProcessoTabelaDAO dao;
    private final ReplicacaoProcessoDAO processoDAO;

    private JTextField txfId;
    private JComboBox<TB_REPLICACAO_PROCESSO> cmbProcesso;
    private JTextField txfTabelaOrigem;
    private JTextField txfTabelaDestino;
    private JTextField txfOrdem;
    private JCheckBox chkHabilitado;
    private JTextArea txtWhere;

    private JButton btnSalvar;
    private JButton btnAdicionar;
    private JButton btnBuscar;
    private JButton btnExcluir;

    public TelaReplicacaoProcessoTabelaView() throws SQLException {
        this(null, null, null);
    }

    public TelaReplicacaoProcessoTabelaView(Connection conn) throws SQLException {
        this(conn, null, null);
    }

    public TelaReplicacaoProcessoTabelaView(Connection conn, ProcessoTabelaDAO dao, ReplicacaoProcessoDAO processoDAO) throws SQLException {
        this.conn = conn;
        this.dao = dao != null ? dao : (conn != null ? new ProcessoTabelaDAO(conn) : null);
        this.processoDAO = processoDAO != null ? processoDAO : (conn != null ? new ReplicacaoProcessoDAO(conn) : null);

        setTitle("Cadastro de tabelas");
        setSize(720, 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        btnBuscar = new JButton("BUSCAR");
        btnAdicionar = new JButton("ADICIONAR");
        btnSalvar = new JButton("SALVAR");
        btnExcluir = new JButton("EXCLUIR");

        btnBuscar.setBounds(30, 10, 130, 30);
        btnAdicionar.setBounds(180, 10, 130, 30);
        btnSalvar.setBounds(320, 10, 130, 30);
        btnExcluir.setBounds(460, 10, 130, 30);

        getContentPane().add(btnBuscar);
        getContentPane().add(btnAdicionar);
        getContentPane().add(btnSalvar);
        getContentPane().add(btnExcluir);

        JLabel lblId = new JLabel("ID: ");
        lblId.setBounds(10,70,140,25);
        getContentPane().add(lblId);

        txfId = new JTextField();
        txfId.setBounds(160,70,220,25);
        getContentPane().add(txfId);

        JLabel lblProcesso = new JLabel("PROCESSO: ");
        lblProcesso.setBounds(10,105,140,25);
        getContentPane().add(lblProcesso);

        cmbProcesso = new JComboBox<>();
        cmbProcesso.setBounds(160,105,200,25);
        getContentPane().add(cmbProcesso);

        JLabel lblTabelaOrigem = new JLabel("TABELA ORIGEM: ");
        lblTabelaOrigem.setBounds(10,140,140,25);
        getContentPane().add(lblTabelaOrigem);

        txfTabelaOrigem = new JTextField();
        txfTabelaOrigem.setBounds(160,140,520,25);
        getContentPane().add(txfTabelaOrigem);

        JLabel lblTabelaDestino = new JLabel("TABELA DESTINO: ");
        lblTabelaDestino.setBounds(10,175,140,25);
        getContentPane().add(lblTabelaDestino);

        txfTabelaDestino = new JTextField();
        txfTabelaDestino.setBounds(160,175,520,25);
        getContentPane().add(txfTabelaDestino);

        JLabel lblOrdem = new JLabel("ORDEM: ");
        lblOrdem.setBounds(10,210,140,25);
        getContentPane().add(lblOrdem);

        txfOrdem = new JTextField();
        txfOrdem.setBounds(160,210,220,25);
        getContentPane().add(txfOrdem);

        chkHabilitado = new JCheckBox("HABILITADO");
        chkHabilitado.setBounds(10,245,140,25);
        getContentPane().add(chkHabilitado);

        JLabel lblWhere = new JLabel("WHERE: ");
        lblWhere.setBounds(10,280,140,25);
        getContentPane().add(lblWhere);

        txtWhere = new JTextArea();
        txtWhere.setBounds(160,280,520,80);
        getContentPane().add(txtWhere);

        carregarProcessos();

        txfId.setEnabled(false);
        cmbProcesso.setEnabled(false);
        txfTabelaOrigem.setEnabled(false);
        txfTabelaDestino.setEnabled(false);
        txfOrdem.setEnabled(false);
        chkHabilitado.setEnabled(false);
        txtWhere.setEnabled(false);
        btnSalvar.setEnabled(false);
        btnExcluir.setEnabled(false);

        btnAdicionar.addActionListener(e -> {
            modoTela = ModoTela.INSERT;

            txfId.setText("");
            if(cmbProcesso.getItemCount() > 0) cmbProcesso.setSelectedIndex(0);
            txfTabelaOrigem.setText("");
            txfTabelaDestino.setText("");
            txfOrdem.setText("");
            chkHabilitado.setSelected(true);
            txtWhere.setText("");

            habilitarCampos(true);
            btnSalvar.setEnabled(true);
            btnExcluir.setEnabled(false);
        });

        btnSalvar.addActionListener(e -> {
            try {
                if(dao == null) {
                    JOptionPane.showMessageDialog(this, "Conexão não informada");
                    return;
                }
                if(cmbProcesso.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(this, "Selecione um processo");
                    return;
                }
                if(txfTabelaOrigem.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Informe a tabela de origem");
                    return;
                }
                if(txfTabelaDestino.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Informe a tabela de destino");
                    return;
                }
                if(txfOrdem.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Informe a ordem");
                    return;
                }

                int ordem;
                try {
                    ordem = Integer.parseInt(txfOrdem.getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Informe uma ordem numérica");
                    return;
                }

                TB_REPLICACAO_PROCESSO processo = (TB_REPLICACAO_PROCESSO) cmbProcesso.getSelectedItem();

                TB_REPLICACAO_PROCESSO_TABELA tabela = new TB_REPLICACAO_PROCESSO_TABELA();
                tabela.setProcesso_id(processo.getId());
                tabela.setTabela_origem(txfTabelaOrigem.getText().trim());
                tabela.setTabela_destino(txfTabelaDestino.getText().trim());
                tabela.setOrdem(ordem);
                tabela.setHabilitado(chkHabilitado.isSelected());
                tabela.setDs_where(txtWhere.getText().trim());

                if(modoTela == ModoTela.INSERT) {
                    dao.insert(tabela);
                    JOptionPane.showMessageDialog(this, "Tabela inserida com sucesso");
                } else if(modoTela == ModoTela.UPDATE) {
                    if(txfId.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Informe o ID");
                        return;
                    }
                    tabela.setId(Long.parseLong(txfId.getText().trim()));
                    dao.update(tabela);
                    JOptionPane.showMessageDialog(this, "Tabela atualizada com sucesso");
                } else {
                    JOptionPane.showMessageDialog(this, "Clique em ADICIONAR ou BUSCAR antes de salvar");
                    return;
                }

                modoTela = ModoTela.NENHUM;
                habilitarCampos(false);
                btnSalvar.setEnabled(false);
                btnExcluir.setEnabled(false);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage());
            }
        });

        btnExcluir.addActionListener(e -> {
            try {
                if(dao == null) {
                    JOptionPane.showMessageDialog(this, "Conexão não informada");
                    return;
                }
                if(txfId.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Informe o ID");
                    return;
                }

                int op = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir?", "Excluir", JOptionPane.YES_NO_OPTION);
                if(op != JOptionPane.YES_OPTION) return;

                dao.delete(Long.parseLong(txfId.getText().trim()));
                JOptionPane.showMessageDialog(this, "Tabela excluída com sucesso");

                modoTela = ModoTela.NENHUM;
                limparCampos();
                habilitarCampos(false);
                btnSalvar.setEnabled(false);
                btnExcluir.setEnabled(false);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex.getMessage());
            }
        });

        btnBuscar.addActionListener(e -> {
            try {
                if(dao == null) {
                    JOptionPane.showMessageDialog(this, "Conexão não informada");
                    return;
                }

                ConsultaProcessoTabelaDialog dlg = new ConsultaProcessoTabelaDialog(this, dao);
                dlg.setVisible(true);

                TB_REPLICACAO_PROCESSO_TABELA selecionado = dlg.getSelecionado();
                if(selecionado == null) return;

                modoTela = ModoTela.UPDATE;
                preencherCampos(selecionado);
                habilitarCampos(true);
                btnSalvar.setEnabled(true);
                btnExcluir.setEnabled(true);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao buscar: " + ex.getMessage());
            }
        });
    }

    private void carregarProcessos() throws SQLException {
        cmbProcesso.removeAllItems();
        if(processoDAO == null) return;
        for(TB_REPLICACAO_PROCESSO processo : processoDAO.selectAll()) {
            cmbProcesso.addItem(processo);
        }
    }

    private void habilitarCampos(boolean habilitar) {
        cmbProcesso.setEnabled(habilitar);
        txfTabelaOrigem.setEnabled(habilitar);
        txfTabelaDestino.setEnabled(habilitar);
        txfOrdem.setEnabled(habilitar);
        chkHabilitado.setEnabled(habilitar);
        txtWhere.setEnabled(habilitar);
    }

    private void limparCampos() {
        txfId.setText("");
        txfTabelaOrigem.setText("");
        txfTabelaDestino.setText("");
        txfOrdem.setText("");
        chkHabilitado.setSelected(false);
        txtWhere.setText("");
    }

    private void preencherCampos(TB_REPLICACAO_PROCESSO_TABELA tabela) {
        txfId.setText(String.valueOf(tabela.getId()));
        txfTabelaOrigem.setText(tabela.getTabela_origem());
        txfTabelaDestino.setText(tabela.getTabela_destino());
        txfOrdem.setText(String.valueOf(tabela.getOrdem()));
        chkHabilitado.setSelected(tabela.isHabilitado());
        txtWhere.setText(tabela.getDs_where());

        Long processoId = tabela.getProcesso_id();
        for(int i = 0; i < cmbProcesso.getItemCount(); i++) {
            TB_REPLICACAO_PROCESSO processo = cmbProcesso.getItemAt(i);
            if(processo.getId().equals(processoId)) {
                cmbProcesso.setSelectedIndex(i);
                break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> {
            try {
                new TelaReplicacaoProcessoTabelaView().setVisible(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
