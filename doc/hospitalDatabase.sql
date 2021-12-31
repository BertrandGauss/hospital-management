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
-- Table structure for Registration
-- ----------------------------
DROP TABLE IF EXISTS `Registration`;
CREATE TABLE Registration (
	`registrationId` bigint auto_increment,
	`patientId` bigint,
	`doctorId` bigint,
	`rNum` varchar(32),
	primary key(`registrationId`),
	CONSTRAINT fk_patient_registration_pid FOREIGN KEY(`patientId`) REFERENCES `Patient`(`patientId`)
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
	`patientId` bigint,
	`doctorId` bigint,
	`itemName` varchar(32),
	`itemPrice` double,
    `skinTestRes` varchar(32),
    `itemHaveDone` int not null default 0,
    `havePay` int not null default 0,
	`illnessSummary` varchar(100),
	`checkArea` varchar(32),
	`notice` varchar(100),
	`checkTime` date,
	`checkRes` varchar(100),
	`opinion` varchar(100),
	primary key(`itemId`),
	CONSTRAINT fk_patient_item_pid FOREIGN KEY(`patientId`) REFERENCES `Patient`(`patientId`)
);

-- ----------------------------
-- Table structure for Medicine
-- ----------------------------
DROP TABLE IF EXISTS `Medicine`;
CREATE TABLE `Medicine` (
	`medName` varchar(32),
	`remains` int,
	primary key(`medName`)
);
-- ----------------------------
-- Records of medicine
-- ----------------------------
INSERT INTO `medicine` VALUES ('穿心莲内酯滴丸', '20');
INSERT INTO `medicine` VALUES ('连花清瘟胶囊', '15');
INSERT INTO `medicine` VALUES ('阿莫西林', '10');

-- ----------------------------
-- Table structure for Record
-- ----------------------------
DROP TABLE IF EXISTS `Record`;
CREATE TABLE `Record` (
	`recordId` bigint auto_increment,
	`medName` varchar(32),
	`doctorId` bigint,
	`patientId` bigint,
	`dosage` int,
	`units` varchar(10),
	`frequency` varchar(10),
	`days` int,
	`usage` varchar(32),
	`medPrice` double,
	`medHaveDone` int not null default 0,
    `havePay` int not null default 0,
	primary key(`recordId`),
	CONSTRAINT fk_patient_record_pid FOREIGN KEY(`patientId`) REFERENCES `Patient`(`patientId`),
	CONSTRAINT fk_medicine_record_mid FOREIGN KEY(`medName`) REFERENCES `Medicine`(`medName`)
);