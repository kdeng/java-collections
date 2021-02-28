package io.osnz;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
public class ExpressionParserHelperTest {

  @Test
  public void shouldParseTemplateProperly() {
    String message = "User #{user.name} is #{user.age}";
    UserDto user = UserDto.builder().name("asd").age(12).build();
    Map<String, Object> model = Collections.singletonMap("user", user);
    Assertions.assertThat(ExpressionParserHelper.parseTemplate(message, model)).isEqualTo("User asd is 12");
  }

  @Test
  public void shouldParseExpressionProperly() {
    String message = "#user.name";
    UserDto user = UserDto.builder().name("asd").age(12).build();
    Map<String, Object> model = Collections.singletonMap("user", user);
    Assertions.assertThat(ExpressionParserHelper.parseExpression(message, model)).isEqualTo("asd");
  }

}
