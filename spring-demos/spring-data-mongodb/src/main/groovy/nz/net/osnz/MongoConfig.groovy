package nz.net.osnz

import com.mongodb.MongoClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.data.mongodb.MongoDbFactory
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoDbFactory
import org.springframework.data.mongodb.core.convert.CustomConversions
import org.springframework.data.mongodb.core.convert.DbRefResolver
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.mapping.MongoMappingContext

@Configuration
class MongoConfig {
  @Bean
  MongoClient mongo() throws Exception {
//        MongoPropertiesResolver resolver = mongoResolver();
    return new MongoClient("localhost", 27017);
  }

  @Bean
  MongoDbFactory mongoDbFactory() throws Exception {
    return new SimpleMongoDbFactory(mongo(), "database");
  }

  @Bean
  MongoTemplate mongoTemplate() throws Exception {
    return new MongoTemplate(mongoDbFactory(), mongoConverter());
  }

  @Bean
  CustomConversions customConversions() {
    List<Converter<?, ?>> converterList = new ArrayList<Converter<?, ?>>();
//        converterList.add(new TimeZoneReadConverter());
//        converterList.add(new TimeZoneWriteConverter());
    return new CustomConversions(converterList);
  }

  @Bean
  MappingMongoConverter mongoConverter() throws Exception {
    MongoMappingContext mappingContext = new MongoMappingContext();
    DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory());
    MappingMongoConverter mongoConverter = new MappingMongoConverter(dbRefResolver, mappingContext);
    mongoConverter.setCustomConversions(customConversions());
    return mongoConverter;
  }
}
