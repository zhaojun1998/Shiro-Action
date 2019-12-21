# Shiro-Action

[![Build Status](https://travis-ci.org/zhaojun1998/Shiro-Action.svg?branch=master)](https://travis-ci.org/zhaojun1998/Shiro-Action)
![https://img.shields.io/badge/license-MIT-blue.svg?style=flat-square](https://img.shields.io/badge/license-MIT-blue.svg?longCache=true&style=flat-square)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/3b39480c887b42f1875c0210817b500f)](https://www.codacy.com/app/zhaojun1998/Shiro-Action?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=zhaojun1998/Shiro-Action&amp;utm_campaign=Badge_Grade)
![https://img.shields.io/badge/springboot-2.0.6-orange.svg?style=flat-square](https://img.shields.io/badge/springboot-2.0.6-yellow.svg?longCache=true&style=popout-square)
![https://img.shields.io/badge/apache%20shiro-1.4.0-green.svg?style=flat-square](https://img.shields.io/badge/apache%20shiro-1.4.0-green.svg?longCache=true&style=flat-square)
![GitHub tag (latest SemVer)](https://img.shields.io/github/tag/zhaojun1998/Shiro-Action.svg?style=flat-square)

本项目使用 Spring Boot 搭建, 用于加深对 Spring Boot 与 Shiro 的学习, 项目特色是支持 restful 风格权限控制, 支持对同一 URL, 不同 HTTP Mehtod 的权限控制, 适用于更多的场景.

预览地址: [http://shiro.jun6.net/](http://shiro.jun6.net/)

文档地址: [http://docs.zhaojun.im/shiro/](http://docs.zhaojun.im/shiro/)

默认管理员账号: `admin`, 密码: `123456`.

普通用户账号: `user`, 密码: `123456`.

> 为了不影响其他人的浏览体验, 请尽量不要进行删除类的敏感操作.  `admin` 为超级管理员, 自动拥有全部权限.

## 系统特色

* 支持根据同 URL, 不同 HTTP Method 来校验权限, 更支持 restful 场景.
* 集成 OAuth2 登录, 且提供了接口易于拓展开发.
* 全局异常处理. 根据请求方式区分返回 json 数据还是错误页面.
* Logback MDC 支持, 将当前登录人和操作者 IP 加入日志中.
* JSR-303 数据校验

## 运行环境

* JDK 1.8
* MySQL 5.7
* Redis

## 快速开始

### 下载项目

```git
git clone https://github.com/zhaojun1998/Shiro-Action.git
```

### 导入项目

使用自己的 IDE 导入, Eclipse 和 Intellij IDEA 均可.

### 导入数据库

创建数据库, 字符集选择 `utf8`, 排序规则选择 `utf8_general_ci`.

然后导入 `shiro_action.sql` 到数据库中.

### 配置文件

打开 `application.properties` 修改 `MySQL` 和 `Redis` 连接信息.

```properties
spring.redis.host=127.0.0.1
spring.redis.port=6379
spring.cache.type=redis

spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/shiro_action?useSSL=false&characterEncoding=UTF8
```

### 启动项目
在完成了上述步骤后，找到 ShiroActionApplication 启动类, 启动即可.

启动后访问地址为：http://localhost:8080 , 用户名：admin, 密码：123456.

## 系统预览

![](https://cdn.jun6.net/201905292303_211.png)

![](https://cdn.jun6.net/201906302159_326.png)

![](https://cdn.jun6.net/2019/06/30/5d18c0a85b38a.png)

![](https://cdn.jun6.net/2019/06/30/5d18c0a849285.png)

![](https://cdn.jun6.net/2019/06/30/5d18c1e09d314.png)

![](https://cdn.jun6.net/2019/06/30/5d18c1ad27580.png)

![](https://cdn.jun6.net/2019/06/30/5d18c1ad29d05.png)

### 技术选型

#### 前端

* 前端框架: [Layui](https://www.layui.com/)
* 后台模板: [Z-Admin](https://github.com/zhaojun1998/Z-Admin/)

#### 后端

* SpringBoot 2.0.6.RELEASE
* Shiro 1.4.0
* MyBatis 1.3.2
* Druid 1.1.10
* PageHelper 1.2.9
* Shiro-Redis 3.2.3

#### 其他工具类

* OAuth2 认证工具类: [JustAuth](https://gitee.com/yadong.zhang/JustAuth)
* Hutool : [https://hutool.cn/](https://hutool.cn/)

### 反馈交流

QQ 群号: [850586205](//shang.qq.com/wpa/qunwpa?idkey=b482b2e48a938a993ef54adcff7ed499a45127a73802a68f1238256fb02ed425)

![](https://cdn.jun6.net/201904141340_574.png)
