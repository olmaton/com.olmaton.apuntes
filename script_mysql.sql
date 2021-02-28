drop database if exists olm_apuntes;
create database olm_apuntes;
use olm_apuntes;

create table usuarios(
id int primary key not null auto_increment,
nombres varchar(50) not null,
email varchar(100) not null unique,
password varchar(300) not null,
intentos_error int not null default 0,
activo TINYINT not null default 1 comment '0:Inactivo,1:Activo',
creado datetime not null default current_timestamp
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

create table contactos(
id int primary key not null auto_increment,
id_usuario int not null,
id_usuario_amigo int not null,
tipo TINYINT not null default 0 comment '0:En proceso,1:Aceptado,2:Rechazado,3:Anulado',
id_usuario_responsable int,
creado datetime not null default current_timestamp,
actualizado datetime not null default current_timestamp,
activo TINYINT not null default 1 comment '0:Inactivo,1:Activo',
CONSTRAINT contactos_fk_usuarios FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
CONSTRAINT contactos_fk_usuarios_amigo FOREIGN KEY (id_usuario_amigo) REFERENCES usuarios(id),
CONSTRAINT contactos_fk_usuarios_responsable FOREIGN KEY (id_usuario_responsable) REFERENCES usuarios(id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

create table usuarios_sesion(
id serial primary key,
id_usuario int not null,
token text not null,
token_refresh text not null,
origen varchar(50) not null,
origen_codigo varchar(300) not null,
origen_ip varchar(50) not null,
origen_descripcion varchar(500) not null,
fecha_ultimo_acceso datetime not null,
fecha_registro datetime not null default current_timestamp,
fecha_cierre datetime,
activo boolean not null default true,
CONSTRAINT usuarios_sesion_fk_usuarios FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

create table cuentas(
id int primary key not null auto_increment,
nombre varchar(20) not null,
descripcion varchar(300) not null default "",
creado datetime not null default current_timestamp,
actualizado datetime not null default current_timestamp,
activo TINYINT not null default 1 comment '0:Inactivo,1:Activo'
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

create table cuentas_usuarios(
id int primary key not null auto_increment,
id_usuario int not null,
id_cuenta int not null,
tipo TINYINT not null default 1 comment '0:En proceso,1:Propietario,2:Compartido',
activo TINYINT not null default 1 comment '0:Inactivo,1:Activo',
CONSTRAINT cuentas_usuarios_fk_usuarioss FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
CONSTRAINT cuentas_usuarios_fk_cuentas FOREIGN KEY (id_cuenta) REFERENCES cuentas(id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

create table tipos(
id int primary key not null auto_increment,
nombre varchar(20) not null,
signo int not null comment '1:Ingreso,-1:Egreso',
id_usuario int not null,
activo TINYINT not null default 1 comment '0:Inactivo,1:Activo',
CONSTRAINT tipos_fk_usuarios FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

create table tipos_usuarios(
id int primary key not null auto_increment,
id_usuario int not null,
id_tipo int not null,
creado datetime not null default current_timestamp,
actualizado datetime not null default current_timestamp,
activo TINYINT not null default 1 comment '0:Inactivo,1:Activo',
CONSTRAINT tipos_usuarios_fk_usuarios FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
CONSTRAINT tipos_usuarios_fk_tipos FOREIGN KEY (id_tipo) REFERENCES tipos(id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

create table movimientos(
id int primary key not null auto_increment,
id_cuenta int not null,
nombre varchar(50) not null,
descripcion varchar(300) not null,
id_tipo int not null,
fecha_hora datetime not null,
valor_unitario_ingreso decimal(15,5) not null default 0,
valor_unitario_egreso decimal(15,5) not null default 0,
cantidad int not null,
es_presupuesto int not null,
creado datetime not null default current_timestamp,
actualizado datetime not null default current_timestamp,
activo TINYINT not null default 1 comment '0:Inactivo,1:Activo',
CONSTRAINT movimientos_fk_cuentas FOREIGN KEY (id_cuenta) REFERENCES cuentas(id),
CONSTRAINT movimientos_fk_tipos FOREIGN KEY (id_tipo) REFERENCES tipos(id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;
