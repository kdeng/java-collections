package nz.net.osnz.demos;

import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;
import org.assertj.core.api.Assertions;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.hasItems;

public class FlowableServiceTest {

  @ParameterizedTest(name = "{index} -> {0}")
  @MethodSource("flowableProvider")
  public void shouldSubscribeFlowableProperly(Flowable source) {


    // given
    Assertions.assertThat(source).isNotNull();
    TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();

    // when
    source.subscribe(testSubscriber);

    // then
    testSubscriber.assertComplete();
    testSubscriber.assertNoErrors();
    testSubscriber.assertValueCount(100);
    IntStream.rangeClosed(1, 100).forEach(i -> testSubscriber.assertValueAt(i - 1, i));
    MatcherAssert.assertThat(testSubscriber.values(), hasItems(1, 10, 100));
  }

  private static Stream<Arguments> flowableProvider() {
    return Stream.of(
        Arguments.of(FlowableService.newFlowableRange1()),
        Arguments.of(FlowableService.newFlowableRange2())
    );
  }

//  private static Stream<Flowable> flowableProvider() {
//    return Stream.of(
//        FlowableService.newFlowableRange1(),
//        FlowableService.newFlowableRange2()
//    );
//  }


}
