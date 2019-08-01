package io.osnz.demos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
public class Application {

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {

//        SpringApplication app = new SpringApplication(Application.class);
//        app.setBannerMode(Banner.Mode.OFF);
//        app.run(args);

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
    JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");

    String jobId = "helloJob";
    //任务
    Job job = (Job) context.getBean(jobId);

    log.debug("任务【" + jobId + "】开始执行");
    long start = System.currentTimeMillis();
    try {
      //执行任务
      JobExecution execution = jobLauncher.run(job, new JobParameters());
      log.debug("任务【" + jobId + "】执行结果：" + execution.getStatus());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      /** 如果jobLauncher的taskExecutor设置为异步多线程，这里应该注释掉，否则由于job还在执行，而context关闭
       * ，就会导致所有bean（包括数据源dataSource）关闭，从而导致job出错：Data source is closed等错误
       if(context != null){
       context.close();
       }
       **/
    }
    long end = System.currentTimeMillis();
    log.debug("任务【" + jobId + "】执行完毕！共花费：" + (end - start) + "毫秒");

  }

}
