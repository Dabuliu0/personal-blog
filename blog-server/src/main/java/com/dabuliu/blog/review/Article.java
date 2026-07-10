package com.dabuliu.blog.review;

public class Article {

    private long id;
    private String title;
    private boolean published;

    public Article(long id, String title, boolean published) {
        this.id = id;

        // TODO 1：给 title 赋值
        this.title = title;

        // TODO 2：给 published 赋值
        this.published = published;
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

    public void publish() {
        // TODO 5：将 published 修改为 true
        this.published = true;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", published=" + published +
                '}';
    }
}