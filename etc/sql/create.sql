drop table if exists cliente cascade;
drop table if exists email_cliente cascade;
drop table if exists carteira cascade;
drop table if exists dependente cascade;
drop table if exists pedido cascade;
drop table if exists produto cascade;
drop table if exists fornecedor cascade;
drop table if exists pedido_produto cascade;
drop table if exists produto_fornecedor cascade;

create table cliente(
id_cliente serial,
nome varchar(255) not null,
end_cep char(8),
end_rua varchar(255),
end_cidade varchar(255),
rg varchar(50),
cpf char(11),
cnpj varchar(50),
insc_estadual varchar(50),
tipo varchar(20),
constraint cliente_pk primary key (id_cliente),
constraint cliente_tipo_ck check(tipo in ('fisico', 'juridico'))
);

create table email_cliente(
email varchar(255),
id_cliente integer,
constraint email_cliente_pk primary key (email, id_cliente),
constraint email_cliente_id_fk foreign key (id_cliente)
	references cliente(id_cliente)
	on update cascade
	on delete cascade
);

create table carteira(
id_carteira serial,
saldo double precision not null default 0.0,
pontos integer not null default 0,
id_cliente integer not null,
constraint carteira_pk primary key (id_carteira),
constraint carteira_id_cliente_fk foreign key (id_cliente)
	references cliente(id_cliente)
	on update cascade
	on delete no action
);

create table dependente(
id_cliente integer,
rg varchar(50),
nome varchar(255) not null,
constraint dependente_pk primary key (id_cliente, rg),
constraint dependente_id_cliente_fk foreign key (id_cliente)
	references cliente(id_cliente)
	on update cascade
	on delete cascade
);

create table pedido(
id_pedido serial,
valor_total double precision,
data date not null,
end_rua varchar(255) not null,
end_cidade varchar(255) not null,
end_cep char(8) not null,
id_cliente integer not null,
constraint pedido_pk primary key (id_pedido),
constraint pedido_id_cliente_fk foreign key (id_cliente)
	references cliente(id_cliente)
	on update cascade
	on delete cascade
);

create table produto(
id_produto serial,
nome varchar(255),
descricao varchar(255),
valor double precision not null,
constraint produto_pk primary key (id_produto)
);

create table fornecedor(
id_fornecedor serial,
nome varchar(255) not null,
constraint fornecedor_pk primary key (id_fornecedor)
);

create table pedido_produto(
id_pedido integer,
id_produto integer,
valor_unidade double precision not null,
qtd integer not null default 1,
constraint pedido_produto_pk primary key (id_pedido, id_produto),
constraint pedido_produto_id_pedido_fk foreign key (id_pedido)
	references pedido(id_pedido)
	on update cascade
	on delete cascade,
constraint pedido_produto_id_produto_fk foreign key (id_produto)
	references produto(id_produto)
	on update cascade
	on delete cascade
);

create table produto_fornecedor(
id_fornecedor integer,
id_produto integer,
constraint produto_fornecedor_pk primary key(id_fornecedor, id_produto),
constraint produto_fornecedor_id_produto_fk foreign key (id_produto)
	references produto(id_produto)
	on update cascade
	on delete cascade,
constraint produto_fornecedor_id_fornecedor_fk foreign key (id_fornecedor)
	references fornecedor(id_fornecedor)
	on update cascade
	on delete cascade
);