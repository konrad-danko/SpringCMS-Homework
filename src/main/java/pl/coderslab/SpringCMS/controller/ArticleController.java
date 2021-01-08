package pl.coderslab.SpringCMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.SpringCMS.model.*;
import pl.coderslab.SpringCMS.repository.ArticleRepository;
import pl.coderslab.SpringCMS.repository.AuthorRepository;
import pl.coderslab.SpringCMS.repository.CategoryRepository;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/article")
public class ArticleController {

    private final ArticleRepository articleRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    public ArticleController(ArticleRepository articleRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.articleRepository = articleRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    private static final Map<String, String> ADD_ARTICLE_ATTR_MAP = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("headerMessage", "Dodaj nowy artykuł"),
            new AbstractMap.SimpleEntry<>("disabledParam", "false"),
            new AbstractMap.SimpleEntry<>("buttonClass", "btn-success")
    );
    private static final Map<String, String> EDIT_ARTICLE_ATTR_MAP = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("headerMessage", "Edytuj artykuł"),
            new AbstractMap.SimpleEntry<>("disabledParam", "false"),
            new AbstractMap.SimpleEntry<>("buttonClass", "btn-warning")
    );
    private static final Map<String, String> DEL_ARTICLE_ATTR_MAP = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("headerMessage", "Potwierdź usunięcie artykułu"),
            new AbstractMap.SimpleEntry<>("disabledParam", "true"),
            new AbstractMap.SimpleEntry<>("buttonClass", "btn-danger")
    );

    private void setModelAttributes(Model model, Map<String, String> map){
        map.keySet().stream()
                .forEach(key -> model.addAttribute(key, map.get(key)));
    }


    @ModelAttribute("allAuthors")
    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }
    @ModelAttribute("allCategories")
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    @GetMapping(path = "/showAllArticles")
    public String showAllArticles(Model model) {
        model.addAttribute("allArticles", articleRepository.readAllArticlesAndDrafts());
        return "/article/allArticles";
    }

    //add block
    @GetMapping(path = "/addArticleOrDraft")
    public String initiateAddArticleOrDraft(Model model) {
        setModelAttributes(model, ADD_ARTICLE_ATTR_MAP);
        model.addAttribute("article", new Article());
        return "/article/formArticle";
    }
    @PostMapping(path = "/addArticleOrDraft")
    public String processAddArticleOrDraft(@ModelAttribute Article article) {
        return article.isDraft() ? "forward:/article/addDraft" : "forward:/article/addArticle";
    }
    @PostMapping(path = "/addArticle")
    public String processAddArticle(@ModelAttribute @Validated(AdvanceInfo.class) Article article, BindingResult result, Model model) {
        return processAddArtDraft(article, result, model);
    }
    @PostMapping(path = "/addDraft")
    public String processAddDraft(@ModelAttribute @Validated(BasicInfo.class) Article article, BindingResult result, Model model) {
        return processAddArtDraft(article, result, model);
    }
    public String processAddArtDraft(Article article, BindingResult result, Model model){
        if(result.hasErrors()){
            setModelAttributes(model, ADD_ARTICLE_ATTR_MAP);
            return "/article/formArticle";
        }
        articleRepository.save(article);
        return "redirect:/article/showAllArticles";
    }


    //edit block
    @GetMapping(path = "/editArticleOrDraft/{id}")
    public String initiateEditArticleOrDraft(Model model, @PathVariable long id) {
        setModelAttributes(model, EDIT_ARTICLE_ATTR_MAP);
        model.addAttribute("article", articleRepository.findById(id).orElse(null));
        return "/article/formArticle";
    }
    @PostMapping(path = "/editArticleOrDraft/{id}")
    public String processEditArticleOrDraft(@ModelAttribute Article article) {
        long id = article.getId();
        return article.isDraft() ? "forward:/article/editDraft/"+id : "forward:/article/editArticle/"+id;
    }
    @PostMapping(path = "/editArticle/{id}")
    public String processEditArticle(@ModelAttribute @Validated(AdvanceInfo.class) Article article, BindingResult result, Model model) {
        return processEditArtDraft(article, result, model);
    }
    @PostMapping(path = "/editDraft/{id}")
    public String processEditDraft(@ModelAttribute @Validated(BasicInfo.class) Article article, BindingResult result, Model model) {
        return processEditArtDraft(article, result, model);
    }
    public String processEditArtDraft(Article article, BindingResult result, Model model){
        if(result.hasErrors()){
            setModelAttributes(model, EDIT_ARTICLE_ATTR_MAP);
            return "/article/formArticle";
        }
        articleRepository.save(article);
        return "redirect:/article/showAllArticles";
    }

    //delete block
    @GetMapping(path = "/deleteArticle/{id}")
    public String initiateDeleteArticle(Model model, @PathVariable long id){
        setModelAttributes(model, DEL_ARTICLE_ATTR_MAP);
        model.addAttribute("article", articleRepository.findById(id).orElse(null));
        return "/article/formArticle";
    }
    @PostMapping(path = "/deleteArticle/{id}")
    public String processDeleteArticle(@ModelAttribute Article article) {
        articleRepository.delete(article);
        return "redirect:/article/showAllArticles";
    }
}
