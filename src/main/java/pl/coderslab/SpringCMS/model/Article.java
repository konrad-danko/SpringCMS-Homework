package pl.coderslab.SpringCMS.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=200)
    @Size(max = 200, message = "Pole może zawierać do 200 znaków", groups = {BasicInfo.class, AdvanceInfo.class})
    @NotBlank(message="Pole nie może być puste", groups = {BasicInfo.class, AdvanceInfo.class})
    private String title; //(max. 200 znaków),

    @ManyToOne
    private Author author; //(powiązanie relacją do klasy Author) - artykuł może mieć tylko jednego autora

    @Size(min = 1, message = "Należy wybrać przynajmniej 1 kategorię", groups = AdvanceInfo.class)
    @ManyToMany
    private List<Category> categories = new ArrayList<>(); //(powiązanie relacją do klasy Category) - artykuł może należeć do wielu kategorii
    
    @Column(length=1000)
    @Size(min = 50, max = 1000, message="Pole musi zawierać od 50 do 1000 znaków", groups = {BasicInfo.class, AdvanceInfo.class})
    @NotBlank(message="Pole nie może być puste", groups = {BasicInfo.class, AdvanceInfo.class})
    private String content;

    private LocalDateTime created; //(wartość ma być automatycznie dodawana podczas zapisu)
    private LocalDateTime updated; //(wartość ma być automatycznie zmieniana podczas edycji)

    private boolean draft;

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate() {
        updated = LocalDateTime.now();
    }

    public Article() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Category> getCategories() {
        return categories;
    }
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated() {
        return created;
    }
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }
    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public boolean isDraft() {
        return draft;
    }
    public void setDraft(boolean draft) {
        this.draft = draft;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", categories=" + categories +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", draft=" + draft +
                '}';
    }
}
