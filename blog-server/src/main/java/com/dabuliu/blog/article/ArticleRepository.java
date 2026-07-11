package com.dabuliu.blog.article;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArticleRepository {

    private final List<Article> articles = new ArrayList<>();


    public void save(Article article){

        articles.add(article);

    }


    public Optional<Article> findById(long id){

        for(Article article : articles){

            if(article.getId() == id){

                return Optional.of(article);

            }
        }

        return Optional.empty();


    }

    public List<Article> findAll(){

        return articles;
    }

    public void addArticle(Article article) {
        // TODO 1：将文章加入 articles
        articles.add(article);
    }

}