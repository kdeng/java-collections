package io.osnz;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
public interface HelloService {

  /**
   * Say hello to the name
   *
   * @param userDto is a given name
   * @return a string representation
   */
  String sayHello(UserDto userDto);

}
