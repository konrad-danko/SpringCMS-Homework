package pl.coderslab.SpringCMS.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.SpringCMS.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class AuthorDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Author author){
        entityManager.persist(author);
    }

    public Author read(long id){
        return entityManager.find(Author.class, id);
    }

    public void update(Author author){
        entityManager.merge(author);
    }
    
    public void delete(Author author){
        entityManager.remove(entityManager.contains(author) ? author : entityManager.merge(author));
    }

    public List<Author> readAll() {
        Query query = entityManager.createQuery("SELECT a from Author a");
        return query.getResultList();
    }
}
