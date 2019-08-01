package nz.net.osnz.kotlin

import org.fest.assertions.Assertions.assertThat
import org.junit.Test

class HelloTest {

  @Test
  fun hello() {

    assertThat("hello").isEqualTo("hello")
  }
}
