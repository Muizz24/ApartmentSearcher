package csc.summer2019.cscc01.app;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import csc.summer2019.cscc01.Validator;

public class ValidatorTests {

  @Test
  public void isValidInt() {
    boolean actual = Validator.isInt("10");
    assertTrue(actual);
  }
  
  @Test
  public void isInvalidInt() {
    boolean actual = Validator.isInt("NOTANINT");
    assertFalse(actual);
  }
  
  @Test
  public void isInvalidKijijiAd() {
    String link = "https://www.kijiji.ca/b-apartments-condos/canada/c37l0?siteLocale=en_CA";
    boolean actual = Validator.isKijijiAd(link);
    assertFalse(actual);
  }
  
  @Test
  public void isValidKijijiAd() {
    String link = "https://www.kijiji.ca/v-apartments-condos/red-deer/lake-front-property-for-rent/1445215635?enableSearchNavigationFlag=true";
    boolean actual = Validator.isKijijiAd(link);
    assertTrue(actual);
  }
  
  @Test
  public void isValidNextPageLink() {
    String link = "https://www.kijiji.ca/b-apartments-condos/canada/page-2/c37l0";
    boolean actual = Validator.isNextPage(link,2);
    assertTrue(actual);
  }
  
  @Test
  public void isInvalidNextPageLink() {
    String link = "https://www.kijiji.ca/b-apartments-condos/canada/c37l0";
    boolean actual = Validator.isNextPage(link,2);
    assertFalse(actual);
  }
  
  @Test
  public void isValidNextPageLinkButWrongPage() {
    int page = 7;
    String link = "https://www.kijiji.ca/b-apartments-condos/canada/page-2/c37l0";
    boolean actual = Validator.isNextPage(link, page);
    assertFalse(actual);
  }
  
}
