package csc.summer2019.cscc01;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.mongodb.*;
import csc.summer2019.cscc01.data.MongoDB;
import csc.summer2019.cscc01.data.RentalNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Assignment3WebCrawler {
  public static MongoDB mongoDB = new MongoDB();
  private String seed;
  private int limit;
  private int currLink = 0;
  private int currPage = 2;
  
  public Assignment3WebCrawler() {
  }

  public void setSeed(String seed) {
    this.seed = seed;
  }
  
  public String getSeed() {
    return seed;
  }
  
  public void setLimit(int limit) {
    this.limit = limit;
  }
  
  public void setLimit(String limit) {
    try {
      this.limit = Integer.parseInt(limit);
    } catch (Exception e) {
      System.err.println("Error, cannot Parse Int");
    }
  }
  
  public int getLimit() {
    return this.limit;
  }
  
  public void getPageLinks(String URL) {
      // EMPTY MONGODB BEFORE REFILLING
      mongoDB.clearDB();
      try {
        // Connect to url and get all hyperlinks
        Document document = Jsoup.connect(URL).get();
        Elements linksOnPage = document.select("a[href]");
        
        // Only increment if hyperlink leads to kijiji ad and go to next page
        // if limit exceeds results per page
        for (Element link : linksOnPage) {
          if (this.currLink >= this.limit) {
            // Reset currLink and currPage after limit is reached
            this.currLink = 0;
            this.currPage = 2;
            break;
          } else {
            // Grab link and convert to string format
            String adLink = link.attr("abs:href");
            // Check if link goes to a Kijiji ad
            if (Validator.isKijijiAd(adLink) == true) {
              
              // Parse any information in the Kijiji Ad and add to MongoDB
              this.parseAdLink(Jsoup.connect(adLink).get()).addDataToMongo();
              
              this.currLink++;
            } 
            // Check if link goes to next page
            else if (Validator.isNextPage(adLink, this.currPage) == true) {
              // Go to next page
              currPage++;
              this.getPageLinks(adLink);
              break;
            }
          }
      }
      } catch (IOException e) {
        System.err.println("Connection to '" + URL + " failed.");
      }
    }
  
  public RentalNode parseAdLink(Document adHTML) {
    String address = "N/A";
    String optionalParams[] = {"Unit Type ", "Bedrooms ", "Bathrooms ", 
                               "Pet Friendly ", "Furnished "};
      
    // Get the address from the HTML
    Elements htmlAddress = adHTML.select("[itemtype=http://schema.org/PostalAddress]");
    for (Element link : htmlAddress) {
      address = link.html();
      break;
    }

    // Get the optional Parameters from the HTML
    int i = 0;
    Elements htmlOptionalParams= adHTML.select("li").select("div");
    for (Element optionalParam : htmlOptionalParams) {
      String optionalParamStr = optionalParam.text();
      if (i >= optionalParams.length) {
        break;
      }
      if (optionalParamStr.contains(optionalParams[i])) {
        optionalParamStr = optionalParamStr.replaceAll(optionalParams[i], "");
        optionalParams[i] = optionalParamStr;
        i++;
      }
    }

    // Finally, create the Rental Node and store the aquiredData
    RentalNode parsedAd = new RentalNode.Builder(address)
                                        .unitType(optionalParams[0])
                                        .bedrooms(optionalParams[1])
                                        .bathrooms(optionalParams[2])
                                        .isPetFriendly(optionalParams[3])
                                        .isFurnished(optionalParams[4])
                                        .build();
    
    return parsedAd;
  }
  
  
  public static void main( String[] args ) throws IOException 
  {
    Assignment3WebCrawler crawler = new Assignment3WebCrawler();
    String[] url_list = args;
    crawler.setLimit(Integer.parseInt(url_list[1]));
    crawler.setSeed(url_list[0]);
    crawler.getPageLinks(crawler.getSeed());
  }
  


}
