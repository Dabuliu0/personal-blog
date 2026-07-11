# 个人博客开发学习记录

## 2026-07-10：项目初始化

### 今天完成

-   创建个人博客 GitHub 仓库
-   配置 Git
-   完成第一次提交与推送
-   编写 README

### 今天学到

Git 工作流程：

``` text
修改文件 → git add → git commit → git push
```

------------------------------------------------------------------------

# 2026-07-11：文章业务完善与分层重构

## 今天完成

-   实现文章修改功能
-   增加创建时间和更新时间
-   完成标题和正文非空校验
-   创建 ArticleValidationException
-   调整项目包结构
-   完成 Service 与 Repository 分层

## 今天学习

### 封装

以前认为：

> 封装 = private 字段 + getter/setter

现在理解：

> 封装 = 对象保护自己的状态，不允许进入非法状态。

### 异常设计

RuntimeException 太泛。

新增：

``` text
ArticleValidationException
```

用于表达文章校验失败。

### Service 与 Repository 分层

重构前：

``` text
ArticleManager
```

同时负责数据和业务。

重构后：

``` text
ArticleService
    |
ArticleRepository
    |
Article
```

Service 负责业务流程。

Repository 负责数据访问。

Article 负责自身状态和规则。

## 下一步

完成文章删除功能。