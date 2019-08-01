package nz.net.osnz.demos.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
public class SocketClient {

    private static final Logger LOG = LoggerFactory.getLogger(SocketClient.class);

    public static String sendMessage(String message) {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 9000);
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(message);

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                response.append(line);
            }

            return response.toString();
        } catch (UnknownHostException ex) {
            LOG.error("Failed to connect localhost on port 9000", ex);
        } catch (Exception ex) {
            LOG.error("Unexpected error when sending a message", ex);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                    socket = null;
                } catch (Exception ex) {
                    // do nothing
                }
            }
        }
        return null;
    }


}
