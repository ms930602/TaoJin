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
   f_id                 int not null comment '主键',
   f_game_id            int not null comment '游戏外键',
   f_name               varchar(256) not null comment '名称',
   f_ratio              decimal(18,2) not null comment '系数 该货币转换成最低货币',
   f_group              char(1) not null comment '组号 区分货币类别',
   f_group_name         varchar(256) not null comment '组名',
   f_sort               int not null comment '序号',
   f_createtime         datetime not null comment '创建时间',
   f_create_user_id     int not null comment '创建人外键',
   f_create_user_name   varchar(500) not null comment '创建人姓名',
   f_last_modify_person_id int comment '最后修改人外键',
   f_last_modify_person_name varchar(500) comment '最后修改人姓名',
   primary key (f_id)
);

alter table t_currency_unit comment '游戏货币单位';

/*==============================================================*/
/* Table: t_ms_buy                                              */
/*==============================================================*/
create table t_ms_buy
(
   f_id                 int not null comment '主键',
   f_login_name         varchar(500) not null comment '买入账号',
   f_game_id            int not null comment '游戏外键',
   f_item_id            int not null comment '物品外键',
   f_group_no           char(1) not null comment '货币组号',
   f_quantity           int not null comment '数量',
   f_price              decimal(18,2) not null comment '买入均价',
   f_buy_time           datetime not null comment '买入时间',
   f_time_status        char(1) not null comment '是否期限 0 否 1是',
   f_exist_time         datetime comment '到期日期',
   f_status             char(1) not null default '0' comment '0 正常 1 作废 2 卖完',
   f_remark             varchar(500) comment '备注',
   f_createtime         datetime not null comment '创建时间',
   f_create_user_id     int not null comment '创建人外键',
   f_create_user_name   varchar(500) not null comment '创建人姓名',
   f_last_modify_person_id int comment '最后修改人外键',
   f_last_modify_person_name varchar(500) comment '最后修改人姓名',
   primary key (f_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: t_ms_game                                             */
/*==============================================================*/
create table t_ms_game
(
   f_id                 int not null auto_increment comment '主键',
   f_name               varchar(200) not null comment '名称',
   f_type               char(1) comment '类型',
   f_column_a           int comment '扩展字段1',
   f_column_b           int comment '扩展字段2',
   f_column_c           varchar(200) comment '扩展字段3',
   f_column_d           varchar(200) comment '扩展字段4',
   f_createtime         datetime not null comment '创建时间',
   f_create_user_id     int not null comment '创建人外键',
   f_create_user_name   varchar(500) not null comment '创建人姓名',
   f_last_modify_person_id int comment '最后修改人外键',
   f_last_modify_person_name varchar(500) comment '最后修改人姓名',
   primary key (f_id)
);

alter table t_ms_game comment '游戏';

/*==============================================================*/
/* Table: t_ms_item                                             */
/*==============================================================*/
create table t_ms_item
(
   f_id                 int not null comment '主键',
   游戏主键                 int not null comment '游戏主键',
   f_name               varchar(200) not null comment '名称',
   f_type               char(1) not null comment '类型 1永久 2 时限 3元宝',
   f_remark             varchar(200) comment '备注',
   f_createtime         datetime not null comment '创建时间',
   f_create_user_id     int not null comment '创建人外键',
   f_create_user_name   varchar(500) not null comment '创建人姓名',
   f_last_modify_person_id int comment '最后修改人外键',
   f_last_modify_person_name varchar(500) comment '最后修改人姓名',
   primary key (f_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_ms_item comment '物品信息';

/*==============================================================*/
/* Table: t_ms_pre_buy                                          */
/*==============================================================*/
create table t_ms_pre_buy
(
   f_id                 int not null auto_increment comment '主键',
   f_game_id            int not null comment '游戏外键',
   f_item_id            int not null comment '物品外键',
   f_login_name         varchar(500) comment '买入账号',
   f_quantity           decimal(18,2) comment '预买数量',
   f_price              decimal(18,2) comment '预计价格',
   f_time               datetime not null comment '预买入时间',
   f_remark             varchar(500) comment '备注',
   f_createtime         datetime not null comment '创建时间',
   f_create_user_id     int not null comment '创建人外键',
   f_create_user_name   varchar(500) not null comment '创建人姓名',
   f_last_modify_person_id int comment '最后修改人外键',
   f_last_modify_person_name varchar(500) comment '最后修改人姓名',
   f_status             char(1) not null default '0' comment '0 正常 1作废',
   primary key (f_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_ms_pre_buy comment '预买入';

/*==============================================================*/
/* Table: t_ms_pre_sell                                         */
/*==============================================================*/
create table t_ms_pre_sell
(
   f_id                 int not null auto_increment comment '主键',
   f_game_id            int not null comment '游戏外键',
   f_item_id            int not null comment '物品外键',
   f_login_name         varchar(500) comment '卖出账号',
   f_quantity           decimal(18,2) not null comment '预卖数量',
   f_price              decimal(18,2) comment '预计价格',
   f_time               datetime not null comment '预卖出时间',
   f_remark             varchar(500) comment '备注',
   f_createtime         datetime not null comment '创建时间',
   f_create_user_id     int not null comment '创建人外键',
   f_create_user_name   varchar(500) not null comment '创建人姓名',
   f_last_modify_person_id int comment '最后修改人外键',
   f_last_modify_person_name varchar(500) comment '最后修改人姓名',
   f_status             char(1) not null default '0' comment '0 正常 1作废',
   primary key (f_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_ms_pre_sell comment '预卖出';

/*==============================================================*/
/* Table: t_ms_revenue_ratio                                    */
/*==============================================================*/
create table t_ms_revenue_ratio
(
   f_id                 int not null comment '主键',
   f_game_id            int not null comment '游戏主键',
   f_name               varchar(256) not null comment '名称',
   f_ratio              decimal(3,2) not null comment '比率',
   f_createtime         datetime not null comment '创建时间',
   f_create_user_id     int not null comment '创建人外键',
   f_create_user_name   varchar(500) not null comment '创建人姓名',
   f_last_modify_person_id int comment '最后修改人外键',
   f_last_modify_person_name varchar(500) comment '最后修改人姓名',
   primary key (f_id)
);

/*==============================================================*/
/* Table: t_ms_sell                                             */
/*==============================================================*/
create table t_ms_sell
(
   f_id                 int not null auto_increment comment '主键',
   f_game_id            int not null comment '游戏外键',
   f_item_id            int not null comment '物品外键',
   f_quantity           decimal(18,2) not null comment '数量',
   f_price              decimal(18,2) not null comment '卖出价格',
   f_sell_time          datetime not null comment '卖出时间',
   f_remark             varchar(500) comment '备注',
   f_createtime         datetime not null comment '创建时间',
   f_create_user_id     int not null comment '创建人外键',
   f_create_user_name   varchar(500) not null comment '创建人姓名',
   f_last_modify_person_id int comment '最后修改人外键',
   f_last_modify_person_name varchar(500) comment '最后修改人姓名',
   primary key (f_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_ms_sell comment '卖出';

/*==============================================================*/
/* Table: t_ms_sell_detail                                      */
/*==============================================================*/
create table t_ms_sell_detail
(
   f_id                 int not null comment '主键',
   f_main_id            int not null comment '主表外键',
   f_buy_id             int not null comment '买入外键',
   f_ratio_id           int not null comment '比率外键',
   f_quantity           decimal(18,2) not null comment '数量',
   f_price              decimal(18,2) not null comment '卖出价格',
   f_sell_time          datetime not null comment '卖出时间',
   f_remark             varchar(500) comment '备注',
   f_createtime         datetime not null comment '创建时间',
   f_create_user_id     int not null comment '创建人外键',
   f_create_user_name   varchar(500) not null comment '创建人姓名',
   f_last_modify_person_id int comment '最后修改人外键',
   f_last_modify_person_name varchar(500) comment '最后修改人姓名',
   primary key (f_id)
);

/*==============================================================*/
/* Table: t_ms_user_game                                        */
/*==============================================================*/
create table t_ms_user_game
(
   f_id                 int not null comment '主键',
   f_game_id            int not null comment '游戏外键',
   f_name               varchar(200) not null comment '名称',
   f_type               char(1) comment '类型',
   f_sort               int not null default 0 comment '置顶排序',
   f_column_a           int comment '扩展字段1',
   f_column_b           int comment '扩展字段2',
   f_column_c           varchar(200) comment '扩展字段3',
   f_column_d           varchar(200) comment '扩展字段4',
   f_createtime         datetime not null comment '创建时间',
   f_create_user_id     int not null comment '创建人外键',
   f_create_user_name   varchar(500) not null comment '创建人姓名',
   f_last_modify_person_id int comment '最后修改人外键',
   f_last_modify_person_name varchar(500) comment '最后修改人姓名',
   primary key (f_id)
);

/*==============================================================*/
/* Table: t_opinion_ms                                          */
/*==============================================================*/
create table t_opinion_ms
(
   f_id                 int not null auto_increment comment '主键',
   f_type               char(1) not null comment '类型',
   f_title              varchar(256) not null comment '标题',
   f_remark             varchar(500) comment '内容',
   f_handle_type        char(1) not null comment '处理状态',
   f_re_text            varchar(256) comment '反馈内容',
   f_re_time            datetime comment '反馈时间',
   f_re_reward_text     varchar(256) comment '奖励',
   f_createtime         datetime not null comment '创建时间',
   f_create_user_id     int not null comment '创建人外键',
   f_create_user_name   varchar(500) not null comment '创建人名称',
   f_last_modify_person_id int comment '最后修改人外键',
   f_last_modify_person_name varchar(500) comment '最后修改人姓名',
   primary key (f_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_opinion_ms comment '意见反馈';

/*==============================================================*/
/* Table: t_user_account_number                                 */
/*==============================================================*/
create table t_user_account_number
(
   f_id                 int not null comment '主键',
   f_game_id            int not null comment '游戏外键',
   f_login_name         varchar(500) not null comment '账号名称',
   f_password           varchar(500) comment '账号密码',
   f_status             char(1) not null default '0' comment '0否 1 是 ',
   f_use_time           datetime comment '解印时间',
   f_remark             varchar(500) comment '备注',
   f_createtime         datetime not null comment '创建时间',
   f_create_user_id     int not null comment '创建人外键',
   f_create_user_name   varchar(500) not null comment '创建人姓名',
   f_last_modify_person_id int comment '最后修改人外键',
   f_last_modify_person_name varchar(500) comment '最后修改人姓名',
   primary key (f_id)
);

alter table t_currency_unit add constraint FK_Reference_7 foreign key (f_game_id)
      references t_ms_game (f_id) on delete restrict on update restrict;

alter table t_ms_buy add constraint FK_Reference_6 foreign key (f_item_id)
      references t_ms_item (f_id) on delete restrict on update restrict;

alter table t_ms_buy add constraint FK_Reference_8 foreign key (f_game_id)
      references t_ms_game (f_id) on delete restrict on update restrict;

alter table t_ms_item add constraint FK_Reference_3 foreign key (游戏主键)
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

