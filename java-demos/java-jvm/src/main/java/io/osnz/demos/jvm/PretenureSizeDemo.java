package io.osnz.demos.jvm;

/**
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
 */
public class PretenureSizeDemo {

  private static final int _1_MB = 1024 * 1024;

  public static void main(String[] args) {
    System.out.println("Start");
    byte[] allocation = new byte[7 * _1_MB];
    System.out.println("Takes 7 MB");
  }
}

/**
 * output:
 *
 * Start
 * Takes 7 MB
 * Heap
 *  PSYoungGen      total 9216K, used 1695K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
 *   eden space 8192K, 20% used [0x00000007bf600000,0x00000007bf7a7ea0,0x00000007bfe00000)
 *   from space 1024K, 0% used [0x00000007bff00000,0x00000007bff00000,0x00000007c0000000)
 *   to   space 1024K, 0% used [0x00000007bfe00000,0x00000007bfe00000,0x00000007bff00000)
 *  ParOldGen       total 10240K, used 7168K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
 *   object space 10240K, 70% used [0x00000007bec00000,0x00000007bf300010,0x00000007bf600000)
 *  Metaspace       used 2998K, capacity 4496K, committed 4864K, reserved 1056768K
 *   class space    used 328K, capacity 388K, committed 512K, reserved 1048576K
 *
 * Process finished with exit code 0
 *
 */
