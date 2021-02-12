--create database olm_apuntes;

create table usuarios(
id serial primary key not null,
nombres varchar(50) not null,
email varchar(100) not null unique,
password varchar(300) not null,
creado TIMESTAMP not null default NOW()
);

create table cuentas(
id serial primary key not null,
nombre varchar(20) not null,
descripcion varchar(300) not null,
id_usuario int not null,
creado TIMESTAMP not null default NOW(),
actualizado TIMESTAMP not null default NOW(),
activo int not null default 1,
CONSTRAINT cuentas_fk_usuarios FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

create table tipos(
id serial primary key not null,
nombre varchar(20) not null,
signo int not null,
id_usuario int not null,
general int not null default 0,
CONSTRAINT tipos_fk_usuarios FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

--insert into tipos(nombre,signo,general) values('Ingresos',1,1),('Egresos',-1,1);

create table movimientos(
id serial primary key not null,
id_cuenta int not null,
nombre varchar(50) not null,
descripcion varchar(300) not null,
id_tipo int not null,
fecha_hora TIMESTAMP not null,
valor_unitario_ingreso decimal(15,5) not null default 0,
valor_unitario_egreso decimal(15,5) not null default 0,
cantidad int not null,
es_presupuesto int not null,
creado TIMESTAMP not null default NOW(),
actualizado TIMESTAMP not null default NOW(),
activo int not null default 1,
CONSTRAINT movimientos_fk_cuentas FOREIGN KEY (id_cuenta) REFERENCES cuentas(id),
CONSTRAINT movimientos_fk_tipos FOREIGN KEY (id_tipo) REFERENCES tipos(id)
);

create table tipos_usuarios(
id serial primary key not null,
id_tipo int not null,
id_usuario int not null,
creado TIMESTAMP not null default NOW(),
actualizado TIMESTAMP not null default NOW(),
activo int not null default 1,
CONSTRAINT movimientos_tipos_fk_tipos FOREIGN KEY (id_tipo) REFERENCES tipos(id),
CONSTRAINT movimientos_tipos_fk_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

/*
select es_presupuesto,sum(valor_unitario_egreso*cantidad) as total_egresos,sum(valor_unitario_ingreso*cantidad) as total_ingresos,count(*) AS cantidad_total 
from movimientos group by es_presupuesto;

select * from movimientos where activo=1;
select * from tipos_usuarios;

*/
