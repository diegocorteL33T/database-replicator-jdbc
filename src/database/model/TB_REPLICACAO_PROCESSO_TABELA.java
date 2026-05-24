package database.model;

public class TB_REPLICACAO_PROCESSO_TABELA {

    /*

        id BIGSERIAL PRIMARY KEY,
        processo_id BIGINT NOT NULL,
        tabela_origem VARCHAR(150) NOT NULL,
        tabela_destino VARCHAR(150) NOT NULL,
        ordem INTEGER NOT NULL,
        habilitado BOOLEAN DEFAULT TRUE,
        ds_where VARCHAR(500) NOT NULL

	*/

    private Long id;
    private Long processo_id;
    private String tabela_origem;
    private String tabela_destino;
    private Integer ordem;
    private boolean habilitado;
    private String ds_where;

    public TB_REPLICACAO_PROCESSO_TABELA() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProcesso_id() {
        return processo_id;
    }

    public void setProcesso_id(Long processo_id) {
        this.processo_id = processo_id;
    }

    public String getTabela_origem() {
        return tabela_origem;
    }

    public void setTabela_origem(String tabela_origem) {
        this.tabela_origem = tabela_origem;
    }

    public String getTabela_destino() {
        return tabela_destino;
    }

    public void setTabela_destino(String tabela_destino) {
        this.tabela_destino = tabela_destino;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public String getDs_where() {
        return ds_where;
    }

    public void setDs_where(String ds_where) {
        this.ds_where = ds_where;
    }

    @Override
    public String toString() {
        return "TB_REPLICACAO_PROCESSO_TABELA{" +
                "id=" + id +
                ", processo_id=" + processo_id +
                ", tabela_origem='" + tabela_origem + '\'' +
                ", tabela_destino='" + tabela_destino + '\'' +
                ", ordem=" + ordem +
                ", habilitado=" + habilitado +
                ", ds_where='" + ds_where + '\'' +
                '}';
    }
}
