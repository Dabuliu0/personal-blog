package com.dabuliu.blog.article;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import java.util.Optional;

import com.dabuliu.blog.exception.ArticleNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleService {

    private final ArticleRepository repository;

    public ArticleService(ArticleRepository repository) {

        this.repository = repository;

    }

    // 添加文章
    public void addArticle(Article article) {
       
        repository.save(article);
    }

    // 删除文章
    @Transactional
    public void deleteArticle(long id) {
        Article article = repository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id));

        article.delete();
    }

    // 寻找所有已发布的文章
    public List<Article> findPublishedArticles() {
        List<Article> result = new ArrayList<>();

        for (Article article : repository.findAll()) {
            // TODO 3：把已发布文章加入 result
            if (article.isPublished() && !article.isDeleted()) {
                result.add(article);
            }
        }

        return result;
    }

    // 返回文章数量
    public int getArticleCount() {
        // TODO 4：返回文章数量
        return (int) repository.findAll().stream()
                .filter(article -> !article.isDeleted())
                .count();
    }

    // 根据标题找文章
    public List<Article> searchByTitle(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            throw new IllegalArgumentException("搜索关键词不能为空");
        }

        String normalizedKeyword = keyword.trim().toLowerCase();

        return repository.findAll().stream()
                .filter(article -> !article.isDeleted())
                .filter(article -> article.getTitle()
                        .toLowerCase()
                        .contains(normalizedKeyword))
                .toList();
    }

    @Transactional
    public void restoreArticle(long id) {
        Article article = repository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id));

        article.restore();
    }

    // 修改文章
    @Transactional
    public Article updateArticle(
            long id,
            String newTitle,
            String newContent,
            boolean published) {

        Article article = findById(id)
                .orElseThrow();

        article.updateTitle(newTitle);
        article.updateContent(newContent);
        article.updatePublished(published);

        return article;
    }

    // 查询所有未删除文章
    public List<Article> findAllArticles() {
        return repository.findAll().stream()
                .filter(article -> !article.isDeleted())
                .sorted(Comparator.comparingLong(Article::getId))
                .toList();
    }

    // 根据Id找文章
    public Optional<Article> findById(long id) {
        return repository.findById(id)
                .filter(article -> !article.isDeleted());
    }

}