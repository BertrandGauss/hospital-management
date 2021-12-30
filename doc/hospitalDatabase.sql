/*
Navicat MySQL Data Transfer

Author		: Gan Yan
Date		: 2021-12-20

Environment	: Navicat Premium 12
Source Database	: hospital
Target Server Type	: MYSQL
File Encoding	: UTF-8
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for Doctor
-- ----------------------------
DROP TABLE IF EXISTS `Doctor`;
CREATE TABLE `Doctor`(
	`doctorId` bigint auto_increment,
	`dUsername` varchar(32) not null,
	`dPassword` varchar(32) not null,
	`dName` varchar(32) not null default '',
	`dIdentificationNum` varchar(18) not null default '',
	`dOffice` varchar(32) not null default '',
	`dTitle` varchar(32),
	`dSkill` varchar(100),
	primary key(`doctorId`)
);

-- ----------------------------
-- Table structure for Patient
-- ----------------------------
DROP TABLE IF EXISTS `Patient`;
CREATE TABLE `Patient`(
	`patientId` bigint auto_increment,
	`pUsername` varchar(32) not null,
	`pPassword` varchar(32) not null,
	`pIdentificationNum` varchar(18) not null default '',
	`pName` varchar(32) not null default '',
	`pGender` varchar(1),
	`pAge` int,
	`pOccupation` varchar(32),
	`pPhone` varchar(11),
	`pAddress` varchar(100),
	`isOrder` int not null default 0,
	`orderTimes` int not null default 0,
	`isInBlacklist` int not null default 0,
	primary key(`patientId`)
);

-- ----------------------------
-- Table structure for Order
-- ----------------------------
DROP TABLE IF EXISTS `Order`;
CREATE TABLE `Order`(
	`orderId` bigint auto_increment,
	`patientId` bigint,
	`department` varchar(32),
	`doctorId` bigint,
	`oType` varchar(10),
	`oDate` date,
	`oTime` time,
	`isValid` int not null default 1,
	primary key(`orderId`),
	CONSTRAINT fk_patient_order_pid FOREIGN KEY(`patientId`) REFERENCES `Patient`(`patientId`)
);

-- ----------------------------
-- Table structure for Regisitration
-- ----------------------------
DROP TABLE IF EXISTS `Regisitration`;
CREATE TABLE Regisitration (
	`regisitrationId` bigint auto_increment,
	`patientId` bigint,
	`doctorId` bigint,
	`rNum` varchar(32),
	primary key(`regisitrationId`),
	CONSTRAINT fk_patient_regisitration_pid FOREIGN KEY(`patientId`) REFERENCES `Patient`(`patientId`)
);

-- ----------------------------
-- Table structure for History;
-- ----------------------------
DROP TABLE IF EXISTS `History`;
CREATE TABLE `History` (
	`historyId` bigint auto_increment,
	`patientId` bigint,
	`pIdentificationNum` varchar(18) not null default '',
	`clinicType` varchar(32),
	`diseaseDate` date,
	`diagnosis` varchar(32),
	`drugAllergyHis` varchar(10),
	`chiefComplaint` varchar(100),
	`presentIllness` varchar(100),
	primary key(`historyId`),
	CONSTRAINT fk_patient_history_pid FOREIGN KEY(`patientId`) REFERENCES `Patient`(`patientId`)
);

-- ----------------------------
-- Table structure for Item;
-- ----------------------------
DROP TABLE IF EXISTS `Item`;
CREATE TABLE `Item` (
	`itemId` bigint auto_increment,
	`illnessSummary` varchar(100),
	`checkArea` varchar(32),
	`notice` varchar(100),
	`checkTime` date,
	`checkRes` varchar(100),
	`opinion` varchar(100),
	primary key(`itemId`)
);

-- ----------------------------
-- Table structure for Recipe
-- ----------------------------
DROP TABLE IF EXISTS `Recipe`;
CREATE TABLE Recipe (
	`recipeId` bigint auto_increment,
	`doctorId` bigint,
	`patientId` bigint,
	`itemId` bigint,
	`recipeName` varchar(32),
	`dosage` varchar(32),
	`units` varchar(10),
	`frequency` varchar(10),
	`days` int,
	`usage` varchar(32),
	`price` varchar(10),
	`totalPrice` varchar(32),
	`skinTestRes` varchar(32),
	`remains` int,
	`haveDone` int not null default 0,
	primary key(`recipeId`),
	CONSTRAINT fk_doctor_recipe_did FOREIGN KEY(`doctorId`) REFERENCES `Doctor`(`doctorId`),
	CONSTRAINT fk_patient_recipe_pid FOREIGN KEY(`patientId`) REFERENCES `Patient`(`patientId`),
	CONSTRAINT fk_item_recipe_iid FOREIGN KEY(`itemId`) REFERENCES `Item`(`itemId`)
);