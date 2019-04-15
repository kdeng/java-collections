package nz.net.osnz.demos.jersey;

public class HelloService {

  public String getHello() {
    return getHello("world");
  }

  public String getHello(String name) {
    return "Hello " + name;
  }

}
