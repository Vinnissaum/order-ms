RENAME TABLE pedidos to orders;

RENAME TABLE item_do_pedido to order_item;

ALTER TABLE orders RENAME COLUMN data_hora TO date_time;

ALTER TABLE order_item RENAME COLUMN descricao TO description;
ALTER TABLE order_item RENAME COLUMN quantidade TO quantity;
ALTER TABLE order_item RENAME COLUMN pedido_id TO order_id;
