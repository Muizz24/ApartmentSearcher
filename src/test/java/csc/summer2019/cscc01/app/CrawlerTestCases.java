package csc.summer2019.cscc01.app;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import csc.summer2019.cscc01.Assignment3WebCrawler;
import csc.summer2019.cscc01.data.RentalNode;
import static org.mockito.Mockito.*;
import org.jsoup.nodes.Document;

public class CrawlerTestCases {
  private Assignment3WebCrawler crawler;
  private Assignment3WebCrawler crawlerMock = mock(Assignment3WebCrawler.class);
  private Document html = null;

  @Before
  public void setUp() throws Exception {
    crawler = new Assignment3WebCrawler();
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testSeed() {
    String seed = "https://www.kijiji.ca/b-apartments-condos/canada/c37l0?siteLocale=en_CA";
    crawler.setSeed(seed);
    assertEquals(seed, crawler.getSeed());
  }
  
  @Test
  public void testLimitAsInt() {
    int limit = 10;
    crawler.setLimit(limit);
    assertEquals(limit, crawler.getLimit());
  }
  
  @Test
  public void testLimitAsValidString() {
    String limit = "10";
    crawler.setLimit(limit);
    assertEquals(Integer.parseInt(limit), crawler.getLimit());
  }
  
  @Test
  public void testLimitAsInvalidString() {
    String limit = "NOTANUMBER";
    crawler.setLimit(limit);
    // We are assuming the uninitialized value of 0 would remain the same if the
    // setLimit failed.
    int expectedLimit = 0; 
    assertEquals(expectedLimit, crawler.getLimit());
  }
  
  @Test
  public void testAdParsingAddress() {
    RentalNode parsedAd = new RentalNode.Builder("bobmar Rd.").build();
    assertEquals("bobmar Rd.", parsedAd.getLocation());
  }
  
  @Test
  public void testAdParsingUnitType() {
    RentalNode parsedAd = new RentalNode.Builder("bobmar Rd.")
                                    .unitType("apartment")
                                    .build();
    assertEquals("apartment", parsedAd.getUnitType());
  }
  
  @Test
  public void testAdParsingOptionalParams() {
    RentalNode parsedAd = new RentalNode.Builder("bobmar Rd.").build();
    // Assuming the rest of the optional parameters will become N/A as well
    assertEquals("N/A", parsedAd.getUnitType());
  }

}
