DROP DATABASE if exists vending_machine_test;

CREATE DATABASE vending_machine_test;

USE vending_machine_test;

CREATE TABLE IF NOT EXISTS `item` (
 `item_id` int(11) NOT NULL AUTO_INCREMENT,
 `item_name` varchar(20) NOT NULL,
 `item_price` DECIMAL(5,2) NOT NULL, 
 `item_quantity` varchar(10) NOT NULL,
  PRIMARY KEY (`item_id`)
)  ;

CREATE TABLE IF NOT EXISTS `change` (
	`change_id` int(11) NOT NULL AUTO_INCREMENT,
    `quarters` int(10) NOT NULL,
    `dimes` int(10) NOT NULL,
    `nickels` int(10) NOT NULL,
    `pennies` int(10) NOT NULL,
    PRIMARY KEY (`change_id`)
);

INSERT INTO item (item_name, item_price, item_quantity)
VALUES ('Ice Cream Sandwich', 0.85, 1),
('Pop-Tarts', 1.50, 10),
('Snickers', 0.95, 10),
('M&Ms', 1.10, 10),
('Coke', 1.50, 10),
('Doritos', 1.25, 10),
('Sour Patch Kids', 1.75, 10),
('Milky-Way', 1.25, 10),
('Fritos', 1.10, 10);