drop table if exists skt.products;$$

create table skt.products
(
    id          int auto_increment primary key,
    sku         varchar(20)  not null,
    name        varchar(30)  not null,
    description varchar(200) not null,
    units       int          not null,
    constraint products_name_uindex unique (name),
    constraint products_sku_uindex unique (sku)
);$$

drop procedure if exists skt.get_all_products;$$

create procedure skt.get_all_products()
begin
    select id,
           sku,
           name,
           description,
           units
    from skt.products;
end;$$

drop procedure if exists skt.insert_product;$$

create procedure skt.insert_product(IN p_sku varchar(20), IN p_name varchar(30), IN p_description varchar(200), IN p_units int)
begin
    insert into products (sku, name, description, units)
    values (p_sku, p_name, p_description, p_units);
    select id from skt.products where sku = p_sku;
end;$$