create table if not exists w_warehouse
(
    id          bigint(10) auto_increment not null primary key,
    description varchar(15) default null
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;

create table if not exists w_shelf
(
    id           bigint(10) auto_increment not null primary key,
    code         varchar(15)               not null,
    warehouse_id bigint(10)                not null,
    constraint fk_w_warehouse foreign key (warehouse_id) references w_warehouse (id) on update cascade on delete cascade
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;