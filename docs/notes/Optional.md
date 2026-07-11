# Optional

## 在博客项目中的场景

根据文章 ID 查询文章时，文章可能存在，也可能不存在。

传统 null：

``` java
Article article = findById(99);
article.getTitle();
```

可能导致 NullPointerException。

## Optional 表达什么？

Optional 表示：

> 这个结果可能存在，也可能不存在。

例如：

``` java
Optional<Article>
```

## 推荐写法

``` java
Article article = findById(id)
        .orElseThrow();
```

含义：

找到： 返回 Article。

没有找到： 抛出异常。

## 我的理解

Optional 不是为了消灭 null。

它是提醒开发者：

这个数据可能不存在。