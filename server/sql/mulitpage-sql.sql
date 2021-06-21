CREATE DATABASE IF NOT EXISTS `mulit_blog_datasource`;
use `mulit_blog_datasource`;

-- 管理员表
CREATE TABLE IF NOT EXISTS `zpwan_blog_admin` (
  `id` INTEGER (10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` VARCHAR (10) NOT NULL  DEFAULT '' COMMENT '管理员名',
  `salt` VARCHAR (50) NOT NULL  DEFAULT '' COMMENT '管理员加密',
  `password` VARCHAR (50) NOT NULL DEFAULT '1' COMMENT '管理员密码',
 `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
 `modify_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '修改时间',
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 文章表
CREATE TABLE IF NOT EXISTS `zpwan_blog_article` (
  `id` VARCHAR(50) NOT NULL COMMENT 'id',
  `title` VARCHAR(100) NOT NULL COMMENT '文章标题',
  `description` VARCHAR(100) DEFAULT '' COMMENT '文章描述',
  `content` longtext CHARACTER SET utf8mb4 NOT NULL  COMMENT '文章内容-代码',
  `md` longtext CHARACTER SET utf8mb4 NOT NULL COMMENT '文章内容-用于编辑',
  `imgUrl` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '文章封面',
  `tags` VARCHAR(500) CHARACTER SET utf8 NOT NULL COMMENT '文章tags数组',
  `first_catalog_id` varchar(50) NOT NULL DEFAULT '' COMMENT '一级目录id',
  `first_catalog_name` varchar(50) NOT NULL DEFAULT '' COMMENT '一级目录名称',
  `second_catalog_id` varchar(50) NOT NULL DEFAULT '' COMMENT '二级目录id',
  `second_catalog_name` varchar(50) NOT NULL DEFAULT '' COMMENT '二级目录名称',
  `status` INTEGER (10) NOT NULL DEFAULT '1' COMMENT '文章状态 0-被删除 1-未上架 2-已上架 3-推荐 4-置顶',
  `visit_count` int(50) NOT NULL DEFAULT '0' COMMENT '访问量',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `modify_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '修改时间',
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--   banner表
CREATE TABLE IF NOT EXISTS `zpwan_blog_banners`(
   `id` INTEGER(10) NOT NULL  AUTO_INCREMENT COMMENT 'id',
   `image_url` VARCHAR (255) NOT NULL COMMENT 'banner封面',
   `title` VARCHAR(50) NOT NULL COMMENT 'banner标题',
   `article_id` VARCHAR(50) NOT NULL COMMENT '所属文章',
   `state` INTEGER (10) NOT NULL  DEFAULT '0' COMMENT 'banner状态 0-未上架 1-已上架',
    PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 文件表
CREATE TABLE IF NOT EXISTS `zpwan_blog_file` (
  `id` VARCHAR (50) NOT NULL COMMENT 'id',
  `file_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '文件名',
  `md5` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'md5值',
  `url` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '地址',
  `length` bigint(10) NOT NULL DEFAULT '0' COMMENT '长度',
  `extension` VARCHAR (10) NOT NULL DEFAULT 'png' COMMENT '后缀名',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--  字典表
CREATE TABLE IF NOT EXISTS `zpwan_blog_dictionary` (
  `id` INTEGER(10) NOT NULL  AUTO_INCREMENT COMMENT 'id',
  `type` INTEGER (10) NOT NULL COMMENT '类别enum表获取',
  `title` VARCHAR(50) NOT NULL COMMENT '类别名称',
  `parent_id` INTEGER (10) NOT NULL DEFAULT '0' COMMENT '父级id 只有二级目录才有',
  `parent_title` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '父级类别名称',
  `description` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '描述',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `modify_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '修改时间',
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 枚举表
CREATE TABLE IF NOT EXISTS `zpwan_blog_enum` (
  `id` INTEGER(10) NOT NULL  AUTO_INCREMENT COMMENT 'id',
  `code` INTEGER (10) NOT NULL COMMENT '枚举code 0-tag 1-一级目录 2-二级目录 3-文章类别',
  `title` VARCHAR (20) NOT NULL COMMENT '枚举title',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `modify_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '修改时间',
   PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

