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

 Date: 23/12/2018 21:54:16
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
) ENGINE = InnoDB AUTO_INCREMENT = 139 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '登录日志表' ROW_FORMAT = Dynamic;

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
INSERT INTO `login_log` VALUES (118, '2018-12-23 14:57:23', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (119, '2018-12-23 16:55:28', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (120, '2018-12-23 16:56:03', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (121, '2018-12-23 16:56:16', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (122, '2018-12-23 16:56:35', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (123, '2018-12-23 16:56:40', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (124, '2018-12-23 16:56:51', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (125, '2018-12-23 17:33:39', 'adminadminqweqw1111', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (126, '2018-12-23 17:46:18', 'admin9856', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (127, '2018-12-23 17:46:27', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (128, '2018-12-23 17:46:55', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (129, '2018-12-23 17:48:30', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (130, '2018-12-23 17:49:00', 'adminqwewqeqweqwe', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (131, '2018-12-23 17:49:15', 'adminqwewqeqweqwe', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (132, '2018-12-23 18:08:54', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (133, '2018-12-23 18:09:23', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (134, '2018-12-23 18:15:51', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (135, '2018-12-23 18:16:45', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (136, '2018-12-23 18:17:03', 'admin', '0', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (137, '2018-12-23 18:44:40', 'admin', '1', '0:0:0:0:0:0:0:1');
INSERT INTO `login_log` VALUES (138, '2018-12-23 21:33:51', 'admin', '1', '0:0:0:0:0:0:0:1');

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
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, 0, '权限管理', NULL, NULL, '0', 0, NULL, '2018-12-02 07:51:17', '2018-12-02 08:01:38');
INSERT INTO `menu` VALUES (2, 1, '用户管理', '/user/index', 'user:list', '1', 1, NULL, '2018-12-23 19:50:25', NULL);
INSERT INTO `menu` VALUES (3, 1, '角色管理', '/role/index', 'role:list', '1', 2, '', '2018-12-02 07:51:18', '2018-12-23 19:50:44');
INSERT INTO `menu` VALUES (4, 1, '菜单管理', '/menu/index', 'menu:list', '1', 3, NULL, '2018-12-02 07:51:18', '2018-12-23 19:50:44');
INSERT INTO `menu` VALUES (5, 2, '用户禁用', '/user/*/disable', 'user:disable', '2', 1, 'POST', '2018-12-02 12:30:42', NULL);
INSERT INTO `menu` VALUES (6, 2, '用户激活', '/user/*/enable', 'user:enable', '2', 2, 'POST', '2018-12-02 12:32:29', NULL);
INSERT INTO `menu` VALUES (7, 3, '角色添加', '/role', 'role:add', '2', 1, 'POST', '2018-12-02 12:35:56', NULL);
INSERT INTO `menu` VALUES (8, 3, '角色编辑', '/role', 'role:update', '2', 2, 'PUT', '2018-12-02 12:35:40', NULL);
INSERT INTO `menu` VALUES (9, 4, '新增菜单', '/menu', 'menu:add', '2', 1, 'POST', '2018-12-02 12:39:26', NULL);
INSERT INTO `menu` VALUES (10, 4, '菜单编辑', '/menu', 'menu:update', '2', 2, 'PUT', '2018-12-02 12:42:07', NULL);
INSERT INTO `menu` VALUES (11, 0, '系统管理', NULL, NULL, '0', 1, NULL, '2018-12-23 15:40:32', NULL);
INSERT INTO `menu` VALUES (12, 11, '登录日志', '/log/login/index', 'login:log:list', '1', 1, NULL, '2018-12-09 10:07:36', '2018-12-23 19:50:59');
INSERT INTO `menu` VALUES (13, 0, '错误页面', NULL, NULL, '0', 2, NULL, '2018-12-10 14:14:09', NULL);
INSERT INTO `menu` VALUES (14, 13, '403', '/403', 'error:403', '1', 1, NULL, '2018-12-10 14:14:55', NULL);
INSERT INTO `menu` VALUES (15, 13, '404', '404', 'error:404', '1', 2, NULL, '2018-12-10 14:15:11', NULL);
INSERT INTO `menu` VALUES (16, 13, '500', '/500', 'error:500', '1', 3, NULL, '2018-12-10 14:15:58', NULL);
INSERT INTO `menu` VALUES (17, 2, '用户授权', '/user/*/allocation', 'user:allocation', '2', 0, 'POST', '2018-12-17 22:16:33', NULL);
INSERT INTO `menu` VALUES (19, 11, '操作日志', '/log/sys/index', 'sys:log:list', '1', 2, NULL, '2018-12-22 22:48:27', '2018-12-23 19:50:59');
INSERT INTO `menu` VALUES (20, 11, '在线用户', '/online/index', 'user:online', '1', 3, NULL, '2018-12-23 15:40:21', NULL);

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
INSERT INTO `role_menu` VALUES (1, 20);
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
) ENGINE = InnoDB AUTO_INCREMENT = 467 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志表' ROW_FORMAT = Dynamic;

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
INSERT INTO `sys_log` VALUES (61, 'admin', '登录', 66, 'im.zhaojun.controller.LoginController.login()', '  user: User{userId=null, username=\'admin\', password=\'123456\', salt=\'null\', email=\'null\', status=\'null\', lastLoginTime=null, createTime=null, modifyTime=null, activeCode=\'null\'}  captcha: 2duj', '0:0:0:0:0:0:0:1', '2018-12-23 14:57:22');
INSERT INTO `sys_log` VALUES (62, 'admin', '访问我的桌面', 0, 'im.zhaojun.controller.IndexController.welcome()', '', '0:0:0:0:0:0:0:1', '2018-12-23 14:57:24');
INSERT INTO `sys_log` VALUES (63, 'admin', '查看近七日登录统计图', 6, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 14:57:24');
INSERT INTO `sys_log` VALUES (64, 'admin', '获取用户列表', 75, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 14:57:26');
INSERT INTO `sys_log` VALUES (65, 'admin', '获取角色列表', 25, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 14:57:27');
INSERT INTO `sys_log` VALUES (66, 'admin', '获取菜单列表', 16, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 14:57:28');
INSERT INTO `sys_log` VALUES (67, 'admin', '访问我的桌面', 1, 'im.zhaojun.controller.IndexController.welcome()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:21:04');
INSERT INTO `sys_log` VALUES (68, 'admin', '查看近七日登录统计图', 9, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:21:05');
INSERT INTO `sys_log` VALUES (69, 'admin', '获取用户列表', 71, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 15:21:30');
INSERT INTO `sys_log` VALUES (70, 'admin', '获取角色列表', 42, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 15:21:31');
INSERT INTO `sys_log` VALUES (71, 'admin', '查看登录日志', 15, 'im.zhaojun.controller.LoginLogController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 15:21:33');
INSERT INTO `sys_log` VALUES (72, 'admin', '获取菜单列表', 12, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:21:35');
INSERT INTO `sys_log` VALUES (73, 'admin', '获取菜单列表', 9, 'im.zhaojun.controller.MenuController.tree()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:21:38');
INSERT INTO `sys_log` VALUES (74, 'admin', '获取菜单列表', 10, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:21:38');
INSERT INTO `sys_log` VALUES (75, 'admin', '获取菜单列表', 1, 'im.zhaojun.controller.MenuController.tree()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:21:39');
INSERT INTO `sys_log` VALUES (76, 'admin', '新增菜单', 60, 'im.zhaojun.controller.MenuController.add()', '  menu: Menu{menuId=20, parentId=11, menuName=\'在线用户\', url=\'/online/user\', perms=\'user:online\', type=\'1\', orderNum=3, createTime=null, modifyTime=null, method=\'null\'}', '0:0:0:0:0:0:0:1', '2018-12-23 15:22:21');
INSERT INTO `sys_log` VALUES (77, 'admin', '获取菜单列表', 1, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:22:22');
INSERT INTO `sys_log` VALUES (78, 'admin', '获取菜单列表', 3, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:22:29');
INSERT INTO `sys_log` VALUES (79, 'admin', '获取角色列表', 1, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 15:22:37');
INSERT INTO `sys_log` VALUES (80, 'admin', '获取菜单列表', 1, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:22:38');
INSERT INTO `sys_log` VALUES (81, 'admin', '获取菜单列表', 1, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:22:54');
INSERT INTO `sys_log` VALUES (82, 'admin', '获取菜单列表', 2, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:27:37');
INSERT INTO `sys_log` VALUES (83, 'admin', '获取菜单列表', 1, 'im.zhaojun.controller.MenuController.tree()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:28:21');
INSERT INTO `sys_log` VALUES (84, 'admin', '新增菜单', 7911, 'im.zhaojun.controller.MenuController.add()', '  menu: Menu{menuId=21, parentId=0, menuName=\'156\', url=\'wqe\', perms=\'qwr\', type=\'1\', orderNum=1, createTime=null, modifyTime=null, method=\'null\'}', '0:0:0:0:0:0:0:1', '2018-12-23 15:28:51');
INSERT INTO `sys_log` VALUES (85, 'admin', '获取菜单列表', 3, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:28:55');
INSERT INTO `sys_log` VALUES (86, 'admin', '获取菜单列表', 60, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:33:33');
INSERT INTO `sys_log` VALUES (87, 'admin', '获取菜单列表', 37, 'im.zhaojun.controller.MenuController.tree()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:33:38');
INSERT INTO `sys_log` VALUES (88, 'admin', '获取菜单列表', 7, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:33:40');
INSERT INTO `sys_log` VALUES (89, 'admin', '获取菜单列表', 2, 'im.zhaojun.controller.MenuController.tree()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:33:42');
INSERT INTO `sys_log` VALUES (90, 'admin', '新增菜单', 55, 'im.zhaojun.controller.MenuController.add()', '  menu: Menu{menuId=22, parentId=0, menuName=\'124\', url=\'12\', perms=\'12\', type=\'1\', orderNum=4, createTime=null, modifyTime=null, method=\'null\'}', '0:0:0:0:0:0:0:1', '2018-12-23 15:33:46');
INSERT INTO `sys_log` VALUES (91, 'admin', '获取菜单列表', 2, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:33:50');
INSERT INTO `sys_log` VALUES (92, 'admin', '获取菜单列表', 1, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:33:57');
INSERT INTO `sys_log` VALUES (93, 'admin', '获取菜单列表', 3, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:33:58');
INSERT INTO `sys_log` VALUES (94, 'admin', '获取角色列表', 387, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 15:34:31');
INSERT INTO `sys_log` VALUES (95, 'admin', '获取菜单列表', 3, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:34:32');
INSERT INTO `sys_log` VALUES (96, 'admin', '修改角色', 50, 'im.zhaojun.controller.RoleController.update()', '  role: im.zhaojun.model.Role@bda4e16  menuIds: [Ljava.lang.Integer;@29242da6', '0:0:0:0:0:0:0:1', '2018-12-23 15:34:35');
INSERT INTO `sys_log` VALUES (97, 'admin', '获取角色列表', 2, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 15:34:36');
INSERT INTO `sys_log` VALUES (98, 'admin', '访问我的桌面', 0, 'im.zhaojun.controller.IndexController.welcome()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:34:38');
INSERT INTO `sys_log` VALUES (99, 'admin', '查看近七日登录统计图', 3, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:34:38');
INSERT INTO `sys_log` VALUES (100, 'admin', '获取角色列表', 2, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 15:34:38');
INSERT INTO `sys_log` VALUES (101, 'admin', '获取用户列表', 99, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 15:39:08');
INSERT INTO `sys_log` VALUES (102, 'admin', '获取菜单列表', 19, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:39:11');
INSERT INTO `sys_log` VALUES (103, 'admin', '获取菜单列表', 2, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:39:13');
INSERT INTO `sys_log` VALUES (104, 'admin', '删除菜单', 24, 'im.zhaojun.controller.MenuController.delete()', '  id: 21', '0:0:0:0:0:0:0:1', '2018-12-23 15:39:15');
INSERT INTO `sys_log` VALUES (105, 'admin', '获取菜单列表', 24, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:39:17');
INSERT INTO `sys_log` VALUES (106, 'admin', '删除菜单', 16, 'im.zhaojun.controller.MenuController.delete()', '  id: 22', '0:0:0:0:0:0:0:1', '2018-12-23 15:39:20');
INSERT INTO `sys_log` VALUES (107, 'admin', '获取菜单列表', 10, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:39:21');
INSERT INTO `sys_log` VALUES (108, 'admin', '获取菜单列表', 35, 'im.zhaojun.controller.MenuController.tree()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:39:22');
INSERT INTO `sys_log` VALUES (109, 'admin', '新增菜单', 16, 'im.zhaojun.controller.MenuController.add()', '  menu: Menu{menuId=23, parentId=0, menuName=\'13\', url=\'null\', perms=\'null\', type=\'0\', orderNum=32, createTime=null, modifyTime=null, method=\'null\'}', '0:0:0:0:0:0:0:1', '2018-12-23 15:39:25');
INSERT INTO `sys_log` VALUES (110, 'admin', '获取菜单列表', 6, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:39:29');
INSERT INTO `sys_log` VALUES (111, 'admin', '删除菜单', 13, 'im.zhaojun.controller.MenuController.delete()', '  id: 23', '0:0:0:0:0:0:0:1', '2018-12-23 15:39:31');
INSERT INTO `sys_log` VALUES (112, 'admin', '获取用户列表', 8, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 15:40:12');
INSERT INTO `sys_log` VALUES (113, 'admin', '获取角色列表', 4, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 15:40:13');
INSERT INTO `sys_log` VALUES (114, 'admin', '获取菜单列表', 8, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:40:14');
INSERT INTO `sys_log` VALUES (115, 'admin', '获取菜单列表', 8, 'im.zhaojun.controller.MenuController.tree()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:40:18');
INSERT INTO `sys_log` VALUES (116, 'admin', '修改菜单', 13, 'im.zhaojun.controller.MenuController.update()', '  menu: Menu{menuId=20, parentId=11, menuName=\'在线用户\', url=\'/online/index\', perms=\'user:online\', type=\'1\', orderNum=3, createTime=null, modifyTime=null, method=\'null\'}', '0:0:0:0:0:0:0:1', '2018-12-23 15:40:21');
INSERT INTO `sys_log` VALUES (117, 'admin', '获取菜单列表', 7, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:40:23');
INSERT INTO `sys_log` VALUES (118, 'admin', '获取菜单列表', 6, 'im.zhaojun.controller.MenuController.tree()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:40:25');
INSERT INTO `sys_log` VALUES (119, 'admin', '修改菜单', 12, 'im.zhaojun.controller.MenuController.update()', '  menu: Menu{menuId=11, parentId=0, menuName=\'系统管理\', url=\'null\', perms=\'null\', type=\'0\', orderNum=1, createTime=null, modifyTime=null, method=\'null\'}', '0:0:0:0:0:0:0:1', '2018-12-23 15:40:32');
INSERT INTO `sys_log` VALUES (120, 'admin', '获取菜单列表', 7, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:40:33');
INSERT INTO `sys_log` VALUES (121, 'admin', '访问我的桌面', 0, 'im.zhaojun.controller.IndexController.welcome()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:40:34');
INSERT INTO `sys_log` VALUES (122, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:40:35');
INSERT INTO `sys_log` VALUES (123, 'admin', '获取菜单列表', 3, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:40:35');
INSERT INTO `sys_log` VALUES (124, 'admin', '查看操作日志', 233, 'im.zhaojun.controller.SysLogController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 15:45:31');
INSERT INTO `sys_log` VALUES (125, 'admin', '获取用户列表', 29, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 15:45:32');
INSERT INTO `sys_log` VALUES (126, 'admin', '获取角色列表', 81, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 15:45:34');
INSERT INTO `sys_log` VALUES (127, 'admin', '获取菜单列表', 16, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 15:47:47');
INSERT INTO `sys_log` VALUES (128, NULL, '登录', 55, 'im.zhaojun.controller.LoginController.login()', '  user: User{userId=null, username=\'admin\', password=\'123456\', salt=\'null\', email=\'null\', status=\'null\', lastLoginTime=null, createTime=null, modifyTime=null, activeCode=\'null\'}  captcha: jebe', '0:0:0:0:0:0:0:1', '2018-12-23 16:55:28');
INSERT INTO `sys_log` VALUES (129, NULL, '登录', 11, 'im.zhaojun.controller.LoginController.login()', '  user: User{userId=null, username=\'admin\', password=\'123456\', salt=\'null\', email=\'null\', status=\'null\', lastLoginTime=null, createTime=null, modifyTime=null, activeCode=\'null\'}  captcha: jebe', '0:0:0:0:0:0:0:1', '2018-12-23 16:56:02');
INSERT INTO `sys_log` VALUES (130, 'admin', '登录', 45, 'im.zhaojun.controller.LoginController.login()', '  user: User{userId=null, username=\'admin\', password=\'123456\', salt=\'null\', email=\'null\', status=\'null\', lastLoginTime=null, createTime=null, modifyTime=null, activeCode=\'null\'}  captcha: qzaq', '0:0:0:0:0:0:0:1', '2018-12-23 16:56:15');
INSERT INTO `sys_log` VALUES (131, 'admin', '访问我的桌面', 0, 'im.zhaojun.controller.IndexController.welcome()', '', '0:0:0:0:0:0:0:1', '2018-12-23 16:56:17');
INSERT INTO `sys_log` VALUES (132, 'admin', '查看近七日登录统计图', 13, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 16:56:17');
INSERT INTO `sys_log` VALUES (133, NULL, '登录', 9, 'im.zhaojun.controller.LoginController.login()', '  user: User{userId=null, username=\'admin\', password=\'123456\', salt=\'null\', email=\'null\', status=\'null\', lastLoginTime=null, createTime=null, modifyTime=null, activeCode=\'null\'}  captcha: awzr', '0:0:0:0:0:0:0:1', '2018-12-23 16:56:34');
INSERT INTO `sys_log` VALUES (134, NULL, '登录', 9, 'im.zhaojun.controller.LoginController.login()', '  user: User{userId=null, username=\'admin\', password=\'123456\', salt=\'null\', email=\'null\', status=\'null\', lastLoginTime=null, createTime=null, modifyTime=null, activeCode=\'null\'}  captcha: 4pga', '0:0:0:0:0:0:0:1', '2018-12-23 16:56:39');
INSERT INTO `sys_log` VALUES (135, 'admin', '登录', 16, 'im.zhaojun.controller.LoginController.login()', '  user: User{userId=null, username=\'admin\', password=\'123456\', salt=\'null\', email=\'null\', status=\'null\', lastLoginTime=null, createTime=null, modifyTime=null, activeCode=\'null\'}  captcha: xfrx', '0:0:0:0:0:0:0:1', '2018-12-23 16:56:50');
INSERT INTO `sys_log` VALUES (136, 'admin', '访问我的桌面', 0, 'im.zhaojun.controller.IndexController.welcome()', '', '0:0:0:0:0:0:0:1', '2018-12-23 16:56:52');
INSERT INTO `sys_log` VALUES (137, 'admin', '查看近七日登录统计图', 5, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 16:56:52');
INSERT INTO `sys_log` VALUES (138, 'admin', '获取用户列表', 109, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 16:57:41');
INSERT INTO `sys_log` VALUES (139, 'admin', '查看登录日志', 11, 'im.zhaojun.controller.LoginLogController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 16:57:43');
INSERT INTO `sys_log` VALUES (140, 'admin', '查看操作日志', 10, 'im.zhaojun.controller.SysLogController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 16:57:43');
INSERT INTO `sys_log` VALUES (141, 'admin', '获取用户列表', 7, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 16:57:45');
INSERT INTO `sys_log` VALUES (142, 'admin', '获取角色列表', 22, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 16:57:45');
INSERT INTO `sys_log` VALUES (143, 'admin', '获取菜单列表', 10, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 16:57:46');
INSERT INTO `sys_log` VALUES (144, 'admin', '获取用户列表', 34, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 16:59:27');
INSERT INTO `sys_log` VALUES (145, 'admin', '访问我的桌面', 1, 'im.zhaojun.controller.IndexController.welcome()', '', '0:0:0:0:0:0:0:1', '2018-12-23 17:28:05');
INSERT INTO `sys_log` VALUES (146, 'admin', '查看近七日登录统计图', 7, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 17:28:06');
INSERT INTO `sys_log` VALUES (147, NULL, '注销', 20, 'im.zhaojun.controller.LoginController.logout()', '', '0:0:0:0:0:0:0:1', '2018-12-23 17:28:07');
INSERT INTO `sys_log` VALUES (148, NULL, '登录', 20, 'im.zhaojun.controller.LoginController.login()', '  user: User{userId=null, username=\'adminadminqweqw1111\', password=\'123456\', salt=\'null\', email=\'null\', status=\'null\', lastLoginTime=null, createTime=null, modifyTime=null, activeCode=\'null\'}  captcha: xxxu', '0:0:0:0:0:0:0:1', '2018-12-23 17:33:38');
INSERT INTO `sys_log` VALUES (149, NULL, '激活注册账号', 30, 'im.zhaojun.controller.LoginController.active()', '  token: 287d91e9defe4f9bbe32b9206573dec8  model: {msg=激活成功!}', '0:0:0:0:0:0:0:1', '2018-12-23 17:44:30');
INSERT INTO `sys_log` VALUES (150, NULL, '激活注册账号', 3, 'im.zhaojun.controller.LoginController.active()', '  token: 287d91e9defe4f9bbe32b9206573dec8  model: {msg=用户已激活, 请勿重复激活!}', '0:0:0:0:0:0:0:1', '2018-12-23 17:44:32');
INSERT INTO `sys_log` VALUES (151, 'admin9856', '访问我的桌面', 1, 'im.zhaojun.controller.IndexController.welcome()', '', '0:0:0:0:0:0:0:1', '2018-12-23 17:46:19');
INSERT INTO `sys_log` VALUES (152, 'admin9856', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 17:46:20');
INSERT INTO `sys_log` VALUES (153, NULL, '注销', 5, 'im.zhaojun.controller.LoginController.logout()', '', '0:0:0:0:0:0:0:1', '2018-12-23 17:46:23');
INSERT INTO `sys_log` VALUES (154, 'admin', '访问我的桌面', 0, 'im.zhaojun.controller.IndexController.welcome()', '', '0:0:0:0:0:0:0:1', '2018-12-23 17:48:31');
INSERT INTO `sys_log` VALUES (155, 'admin', '查看近七日登录统计图', 5, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 17:48:32');
INSERT INTO `sys_log` VALUES (156, NULL, '注销', 4, 'im.zhaojun.controller.LoginController.logout()', '', '0:0:0:0:0:0:0:1', '2018-12-23 17:48:33');
INSERT INTO `sys_log` VALUES (157, 'admin', '访问我的桌面', 0, 'im.zhaojun.controller.IndexController.welcome()', '', '0:0:0:0:0:0:0:1', '2018-12-23 18:08:55');
INSERT INTO `sys_log` VALUES (158, 'admin', '查看近七日登录统计图', 5, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 18:08:56');
INSERT INTO `sys_log` VALUES (159, NULL, '注销', 5, 'im.zhaojun.controller.LoginController.logout()', '', '0:0:0:0:0:0:0:1', '2018-12-23 18:08:59');
INSERT INTO `sys_log` VALUES (160, 'admin', '访问我的桌面', 0, 'im.zhaojun.controller.IndexController.welcome()', '', '0:0:0:0:0:0:0:1', '2018-12-23 18:15:53');
INSERT INTO `sys_log` VALUES (161, 'admin', '查看近七日登录统计图', 7, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 18:15:53');
INSERT INTO `sys_log` VALUES (162, NULL, '注销', 7, 'im.zhaojun.controller.LoginController.logout()', '', '0:0:0:0:0:0:0:1', '2018-12-23 18:15:59');
INSERT INTO `sys_log` VALUES (163, 'admin', '访问我的桌面', 0, 'im.zhaojun.controller.IndexController.welcome()', '', '0:0:0:0:0:0:0:1', '2018-12-23 18:44:42');
INSERT INTO `sys_log` VALUES (164, 'admin', '查看近七日登录统计图', 14, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 18:44:42');
INSERT INTO `sys_log` VALUES (165, 'admin', '访问我的桌面', 0, 'im.zhaojun.controller.IndexController.welcome()', '', '0:0:0:0:0:0:0:1', '2018-12-23 18:45:11');
INSERT INTO `sys_log` VALUES (166, 'admin', '查看近七日登录统计图', 5, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 18:45:12');
INSERT INTO `sys_log` VALUES (167, 'admin', '访问我的桌面', 0, 'im.zhaojun.controller.IndexController.welcome()', '', '0:0:0:0:0:0:0:1', '2018-12-23 18:45:21');
INSERT INTO `sys_log` VALUES (168, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 18:45:21');
INSERT INTO `sys_log` VALUES (169, 'admin', '访问我的桌面', 0, 'im.zhaojun.controller.IndexController.welcome()', '', '0:0:0:0:0:0:0:1', '2018-12-23 18:45:23');
INSERT INTO `sys_log` VALUES (170, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 18:45:23');
INSERT INTO `sys_log` VALUES (171, 'admin', '访问我的桌面', 0, 'im.zhaojun.controller.IndexController.welcome()', '', '0:0:0:0:0:0:0:1', '2018-12-23 18:46:57');
INSERT INTO `sys_log` VALUES (172, 'admin', '查看近七日登录统计图', 5, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 18:46:58');
INSERT INTO `sys_log` VALUES (173, 'admin', '访问我的桌面', 0, 'im.zhaojun.controller.IndexController.welcome()', '', '0:0:0:0:0:0:0:1', '2018-12-23 18:47:41');
INSERT INTO `sys_log` VALUES (174, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 18:47:41');
INSERT INTO `sys_log` VALUES (175, 'admin', '访问我的桌面', 14, 'im.zhaojun.controller.IndexController.welcome()', '  model: {}', '0:0:0:0:0:0:0:1', '2018-12-23 19:01:36');
INSERT INTO `sys_log` VALUES (176, 'admin', '查看近七日登录统计图', 3, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:01:36');
INSERT INTO `sys_log` VALUES (177, 'admin', '访问我的桌面', 108, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=176, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:03:40');
INSERT INTO `sys_log` VALUES (178, 'admin', '查看近七日登录统计图', 11, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:03:40');
INSERT INTO `sys_log` VALUES (179, 'admin', '访问我的桌面', 28, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=178, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:09:08');
INSERT INTO `sys_log` VALUES (180, 'admin', '查看近七日登录统计图', 5, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:09:08');
INSERT INTO `sys_log` VALUES (181, 'admin', '访问我的桌面', 21, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=180, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:12:07');
INSERT INTO `sys_log` VALUES (182, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:12:08');
INSERT INTO `sys_log` VALUES (183, 'admin', '访问我的桌面', 18, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=182, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:12:25');
INSERT INTO `sys_log` VALUES (184, 'admin', '查看近七日登录统计图', 2, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:12:25');
INSERT INTO `sys_log` VALUES (185, 'admin', '访问我的桌面', 22, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=184, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:12:42');
INSERT INTO `sys_log` VALUES (186, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:12:43');
INSERT INTO `sys_log` VALUES (187, 'admin', '访问我的桌面', 18, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=186, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:13:02');
INSERT INTO `sys_log` VALUES (188, 'admin', '查看近七日登录统计图', 5, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:13:03');
INSERT INTO `sys_log` VALUES (189, 'admin', '获取用户列表', 140, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 19:13:51');
INSERT INTO `sys_log` VALUES (190, 'admin', '访问我的桌面', 16, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=189, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:14:27');
INSERT INTO `sys_log` VALUES (191, 'admin', '查看近七日登录统计图', 5, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:14:28');
INSERT INTO `sys_log` VALUES (192, 'admin', '获取用户列表', 10, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 19:15:44');
INSERT INTO `sys_log` VALUES (193, 'admin', '访问我的桌面', 27, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=192, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:17:38');
INSERT INTO `sys_log` VALUES (194, 'admin', '查看近七日登录统计图', 3, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:17:38');
INSERT INTO `sys_log` VALUES (195, 'admin', '访问我的桌面', 13, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=194, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:22:18');
INSERT INTO `sys_log` VALUES (196, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:22:19');
INSERT INTO `sys_log` VALUES (197, 'admin', '访问我的桌面', 14, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=196, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:22:53');
INSERT INTO `sys_log` VALUES (198, 'admin', '查看近七日登录统计图', 2, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:22:54');
INSERT INTO `sys_log` VALUES (199, 'admin', '访问我的桌面', 16, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=198, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:25:29');
INSERT INTO `sys_log` VALUES (200, 'admin', '查看近七日登录统计图', 3, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:25:29');
INSERT INTO `sys_log` VALUES (201, 'admin', '访问我的桌面', 14, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=200, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:25:56');
INSERT INTO `sys_log` VALUES (202, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:25:56');
INSERT INTO `sys_log` VALUES (203, 'admin', '访问我的桌面', 11, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=202, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:27:26');
INSERT INTO `sys_log` VALUES (204, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:27:26');
INSERT INTO `sys_log` VALUES (205, 'admin', '访问我的桌面', 16, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=204, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:28:31');
INSERT INTO `sys_log` VALUES (206, 'admin', '查看近七日登录统计图', 5, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:28:32');
INSERT INTO `sys_log` VALUES (207, 'admin', '访问我的桌面', 16, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=206, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:30:31');
INSERT INTO `sys_log` VALUES (208, 'admin', '查看近七日登录统计图', 2, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:30:32');
INSERT INTO `sys_log` VALUES (209, 'admin', '获取用户列表', 7, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 19:30:33');
INSERT INTO `sys_log` VALUES (210, 'admin', '获取用户列表', 12, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 19:30:40');
INSERT INTO `sys_log` VALUES (211, 'admin', '获取角色列表', 8, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 19:30:43');
INSERT INTO `sys_log` VALUES (212, 'admin', '获取用户列表', 6, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 19:30:45');
INSERT INTO `sys_log` VALUES (213, 'admin', '获取角色列表', 2, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 19:30:50');
INSERT INTO `sys_log` VALUES (214, 'admin', '获取菜单列表', 13, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:30:51');
INSERT INTO `sys_log` VALUES (215, 'admin', '获取用户列表', 7, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 19:30:52');
INSERT INTO `sys_log` VALUES (216, 'admin', '获取角色列表', 2, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 19:30:52');
INSERT INTO `sys_log` VALUES (217, 'admin', '访问我的桌面', 10, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=216, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:37:17');
INSERT INTO `sys_log` VALUES (218, 'admin', '查看近七日登录统计图', 3, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:37:17');
INSERT INTO `sys_log` VALUES (219, 'admin', '获取角色列表', 2, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 19:37:18');
INSERT INTO `sys_log` VALUES (220, 'admin', '获取用户列表', 8, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 19:37:20');
INSERT INTO `sys_log` VALUES (221, 'admin', '获取菜单列表', 1, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:37:21');
INSERT INTO `sys_log` VALUES (222, 'admin', '访问我的桌面', 16, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=221, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:40:23');
INSERT INTO `sys_log` VALUES (223, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:40:23');
INSERT INTO `sys_log` VALUES (224, 'admin', '访问我的桌面', 14, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=223, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:40:49');
INSERT INTO `sys_log` VALUES (225, 'admin', '查看近七日登录统计图', 3, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:40:49');
INSERT INTO `sys_log` VALUES (226, 'admin', '获取用户列表', 6, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 19:40:51');
INSERT INTO `sys_log` VALUES (227, 'admin', '获取用户列表', 7, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 19:41:18');
INSERT INTO `sys_log` VALUES (228, 'admin', '获取用户列表', 9, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 19:41:20');
INSERT INTO `sys_log` VALUES (229, 'admin', '访问我的桌面', 17, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=228, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:41:26');
INSERT INTO `sys_log` VALUES (230, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:41:26');
INSERT INTO `sys_log` VALUES (231, 'admin', '获取用户列表', 6, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 19:41:27');
INSERT INTO `sys_log` VALUES (232, 'admin', '访问我的桌面', 10, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=231, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:41:36');
INSERT INTO `sys_log` VALUES (233, 'admin', '查看近七日登录统计图', 3, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:41:36');
INSERT INTO `sys_log` VALUES (234, 'admin', '获取用户列表', 6, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 19:41:38');
INSERT INTO `sys_log` VALUES (235, 'admin', '访问我的桌面', 9, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=234, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:41:55');
INSERT INTO `sys_log` VALUES (236, 'admin', '查看近七日登录统计图', 3, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:41:55');
INSERT INTO `sys_log` VALUES (237, 'admin', '获取用户列表', 5, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 19:41:59');
INSERT INTO `sys_log` VALUES (238, 'admin', '访问我的桌面', 13, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=237, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:43:02');
INSERT INTO `sys_log` VALUES (239, 'admin', '查看近七日登录统计图', 2, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:43:02');
INSERT INTO `sys_log` VALUES (240, 'admin', '获取用户列表', 5, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 19:43:03');
INSERT INTO `sys_log` VALUES (241, 'admin', '访问我的桌面', 12, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=240, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:43:56');
INSERT INTO `sys_log` VALUES (242, 'admin', '查看近七日登录统计图', 2, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:43:56');
INSERT INTO `sys_log` VALUES (243, 'admin', '获取用户列表', 6, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 19:43:57');
INSERT INTO `sys_log` VALUES (244, 'admin', '获取角色列表', 9, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 19:45:12');
INSERT INTO `sys_log` VALUES (245, 'admin', '获取菜单列表', 7, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:45:14');
INSERT INTO `sys_log` VALUES (246, 'admin', '获取菜单列表', 44, 'im.zhaojun.controller.MenuController.tree()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:50:20');
INSERT INTO `sys_log` VALUES (247, 'admin', '修改菜单', 26, 'im.zhaojun.controller.MenuController.update()', '  menu: Menu{menuId=2, parentId=1, menuName=\'用户管理\', url=\'/user/index\', perms=\'user:list\', type=\'1\', orderNum=1, createTime=null, modifyTime=null, method=\'null\'}', '0:0:0:0:0:0:0:1', '2018-12-23 19:50:25');
INSERT INTO `sys_log` VALUES (248, 'admin', '访问我的桌面', 69, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=247, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:51:50');
INSERT INTO `sys_log` VALUES (249, 'admin', '查看近七日登录统计图', 5, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:51:50');
INSERT INTO `sys_log` VALUES (250, 'admin', '获取用户列表', 73, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 19:51:52');
INSERT INTO `sys_log` VALUES (251, 'admin', '获取角色列表', 9, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 19:51:53');
INSERT INTO `sys_log` VALUES (252, 'admin', '获取菜单列表', 10, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:51:54');
INSERT INTO `sys_log` VALUES (253, 'admin', '查看登录日志', 11, 'im.zhaojun.controller.LoginLogController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 19:51:56');
INSERT INTO `sys_log` VALUES (254, 'admin', '查看操作日志', 11, 'im.zhaojun.controller.SysLogController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 19:51:57');
INSERT INTO `sys_log` VALUES (255, 'admin', '访问我的桌面', 25, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=254, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:54:17');
INSERT INTO `sys_log` VALUES (256, 'admin', '查看近七日登录统计图', 5, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:54:17');
INSERT INTO `sys_log` VALUES (257, 'admin', '访问我的桌面', 15, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=256, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:55:28');
INSERT INTO `sys_log` VALUES (258, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:55:29');
INSERT INTO `sys_log` VALUES (259, 'admin', '访问我的桌面', 14, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=258, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:55:36');
INSERT INTO `sys_log` VALUES (260, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:55:36');
INSERT INTO `sys_log` VALUES (261, 'admin', '访问我的桌面', 14, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=260, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:55:37');
INSERT INTO `sys_log` VALUES (262, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:55:37');
INSERT INTO `sys_log` VALUES (263, 'admin', '访问我的桌面', 13, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=262, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:56:58');
INSERT INTO `sys_log` VALUES (264, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:56:59');
INSERT INTO `sys_log` VALUES (265, 'admin', '获取用户列表', 9, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 19:57:15');
INSERT INTO `sys_log` VALUES (266, 'admin', '访问我的桌面', 28, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=265, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:57:59');
INSERT INTO `sys_log` VALUES (267, 'admin', '查看近七日登录统计图', 5, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:57:59');
INSERT INTO `sys_log` VALUES (268, 'admin', '获取用户列表', 8, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 19:57:59');
INSERT INTO `sys_log` VALUES (269, 'admin', '访问我的桌面', 17, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=268, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 19:59:29');
INSERT INTO `sys_log` VALUES (270, 'admin', '查看近七日登录统计图', 5, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 19:59:29');
INSERT INTO `sys_log` VALUES (271, 'admin', '获取用户列表', 12, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 19:59:31');
INSERT INTO `sys_log` VALUES (272, 'admin', '获取用户列表', 6, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 19:59:34');
INSERT INTO `sys_log` VALUES (273, 'admin', '获取用户列表', 7, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 19:59:35');
INSERT INTO `sys_log` VALUES (274, 'admin', '访问我的桌面', 11, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=273, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 20:00:00');
INSERT INTO `sys_log` VALUES (275, 'admin', '查看近七日登录统计图', 3, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:00:01');
INSERT INTO `sys_log` VALUES (276, 'admin', '获取用户列表', 6, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:00:01');
INSERT INTO `sys_log` VALUES (277, 'admin', '获取用户列表', 7, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:00:06');
INSERT INTO `sys_log` VALUES (278, 'admin', '访问我的桌面', 14, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=277, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 20:00:44');
INSERT INTO `sys_log` VALUES (279, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:00:45');
INSERT INTO `sys_log` VALUES (280, 'admin', '获取用户列表', 7, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:00:45');
INSERT INTO `sys_log` VALUES (281, 'admin', '获取用户列表', 6, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:00:53');
INSERT INTO `sys_log` VALUES (282, 'admin', '访问我的桌面', 13, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=281, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 20:01:46');
INSERT INTO `sys_log` VALUES (283, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:01:47');
INSERT INTO `sys_log` VALUES (284, 'admin', '获取用户列表', 6, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:01:47');
INSERT INTO `sys_log` VALUES (285, 'admin', '访问我的桌面', 14, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=284, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 20:02:14');
INSERT INTO `sys_log` VALUES (286, 'admin', '查看近七日登录统计图', 3, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:02:15');
INSERT INTO `sys_log` VALUES (287, 'admin', '获取用户列表', 4, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:02:17');
INSERT INTO `sys_log` VALUES (288, 'admin', '获取用户列表', 5, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:02:19');
INSERT INTO `sys_log` VALUES (289, 'admin', '访问我的桌面', 12, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=288, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 20:02:44');
INSERT INTO `sys_log` VALUES (290, 'admin', '查看近七日登录统计图', 2, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:02:45');
INSERT INTO `sys_log` VALUES (291, 'admin', '获取用户列表', 5, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:02:47');
INSERT INTO `sys_log` VALUES (292, 'admin', '查看操作日志', 5, 'im.zhaojun.controller.SysLogController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:02:52');
INSERT INTO `sys_log` VALUES (293, 'admin', '访问我的桌面', 10, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=292, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 20:03:37');
INSERT INTO `sys_log` VALUES (294, 'admin', '查看近七日登录统计图', 2, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:03:37');
INSERT INTO `sys_log` VALUES (295, 'admin', '获取用户列表', 5, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:03:40');
INSERT INTO `sys_log` VALUES (296, 'admin', '获取角色列表', 13, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:03:42');
INSERT INTO `sys_log` VALUES (297, 'admin', '访问我的桌面', 11, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=296, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 20:04:05');
INSERT INTO `sys_log` VALUES (298, 'admin', '查看近七日登录统计图', 3, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:04:06');
INSERT INTO `sys_log` VALUES (299, 'admin', '获取用户列表', 5, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:04:09');
INSERT INTO `sys_log` VALUES (300, 'admin', '获取角色列表', 1, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:04:16');
INSERT INTO `sys_log` VALUES (301, 'admin', '获取菜单列表', 8, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:04:17');
INSERT INTO `sys_log` VALUES (302, 'admin', '查看登录日志', 7, 'im.zhaojun.controller.LoginLogController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:04:24');
INSERT INTO `sys_log` VALUES (303, 'admin', '访问我的桌面', 13, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=302, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 20:04:57');
INSERT INTO `sys_log` VALUES (304, 'admin', '查看近七日登录统计图', 2, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:04:57');
INSERT INTO `sys_log` VALUES (305, 'admin', '访问我的桌面', 12, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=304, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 20:05:24');
INSERT INTO `sys_log` VALUES (306, 'admin', '查看近七日登录统计图', 2, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:05:25');
INSERT INTO `sys_log` VALUES (307, 'admin', '查看操作日志', 8, 'im.zhaojun.controller.SysLogController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:05:45');
INSERT INTO `sys_log` VALUES (308, 'admin', '获取角色列表', 2, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:05:46');
INSERT INTO `sys_log` VALUES (309, 'admin', '获取菜单列表', 3, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:05:47');
INSERT INTO `sys_log` VALUES (310, 'admin', '获取菜单列表', 2, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:05:56');
INSERT INTO `sys_log` VALUES (311, 'admin', '获取菜单列表', 2, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:06:07');
INSERT INTO `sys_log` VALUES (312, 'admin', '访问我的桌面', 8, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=311, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 20:07:38');
INSERT INTO `sys_log` VALUES (313, 'admin', '查看近七日登录统计图', 2, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:07:39');
INSERT INTO `sys_log` VALUES (314, 'admin', '访问我的桌面', 15, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=313, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 20:08:33');
INSERT INTO `sys_log` VALUES (315, 'admin', '查看近七日登录统计图', 2, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:08:33');
INSERT INTO `sys_log` VALUES (316, 'admin', '获取用户列表', 6, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:08:35');
INSERT INTO `sys_log` VALUES (317, 'admin', '访问我的桌面', 10, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=316, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 20:08:42');
INSERT INTO `sys_log` VALUES (318, 'admin', '查看近七日登录统计图', 3, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:08:43');
INSERT INTO `sys_log` VALUES (319, 'admin', '访问我的桌面', 12, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=318, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 20:09:47');
INSERT INTO `sys_log` VALUES (320, 'admin', '查看近七日登录统计图', 3, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:09:48');
INSERT INTO `sys_log` VALUES (321, 'admin', '访问我的桌面', 13, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=320, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 20:10:46');
INSERT INTO `sys_log` VALUES (322, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:10:46');
INSERT INTO `sys_log` VALUES (323, 'admin', '访问我的桌面', 9, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=322, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 20:11:51');
INSERT INTO `sys_log` VALUES (324, 'admin', '查看近七日登录统计图', 2, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:11:51');
INSERT INTO `sys_log` VALUES (325, 'admin', '获取用户列表', 5, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:11:53');
INSERT INTO `sys_log` VALUES (326, 'admin', '查看登录日志', 4, 'im.zhaojun.controller.LoginLogController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:12:15');
INSERT INTO `sys_log` VALUES (327, 'admin', '查看操作日志', 5, 'im.zhaojun.controller.SysLogController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:12:16');
INSERT INTO `sys_log` VALUES (328, 'admin', '获取角色列表', 1, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:12:22');
INSERT INTO `sys_log` VALUES (329, 'admin', '获取菜单列表', 1, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:12:22');
INSERT INTO `sys_log` VALUES (330, 'admin', '访问我的桌面', 11, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=329, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 20:13:08');
INSERT INTO `sys_log` VALUES (331, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:13:08');
INSERT INTO `sys_log` VALUES (332, 'admin', '获取用户列表', 5, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:13:10');
INSERT INTO `sys_log` VALUES (333, 'admin', '访问我的桌面', 13, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=332, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 20:13:33');
INSERT INTO `sys_log` VALUES (334, 'admin', '查看近七日登录统计图', 3, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:13:34');
INSERT INTO `sys_log` VALUES (335, 'admin', '获取用户列表', 6, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:13:36');
INSERT INTO `sys_log` VALUES (336, 'admin', '访问我的桌面', 11, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=335, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 20:13:59');
INSERT INTO `sys_log` VALUES (337, 'admin', '查看近七日登录统计图', 2, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:13:59');
INSERT INTO `sys_log` VALUES (338, 'admin', '访问我的桌面', 7, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=26, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=337, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 20:14:16');
INSERT INTO `sys_log` VALUES (339, 'admin', '查看近七日登录统计图', 3, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:14:17');
INSERT INTO `sys_log` VALUES (340, 'admin', '获取用户列表', 6, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:14:18');
INSERT INTO `sys_log` VALUES (341, 'admin', '获取角色列表', 7, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:14:20');
INSERT INTO `sys_log` VALUES (342, 'admin', '访问我的桌面', 115, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=341, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 20:19:04');
INSERT INTO `sys_log` VALUES (343, 'admin', '查看近七日登录统计图', 7, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:19:04');
INSERT INTO `sys_log` VALUES (344, 'admin', '获取用户列表', 71, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:19:08');
INSERT INTO `sys_log` VALUES (345, 'admin', '为用户授予角色', 274, 'im.zhaojun.controller.UserController.allocation()', '  userId: 20  roleIds: [Ljava.lang.Integer;@42f1ac0c', '0:0:0:0:0:0:0:1', '2018-12-23 20:19:16');
INSERT INTO `sys_log` VALUES (346, 'admin', '获取角色列表', 4, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:19:17');
INSERT INTO `sys_log` VALUES (347, 'admin', '获取用户列表', 6, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:19:19');
INSERT INTO `sys_log` VALUES (348, 'admin', '获取角色列表', 2, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:20:08');
INSERT INTO `sys_log` VALUES (349, 'admin', '获取角色列表', 2, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:20:11');
INSERT INTO `sys_log` VALUES (350, 'admin', '访问我的桌面', 15, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=349, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 20:22:37');
INSERT INTO `sys_log` VALUES (351, 'admin', '查看近七日登录统计图', 5, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:22:37');
INSERT INTO `sys_log` VALUES (352, 'admin', '获取角色列表', 3, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:22:38');
INSERT INTO `sys_log` VALUES (353, 'admin', '获取菜单列表', 33, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:22:40');
INSERT INTO `sys_log` VALUES (354, 'admin', '获取角色列表', 2, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:22:42');
INSERT INTO `sys_log` VALUES (355, 'admin', '获取用户列表', 10, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:22:44');
INSERT INTO `sys_log` VALUES (356, 'admin', '获取用户列表', 9, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:22:47');
INSERT INTO `sys_log` VALUES (357, 'admin', '激活账号', 5, 'im.zhaojun.controller.UserController.enable()', '  id: 20', '0:0:0:0:0:0:0:1', '2018-12-23 20:22:47');
INSERT INTO `sys_log` VALUES (358, 'admin', '获取用户列表', 4, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:22:47');
INSERT INTO `sys_log` VALUES (359, 'admin', '禁用账号', 8, 'im.zhaojun.controller.UserController.disable()', '  id: 20', '0:0:0:0:0:0:0:1', '2018-12-23 20:22:48');
INSERT INTO `sys_log` VALUES (360, 'admin', '获取用户列表', 4, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:22:48');
INSERT INTO `sys_log` VALUES (361, 'admin', '获取菜单列表', 2, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:22:50');
INSERT INTO `sys_log` VALUES (362, 'admin', '查看登录日志', 11, 'im.zhaojun.controller.LoginLogController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:22:52');
INSERT INTO `sys_log` VALUES (363, 'admin', '查看操作日志', 8, 'im.zhaojun.controller.SysLogController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:22:52');
INSERT INTO `sys_log` VALUES (364, 'admin', '访问我的桌面', 14, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=363, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 20:32:28');
INSERT INTO `sys_log` VALUES (365, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:32:28');
INSERT INTO `sys_log` VALUES (366, 'admin', '获取用户列表', 7, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 20:32:29');
INSERT INTO `sys_log` VALUES (367, 'admin', '获取菜单列表', 2, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:32:31');
INSERT INTO `sys_log` VALUES (368, 'admin', '访问我的桌面', 16, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=367, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 20:32:53');
INSERT INTO `sys_log` VALUES (369, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:32:54');
INSERT INTO `sys_log` VALUES (370, 'admin', '获取菜单列表', 10, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:32:54');
INSERT INTO `sys_log` VALUES (371, 'admin', '访问我的桌面', 14, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=370, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 20:34:26');
INSERT INTO `sys_log` VALUES (372, 'admin', '查看近七日登录统计图', 5, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:34:26');
INSERT INTO `sys_log` VALUES (373, 'admin', '获取菜单列表', 3, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:34:27');
INSERT INTO `sys_log` VALUES (374, 'admin', '访问我的桌面', 14, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=137, sysLogCount=373, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 20:34:51');
INSERT INTO `sys_log` VALUES (375, 'admin', '查看近七日登录统计图', 3, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:34:52');
INSERT INTO `sys_log` VALUES (376, 'admin', '获取菜单列表', 3, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 20:34:52');
INSERT INTO `sys_log` VALUES (377, 'admin', '访问我的桌面', 8, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=138, sysLogCount=376, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 21:33:52');
INSERT INTO `sys_log` VALUES (378, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:33:52');
INSERT INTO `sys_log` VALUES (379, 'admin', '获取用户列表', 6, 'im.zhaojun.controller.UserController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 21:33:54');
INSERT INTO `sys_log` VALUES (380, 'admin', '获取角色列表', 8, 'im.zhaojun.controller.RoleController.getList()', '  page: 1  limit: 10', '0:0:0:0:0:0:0:1', '2018-12-23 21:33:55');
INSERT INTO `sys_log` VALUES (381, 'admin', '获取菜单列表', 7, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:34:03');
INSERT INTO `sys_log` VALUES (382, 'admin', '获取菜单列表', 2, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:34:35');
INSERT INTO `sys_log` VALUES (383, 'admin', '获取菜单列表', 2, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:34:44');
INSERT INTO `sys_log` VALUES (384, 'admin', '获取菜单列表', 2, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:34:45');
INSERT INTO `sys_log` VALUES (385, 'admin', '获取菜单列表', 2, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:35:29');
INSERT INTO `sys_log` VALUES (386, 'admin', '访问我的桌面', 84, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=138, sysLogCount=385, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 21:42:44');
INSERT INTO `sys_log` VALUES (387, 'admin', '获取菜单列表', 5, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:42:45');
INSERT INTO `sys_log` VALUES (388, 'admin', '查看近七日登录统计图', 8, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:42:45');
INSERT INTO `sys_log` VALUES (389, 'admin', '访问我的桌面', 9, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=138, sysLogCount=388, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 21:46:57');
INSERT INTO `sys_log` VALUES (390, 'admin', '查看近七日登录统计图', 3, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:46:58');
INSERT INTO `sys_log` VALUES (391, 'admin', '获取菜单列表', 14, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:46:58');
INSERT INTO `sys_log` VALUES (392, 'admin', '访问我的桌面', 16, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=138, sysLogCount=391, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 21:47:47');
INSERT INTO `sys_log` VALUES (393, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:47:47');
INSERT INTO `sys_log` VALUES (394, 'admin', '获取菜单列表', 3, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:47:48');
INSERT INTO `sys_log` VALUES (395, 'admin', '访问我的桌面', 15, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=138, sysLogCount=394, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 21:48:22');
INSERT INTO `sys_log` VALUES (396, 'admin', '查看近七日登录统计图', 5, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:48:22');
INSERT INTO `sys_log` VALUES (397, 'admin', '获取菜单列表', 1, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:48:23');
INSERT INTO `sys_log` VALUES (398, 'admin', '访问我的桌面', 13, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=138, sysLogCount=397, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 21:48:45');
INSERT INTO `sys_log` VALUES (399, 'admin', '查看近七日登录统计图', 3, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:48:46');
INSERT INTO `sys_log` VALUES (400, 'admin', '获取菜单列表', 2, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:48:46');
INSERT INTO `sys_log` VALUES (401, 'admin', '访问我的桌面', 12, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=138, sysLogCount=400, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 21:49:03');
INSERT INTO `sys_log` VALUES (402, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:49:03');
INSERT INTO `sys_log` VALUES (403, 'admin', '获取菜单列表', 2, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:49:04');
INSERT INTO `sys_log` VALUES (404, 'admin', '访问我的桌面', 14, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=138, sysLogCount=403, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 21:49:15');
INSERT INTO `sys_log` VALUES (405, 'admin', '查看近七日登录统计图', 3, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:49:15');
INSERT INTO `sys_log` VALUES (406, 'admin', '获取菜单列表', 1, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:49:16');
INSERT INTO `sys_log` VALUES (407, 'admin', '访问我的桌面', 8, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=138, sysLogCount=406, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 21:49:25');
INSERT INTO `sys_log` VALUES (408, 'admin', '查看近七日登录统计图', 3, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:49:26');
INSERT INTO `sys_log` VALUES (409, 'admin', '获取菜单列表', 1, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:49:26');
INSERT INTO `sys_log` VALUES (410, 'admin', '访问我的桌面', 10, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=138, sysLogCount=409, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 21:49:27');
INSERT INTO `sys_log` VALUES (411, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:49:28');
INSERT INTO `sys_log` VALUES (412, 'admin', '获取菜单列表', 1, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:49:28');
INSERT INTO `sys_log` VALUES (413, 'admin', '访问我的桌面', 9, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=138, sysLogCount=412, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 21:49:42');
INSERT INTO `sys_log` VALUES (414, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:49:42');
INSERT INTO `sys_log` VALUES (415, 'admin', '获取菜单列表', 3, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:49:43');
INSERT INTO `sys_log` VALUES (416, 'admin', '访问我的桌面', 9, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=138, sysLogCount=415, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 21:50:05');
INSERT INTO `sys_log` VALUES (417, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:50:05');
INSERT INTO `sys_log` VALUES (418, 'admin', '获取菜单列表', 2, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:50:06');
INSERT INTO `sys_log` VALUES (419, 'admin', '访问我的桌面', 11, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=138, sysLogCount=418, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 21:50:19');
INSERT INTO `sys_log` VALUES (420, 'admin', '查看近七日登录统计图', 3, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:50:20');
INSERT INTO `sys_log` VALUES (421, 'admin', '获取菜单列表', 2, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:50:20');
INSERT INTO `sys_log` VALUES (422, 'admin', '访问我的桌面', 10, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=138, sysLogCount=421, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 21:50:29');
INSERT INTO `sys_log` VALUES (423, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:50:30');
INSERT INTO `sys_log` VALUES (424, 'admin', '获取菜单列表', 2, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:50:30');
INSERT INTO `sys_log` VALUES (425, 'admin', '访问我的桌面', 11, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=138, sysLogCount=424, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 21:50:52');
INSERT INTO `sys_log` VALUES (426, 'admin', '查看近七日登录统计图', 3, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:50:52');
INSERT INTO `sys_log` VALUES (427, 'admin', '获取菜单列表', 2, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:50:53');
INSERT INTO `sys_log` VALUES (428, 'admin', '访问我的桌面', 10, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=138, sysLogCount=427, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 21:51:01');
INSERT INTO `sys_log` VALUES (429, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:51:02');
INSERT INTO `sys_log` VALUES (430, 'admin', '获取菜单列表', 2, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:51:02');
INSERT INTO `sys_log` VALUES (431, 'admin', '访问我的桌面', 14, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=138, sysLogCount=430, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 21:51:18');
INSERT INTO `sys_log` VALUES (432, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:51:19');
INSERT INTO `sys_log` VALUES (433, 'admin', '获取菜单列表', 1, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:51:19');
INSERT INTO `sys_log` VALUES (434, 'admin', '访问我的桌面', 8, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=138, sysLogCount=433, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 21:51:32');
INSERT INTO `sys_log` VALUES (435, 'admin', '查看近七日登录统计图', 2, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:51:32');
INSERT INTO `sys_log` VALUES (436, 'admin', '获取菜单列表', 1, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:51:32');
INSERT INTO `sys_log` VALUES (437, 'admin', '访问我的桌面', 6, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=138, sysLogCount=436, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 21:51:45');
INSERT INTO `sys_log` VALUES (438, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:51:46');
INSERT INTO `sys_log` VALUES (439, 'admin', '获取菜单列表', 2, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:51:46');
INSERT INTO `sys_log` VALUES (440, 'admin', '访问我的桌面', 8, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=138, sysLogCount=439, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 21:51:47');
INSERT INTO `sys_log` VALUES (441, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:51:47');
INSERT INTO `sys_log` VALUES (442, 'admin', '获取菜单列表', 2, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:51:48');
INSERT INTO `sys_log` VALUES (443, 'admin', '访问我的桌面', 7, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=138, sysLogCount=442, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 21:52:07');
INSERT INTO `sys_log` VALUES (444, 'admin', '查看近七日登录统计图', 3, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:52:08');
INSERT INTO `sys_log` VALUES (445, 'admin', '获取菜单列表', 2, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:52:08');
INSERT INTO `sys_log` VALUES (446, 'admin', '访问我的桌面', 11, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=138, sysLogCount=445, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 21:52:21');
INSERT INTO `sys_log` VALUES (447, 'admin', '查看近七日登录统计图', 3, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:52:21');
INSERT INTO `sys_log` VALUES (448, 'admin', '获取菜单列表', 3, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:52:22');
INSERT INTO `sys_log` VALUES (449, 'admin', '访问我的桌面', 10, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=138, sysLogCount=448, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 21:52:36');
INSERT INTO `sys_log` VALUES (450, 'admin', '查看近七日登录统计图', 3, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:52:37');
INSERT INTO `sys_log` VALUES (451, 'admin', '获取菜单列表', 2, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:52:37');
INSERT INTO `sys_log` VALUES (452, 'admin', '访问我的桌面', 6, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=138, sysLogCount=451, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 21:52:38');
INSERT INTO `sys_log` VALUES (453, 'admin', '查看近七日登录统计图', 2, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:52:40');
INSERT INTO `sys_log` VALUES (454, 'admin', '获取菜单列表', 2, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:52:40');
INSERT INTO `sys_log` VALUES (455, 'admin', '访问我的桌面', 8, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=138, sysLogCount=454, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 21:52:49');
INSERT INTO `sys_log` VALUES (456, 'admin', '查看近七日登录统计图', 4, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:52:50');
INSERT INTO `sys_log` VALUES (457, 'admin', '获取菜单列表', 1, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:52:50');
INSERT INTO `sys_log` VALUES (458, 'admin', '访问我的桌面', 7, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=138, sysLogCount=457, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 21:52:51');
INSERT INTO `sys_log` VALUES (459, 'admin', '查看近七日登录统计图', 3, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:52:52');
INSERT INTO `sys_log` VALUES (460, 'admin', '获取菜单列表', 1, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:52:52');
INSERT INTO `sys_log` VALUES (461, 'admin', '访问我的桌面', 6, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=138, sysLogCount=460, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 21:53:00');
INSERT INTO `sys_log` VALUES (462, 'admin', '查看近七日登录统计图', 3, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:53:00');
INSERT INTO `sys_log` VALUES (463, 'admin', '获取菜单列表', 3, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:53:00');
INSERT INTO `sys_log` VALUES (464, 'admin', '访问我的桌面', 9, 'im.zhaojun.controller.IndexController.welcome()', '  model: {userCount=2, roleCount=2, menuCount=19, loginLogCount=138, sysLogCount=463, userOnlineCount=1}', '0:0:0:0:0:0:0:1', '2018-12-23 21:53:10');
INSERT INTO `sys_log` VALUES (465, 'admin', '查看近七日登录统计图', 3, 'im.zhaojun.controller.IndexController.recentlyWeekLoginCount()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:53:11');
INSERT INTO `sys_log` VALUES (466, 'admin', '获取菜单列表', 3, 'im.zhaojun.controller.MenuController.getList()', '', '0:0:0:0:0:0:0:1', '2018-12-23 21:53:11');

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
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'f51703256a38e6bab3d9410a070c32ea', 'salt', 'root@zhaojun.im', 1, '2018-12-23 21:33:51', '2018-12-02 07:30:52', '2018-12-23 21:33:51', NULL);
INSERT INTO `user` VALUES (20, 'user', '2408794d39e51cb1771644a9bc07a0e4', '1543753139522', NULL, 0, '2018-12-23 20:22:48', '2018-12-02 12:18:59', '2018-12-23 20:22:48', NULL);

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
INSERT INTO `user_role` VALUES (1, 1);
INSERT INTO `user_role` VALUES (20, 1);

SET FOREIGN_KEY_CHECKS = 1;
