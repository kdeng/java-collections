package nz.net.osnz.demos.j2ee.servlet

import org.eclipse.jetty.websocket.WebSocket
import org.eclipse.jetty.websocket.WebSocketServlet

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created with IntelliJ IDEA.
 * User: kdeng
 * Date: 19/11/13
 * Time: 9:48 AM
 * To change this template use File | Settings | File Templates.
 */
class EchoWebSocketServlet extends WebSocketServlet {

    protected final Map<String, EchoSocket> members = new HashMap<>()

    @Override
    public void init() throws ServletException {
        super.init()
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getNamedDispatcher("default").forward(request, response);
    }

    protected void broardcast(String message) {
        members.each {String id, EchoSocket echoSocket ->
            echoSocket.sendMessage("[${id}]: ${message}")
        }
    }

    @Override
    WebSocket doWebSocketConnect(HttpServletRequest request, String protocol) {
        return new EchoSocket(request.getRemoteAddr(), request.getRemotePort())
    }

    class EchoSocket implements WebSocket.OnTextMessage {

        private String remoteIP

        private WebSocket.Connection connection

        public EchoSocket(String ip, int port) {
            this.remoteIP = ip + ':' + port
        }

        @Override
        void onOpen(WebSocket.Connection connection) {
            System.out.println("Opened a connection : ")
            this.connection = connection
            members.put(this.remoteIP, this)
        }

        @Override
        void onMessage(String data) {
            System.out.println("Received message from client : " + data)
            broardcast(data)
        }

        @Override
        void onClose(int closeCode, String message) {
            System.out.println('Closed: ' + closeCode + ' -> ' + message)
            members.remove(this.remoteIP)
            this.connection.close()
            this.connection = null
            broardcast("Has left from chat")
        }

        /**
         * Send message to client
         * @param data
         * @throws IOException
         */
        public void sendMessage(String data) throws IOException {
            System.out.println("Sending message to client : " + data)
            this.connection.sendMessage(data)
        }


    }

}
