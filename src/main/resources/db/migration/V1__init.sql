create table delivery
(
    id            bigint auto_increment
        primary key,
    create_time   datetime(6)  not null,
    update_time   datetime(6)  not null,
    delivery_fee  int          null,
    delivery_name varchar(100) null,
    member_id     bigint       null
);

create table item
(
    id               bigint auto_increment
        primary key,
    create_time      datetime(6)  not null,
    update_time      datetime(6)  not null,
    delivery_id      bigint       null,
    item_detail      longtext     not null,
    item_name        varchar(100) not null,
    item_sell_status varchar(255) not null,
    item_token       varchar(255) null,
    member_id        bigint       null,
    price            int          not null,
    stock_number     int          not null
);

create table item_image
(
    id                  bigint auto_increment
        primary key,
    create_time         datetime(6)  not null,
    update_time         datetime(6)  not null,
    image_name          varchar(500) null,
    image_url           varchar(500) null,
    is_rep_image        bit          null,
    original_image_name varchar(200) null,
    item_id             bigint       null,
    constraint FKta6kqet3u8mv95y7jwtgwqpys
        foreign key (item_id) references item (id)
);

create table member
(
    id           bigint auto_increment
        primary key,
    create_time  datetime(6)  not null,
    update_time  datetime(6)  not null,
    email        varchar(50)  not null,
    member_name  varchar(20)  not null,
    role         varchar(20)  not null,
    member_token varchar(255) null,
    password     varchar(200) not null,
    type         varchar(10)  not null,
    constraint UK_mbmcqelty0fbrvxp1q58dn57t
        unique (email)
);

create table orders
(
    id           bigint auto_increment
        primary key,
    create_time  datetime(6)  not null,
    update_time  datetime(6)  not null,
    member_id    bigint       null,
    order_status varchar(255) null,
    order_time   datetime(6)  null,
    order_token  varchar(255) null
);

create table order_item
(
    id          bigint auto_increment
        primary key,
    create_time datetime(6) not null,
    update_time datetime(6) not null,
    count       int         not null,
    item_id     bigint      null,
    order_price int         not null,
    order_id    bigint      null,
    constraint FKt4dc2r9nbvbujrljv3e23iibt
        foreign key (order_id) references orders (id)
);

