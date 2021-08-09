create table cliente(
    id_cliente serial primary key,
    nm_cliente varchar(40) not null,
    nr_documento varchar(11) not null,
    dt_nascimento date not null
);

create table produto(
    id_produto serial primary key,
    nm_produto varchar(80) not null,
    desc_produto varchar(40) not null,
    vl_produto numeric(5,2) not null,
    nr_qtdeestoque numeric(9,2) not null
);

create table pedido(
    id_pedido serial primary key,
    id_cliente int references cliente(id_cliente),
    dt_pedido date not null,
    vl_totalpedido numeric(9,2) not null
);

create table item_pedido(
    id_itempedido serial primary key,
    id_pedido int not null references pedido(id_pedido),
    id_produto int not null references produto(id_produto),
    nr_quantidade numeric(9,2) not null,
    vl_totalitem numeric(9,2) not null
);

