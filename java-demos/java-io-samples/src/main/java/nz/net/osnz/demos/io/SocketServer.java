package nz.net.osnz.demos.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
public class SocketServer {

  public static void start() throws Exception {
    int port = 9000;
    ServerSocket ss = new ServerSocket(port);
    while (true) {
      final Socket socket = ss.accept();
      new Thread(new Runnable() {
        public void run() {
          while (true) {
            try {
              BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
              byte[] buf = new byte[1024];
              int len = in.read(buf); // read message from client
              String message = new String(buf, 0, len);
              BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());
              out.write(String.format("Send back %s", message).getBytes()); // echo to client
              out.flush();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }

        }
      }).start();
    }
  }

}
