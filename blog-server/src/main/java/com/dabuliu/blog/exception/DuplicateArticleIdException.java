package com.dabuliu.blog.exception;

public class DuplicateArticleIdException extends RuntimeException {

    public DuplicateArticleIdException(long id) {
        super("文章 ID 已存在：" + id);
    }
}