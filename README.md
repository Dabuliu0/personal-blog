# Personal Blog

一个用于学习全栈开发的个人博客项目。

项目采用前后端分离架构，计划实现博客前台、管理后台、后端接口、数据库和服务器部署。开发过程会同步记录在 GitHub 中。

## 技术栈

- 前端：Vue 3、TypeScript、Vite
- 后端：Java、Spring Boot
- 数据库：MySQL
- 部署：Docker、Nginx、Linux
- 版本管理：Git、GitHub

## 计划功能

### 博客前台

- 博客首页
- 文章列表
- 文章详情
- 分类与标签
- 文章搜索
- 关于我

### 管理后台

- 管理员登录
- 文章管理
- 分类管理
- 标签管理

## 项目结构

```text
personal-blog
├── blog-web          前端项目
├── blog-server       后端项目
├── sql               数据库脚本
├── deploy            部署配置
├── docs              学习与开发记录
├── README.md
└── .gitignore

## 已实现的后端功能

- 文章新增、查询、更新、软删除与恢复
- 文章标题搜索、分页和排序
- 分类创建、查询与分类名称去重
- 文章与分类的多对一关联
- 用户注册：用户名、邮箱去重，密码使用 BCrypt 摘要保存
- JWT 登录：登录成功返回 Bearer Token
- Spring Security 接口保护
- 文章作者关联：仅文章作者可修改或删除文章
- JUnit 单元测试与 JWT 篡改测试

## 后端本地运行

前提：

- MySQL 服务已启动
- 已创建数据库 `blog_db`
- 已创建应用数据库用户 `blog_app`

在 PowerShell 中：

```powershell
cd E:\Projects_java\personal-blog\blog-server
$env:DB_PASSWORD = '<blog_app 的数据库密码>'
$env:JWT_SECRET = [guid]::NewGuid().ToString() + [guid]::NewGuid().ToString()
mvn spring-boot:run
```

本地运行测试：

```powershell
cd E:\Projects_java\personal-blog\blog-server
mvn test
```

> 不要把真实 `DB_PASSWORD` 或 `JWT_SECRET` 写入代码、README 或 Git。每次重新生成 `JWT_SECRET` 后，之前签发的 Token 会失效。

## API 摘要

服务默认地址：

```text
http://localhost:8080
```

公开接口：

- `POST /users/register`：用户注册
- `POST /users/login`：用户名密码登录，成功后返回 JWT
- `GET /articles?page=0&size=10`：文章分页查询
- `GET /articles/search?keyword=Java&page=0&size=10`：文章标题搜索
- `GET /articles/{id}`：文章详情
- `GET /categories`：分类列表

需要 JWT 的接口：

- `POST /articles`：创建文章
- `PUT /articles/{id}`：更新文章，仅作者可操作
- `DELETE /articles/{id}`：软删除文章，仅作者可操作
- `POST /categories`：创建分类

受保护接口请求头：

```http
Authorization: Bearer <登录接口返回的 token>
```

错误状态说明：

- `400`：请求参数或业务规则错误
- `401`：未登录、密码错误或 Token 无效
- `403`：已登录，但不是文章作者
- `404`：文章不存在