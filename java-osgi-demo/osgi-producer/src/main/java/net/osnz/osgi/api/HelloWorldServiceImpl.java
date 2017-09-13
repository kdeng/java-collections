package net.osnz.osgi.api;

/**
 * Created with IntelliJ IDEA.
 * User: kdeng
 * Date: 19/04/13
 * Time: 9:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class HelloWorldServiceImpl implements HelloWorldService {

  public HelloWorldServiceImpl() {
    System.out.println("HelloWorldServiceImpl constructor");
  }

  @Override
  public void hello() {
    System.out.println("Hello World");
  }
}
