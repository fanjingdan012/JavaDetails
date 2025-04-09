package net;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class UrlUtil {
  private UrlUtil() {
  }

  public static LinkStructure parse(String href) throws URISyntaxException {
    LinkStructure linkStructure = new LinkStructure();
    if (href == null) {
      return linkStructure;
    }
    String[] parts = href.split("\\{&");
    if (parts.length > 1) {
      String freeParam = parts[1].substring(0, parts[1].lastIndexOf("}"));
      String[] freeParams = freeParam.split(",");
      Set<String> freeParamsSet = Arrays.stream(freeParams).collect(Collectors.toSet());
      linkStructure.setFreeParameters(freeParamsSet);
    }
    //    URI uri = new URI(parts[0]);
    //    System.out.println("path" + uri.getPath());
    //    System.out.println("query" + uri.getQuery());
    //    System.out.println("scheme" + uri.getScheme());
    //    System.out.println("raw scheme" + uri.getRawSchemeSpecificPart());
    //    URI u = new URI()
    String[] parts2 = parts[0].split("\\?");
    if (parts2.length > 1) {
      String queryPart = parts2[1];
      List<NameValuePair> params = URLEncodedUtils.parse(queryPart, StandardCharsets.UTF_8);
      linkStructure.setParameters(
          params.stream().collect(Collectors.toMap(NameValuePair::getName, NameValuePair::getValue)));
    }
    linkStructure.setPath(parts2[0]);
    return linkStructure;
  }

  public static String buildLink(LinkStructure linkStructure) {
    StringBuilder sbHref = new StringBuilder(linkStructure.getPath());
    return new String(sbHref);
  }

  public static String getParameterConcatenation(String href) {
    if (href.contains("?")) {
      return "&";
    }
    return "?";
  }
}