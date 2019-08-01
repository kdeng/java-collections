package nz.net.osnz.demos.jms

/**
 * @author Kefeng Deng (deng@51any.com)
 */
class Message {

  String to
  String body

  @Override
  String toString() {
    return String.format("Message{to=%s, body=%s}", getTo(), getBody());
  }

}
