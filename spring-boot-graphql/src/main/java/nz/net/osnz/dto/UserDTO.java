package nz.net.osnz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import nz.net.osnz.model.User;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class UserDTO {

    private User user;

}
