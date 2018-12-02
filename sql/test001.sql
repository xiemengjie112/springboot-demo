/*
Navicat MySQL Data Transfer

Source Server         : 本地连接
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : test001

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-12-02 19:43:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `dr` int(11) DEFAULT NULL,
  `pk_order` varchar(32) NOT NULL,
  `create_date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pk_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('1', '111', '2018-11-9 21:15:02');
INSERT INTO `order` VALUES ('1', '222', '2018-11-9 21:15:16');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `dr` int(2) DEFAULT NULL,
  `pk_product` varchar(32) NOT NULL,
  `pk_order` varchar(32) DEFAULT NULL,
  `num` int(20) DEFAULT NULL,
  PRIMARY KEY (`pk_product`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '111', '111', '0');
INSERT INTO `product` VALUES ('1', '222', '111', '0');
INSERT INTO `product` VALUES ('1', '333', '222', '0');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `dr` varchar(255) NOT NULL COMMENT '状态',
  `pk_student` varchar(255) NOT NULL DEFAULT '' COMMENT '主键',
  `student_name` varchar(255) DEFAULT NULL COMMENT '名称',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`pk_student`),
  UNIQUE KEY `学生主键索引` (`pk_student`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', 'a123456', '何一鸣', '10');
