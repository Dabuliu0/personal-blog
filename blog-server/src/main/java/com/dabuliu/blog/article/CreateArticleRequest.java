package com.dabuliu.blog.article;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateArticleRequest(
        @NotBlank(message = "标题不能为空")
        @Size(max = 100, message = "标题不能超过 100 个字符")
        String title,

        @NotBlank(message = "正文不能为空")
        @Size(max = 10000, message = "正文不能超过 10000 个字符")
        String content,

        boolean published,

        @NotNull(message = "分类 ID 不能为空")
        @Positive(message = "分类 ID 必须大于 0")
        Long categoryId) {
}