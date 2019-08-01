package nz.net.osnz.demos.graphql.repository;

import nz.net.osnz.demos.graphql.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Component
public class BookRepository {

  private static Map<Long, Book> BOOK_LIST = new HashMap<>();

  @Autowired
  UserRepository userRepository;

  @PostConstruct
  public void mockBooks() {
    for (int i = 1; i <= 100; i++) {
      BOOK_LIST.put(
        Integer.toUnsignedLong(i),
        Book.builder().name("Book Name #" + i).authors(userRepository.getUsers().subList(i, i + 10)).id(i).build()
      );
    }
  }

  public Book getBookById(Long id) {
    return BOOK_LIST.get(id.intValue());
  }

  public List<Book> getBooks() {
    return new ArrayList<>(BOOK_LIST.values());
  }

}
