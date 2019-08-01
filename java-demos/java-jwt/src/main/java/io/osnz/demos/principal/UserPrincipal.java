package io.osnz.demos.principal;

import lombok.Builder;
import lombok.Data;

import java.security.Principal;

/**
 * @author Kefeng Deng (https://bit.ly/2JFoCO1)
 */
@Data
@Builder
public class UserPrincipal implements Principal {

  private String name;
  private String subject;
  private String email;

}
