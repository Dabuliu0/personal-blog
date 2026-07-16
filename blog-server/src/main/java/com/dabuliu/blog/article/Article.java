package com.dabuliu.blog.article;

import java.time.LocalDateTime;
import com.dabuliu.blog.exception.ArticleValidationException;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.dabuliu.blog.category.Category;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private boolean published;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public boolean isDeleted() {
        return deleted;
    }

    public Category getCategory() {
        return category;
    }

    public void assignCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("文章分类不能为空");
        }

        this.category = category;
        this.updatedTime = LocalDateTime.now();
    }

    public void delete() {
        if (deleted) {
            throw new IllegalStateException("文章已经删除");
        }

        this.deleted = true;
        this.updatedTime = LocalDateTime.now();
    }

    public void restore() {
        if (!deleted) {
            throw new IllegalStateException("文章未被删除");
        }

        this.deleted = false;
        this.updatedTime = LocalDateTime.now();
    }

    public Article() {

    }

    public Article(String title, String content, boolean published) {

        // TODO 1：给 title 赋值
        checkTitle(title);
        this.title = title;

        checkContent(content);
        this.content = content;

        // TODO 2：给 published 赋值
        this.published = published;

        this.createdTime = LocalDateTime.now();
        this.updatedTime = LocalDateTime.now();
    }

    private void checkTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new ArticleValidationException("标题不能为空");
        }

    }

    private void checkContent(String content) {
        if (content == null || content.trim().isEmpty()) {
            throw new ArticleValidationException("正文不能为空");
        }

    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        // TODO 3：返回 title
        return this.title;
    }

    public boolean isPublished() {
        // TODO 4：返回 published
        return this.published;
    }

    public String getContent() {
        return this.content;
    }

    public void publish() {
        // TODO 5：将 published 修改为 true
        this.published = true;
        this.updatedTime = LocalDateTime.now();
    }

    public void updateTitle(String title) {
        checkTitle(title);
        this.title = title;
        this.updatedTime = LocalDateTime.now();
    }

    public void updateContent(String content) {
        checkContent(content);
        this.content = content;
        this.updatedTime = LocalDateTime.now();
    }

    public void updatePublished(boolean published) {
        this.published = published;
        this.updatedTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", published=" + published +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", deleted=" + deleted +
                '}';
    }
}