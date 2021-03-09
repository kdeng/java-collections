package io.osnz;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class HelloService {

//  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public String sayHello(String name) {
     return addThree(addTwo(addOne(name)));
  }

  private String addOne(String name) {
    return name + "1";
  }

  private String addTwo(String name) {
    return name + "2";
  }

  private String addThree(String name) {
    return name + "3";
  }


}
