/*
 Navicat Premium Data Transfer

 Source Server         : MySQL57
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : precast_beam_system

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 23/06/2021 20:40:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for beam_info
-- ----------------------------
DROP TABLE IF EXISTS `beam_info`;
CREATE TABLE `beam_info`  (
  `beam_id` varchar(10) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `beam_kind` varchar(6) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `steel_type_1` varchar(5) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `steel_type_2` varchar(5) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `steel_type_3` varchar(5) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `length` double NULL DEFAULT NULL,
  `width` double NULL DEFAULT NULL,
  `radius` double NULL DEFAULT NULL,
  `height` double NULL DEFAULT NULL,
  `beam_state` varchar(10) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  PRIMARY KEY (`beam_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of beam_info
-- ----------------------------
INSERT INTO `beam_info` VALUES ('1', '长方体', 'A1', 'A2', 'A3', 5, 2, 0, 1, '存储');
INSERT INTO `beam_info` VALUES ('2', '圆柱体', 'A1', 'A2', '', 0, 0, 1, 5, '预处理');
INSERT INTO `beam_info` VALUES ('3', '长方体', 'A1', 'A2', '', 10, 4, 0, 1, '扎钢筋');
INSERT INTO `beam_info` VALUES ('4', '圆柱体', 'A1', 'A2', 'A3', 0, 0, 2, 5, '浇筑');
INSERT INTO `beam_info` VALUES ('5', '长方体', 'A1', 'A2', 'A3', 10, 4, 0, 1.5, '养护');

-- ----------------------------
-- Table structure for beam_store
-- ----------------------------
DROP TABLE IF EXISTS `beam_store`;
CREATE TABLE `beam_store`  (
  `beam_id` varchar(10) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `store_inspector` varchar(10) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `store_start` datetime NULL DEFAULT NULL,
  `shipment_expect` datetime NULL DEFAULT NULL,
  `shipment_actual` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`beam_id`) USING BTREE,
  CONSTRAINT `storeBeamId` FOREIGN KEY (`beam_id`) REFERENCES `beam_info` (`beam_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of beam_store
-- ----------------------------
INSERT INTO `beam_store` VALUES ('1', '周晨风', '2021-06-09 00:00:00', '2021-06-11 00:00:00', '2021-06-12 00:00:00');

-- ----------------------------
-- Table structure for curing_info
-- ----------------------------
DROP TABLE IF EXISTS `curing_info`;
CREATE TABLE `curing_info`  (
  `beam_id` varchar(10) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `curing_inspector` varchar(10) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `curing_start` datetime NULL DEFAULT NULL,
  `curing_finish` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`beam_id`) USING BTREE,
  CONSTRAINT `curingBeamId` FOREIGN KEY (`beam_id`) REFERENCES `beam_info` (`beam_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of curing_info
-- ----------------------------
INSERT INTO `curing_info` VALUES ('1', '汪奕琰', '2021-06-06 00:00:00', '2021-06-08 00:00:00');
INSERT INTO `curing_info` VALUES ('5', '陈佳语', '2021-06-27 00:00:00', '2021-06-29 00:00:00');

-- ----------------------------
-- Table structure for pouring_info
-- ----------------------------
DROP TABLE IF EXISTS `pouring_info`;
CREATE TABLE `pouring_info`  (
  `beam_id` varchar(10) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `pouring_inspector` varchar(10) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `pouring_start` datetime NULL DEFAULT NULL,
  `pouring_finish` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`beam_id`) USING BTREE,
  CONSTRAINT `pouringBeamId` FOREIGN KEY (`beam_id`) REFERENCES `beam_info` (`beam_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pouring_info
-- ----------------------------
INSERT INTO `pouring_info` VALUES ('1', '王瀚民', '2021-06-04 00:00:00', '2021-06-05 00:00:00');
INSERT INTO `pouring_info` VALUES ('4', '王瀚民', '2021-06-03 00:00:00', '2021-06-05 00:00:00');
INSERT INTO `pouring_info` VALUES ('5', '周晨风', '2021-06-25 00:00:00', '2021-06-26 00:00:00');

-- ----------------------------
-- Table structure for steel_info
-- ----------------------------
DROP TABLE IF EXISTS `steel_info`;
CREATE TABLE `steel_info`  (
  `steel_type` varchar(5) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `diameter` double NULL DEFAULT NULL,
  PRIMARY KEY (`steel_type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of steel_info
-- ----------------------------

-- ----------------------------
-- Table structure for tie_info
-- ----------------------------
DROP TABLE IF EXISTS `tie_info`;
CREATE TABLE `tie_info`  (
  `beam_id` varchar(10) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `wire_inspector` varchar(10) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `wire_start` datetime NULL DEFAULT NULL,
  `wire_finish` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`beam_id`) USING BTREE,
  CONSTRAINT `tieBeamId` FOREIGN KEY (`beam_id`) REFERENCES `beam_info` (`beam_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tie_info
-- ----------------------------
INSERT INTO `tie_info` VALUES ('1', '陈佳语', '2021-06-01 00:00:00', '2021-06-03 00:00:00');
INSERT INTO `tie_info` VALUES ('3', '陈佳语', '2021-06-17 00:00:00', '2021-06-19 00:00:00');
INSERT INTO `tie_info` VALUES ('4', '陈佳语', '2021-05-31 00:00:00', '2021-06-02 00:00:00');
INSERT INTO `tie_info` VALUES ('5', '汪奕琰', '2021-06-22 00:00:00', '2021-06-24 00:00:00');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(30) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `password` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `md5check` varchar(500) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1162189626@qq.om', 'T.S.REP1213', NULL);
INSERT INTO `users` VALUES ('imrobertchen@qq.com', 'T.S.RED1213', NULL);

SET FOREIGN_KEY_CHECKS = 1;
