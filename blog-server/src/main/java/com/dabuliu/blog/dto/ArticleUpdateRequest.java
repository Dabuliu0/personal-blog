package com.dabuliu.blog.dto;

public class ArticleUpdateRequest {

    private String title;

    private String content;

    private boolean published;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean isPublished() {
        return published;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}