package nz.net.osnz.demos.graphql.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Data
@Builder
public class Book {
  private int id;
  private String name;
  private List<User> authors;

}
