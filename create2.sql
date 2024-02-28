-- ----------------------------
-- 创建user表
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(255) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(1023) NOT NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(1023) NOT NULL DEFAULT 'SALT' COMMENT '盐',
  `authority_name` varchar(63) DEFAULT '' COMMENT '权限名称，ROLE_开头，全大写',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `channel` int(1) NOT NULL DEFAULT '0' COMMENT '渠道',
  `product_line_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '业务线',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `gmt_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=677 DEFAULT CHARSET=utf8 COMMENT='用户信息';


-- ----------------------------
-- 创建case_backup
-- ----------------------------
DROP TABLE IF EXISTS `case_backup`;
CREATE TABLE `case_backup` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `case_id` bigint NOT NULL DEFAULT '0' COMMENT '用例集id',
  `title` varchar(64) NOT NULL DEFAULT '' COMMENT '用例名称',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '用例保存人',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '用例保存时间',
  `case_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `record_content` longtext COMMENT '任务执行内容',
  `extra` varchar(256) NOT NULL DEFAULT '' COMMENT '扩展字段',
  `is_delete` int NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_caseId` (`case_id`)
) ENGINE=InnoDB AUTO_INCREMENT=717 DEFAULT CHARSET=utf8mb3 COMMENT='测试备份';


-- ----------------------------
-- 创建exec_record
-- ----------------------------
DROP TABLE IF EXISTS `exec_record`;
CREATE TABLE `exec_record` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `case_id` bigint NOT NULL DEFAULT '0' COMMENT '执行的用例id',
  `title` varchar(64) NOT NULL DEFAULT '' COMMENT '用例名称',
  `env` int NOT NULL DEFAULT '0' COMMENT '执行环境： 0、测试环境 1、预发环境 2.线上环境 3.冒烟qa 4.冒烟rd',
  `case_content` longtext COMMENT '任务执行内容',
  `is_delete` int NOT NULL DEFAULT '0' COMMENT '用例状态 0-正常 1-删除',
  `pass_count` int NOT NULL DEFAULT '0' COMMENT '执行个数',
  `total_count` int NOT NULL DEFAULT '0' COMMENT '需执行总个数',
  `success_count` int NOT NULL DEFAULT '0' COMMENT '成功个数',
  `ignore_count` int NOT NULL DEFAULT '0' COMMENT '不执行个数',
  `block_count` int NOT NULL DEFAULT '0' COMMENT '阻塞个数',
  `fail_count` int NOT NULL DEFAULT '0' COMMENT '失败个数',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '用例创建人',
  `modifier` varchar(20) NOT NULL DEFAULT '' COMMENT '用例修改人',
  `executors` varchar(200) NOT NULL DEFAULT '' COMMENT '执行人',
  `description` varchar(1000) NOT NULL DEFAULT '' COMMENT '描述',
  `choose_content` varchar(200) NOT NULL DEFAULT '' COMMENT '圈选用例内容',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录修改时间',
  `expect_start_time` timestamp NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '预计开始时间',
  `expect_end_time` timestamp NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '预计结束时间',
  `actual_start_time` timestamp NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '实际开始时间',
  `actual_end_time` timestamp NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '实际结束时间',
  `owner` varchar(200) NOT NULL DEFAULT '' COMMENT '负责人',
  PRIMARY KEY (`id`),
  KEY `idx_caseId_isdelete` (`case_id`,`is_delete`)
) ENGINE=InnoDB AUTO_INCREMENT=898 DEFAULT CHARSET=utf8mb3 COMMENT='用例执行记录';


-- ----------------------------
-- 创建test_case
-- ----------------------------
DROP TABLE IF EXISTS `test_case`;
CREATE TABLE test_case (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `step_number` INT NOT NULL DEFAULT '1' COMMENT '操作步骤ID',
  `pre_condition` varchar(1000)  NULL DEFAULT NULL COMMENT '操作步骤',
  `actions` varchar(1000)  NULL DEFAULT NULL COMMENT '操作步骤',
  `expected_results` varchar(1000)  NULL DEFAULT NULL COMMENT '预期结果',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `biz_id` varchar(500) default '-1' not null comment '关联的文件夹id',
  `del_flag` tinyint(4) DEFAULT NULL COMMENT '删除标志 1 未删除 0 已删除',
  `create_user_id` int(11) NOT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `execution_type` INT NOT NULL DEFAULT '1' COMMENT '执行类型：1功能，2接口，3UI自动化，4性能',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6329 DEFAULT CHARSET=utf8mb4 COMMENT='测试用例信息';


-- DROP TABLE IF EXISTS `test_case`;
-- CREATE TABLE `test_case` (
--   `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
--   `group_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用例集id',
--   `title` varchar(64) NOT NULL DEFAULT 'testcase' COMMENT '用例名称',
--   `description` varchar(512) NOT NULL DEFAULT '' COMMENT '用例描述',
--   `is_delete` int(11) NOT NULL DEFAULT '0' COMMENT '用例状态 0-正常 1-删除',
--   `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '用例创建人',
--   `modifier` varchar(1000) NOT NULL DEFAULT '' COMMENT '用例修改人',
--   `biz_id` varchar(50) NOT NULL DEFAULT '-1' COMMENT '关联的文件夹id',
--   `case_content` longtext CHARACTER SET utf8mb4,
--   `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
--   `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
--   `extra` varchar(256) NOT NULL DEFAULT '' COMMENT '扩展字段',
--   `product_line_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '业务线id 默认0',
--   `case_type` int(11) NOT NULL DEFAULT '0' COMMENT '0-需求用例，1-核心用例，2-冒烟用例',
--   `module_node_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '模块节点id',
--   `requirement_id` varchar(1000) NOT NULL DEFAULT '0' COMMENT '需求id',
--   `smk_case_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '冒烟case的id',
--   `channel` int(11) NOT NULL DEFAULT '0' COMMENT '渠道标志 现默认1',
--   PRIMARY KEY (`id`),
--   KEY `idx_productline_isdelete` (`product_line_id`,`is_delete`),
--   KEY `idx_requirement_id` (`requirement_id`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=2207 DEFAULT CHARSET=utf8 COMMENT='测试用例';


-- ----------------------------
-- 创建biz
-- ----------------------------
DROP TABLE IF EXISTS `biz`;
CREATE TABLE `biz` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '文件夹主键',
  `product_line_id` bigint NOT NULL DEFAULT '0' COMMENT '业务线名称',
  `content` mediumtext NOT NULL COMMENT '文件数内容',
  `channel` int NOT NULL DEFAULT '0' COMMENT '渠道',
  `is_delete` int NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文件夹';

INSERT INTO TABLE 'biz' (id,product_line_id,content,channel,is_delete,gmt_create,gmt_modified)
VALUES (1,0,'ops',0,0, , );
-- ----------------------------
-- 创建authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `authority_name` varchar(63) NOT NULL DEFAULT '' COMMENT '权限名称，ROLE_开头，全大写',
  `authority_desc` varchar(255) NOT NULL DEFAULT '' COMMENT '权限描述',
  `authority_content` varchar(1023) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限内容，可访问的url，多个时用,隔开',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COMMENT='权限信息';


-- ----------------------------
-- 创建test_task
-- ----------------------------
DROP TABLE IF EXISTS `test_task`;
CREATE TABLE `test_task` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `create_user_id` int NOT NULL COMMENT '创建人id',
  `case_count` int DEFAULT NULL COMMENT '用例数量',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `test_command` text NOT NULL COMMENT 'Jenkins执行测试时的命令脚本',
  `task_type` tinyint NOT NULL DEFAULT '1' COMMENT '任务类型 1 单个执行测试任务 2 一键执行测试的任务',
  `status` int NOT NULL DEFAULT '1' COMMENT '用例状态 0 无效 1 未执行  2 执行成功  3 执行失败 4 阻碍',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=194 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='测试任务表';

-- ----------------------------
-- 创建test_task_case_rel
-- ----------------------------
DROP TABLE IF EXISTS `test_task_case_rel`;
CREATE TABLE `test_task_case_rel` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_user_id` int DEFAULT NULL COMMENT '创建人id',
  `task_id` int DEFAULT NULL COMMENT '任务id',
  `case_id` int DEFAULT NULL COMMENT '用例id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=241 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='测试任务表';

-- ----------------------------
-- 创建test_case_group
-- ----------------------------
DROP TABLE IF EXISTS `test_case_group`;
CREATE TABLE `test_case_group` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `group_id` bigint NOT NULL DEFAULT '0' COMMENT '用例集id',
  `title` varchar(64) NOT NULL DEFAULT 'testcase' COMMENT '用例名称',
  `description` varchar(512) NOT NULL DEFAULT '' COMMENT '用例描述',
  `is_delete` int NOT NULL DEFAULT '0' COMMENT '用例状态 0-正常 1-删除',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '用例集创建人',
  `modifier` varchar(1000) NOT NULL DEFAULT '' COMMENT '用例集修改人',
  `case_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `extra` varchar(256) NOT NULL DEFAULT '' COMMENT '扩展字段',
  `product_line_id` bigint NOT NULL DEFAULT '0' COMMENT '业务线id 默认0',
  `case_type` int NOT NULL DEFAULT '0' COMMENT '0-需求用例，1-核心用例，2-冒烟用例',
  `module_node_id` bigint NOT NULL DEFAULT '0' COMMENT '模块节点id',
  `requirement_id` varchar(1000) NOT NULL DEFAULT '0' COMMENT '需求id',
  `smk_case_id` bigint NOT NULL DEFAULT '0' COMMENT '冒烟case的id',
  `channel` int NOT NULL DEFAULT '0' COMMENT '渠道标志 现默认1',
  `biz_id` varchar(500) NOT NULL DEFAULT '-1' COMMENT '关联的文件夹id',
  PRIMARY KEY (`id`),
  KEY `idx_productline_isdelete` (`product_line_id`,`is_delete`),
  KEY `idx_requirement_id` (`requirement_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2209 DEFAULT CHARSET=utf8mb3 COMMENT='测试用例集';

