package nz.net.osnz

import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.Banner
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@SpringBootApplication
@CompileStatic
@Configuration
@EnableMongoRepositories(basePackageClasses = PersonRepository.class)
@Import(MongoConfig.class)
class Application implements CommandLineRunner {

    @Autowired
    private PersonRepository repository;

    static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application)
        app.bannerMode = Banner.Mode.OFF
        app.run(args)
    }

    @Override
    void run(String... args) throws Exception {
        repository.deleteAll();

        // save a couple of customers
        repository.save(new Person(firstName: "Alice", lastName: "Smith"));
        repository.save(new Person(firstName: "Bob", lastName: "Smith"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Person customer : repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Person customer : repository.findByLastName("Smith")) {
            System.out.println(customer);
        }
    }
}
