# Shiro-Action
![https://img.shields.io/badge/license-MIT-blue.svg?style=flat-square](https://img.shields.io/badge/license-MIT-blue.svg?longCache=true&style=flat-square)
![https://img.shields.io/badge/springboot-2.0.6-orange.svg?style=flat-square](https://img.shields.io/badge/springboot-2.0.4-yellow.svg?longCache=true&style=popout-square)
![https://img.shields.io/badge/apache%20shiro-1.4.0-green.svg?style=flat-square](https://img.shields.io/badge/apache%20shiro-1.4.0-green.svg?longCache=true&style=flat-square)
![GitHub tag (latest SemVer)](https://img.shields.io/github/tag/zhaojun1998/Shiro-Action.svg?style=flat-square)

本项目使用 Spring Boot 搭建, 用于加深对 Spring Boot 与 Shiro 的学习, 项目特色是支持 restful 风格权限控制, 支持对同一 URL, 不同 HTTP Mehtod 的权限控制, 适用于更多的场景.

预览地址 (): [http://shiro.zhaojun.im/](http://shiro.zhaojun.im/)
备用地址 (国内): [https://shiro.jun6.net/](https://shiro.jun6.net/login)
默认管理员账号: `admin`, 密码: `123456`.
普通用户账号: `user`, 密码: `123456`.

> 为了不影响其他人的浏览体验, 请尽量不要进行删除类的敏感操作.  `admin` 为超级管理员, 自动拥有全部权限.

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

![](https://cdn.jun6.net/201904032326_720.png)

![](https://cdn.jun6.net/201904032327_602.png)

![](https://cdn.jun6.net/201904032327_107.png)

![](https://cdn.jun6.net/201904032327_936.png)

![](https://cdn.jun6.net/201904032328_32.png)

![](https://cdn.jun6.net/201904142201_744.png)

![](https://cdn.jun6.net/2019/04/14/5cb340e86aacb.png)

![](https://cdn.jun6.net/2019/04/14/5cb34129083c8.png)

### 反馈交流

QQ 群号: [850586205](//shang.qq.com/wpa/qunwpa?idkey=b482b2e48a938a993ef54adcff7ed499a45127a73802a68f1238256fb02ed425)

![](https://cdn.jun6.net/201904141340_574.png)