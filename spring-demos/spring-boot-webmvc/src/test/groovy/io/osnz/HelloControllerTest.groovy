package io.osnz

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Title
import spock.lang.Unroll

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Title("HelloController Specification")
@Narrative("The Specification of the behaviour of the HelloController. It can greet a person, change the name and reset it to 'world'")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerTest extends Specification {

  @Autowired
  MockMvc mockMvc

  def "when get is performed then the response has status 200 and content is 'Hello World'"() {
    expect: "Status is 200 and the response is 'Hello World'"
      mockMvc.perform(MockMvcRequestBuilders.get('/'))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn()
        .response
        .contentAsString == 'Hello World'
  }

  @Unroll
  def 'URI #uri should return status code #code and response body #body'(String uri, int code, String body) {
    expect: "should works as expect"
      mockMvc.perform(MockMvcRequestBuilders.get(uri))
        .andExpect(MockMvcResultMatchers.status().is(code))
        .andReturn()
        .response
        .contentAsString == body

    where:
      uri      | code | body
      "/"      | 200  | "Hello World"
      "/dummy" | 200  | "Hello dummy"
  }


}
