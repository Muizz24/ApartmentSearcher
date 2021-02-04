package csc.summer2019.cscc01.data;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoDB {
  private MongoClient mongoClient = new MongoClient("localhost", 27017);
  private DB dbs = mongoClient.getDB("rentaldata");
  private DBCollection coll = dbs.getCollection("rentaldata");
  
  public MongoDB() {
    mongoClient = new MongoClient("localhost", 27017);
  }
  
  public DB getDatabase() {
    return dbs;
  }
  
  public DBCollection getCollection() {
    return coll;
  }
  
  public void clearDB() {
    coll.drop();
    
  }
}
