package net;

import org.apache.http.NameValuePair;
import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;
import java.util.List;

public class UrlUtilTest {

  @Test
  public void testIsAlreadyPresent() throws Exception {
    boolean result = UrlUtil.isAlreadyPresent("entity",
        "/rest/workforce/v1/valueLists?entity=globalDetail&property=genericString4&country={countryCode}&$at={startDate}&id={id}{&$top,$skip,$search}");
    Assert.assertEquals(true, result);
  }

  @Test
  public void testGetParams() throws URISyntaxException {
    List<NameValuePair> params = UrlUtil.getRequestParams(
        "http://localhost:9090/rest/workforce/v1/valueLists?entity=globalDetail&property=genericString4");
    //            "&country={countryCode}&$at={startDate}&id={id}{&$top,$skip,$search}");
    NameValuePair n = params.get(0);
    System.out.println(params);
  }

  @Test
  public void testGetParams1() {
    String href = "/odatav4/servicesfoundation/search/PersonSearchService.svc/v1/personSimpleSearch()?$format=json{&searchValue}";
  }

  @Test
  public void testGetQueryPart() throws Exception {
    String result = UrlUtil.getQueryPart("relativeHref");
    Assert.assertEquals("replaceMeWithExpectedResult", result);
  }

  @Test
  public void testGetParameterConcatenation() throws Exception {
    String result = UrlUtil.getParameterConcatenation("href");
    Assert.assertEquals("replaceMeWithExpectedResult", result);
  }
}