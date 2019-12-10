package nz.net.osnz.demos.jersey;

public class HelloService {

  public String getHello() {
    return getHello("world");
  }

  public String getHello(String name) {
    return "Hello " + name;
  }

  public static void hello() {
    throw new IllegalArgumentException("Hello exception");
  }

}
