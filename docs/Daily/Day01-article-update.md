# 文章修改功能设计

## 今日需求

管理员可以修改文章。

## 业务流程

用户点击编辑按钮

↓

获取文章 id

↓

查询文章

↓

修改标题、正文、发布状态

↓

保存

## 学到的知识

### 封装

对象不允许外部随便修改自己的状态。

应该通过方法：

``` java
article.updateTitle()
```

控制修改。

### Optional

findById() 可能找不到文章。

使用：

``` java
orElseThrow()
```

表示找到继续，没有就停止。

## 最终实现

-   Article 负责自身状态修改
-   ArticleService 负责业务流程
-   ArticleRepository 负责数据访问
-   使用自定义异常处理文章校验失败

结构：

``` text
Main

↓

ArticleService

↓

ArticleRepository

↓

Article