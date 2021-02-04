package csc.summer2019.cscc01.data;

import com.mongodb.BasicDBObject;
import csc.summer2019.cscc01.Assignment3WebCrawler;

/**
 * Stores data relevant for the user viewing the ad
 * @author monip
 *
 */

public class RentalNode {
  // Mandatory Parameter(s);
  private String location;
  
  // Optional Parameters
  private String unitType;
  private String bedrooms;
  private String bathrooms;
  private String isPetFriendly;
  private String isFurnished;
  
  public RentalNode(Builder b) {
    this.setLocation(b.location);
    this.bedrooms = b.bedrooms;
    this.bathrooms = b.bathrooms;
    this.isPetFriendly = b.isPetFriendly;
    this.isFurnished = b.isFurnished;
    this.setUnitType(b.unitType);
  }
  
  //Static Builder class
  public static class Builder {
    // Mandatory Parameter(s)
    private String location;
    
    // Optional Parameters
    private String unitType = "N/A";
    private String bedrooms = "N/A";
    private String bathrooms = "N/A";
    private String isPetFriendly = "N/A";
    private String isFurnished = "N/A";
    
    public Builder(String location) {
      this.location = location;
    }
    
    public Builder unitType(String unitType) {
      this.unitType = unitType;
      
      return this;
    }
    
    public Builder bedrooms(String bedrooms) {
      this.bedrooms = bedrooms;
      
      return this;
    }
    
    public Builder bathrooms(String bathrooms) {
      this.bathrooms = bathrooms;
      
      return this;
    }
    
    public Builder isPetFriendly(String isPetFriendly) {
      this.isPetFriendly = isPetFriendly;
      
      return this;
    }
    
    public Builder isFurnished(String isFurnished) {
      this.isFurnished = isFurnished;
      
      return this;
    }
    
    public RentalNode build() {
      return new RentalNode(this);
    }
  }
  
  public void addDataToMongo() {
    BasicDBObject parsedAdData = new BasicDBObject("Address", this.getLocation())
        .append("Unit Type", this.getUnitType())
        .append("Bedrooms", this.bedrooms)
        .append("Bathrooms", this.bathrooms)
        .append("is Pet Friendly", this.isPetFriendly)
        .append("is Furnished", this.isFurnished);
    Assignment3WebCrawler.mongoDB.getCollection().insert(parsedAdData);
  }

  /**
   * @return the location
   */
  public String getLocation() {
    return location;
  }

  /**
   * @param location the location to set
   */
  public void setLocation(String location) {
    this.location = location;
  }

  /**
   * @return the unitType
   */
  public String getUnitType() {
    return unitType;
  }

  /**
   * @param unitType the unitType to set
   */
  public void setUnitType(String unitType) {
    this.unitType = unitType;
  }

}
