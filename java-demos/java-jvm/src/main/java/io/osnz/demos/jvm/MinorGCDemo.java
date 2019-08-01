package io.osnz.demos.jvm;

/**
 * VM :  -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 */
public class MinorGCDemo {

  private static final int _1_MB = 1024 * 1024;


  public static void main(String[] args) {
    byte[] allocation1, allocation2, allocation3, allocation4;
    System.out.println("Start");
    allocation1 = new byte[4 * _1_MB];
    System.out.println("Takes 4 MB");
    allocation2 = new byte[2 * _1_MB];
    System.out.println("Takes 2 MB");
    allocation3 = new byte[2 * _1_MB];
    System.out.println("Takes 2 MB");
    allocation4 = new byte[2 * _1_MB];
    System.out.println("Takes 2 MB");
  }

}

/**
 * Output:
 *
 * Start
 * Takes 4 MB
 * Takes 2 MB
 * [GC (Allocation Failure) [PSYoungGen: 7480K->560K(9216K)] 7480K->6712K(19456K), 0.0041270 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
 * [Full GC (Ergonomics) [PSYoungGen: 560K->0K(9216K)] [ParOldGen: 6152K->6519K(10240K)] 6712K->6519K(19456K), [Metaspace: 2954K->2954K(1056768K)], 0.0039429 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
 * Takes 2 MB
 * Takes 2 MB
 * Heap
 *  PSYoungGen      total 9216K, used 4420K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
 *   eden space 8192K, 53% used [0x00000007bf600000,0x00000007bfa513b8,0x00000007bfe00000)
 *   from space 1024K, 0% used [0x00000007bfe00000,0x00000007bfe00000,0x00000007bff00000)
 *   to   space 1024K, 0% used [0x00000007bff00000,0x00000007bff00000,0x00000007c0000000)
 *  ParOldGen       total 10240K, used 6519K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
 *   object space 10240K, 63% used [0x00000007bec00000,0x00000007bf25ddc0,0x00000007bf600000)
 *  Metaspace       used 2966K, capacity 4496K, committed 4864K, reserved 1056768K
 *   class space    used 328K, capacity 388K, committed 512K, reserved 1048576K
 *
 * Process finished with exit code 0
 *
 */
