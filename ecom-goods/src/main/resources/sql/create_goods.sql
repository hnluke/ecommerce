/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/6/25 19:40:00                           */
/*==============================================================*/


drop table if exists comments;

drop table if exists goods;

/*==============================================================*/
/* Table: comments                                              */
/*==============================================================*/
create table comments
(
   comm_id              int not null auto_increment,
   comm_gd_id           int,
   comm_text            varchar(50),
   comm_date            datetime default CURRENT_TIMESTAMP,
   primary key (comm_id)
)ENGINE=INNODB auto_increment=1 default CHARSET=utf8;

/*==============================================================*/
/* Table: goods                                                 */
/*==============================================================*/
create table goods
(
   good_id              int not null auto_increment,
   good_name            varchar(12),
   good_type            varchar(10),
   good_model           varchar(20),
   good_product         varchar(30),
   good_store           int,
   good_status          varchar(10),
   good_price           double,
   good_putUpDate       datetime default CURRENT_TIMESTAMP,
   good_detail          varchar(50),
   primary key (good_id)
)ENGINE=INNODB auto_increment=1 default CHARSET=utf8;

alter table comments add constraint FK_Relationship_1 foreign key (comm_gd_id)
      references goods (good_id) on delete restrict on update restrict;

insert into goods (good_name, good_type, good_model, good_product, good_store, good_status, good_price)
values ('电脑', '数码', '联想ADEFS', '广州', 300, '上架', 5000);
insert into goods (good_name, good_type, good_model, good_product, good_store, good_status, good_price)
values ('手机', '数码', '华为DSEG', '北京', 100, '上架', 4000);

