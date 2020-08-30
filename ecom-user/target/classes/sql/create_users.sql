drop table if exists users;
create table users(
	user_id int(11) not NULL auto_increment,
	user_name VARCHAR(20) DEFAULT NULL,
	user_pwd VARCHAR(10) DEFAULT NULL,
	user_prio VARCHAR(2) DEFAULT 'C',
	user_account double default 0.0,
	user_addr varchar(30) default '',
	user_phone varchar(11) default '',
	PRIMARY KEY(user_id)
)ENGINE=INNODB auto_increment=1 default CHARSET=utf8;

insert into users(user_name, user_pwd, user_prio, user_account, user_addr, user_phone)
values ('luke', '123', 'S', 1000.0, '益阳市赫山区', '13352452145');
insert into users(user_name, user_pwd, user_prio, user_account, user_addr, user_phone)
values ('rain', '456', 'C', 1200.0, '常德市三一大道', '13524565214');