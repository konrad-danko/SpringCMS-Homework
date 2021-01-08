package pl.coderslab.SpringCMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.SpringCMS.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
