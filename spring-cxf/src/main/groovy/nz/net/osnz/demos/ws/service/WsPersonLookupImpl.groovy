package nz.net.osnz.demos.ws.service

import nz.net.osnz.demos.ws.WsListOfPerson
import nz.net.osnz.demos.ws.WsPerson

import javax.jws.WebService

/**
 * @author Kefeng Deng
 */
@WebService
public class WsPersonLookupImpl implements WsPersonLookup {

	@Override
	WsListOfPerson findAll() {
		return new WsListOfPerson(persons : [
				new WsPerson(id: 1, email: 'dkf_genius1@hotmail', mobile: '021856068'),
				new WsPerson(id: 2, email: 'dkf_genius2@hotmail', mobile: '021111222'),
				new WsPerson(id: 3, email: 'dkf_genius3@hotmail', mobile: '021333444'),
		])
	}

	@Override
	public WsListOfPerson find(WsPerson wsPerson) {
		return new WsListOfPerson(persons: [
				new WsPerson(id: 1, email: 'dkf_genius1@hotmail', mobile: '021856068'),
				new WsPerson(id: 3, email: 'dkf_genius3@hotmail', mobile: '021333444')
		])
	}

}
