package io.osnz.demos.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms100M -Xmx100M -XX:+UseSerialGC  -verbose:gc -XX:+PrintGCDetails -XX:SurvivorRatio=8
 */
public class OOMObjectDemo {

  static class OOMObject {
    public byte[] placeholder = new byte[64 * 1024];
  }

  static void fillHeap(int num) throws InterruptedException {
    List<OOMObject> list = new ArrayList<>();
    for (int i = 0; i< num; i++) {
      System.out.println(i);
      Thread.sleep(200);
      list.add(new OOMObject());
    }
    System.out.println("before GC");
    System.gc();
    System.out.println("after GC");
  }

  public static void main(String[] args) throws Exception {
    fillHeap(1000);
  }

}
