# Shiro-Action

本项目使用 Spring Boot 搭建, 用于加深对 Spring Boot 与 Shiro 的学习.

预览地址: [http://122.152.195.194/login](http://122.152.195.194/login)
默认管理员账号: `admin`, 密码: `123456`.
普通用户账号: `user`, 密码: `123456`.

> 为了不影响其他人的浏览体验, 请尽量不要进行删除类的敏感操作.

## 技术选型

### 后端
* 基础框架：Spring Boot 2.0.6.RELEASE
* 持久层框架：Mybatis 3.4.6
* 安全框架：Apache Shiro 1.4.0
摸板引擎：Thymeleaf 3.0.9.RELEASE
数据库连接池：Druid 1.1.10
缓存框架：Redis
日志打印：logback

### 前端
* 基本模板: [X-Admin](http://x.xuebingsi.com/)
* 弹出层: Layer

## 接口返回格式

### 新增
```json
{
    "code": 0,
    "msg": "success",
    "data": 5,
}
```

### 删除&修改
```json
{
    "code": 0,
    "msg": "success",
    "data": "true/false",
}
```

### 获取单个
```json
{
    "code": 0,
    "msg": "success",
    "data": {
        "username": "abc",
        "age": 28
    }
}
```

### 获取所有
```json
{
    "code": 0,
    "msg": "success",
    "data": [
        {
            "userId": 1,
            "username": "admin",
            "email": "root@zhaojun.im"
        },
        {
            "userId": 2,
            "username": "user",
            "email": "xxx@zhaojun.im"
        }
    ]
}
```

### 分页获取
```json
{
    "count": 2,
    "code": 0,
    "data": [
        {
            "userId": 1,
            "username": "admin",
            "email": "root@zhaojun.im"
        },
        {
            "userId": 2,
            "username": "user",
            "email": "xxx@zhaojun.im"
        }
    ]
}
```

## 前端处理
```javascript

/**
 * 显示错误信息
 * @param result： 错误信息
 */
function showError(s) {
    layer.msg(s);
}

/**
 * 处理 ajax 请求结果
 * @param result： ajax 返回的结果
 * @param fn： 成功的处理函数 ( 传入data: fn(result.data) )
 */
function handlerResult(result, fn) {
    // 成功执行操作，失败提示原因
    if (result.code == 0) {
        fn(result.data);
    }
    // 没有登陆异常，重定向到登陆页面
    else if (result.code == -1) {
        //showError("没有登录");
        $("#logindlg").modal('show');
    }
    // 参数校验出错，直接提示
    else if (result.code == 1) {
        showError(result.msg);
    }
    // 没有权限，显示申请权限电子流
    else if (result.code == 2) {
        showError("没有权限");
    } else {
        // 不应该出现的异常，应该重点关注
        showError(result.msg);
    }
}

/**
 * 根据id删除配置项
 */
function deleteConfig(id) {
    $.delete('config/delete', {
        id: id
    }, function (result) {
        console.log('delete result', result);
        handlerResult(result, fetchAllConfigs);
    });
}
```