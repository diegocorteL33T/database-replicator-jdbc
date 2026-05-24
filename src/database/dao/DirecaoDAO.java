package database.dao;

import database.model.TB_REPLICACAO_DIRECAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DirecaoDAO {

    private Connection conn;

    private static final String SQL_SELECT_ALL =
            "SELECT * FROM TB_REPLICACAO_DIRECAO";

    private static final String SQL_SELECT_BY_ID =
            "SELECT * FROM TB_REPLICACAO_DIRECAO WHERE ID = ?";

    private static final String SQL_SELECT_BY_PROCESSO_HABILITADO =
            "SELECT * FROM TB_REPLICACAO_DIRECAO WHERE PROCESSO_ID = ? AND HABILITADO = TRUE";

    private static final String SQL_INSERT =
            "INSERT INTO TB_REPLICACAO_DIRECAO (DIRECAO_ORIGEM, DIRECAO_DESTINO, USUARIO_ORIGEM, USUARIO_DESTINO, SENHA_ORIGEM, SENHA_DESTINO, HABILITADO, PROCESSO_ID) VALUES (?,?,?,?,?,?,?,?)";

    private static final String SQL_UPDATE =
            "UPDATE TB_REPLICACAO_DIRECAO SET DIRECAO_ORIGEM = ?, DIRECAO_DESTINO = ?, USUARIO_ORIGEM = ?, USUARIO_DESTINO = ?, SENHA_ORIGEM = ?, SENHA_DESTINO = ?, HABILITADO = ?, PROCESSO_ID = ? WHERE ID = ?";

    private static final String SQL_DELETE =
            "DELETE FROM TB_REPLICACAO_DIRECAO WHERE ID = ?";

    private PreparedStatement pstSelectAll;
    private PreparedStatement pstSelectById;
    private PreparedStatement pstSelectByProcessoHabilitado;
    private PreparedStatement pstInsert;
    private PreparedStatement pstUpdate;
    private PreparedStatement pstDelete;

    public DirecaoDAO(Connection conn) throws SQLException {
        this.conn = conn;
        this.pstSelectAll = conn.prepareStatement(SQL_SELECT_ALL);
        this.pstSelectById = conn.prepareStatement(SQL_SELECT_BY_ID);
        this.pstSelectByProcessoHabilitado = conn.prepareStatement(SQL_SELECT_BY_PROCESSO_HABILITADO);
        this.pstInsert = conn.prepareStatement(SQL_INSERT);
        this.pstUpdate = conn.prepareStatement(SQL_UPDATE);
        this.pstDelete = conn.prepareStatement(SQL_DELETE);
    }

    public ArrayList<TB_REPLICACAO_DIRECAO> selectAll() throws SQLException {
        ArrayList<TB_REPLICACAO_DIRECAO> list = new ArrayList<>();
        try (ResultSet resultSet = pstSelectAll.executeQuery()) {
            while (resultSet.next()) {
                list.add(map(resultSet));
            }
        }
        return list;
    }

    public TB_REPLICACAO_DIRECAO selectById(Long id) throws SQLException {
        pstSelectById.setLong(1, id);
        try (ResultSet resultSet = pstSelectById.executeQuery()) {
            return resultSet.next() ? map(resultSet) : null;
        }
    }

    public ArrayList<TB_REPLICACAO_DIRECAO> selectByProcessoHabilitado(Long processoId) throws SQLException {
        ArrayList<TB_REPLICACAO_DIRECAO> list = new ArrayList<>();
        pstSelectByProcessoHabilitado.setLong(1, processoId);
        try (ResultSet resultSet = pstSelectByProcessoHabilitado.executeQuery()) {
            while (resultSet.next()) {
                list.add(map(resultSet));
            }
        }
        return list;
    }

    public void insert(TB_REPLICACAO_DIRECAO tb) throws SQLException {
        pstInsert.setString(1, tb.getDirecao_origem());
        pstInsert.setString(2, tb.getDirecao_destino());
        pstInsert.setString(3, tb.getUsuario_origem());
        pstInsert.setString(4, tb.getUsuario_destino());
        pstInsert.setString(5, tb.getSenha_origem());
        pstInsert.setString(6, tb.getSenha_destino());
        pstInsert.setBoolean(7, tb.isHabilitado());
        pstInsert.setLong(8, tb.getProcesso_id());
        pstInsert.executeUpdate();
    }

    public void update(TB_REPLICACAO_DIRECAO tb) throws SQLException {
        pstUpdate.setString(1, tb.getDirecao_origem());
        pstUpdate.setString(2, tb.getDirecao_destino());
        pstUpdate.setString(3, tb.getUsuario_origem());
        pstUpdate.setString(4, tb.getUsuario_destino());
        pstUpdate.setString(5, tb.getSenha_origem());
        pstUpdate.setString(6, tb.getSenha_destino());
        pstUpdate.setBoolean(7, tb.isHabilitado());
        pstUpdate.setLong(8, tb.getProcesso_id());
        pstUpdate.setLong(9, tb.getId());
        pstUpdate.executeUpdate();
    }

    public void delete(Long id) throws SQLException {
        pstDelete.setLong(1, id);
        pstDelete.executeUpdate();
    }

    private TB_REPLICACAO_DIRECAO map(ResultSet resultSet) throws SQLException {
        TB_REPLICACAO_DIRECAO tb = new TB_REPLICACAO_DIRECAO();
        tb.setId(resultSet.getLong("ID"));
        tb.setDirecao_origem(resultSet.getString("DIRECAO_ORIGEM"));
        tb.setDirecao_destino(resultSet.getString("DIRECAO_DESTINO"));
        tb.setUsuario_origem(resultSet.getString("USUARIO_ORIGEM"));
        tb.setUsuario_destino(resultSet.getString("USUARIO_DESTINO"));
        tb.setSenha_origem(resultSet.getString("SENHA_ORIGEM"));
        tb.setSenha_destino(resultSet.getString("SENHA_DESTINO"));
        tb.setHabilitado(resultSet.getBoolean("HABILITADO"));
        tb.setProcesso_id(resultSet.getLong("PROCESSO_ID"));
        return tb;
    }

}
