-- 包含用户名 密码 盐 生日
create table `user`
(
    id          int auto_increment primary key,
    name        varchar(255),
    password    varchar(255),
    salt        varchar(255),
    birthday    timestamp,
    create_time date,
    update_time date
);

-- 商品表。英文名 中文名 商品id(用来唯一确定一个商品的) 版本，用来确认是买的什么版本的
-- 图片的url 价格
create table `goods`
(
    id          int auto_increment primary key,
    name        varchar(255),
    cname       varchar(255),
    goods_id    int,
    version     int,
    image_url   varchar(255),
    price       double,
    create_time date,
    update_time date
);

-- 订单表 下单客户。订单商品详情,在详情id中，才有存储是具体哪些商品 这张表只存储了id
create table `orders`
(
    id          int auto_increment primary key,
    user_id     int,
    detail_id   int,
    status      varchar(50),
    total_price double,
    create_time date,
    update_time date
);

create table `order_detail`
(
    id          int auto_increment primary key,
    user_id     int,
    goods_ids   varchar(255),
    status      varchar(50),
    total_price double,
    create_time date,
    update_time date
);
