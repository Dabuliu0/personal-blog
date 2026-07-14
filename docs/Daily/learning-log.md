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

------------------------------------------------------------------------

## 2026-07-12：文章软删除与查询规则

### 今日完成

1. 明确了 Article、Repository、Service 的职责：
   - Article 管理单篇文章自身的状态和合法性
   - Repository 负责数据的保存与查询
   - Service 负责组织完整业务流程

2. 为 Article 增加软删除功能：
   - 新增 `deleted` 字段
   - 新增 `delete()` 方法
   - 新增 `restore()` 方法
   - 新增 `isDeleted()` 方法
   - 重复删除和重复恢复时抛出异常
   - 删除与恢复时同步更新 `updatedTime`

3. 在 ArticleService 中实现删除与恢复流程：
   - 根据文章 ID 查找文章
   - 找不到文章时抛出 `ArticleNotFoundException`
   - 调用 Article 自身的方法改变删除状态
   - 当前使用内存 List，修改的是原对象引用，因此不需要再次保存

4. 完善普通查询规则：
   - `findById()` 不返回已删除文章
   - `findAllArticles()` 只返回未删除文章
   - 文章总数不统计已删除文章
   - 已发布文章查询不包含已删除文章
   - 标题搜索不包含已删除文章
   - 已删除文章不能被修改

5. 增加重复 ID 校验：
   - 添加文章前检查 Repository 中是否存在相同 ID
   - 软删除文章的 ID 仍然不能重复使用
   - 新增 `DuplicateArticleIdException`

6. 为文章列表增加默认排序：
   - 普通文章列表按照 ID 从小到大排列

7. 整理 Main 测试代码：
   - 测试重复 ID
   - 测试软删除与恢复
   - 测试查询过滤
   - 测试文章排序
   - 将变量名 `server` 修正为 `service`

### 今日理解

- Article 负责“自己如何变化”。
- Repository 负责“数据怎么找到和保存”。
- Service 负责“整个业务流程如何完成”。
- `List<Article>` 保存的是对象引用，查询到对象后修改它，列表中的对象也会同步变化。
- `Article::getTitle` 只等价于 `article -> article.getTitle()`，无法直接表示带有 `contains(keyword)` 的复合逻辑。
- `super(...)` 用于调用父类构造方法，并把自定义异常信息交给父类保存。

### Git 提交

- 分支：`feat/article-backend`
- 提交：`575c7c0`
- 提交信息：`feat: add article soft delete and query rules`

### 下一步计划

- 使用 JUnit 编写自动化测试
- 将 ArticleRepository 改造成接口，并增加内存实现类
- 开始搭建 Spring Boot 项目骨架
- 实现第一个文章查询接口 `GET /articles`