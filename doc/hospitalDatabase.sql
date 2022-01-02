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
	`isValid` int not null default 0,
	`dName` varchar(32) not null default '',
	`dIdentificationNum` varchar(18) not null default '',
	`dOffice` varchar(32) not null default '',
	`dTitle` varchar(32),
	`dSkill` varchar(100),
	`patientId` bigint,
	primary key(`doctorId`)
);

-- ----------------------------
-- Insert values into Doctor
-- ----------------------------
# INSERT INTO `Doctor` (`dUsername`, `dPassword`, `isValid`, `dName`, `dIdentificationNum`, `dOffice`, `dTitle`)
# VALUES ('doctor1', 'a123456', 1, '张一', '234569833647390534', '外科', '专家');
#
# INSERT INTO `Doctor` (`dUsername`, `dPassword`, `isValid`, `dName`, `dIdentificationNum`, `dOffice`, `dTitle`)
# VALUES ('doctor2', 'b123456', 0, '张二', '234569473647390534', '外科', '普通');
#
# INSERT INTO `Doctor` (`dUsername`, `dPassword`, `isValid`, `dName`, `dIdentificationNum`, `dOffice`, `dTitle`)
# VALUES ('doctor3', 'c123456', 1, '张三', '234569833647090534', '内科', '普通');
#
# INSERT INTO `Doctor` (`dUsername`, `dPassword`, `isValid`, `dName`, `dIdentificationNum`, `dOffice`, `dTitle`)
# VALUES ('doctor4', 'd123456', 1, '张四', '234569833677390534', '内科', '专家');
#
# INSERT INTO `Doctor` (`dUsername`, `dPassword`, `isValid`, `dName`, `dIdentificationNum`, `dOffice`, `dTitle`)
# VALUES ('doctor5', 'e123456', 0, '张五', '234569836147390534', '内科', '普通');
#
# INSERT INTO `Doctor` (`dUsername`, `dPassword`, `isValid`, `dName`, `dIdentificationNum`, `dOffice`, `dTitle`)
# VALUES ('doctor6', 'f123456', 0, '张六', '234569403647390534', '外科', '专家');
#
# INSERT INTO `Doctor` (`dUsername`, `dPassword`, `isValid`, `dName`, `dIdentificationNum`, `dOffice`, `dTitle`)
# VALUES ('doctor7', 'g123456', 1, '张七', '234529403647399834', '外科', '专家');
#
# INSERT INTO `Doctor` (`dUsername`, `dPassword`, `isValid`, `dName`, `dIdentificationNum`, `dOffice`, `dTitle`)
# VALUES ('doctor8', 'h123456', 0, '张八', '114569403647390534', '外科', '普通');
#
# INSERT INTO `Doctor` (`dUsername`, `dPassword`, `isValid`, `dName`, `dIdentificationNum`, `dOffice`, `dTitle`)
# VALUES ('doctor9', 'i123456', 1, '张九', '344569403647390534', '内科', '普通');
#
# INSERT INTO `Doctor` (`dUsername`, `dPassword`, `isValid`, `dName`, `dIdentificationNum`, `dOffice`, `dTitle`)
# VALUES ('doctor10', 'j123456', 1, '张十', '234569406473945040', '外科', '普通');


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
-- Insert values into Patient
-- ----------------------------
# INSERT INTO `Patient` (`pUsername`, `pPassword`, `pIdentificationNum`, `pName`, `pGender`, `pAge`, `pOccupation`, `pPhone`, `pAddress`)
# VALUES ('patient1', 'a123', '098647799567035486', '李一', 'F', 23, '学生', '13485764984', '重庆');
#
# INSERT INTO `Patient` (`pUsername`, `pPassword`, `pIdentificationNum`, `pName`, `pGender`, `pAge`, `pOccupation`, `pPhone`, `pAddress`)
# VALUES ('patient2', 'b123', '098647796583035486', '李二', 'M', 24, '学生', '13485764984', '成都');
#
# INSERT INTO `Patient` (`pUsername`, `pPassword`, `pIdentificationNum`, `pName`, `pGender`, `pAge`, `pOccupation`, `pPhone`, `pAddress`)
# VALUES ('patient3', 'c123', '098647790955035486', '李三', 'F', 20, '学生', '13485764984', '重庆');
#
# INSERT INTO `Patient` (`pUsername`, `pPassword`, `pIdentificationNum`, `pName`, `pGender`, `pAge`, `pOccupation`, `pPhone`, `pAddress`)
# VALUES ('patient4', 'd123', '098642347567035486', '李四', 'M', 53, '老师', '13485764984', '北京');
#
# INSERT INTO `Patient` (`pUsername`, `pPassword`, `pIdentificationNum`, `pName`, `pGender`, `pAge`, `pOccupation`, `pPhone`, `pAddress`)
# VALUES ('patient5', 'e123', '098647346067035486', '李五', 'F', 70, '兽医', '13485764984', '成都');
#
# INSERT INTO `Patient` (`pUsername`, `pPassword`, `pIdentificationNum`, `pName`, `pGender`, `pAge`, `pOccupation`, `pPhone`, `pAddress`)
# VALUES ('patient6', 'f123', '098647799567997686', '李六', 'F', 54, '白领', '13485764984', '张家口');
#
# INSERT INTO `Patient` (`pUsername`, `pPassword`, `pIdentificationNum`, `pName`, `pGender`, `pAge`, `pOccupation`, `pPhone`, `pAddress`)
# VALUES ('patient7', 'g123', '098647799567038666', '李七', 'M', 13, '白领', '13485764984', '重庆');
#
# INSERT INTO `Patient` (`pUsername`, `pPassword`, `pIdentificationNum`, `pName`, `pGender`, `pAge`, `pOccupation`, `pPhone`, `pAddress`)
# VALUES ('patient8', 'h123', '012327799567035486', '李八', 'M', 49, '学生', '13485764984', '武汉');
#
# INSERT INTO `Patient` (`pUsername`, `pPassword`, `pIdentificationNum`, `pName`, `pGender`, `pAge`, `pOccupation`, `pPhone`, `pAddress`)
# VALUES ('patient9', 'i123', '880447799567035486', '李九', 'M', 73, '白领', '13485764984', '重庆');
#
# INSERT INTO `Patient` (`pUsername`, `pPassword`, `pIdentificationNum`, `pName`, `pGender`, `pAge`, `pOccupation`, `pPhone`, `pAddress`)
# VALUES ('patient10', 'j123', '224847799567035486', '李十', 'F', 62, '老师', '13485764984', '南昌');



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
-- Insert values into Order
-- ----------------------------
INSERT INTO `Order` (`patientId`, `department`, `doctorId`, `oType`, `oDate`, `oTime`)
VALUES (1, '外科', '4', '专家预约', '2021-12-30', '10:00:00');

INSERT INTO `Order` (`patientId`, `department`, `doctorId`, `oType`, `oDate`, `oTime`)
VALUES (1, '外科', '8', '科室预约', '2022-01-01', '15:00:00');

INSERT INTO `Order` (`patientId`, `department`, `doctorId`, `oType`, `oDate`, `oTime`)
VALUES (2, '外科', '2', '科室预约', '2021-01-01', '15:00:00');



-- ----------------------------
-- Table structure for Registration
-- ----------------------------
DROP TABLE IF EXISTS `Registration`;
CREATE TABLE Registration (
	`registrationId` bigint auto_increment,
	`patientId` bigint,
	`doctorId` bigint,
	`department` varchar(32),
	`rNum` varchar(32),
	`isValid` int not null default 1,
	primary key(`registrationId`),
	CONSTRAINT fk_patient_registration_pid FOREIGN KEY(`patientId`) REFERENCES `Patient`(`patientId`)
);

# INSERT INTO `Registration` (patientId, doctorId, rNum) VALUES (1, 8, 'v1');
# INSERT INTO `Registration` (patientId, doctorId, rNum) VALUES (5, 2, '1');
# INSERT INTO `Registration` (patientId, doctorId, rNum) VALUES (3, 4, '2');
# INSERT INTO `Registration` (patientId, doctorId, rNum) VALUES (6, 4, '3');
# INSERT INTO `Registration` (patientId, doctorId, rNum) VALUES (2, 2, 'v2');


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

INSERT INTO `History` (patientId, pIdentificationNum, clinicType, diseaseDate, diagnosis, drugAllergyHis, chiefComplaint, presentIllness)
VALUES (1, '098647799567035486', '发热', '2021-12-20', '感冒', '无', '无', '无');

INSERT INTO `History` (patientId, pIdentificationNum, clinicType, diseaseDate, diagnosis, drugAllergyHis, chiefComplaint, presentIllness)
VALUES (1, '098647799567035486', '头疼', '2021-12-28', '过度劳累', '无', '无', '无');

INSERT INTO `History` (patientId, pIdentificationNum, clinicType, diseaseDate, diagnosis, drugAllergyHis, chiefComplaint, presentIllness)
VALUES (2, '098647796583035486', '关节疼痛', '2021-12-08', '关节炎', '无', '无', '无');

INSERT INTO `History` (patientId, pIdentificationNum, clinicType, diseaseDate, diagnosis, drugAllergyHis, chiefComplaint, presentIllness)
VALUES (3, '098647790955035486', '胸闷气短', '2022-01-01', '癌症', '无', '无', '无');

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
	`department`  varchar(32),
	`checkArea` varchar(32),
	`notice` varchar(100),
	`checkTime` date,
	`checkRes` varchar(100),
	`opinion` varchar(100),
	primary key(`itemId`),
	CONSTRAINT fk_patient_item_pid FOREIGN KEY(`patientId`) REFERENCES `Patient`(`patientId`),
  CONSTRAINT fk_doctor_item_did FOREIGN KEY(`doctorId`) REFERENCES `Doctor`(`doctorId`)
);

INSERT INTO `Item` (patientId, doctorId, itemName, itemPrice, skinTestRes, illnessSummary, department, checkArea, notice, checkTime, checkRes, opinion)
VALUES (1, 8, '核磁共振', 128.4, '', '无', '外科', '头部', '饮食清淡', '2022-01-01', '正常', '多喝热水');

INSERT INTO `Item` (patientId, doctorId, itemName, itemPrice, skinTestRes, illnessSummary, department, checkArea, notice, checkTime, checkRes, opinion)
VALUES (1, 8, 'CT', 94.5, '', '无', '外科', '胸部', '饮食清淡，禁辛辣', '2022-01-01', '正常', '多喝热水');

INSERT INTO `Item` (patientId, doctorId, itemName, itemPrice, skinTestRes, illnessSummary, department, checkArea, notice, checkTime, checkRes, opinion)
VALUES (1, 8, '验血', 49, '轻微炎症', '无', '外科', '血液', '饮食清淡，合理膳食', '2022-01-01', '较正常', '多喝热水');

INSERT INTO `Item` (patientId, doctorId, itemName, itemPrice, skinTestRes, illnessSummary, department, checkArea, notice, checkTime, checkRes, opinion)
VALUES (3, 4, '验血', 49, '炎症', '无', '内科', '血液', '禁烟酒', '2022-01-02', '较严重', '建议住院观察');

INSERT INTO `Item` (patientId, doctorId, itemName, itemPrice, skinTestRes, illnessSummary, department, checkArea, notice, checkTime, checkRes, opinion)
VALUES (3, 4, '肾透析', 280, '', '无', '内科', '肾', '饮食清淡，合理膳食，禁烟酒', '2022-01-02', '不正常', '建议手术');

INSERT INTO `Item` (patientId, doctorId, itemName, itemPrice, skinTestRes, illnessSummary, department, checkArea, notice, checkTime, checkRes, opinion)
VALUES (2, 2, '心电图', 199.8, '', '无', '外科', '头部', '注意饮食', '2022-01-02', '正常', '多喝热水');

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
INSERT INTO `medicine` VALUES ('999感冒灵颗粒', '5');

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
  CONSTRAINT fk_doctor_record_did FOREIGN KEY(`doctorId`) REFERENCES `Doctor`(`doctorId`),
	CONSTRAINT fk_medicine_record_mid FOREIGN KEY(`medName`) REFERENCES `Medicine`(`medName`)
);
INSERT INTO `Record` (medName, doctorId, patientId, dosage, units, frequency, days, `usage`, medPrice)
VALUES ('穿心莲内酯滴丸', 8, 1, 2, '盒', '一日三次', 3, '口服', '25.5');

INSERT INTO `Record` (medName, doctorId, patientId, dosage, units, frequency, days, `usage`, medPrice)
VALUES ('阿莫西林', 8, 1, 1, '盒', '一日两次', 3, '口服', '19.8');

INSERT INTO `Record` (medName, doctorId, patientId, dosage, units, frequency, days, `usage`, medPrice)
VALUES ('连花清瘟胶囊', 4, 3, 3, '盒', '一日三次', 5, '口服', '59');

INSERT INTO `Record` (medName, doctorId, patientId, dosage, units, frequency, days, `usage`, medPrice)
VALUES ('999感冒灵颗粒', 2, 3, 1, '盒', '一日三次', 3, '口服', '12.5');


-- ----------------------------
-- Table structure for Trace
-- ----------------------------
DROP TABLE IF EXISTS `Trace`;
CREATE TABLE `Trace` (
	`traceId` bigint auto_increment,
	`patientId` bigint,
	`isValid` int not null default 0,
	`state` int not null default 0,
	primary key(`traceId`),
	CONSTRAINT fk_patient_trace_pid FOREIGN KEY(`patientId`) REFERENCES `Patient`(`patientId`)
);