package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Category;
import pl.coderslab.entity.Publisher;

import java.util.List;

// Spring Data zastępuje BookDao CRTL+F12 - można przeglądnąć wszystkie odziedziczone metody
public interface BookRepository extends JpaRepository<Book, Long> {

    //metodę wyszukującą książki dla zadanego tytułu.
    List<Book> findAllByTitle(String title);

    //metodę wyszukującą książki dla zadanej kategorii
    List<Book> findAllByCategory(Category category);

    //metodę wyszukującą książki dla zadanego id kategorii
    List<Book> findAllByCategoryId(Long id);

    List<Book> findAllByTitleLike(String title);
    //Listę książek dla określonego autora
    List<Book> findAllByAuthors(Author author);

    //podpowiadanie CTRL+spacja
    //Listę książek dla określonego wydawcy
    List<Book> findAllByPublisher(Publisher publisher);

    //Listę książek dla określonego ratingu
    List<Book> findAllByRating(int rating);

    //Pierwszą książkę z zadanej kategorii, z sortowaniem po tytule.
    Book findFirstByCategoryOrderByTitle(Category category);
}

//Stwórz encję Category i połącz ją relacją z Book. Książka ma jedną kategorię.
//W repozytorium dla klasy Book utwórz metody pobierające:


