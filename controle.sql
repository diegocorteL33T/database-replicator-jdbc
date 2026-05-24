CREATE TABLE tb_replicacao_processo(
	id BIGSERIAL PRIMARY KEY,
	processo VARCHAR(100) NOT NULL,
	descricao VARCHAR(300),
	habilidado BOOLEAN DEFAULT TRUE
);

CREATE TABLE tb_replicacao_processo_tabela(
	id BIGSERIAL PRIMARY KEY,
	processo_id BIGINT NOT NULL,
	tabela_origem VARCHAR(150) NOT NULL,
	tabela_destino VARCHAR(150) NOT NULL,
	ordem INTEGER NOT NULL,
	habilitado BOOLEAN DEFAULT TRUE,
	ds_where VARCHAR(500) NOT NULL
);

CREATE TABLE tb_replicacao_direcao(
	id BIGSERIAL PRIMARY KEY,
	direcao_origem VARCHAR(150) NOT NULL,
	direcao_destino VARCHAR(150) NOT NULL,
	usuario_origem VARCHAR(45) NOT NULL,
	usuario_destino VARCHAR(45) NOT NULL,
	senha_origem VARCHAR(45) NOT NULL,
	senha_destino VARCHAR(45) NOT NULL,
	habilitado BOOLEAN DEFAULT TRUE,
	processo_id BIGINT NOT NULL
);

ALTER TABLE tb_replicacao_direcao
	ADD CONSTRAINT tb_replicacao_direcao_fk
		FOREIGN KEY (processo_id) REFERENCES tb_replicacao_processo(id);

ALTER TABLE tb_replicacao_processo_tabela
	ADD CONSTRAINT tb_replicacao_processo_tabela_fk
		FOREIGN KEY (processo_id) REFERENCES tb_replicacao_processo(id);

