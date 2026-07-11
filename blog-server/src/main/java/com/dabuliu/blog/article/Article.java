package com.dabuliu.blog.article;

import java.time.LocalDateTime;

public class Article {

    private long id;
    private String title;
    private String content;
    private boolean published;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public Article(long id, String title,String content, boolean published) {
        this.id = id;

        // TODO 1：给 title 赋值
        this.title = title;

        this.content = content;

        // TODO 2：给 published 赋值
        this.published = published;

        this.createdTime = LocalDateTime.now();
        this.updatedTime = LocalDateTime.now();
    }

    

    public long getId() {
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

    public String getContent(){
        return this.content;
    }

    public void publish() {
        // TODO 5：将 published 修改为 true
        this.published = true;
    }

    public void updateTitle(String title) {
    this.title = title;
    this.updatedTime = LocalDateTime.now();
}

public void updateContent(String content) {
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
            ", updateTime=" + updatedTime +
            '}';
}
}