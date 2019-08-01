package nz.net.osnz.demos.graphql.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import nz.net.osnz.demos.graphql.model.User;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class UserDTO {

  private User user;

}
