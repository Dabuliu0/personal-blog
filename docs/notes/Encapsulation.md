# 封装（Encapsulation）

## 在博客项目中的场景

ArticleManager 需要修改 Article。

如果直接暴露字段：

``` java
article.title = "新的标题";
```

任何地方都可以修改数据，无法控制修改规则。

## 封装的作用

封装不是简单隐藏代码，而是控制对象状态变化。

应该：

``` java
article.updateTitle("Java进阶");
```

而不是直接修改字段。

这样可以在方法内部加入校验规则。

## Article 和 ArticleManager 职责

Article： - 保存自己的状态 - 控制自己的变化

ArticleManager： - 找到文章 - 管理业务流程

修改流程：

ArticleManager 找到 Article，然后调用 Article 的修改方法。

## 我的理解

封装的目的： - 限制错误修改 - 保证业务规则 - 明确谁负责修改数据