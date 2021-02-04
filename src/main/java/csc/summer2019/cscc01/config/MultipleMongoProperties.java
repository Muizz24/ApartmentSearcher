package csc.summer2019.cscc01.config;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

@Data
@ConfigurationProperties(prefix = "mongodb")
public class MultipleMongoProperties {
    private MongoProperties primary = new MongoProperties();
    private MongoProperties secondary = new MongoProperties();
    private MongoProperties tertiary = new MongoProperties();
    
    public MongoProperties getPrimary() {
      return primary;
    }
    
    public MongoProperties getSecondary() {
      return secondary;
    }
    
    public MongoProperties getTertiary() {
      return tertiary;
    }
}