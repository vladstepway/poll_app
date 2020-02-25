create table if not exists roles
(
    id   bigint auto_increment,
    name varchar(60) not null,
    constraint roles_pk
        primary key (id)
);

INSERT INTO roles(name)
VALUES ('ROLE_USER');
INSERT INTO roles(name)
VALUES ('ROLE_ADMIN');


create table if not exists users
(
	id bigint auto_increment
		primary key,
	name varchar(40) not null,
	username varchar(15) not null,
	email varchar(40) not null,
	password varchar(255) not null,
	createdAt timestamp null,
	updatedAt timestamp null,
	constraint users_email_uindex
		unique (email),
	constraint users_username_uindex
		unique (username)
);
create unique index users_email_uindex
    on users (email);

create unique index users_username_uindex
    on users (username);

create table if not exists user_roles
(
    user_id bigint not null,
    role_id bigint not null
);

