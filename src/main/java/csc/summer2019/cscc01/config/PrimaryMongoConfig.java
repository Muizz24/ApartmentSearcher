package csc.summer2019.cscc01.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "csc.summer2019.cscc01.data",
        mongoTemplateRef = "primaryMongoTemplate")
public class PrimaryMongoConfig {
  
}

