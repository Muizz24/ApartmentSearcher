package csc.summer2019.cscc01.config;
// https://dzone.com/articles/multiple-mongodb-connectors-with-spring-boot

import com.mongodb.MongoClient;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
  
@Configuration
public class MultipleMongoConfig {

@Primary
@Bean(name = "primary")
@ConfigurationProperties(prefix = "spring.data.mongodb")
public MongoProperties getPrimary() {
    return new MongoProperties();
}

@Bean(name = "secondary")
@ConfigurationProperties(prefix = "mongodb")
public MongoProperties getSecondary() {
    return new MongoProperties();
}

@Bean(name = "tertiary")
@ConfigurationProperties(prefix = "mongodb")
public MongoProperties getTertiary() {
    return new MongoProperties();
}

@Primary
@Bean(name = "primaryMongoTemplate")
public MongoTemplate primaryMongoTemplate() throws Exception {
    return new MongoTemplate(primaryFactory(getPrimary()));
}

@Bean(name = "secondaryMongoTemplate")
public MongoTemplate secondaryMongoTemplate() throws Exception {
    return new MongoTemplate(secondaryFactory(getSecondary()));
}

@Bean(name = "tertiaryMongoTemplate")
public MongoTemplate tertiaryMongoTemplate() throws Exception {
    return new MongoTemplate(secondaryFactory(getSecondary()));
}

@Bean
@Primary
public MongoDbFactory primaryFactory(final MongoProperties mongo) throws Exception {
    return new SimpleMongoDbFactory(new MongoClient(mongo.getHost(), mongo.getPort()),
            mongo.getDatabase());
}

@Bean
public MongoDbFactory secondaryFactory(final MongoProperties mongo) throws Exception {
    return new SimpleMongoDbFactory(new MongoClient(mongo.getHost(), mongo.getPort()),
            mongo.getDatabase());
}

@Bean
public MongoDbFactory tertiaryFactory(final MongoProperties mongo) throws Exception {
    return new SimpleMongoDbFactory(new MongoClient(mongo.getHost(), mongo.getPort()),
            mongo.getDatabase());
}

//@Configuration
//@RequiredArgsConstructor
//@EnableConfigurationProperties(MultipleMongoProperties.class)
//public class MultipleMongoConfig {
//  
//    private final MultipleMongoProperties mongoProperties = new MultipleMongoProperties();
//    
//    @Primary
//    @Bean(name = "primaryMongoTemplate")
//    public MongoTemplate primaryMongoTemplate() throws Exception {
//        return new MongoTemplate(primaryFactory(this.mongoProperties.getPrimary()));
//    }
//    
//    @Bean(name = "secondaryMongoTemplate")
//    public MongoTemplate secondaryMongoTemplate() throws Exception {
//        return new MongoTemplate(secondaryFactory(this.mongoProperties.getSecondary()));
//    }
//    
//    @Bean
//    @Primary
//    public MongoDbFactory primaryFactory(final MongoProperties mongo) throws Exception {
//        return new SimpleMongoDbFactory(new MongoClient(mongo.getHost(), mongo.getPort()),
//                mongo.getDatabase());
//    }
//    
//    @Bean
//    public MongoDbFactory secondaryFactory(final MongoProperties mongo) throws Exception {
//        return new SimpleMongoDbFactory(new MongoClient(mongo.getHost(), mongo.getPort()),
//                mongo.getDatabase());
//    }
}