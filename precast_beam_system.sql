/*
 Navicat Premium Data Transfer

 Source Server         : MySQL Server
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : precast_beam_system

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 16/06/2021 23:15:02
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

SET FOREIGN_KEY_CHECKS = 1;
