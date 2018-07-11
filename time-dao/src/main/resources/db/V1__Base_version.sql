SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for time_user
-- ----------------------------
DROP TABLE IF EXISTS time_user;
CREATE TABLE time_user (
	id INT(11) NOT NULL AUTO_INCREMENT,
	create_time DATETIME DEFAULT NULL,
	update_time DATETIME DEFAULT NULL,
	phone CHAR(11) NOT NULL DEFAULT '' comment '手机号',
	username VARCHAR(36) NOT NULL DEFAULT '' comment '账号',
	email VARCHAR(46) NOT NULL DEFAULT '' comment '邮箱',
	password VARCHAR(64) NOT NULL comment '密码',
	salt VARCHAR(32) NOT NULL comment '密码盐',
  avatar VARCHAR(100) NOT NULL DEFAULT '' comment '头像',
	administrator TINYINT(1) NOT NULL DEFAULT 0 comment '是否超级管理员 1超管',
	status TINYINT(4) NOT NULL DEFAULT 0 comment '账号状态 0正常',
	register_ip CHAR(15) NOT NULL DEFAULT '' comment '登陆ip',
	PRIMARY KEY (id),
	UNIQUE KEY (phone),
	UNIQUE KEY (username),
	UNIQUE KEY (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO time.time_user (id, create_time, update_time, phone, username, email, password, salt, avatar, administrator, status, register_ip) VALUES (1, '2018-07-11 07:27:45', '2018-07-11 07:27:51', '15865658745', 'admin', '15478541254@126.com', 'akskdfnakdfasmdfasdfsa', 'sdfsdf', 'dfgdgdfg', 1, 0, '192.168.2.12');

-- ----------------------------
-- Table structure for time_role
-- ----------------------------
DROP TABLE IF EXISTS time_role;
CREATE TABLE time_role (
	id INT(11) NOT NULL AUTO_INCREMENT,
	create_time DATETIME DEFAULT NULL,
	update_time DATETIME DEFAULT NULL,
	name VARCHAR(36) NOT NULL DEFAULT '' comment '角色名',
	priority INT(3) NOT NULL DEFAULT 0 comment '优先级',
	description VARCHAR(255) NOT NULL DEFAULT '' comment '描述',
	PRIMARY KEY (id),
	UNIQUE KEY (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for time_role_user
-- ----------------------------
DROP TABLE IF EXISTS time_role_user;
CREATE TABLE time_role_user (
	id INT(11) NOT NULL AUTO_INCREMENT,
	create_time DATETIME DEFAULT NULL,
	update_time DATETIME DEFAULT NULL,
	user_id INT(11) NOT NULL comment '用户id',
	role_id INT(11) NOT NULL comment '角色id',
	PRIMARY KEY (id),
	UNIQUE KEY (role_id, user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for time_permission
-- ----------------------------
DROP TABLE IF EXISTS time_permission;
CREATE TABLE time_permission (
	id INT(11) NOT NULL AUTO_INCREMENT,
	create_time DATETIME DEFAULT NULL,
	update_time DATETIME DEFAULT NULL,
	role_id INT(11) NOT NULL,
	resource_id INT(11) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE KEY (role_id, resource_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;