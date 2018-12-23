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

 Date: 23/12/2018 13:56:43
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
) ENGINE = InnoDB AUTO_INCREMENT = 118 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '登录日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of login_log
-- ----------------------------
INSERT INTO `login_log` VALUES (1, '2018-12-09 17:40:35', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (2, '2018-12-09 17:40:41', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (3, '2018-12-08 17:40:47', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (4, '2018-12-07 17:41:30', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (5, '2018-12-09 17:43:35', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (6, '2018-12-09 18:29:14', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (7, '2018-12-09 19:14:57', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (8, '2018-12-09 21:13:57', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (9, '2018-12-09 21:14:06', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (10, '2018-12-09 21:15:50', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (11, '2018-12-09 21:17:08', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (12, '2018-12-09 21:22:08', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (13, '2018-12-09 21:22:15', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (14, '2018-12-09 21:22:21', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (15, '2018-12-09 21:30:21', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (16, '2018-12-09 22:18:07', 'admin', '1', '101.90.124.221');
INSERT INTO `login_log` VALUES (17, '2018-12-09 22:22:15', 'user', '1', '101.90.124.221');
INSERT INTO `login_log` VALUES (18, '2018-12-09 22:26:32', 'admin', '1', '101.90.124.221');
INSERT INTO `login_log` VALUES (19, '2018-12-09 22:26:51', 'user', '1', '101.90.124.221');
INSERT INTO `login_log` VALUES (20, '2018-12-09 22:32:26', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (21, '2018-12-09 22:33:44', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (22, '2018-12-09 22:33:47', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (23, '2018-12-09 22:33:53', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (24, '2018-12-09 22:34:11', 'user', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (25, '2018-12-10 01:09:40', 'admin', '1', '112.94.118.157');
INSERT INTO `login_log` VALUES (26, '2018-12-10 09:00:26', 'admin', '0', '219.142.235.159');
INSERT INTO `login_log` VALUES (27, '2018-12-10 09:00:31', 'admin', '0', '219.142.235.159');
INSERT INTO `login_log` VALUES (28, '2018-12-10 09:00:37', 'admin', '0', '219.142.235.159');
INSERT INTO `login_log` VALUES (29, '2018-12-10 09:00:41', 'admin', '1', '219.142.235.159');
INSERT INTO `login_log` VALUES (30, '2018-12-10 16:15:46', 'admin', '1', '175.10.241.141');
INSERT INTO `login_log` VALUES (31, '2018-12-10 21:36:54', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (32, '2018-12-10 22:54:44', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (33, '2018-12-10 22:56:27', 'user', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (34, '2018-12-10 22:58:35', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (35, '2018-12-10 22:58:49', 'user', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (36, '2018-12-10 22:59:15', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (37, '2018-12-11 08:54:39', 'admin', '0', '124.126.162.152');
INSERT INTO `login_log` VALUES (38, '2018-12-11 08:54:48', 'admin', '1', '124.126.162.152');
INSERT INTO `login_log` VALUES (39, '2018-12-11 21:30:28', 'admin', '1', '36.157.218.82');
INSERT INTO `login_log` VALUES (40, '2018-12-13 23:57:42', 'admin', '1', '112.94.116.66');
INSERT INTO `login_log` VALUES (41, '2018-12-15 04:26:08', 'admin', '0', '112.94.117.137');
INSERT INTO `login_log` VALUES (42, '2018-12-15 04:26:16', 'admin', '1', '112.94.117.137');
INSERT INTO `login_log` VALUES (43, '2018-12-15 15:54:38', 'admin', '0', '219.143.151.141');
INSERT INTO `login_log` VALUES (44, '2018-12-15 15:54:43', 'admin', '0', '219.143.151.141');
INSERT INTO `login_log` VALUES (45, '2018-12-15 15:54:48', 'admin', '1', '219.143.151.141');
INSERT INTO `login_log` VALUES (46, '2018-12-15 17:05:32', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (47, '2018-12-16 18:06:27', 'admin', '1', '127.0.0.1');
INSERT INTO `login_log` VALUES (48, '2018-12-16 20:04:43', 'admin', '1', '127.0.0.1');
INSERT INTO `login_log` VALUES (49, '2018-12-17 21:33:11', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (50, '2018-12-17 22:07:59', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (51, '2018-12-17 22:08:02', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (52, '2018-12-17 22:08:45', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (53, '2018-12-17 22:08:55', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (54, '2018-12-17 22:08:58', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (55, '2018-12-17 22:12:38', 'zhaojun', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (56, '2018-12-17 22:12:42', 'zhaojun', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (57, '2018-12-17 22:15:34', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (58, '2018-12-17 22:35:08', 'root@zhaojun.im', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (59, '2018-12-18 16:40:06', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (60, '2018-12-18 17:03:53', 'user', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (61, '2018-12-18 21:05:52', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (62, '2018-12-18 21:13:30', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (63, '2018-12-18 21:14:03', 'user', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (64, '2018-12-18 21:14:05', 'user', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (65, '2018-12-18 21:14:08', 'user', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (66, '2018-12-18 21:14:10', 'user', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (67, '2018-12-18 21:14:10', 'user', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (68, '2018-12-18 21:14:10', 'user', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (69, '2018-12-18 21:14:10', 'user', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (70, '2018-12-18 21:14:10', 'user', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (71, '2018-12-18 21:14:11', 'user', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (72, '2018-12-18 21:14:11', 'user', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (73, '2018-12-18 21:14:11', 'user', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (74, '2018-12-18 21:14:12', 'user', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (75, '2018-12-18 21:14:19', 'user', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (76, '2018-12-18 21:14:19', 'user', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (77, '2018-12-18 21:14:20', 'user', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (78, '2018-12-18 21:14:49', 'user', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (79, '2018-12-18 21:14:54', 'user', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (80, '2018-12-18 21:15:01', 'user', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (81, '2018-12-18 22:52:16', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (82, '2018-12-18 22:52:27', 'admin111', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (83, '2018-12-18 22:54:48', 'admin123456', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (84, '2018-12-18 22:54:53', 'admin123456', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (85, '2018-12-18 23:20:11', 'admin1234567', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (86, '2018-12-20 21:23:57', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (87, '2018-12-20 22:47:12', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (88, '2018-12-20 22:47:17', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (89, '2018-12-21 17:53:22', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (90, '2018-12-21 17:55:03', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (91, '2018-12-21 17:57:33', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (92, '2018-12-21 17:59:12', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (93, '2018-12-22 15:01:24', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (94, '2018-12-22 15:02:58', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (95, '2018-12-22 15:10:15', 'adminqweqweq', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (96, '2018-12-22 15:10:16', 'adminqweqweq', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (97, '2018-12-22 15:10:21', 'adminqweqweq', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (98, '2018-12-22 15:10:48', 'adminqweqweq', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (99, '2018-12-22 15:50:18', 'admin', '0', '127.0.0.1');
INSERT INTO `login_log` VALUES (100, '2018-12-22 16:24:44', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (101, '2018-12-22 16:24:48', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (102, '2018-12-22 16:26:46', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (103, '2018-12-22 16:26:49', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (104, '2018-12-22 16:26:53', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (105, '2018-12-22 16:32:53', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (106, '2018-12-22 16:36:46', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (107, '2018-12-22 17:18:43', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (108, '2018-12-22 17:19:04', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (109, '2018-12-22 17:19:16', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (110, '2018-12-22 18:52:59', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (111, '2018-12-22 18:53:16', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (112, '2018-12-22 18:53:23', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (113, '2018-12-22 18:53:32', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (114, '2018-12-22 18:58:47', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (115, '2018-12-22 22:45:13', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (116, '2018-12-23 00:29:19', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (117, '2018-12-23 00:33:35', 'admin', '1', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NULL DEFAULT 0,
  `menu_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `perms` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `order_num` int(11) NULL DEFAULT NULL,
  `method` varchar(7) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modify_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, 0, '权限管理', NULL, NULL, '0', 0, NULL, '2018-12-02 07:51:17', '2018-12-02 08:01:38');
INSERT INTO `menu` VALUES (2, 1, '用户管理', '/users', 'user:list', '1', 1, '', '2018-12-02 07:51:17', NULL);
INSERT INTO `menu` VALUES (3, 1, '角色管理', '/roles', 'role:list', '1', 2, '', '2018-12-02 07:51:18', NULL);
INSERT INTO `menu` VALUES (4, 1, '菜单管理', '/menus', 'menu:list', '1', 3, NULL, '2018-12-02 07:51:18', NULL);
INSERT INTO `menu` VALUES (5, 2, '用户禁用', '/user/*/disable', 'user:disable', '2', 1, 'POST', '2018-12-02 12:30:42', NULL);
INSERT INTO `menu` VALUES (6, 2, '用户激活', '/user/*/enable', 'user:enable', '2', 2, 'POST', '2018-12-02 12:32:29', NULL);
INSERT INTO `menu` VALUES (7, 3, '角色添加', '/role', 'role:add', '2', 1, 'POST', '2018-12-02 12:35:56', NULL);
INSERT INTO `menu` VALUES (8, 3, '角色编辑', '/role', 'role:update', '2', 2, 'PUT', '2018-12-02 12:35:40', NULL);
INSERT INTO `menu` VALUES (9, 4, '新增菜单', '/menu', 'menu:add', '2', 1, 'POST', '2018-12-02 12:39:26', NULL);
INSERT INTO `menu` VALUES (10, 4, '菜单编辑', '/menu', 'menu:update', '2', 2, 'PUT', '2018-12-02 12:42:07', NULL);
INSERT INTO `menu` VALUES (11, 0, '日志管理', NULL, NULL, '0', 1, NULL, '2018-12-09 09:46:36', NULL);
INSERT INTO `menu` VALUES (12, 11, '登录日志', '/log/login', 'login:log:list', '1', 1, NULL, '2018-12-09 10:07:36', NULL);
INSERT INTO `menu` VALUES (13, 0, '错误页面', NULL, NULL, '0', 2, NULL, '2018-12-10 14:14:09', NULL);
INSERT INTO `menu` VALUES (14, 13, '403', '/403', 'error:403', '1', 1, NULL, '2018-12-10 14:14:55', NULL);
INSERT INTO `menu` VALUES (15, 13, '404', '404', 'error:404', '1', 2, NULL, '2018-12-10 14:15:11', NULL);
INSERT INTO `menu` VALUES (16, 13, '500', '/500', 'error:500', '1', 3, NULL, '2018-12-10 14:15:58', NULL);
INSERT INTO `menu` VALUES (17, 2, '用户授权', '/user/*/allocation', 'user:allocation', '2', 0, 'POST', '2018-12-17 22:16:33', NULL);
INSERT INTO `menu` VALUES (19, 11, '操作日志', '/log/sys', 'sys:log:list', '1', 2, NULL, '2018-12-22 22:48:27', NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `role_menu` VALUES (2, 1);
INSERT INTO `role_menu` VALUES (2, 2);
INSERT INTO `role_menu` VALUES (2, 3);
INSERT INTO `role_menu` VALUES (2, 4);
INSERT INTO `role_menu` VALUES (2, 8);
INSERT INTO `role_menu` VALUES (1, 1);
INSERT INTO `role_menu` VALUES (1, 2);
INSERT INTO `role_menu` VALUES (1, 17);
INSERT INTO `role_menu` VALUES (1, 5);
INSERT INTO `role_menu` VALUES (1, 6);
INSERT INTO `role_menu` VALUES (1, 3);
INSERT INTO `role_menu` VALUES (1, 7);
INSERT INTO `role_menu` VALUES (1, 8);
INSERT INTO `role_menu` VALUES (1, 4);
INSERT INTO `role_menu` VALUES (1, 9);
INSERT INTO `role_menu` VALUES (1, 10);
INSERT INTO `role_menu` VALUES (1, 11);
INSERT INTO `role_menu` VALUES (1, 12);
INSERT INTO `role_menu` VALUES (1, 19);
INSERT INTO `role_menu` VALUES (1, 13);
INSERT INTO `role_menu` VALUES (1, 14);
INSERT INTO `role_menu` VALUES (1, 15);
INSERT INTO `role_menu` VALUES (1, 16);

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
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1, 'admin', '新增菜单', 24, 'im.zhaojun.controller.MenuController.add()', '  menu: Menu{menuId=19, parentId=11, menuName=\'操作日志\', url=\'/log/sys\', perms=\'log:sys:list\', type=\'1\', orderNum=2, createTime=null, modifyTime=null, method=\'null\'}', '0:0:0:0:0:0:0:1', '2018-12-22 22:48:12');
INSERT INTO `sys_log` VALUES (2, 'admin', '修改菜单', 9, 'im.zhaojun.controller.MenuController.update()', '  menu: Menu{menuId=19, parentId=11, menuName=\'操作日志\', url=\'/log/sys\', perms=\'sys:log:list\', type=\'1\', orderNum=2, createTime=null, modifyTime=null, method=\'null\'}', '0:0:0:0:0:0:0:1', '2018-12-22 22:48:27');
INSERT INTO `sys_log` VALUES (3, 'admin', '修改角色', 49, 'im.zhaojun.controller.RoleController.update()', '  role: im.zhaojun.model.Role@665e2ee3  menuIds: [Ljava.lang.Integer;@6a82d856', '0:0:0:0:0:0:0:1', '2018-12-22 22:48:43');
INSERT INTO `sys_log` VALUES (4, 'admin', '访问我的桌面', 0, 'im.zhaojun.controller.IndexController.welcome()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:01:29');
INSERT INTO `sys_log` VALUES (5, 'admin', '获取用户列表', 11, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 00:01:30');
INSERT INTO `sys_log` VALUES (6, 'admin', '查看近七日登录统计图', 5, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:01:30');
INSERT INTO `sys_log` VALUES (7, 'admin', '获取用户列表', 16, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 00:01:37');
INSERT INTO `sys_log` VALUES (8, 'admin', '查看登录日志', 10, 'im.zhaojun.controller.LoginLogController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 00:01:46');
INSERT INTO `sys_log` VALUES (9, 'admin', '查看操作日志', 8, 'im.zhaojun.controller.SysLogController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 00:01:47');
INSERT INTO `sys_log` VALUES (10, 'admin', '查看操作日志', 5, 'im.zhaojun.controller.SysLogController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 00:01:54');
INSERT INTO `sys_log` VALUES (11, 'admin', '访问我的桌面', 0, 'im.zhaojun.controller.IndexController.welcome()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:20:46');
INSERT INTO `sys_log` VALUES (12, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:20:46');
INSERT INTO `sys_log` VALUES (13, 'admin', '获取菜单列表', 9, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:20:47');
INSERT INTO `sys_log` VALUES (14, 'admin', '获取菜单列表', 3, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:20:53');
INSERT INTO `sys_log` VALUES (15, 'admin', '获取菜单列表', 18, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:21:47');
INSERT INTO `sys_log` VALUES (16, 'admin', '获取用户列表', 82, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 00:21:49');
INSERT INTO `sys_log` VALUES (17, 'admin', '获取用户列表', 7, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 00:21:51');
INSERT INTO `sys_log` VALUES (18, 'admin', '获取用户列表', 7, 'im.zhaojun.controller.UserController.getList()', '  page: 2  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 00:21:53');
INSERT INTO `sys_log` VALUES (19, 'admin', '获取用户列表', 11, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 00:21:54');
INSERT INTO `sys_log` VALUES (20, 'admin', '获取角色列表', 57, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 00:22:08');
INSERT INTO `sys_log` VALUES (21, 'admin', '获取角色列表', 2, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 00:22:09');
INSERT INTO `sys_log` VALUES (22, 'admin', '获取菜单列表', 3, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:22:13');
INSERT INTO `sys_log` VALUES (23, 'admin', '登录', 80, 'im.zhaojun.controller.LoginController.login()', '  user: User{userId=null, username=\'admin\', password=\'123456\', salt=\'null\', email=\'null\', status=\'null\', lastLoginTime=null, createTime=null, modifyTime=null, activeCode=\'null\'}  captcha: jfu2', '0:0:0:0:0:0:0:1', '2018-12-23 00:29:18');
INSERT INTO `sys_log` VALUES (24, 'admin', '访问我的桌面', 0, 'im.zhaojun.controller.IndexController.welcome()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:29:21');
INSERT INTO `sys_log` VALUES (25, 'admin', '查看近七日登录统计图', 7, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:29:21');
INSERT INTO `sys_log` VALUES (26, 'admin', '获取用户列表', 86, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 00:29:24');
INSERT INTO `sys_log` VALUES (27, 'admin', '获取角色列表', 26, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 00:29:25');
INSERT INTO `sys_log` VALUES (28, 'admin', '获取菜单列表', 9, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:29:28');
INSERT INTO `sys_log` VALUES (29, 'admin', '登录', 78, 'im.zhaojun.controller.LoginController.login()', '  user: User{userId=null, username=\'admin\', password=\'123456\', salt=\'null\', email=\'null\', status=\'null\', lastLoginTime=null, createTime=null, modifyTime=null, activeCode=\'null\'}  captcha: raa3', '0:0:0:0:0:0:0:1', '2018-12-23 00:33:35');
INSERT INTO `sys_log` VALUES (30, 'admin', '访问我的桌面', 0, 'im.zhaojun.controller.IndexController.welcome()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:33:41');
INSERT INTO `sys_log` VALUES (31, 'admin', '查看近七日登录统计图', 7, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:33:41');
INSERT INTO `sys_log` VALUES (32, 'admin', '获取用户列表', 93, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 00:33:45');
INSERT INTO `sys_log` VALUES (33, 'admin', '获取角色列表', 27, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 00:33:46');
INSERT INTO `sys_log` VALUES (34, 'admin', '获取菜单列表', 17, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:33:47');
INSERT INTO `sys_log` VALUES (35, 'admin', '访问我的桌面', 0, 'im.zhaojun.controller.IndexController.welcome()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:35:52');
INSERT INTO `sys_log` VALUES (36, 'admin', '查看近七日登录统计图', 13, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:35:53');
INSERT INTO `sys_log` VALUES (37, 'admin', '获取角色列表', 110, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 00:35:53');
INSERT INTO `sys_log` VALUES (38, 'admin', '获取菜单列表', 19, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:36:19');
INSERT INTO `sys_log` VALUES (39, 'admin', '获取菜单列表', 50, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:38:38');
INSERT INTO `sys_log` VALUES (40, 'admin', '获取角色列表', 131, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 00:38:51');
INSERT INTO `sys_log` VALUES (41, 'admin', '访问我的桌面', 0, 'im.zhaojun.controller.IndexController.welcome()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:40:01');
INSERT INTO `sys_log` VALUES (42, 'admin', '查看近七日登录统计图', 7, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:40:02');
INSERT INTO `sys_log` VALUES (43, 'admin', '获取角色列表', 89, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 00:40:02');
INSERT INTO `sys_log` VALUES (44, 'admin', '获取用户列表', 13, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 00:40:04');
INSERT INTO `sys_log` VALUES (45, 'admin', '获取菜单列表', 10, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:40:05');
INSERT INTO `sys_log` VALUES (46, 'admin', '获取菜单列表', 11, 'im.zhaojun.controller.MenuController.tree()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:40:08');
INSERT INTO `sys_log` VALUES (47, 'admin', '获取菜单列表', 287, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:40:10');
INSERT INTO `sys_log` VALUES (48, 'admin', '获取菜单列表', 2, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:40:12');
INSERT INTO `sys_log` VALUES (49, 'admin', '获取角色列表', 3, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 00:40:13');
INSERT INTO `sys_log` VALUES (50, 'admin', '获取菜单列表', 3, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:40:16');
INSERT INTO `sys_log` VALUES (51, 'admin', '获取角色列表', 9, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 00:42:42');
INSERT INTO `sys_log` VALUES (52, 'admin', '访问我的桌面', 0, 'im.zhaojun.controller.IndexController.welcome()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:46:36');
INSERT INTO `sys_log` VALUES (53, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:46:36');
INSERT INTO `sys_log` VALUES (54, 'admin', '获取角色列表', 93, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 00:46:37');
INSERT INTO `sys_log` VALUES (55, 'admin', '访问我的桌面', 0, 'im.zhaojun.controller.IndexController.welcome()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:49:45');
INSERT INTO `sys_log` VALUES (56, 'admin', '查看近七日登录统计图', 7, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:49:46');
INSERT INTO `sys_log` VALUES (57, 'admin', '获取角色列表', 114, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 00:49:46');
INSERT INTO `sys_log` VALUES (58, 'admin', '查看操作日志', 16, 'im.zhaojun.controller.SysLogController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 00:49:52');
INSERT INTO `sys_log` VALUES (59, 'admin', '获取用户列表', 15, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 00:49:53');
INSERT INTO `sys_log` VALUES (60, 'admin', '获取菜单列表', 14, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 00:49:54');

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
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'f51703256a38e6bab3d9410a070c32ea', 'salt', 'root@zhaojun.im', 1, '2018-12-23 00:33:35', '2018-12-02 07:30:52', '2018-12-23 00:33:35', NULL);
INSERT INTO `user` VALUES (20, 'user', '2408794d39e51cb1771644a9bc07a0e4', '1543753139522', NULL, 0, '2018-12-18 21:13:38', '2018-12-02 12:18:59', '2018-12-18 21:13:38', NULL);
INSERT INTO `user` VALUES (21, '1', '366981cbbeb34331b886d78354181f46', '1544968123483', '2', 1, '2018-12-17 21:33:36', '2018-12-16 21:48:43', '2018-12-17 21:33:36', NULL);
INSERT INTO `user` VALUES (23, '123', '0ff81b1605dbc90be5b13ef3373327d6', '1545055755570', 'qwe', 1, '2018-12-18 17:13:38', '2018-12-17 22:09:15', '2018-12-18 17:13:38', NULL);
INSERT INTO `user` VALUES (24, '12', '27a91317bf49abbab610d9d65db2e8de', '1545055878979', 'qwe', 1, '2018-12-17 22:11:18', '2018-12-17 22:11:18', NULL, NULL);
INSERT INTO `user` VALUES (25, 'zhaojun', 'c889fd7983a55e38133b3496aa23fffb', '1545055950059', NULL, 1, '2018-12-17 22:12:42', '2018-12-17 22:12:30', '2018-12-17 22:12:42', NULL);
INSERT INTO `user` VALUES (26, 'emailtest', '676772467986e56b7c34b34c03645aea', '1545144400444', 'root@zhaojun.im', 0, '2018-12-18 22:46:40', '2018-12-18 22:46:40', NULL, NULL);
INSERT INTO `user` VALUES (27, 'emailtest1', '4fe55409375d00ef385f50087fcf6c2d', '1545144443102', 'root@zhaojun.im', 0, '2018-12-18 22:49:04', '2018-12-18 22:49:04', NULL, NULL);
INSERT INTO `user` VALUES (28, 'emailtest1222', 'fd67af3533aa697e8c2993d8b4f0e71c', '1545144637089', 'root@zhaojun.im', 0, '2018-12-18 22:50:39', '2018-12-18 22:50:39', NULL, NULL);
INSERT INTO `user` VALUES (29, 'admin111', '207d39a5a19633a5cd53f955885d8803', '1545144716781', '873019219@qq.com', 0, '2018-12-18 22:51:56', '2018-12-18 22:51:56', NULL, NULL);
INSERT INTO `user` VALUES (30, 'admin123456', '581fa0ec8256576d6f59af3df668cb11', '1545144873589', 'root@zhaojun.im', 0, '2018-12-18 22:54:33', '2018-12-18 22:54:33', NULL, NULL);
INSERT INTO `user` VALUES (31, 'admin1234567', '14ec37a927e2e8e4976f1ed2d7dc136c', '1545146379543', '873019219@qq.com', 1, '2018-12-18 23:20:10', '2018-12-18 23:19:39', '2018-12-18 23:20:10', '17f56332d6f445de975e80c8eb2b9458');
INSERT INTO `user` VALUES (32, 'adminqweqwe', 'd3d8e9f02147e0c015b5e9a865827c3c', '1545462234070', '873019219@qq.com', 0, '2018-12-22 15:03:54', '2018-12-22 15:03:54', NULL, '92c27351b1b44ff1b7500a8b2b6bfa88');
INSERT INTO `user` VALUES (33, 'adminqweqwe11', '5ba1030d49531af8694a8c7b1ff63e88', '1545462324533', '873019219@qq.com', 0, '2018-12-22 15:05:24', '2018-12-22 15:05:24', NULL, 'a908a2f771b04254887e569845ebfe5a');
INSERT INTO `user` VALUES (34, 'adminqweqwe1113', 'e8b7d1cbc3683a204c5801fb33c36808', '1545462456590', '873019219@qq.com', 0, '2018-12-22 15:07:36', '2018-12-22 15:07:36', NULL, 'c197f0321fde4eb18ce2a31fc2ea1412');
INSERT INTO `user` VALUES (35, 'adminqweqweq', 'bef7978908dbb6a73a3083f0cf6a810e', '1545462524844', '873019219@qq.com', 1, '2018-12-22 15:08:44', '2018-12-22 15:08:44', '2018-12-22 15:10:05', 'e8917d93c1194d829b96bd683311e636');

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
INSERT INTO `user_role` VALUES (21, 2);
INSERT INTO `user_role` VALUES (22, 1);
INSERT INTO `user_role` VALUES (23, 1);
INSERT INTO `user_role` VALUES (25, 2);
INSERT INTO `user_role` VALUES (1, 1);
INSERT INTO `user_role` VALUES (20, 1);
INSERT INTO `user_role` VALUES (20, 2);
INSERT INTO `user_role` VALUES (26, 2);
INSERT INTO `user_role` VALUES (27, 2);
INSERT INTO `user_role` VALUES (28, 2);
INSERT INTO `user_role` VALUES (29, 2);
INSERT INTO `user_role` VALUES (30, 2);
INSERT INTO `user_role` VALUES (31, 2);
INSERT INTO `user_role` VALUES (32, 2);
INSERT INTO `user_role` VALUES (33, 2);
INSERT INTO `user_role` VALUES (34, 2);
INSERT INTO `user_role` VALUES (35, 2);

SET FOREIGN_KEY_CHECKS = 1;
