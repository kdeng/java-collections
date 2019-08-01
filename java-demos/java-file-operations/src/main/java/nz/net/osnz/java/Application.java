package nz.net.osnz.java;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author Kefeng Deng (deng@51any.com)
 */

public class Application {

    public static void main(String[] args) {
        String fileName = "/tmp/line.txt";
        operation2(fileName);
    }

    private static void operation1(String filename) {
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            stream.forEach(System.out::println);
        } catch (IOException ioEx) {

        }
    }

    private static void operation2(String filename) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filename))) {
            br.lines().forEach(System.out::println);
        } catch (IOException ioEx) {

        }
    }

}
