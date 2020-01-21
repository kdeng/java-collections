package io.osnz;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
public class Application {

  Application() {

  }

  public static void main(String[] args) throws Exception {
    Application app = new Application();
//    app.testCase2(app);
    app.testCase1(app);
    app.testCase2(app);

  }

  protected void testCase1(Application application) throws Exception {
    long startTime = System.currentTimeMillis();
    ExecutorService executorService = Executors.newWorkStealingPool(10);
    List<Runnable> taskList = application.createTaskList(10);
    taskList.forEach(executorService::execute);
    executorService.shutdown();
    executorService.awaitTermination(10, TimeUnit.SECONDS);
    System.out.println(String.format("Test case 1 took %s ms", System.currentTimeMillis() - startTime));
  }

  protected void testCase2(Application application) throws InterruptedException {
    long startTime = System.currentTimeMillis();
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    List<Task> taskList = IntStream.range(0, 10).mapToObj(i -> new Task(i)).collect(Collectors.toList());
    executorService.invokeAll(taskList);
    executorService.shutdown();
    System.out.println(String.format("Test case 2 took %s ms", System.currentTimeMillis() - startTime));
  }

  private ExecutorService createExecutorService(int size) {
    return Executors.newFixedThreadPool(size);
  }

  private List<Runnable> createTaskList(int jobSize) {
    List<Runnable> taskList = new ArrayList<>();
    IntStream.range(0, jobSize).forEach(i -> taskList.add(() -> {
      System.out.println("Worker #" + i + " start");
      try {
        TimeUnit.SECONDS.sleep(3);
      } catch (Exception e) {
        System.err.println("Unexpected interrupted");
      }
      System.out.println("Worker #" + i + " finish");
    }));
    return taskList;
  }


  public static class Task implements Callable<Boolean> {

    private final int worker;

    Task(int worker) {
      this.worker = worker;
    }

    @Override
    public Boolean call() throws Exception {
      System.out.println("Work #" + worker + " started");
      TimeUnit.SECONDS.sleep(3);
      System.out.println("Work #" + worker + " finished");
      return true;
    }
  }


}
