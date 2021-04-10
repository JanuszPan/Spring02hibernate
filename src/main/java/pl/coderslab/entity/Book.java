package pl.coderslab.entity;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
//@Data //Lombok sam generuje settery, gettery, toString i wiele innych
//@AllArgsConstructor
//@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5,message = "{title.must.has}")//message="Tytuł musi być większy niż {min} znaków."
    private String title;

    @Range(min = 1, max = 10)
//    @Getter(AccessLevel.NONE)//Lombok: można wykluczyć tworzenie np. gettera dla danego pola
    private int rating;

    @Size(max = 600)
    private String description;

    @Min(2)
    private int pages;

    @NotNull
    @ManyToOne
    private Publisher publisher;

    @NotNull
    @Size(min = 1)
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Author> authors = new ArrayList<>();

    @ManyToOne
    private Category category;

    public Category getCategory() { return category; }

    public void setCategory(Category category) { this.category = category; }

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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                '}';
    }
}