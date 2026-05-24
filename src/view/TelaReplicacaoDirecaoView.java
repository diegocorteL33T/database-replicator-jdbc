package view;

import database.model.TB_REPLICACAO_DIRECAO;

import javax.swing.*;
import java.awt.*;

public class TelaReplicacaoDirecaoView extends JFrame{

    private JTextField txfId;
    private JComboBox<TB_REPLICACAO_DIRECAO> cmbProcesso;

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

    public TelaReplicacaoDirecaoView(){
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


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> new TelaReplicacaoDirecaoView().setVisible(true));
    }

}
