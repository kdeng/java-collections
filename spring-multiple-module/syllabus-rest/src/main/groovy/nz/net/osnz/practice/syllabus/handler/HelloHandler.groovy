package nz.net.osnz.practice.syllabus.handler

import nz.ac.auckland.syllabus.events.Event
import nz.ac.auckland.syllabus.http.Get
import nz.ac.auckland.syllabus.http.Public

/**
 *
 * @author Kefeng Deng (deng@51any.com)
 */
@Event(namespace = "bus", name = "hello")
//@Get
//@Public
class HelloHandler {

	public HelloResponse handle() {
		HelloResponse response = new HelloResponse()
		response.name = 'Syllabus'
		response.now = new Date()
		return response
	}

	static class HelloResponse {
		String name
		Date now
	}

}
