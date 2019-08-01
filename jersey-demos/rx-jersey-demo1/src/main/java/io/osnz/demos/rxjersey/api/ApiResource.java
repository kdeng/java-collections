package io.osnz.demos.rxjersey.api;

import io.reactivex.Flowable;
import org.glassfish.jersey.client.rx.rxjava2.RxFlowableInvoker;
import org.glassfish.jersey.client.rx.rxjava2.RxFlowableInvokerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Kefeng Deng (kefeng.deng@clearpoint.co.nz)
 */
@Path("/")
public class ApiResource {

  private static final Logger LOG = LoggerFactory.getLogger(ApiResource.class);

  private final WebTarget webTarget;

  public ApiResource() {
    Client client = ClientBuilder.newClient();
    client.register(RxFlowableInvokerProvider.class);
    webTarget = client.target("http://localhost:9090/user");
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Flowable<List<Long>> getUser() {
    // used to keep track of the progress of the subsequent calls
//    CountDownLatch completionTracker = new CountDownLatch(20);

    return webTarget
      .request()
      .rx(RxFlowableInvoker.class)
      .get(new GenericType<List<Long>>() {
      });

//    // wait for inner requests to complete in 10 seconds
//    if (!completionTracker.await(10, TimeUnit.SECONDS)) {
//      LOG.warn("Some requests didn't complete within the timeout");
//    }
  }

}
