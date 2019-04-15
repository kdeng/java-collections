package nz.net.osnz.demos.jersey;

import nz.net.osnz.common.jackson.JacksonException;
import nz.net.osnz.common.jackson.JacksonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class UserMessageBodyWriter implements MessageBodyWriter<User> {

  private static final Logger LOG = LoggerFactory.getLogger(UserMessageBodyWriter.class);

  @Override
  public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
    return type == User.class;
  }

  @Override
  public void writeTo(User user, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
    try (final Writer writer = new PrintWriter(entityStream)) {
      writer.write(JacksonHelper.serialize(user));
      writer.flush();
    } catch (JacksonException ex) {
      LOG.error("Cannot serialise User object {}", user, ex);
    }

  }

}
