package nz.net.osnz.app.domain

import groovy.transform.CompileStatic
import io.ebean.annotation.DbEnumValue

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@CompileStatic
public enum Role {

	USER,
	CLIENT,
	ADMIN,
	SUPER

	// map to DB INTEGER
	@DbEnumValue
	String getValue() {
		return name()
	}

}
