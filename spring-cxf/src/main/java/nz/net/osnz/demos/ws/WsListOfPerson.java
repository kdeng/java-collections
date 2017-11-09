package nz.net.osnz.demos.ws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

public class WsListOfPerson {

	/**
	 * List of persons
	 */

	private List<WsPerson> persons;

	@XmlElement(name = "Person")
	@XmlElementWrapper(name = "Persons")
	public void setPersons(List<WsPerson> users) {
		if (this.persons == null) {
			this.persons = new ArrayList<>();
		}
		this.persons.addAll(users);
	}

	public List<WsPerson> getPersons() {
		if (this.persons != null) {
			return this.persons;
		}

		return new ArrayList<WsPerson>();
	}


}
