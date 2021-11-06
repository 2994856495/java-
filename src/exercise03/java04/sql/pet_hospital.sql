/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : pet_hospital

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 22/06/2021 23:24:35
*/
CREATE database if not EXISTS pet_hospital;
use pet_hospital;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for check
-- ----------------------------
DROP TABLE IF EXISTS `check`;
CREATE TABLE `check`  (
  `check_record` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `check_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `pet_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `host_tele` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`check_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of check
-- ----------------------------
INSERT INTO `check` VALUES ('ddee', 'edfd', 'rdfer', NULL);
INSERT INTO `check` VALUES ('s', 'sssd', 's', 's');
INSERT INTO `check` VALUES ('w权威的', 'Tue Jun 22 21:18:03 CST 2021', '请问', 'q请勿');

-- ----------------------------
-- Table structure for pet
-- ----------------------------
DROP TABLE IF EXISTS `pet`;
CREATE TABLE `pet`  (
  `pet_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `pet_species` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `pet_birthday` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `pet_variety` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `pet_weigth` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `host_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `host_tele` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`pet_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pet
-- ----------------------------
INSERT INTO `pet` VALUES ('12342', '213243', '42534324453', '32453', '32453', '34253632', '543');
INSERT INTO `pet` VALUES ('s', 'ss', 's', 'ss', 'sss', 's', 's');
INSERT INTO `pet` VALUES ('ss', 'dd', 'sd', 'dsd', '23', 'sd', '234');
INSERT INTO `pet` VALUES ('sss', 'ded', 'ewedw', 'sdefd', '12324', '1324w', '243');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `usr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `passwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`usr`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('11212', 'wewew1212');
INSERT INTO `user` VALUES ('12234', '1234rsdfd');
INSERT INTO `user` VALUES ('1w2eew', 'efdv1232');
INSERT INTO `user` VALUES ('admin', 'abcd1234');
INSERT INTO `user` VALUES ('DATABASE', 'abcd1234');
INSERT INTO `user` VALUES ('ddv', 'abcd1234');
INSERT INTO `user` VALUES ('eerd', 'fdfd111ee');
INSERT INTO `user` VALUES ('erf', 'ewretryr11');
INSERT INTO `user` VALUES ('EVENT', 'abcd1234');
INSERT INTO `user` VALUES ('FOREIGN', 'abcd1234');
INSERT INTO `user` VALUES ('jack', 'abcd1234');
INSERT INTO `user` VALUES ('li', 'abcd1234');
INSERT INTO `user` VALUES ('mary', 'abcd1234');
INSERT INTO `user` VALUES ('mike', 'abcd1234');
INSERT INTO `user` VALUES ('qian', 'abcd1234');
INSERT INTO `user` VALUES ('qwe', 'wertyre232');
INSERT INTO `user` VALUES ('red', 'abcd1234');
INSERT INTO `user` VALUES ('sun', 'abcd1234');
INSERT INTO `user` VALUES ('UPDATE', 'abcd1234');
INSERT INTO `user` VALUES ('VARCHAR', 'abcd1234');
INSERT INTO `user` VALUES ('VIEW', 'abcd1234');
INSERT INTO `user` VALUES ('wang', 'abcd1234');
INSERT INTO `user` VALUES ('WHERE', 'abcd1234');
INSERT INTO `user` VALUES ('wu', 'abcd1234');
INSERT INTO `user` VALUES ('ww', 'weeewerf2');
INSERT INTO `user` VALUES ('zhao', 'abcd1234');
INSERT INTO `user` VALUES ('zheng', 'abcd1234');
INSERT INTO `user` VALUES ('zhou', 'abcd1234');

SET FOREIGN_KEY_CHECKS = 1;
