package database.dao;

import database.model.TB_REPLICACAO_PROCESSO_TABELA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProcessoTabelaDAO {

    private Connection conn;

    private static final String SQL_SELECT_BY_PROCESSO_HABILITADO =
            "SELECT * FROM TB_REPLICACAO_PROCESSO_TABELA WHERE PROCESSO_ID = ? AND HABILITADO = TRUE ORDER BY ORDEM";

    private static final String SQL_SELECT_ALL =
            "SELECT * FROM TB_REPLICACAO_PROCESSO_TABELA";

    private static final String SQL_SELECT_BY_ID =
            "SELECT * FROM TB_REPLICACAO_PROCESSO_TABELA WHERE ID = ?";

    private static final String SQL_INSERT =
            "INSERT INTO TB_REPLICACAO_PROCESSO_TABELA (PROCESSO_ID, TABELA_ORIGEM, TABELA_DESTINO, ORDEM, HABILITADO, DS_WHERE) VALUES (?,?,?,?,?,?)";

    private static final String SQL_UPDATE =
            "UPDATE TB_REPLICACAO_PROCESSO_TABELA SET PROCESSO_ID = ?, TABELA_ORIGEM = ?, TABELA_DESTINO = ? ORDEM = ?, HABILITADO = ?, DS_WHERE = ?";

    private static final String SQL_DELETE =
            "DELETE FROM TB_REPLICACAO_PROCESSO_TABELA WHERE ID = ?";

    private PreparedStatement pstSelectByProcessoHabilitado;
    private PreparedStatement pstSelectAll;
    private PreparedStatement pstSelectById;
    private PreparedStatement pstInsert;
    private PreparedStatement pstUpdate;
    private PreparedStatement pstDelete;

    public ProcessoTabelaDAO(Connection conn) throws SQLException {
        this.conn = conn;
        this.pstSelectByProcessoHabilitado = conn.prepareStatement(SQL_SELECT_BY_PROCESSO_HABILITADO);
        this.pstSelectAll = conn.prepareStatement(SQL_SELECT_ALL);
        this.pstSelectById = conn.prepareStatement(SQL_SELECT_BY_ID);
        this.pstInsert = conn.prepareStatement(SQL_INSERT);
        this.pstUpdate = conn.prepareStatement(SQL_UPDATE);
        this.pstDelete = conn.prepareStatement(SQL_DELETE);
    }

    public ArrayList<TB_REPLICACAO_PROCESSO_TABELA> selectProcessoHabilitado (Long processoId) throws SQLException {
        ArrayList list = new ArrayList<>();
        pstSelectByProcessoHabilitado.setLong(1,processoId);
        try(ResultSet resultSet = pstSelectByProcessoHabilitado.executeQuery()) {
            while(resultSet.next()){
                list.add(map(resultSet));
            }

        }
        return list;
    }

    public ArrayList selectAll() throws SQLException {
         ArrayList<TB_REPLICACAO_PROCESSO_TABELA> list = new ArrayList<>();
         try(ResultSet resultSet = pstSelectAll.executeQuery()){
             while(resultSet.next()){
                 list.add(map(resultSet));
             }
         }
         return list;
    }

    public TB_REPLICACAO_PROCESSO_TABELA selectById(Long id) throws SQLException {
        pstSelectById.setLong(1,id);
        try(ResultSet resultSet = pstSelectById.executeQuery()){
            return resultSet.next() ? map(resultSet) : null;
        }
    }

     public void insert(TB_REPLICACAO_PROCESSO_TABELA tb) throws SQLException {
        pstInsert.setLong(1,tb.getProcesso_id());
        pstInsert.setString(2,tb.getTabela_origem());
        pstInsert.setString(3,tb.getTabela_destino());
        pstInsert.setInt(4,tb.getOrdem());
        pstInsert.setBoolean(5, tb.isHabilitado());
        pstInsert.setString(6, tb.getDs_where());
        pstInsert.executeUpdate();
     }

     public void update(TB_REPLICACAO_PROCESSO_TABELA tb) throws  SQLException {
         pstUpdate.setLong(1,tb.getProcesso_id());
         pstUpdate.setString(2,tb.getTabela_origem());
         pstUpdate.setString(3,tb.getTabela_destino());
         pstUpdate.setInt(4,tb.getOrdem());
         pstUpdate.setBoolean(5, tb.isHabilitado());
         pstUpdate.setString(6, tb.getDs_where());
         pstUpdate.setLong(7,tb.getId());
         pstUpdate.executeUpdate();
     }

     public void delete(Long id) throws SQLException {
        pstDelete.setLong(1,id);
        pstDelete.executeUpdate();
     }

    private TB_REPLICACAO_PROCESSO_TABELA map(ResultSet resultSet)throws SQLException {
        TB_REPLICACAO_PROCESSO_TABELA tb = new TB_REPLICACAO_PROCESSO_TABELA();
        tb.setId(resultSet.getLong("ID"));
        tb.setProcesso_id(resultSet.getLong("PROCESSO_ID"));
        tb.setTabela_origem(resultSet.getString("TABELA_ORIGEM"));
        tb.setTabela_destino(resultSet.getString("TABELA_DESTINO"));
        tb.setOrdem(resultSet.getInt("ORDEM"));
        tb.setHabilitado(resultSet.getBoolean("HABILITADO"));
        tb.setDs_where(resultSet.getString("DS_WHERE"));
        return tb;
    }

}
