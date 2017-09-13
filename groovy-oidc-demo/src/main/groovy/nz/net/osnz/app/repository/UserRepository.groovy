package nz.net.osnz.app.repository

import io.ebean.EbeanServer
import nz.net.osnz.app.domain.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Repository
class UserRepository {

	@Autowired
	EbeanServer ebeanServer

	public User findByUsername(String username) {
		return ebeanServer.find(User).fetch('username', username).findUnique()
	}


}
