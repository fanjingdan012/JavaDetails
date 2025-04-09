package file.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

public class JsonReader {
  public static void main(String[] args) throws IOException {
    String json = """
        {
          "ssn": "001",
          "birthday": "01.01.2020",
          "address": { "city": "London", "country": "UK" },
          "unknownProperty":"aaa"
        }
        """;

    //Resources.toString(Resources.getResource("SomePerson.json"), StandardCharsets.UTF_8);

    ObjectMapper objectMapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    objectMapper.registerModule(module);
    SomePerson s = objectMapper.readValue(json, SomePerson.class);
    System.out.println(s.getAddress().getCountry());
  }

}
