create table baskets (
                         id bigint not null,
                         user_id bigint,
                         primary key (id)
);
create table baskets_products (
                                  basket_id bigint not null,
                                  product_id bigint not null
);
create table categories (
                            id bigint not null,
                            title varchar(255),
                            primary key (id)
);
create table order_details (
                               id bigint not null,
                               amount numeric(38,2),
                               price numeric(38,2),
                               order_id bigint,
                               product_id bigint,
                               primary key (id)
);
create table orders (
                        id bigint not null,
                        address varchar(255),
                        created timestamp(6),
                        status enum ('APPROVED','CANSELED','ClOSED','NEW','PAID'),
                        sum numeric(38,2),
                        update timestamp(6),
                        user_id bigint,
                        primary key (id)
);
create table orders_details (
                                order_id bigint not null,
                                details_id bigint not null
);
create table products (
                          id bigint not null,
                          description varchar(255),
                          price numeric(38,2),
                          title varchar(255),
                          primary key (id)
);
create table products_categories (
                                     product_id bigint not null,
                                     category_id bigint not null
);
create table users (
                       id bigint not null,
                       archive boolean,
                       email varchar(255),
                       password varchar(255),
                       role enum ('ADMIN','MANAGER','USER'),
                       username varchar(255),
                       basket_id bigint,
                       primary key (id)
);
alter table if exists baskets
drop constraint if exists UK49gb0uw38g2fs913y3r0p74yq;
alter table if exists baskets
    add constraint UK49gb0uw38g2fs913y3r0p74yq unique (user_id);
alter table if exists orders_details
drop constraint if exists UKkk6y3pyhjt6kajomtjbhsoajo;
alter table if exists orders_details
    add constraint UKkk6y3pyhjt6kajomtjbhsoajo unique (details_id);
alter table if exists users
drop constraint if exists UKevmh3m4e7d8matob68ybo0mkc;
alter table if exists users
    add constraint UKevmh3m4e7d8matob68ybo0mkc unique (basket_id) ;
create sequence basket_seq start with 1 increment by 1;
create sequence category_seq start with 1 increment by 1;
create sequence order_detail_seq start with 1 increment by 1;
create sequence order_seq start with 1 increment by 1;
create sequence product_seq start with 1 increment by 1;
create sequence user_seq start with 1 increment by 1;
alter table if exists baskets
    add constraint FK87s17cinc4wkx0taas5nh0s8h
    foreign key (user_id)
    references users;
alter table if exists baskets_products
    add constraint FK81mtboy4cahxvtv86apcin43c
    foreign key (product_id)
    references products;
alter table if exists baskets_products
    add constraint FKribumfhgwuqeoqhq8gs88smee
    foreign key (basket_id)
    references baskets;
alter table if exists order_details
    add constraint FKjyu2qbqt8gnvno9oe9j2s2ldk
    foreign key (order_id)
    references orders;
alter table if exists order_details
    add constraint FK4q98utpd73imf4yhttm3w0eax
    foreign key (product_id)
    references products;
alter table if exists orders
    add constraint FK32ql8ubntj5uh44ph9659tiih
    foreign key (user_id)
    references users;
alter table if exists orders_details
    add constraint FKbblrq2kcscnyr9fsv8ptp98wy
    foreign key (details_id)
    references order_details;
alter table if exists orders_details
    add constraint FK5o977kj2vptwo70fu7w7so9fe
    foreign key (order_id)
    references orders;
alter table if exists products_categories
    add constraint FKqt6m2o5dly3luqcm00f5t4h2p
    foreign key (category_id)
    references categories;
alter table if exists products_categories
    add constraint FKtj1vdea8qwerbjqie4xldl1el
    foreign key (product_id)
    references products;
alter table if exists users
    add constraint FKommxbvym8k63qbwomj0ikj8us
    foreign key (basket_id)
    references baskets;


