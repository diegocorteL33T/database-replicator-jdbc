# Database Replicator JDBC

Software replicador de bancos de dados desenvolvido em Java Swing e JDBC para um desafio tecnico. A aplicacao copia dados de um banco de origem para um ou mais bancos de destino com base nas regras, conexoes e tabelas configuradas em um banco de controle.

## Objetivo

Centralizar a configuracao de processos de replicacao em tabelas de controle e executar a copia dos dados entre bancos PostgreSQL, permitindo definir:

- processos de replicacao;
- tabelas de origem e destino;
- ordem de processamento;
- filtros por tabela;
- direcoes de origem e destino habilitadas.

## Tecnologias

- Java
- Swing/JFrame
- JDBC
- PostgreSQL

## Estrutura

- `src/database/model`: entidades das tabelas de controle.
- `src/database/dao`: acesso aos dados via JDBC.
- `src/view`: telas Swing para cadastro e gerenciamento das configuracoes.
- `controle.sql`: script do banco de controle.
- `database.sql`: script de exemplo para banco operacional.

## Descricao do desafio

O sistema atua como um replicador de banco de dados responsavel por copiar informacoes de um banco de origem para um ou mais bancos de destino. As regras de replicacao ficam armazenadas em um banco de controle, que define quais processos estao habilitados, quais tabelas serao copiadas, em qual ordem e para quais destinos.
