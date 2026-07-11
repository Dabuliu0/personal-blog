package com.dabuliu.blog.exception;

public class ArticleValidationException extends RuntimeException {
//RuntimeException 运行时异常
    public ArticleValidationException(String message) {
        super(message);
    }

}