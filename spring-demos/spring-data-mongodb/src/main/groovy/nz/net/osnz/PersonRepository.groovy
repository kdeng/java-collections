package nz.net.osnz

import org.springframework.data.mongodb.repository.MongoRepository

/**
 * @author Kefeng Deng (deng@51any.com)
 */
interface PersonRepository extends MongoRepository<Person, String> {

  Person findByFirstName(String firstName);

  List<Person> findByLastName(String lastName);

}
