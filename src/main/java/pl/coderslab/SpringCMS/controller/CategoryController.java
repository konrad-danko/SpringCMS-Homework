package pl.coderslab.SpringCMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.SpringCMS.model.Category;
import pl.coderslab.SpringCMS.repository.ArticleRepository;
import pl.coderslab.SpringCMS.repository.CategoryRepository;

import javax.validation.Valid;
import java.util.AbstractMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/category")
public class CategoryController {

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    public CategoryController(ArticleRepository articleRepository, CategoryRepository categoryRepository) {
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
    }

    private static final Map<String, String> ADD_CAT_ATTR_MAP = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("headerMessage", "Dodaj nową kategorię"),
            new AbstractMap.SimpleEntry<>("disabledParam", "false"),
            new AbstractMap.SimpleEntry<>("buttonClass", "btn-success")
    );
    private static final Map<String, String> EDIT_CAT_ATTR_MAP = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("headerMessage", "Edytuj kategorię"),
            new AbstractMap.SimpleEntry<>("disabledParam", "false"),
            new AbstractMap.SimpleEntry<>("buttonClass", "btn-warning")
    );
    private static final Map<String, String> DEL_CAT_ATTR_MAP = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("headerMessage", "Potwierdź usunięcie kategorii"),
            new AbstractMap.SimpleEntry<>("disabledParam", "true"),
            new AbstractMap.SimpleEntry<>("buttonClass", "btn-danger")
    );

    private void setModelAttributes(Model model, Map<String, String> map){
        map.keySet().stream()
                .forEach(key -> model.addAttribute(key, map.get(key)));
    }

    @GetMapping(path = "/showAllCategories")
    public String showAllCategories(Model model) {
        model.addAttribute("allCategories", categoryRepository.findAll());
        return "/category/allCategories";
    }

    //add block
    @GetMapping(path = "/addCategory")
    public String initiateAddCategory(Model model) {
        setModelAttributes(model, ADD_CAT_ATTR_MAP);
        model.addAttribute("category", new Category());
        return "/category/formCategory";
    }
    @PostMapping(path = "/addCategory")
    public String processAddCategory(@ModelAttribute @Valid Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            setModelAttributes(model, ADD_CAT_ATTR_MAP);
            return "/category/formCategory";
        }
        categoryRepository.save(category);
        return "redirect:/category/showAllCategories";
    }

    //edit block
    @GetMapping(path = "/editCategory/{id}")
    public String initiateEditCategory(Model model, @PathVariable long id) {
        setModelAttributes(model, EDIT_CAT_ATTR_MAP);
        model.addAttribute("category", categoryRepository.findById(id).orElse(null));
        return "/category/formCategory";
    }
    @PostMapping(path = "/editCategory/{id}")
    public String processEditCategory(@ModelAttribute @Valid Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            setModelAttributes(model, EDIT_CAT_ATTR_MAP);
            return "/category/formCategory";
        }
        categoryRepository.save(category);
        return "redirect:/category/showAllCategories";
    }

    //delete block
    @GetMapping(path = "/deleteCategory/{id}")
    public String initiateDeleteCategory(Model model, @PathVariable long id){
        setModelAttributes(model, DEL_CAT_ATTR_MAP);
        model.addAttribute("category", categoryRepository.findById(id).orElse(null));
        return "/category/formCategory";
    }
    @PostMapping(path = "/deleteCategory/{id}")
    public String processDeleteCategory(@PathVariable long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        articleRepository.readAllArticlesAndDrafts().stream()
                .filter(article -> article.getCategories().contains(category))
                .forEach(article -> {
                    article.getCategories().remove(category);
                    articleRepository.save(article);
                });
        categoryRepository.delete(category);
        return "redirect:/category/showAllCategories";
    }
}
