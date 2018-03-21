package nz.net.osnz

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Document
class Person {

    @Id
    String id;

    String firstName;
    String lastName;

    @Override
    String toString() {
        return String.format(
            "Person[id=%s, firstName='%s', lastName='%s']",
            id, firstName, lastName);
    }


}
