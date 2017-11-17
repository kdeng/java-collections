package nz.net.osnz

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

/**
 * @author Kefeng Deng (deng@51any.com)
 */
class ThreadPool {
    private static final Logger LOG = LoggerFactory.getLogger(ThreadPool)


    ExecutorService executorService1
    ExecutorService executorService2
    ScheduledExecutorService executorService3

    ThreadPool() {
        executorService1 = Executors.newSingleThreadExecutor()
        executorService2 = Executors.newFixedThreadPool(10)
        executorService3 = Executors.newScheduledThreadPool(1)
    }

    void executeSingleThread() {
        final Random random = new Random()
        for (int i = 1; i < 10; i++) {
            final AtomicInteger atomicInteger = new AtomicInteger(i);
            executorService1.execute(new Runnable() {
                void run() {
                    long sleepTime = random.nextInt(5) * 1000L
                    LOG.info("Service1 -> " + atomicInteger.get() + " : Start asynchronous task - sleep : " +
                        sleepTime)
                    Thread.sleep(sleepTime);
                    LOG.info("Service1 -> " + atomicInteger.get() + " : finish asynchronous task")
                }
            });
        }
    }

    void executeFixedMultipleThreads() {
        final Random random = new Random()
        for (int i = 1; i < 50; i++) {
            final AtomicInteger atomicInteger = new AtomicInteger(i);
            executorService2.execute(new Runnable() {
                void run() {
                    long sleepTime = random.nextInt(4) * 1000L
                    LOG.info("Service2 -> " + atomicInteger.get() + " : Start asynchronous task - sleep : " +
                        sleepTime)
                    Thread.sleep(sleepTime);
                    LOG.info("Service2 -> " + atomicInteger.get() + " : finish asynchronous task")
                }
            });
        }
    }

    void executeScheduleMultipleThreads() {
        final Random random = new Random()
        for (int i = 1; i < 20; i++) {
            final AtomicInteger atomicInteger = new AtomicInteger(i);
            executorService3.schedule(new Runnable() {
                void run() {
                    long sleepTime = random.nextInt(4) * 1000L
                    LOG.info("Service3 -> " + atomicInteger.get() + " : Start asynchronous task - sleep : " +
                        sleepTime)
//                    Thread.sleep(sleepTime);
                    LOG.info("Service3 -> " + atomicInteger.get() + " : finish asynchronous task")
                }
            }, i * 2, TimeUnit.SECONDS)
        }
    }

    void shutdown() {
        executorService1.shutdown();
        executorService2.shutdown();
//        executorService3.shutdown();
    }

    static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool();

//        threadPool.executeSingleThread();
//        threadPool.executeFixedMultipleThreads();
        threadPool.executeScheduleMultipleThreads();

        threadPool.shutdown()
    }


}
