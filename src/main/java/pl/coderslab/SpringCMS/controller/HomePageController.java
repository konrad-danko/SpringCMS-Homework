package pl.coderslab.SpringCMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.SpringCMS.model.Category;
import pl.coderslab.SpringCMS.repository.ArticleRepository;
import pl.coderslab.SpringCMS.repository.CategoryRepository;

@Controller
@RequestMapping(path = "/home")
public class HomePageController {

    private final CategoryRepository categoryRepository;
    private final ArticleRepository articleRepository;
    public HomePageController(CategoryRepository categoryRepository, ArticleRepository articleRepository) {
        this.categoryRepository = categoryRepository;
        this.articleRepository = articleRepository;
    }

    @GetMapping(path = "")
    public String home(Model model){
        model.addAttribute("recent5Articles", articleRepository.read5Recent());
        model.addAttribute("allCategoriesSorted", categoryRepository.readAllSortedByName());
        return "/home";
    }

    @GetMapping(path = "/categoryArticles/{id}")
    public String showCategoryArticles(Model model, @PathVariable long id) {
        model.addAttribute("recent5Articles", articleRepository.read5Recent());
        model.addAttribute("allCategoriesSorted", categoryRepository.readAllSortedByName());
        Category category = categoryRepository.findById(id).orElse(null);
        model.addAttribute("categoryName", category.getName());
        model.addAttribute("allArticlesWithGivenCategory", articleRepository.findAllByCategories(category));
        return "/home";
    }
}
