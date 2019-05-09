package io.osnz.demos.jvm;

/**
 * -XX:MaxMetaspaceSize=10M -XX:MetaspaceSize=10M
 * -XX:MaxPermSize=10M -XX:PermSize=10M
 */
public class StringIntern {

  public static void main(String[] args) {
    // Run this in java 6 and java 7
//    List<String> list = new ArrayList<>();
//    int i = 0;
//    while (true) {
//      //
//      list.add(String.valueOf(i++).intern());
//    }
    String string1 = new StringBuilder("Hello").append("World").toString();
    System.out.println(string1.intern() == string1); // true

    String string2 = new StringBuilder("ja").append("va").toString();
    System.out.println(string2.intern() == string2); // false

    String string3 = new StringBuilder("Hello").append("World").toString();
    System.out.println(string3.intern() == string3); // false
    System.out.println(string3.intern() == string1); // true


  }

}
