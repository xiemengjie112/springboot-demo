/*
Navicat MySQL Data Transfer

Source Server         : 本地连接
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : student

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-12-02 19:43:00
*/

SET FOREIGN_KEY_CHECKS=0;

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
INSERT INTO `student` VALUES ('1', '111111', '测试1', '1');
INSERT INTO `student` VALUES ('1', '1111112', '测试2', '1');
INSERT INTO `student` VALUES ('1', '1111114', '测试3', '1');
INSERT INTO `student` VALUES ('1', '111113', '测试4', '1');
INSERT INTO `student` VALUES ('2', '123', '何一鸣5', '18');
INSERT INTO `student` VALUES ('1', '4644a60a-ab58-464b-8d54-88901b028b8e', '测试6', '1');
INSERT INTO `student` VALUES ('1', '5dd2f77b-c75e-4817-b0dc-6c1d9498b6d2', '测试7', '1');
INSERT INTO `student` VALUES ('1', '7c71cf61-8d7d-490c-aca5-fbdbcb5c55d3', '测试8', '1');
INSERT INTO `student` VALUES ('1', 'a123', '谢孟9', '16');
INSERT INTO `student` VALUES ('1', 'a8626590-1895-4210-b7b7-e02c91d6854c', '测试10', '1');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(10) DEFAULT NULL,
  `method` varchar(100) DEFAULT NULL,
  `params` varchar(100) DEFAULT NULL,
  `ip` varchar(50) DEFAULT NULL,
  `create_date` varchar(32) DEFAULT NULL,
  `operation` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1', null, null, null, null, '', null);
INSERT INTO `sys_log` VALUES ('2', 'aaa', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', '', '0:0:0:0:0:0:0:1', '2018-11-17 15:23:41', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('3', 'aaa', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', '', '0:0:0:0:0:0:0:1', '2018-11-17 15:24:45', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('4', 'aaa', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', '', '0:0:0:0:0:0:0:1', '2018-11-17 15:30:54', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('5', 'aaa', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', '', '0:0:0:0:0:0:0:1', '2018-11-17 15:31:59', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('6', 'aaa', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', '', '0:0:0:0:0:0:0:1', '2018-11-17 15:33:53', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('7', 'aaa', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', '', '0:0:0:0:0:0:0:1', '2018-11-17 15:34:20', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('8', 'aaa', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', '', '0:0:0:0:0:0:0:1', '2018-11-17 15:36:30', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('9', 'aaa', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', '', '0:0:0:0:0:0:0:1', '2018-11-17 15:37:08', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('10', 'aaa', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', '', '0:0:0:0:0:0:0:1', '2018-11-17 15:39:21', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('11', 'aaa', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', '', '0:0:0:0:0:0:0:1', '2018-11-17 15:41:06', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('12', 'aaa', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', '[\"xie\"]', '0:0:0:0:0:0:0:1', '2018-11-17 15:42:21', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('13', 'aaa', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', '{}', '0:0:0:0:0:0:0:1', '2018-11-17 15:48:09', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('14', 'aaa', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', '{}', '0:0:0:0:0:0:0:1', '2018-11-17 15:49:03', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('15', 'aaa', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', 'findAllStudent', '127.0.0.1', '2018-11-17 15:56:24', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('16', 'aaa', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', 'findAllStudent', '127.0.0.1', '2018-11-17 15:58:03', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('17', 'aaa', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', 'org.apache.catalina.connector.RequestFacade@3a8a948c', '127.0.0.1', '2018-11-17 16:00:01', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('18', 'aaa', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', 'org.apache.catalina.connector.RequestFacade@6b33e58e', '127.0.0.1', '2018-11-17 16:01:04', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('19', 'aaa', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', 'org.apache.catalina.connector.RequestFacade@39cf4c99', '127.0.0.1', '2018-11-17 16:01:50', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('20', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', '入参', '127.0.0.1', '2018-11-17 16:05:33', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('21', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', '{\"name\":[\"xie\"]}', '127.0.0.1', '2018-11-17 16:11:50', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('22', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', '{\"name\":[\"xie\"]}', '192.168.1.101', '2018-11-17 20:16:42', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('23', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', '{\"name\":[\"xie\"]}', '127.0.0.1', '2018-11-17 21:37:24', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('24', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.addStudents', '{}', '127.0.0.1', '2018-11-28 21:10:02', '批量插入学生信息');
INSERT INTO `sys_log` VALUES ('25', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.addStudents', '{}', '127.0.0.1', '2018-11-28 22:22:42', '批量插入学生信息');
INSERT INTO `sys_log` VALUES ('26', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', '{}', '127.0.0.1', '2018-12-01 16:23:32', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('27', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', '{}', '127.0.0.1', '2018-12-01 16:24:36', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('28', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', '{}', '127.0.0.1', '2018-12-01 16:25:38', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('29', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', '{}', '127.0.0.1', '2018-12-01 16:25:39', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('30', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', '{}', '127.0.0.1', '2018-12-01 16:25:39', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('31', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', '{}', '127.0.0.1', '2018-12-01 16:25:40', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('32', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', '{}', '127.0.0.1', '2018-12-01 16:25:40', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('33', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', '{}', '127.0.0.1', '2018-12-01 16:25:41', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('34', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', '{}', '127.0.0.1', '2018-12-01 16:25:42', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('35', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', '{}', '127.0.0.1', '2018-12-01 16:25:42', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('36', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', '{}', '127.0.0.1', '2018-12-01 16:25:43', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('37', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.addStudents', '{}', '127.0.0.1', '2018-12-01 16:28:52', '批量插入学生信息');
INSERT INTO `sys_log` VALUES ('38', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.addStudents', '{}', '127.0.0.1', '2018-12-01 16:29:09', '批量插入学生信息');
INSERT INTO `sys_log` VALUES ('39', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.findAllStudent', '{}', '127.0.0.1', '2018-12-01 18:23:35', '查询全部学生信息');
INSERT INTO `sys_log` VALUES ('40', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.studentsPageInfo', 'no params, casue no request found.', '127.0.0.1', '2018-12-01 20:28:41', '分页查询学生信息');
INSERT INTO `sys_log` VALUES ('41', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.studentsPageInfo', 'no params, casue no request found.', '127.0.0.1', '2018-12-01 20:30:12', '分页查询学生信息');
INSERT INTO `sys_log` VALUES ('42', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.studentsPageInfo', 'no params, casue no request found.', '127.0.0.1', '2018-12-01 20:31:02', '分页查询学生信息');
INSERT INTO `sys_log` VALUES ('43', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.studentsPageInfo', 'no params, casue no request found.', '127.0.0.1', '2018-12-01 20:34:47', '分页查询学生信息');
INSERT INTO `sys_log` VALUES ('44', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.studentsPageInfo', 'no params, casue no request found.', '127.0.0.1', '2018-12-01 20:36:34', '分页查询学生信息');
INSERT INTO `sys_log` VALUES ('45', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.studentsPageInfo', 'no params, casue no request found.', '127.0.0.1', '2018-12-01 20:37:59', '分页查询学生信息');
INSERT INTO `sys_log` VALUES ('46', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.studentsPageInfo', 'no params, casue no request found.', '127.0.0.1', '2018-12-01 20:38:01', '分页查询学生信息');
INSERT INTO `sys_log` VALUES ('47', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.studentsPageInfo', 'no params, casue no request found.', '127.0.0.1', '2018-12-01 20:38:03', '分页查询学生信息');
INSERT INTO `sys_log` VALUES ('48', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.studentsPageInfo', 'no params, casue no request found.', '127.0.0.1', '2018-12-01 20:38:04', '分页查询学生信息');
INSERT INTO `sys_log` VALUES ('49', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.studentsPageInfo', 'no params, casue no request found.', '127.0.0.1', '2018-12-01 20:38:05', '分页查询学生信息');
INSERT INTO `sys_log` VALUES ('50', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.studentsPageInfo', 'no params, casue no request found.', '127.0.0.1', '2018-12-01 20:38:10', '分页查询学生信息');
INSERT INTO `sys_log` VALUES ('51', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.studentsPageInfo', 'no params, casue no request found.', '127.0.0.1', '2018-12-01 20:38:17', '分页查询学生信息');
INSERT INTO `sys_log` VALUES ('52', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.studentsPageInfo', 'no params, casue no request found.', '127.0.0.1', '2018-12-01 20:38:32', '分页查询学生信息');
INSERT INTO `sys_log` VALUES ('53', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.studentsPageInfo', 'no params, casue no request found.', '127.0.0.1', '2018-12-01 20:41:26', '分页查询学生信息');
INSERT INTO `sys_log` VALUES ('54', '用户名', 'com.xmj.springbootdemo.Controller.StudentController.studentsPageInfo', 'no params, casue no request found.', '127.0.0.1', '2018-12-01 20:48:35', '分页查询学生信息');
