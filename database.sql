
create table bombas(
	id BIGSERIAL PRIMARY KEY,
	identificador VARCHAR(50) NOT NULL,
	tipo_combustivel VARCHAR(50) NOT NULL,
	ativo BOOLEAN NOT NULL DEFAULT TRUE,
	created_at TIMESTAMP NOT NULL DEFAULT NOW(),
	updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

create table clientes(
	id BIGSERIAL PRIMARY KEY,
	nome VARCHAR(150) NOT NULL,
	documento VARCHAR(20) NOT NULL,
	ativo BOOLEAN NOT NULL DEFAULT TRUE,
	created_at TIMESTAMP NOT NULL DEFAULT NOW(),
	updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

create table funcionarios(
	id BIGSERIAL PRIMARY KEY,
	nome VARCHAR(150) NOT NULL,
	cpf VARCHAR(14) NOT NULL UNIQUE,
	ativo BOOLEAN NOT NULL DEFAULT TRUE,
	created_at TIMESTAMP NOT NULL DEFAULT NOW(),
	updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

create table abastecimentos(
	id BIGSERIAL PRIMARY KEY, 
	funcionario_id BIGINT NOT NULL,
	cliente_id BIGINT,
	bomba_id BIGINT NOT NULL,
	litros NUMERIC(10,3) NOT NULL,
	valor_total NUMERIC(10,2) NOT NULL,
	data_hora TIMESTAMP NOT NULL,
	created_at TIMESTAMP NOT NULL DEFAULT NOW(),
	updated_at TIMESTAMP NOT NULL DEFAULT NOW(),

	CONSTRAINT fk_abastecimentos_funcionario
	FOREIGN KEY (funcionario_id)
	REFERENCES funcionarios(id),

	CONSTRAINT fk_abastecimentos_cliente
	FOREIGN KEY (cliente_id)
	REFERENCES clientes(id),

	CONSTRAINT fk_abastecimentos_bomba
	FOREIGN KEY (bomba_id)
	REFERENCES bombas(id)
	
);