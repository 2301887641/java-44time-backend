-- ----------------------------
-- Table structure for time_user
-- ----------------------------
DROP TABLE IF EXISTS time_user;
CREATE TABLE time_user (
	id INT(11) NOT NULL AUTO_INCREMENT,
	create_time timestamp DEFAULT 0,
	update_time timestamp DEFAULT 0,
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
-- Table structure for t_organization
-- ----------------------------
DROP TABLE IF EXISTS time_organization;
CREATE TABLE time_organization (
	id INT(11) NOT NULL AUTO_INCREMENT,
	create_time timestamp DEFAULT 0,
	update_time timestamp DEFAULT 0,
	name VARCHAR(36) NOT NULL,
	level INT(3) DEFAULT NULL comment '级别',
	priority INT(3) DEFAULT 0 comment '优先级',
	path VARCHAR(250) DEFAULT '|' NOT NULL comment '路径 记录父id 用|分割',
	parent_id INT(11) DEFAULT 0 comment '父类id',
	enable TINYINT(1) DEFAULT 0 COMMENT '是否启用',
	description varchar(255) DEFAULT NULL comment '描述',
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for time_role
-- ----------------------------
DROP TABLE IF EXISTS time_role;
CREATE TABLE time_role (
	id INT(11) NOT NULL AUTO_INCREMENT,
	create_time timestamp DEFAULT 0,
	update_time timestamp DEFAULT 0,
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
	create_time timestamp DEFAULT 0,
	update_time timestamp DEFAULT 0,
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
	create_time timestamp DEFAULT 0,
	update_time timestamp DEFAULT 0,
	role_id INT(11) NOT NULL,
	resource_id INT(11) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE KEY (role_id, resource_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for time_resource
-- ----------------------------
DROP TABLE IF EXISTS time_resource;
create table time_resource(
	id INT(11) auto_increment primary key,
	create_time timestamp DEFAULT 0,
	update_time timestamp DEFAULT 0,
	level INT(3) DEFAULT NULL comment '级别',
	path varchar(250) default '|' not null comment '路径 记录父id 用|分割',
	name varchar(36) default '' not null comment '菜单或方法名称',
	code varchar(100) default '' not null comment '权限路由',
	description varchar(255) default '' not null comment '描述',
	icon varchar(30) default '' not null comment '图标',
	resource_type int default 0 not null comment '资源类型 0 模块  1操作 模块就是可以有下级，操作就是增删改查 ',
	url varchar(255) default '' not null comment '页面访问url',
	parent_id INT(11) default 0 not null comment '父id',
	priority INT(11) default 0 not null comment '优先级'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS time_operation_log;
CREATE TABLE time_operation_log (
  id INT(11) NOT NULL AUTO_INCREMENT,
  create_time timestamp DEFAULT 0,
  log_type TINYINT(1) NOT NULL DEFAULT 1 comment '1:业务日志',
  title VARCHAR(64) NOT NULL DEFAULT '' comment '操作事项',
  content VARCHAR(255) NOT NULL DEFAULT '' comment '操作内容',
  ip CHAR(15) NOT NULL DEFAULT '0' comment 'ip',
  user_id INT(11) NOT NULL DEFAULT 0 comment '用户id',
  classname varchar(255) NOT NULL DEFAULT '' comment '类名称',
  method varchar(255) NOT NULL DEFAULT '' comment '方法名称',
  result varchar(20) NOT NULL DEFAULT '成功' comment '是否成功',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;