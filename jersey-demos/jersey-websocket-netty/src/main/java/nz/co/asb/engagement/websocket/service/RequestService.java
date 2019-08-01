package nz.co.asb.engagement.websocket.service;

import nz.co.asb.engagement.websocket.model.Client;
import nz.net.osnz.common.jackson.JacksonException;
import nz.net.osnz.common.jackson.JacksonHelper;
import org.apache.commons.lang3.StringUtils;

import java.util.Base64;

/**
 * @author Kefeng Deng
 * @since 2019-04-30
 */
public class RequestService {


  /**
   * Register incoming client request
   *
   * @param request a serialised JSON object
   *                {
   *                uid : 1,
   *                token: "43606811c7305ccc6abb2be116579bfd"
   *                }
   * @return
   */
  public static Client clientRegister(String request) throws JacksonException {
    String decodedRequest = new String(Base64.getDecoder().decode(request));
    Client newClient = JacksonHelper.deserialize(decodedRequest, Client.class);

    if (StringUtils.isBlank(newClient.getToken()) || StringUtils.isBlank(newClient.getClientId())) {
      throw new IllegalArgumentException(String.format("Invalid Client information in '%s'", decodedRequest));
    }

    if (StringUtils.isBlank(newClient.getTopic())) {
      newClient.setTopic("default");
    }

    return newClient;
  }


}
