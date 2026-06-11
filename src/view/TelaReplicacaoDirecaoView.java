package view;

import database.dao.DirecaoDAO;
import database.model.TB_REPLICACAO_DIRECAO;
import database.model.TB_REPLICACAO_PROCESSO;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class TelaReplicacaoDirecaoView extends JFrame{

    private enum ModoTela{NENHUM, INSERT, UPDATE}
    private TelaReplicacaoDirecaoView.ModoTela modoTela = TelaReplicacaoDirecaoView.ModoTela.NENHUM;

    private final Connection conn;
    private final DirecaoDAO dao;

    private JTextField txfId;
    private JComboBox<TB_REPLICACAO_PROCESSO> cmbProcesso;

    private JTextField txfOrigem;
    private JTextField txfDestino;
    private JTextField txfUsuarioOrigem;
    private JTextField txfUsuarioDestino;
    private JTextField txfSenhaOrigem;
    private JTextField txfSenhaDestino;
    private JCheckBox chkHabilitado;

    private JButton btnSalvar;
    private JButton btnAdicionar;
    private JButton btnBuscar;
    private JButton btnExcluir;

    public TelaReplicacaoDirecaoView() throws SQLException {
        this(null, null);
    }

    public TelaReplicacaoDirecaoView(Connection conn) throws SQLException {
        this(conn, null);
    }

    public TelaReplicacaoDirecaoView(Connection conn, DirecaoDAO dao) throws SQLException {
        this.conn = conn;
        this.dao = dao != null ? dao : (conn != null ? new DirecaoDAO(conn) : null);
        setTitle("Cadastro de direcoes");
        setSize(760, 500);
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

        JLabel lblOrigem = new JLabel("ORIGEM: ");
        lblOrigem.setBounds(10,140,140,25);
        lblOrigem.setFont(lblOrigem.getFont().deriveFont(Font.BOLD));
        getContentPane().add(lblOrigem);

        JLabel lblDirecaoOrigem = new JLabel("DIRECAO ORIGEM: ");
        lblDirecaoOrigem.setBounds(10,175,140,25);
        getContentPane().add(lblDirecaoOrigem);

        txfOrigem = new JTextField();
        txfOrigem.setBounds(160,175,560,25);
        getContentPane().add(txfOrigem);

        JLabel lblUsuarioOrigem = new JLabel("USUARIO ORIGEM: ");
        lblUsuarioOrigem.setBounds(10,210,140,25);
        getContentPane().add(lblUsuarioOrigem);

        txfUsuarioOrigem = new JTextField();
        txfUsuarioOrigem.setBounds(160,210,280,25);
        getContentPane().add(txfUsuarioOrigem);

        JLabel lblSenhaOrigem = new JLabel("SENHA ORIGEM: ");
        lblSenhaOrigem.setBounds(460,210,140,25);
        getContentPane().add(lblSenhaOrigem);

        txfSenhaOrigem = new JTextField();
        txfSenhaOrigem.setBounds(570,210,150,25);
        getContentPane().add(txfSenhaOrigem);

        JLabel lblDestino = new JLabel("DESTINO: ");
        lblDestino.setBounds(10,265,200,25);
        lblDestino.setFont(lblDestino.getFont().deriveFont(Font.BOLD));
        getContentPane().add(lblDestino);

        JLabel lblDirecaoDestino = new JLabel("DIRECAO DESTINO: ");
        lblDirecaoDestino.setBounds(10,300,140,25);
        getContentPane().add(lblDirecaoDestino);

        txfDestino = new JTextField();
        txfDestino.setBounds(160,300,560,25);
        getContentPane().add(txfDestino);

        JLabel lblUsuarioDestino = new JLabel("USUARIO DESTINO: ");
        lblUsuarioDestino.setBounds(10,335,140,25);
        getContentPane().add(lblUsuarioDestino);

        txfUsuarioDestino = new JTextField();
        txfUsuarioDestino.setBounds(160,335,280,25);
        getContentPane().add(txfUsuarioDestino);

        JLabel lblSenhaDestino = new JLabel("SENHA DESTINO: ");
        lblSenhaDestino.setBounds(460,335,150,25);
        getContentPane().add(lblSenhaDestino);

        txfSenhaDestino = new JTextField();
        txfSenhaDestino.setBounds(570,335,150,25);
        getContentPane().add(txfSenhaDestino);

        chkHabilitado = new JCheckBox("HABILITADO");
        chkHabilitado.setBounds(10,390,140,25);
        getContentPane().add(chkHabilitado);


        txfId.setEnabled(false);
        cmbProcesso.setEnabled(false);
        chkHabilitado.setEnabled(false);
        txfOrigem.setEnabled(false);
        txfUsuarioOrigem.setEnabled(false);
        txfSenhaOrigem.setEnabled(false);
        txfDestino.setEnabled(false);
        txfUsuarioDestino.setEnabled(false);
        txfSenhaDestino.setEnabled(false);
        btnSalvar.setEnabled(false);
        btnExcluir.setEnabled(false);

        btnAdicionar.addActionListener(e -> {

            modoTela = ModoTela.INSERT;

            txfOrigem.setText("");

            if(cmbProcesso.getItemCount() > 0) cmbProcesso.setSelectedIndex(0);
            chkHabilitado.setSelected(true);

            txfOrigem.setText("");
            txfUsuarioOrigem.setText("");
            txfSenhaOrigem.setText("");
            txfDestino.setText("");
            txfUsuarioDestino.setText("");
            txfSenhaDestino.setText("");

            cmbProcesso.setEnabled(true);
            chkHabilitado.setEnabled(true);
            btnSalvar.setEnabled(true);
            txfOrigem.setEnabled(true);
            txfUsuarioOrigem.setEnabled(true);
            txfSenhaOrigem.setEnabled(true);
            txfDestino.setEnabled(true);
            txfUsuarioDestino.setEnabled(true);
            txfSenhaDestino.setEnabled(true);

        });

        btnSalvar.addActionListener(e -> {
            try {
                if(cmbProcesso.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "Selecione um processo");
                    return;
                }

                if(txfOrigem.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this,"Informe a origem");
                    return;
                }
                if(txfDestino.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this,"Informe a destino");
                    return;
                }
                if(txfSenhaOrigem.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this,"Informe a senha de origem");
                    return;
                }
                if(txfSenhaDestino.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this,"Informe a senha de destino");
                    return;
                }
                if(txfUsuarioOrigem.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this,"Informe o usuario de origem");
                    return;
                }
                if(txfUsuarioDestino.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this,"Informe o usuario de destino");
                    return;
                }

                TB_REPLICACAO_PROCESSO pSel = (TB_REPLICACAO_PROCESSO) cmbProcesso.getSelectedItem();

                TB_REPLICACAO_DIRECAO d = new TB_REPLICACAO_DIRECAO();
                d.setProcesso_id(pSel.getId());
                d.setHabilitado(chkHabilitado.isSelected());
                d.setDirecao_origem(txfOrigem.getText());
                d.setDirecao_destino(txfDestino.getText());
                d.setUsuario_origem(txfUsuarioOrigem.getText());
                d.setUsuario_destino(txfUsuarioDestino.getText());
                d.setSenha_origem(txfSenhaOrigem.getText());
                d.setSenha_destino(txfSenhaDestino.getText());

                if (modoTela == ModoTela.INSERT) {
                    dao.insert(d);
                    JOptionPane.showMessageDialog(this, "Direção inserida com sucesso");
                } else if (modoTela == ModoTela.UPDATE) {
                    if (txfId.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Id não preenchido");
                        return;
                    }
                    d.setId((long) Integer.parseInt(txfId.getText()));
                    dao.update(d);
                    JOptionPane.showMessageDialog(this,"Direção atualizada com sucesso");
                } else {
                    JOptionPane.showMessageDialog(this,"Clique em ADICIONAR ou BUSCAR antes de salvar");
                }

                modoTela = ModoTela.NENHUM;
                txfId.setEnabled(false);
                cmbProcesso.setEnabled(false);
                chkHabilitado.setEnabled(false);
                txfOrigem.setEnabled(false);
                txfUsuarioOrigem.setEnabled(false);
                txfSenhaOrigem.setEnabled(false);
                txfDestino.setEnabled(false);
                txfUsuarioDestino.setEnabled(false);
                txfSenhaDestino.setEnabled(false);
                btnSalvar.setEnabled(false);
                btnExcluir.setEnabled(false);


            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao salvar:" +ex.getMessage());
            }
        });

        btnExcluir.addActionListener(e -> {
            try {
                if (txfId.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this,"Insira o ID para excluir");
                    return;
                }
                int op  = JOptionPane.showConfirmDialog(this,"Deseja realmente excluir?");
                if (op != JOptionPane.YES_OPTION) return;
                dao.delete(Long.parseLong(txfId.getText()));

                JOptionPane.showMessageDialog(this,"Direção excluida com sucesso");

                txfOrigem.setText("");
                txfUsuarioOrigem.setText("");
                txfSenhaOrigem.setText("");
                txfDestino.setText("");
                txfUsuarioDestino.setText("");
                txfSenhaDestino.setText("");

                cmbProcesso.setEnabled(false);
                chkHabilitado.setEnabled(false);
                btnSalvar.setEnabled(false);
                btnExcluir.setEnabled(false);
                txfOrigem.setEnabled(false);
                txfUsuarioOrigem.setEnabled(false);
                txfSenhaOrigem.setEnabled(false);
                txfDestino.setEnabled(false);
                txfUsuarioDestino.setEnabled(false);
                txfSenhaDestino.setEnabled(false);


            }   catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao excluir:" +ex.getMessage());
            }

        });

        btnBuscar.addActionListener(e -> {
            try {

            ConsultaDirecaoDialog cdd = new ConsultaDirecaoDialog(this,dao);
            cdd.setVisible(true);

            TB_REPLICACAO_DIRECAO d = new TB_REPLICACAO_DIRECAO();

            if (d == null) return;

            modoTela = ModoTela.UPDATE;


            txfId.setText(String.valueOf(d.getId()));
            chkHabilitado.setSelected(d.isHabilitado());
            txfUsuarioOrigem.setText(d.getUsuario_origem());
            txfUsuarioDestino.setText(d.getUsuario_destino());
            txfSenhaOrigem.setText(d.getSenha_origem());
            txfSenhaDestino.setText(d.getSenha_destino());
            txfOrigem.setText(d.getUsuario_origem());
            txfDestino.setText(d.getUsuario_destino());

            Long id = (Long) d.getProcesso_id();
            for (int i = 0; i < cmbProcesso.getItemCount(); i++) {
                TB_REPLICACAO_PROCESSO p = cmbProcesso.getItemAt(i);
                if (p.getId() == id) {
                    cmbProcesso.setSelectedIndex(i);
                    break;
                }
            }

            cmbProcesso.setEnabled(true);
            chkHabilitado.setEnabled(true);
            txfOrigem.setEnabled(true);
            txfUsuarioOrigem.setEnabled(true);
            txfSenhaOrigem.setEnabled(true);
            txfDestino.setEnabled(true);
            txfUsuarioDestino.setEnabled(true);
            txfSenhaDestino.setEnabled(true);
            btnSalvar.setEnabled(true);
            btnExcluir.setEnabled(true);


            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao buscar:" +ex.getMessage());
            }
        });

        btnSalvar.addActionListener(e -> {

        });

    }

}
