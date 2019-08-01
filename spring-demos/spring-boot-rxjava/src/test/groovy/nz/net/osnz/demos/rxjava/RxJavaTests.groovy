package nz.net.osnz.demos.rxjava

import io.reactivex.Observable
import org.junit.Assert
import org.junit.Test

/**
 * @author Kefeng Deng (deng@51any.com)
 */
class RxJavaTests {

  @Test
  void shouldOutputOnceOnly() {
    StringBuilder stringBuilder = new StringBuilder()
    Observable<String> observer = Observable.just("Hello")
    observer.subscribe({ s -> stringBuilder.append(s) })
    Assert.assertEquals("Hello", stringBuilder.toString())
  }

}
