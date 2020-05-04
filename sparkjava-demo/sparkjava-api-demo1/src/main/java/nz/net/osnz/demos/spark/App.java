package nz.net.osnz.demos.spark;

import nz.net.osnz.common.jackson.JacksonHelper;
import spark.ResponseTransformer;
import spark.Spark;


/**
 * @author Kefeng Deng (deng@51any.com)
 */
public class App {

  public static void main(String[] args) {

    Spark.get("/hello", (request, response) -> "Hello world");

    Spark.get("/user", "application/json", (request, response) -> {
      response.type("application/json");
      return new User("john");
    }, new JsonTransformer());
  }

  static class JsonTransformer implements ResponseTransformer {
    @Override
    public String render(Object model) throws Exception {
      return JacksonHelper.serialize(model);
    }
  }

  static class User {

    String name;

    public User() {}

    public User(String n) {
      this.name = n;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

}
