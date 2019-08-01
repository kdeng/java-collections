package nz.net.osnz.demos.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import graphql.GraphQLException;
import nz.net.osnz.demos.graphql.model.Book;
import nz.net.osnz.demos.graphql.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Component
public class QueryBook implements GraphQLQueryResolver {

  @Autowired
  private BookRepository bookRepository;


  public Book book(Long id) {
    try {
      return bookRepository.getBookById(id);
    } catch (NullPointerException ex) {
      throw new GraphQLException("Cannot find by '" + id + "'");
    }
  }

  public List<Book> books() {
    return bookRepository.getBooks();
  }

}
