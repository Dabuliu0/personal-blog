package com.dabuliu.blog.exception;

public class ArticleNotFoundException extends RuntimeException {

    public ArticleNotFoundException(long id) {
        super("文章不存在，id：" + id);
    }
}
