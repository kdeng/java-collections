package nz.net.osnz.demos.spark

import io.restassured.RestAssured
import io.restassured.response.Response
import org.apache.http.HttpStatus

class AppTest extends spock.lang.Specification {

  def setupSpec() {
    println('setting up application')
    App.main()
  }

  def 'should return response properly from /hello'() {
    when:
      Response response = RestAssured.get('http://localhost:4567/hello')

    then:
      'Hello world' == response.asString()
      200 == response.statusCode()
  }


  def 'should return user json data properly from /user'() {
    expect:
      App.User user = RestAssured.get('http://localhost:4567/user')
        .then()
        .assertThat()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .as(App.User)
    
    and:
      user.name == 'john'
  }

}
