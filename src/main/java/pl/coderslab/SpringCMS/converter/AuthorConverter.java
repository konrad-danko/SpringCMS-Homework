package pl.coderslab.SpringCMS.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.coderslab.SpringCMS.dao.AuthorDao;
import pl.coderslab.SpringCMS.model.Author;

@Component
public class AuthorConverter implements Converter<String, Author> {

    @Autowired
    private AuthorDao authorDao;

    @Override
    public Author convert(String source) {
        return authorDao.read(Long.parseLong(source));
    }
}
