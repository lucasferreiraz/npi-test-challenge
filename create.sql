create table tb_dependente (idade integer, created_at TIMESTAMP WITHOUT TIME ZONE, id bigserial not null, socio_id bigint, updated_at TIMESTAMP WITHOUT TIME ZONE, nome varchar(255), primary key (id));
create table tb_socio (ativo boolean, renda float(53), created_at TIMESTAMP WITHOUT TIME ZONE, id bigserial not null, updated_at TIMESTAMP WITHOUT TIME ZONE, nome varchar(255), primary key (id));
alter table if exists tb_dependente add constraint FKq3lc9sacy5sqfq0uom1gt8cg7 foreign key (socio_id) references tb_socio;

INSERT INTO tb_socio (nome, renda, ativo) VALUES ('João Silva', 1000.0, true);
INSERT INTO tb_socio (nome, renda, ativo) VALUES ('Maria Oliveira', 2000.0, true);
INSERT INTO tb_dependente (nome, idade, socio_id) VALUES ('Pedro Silva', 10, 1);
INSERT INTO tb_dependente (nome, idade, socio_id) VALUES ('Maria Silva', 8, 1);
INSERT INTO tb_dependente (nome, idade, socio_id) VALUES ('Carlos Silva', 12, 1);
INSERT INTO tb_dependente (nome, idade, socio_id) VALUES ('Ana Oliveira', 6, 2);
INSERT INTO tb_dependente (nome, idade, socio_id) VALUES ('Luiza Oliveira', 4, 2);
INSERT INTO tb_dependente (nome, idade, socio_id) VALUES ('Pedro Oliveira', 8, 2);
INSERT INTO tb_socio (nome, renda, ativo) VALUES ('José Santos', 1500.0, true);
INSERT INTO tb_socio (nome, renda, ativo) VALUES ('Ana Souza', 1800.0, true);
INSERT INTO tb_dependente (nome, idade, socio_id) VALUES ('Mariana Santos', 7, 3);
INSERT INTO tb_dependente (nome, idade, socio_id) VALUES ('Lucas Santos', 5, 3);
INSERT INTO tb_dependente (nome, idade, socio_id) VALUES ('Lara Souza', 10, 4);
INSERT INTO tb_dependente (nome, idade, socio_id) VALUES ('Gabriel Souza', 9, 4);
INSERT INTO tb_dependente (nome, idade, socio_id) VALUES ('Rafael Souza', 6, 4);
INSERT INTO tb_socio (nome, renda, ativo) VALUES ('Paula Costa', 2500.0, true);
INSERT INTO tb_socio (nome, renda, ativo) VALUES ('Fernando Lima', 3000.0, true);
INSERT INTO tb_dependente (nome, idade, socio_id) VALUES ('Luciana Costa', 12, 5);
INSERT INTO tb_dependente (nome, idade, socio_id) VALUES ('Miguel Costa', 10, 5);
INSERT INTO tb_dependente (nome, idade, socio_id) VALUES ('Larissa Lima', 8, 6);
INSERT INTO tb_dependente (nome, idade, socio_id) VALUES ('Gustavo Lima', 6, 6);

