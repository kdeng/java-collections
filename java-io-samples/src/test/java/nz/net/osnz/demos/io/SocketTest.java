package nz.net.osnz.demos.io;

import nz.net.osnz.demos.io.SocketClient;
import nz.net.osnz.demos.io.SocketServer;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
public class SocketTest {

  @Test
  @Ignore
  public void testSocket() throws Exception {
    new Thread(new Runnable() {
      public void run() {
        try {
          SocketServer.start();
        } catch (Exception ex) {

        }
      }
    }).start();
    SocketClient.sendMessage("Hello world");
  }

}
