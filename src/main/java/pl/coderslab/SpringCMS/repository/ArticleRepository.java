package pl.coderslab.SpringCMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.SpringCMS.model.Article;
import pl.coderslab.SpringCMS.model.Category;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAllByCategories(Category category);

    @Query("SELECT a from Article a")
    List<Article> readAllArticlesAndDrafts();

    @Query(value="select * from articles order by id desc limit 5", nativeQuery = true)
    List<Article> read5Recent();
}
