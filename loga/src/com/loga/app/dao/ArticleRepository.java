package com.loga.app.dao;

import com.loga.model.Article;
import com.loga.vendor.Factory;
import com.loga.vendor.Repository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleRepository extends Repository<Article,Long>{

    public ArticleRepository() {
        super(Article.class);
    }

    public List<Article> search(String text){
        List<Article> articles = new ArrayList<>();

        StringBuilder query = new StringBuilder();
        query.append("select distinct article.*");
        query.append(" from article");
        query.append(" where article.designation like '%"+text+"%'");
        try {
            ResultSet rs = Factory.getInstance().get(query.toString());
            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getLong("id"));
                article.setReference(rs.getString("reference"));
                article.setCategorie(rs.getString("categorie"));
                article.setDesignation(rs.getString("designation"));
                article.setDescription(rs.getString("description"));
                article.setPrix(rs.getDouble("prix"));
                article.setStock(rs.getInt("stock"));
                articles.add(article);
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return articles;
    }

    public Article find(String text){
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from Article a where a.designation=:designation");
        query.setParameter("designation",text);
        Article article = (Article) query.getSingleResult();
        session.close();
        return article;
    }
}
