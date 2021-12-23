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
	`dUsername` varchar(32) not null default '',
	`dPassword` varchar(32) not null default '',
	`dName` varchar(32) not null default '',
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
	`pUsername` varchar(32) not null default '',
	`pPassword` varchar(32) not null default '',
	`pIdentificationNum` varchar(18) not null default '',
	`pName` varchar(32) not null default '',
	`pGender` varchar(1),
	`pAge` varchar(3),
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
	`oType` varchar(10),
	`oTime` varchar(32),
	`oNum` int,
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
	CONSTRAINT fk_patient_regisitration_pid FOREIGN KEY(`patientId`) REFERENCES `Patient`(`patientId`),
	CONSTRAINT fk_doctor_regisitration_did FOREIGN KEY(`doctorId`) REFERENCES `Doctor`(`doctorId`)
);

-- ----------------------------
-- Table structure for History;
-- ----------------------------
DROP TABLE IF EXISTS `History`;
CREATE TABLE `History` (
	`historyId` bigint auto_increment,
	`patientId` bigint,
	`clinicType` varchar(32),
	`diseaseDate` varchar(10),
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
	`tag` int not null default 0,
	`itemName` varchar(32),
	`price` varchar(10),
	`usage` varchar(32),
	`remains` int,
	primary key(`itemId`),
	`haveDone` int not null default 0
);

-- ----------------------------
-- Table structure for Recipe
-- ----------------------------
DROP TABLE IF EXISTS `Recipe`;
CREATE TABLE Recipe (
	`recipeId` bigint auto_increment,
	`doctorId` bigint,
	`itemId` bigint,
	`dosage` varchar(32),
	`units` varchar(10),
	`frequency` varchar(10),
	`days` int,
	`skinTestRes` varchar(32),
	primary key(`recipeId`),
	CONSTRAINT fk_doctor_reipe_did FOREIGN KEY(`doctorId`) REFERENCES `DoctorId`(`doctorId`),
	CONSTRAINT fk_item_recipe_iid FOREIGN KEY(`itemId`) REFERENCES `Item`(`itemId`)
);