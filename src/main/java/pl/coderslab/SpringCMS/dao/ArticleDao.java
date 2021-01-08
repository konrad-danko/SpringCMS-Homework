package pl.coderslab.SpringCMS.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.SpringCMS.model.Article;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class ArticleDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Article article) {
        entityManager.persist(article);
    }

    public Article read(long id) {
        return entityManager.find(Article.class, id);
    }

    public void update(Article article) {
        entityManager.merge(article);
    }

    public void delete(Article article) {
        entityManager.remove(entityManager.contains(article) ? article : entityManager.merge(article));
    }

    public List<Article> readAllArticles() {
        Query query = entityManager.createQuery("SELECT a from Article a where a.draft=false");
        return query.getResultList();
    }

    public List<Article> read5Recent(){
        Query query = entityManager.createQuery("SELECT a from Article a ORDER BY a.id desc");
        query.setMaxResults(5);
        return query.getResultList();
    }

    public List<Article> readAllDrafts() {
        Query query = entityManager.createQuery("SELECT a from Article a where a.draft=true");
        return query.getResultList();
    }
}
