package nz.net.osnz.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Setter
@Getter
@Builder
public class Book {
    private int id;
    private String name;
    private List<User> authors;

}
