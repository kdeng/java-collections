package io.osnz.demos.feign;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
public class ApiException extends RuntimeException {

  private List<String> LIST = new ArrayList<>();

  public ApiException(String s) {
    super(s);
    LIST.add(s);
  }

  public ApiException(List<String> list) {
    super(CollectionUtils.isEmpty(list) ? "" : list.get(0));
    LIST.addAll(list);
  }

  public List<String> getMessages() {
    return LIST;
  }


}
