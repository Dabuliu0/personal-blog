package com.dabuliu.blog.article;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateArticleRequest(
        @NotBlank(message = "标题不能为空")
        @Size(max = 100, message = "标题不能超过 100 个字符")
        String title,

        @NotBlank(message = "正文不能为空")
        @Size(max = 10000, message = "正文不能超过 10000 个字符")
        String content,

        boolean published) {
}