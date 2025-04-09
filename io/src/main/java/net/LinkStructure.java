package net;

import java.util.Map;
import java.util.Set;

public class LinkStructure {
  private String path;

  private Map<String, String> parameters;

  private Set<String> freeParameters;

  private Map<String, String> templatePointers;

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Map<String, String> getParameters() {
    return parameters;
  }

  public void setParameters(Map<String, String> parameters) {
    this.parameters = parameters;
  }

  public Set<String> getFreeParameters() {
    return freeParameters;
  }

  public void setFreeParameters(Set<String> freeParameters) {
    this.freeParameters = freeParameters;
  }

  public Map<String, String> getTemplatePointers() {
    return templatePointers;
  }

  public void setTemplatePointers(Map<String, String> templatePointers) {
    this.templatePointers = templatePointers;
  }
}
