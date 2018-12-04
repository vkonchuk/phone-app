CREATE DATABASE IF NOT EXISTS phone_app;

USE `phone_app`;

DROP TABLE IF EXISTS `phone`;

CREATE TABLE `phone` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Unique identifier for this table',
  `name` varchar(32) NOT NULL COMMENT 'Name of the phone',
  `description` varchar(128) COMMENT 'Short description of the phone',
  `image_url` varchar(128) COMMENT 'URL of the phone image',
  `price` decimal(10, 2) COMMENT 'Price of the phone',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date when phone was created',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Date when phone was modified',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT 'Table stores information about phones';

INSERT INTO phone_app.phone SET name = 'Apple iPhone', description = 'iPhone\'s description', image_url = 'http://www.apple.com', price = 999.99;
INSERT INTO phone_app.phone SET name = 'Samsung Galaxy S9', description = 'Galaxy\'s description', image_url = 'https://www.samsung.com', price = 989.99;
INSERT INTO phone_app.phone SET name = 'Google Pixel 2', description = 'Pixel\'s description', image_url = 'https://www.google.com', price = 979.99;

CREATE USER 'testuser'@'localhost' IDENTIFIED BY 'testpassword';
GRANT ALL PRIVILEGES ON *.* TO 'testuser'@'localhost';