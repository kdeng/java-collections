package nz.net.osnz.demos.cxf.ws;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * a data-container for the person information that is sent to the PersonLookupService service
 *
 * @author Kefeng Deng
 */
public class WsPerson {

	/**
	 * Person ID
	 */
	private String id;

	/**
	 * Person mobile number
	 */
	private String mobile;

	/**
	 * person email
	 */

	private String email;

	/**
	 * setter and getter
	 */
	@XmlAttribute(name = "id")
	public void setId(String id) {
		this.id = id;
	}

	@XmlElement(nillable = true, name = "mobile")
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@XmlElement(nillable = true, name = "email")
	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return this.id;
	}

	public String getMobile() {
		return this.mobile;
	}

	public String getEmail() {
		return this.email;
	}

}
