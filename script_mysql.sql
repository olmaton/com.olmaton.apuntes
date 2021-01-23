drop database if exists olm_apuntes;
create database olm_apuntes;
use olm_apuntes;

create table usuarios(
id int primary key not null auto_increment,
nombres varchar(50) not null,
email varchar(100) not null unique,
password varchar(300) not null,
creado datetime not null default current_timestamp
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

create table cuentas(
id int primary key not null auto_increment,
nombre varchar(20) not null,
descripcion varchar(300) not null default "",
id_usuario int not null,
creado datetime not null default current_timestamp,
actualizado datetime not null default current_timestamp,
activo TINYINT not null default 1 comment '0:Inactivo,1:Activo',
CONSTRAINT cuentas_fk_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

create table tipos(
id int primary key not null auto_increment,
nombre varchar(20) not null,
signo int not null,
activo TINYINT not null default 1 comment '0:Inactivo,1:Activo'
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

insert into tipos(nombre,signo) values('Ingresos',1),('Egresos',-1);

create table movimientos(
id int primary key not null auto_increment,
id_cuenta int not null,
nombre varchar(50) not null,
descripcion varchar(300) not null,
id_tipo int not null,
fecha_hora datetime not null,
ingresos decimal(15,5) not null,
salidas decimal(15,5) not null,
cantidad int not null,
creado datetime not null default current_timestamp,
actualizado datetime not null default current_timestamp,
activo TINYINT not null default 1 comment '0:Inactivo,1:Activo',
CONSTRAINT movimientos_fk_cuentas FOREIGN KEY (id_cuenta) REFERENCES cuentas(id),
CONSTRAINT movimientos_fk_tipos FOREIGN KEY (id_tipo) REFERENCES tipos(id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;
