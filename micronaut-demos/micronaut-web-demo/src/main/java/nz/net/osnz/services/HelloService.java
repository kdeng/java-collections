package nz.net.osnz.services;


import javax.inject.Singleton;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Singleton
public class HelloService {

  public String hello(String s) {
    return "Hello " + s + "!!!";
  }

}
