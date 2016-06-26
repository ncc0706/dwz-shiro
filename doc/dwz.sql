/*
Navicat MySQL Data Transfer

Source Server         : ERP
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : dwz

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-06-27 01:09:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `login_log`
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `login_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `login_ip` varchar(19) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of login_log
-- ----------------------------
INSERT INTO `login_log` VALUES ('1', '2', '2016-06-27 00:58:09', '127.0.0.1');
INSERT INTO `login_log` VALUES ('2', '2', '2016-06-27 00:59:54', '127.0.0.1');

-- ----------------------------
-- Table structure for `sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_id` int(11) DEFAULT NULL,
  `dept_name` varchar(255) DEFAULT '',
  `pid` int(11) DEFAULT NULL,
  `is_end` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', null, '技术部', '1', null);
INSERT INTO `sys_dept` VALUES ('2', null, '后勤部', '1', null);

-- ----------------------------
-- Table structure for `sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_code` varchar(100) DEFAULT '',
  `permission_desc` varchar(100) DEFAULT '',
  `path` varchar(500) DEFAULT '',
  `pid` varchar(100) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_desc` varchar(255) DEFAULT NULL,
  `role_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '测00试', 'c001cc');
INSERT INTO `sys_role` VALUES ('2', '测试', 'c001');
INSERT INTO `sys_role` VALUES ('3', '测试', 'c002');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  `real_name` varchar(20) DEFAULT NULL,
  `id_card` varchar(18) DEFAULT NULL,
  `sex` tinyint(4) DEFAULT NULL,
  `age` tinyint(4) DEFAULT NULL,
  `is_use` tinyint(4) DEFAULT NULL,
  `birthday` timestamp NULL DEFAULT NULL,
  `join_time` timestamp NULL DEFAULT NULL,
  `left_time` timestamp NULL DEFAULT NULL,
  `job_lvl` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'admin', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('2', 'zhangsan', 'abc123', '张三', 'sdfasfa', '1', '23', '1', '2016-06-27 00:00:00', '2016-06-27 00:00:00', null, '1', '13220575514', '');

-- ----------------------------
-- Table structure for `sys_user_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_dept`;
CREATE TABLE `sys_user_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_dept
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
