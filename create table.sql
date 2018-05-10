CREATE SCHEMA `food_delivery`;
USE `food_delivery`;

CREATE TABLE `Customer` (
	`ID` INT NOT NULL AUTO_INCREMENT,
	`Name` VARCHAR(255) NOT NULL,
	`Tel` INT NOT NULL,
	`Address` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`ID`)
);

CREATE TABLE `Order` (
	`ID` INT NOT NULL AUTO_INCREMENT,
	`CustomerID` INT NOT NULL,
	`StageStr` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`ID`)
);

CREATE TABLE `Stage` (
	`StageStr` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`StageStr`)
);

CREATE TABLE `Menu` (
	`ID` INT NOT NULL AUTO_INCREMENT,
	`MenuStr` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`ID`)
);

CREATE TABLE `Material` (
	`ID` INT NOT NULL AUTO_INCREMENT,
	`MaterialStr` VARCHAR(255) NOT NULL,
	`UnitPrice` FLOAT NOT NULL,
	PRIMARY KEY (`ID`)
);

CREATE TABLE `Food` (
	`ID` INT NOT NULL AUTO_INCREMENT,
	`FoodStr` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`ID`)
);

CREATE TABLE `Dish` (
	`ID` INT NOT NULL AUTO_INCREMENT,
	`MenuID` INT NOT NULL,
	`FoodID` INT NOT NULL,
	`Price` FLOAT NOT NULL,
	PRIMARY KEY (`ID`)
);

CREATE TABLE `contains` (
	`OrderID` INT NOT NULL,
	`DishID` INT NOT NULL,
	PRIMARY KEY (`OrderID`,`DishID`)
);

CREATE TABLE `needs` (
	`FoodID` INT NOT NULL,
	`MaterialID` INT NOT NULL,
	`MaterialAmount` INT NOT NULL,
	PRIMARY KEY (`FoodID`,`MaterialID`)
);

ALTER TABLE `Order` ADD CONSTRAINT `Order_fk0` FOREIGN KEY (`CustomerID`) REFERENCES `Customer`(`ID`) ON DELETE CASCADE ON update cascade;

ALTER TABLE `Order` ADD CONSTRAINT `Order_fk1` FOREIGN KEY (`StageID`) REFERENCES `Stage`(`StageStr`) ON DELETE CASCADE ON update cascade;

ALTER TABLE `Dish` ADD CONSTRAINT `Dish_fk0` FOREIGN KEY (`MenuID`) REFERENCES `Menu`(`ID`) ON DELETE CASCADE ON update cascade;

ALTER TABLE `Dish` ADD CONSTRAINT `Dish_fk1` FOREIGN KEY (`FoodID`) REFERENCES `Food`(`ID`) ON DELETE CASCADE ON update cascade;

ALTER TABLE `contains` ADD CONSTRAINT `contains_fk0` FOREIGN KEY (`OrderID`) REFERENCES `Order`(`ID`) ON DELETE CASCADE ON update cascade;

ALTER TABLE `contains` ADD CONSTRAINT `contains_fk1` FOREIGN KEY (`DishID`) REFERENCES `Dish`(`ID`) ON DELETE CASCADE ON update cascade;

ALTER TABLE `needs` ADD CONSTRAINT `needs_fk0` FOREIGN KEY (`FoodID`) REFERENCES `Food`(`ID`) ON DELETE CASCADE ON update cascade;

ALTER TABLE `needs` ADD CONSTRAINT `needs_fk1` FOREIGN KEY (`MaterialID`) REFERENCES `Material`(`ID`) ON DELETE CASCADE ON update cascade;

INSERT INTO `food_delivery`.`stage` (`StageStr`) VALUES ('Received');
INSERT INTO `food_delivery`.`stage` (`StageStr`) VALUES ('Cooking');
INSERT INTO `food_delivery`.`stage` (`StageStr`) VALUES ('Packing');
INSERT INTO `food_delivery`.`stage` (`StageStr`) VALUES ('Packed');
INSERT INTO `food_delivery`.`stage` (`StageStr`) VALUES ('Delivering');
INSERT INTO `food_delivery`.`stage` (`StageStr`) VALUES ('Delivered');
INSERT INTO `food_delivery`.`stage` (`StageStr`) VALUES ('Cancelled');


