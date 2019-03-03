/*
 Navicat Premium Data Transfer

 Source Server         : 本机mysql
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : 127.0.0.1:3306
 Source Schema         : shiro_action

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 03/03/2019 21:05:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for login_log
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '登录时间',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `login_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '登录状态, 0 表示登录失败, 1 表示登录成功.',
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'IP',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 239 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '登录日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单 ID',
  `parent_id` int(11) NULL DEFAULT 0,
  `menu_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单 URL',
  `perms` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识符',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, 0, '权限管理', '#', NULL, 0, '2018-12-02 07:51:17', '2019-02-16 19:47:57');
INSERT INTO `menu` VALUES (2, 1, '用户管理', '/user/index', 'user:list', 1, '2018-12-23 19:50:25', NULL);
INSERT INTO `menu` VALUES (3, 1, '角色管理', '/role/index', 'role:list', 2, '2018-12-02 07:51:18', '2018-12-23 19:50:44');
INSERT INTO `menu` VALUES (4, 1, '菜单权限', '/menu/index', 'menu:list', 3, '2019-02-07 10:57:06', '2019-02-16 19:49:22');
INSERT INTO `menu` VALUES (11, 0, '系统监控', '#', NULL, 5, '2019-02-04 15:07:41', '2019-02-16 19:47:57');
INSERT INTO `menu` VALUES (12, 11, '登录日志', '/log/login/index', 'login:log:list', 1, '2018-12-09 10:07:36', '2019-02-10 22:27:00');
INSERT INTO `menu` VALUES (19, 11, '操作日志', '/log/sys/index', 'sys:log:list', 4, '2018-12-22 22:48:27', '2019-02-10 22:12:13');
INSERT INTO `menu` VALUES (20, 11, '在线用户', '/online/index', 'user:online', 3, '2018-12-23 15:40:21', '2019-02-10 22:27:00');
INSERT INTO `menu` VALUES (27, 1, '操作权限', '/operator/index', 'operator:list', 4, '2019-02-10 17:39:08', '2019-02-16 19:49:22');

-- ----------------------------
-- Table structure for operator
-- ----------------------------
DROP TABLE IF EXISTS `operator`;
CREATE TABLE `operator`  (
  `operator_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单 ID',
  `menu_id` int(11) NULL DEFAULT 0 COMMENT '所属菜单 ID',
  `operator_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源名称',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源 URL',
  `perms` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识符',
  `http_method` varchar(7) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源需要的 HTTP 请求方法',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`operator_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of operator
-- ----------------------------
INSERT INTO `operator` VALUES (1, 2, '删除用户', '/user/*', 'user:delete', 'DELETE', '2019-03-03 16:12:27', '2019-03-03 16:12:50');
INSERT INTO `operator` VALUES (2, 2, '添加用户', '/user', 'user:add', 'POST', '2019-02-19 22:21:17', NULL);
INSERT INTO `operator` VALUES (3, 2, '授予角色', '/user/*/allocation', 'user:allocation', 'POST', '2019-03-03 16:03:37', '2019-03-03 16:06:59');
INSERT INTO `operator` VALUES (4, 2, '重置密码', '/user/{userId}/reset', 'user:reset', 'POST', '2019-03-03 16:05:40', '2019-03-03 16:06:59');
INSERT INTO `operator` VALUES (5, 3, '新增角色', '/role', 'role:add', 'POST', '2019-03-03 16:07:48', NULL);
INSERT INTO `operator` VALUES (6, 3, '删除角色', '/role/*', 'role:delete', 'DELETE', '2019-03-03 16:08:39', NULL);
INSERT INTO `operator` VALUES (7, 3, '授予菜单', '/role/*/grant/menu', 'role:grant:menu', 'POST', '2019-03-03 16:09:13', '2019-03-03 16:17:48');
INSERT INTO `operator` VALUES (8, 3, '授予功能', '/role/*/grant/operator', 'role:grant:operator', 'POST', '2019-03-03 16:12:09', '2019-03-03 16:17:48');
INSERT INTO `operator` VALUES (9, 3, '编辑角色', '/role', 'user:edit', 'PUT', '2019-03-03 16:27:02', NULL);
INSERT INTO `operator` VALUES (10, 4, '新增菜单', '/menu', 'menu:add', 'POST', '2019-03-03 16:29:22', NULL);
INSERT INTO `operator` VALUES (11, 4, '编辑菜单', '/menu', 'menu:edit', 'PUT', '2019-03-03 16:30:59', NULL);
INSERT INTO `operator` VALUES (12, 4, '删除菜单', '/menu/*', '删除菜单', 'DELETE', '2019-03-03 16:31:32', NULL);
INSERT INTO `operator` VALUES (13, 27, '新增操作点', '/operator', 'operator:add', 'POST', '2019-03-03 16:47:35', NULL);
INSERT INTO `operator` VALUES (14, 27, '删除操作点', '/operator', 'operator:delete', 'DELETE', '2019-03-03 16:47:45', NULL);
INSERT INTO `operator` VALUES (15, 27, '修改操作点', '/operator', 'operator:put', 'PUT', '2019-03-03 16:48:01', NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modify_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员', '这是一个管理员', '2018-12-02 07:47:40', '2018-12-02 07:47:45');
INSERT INTO `role` VALUES (2, '普通用户', '这是一个普通用户', '2018-12-02 10:09:08', NULL);

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (1, 1);
INSERT INTO `role_menu` VALUES (1, 2);
INSERT INTO `role_menu` VALUES (1, 3);
INSERT INTO `role_menu` VALUES (1, 4);
INSERT INTO `role_menu` VALUES (1, 11);
INSERT INTO `role_menu` VALUES (1, 12);
INSERT INTO `role_menu` VALUES (1, 19);
INSERT INTO `role_menu` VALUES (1, 20);
INSERT INTO `role_menu` VALUES (2, 1);
INSERT INTO `role_menu` VALUES (2, 2);
INSERT INTO `role_menu` VALUES (2, 3);
INSERT INTO `role_menu` VALUES (2, 4);
INSERT INTO `role_menu` VALUES (2, 27);

-- ----------------------------
-- Table structure for role_operator
-- ----------------------------
DROP TABLE IF EXISTS `role_operator`;
CREATE TABLE `role_operator`  (
  `role_id` int(11) NULL DEFAULT NULL,
  `operator_id` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色-操作关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_operator
-- ----------------------------
INSERT INTO `role_operator` VALUES (1, 1);
INSERT INTO `role_operator` VALUES (1, 2);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `operation` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作',
  `time` int(11) NULL DEFAULT NULL COMMENT '响应时间/耗时',
  `method` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `params` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 671 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` int(1) NULL DEFAULT NULL COMMENT '账号状态: 0: 未激活, 1: 已激活. ',
  `last_login_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后登录时间',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `active_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册激活码',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'f51703256a38e6bab3d9410a070c32ea', 'salt', 'root@zhaojun.im', 1, '2019-03-03 20:37:00', '2018-12-02 07:30:52', '2019-03-03 20:37:00', NULL);
INSERT INTO `user` VALUES (20, 'user', 'e87febb85a752c6885250096c16f0832', '1551274106695', NULL, 1, '2019-02-27 21:28:54', '2018-12-02 12:18:59', '2019-02-27 21:28:54', NULL);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (20, 2);
INSERT INTO `user_role` VALUES (1, 1);
INSERT INTO `user_role` VALUES (1, 2);

SET FOREIGN_KEY_CHECKS = 1;
