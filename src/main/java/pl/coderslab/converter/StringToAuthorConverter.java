package pl.coderslab.converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.entity.Author;
public class StringToAuthorConverter implements Converter<String, Author> {
    @Autowired
    private AuthorDao authorDao;
    @Override
    public Author convert(String source) {
        int authorId = Integer.parseInt(source); // obsługa NumberFormatException
        return authorDao.read(authorId);
    }
}