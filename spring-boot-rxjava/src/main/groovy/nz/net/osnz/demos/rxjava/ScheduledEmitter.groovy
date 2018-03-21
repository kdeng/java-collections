package nz.net.osnz.demos.rxjava

import groovy.transform.CompileStatic
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.annotations.NonNull
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct
import java.text.SimpleDateFormat

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Component
@CompileStatic
class ScheduledEmitter {

    private static final Logger LOG = LoggerFactory.getLogger(ScheduledEmitter)

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss")

    Observable<String> observer

    ObservableEmitter emitter

    ScheduledEmitter() {
        observer = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            void subscribe(@NonNull ObservableEmitter e) throws Exception {
                emitter = e
            }
        })
    }

    @PostConstruct
    void init() {
        observer.subscribe({s -> LOG.info("Receive an event: {}", s)})
    }

    @Scheduled(fixedRate = 5000L)
    void notifyService() {
        LOG.info("Notify service now")
        emitter.onNext(String.format("The time is now %s", dateFormat.format(new Date())))
    }

}
