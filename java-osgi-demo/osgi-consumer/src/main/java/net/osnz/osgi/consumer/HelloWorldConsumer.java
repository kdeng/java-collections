package net.osnz.osgi.consumer;

import net.osnz.osgi.api.HelloWorldService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: kdeng
 * Date: 19/04/13
 * Time: 9:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class HelloWorldConsumer implements ActionListener {

  private final HelloWorldService service;

  private final Timer timer;

  public HelloWorldConsumer(HelloWorldService service) {
    super();
    this.service = service;
    timer = new Timer(1000, this);
  }


  public void startTimer() {
    System.out.println("Start Timer");
    timer.start();
  }

  public void stopTimer() {
    System.out.println("Stop timer");
    timer.stop();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    //To change body of implemented methods use File | Settings | File Templates.
    service.hello();
  }
}
