/*
Navicat MySQL Data Transfer

Source Server         : niyaowoa
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : goods

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-04-28 16:39:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for good
-- ----------------------------
DROP TABLE IF EXISTS `good`;
CREATE TABLE `good` (
  `good_code` int(11) NOT NULL COMMENT '物品编码',
  `good_pid` varchar(10) DEFAULT NULL COMMENT '物品分类码',
  `good_name` varchar(20) DEFAULT NULL COMMENT '物品名称',
  `good_unit` varchar(20) DEFAULT NULL COMMENT '物品单位',
  `address` varchar(50) DEFAULT NULL COMMENT '物品位置',
  `good_value` int(11) DEFAULT NULL COMMENT '物品价格',
  `good_photo` varchar(50) DEFAULT NULL COMMENT '物品图片链接',
  `good_remarks` varchar(50) DEFAULT NULL COMMENT '备注',
  `good_num` int(11) DEFAULT '0' COMMENT '数量',
  PRIMARY KEY (`good_code`),
  KEY `pid` (`good_pid`),
  CONSTRAINT `pid` FOREIGN KEY (`good_pid`) REFERENCES `good_category` (`pid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of good
-- ----------------------------
INSERT INTO `good` VALUES ('1000', '1214', '米文辉', '头', '厕所', '100', 'photos/1000.jpg', null, '300');

-- ----------------------------
-- Table structure for good_category
-- ----------------------------
DROP TABLE IF EXISTS `good_category`;
CREATE TABLE `good_category` (
  `pid` varchar(10) NOT NULL COMMENT '物品类别编码',
  `name` varchar(20) DEFAULT NULL COMMENT '物品类名',
  `message` varchar(50) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of good_category
-- ----------------------------
INSERT INTO `good_category` VALUES ('1214', '床上用品', '湘潭大学');
INSERT INTO `good_category` VALUES ('1215', '书籍类', '书本等');

-- ----------------------------
-- Table structure for good_stackin
-- ----------------------------
DROP TABLE IF EXISTS `good_stackin`;
CREATE TABLE `good_stackin` (
  `batchcode` varchar(10) NOT NULL COMMENT '入库批次',
  `good_code` int(11) DEFAULT NULL COMMENT '物品编号',
  `num` int(11) DEFAULT NULL COMMENT '物品数量',
  `creator_id` int(11) DEFAULT NULL COMMENT '操作人',
  `create_time` datetime DEFAULT NULL COMMENT '操作时间',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `storage` int(11) DEFAULT NULL COMMENT '仓库编码',
  PRIMARY KEY (`batchcode`),
  KEY `good_stackin_ibfk_1` (`good_code`),
  KEY `good_stackin_ibfk_3` (`storage`),
  KEY `good_stackin_ibfk_2` (`creator_id`),
  CONSTRAINT `good_stackin_ibfk_1` FOREIGN KEY (`good_code`) REFERENCES `good` (`good_code`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `good_stackin_ibfk_2` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `good_stackin_ibfk_3` FOREIGN KEY (`storage`) REFERENCES `storage` (`storage_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of good_stackin
-- ----------------------------
INSERT INTO `good_stackin` VALUES ('1000', '1000', '100', '25', '2017-04-25 19:40:19', null, '1000');
INSERT INTO `good_stackin` VALUES ('1001', '1000', '200', '21', '2017-04-27 02:01:44', '进货', '1001');

-- ----------------------------
-- Table structure for good_stackout
-- ----------------------------
DROP TABLE IF EXISTS `good_stackout`;
CREATE TABLE `good_stackout` (
  `batchcode` varchar(10) NOT NULL COMMENT '出库批次',
  `good_code` int(11) DEFAULT NULL COMMENT '物品编号',
  `num` int(11) DEFAULT NULL COMMENT '物品数量',
  `creator_id` int(11) DEFAULT NULL COMMENT '操作人',
  `create_time` datetime DEFAULT NULL COMMENT '操作时间',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注  出库原因之类的',
  `storage` int(11) DEFAULT NULL COMMENT '仓库编码',
  PRIMARY KEY (`batchcode`),
  KEY `good_stackout_ibfk_1` (`good_code`),
  KEY `good_stackout_ibfk_2` (`creator_id`),
  KEY `good_stackout_ibfk_3` (`storage`),
  CONSTRAINT `good_stackout_ibfk_1` FOREIGN KEY (`good_code`) REFERENCES `good` (`good_code`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `good_stackout_ibfk_2` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `good_stackout_ibfk_3` FOREIGN KEY (`storage`) REFERENCES `storage` (`storage_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of good_stackout
-- ----------------------------

-- ----------------------------
-- Table structure for good_storage
-- ----------------------------
DROP TABLE IF EXISTS `good_storage`;
CREATE TABLE `good_storage` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '库存id',
  `storage_id` int(11) NOT NULL COMMENT '仓库编码',
  `storage_name` varchar(20) DEFAULT NULL COMMENT '仓库名',
  `good_id` int(11) DEFAULT NULL COMMENT '物品编码',
  `num` int(11) DEFAULT NULL COMMENT '物品数量',
  PRIMARY KEY (`id`),
  KEY `pk1` (`storage_id`),
  KEY `pk2` (`good_id`),
  CONSTRAINT `pk1` FOREIGN KEY (`storage_id`) REFERENCES `storage` (`storage_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pk2` FOREIGN KEY (`good_id`) REFERENCES `good` (`good_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1008 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of good_storage
-- ----------------------------
INSERT INTO `good_storage` VALUES ('1006', '1000', null, '1000', '100');
INSERT INTO `good_storage` VALUES ('1007', '1001', null, '1000', '200');

-- ----------------------------
-- Table structure for rent
-- ----------------------------
DROP TABLE IF EXISTS `rent`;
CREATE TABLE `rent` (
  `rent_id` int(11) NOT NULL COMMENT '租借编号',
  `user_id` int(11) DEFAULT NULL COMMENT '租借人id',
  `good_id` int(11) DEFAULT NULL COMMENT '借用物品id',
  `good_num` int(11) DEFAULT NULL COMMENT '借用数量',
  `rent_time` datetime DEFAULT NULL COMMENT '租借时间',
  `rent_date` int(11) DEFAULT NULL COMMENT '借用天数',
  `deposit` int(11) DEFAULT NULL COMMENT '押金',
  `hire` int(11) DEFAULT NULL COMMENT '租金',
  PRIMARY KEY (`rent_id`),
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `good_id` (`good_id`) USING BTREE,
  CONSTRAINT `rent_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `rent_ibfk_2` FOREIGN KEY (`good_id`) REFERENCES `good` (`good_code`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rent
-- ----------------------------
INSERT INTO `rent` VALUES ('1000', '21', '1000', '100', '2017-04-26 00:59:55', '3', '100', '5');

-- ----------------------------
-- Table structure for reservation
-- ----------------------------
DROP TABLE IF EXISTS `reservation`;
CREATE TABLE `reservation` (
  `reservation_id` int(11) NOT NULL COMMENT '预约id',
  `reservator_id` int(11) DEFAULT NULL COMMENT '预约人id',
  `reservation_date` datetime DEFAULT NULL COMMENT '预约时间',
  `good_id` int(11) DEFAULT NULL COMMENT '预约物品id',
  `good_num` int(11) DEFAULT NULL COMMENT '预约物品数量',
  `statue` varchar(20) DEFAULT NULL COMMENT '审核状态',
  PRIMARY KEY (`reservation_id`),
  KEY `reservation_ibfk_1` (`reservator_id`),
  KEY `reservation_ibfk_2` (`good_id`),
  CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`reservator_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`good_id`) REFERENCES `good` (`good_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reservation
-- ----------------------------

-- ----------------------------
-- Table structure for revert
-- ----------------------------
DROP TABLE IF EXISTS `revert`;
CREATE TABLE `revert` (
  `id` int(11) NOT NULL COMMENT '归还编号',
  `user_id` int(11) DEFAULT NULL COMMENT '租借人id',
  `good_id` int(11) DEFAULT NULL COMMENT '归还物品id',
  `good_num` int(11) DEFAULT NULL COMMENT '归还数量',
  `rent_time` datetime DEFAULT NULL COMMENT '归还时间',
  `deposit` int(11) DEFAULT NULL COMMENT '押金',
  `value` int(11) DEFAULT NULL COMMENT '收取费用',
  PRIMARY KEY (`id`),
  KEY `revert_ibfk_1` (`user_id`),
  KEY `revert_ibfk_2` (`good_id`),
  CONSTRAINT `revert_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `revert_ibfk_2` FOREIGN KEY (`good_id`) REFERENCES `good` (`good_code`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of revert
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('0', '管理员');
INSERT INTO `role` VALUES ('1', '用户');

-- ----------------------------
-- Table structure for storage
-- ----------------------------
DROP TABLE IF EXISTS `storage`;
CREATE TABLE `storage` (
  `storage_id` int(11) NOT NULL COMMENT '仓库id',
  `storage_name` varchar(20) DEFAULT NULL COMMENT '仓库名',
  `address` varchar(50) DEFAULT NULL COMMENT '仓库地址',
  PRIMARY KEY (`storage_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of storage
-- ----------------------------
INSERT INTO `storage` VALUES ('1000', '湘潭大学', '湘潭大学');
INSERT INTO `storage` VALUES ('1001', '厕所', '厕所旁边');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `address` varchar(50) DEFAULT NULL COMMENT '用户住址',
  `sex` varchar(20) DEFAULT NULL COMMENT '用户性别',
  `age` int(3) DEFAULT NULL COMMENT '用户年龄',
  `ID_card` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `username` varchar(20) DEFAULT NULL COMMENT '用户账号',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `role` int(11) DEFAULT NULL COMMENT '权限',
  `reg_date` datetime DEFAULT NULL COMMENT '注册日期',
  `last_login` datetime DEFAULT NULL COMMENT '最后登入时间',
  `statue` int(11) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  KEY `pk_role` (`role`),
  CONSTRAINT `pk_role` FOREIGN KEY (`role`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '缪小俊', '湘潭大学', '男', '18', '330327199402017272', 'admin', '15673254624', '123456', '407354967@qq.com', '0', '2017-04-28 16:36:53', '2017-04-28 16:37:15', '1');
INSERT INTO `user` VALUES ('2', '米文辉', null, '男', '18', '330327199402017272', 'root', '13012456789', '017272', '407354967@qq.com', '1', '2017-04-28 16:38:07', null, '1');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) DEFAULT NULL COMMENT '用戶编码',
  `arrears_amount` int(11) DEFAULT '0' COMMENT '欠费金额',
  `overdue` int(11) DEFAULT '0' COMMENT '逾期天数',
  KEY `user_info_ibfk_1` (`id`),
  CONSTRAINT `user_info_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '0', '0');
INSERT INTO `user_info` VALUES ('21', '0', '0');
INSERT INTO `user_info` VALUES ('22', '0', '0');
INSERT INTO `user_info` VALUES ('25', '0', '0');
INSERT INTO `user_info` VALUES ('2', '0', '0');
