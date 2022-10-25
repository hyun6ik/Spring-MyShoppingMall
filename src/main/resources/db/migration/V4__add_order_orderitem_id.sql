alter table orders add order_item_id bigint null;
alter table orders add constraint ORDER_ITEM_FK foreign key (order_item_id) references order_item(id);
