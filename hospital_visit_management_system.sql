/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : hospital_visit_management_system

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 02/05/2024 22:11:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins`  (
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `admin_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `admin_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员名称',
  `gender` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '性别',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `admin_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录密码',
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '管理员信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES ('admin1', 1, '张三', '1', 45, '123456');
INSERT INTO `admins` VALUES ('admin2', 2, '李四', '0', 35, '123456');
INSERT INTO `admins` VALUES ('admin3', 3, '王五', '1', 50, '123456');
INSERT INTO `admins` VALUES ('admin4', 4, '孙六', '0', 40, '123456');
INSERT INTO `admins` VALUES ('admin5', 5, '赵七', '1', 45, '123456');
INSERT INTO `admins` VALUES ('admin6', 6, '黄八', '1', 56, '123456');
INSERT INTO `admins` VALUES ('admin7', 7, '裴九', '1', 76, '123456');
INSERT INTO `admins` VALUES ('admin8', 8, '徐十', '1', 65, '123456');
INSERT INTO `admins` VALUES ('admin9', 9, '赵十一', '1', 43, '123456');
INSERT INTO `admins` VALUES ('admin10', 10, '孙十二', '1', 54, '123456');
INSERT INTO `admins` VALUES ('admin1', 11, '张三', '1', 45, '123456');
INSERT INTO `admins` VALUES ('admin2', 12, '李四', '0', 35, '123456');
INSERT INTO `admins` VALUES ('admin3', 13, '王五', '1', 50, '123456');
INSERT INTO `admins` VALUES ('admin4', 14, '孙六', '0', 40, '123456');
INSERT INTO `admins` VALUES ('admin5', 15, '赵七', '1', 55, '123456');

-- ----------------------------
-- Table structure for appointments
-- ----------------------------
DROP TABLE IF EXISTS `appointments`;
CREATE TABLE `appointments`  (
  `appointment_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '预约ID',
  `patient_id` bigint(20) NOT NULL COMMENT '患者ID',
  `doctor_id` bigint(20) NOT NULL COMMENT '医生ID',
  `appointment_time` bigint(20) NOT NULL COMMENT '预约时间戳',
  `appointment_status` int(11) NOT NULL DEFAULT 0 COMMENT '预约状态',
  PRIMARY KEY (`appointment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '预约' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of appointments
-- ----------------------------
INSERT INTO `appointments` VALUES (1, 1, 1, 1616959180, 1);
INSERT INTO `appointments` VALUES (2, 2, 1, 1616959200, 1);
INSERT INTO `appointments` VALUES (3, 3, 1, 1616962800, 0);
INSERT INTO `appointments` VALUES (4, 4, 1, 1616966400, 1);
INSERT INTO `appointments` VALUES (5, 5, 1, 1616970000, 0);
INSERT INTO `appointments` VALUES (6, 6, 1, 1616951000, 0);
INSERT INTO `appointments` VALUES (7, 7, 1, 1616957800, 0);
INSERT INTO `appointments` VALUES (8, 8, 1, 1616970212, 0);
INSERT INTO `appointments` VALUES (9, 9, 1, 1616412000, 0);
INSERT INTO `appointments` VALUES (10, 10, 3, 1613412000, 0);
INSERT INTO `appointments` VALUES (11, 11, 1, 1611230000, 0);
INSERT INTO `appointments` VALUES (12, 12, 5, 1616790000, 0);
INSERT INTO `appointments` VALUES (13, 13, 6, 1613210000, 0);
INSERT INTO `appointments` VALUES (14, 14, 8, 1616974100, 0);
INSERT INTO `appointments` VALUES (15, 15, 1, 1613270000, 0);
INSERT INTO `appointments` VALUES (16, 1, 5, 1703865600000, 0);
INSERT INTO `appointments` VALUES (17, 1, 5, 1701792000000, 0);
INSERT INTO `appointments` VALUES (18, 5, 5, 1703779200000, 0);

-- ----------------------------
-- Table structure for doctor_schedule
-- ----------------------------
DROP TABLE IF EXISTS `doctor_schedule`;
CREATE TABLE `doctor_schedule`  (
  `schedule_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '时间表ID',
  `doctor_id` bigint(20) NOT NULL COMMENT '医生ID',
  `time_start` bigint(20) NOT NULL COMMENT '上班时间戳',
  `time_end` bigint(20) NOT NULL COMMENT '下班时间戳',
  PRIMARY KEY (`schedule_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '记录医生的工作时间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of doctor_schedule
-- ----------------------------
INSERT INTO `doctor_schedule` VALUES (1, 1, 123, 1233);
INSERT INTO `doctor_schedule` VALUES (2, 2, 1616966400, 1616977200);
INSERT INTO `doctor_schedule` VALUES (3, 3, 1616977200, 1616988000);
INSERT INTO `doctor_schedule` VALUES (4, 4, 1616988000, 1616998800);
INSERT INTO `doctor_schedule` VALUES (5, 5, 1616998800, 1617009600);
INSERT INTO `doctor_schedule` VALUES (6, 6, 1616998800, 1617009600);
INSERT INTO `doctor_schedule` VALUES (7, 7, 1616998800, 1617009600);
INSERT INTO `doctor_schedule` VALUES (8, 8, 1616998800, 1617009600);
INSERT INTO `doctor_schedule` VALUES (9, 9, 1616998800, 1617009600);
INSERT INTO `doctor_schedule` VALUES (10, 10, 1616998800, 1617009600);
INSERT INTO `doctor_schedule` VALUES (11, 11, 1616998800, 1617009600);
INSERT INTO `doctor_schedule` VALUES (12, 12, 1616998800, 1617009600);
INSERT INTO `doctor_schedule` VALUES (13, 13, 1616998800, 1617009600);
INSERT INTO `doctor_schedule` VALUES (14, 14, 1616998800, 1617009600);
INSERT INTO `doctor_schedule` VALUES (15, 15, 1616998800, 1617009600);

-- ----------------------------
-- Table structure for doctors
-- ----------------------------
DROP TABLE IF EXISTS `doctors`;
CREATE TABLE `doctors`  (
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `doctor_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '医生ID',
  `doctor_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '性别',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `education` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学历',
  `doctor_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `department` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '科室',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`doctor_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '医生信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of doctors
-- ----------------------------
INSERT INTO `doctors` VALUES ('doctor1', 1, '八重神子', '1', 50, 'MD', '1234', 'Cardiology', NULL);
INSERT INTO `doctors` VALUES ('doctor2', 2, '和真', '0', 45, 'MD', '123456', 'Neurology', NULL);
INSERT INTO `doctors` VALUES ('doctor3', 3, '雾岛董香', '1', 40, 'MD', '123456', 'Orthopedics', NULL);
INSERT INTO `doctors` VALUES ('doctor4', 4, '阿库娅', '0', 35, 'MD', '123456', 'Pediatrics', NULL);
INSERT INTO `doctors` VALUES ('doctor5', 5, '惠惠', '1', 60, 'MD', '123456', 'General Surgery', NULL);
INSERT INTO `doctors` VALUES ('doctor1', 6, '叶凡', '1', 50, 'MD', '123456', 'Cardiology', NULL);
INSERT INTO `doctors` VALUES ('doctor2', 7, '石昊', '0', 45, 'MD', '123456', 'Neurology', NULL);
INSERT INTO `doctors` VALUES ('doctor3', 8, '唐三', '1', 40, 'MD', '123456', 'Orthopedics', NULL);
INSERT INTO `doctors` VALUES ('doctor4', 9, '萧炎', '0', 35, 'MD', '123456', 'Pediatrics', NULL);
INSERT INTO `doctors` VALUES ('doctor11', 11, '悠悠', '1', 50, 'MD', '123456', 'Cardiology', NULL);
INSERT INTO `doctors` VALUES ('doctor12', 12, '洛琪希', '0', 45, 'MD', '123456', 'Neurology', NULL);
INSERT INTO `doctors` VALUES ('doctor13', 13, '爱丽丝', '1', 40, 'MD', '123456', 'Orthopedics', NULL);
INSERT INTO `doctors` VALUES ('123', 16, '123', '123', 123, '123', '123', '123', NULL);
INSERT INTO `doctors` VALUES ('123', 17, '123', '123', 123, '123', '123', '123', NULL);

-- ----------------------------
-- Table structure for illness
-- ----------------------------
DROP TABLE IF EXISTS `illness`;
CREATE TABLE `illness`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `patient_id` bigint(20) NULL DEFAULT NULL COMMENT '患者id',
  `illness_time` bigint(20) NULL DEFAULT NULL COMMENT '患病时间',
  `illness` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '病情',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of illness
-- ----------------------------
INSERT INTO `illness` VALUES (1, 1, 1616998800, '脑血栓');
INSERT INTO `illness` VALUES (2, 2, 1616218800, '先心病');
INSERT INTO `illness` VALUES (3, 3, 16134998800, '终末期肾病');
INSERT INTO `illness` VALUES (4, 4, 1616128800, '宫颈癌');
INSERT INTO `illness` VALUES (5, 5, 1703751407298, '耐药肺结核');
INSERT INTO `illness` VALUES (6, 6, 1126998800, '艾滋病 机会性感染');
INSERT INTO `illness` VALUES (7, 7, 1647598800, '血友病');
INSERT INTO `illness` VALUES (8, 8, 1612398800, '慢性粒细胞白血病');
INSERT INTO `illness` VALUES (9, 9, 1645798800, '唇腭裂');
INSERT INTO `illness` VALUES (10, 10, 1123998800, '肺癌');
INSERT INTO `illness` VALUES (11, 11, 1560998800, '食道癌');
INSERT INTO `illness` VALUES (12, 12, 1123998800, '胃癌');
INSERT INTO `illness` VALUES (13, 13, 1614298800, 'I型糖尿病');
INSERT INTO `illness` VALUES (14, 14, 1654998800, '甲亢');
INSERT INTO `illness` VALUES (15, 15, 1667998800, '脑梗死');
INSERT INTO `illness` VALUES (24, 123, 1703755359144, '123');
INSERT INTO `illness` VALUES (25, 54, 1703755648164, '45');
INSERT INTO `illness` VALUES (26, 123, 1703756730995, '123');
INSERT INTO `illness` VALUES (27, 56, 1703756955591, '1');
INSERT INTO `illness` VALUES (28, 57, 1703757090692, '4');
INSERT INTO `illness` VALUES (29, 58, 1703757190896, '32');
INSERT INTO `illness` VALUES (30, 16, 1703812469987, '感冒');

-- ----------------------------
-- Table structure for messages
-- ----------------------------
DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages`  (
  `message_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '留言ID',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '留言内容',
  `sender_id` bigint(20) NOT NULL COMMENT '发送者ID',
  `receiver_id` bigint(20) NOT NULL COMMENT '接收者ID',
  `message_time` bigint(20) NULL DEFAULT NULL COMMENT '留言时间，默认当前时间',
  `messages_status` int(11) NOT NULL COMMENT '留言状态',
  PRIMARY KEY (`message_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '留言' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of messages
-- ----------------------------
INSERT INTO `messages` VALUES (1, '你在么，医生', 1, 2, 1616955600, 1);
INSERT INTO `messages` VALUES (2, '我们明天能见一面么', 2, 3, 1616959200, 1);
INSERT INTO `messages` VALUES (3, '我的手术明天能做么', 3, 1, 1616962800, 0);
INSERT INTO `messages` VALUES (4, '请你等待通知', 1, 2, 1616966400, 1);
INSERT INTO `messages` VALUES (5, '你的检查结果出来了', 2, 3, 1616970000, 0);
INSERT INTO `messages` VALUES (6, '这药你不能不吃，不然病好不了', 1, 2, 1616955600, 1);
INSERT INTO `messages` VALUES (7, '医生，这药能不能找点便宜的先吃着？', 2, 3, 1616959200, 1);
INSERT INTO `messages` VALUES (8, '我不想治了啊，医生', 3, 1, 1616962800, 0);
INSERT INTO `messages` VALUES (9, '好疼啊，有没有止痛药啊', 1, 2, 1616966400, 1);
INSERT INTO `messages` VALUES (10, '医生，我还有希望么', 2, 3, 1616970000, 0);
INSERT INTO `messages` VALUES (11, '这药好贵啊，我能不吃么', 1, 2, 1616955600, 1);
INSERT INTO `messages` VALUES (12, '我没吃饭，就是喝了点粥', 2, 3, 1616959200, 1);
INSERT INTO `messages` VALUES (13, '我想吃点东西，可以么，医生', 3, 1, 1616962800, 0);
INSERT INTO `messages` VALUES (14, '给我点建议吧，医生', 1, 2, 1616966400, 1);
INSERT INTO `messages` VALUES (15, '我能无痛苦的走么，医生', 2, 3, 1616970000, 0);
INSERT INTO `messages` VALUES (17, '1', 1, 1, 1703743244748, 0);
INSERT INTO `messages` VALUES (18, '1', 1, 2, 1703743257639, 0);
INSERT INTO `messages` VALUES (19, '123', 1, 5, 1703777192049, 0);
INSERT INTO `messages` VALUES (20, '111', 5, 1, 1703795877160, 0);
INSERT INTO `messages` VALUES (21, '1123', 1, 0, 1703812509257, 0);
INSERT INTO `messages` VALUES (22, '11213123', 5, 4, 1703813089863, 0);
INSERT INTO `messages` VALUES (23, '1231413', 5, 4, 1703813114282, 0);
INSERT INTO `messages` VALUES (24, '1231413', 5, 4, 1703813114958, 0);

-- ----------------------------
-- Table structure for patients
-- ----------------------------
DROP TABLE IF EXISTS `patients`;
CREATE TABLE `patients`  (
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `patient_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '患者ID',
  `patient_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '患者名称',
  `gender` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '性别',
  `age` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '年龄',
  `patient_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录密码',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`patient_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '患者信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of patients
-- ----------------------------
INSERT INTO `patients` VALUES ('patient5', 5, '李火旺', '男', '35', '123456', '1');
INSERT INTO `patients` VALUES ('patient6', 6, '古月方源', '男', '45', '123456', '1');
INSERT INTO `patients` VALUES ('patient7', 7, '齐无惑', '男', '30', '123456', '1');
INSERT INTO `patients` VALUES ('patient8', 8, '李长生', '女', '30', '123456', '1');
INSERT INTO `patients` VALUES ('patient9', 9, '韩跑跑', '男', '25', '123456', '1');
INSERT INTO `patients` VALUES ('patient10', 10, '石毅', '女', '40', '123456', '1');
INSERT INTO `patients` VALUES ('patient11', 11, '石昊', '男', '35', '123456', '1');
INSERT INTO `patients` VALUES ('patient12', 12, '月摇', '女', '45', '123456', '1');
INSERT INTO `patients` VALUES ('patient13', 13, '洛青舟', '男', '30', '123456', '1');
INSERT INTO `patients` VALUES ('patient14', 14, '季无忧', '女', '30', '123456', '1');
INSERT INTO `patients` VALUES ('patient15', 15, '孟浩', '男', '25', '123456', '1');

SET FOREIGN_KEY_CHECKS = 1;
