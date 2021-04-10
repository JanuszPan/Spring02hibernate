package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Category;
import pl.coderslab.entity.Publisher;

import java.awt.print.Pageable;
import java.util.List;

// Spring Data zastępuje BookDao CRTL+F12 - można przeglądnąć wszystkie odziedziczone metody
//jedno repozytorium odpowiada za obsługę jeden encji
public interface BookRepository extends JpaRepository<Book, Long> {

    //metodę wyszukującą książki dla zadanego tytułu.
    List<Book> findAllByTitle(String title);

    //metodę wyszukującą książki dla zadanej kategorii
    List<Book> findAllByCategory(Category category);

    //metodę wyszukującą książki dla zadanej kategorii
    @Query("select b from Book b where b.category = :category")
    Book findByCat(@Param("category") Category category);

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

    @Query("select b from Book b where  b.rating between :min and :max")
    List<Book> findByRat(@Param("rating") int min, int max);

    @Query("select b from Book b where b.publisher.name=?1")
    List<Book> findByPub(String publisherName);

    @Query(value = "select  b from Book b where b.category = :category order by b.title")
    List<Book> booksbyTitleLimit(@Param("category") Category category, Pageable pageable);

//    @Query(value = "SELECT * FROM books b JOIN category c on b.category_id = c.id where c.name = :categoryName ORDER BY b.title LIMIT 1",
//            nativeQuery = true)
//    List<Book> booksByCategoryNameLimit1(@Param("categoryName") String categoryName);
}
//Stwórz encję Category i połącz ją relacją z Book. Książka ma jedną kategorię.
//Listę książek dla których rating jest pomiędzy zadanymi parametrami np. między 3 a 5.
//        Listę książek dla zadanego wydawcy.
//        Pierwszą książkę z zadanej kategorii, z sortowaniem po tytule.

