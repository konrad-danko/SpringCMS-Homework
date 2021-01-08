package pl.coderslab.SpringCMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.SpringCMS.model.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c from Category c order by c.name")
    List<Category> readAllSortedByName();
}
