package pl.coderslab.SpringCMS.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.coderslab.SpringCMS.dao.CategoryDao;
import pl.coderslab.SpringCMS.model.Category;

@Component
public class CategoryConverter implements Converter<String, Category>{

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public Category convert(String source) {
        return categoryDao.read(Long.parseLong(source));
    }
}
