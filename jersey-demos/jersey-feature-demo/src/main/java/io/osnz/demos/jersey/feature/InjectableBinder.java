package io.osnz.demos.jersey.feature;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author Kefeng Deng (kefeng.deng@clearpoint.co.nz)
 */
public class InjectableBinder extends AbstractBinder {

  @Override
  protected void configure() {
    scanAndBind("io.osnz.demos");
  }

  private void scanAndBind(String packageName) {
    try {
      Class[] classes = getClasses(packageName);
      for (Class<?> klazz : classes) {
        System.out.println("klazz: " + klazz.getName());
        Injectable annotation = klazz.getAnnotation(Injectable.class);
        if (annotation != null) {
          bind(klazz).to(klazz);
        }
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static Class[] getClasses(String packageName)
    throws ClassNotFoundException, IOException {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    assert classLoader != null;
    String path = packageName.replace('.', '/');
    Enumeration<URL> resources = classLoader.getResources(path);
    List<File> dirs = new ArrayList<>();
    while (resources.hasMoreElements()) {
      URL resource = resources.nextElement();
      dirs.add(new File(resource.getFile()));
    }
    ArrayList<Class> classes = new ArrayList<Class>();
    for (File directory : dirs) {
      classes.addAll(findClasses(directory, packageName));
    }
    return classes.toArray(new Class[classes.size()]);
  }

  private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
    List<Class> classes = new ArrayList<Class>();
    if (!directory.exists()) {
      return classes;
    }
    File[] files = directory.listFiles();
    for (File file : files) {
      if (file.isDirectory()) {
        assert !file.getName().contains(".");
        classes.addAll(findClasses(file, packageName + "." + file.getName()));
      } else if (file.getName().endsWith(".class")) {
        classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
      }
    }
    return classes;
  }
}
