package file.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {
  @JsonProperty
  private String city;

  @JsonProperty
  private String country;

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }
  
}