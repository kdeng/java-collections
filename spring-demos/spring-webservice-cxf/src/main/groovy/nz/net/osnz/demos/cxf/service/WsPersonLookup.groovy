package nz.net.osnz.demos.cxf.service

import nz.net.osnz.demos.cxf.ws.WsListOfPerson
import nz.net.osnz.demos.cxf.ws.WsPerson
import org.apache.cxf.annotations.WSDLDocumentation

import javax.jws.WebMethod
import javax.jws.WebParam
import javax.jws.WebService

/**
 * @author Kefeng Deng
 */
@WebService(targetNamespace = WsPersonLookup.NAMESPACE)
public interface WsPersonLookup {

  public static final String NAMESPACE = "http://www.osnz.net.nz/demos/PersonReader/v1";

  @WSDLDocumentation(value = "Call to find user records for a specific person information")
  @WebMethod(operationName = "lookup")
  public WsListOfPerson find(@WebParam(name = "person") WsPerson wsPerson);


  @WSDLDocumentation(value = "Find all person information")
  @WebMethod(operationName = "findAll")
  public WsListOfPerson findAll();

}
