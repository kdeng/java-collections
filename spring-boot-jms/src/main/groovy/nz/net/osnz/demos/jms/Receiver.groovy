package nz.net.osnz.demos.jms

import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Component
class Receiver {

    @JmsListener(destination = "messageBox", containerFactory = "myFactory")
    public void receiveMessage(Message message) {
        System.out.println("Received <" + message + ">")
    }

}
