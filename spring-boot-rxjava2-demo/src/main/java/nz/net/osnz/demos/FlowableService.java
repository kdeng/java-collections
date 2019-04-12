package nz.net.osnz.demos;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

import java.util.stream.IntStream;

public class FlowableService {

  public static Flowable newFlowableRange1() {
    return Flowable.range(1, 100);
  }

  public static Flowable<Integer> newFlowableRange2() {
    return Flowable.create(e -> {
      IntStream.rangeClosed(1, 100).forEach(action -> {
        e.onNext(action);
        try {
          Thread.sleep(action);
        } catch (InterruptedException ex) {
          e.onError(ex);
        }
      });
      e.onComplete();
    }, BackpressureStrategy.BUFFER);
  }

}
