package csc.summer2019.cscc01;

public class Validator {
  private static int oldAdID;
  
  public static boolean isInt(String integer) {
    try {
      Integer.parseInt(integer);
      return true;
    } catch (Exception exception) {
      return false;
    }
  }
  
  public static boolean isKijijiAd(String adLink) {
    String regex = "(.+)(/[0-9]{10})(.*)";
    if (adLink.matches(regex)) {
      // grab the id from the link provided
      String id = adLink.replaceAll("(.*)(?=/)/","").substring(0,10);
      int adID = Integer.parseInt(id);
      if (adID != oldAdID) {
        oldAdID = adID;
        return true;
      }
    }
    return false;
  }
  
  public static boolean isNextPage(String link, int pageNo) {
    String regex = "(.+)(page-" + pageNo + ")(.*)";
    if (link.matches(regex)) {
      return true;
    } else {
      return false;
    }
  }

}
