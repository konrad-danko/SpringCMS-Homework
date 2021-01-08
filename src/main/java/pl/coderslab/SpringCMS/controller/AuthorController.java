package pl.coderslab.SpringCMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.SpringCMS.model.Author;
import pl.coderslab.SpringCMS.repository.ArticleRepository;
import pl.coderslab.SpringCMS.repository.AuthorRepository;

import javax.validation.Valid;
import java.util.AbstractMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/author")
public class AuthorController {

    private final ArticleRepository articleRepository;
    private final AuthorRepository authorRepository;
    public AuthorController(ArticleRepository articleRepository, AuthorRepository authorRepository) {
        this.articleRepository = articleRepository;
        this.authorRepository = authorRepository;
    }

    private static final Map<String, String> ADD_AUTH_ATTR_MAP = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("headerMessage", "Dodaj nowego autora"),
            new AbstractMap.SimpleEntry<>("disabledParam", "false"),
            new AbstractMap.SimpleEntry<>("buttonClass", "btn-success")
    );
    private static final Map<String, String> EDIT_AUTH_ATTR_MAP = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("headerMessage", "Edytuj dane autora"),
            new AbstractMap.SimpleEntry<>("disabledParam", "false"),
            new AbstractMap.SimpleEntry<>("buttonClass", "btn-warning")
    );
    private static final Map<String, String> DEL_AUTH_ATTR_MAP = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("headerMessage", "Potwierdź usunięcie autora"),
            new AbstractMap.SimpleEntry<>("disabledParam", "true"),
            new AbstractMap.SimpleEntry<>("buttonClass", "btn-danger")
    );

    private void setModelAttributes(Model model, Map<String, String> map){
        map.keySet().stream()
                .forEach(key -> model.addAttribute(key, map.get(key)));
    }

    @GetMapping(path = "/showAllAuthors")
    public String showAllAuthors(Model model) {
        model.addAttribute("allAuthors", authorRepository.findAll());
        return "/author/allAuthors";
    }

    //add block
    @GetMapping(path = "/addAuthor")
    public String initiateAddAuthor(Model model) {
        setModelAttributes(model, ADD_AUTH_ATTR_MAP);
        model.addAttribute("author", new Author());
        return "/author/formAuthor";
    }
    @PostMapping(path = "/addAuthor")
    public String processAddAuthor(@ModelAttribute @Valid Author author, BindingResult result, Model model) {
        if (result.hasErrors()) {
            setModelAttributes(model, ADD_AUTH_ATTR_MAP);
            return "/author/formAuthor";
        }
        authorRepository.save(author);
        return "redirect:/author/showAllAuthors";
    }

    //edit block
    @GetMapping(path = "/editAuthor/{id}")
    public String initiateEditAuthor(Model model, @PathVariable long id) {
        setModelAttributes(model, EDIT_AUTH_ATTR_MAP);
        model.addAttribute("author", authorRepository.findById(id).orElse(null));
        return "/author/formAuthor";
    }
    @PostMapping(path = "/editAuthor/{id}")
    public String processEditAuthor(@ModelAttribute @Valid Author author, BindingResult result, Model model) {
        if (result.hasErrors()) {
            setModelAttributes(model, EDIT_AUTH_ATTR_MAP);
            return "/author/formAuthor";
        }
        authorRepository.save(author);
        return "redirect:/author/showAllAuthors";
    }

    //delete block
    @GetMapping(path = "/deleteAuthor/{id}")
    public String initiateDeleteAuthor(Model model, @PathVariable long id){
        setModelAttributes(model, DEL_AUTH_ATTR_MAP);
        model.addAttribute("author", authorRepository.findById(id).orElse(null));
        return "/author/formAuthor";
    }
    @PostMapping(path = "/deleteAuthor/{id}")
    public String processDeleteAuthor(@ModelAttribute Author author) {
        long id = author.getId();
        articleRepository.readAllArticlesAndDrafts().stream()
                .filter(article -> article.getAuthor()!=null && article.getAuthor().getId()==id)
                .forEach(article -> {
                    article.setAuthor(null);
                    articleRepository.save(article);
                });
        authorRepository.delete(author);
        return "redirect:/author/showAllAuthors";
    }
}
