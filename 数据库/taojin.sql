/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/11/27 14:20:46                          */
/*==============================================================*/


drop table if exists t_currency_unit;

drop table if exists t_ms_buy;

drop table if exists t_ms_game;

drop table if exists t_ms_item;

drop table if exists t_ms_pre_buy;

drop table if exists t_ms_pre_sell;

drop table if exists t_ms_revenue_ratio;

drop table if exists t_ms_sell;

drop table if exists t_ms_sell_detail;

drop table if exists t_ms_user_game;

drop table if exists t_opinion_ms;

drop table if exists t_user_account_number;

/*==============================================================*/
/* Table: t_currency_unit                                       */
/*==============================================================*/
create table t_currency_unit
(
   f_id                 int not null comment '����',
   f_game_id            int not null comment '��Ϸ���',
   f_name               varchar(256) not null comment '����',
   f_ratio              decimal(18,2) not null comment 'ϵ�� �û���ת������ͻ���',
   f_group              char(1) not null comment '��� ���ֻ������',
   f_group_name         varchar(256) not null comment '����',
   f_sort               int not null comment '���',
   f_createtime         datetime not null comment '����ʱ��',
   f_create_user_id     int not null comment '���������',
   f_create_user_name   varchar(500) not null comment '����������',
   f_last_modify_person_id int comment '����޸������',
   f_last_modify_person_name varchar(500) comment '����޸�������',
   primary key (f_id)
);

alter table t_currency_unit comment '��Ϸ���ҵ�λ';

/*==============================================================*/
/* Table: t_ms_buy                                              */
/*==============================================================*/
create table t_ms_buy
(
   f_id                 int not null comment '����',
   f_login_name         varchar(500) not null comment '�����˺�',
   f_game_id            int not null comment '��Ϸ���',
   f_item_id            int not null comment '��Ʒ���',
   f_group_no           char(1) not null comment '�������',
   f_quantity           int not null comment '����',
   f_price              decimal(18,2) not null comment '�������',
   f_buy_time           datetime not null comment '����ʱ��',
   f_time_status        char(1) not null comment '�Ƿ����� 0 �� 1��',
   f_exist_time         datetime comment '��������',
   f_status             char(1) not null default '0' comment '0 ���� 1 ���� 2 ����',
   f_remark             varchar(500) comment '��ע',
   f_createtime         datetime not null comment '����ʱ��',
   f_create_user_id     int not null comment '���������',
   f_create_user_name   varchar(500) not null comment '����������',
   f_last_modify_person_id int comment '����޸������',
   f_last_modify_person_name varchar(500) comment '����޸�������',
   primary key (f_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: t_ms_game                                             */
/*==============================================================*/
create table t_ms_game
(
   f_id                 int not null auto_increment comment '����',
   f_name               varchar(200) not null comment '����',
   f_type               char(1) comment '����',
   f_column_a           int comment '��չ�ֶ�1',
   f_column_b           int comment '��չ�ֶ�2',
   f_column_c           varchar(200) comment '��չ�ֶ�3',
   f_column_d           varchar(200) comment '��չ�ֶ�4',
   f_createtime         datetime not null comment '����ʱ��',
   f_create_user_id     int not null comment '���������',
   f_create_user_name   varchar(500) not null comment '����������',
   f_last_modify_person_id int comment '����޸������',
   f_last_modify_person_name varchar(500) comment '����޸�������',
   primary key (f_id)
);

alter table t_ms_game comment '��Ϸ';

/*==============================================================*/
/* Table: t_ms_item                                             */
/*==============================================================*/
create table t_ms_item
(
   f_id                 int not null comment '����',
   ��Ϸ����                 int not null comment '��Ϸ����',
   f_name               varchar(200) not null comment '����',
   f_type               char(1) not null comment '���� 1���� 2 ʱ�� 3Ԫ��',
   f_remark             varchar(200) comment '��ע',
   f_createtime         datetime not null comment '����ʱ��',
   f_create_user_id     int not null comment '���������',
   f_create_user_name   varchar(500) not null comment '����������',
   f_last_modify_person_id int comment '����޸������',
   f_last_modify_person_name varchar(500) comment '����޸�������',
   primary key (f_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_ms_item comment '��Ʒ��Ϣ';

/*==============================================================*/
/* Table: t_ms_pre_buy                                          */
/*==============================================================*/
create table t_ms_pre_buy
(
   f_id                 int not null auto_increment comment '����',
   f_game_id            int not null comment '��Ϸ���',
   f_item_id            int not null comment '��Ʒ���',
   f_login_name         varchar(500) comment '�����˺�',
   f_quantity           decimal(18,2) comment 'Ԥ������',
   f_price              decimal(18,2) comment 'Ԥ�Ƽ۸�',
   f_time               datetime not null comment 'Ԥ����ʱ��',
   f_remark             varchar(500) comment '��ע',
   f_createtime         datetime not null comment '����ʱ��',
   f_create_user_id     int not null comment '���������',
   f_create_user_name   varchar(500) not null comment '����������',
   f_last_modify_person_id int comment '����޸������',
   f_last_modify_person_name varchar(500) comment '����޸�������',
   f_status             char(1) not null default '0' comment '0 ���� 1����',
   primary key (f_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_ms_pre_buy comment 'Ԥ����';

/*==============================================================*/
/* Table: t_ms_pre_sell                                         */
/*==============================================================*/
create table t_ms_pre_sell
(
   f_id                 int not null auto_increment comment '����',
   f_game_id            int not null comment '��Ϸ���',
   f_item_id            int not null comment '��Ʒ���',
   f_login_name         varchar(500) comment '�����˺�',
   f_quantity           decimal(18,2) not null comment 'Ԥ������',
   f_price              decimal(18,2) comment 'Ԥ�Ƽ۸�',
   f_time               datetime not null comment 'Ԥ����ʱ��',
   f_remark             varchar(500) comment '��ע',
   f_createtime         datetime not null comment '����ʱ��',
   f_create_user_id     int not null comment '���������',
   f_create_user_name   varchar(500) not null comment '����������',
   f_last_modify_person_id int comment '����޸������',
   f_last_modify_person_name varchar(500) comment '����޸�������',
   f_status             char(1) not null default '0' comment '0 ���� 1����',
   primary key (f_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_ms_pre_sell comment 'Ԥ����';

/*==============================================================*/
/* Table: t_ms_revenue_ratio                                    */
/*==============================================================*/
create table t_ms_revenue_ratio
(
   f_id                 int not null comment '����',
   f_game_id            int not null comment '��Ϸ����',
   f_name               varchar(256) not null comment '����',
   f_ratio              decimal(3,2) not null comment '����',
   f_createtime         datetime not null comment '����ʱ��',
   f_create_user_id     int not null comment '���������',
   f_create_user_name   varchar(500) not null comment '����������',
   f_last_modify_person_id int comment '����޸������',
   f_last_modify_person_name varchar(500) comment '����޸�������',
   primary key (f_id)
);

/*==============================================================*/
/* Table: t_ms_sell                                             */
/*==============================================================*/
create table t_ms_sell
(
   f_id                 int not null auto_increment comment '����',
   f_game_id            int not null comment '��Ϸ���',
   f_item_id            int not null comment '��Ʒ���',
   f_quantity           decimal(18,2) not null comment '����',
   f_price              decimal(18,2) not null comment '�����۸�',
   f_sell_time          datetime not null comment '����ʱ��',
   f_remark             varchar(500) comment '��ע',
   f_createtime         datetime not null comment '����ʱ��',
   f_create_user_id     int not null comment '���������',
   f_create_user_name   varchar(500) not null comment '����������',
   f_last_modify_person_id int comment '����޸������',
   f_last_modify_person_name varchar(500) comment '����޸�������',
   primary key (f_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_ms_sell comment '����';

/*==============================================================*/
/* Table: t_ms_sell_detail                                      */
/*==============================================================*/
create table t_ms_sell_detail
(
   f_id                 int not null comment '����',
   f_main_id            int not null comment '�������',
   f_buy_id             int not null comment '�������',
   f_ratio_id           int not null comment '�������',
   f_quantity           decimal(18,2) not null comment '����',
   f_price              decimal(18,2) not null comment '�����۸�',
   f_sell_time          datetime not null comment '����ʱ��',
   f_remark             varchar(500) comment '��ע',
   f_createtime         datetime not null comment '����ʱ��',
   f_create_user_id     int not null comment '���������',
   f_create_user_name   varchar(500) not null comment '����������',
   f_last_modify_person_id int comment '����޸������',
   f_last_modify_person_name varchar(500) comment '����޸�������',
   primary key (f_id)
);

/*==============================================================*/
/* Table: t_ms_user_game                                        */
/*==============================================================*/
create table t_ms_user_game
(
   f_id                 int not null comment '����',
   f_game_id            int not null comment '��Ϸ���',
   f_name               varchar(200) not null comment '����',
   f_type               char(1) comment '����',
   f_sort               int not null default 0 comment '�ö�����',
   f_column_a           int comment '��չ�ֶ�1',
   f_column_b           int comment '��չ�ֶ�2',
   f_column_c           varchar(200) comment '��չ�ֶ�3',
   f_column_d           varchar(200) comment '��չ�ֶ�4',
   f_createtime         datetime not null comment '����ʱ��',
   f_create_user_id     int not null comment '���������',
   f_create_user_name   varchar(500) not null comment '����������',
   f_last_modify_person_id int comment '����޸������',
   f_last_modify_person_name varchar(500) comment '����޸�������',
   primary key (f_id)
);

/*==============================================================*/
/* Table: t_opinion_ms                                          */
/*==============================================================*/
create table t_opinion_ms
(
   f_id                 int not null auto_increment comment '����',
   f_type               char(1) not null comment '����',
   f_title              varchar(256) not null comment '����',
   f_remark             varchar(500) comment '����',
   f_handle_type        char(1) not null comment '����״̬',
   f_re_text            varchar(256) comment '��������',
   f_re_time            datetime comment '����ʱ��',
   f_re_reward_text     varchar(256) comment '����',
   f_createtime         datetime not null comment '����ʱ��',
   f_create_user_id     int not null comment '���������',
   f_create_user_name   varchar(500) not null comment '����������',
   f_last_modify_person_id int comment '����޸������',
   f_last_modify_person_name varchar(500) comment '����޸�������',
   primary key (f_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_opinion_ms comment '�������';

/*==============================================================*/
/* Table: t_user_account_number                                 */
/*==============================================================*/
create table t_user_account_number
(
   f_id                 int not null comment '����',
   f_game_id            int not null comment '��Ϸ���',
   f_login_name         varchar(500) not null comment '�˺�����',
   f_password           varchar(500) comment '�˺�����',
   f_status             char(1) not null default '0' comment '0�� 1 �� ',
   f_use_time           datetime comment '��ӡʱ��',
   f_remark             varchar(500) comment '��ע',
   f_createtime         datetime not null comment '����ʱ��',
   f_create_user_id     int not null comment '���������',
   f_create_user_name   varchar(500) not null comment '����������',
   f_last_modify_person_id int comment '����޸������',
   f_last_modify_person_name varchar(500) comment '����޸�������',
   primary key (f_id)
);

alter table t_currency_unit add constraint FK_Reference_7 foreign key (f_game_id)
      references t_ms_game (f_id) on delete restrict on update restrict;

alter table t_ms_buy add constraint FK_Reference_6 foreign key (f_item_id)
      references t_ms_item (f_id) on delete restrict on update restrict;

alter table t_ms_buy add constraint FK_Reference_8 foreign key (f_game_id)
      references t_ms_game (f_id) on delete restrict on update restrict;

alter table t_ms_item add constraint FK_Reference_3 foreign key (��Ϸ����)
      references t_ms_game (f_id) on delete restrict on update restrict;

alter table t_ms_pre_buy add constraint FK_Reference_15 foreign key (f_game_id)
      references t_ms_game (f_id) on delete restrict on update restrict;

alter table t_ms_pre_buy add constraint FK_Reference_4 foreign key (f_item_id)
      references t_ms_item (f_id) on delete restrict on update restrict;

alter table t_ms_pre_sell add constraint FK_Reference_16 foreign key (f_game_id)
      references t_ms_game (f_id) on delete restrict on update restrict;

alter table t_ms_pre_sell add constraint FK_Reference_5 foreign key (f_item_id)
      references t_ms_item (f_id) on delete restrict on update restrict;

alter table t_ms_revenue_ratio add constraint FK_Reference_9 foreign key (f_game_id)
      references t_ms_game (f_id) on delete restrict on update restrict;

alter table t_ms_sell_detail add constraint FK_Reference_10 foreign key (f_main_id)
      references t_ms_sell (f_id) on delete restrict on update restrict;

alter table t_ms_sell_detail add constraint FK_Reference_11 foreign key (f_buy_id)
      references t_ms_buy (f_id) on delete restrict on update restrict;

alter table t_ms_sell_detail add constraint FK_Reference_12 foreign key (f_ratio_id)
      references t_ms_revenue_ratio (f_id) on delete restrict on update restrict;

alter table t_ms_user_game add constraint FK_Reference_13 foreign key (f_game_id)
      references t_ms_game (f_id) on delete restrict on update restrict;

alter table t_user_account_number add constraint FK_Reference_14 foreign key (f_game_id)
      references t_ms_game (f_id) on delete restrict on update restrict;

