package io.osnz;

import lombok.Builder;
import lombok.Data;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Data
@Builder
public class UserDto {
  private String name;
  private int age;
}
