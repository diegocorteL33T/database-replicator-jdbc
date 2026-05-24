package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrigemDAO {

    private Connection conn;

    private PreparedStatement pstSelect;

    public OrigemDAO(Connection conn) {
        this.conn = conn;
    }

    public ResultSet SelectComandoOrigem(final String tabela, String where) throws SQLException {
        pstSelect = conn.prepareStatement("SELECT * FROM " +tabela+ "a WHERE " + where);
        System.out.println("Select na Origem: "+pstSelect);
        return pstSelect.executeQuery();
    }

}
