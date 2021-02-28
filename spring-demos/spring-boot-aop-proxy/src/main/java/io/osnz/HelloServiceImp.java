package io.osnz;

import org.springframework.stereotype.Service;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Service
public class HelloServiceImp implements HelloService {

  @Override
  @Audit("User #{userDto.name} with age #{userDto.age}")
  public String sayHello(UserDto userDto) {
    return "Hello " + userDto.getName() + ", you are " + userDto.getAge() + " years old!";
  }

}
