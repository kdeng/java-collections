package nz.net.osnz.train.ws.service

import nz.net.osnz.train.ws.WsListOfPerson
import nz.net.osnz.train.ws.WsPerson
import org.apache.cxf.annotations.WSDLDocumentation

import javax.jws.WebMethod
import javax.jws.WebParam
import javax.jws.WebService

/**
 * @author Kefeng Deng
 */
@WebService(targetNamespace = WsPersonLookup.NAMESPACE)
public interface WsPersonLookup {

	public static final String NAMESPACE = "http://www.osnz.net.nz/train/PersonReader/v1";

	@WSDLDocumentation(value = "Call to find user records for a specific person information")
	@WebMethod(operationName = "lookup")
	public WsListOfPerson find(@WebParam(name = "person") WsPerson wsPerson);


	@WSDLDocumentation(value = "Find all person information")
	@WebMethod(operationName = "findAll")
	public WsListOfPerson findAll();

}