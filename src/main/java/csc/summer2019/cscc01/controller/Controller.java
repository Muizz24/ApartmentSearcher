package csc.summer2019.cscc01.controller;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import csc.summer2019.cscc01.*;

// https://spring.io/guides/gs/accessing-data-mongodb/
// https://o7planning.org/en/11773/spring-boot-and-mongodb-tutorial#
// https://www.springboottutorial.com/spring-boot-react-full-stack-crud-maven-application
// http://4youngpadawans.com/upload-file-to-server-react-and-spring/
// https://stackoverflow.com/questions/47389374/how-to-upload-and-retrieve-file-in-mongodb-in-spring-boot-application-without-us
// https://www.springboottutorial.com/spring-boot-react-full-stack-with-spring-security-basic-and-jwt-authentication
// https://dev.to/onlineinterview/user-account-loginregistration-feature-with-spring-boot--3fc3
// https://hellokoding.com/registration-and-login-example-with-spring-security-spring-boot-spring-data-jpa-hsql-jsp/

//@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@CrossOrigin
@RestController
public class Controller {
  
  private Assignment3WebCrawler crawler = new Assignment3WebCrawler();
  
  @PostMapping("/search")
  public ResponseEntity getSeedAndLimit(@RequestParam("seed") String seed,
      @RequestParam("limit") String limit) {
    try {
      crawler.setSeed(seed);
      crawler.setLimit(Integer.parseInt(limit));
      crawler.getPageLinks(seed);
      return ResponseEntity.ok(seed);
    } catch (Exception e) {
        //if something went bad, we need to inform client about it
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    //everything was OK, return HTTP OK status (200) to the client
    //return ResponseEntity.ok("put response here");
  }

}
