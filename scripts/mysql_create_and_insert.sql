CREATE TABLE `order` (
  `order_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `amount` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT `order`(order_id, amount, product_id, total_price) VALUES (1, 1, 1, 100);