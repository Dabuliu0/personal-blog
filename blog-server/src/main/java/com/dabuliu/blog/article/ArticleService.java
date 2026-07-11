package com.dabuliu.blog.article;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

public class ArticleService {

    private ArticleRepository repository;

    public ArticleService(ArticleRepository repository) {

        this.repository = repository;

    }

    //添加文章
    public void addArticle(Article article) {

        repository.save(article);

    }

    // 寻找所有已发布的文章
    public List<Article> findPublishedArticles() {
        List<Article> result = new ArrayList<>();

        for (Article article : repository.findAll()) {
            // TODO 3：把已发布文章加入 result
            if (article.isPublished()) {
                result.add(article);
            }
        }

        return result;
    }

    // 返回文章数量
    public int getArticleCount() {
        // TODO 4：返回文章数量
        return repository.findAll().size();
    }

    // 根据标题找文章
    public List<Article> searchByTitle(String keyword) {
        return repository.findAll().stream()
                // TODO：只保留标题中包含 keyword 的文章
                // .filter(Article::getTitle.contains(keyword))
                .filter(article -> article.getTitle().contains(keyword))
                .toList();
    }

    // 修改文章
    public Article updateArticle(
            long id,
            String newTitle,
            String newContent,
            boolean published) {

        Article article = repository.findById(id)
                .orElseThrow();

        article.updateTitle(newTitle);
        article.updateContent(newContent);
        article.updatePublished(published);

        return article;
    }

    //根据Id找文章
    public Optional<Article> findById(long id){

    return repository.findById(id);

}
}