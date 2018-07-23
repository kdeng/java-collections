package nz.net.osnz.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
//@Data
//@Accessors(fluent = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class User {
    private int id;
    private String name;
    private String email;
}
