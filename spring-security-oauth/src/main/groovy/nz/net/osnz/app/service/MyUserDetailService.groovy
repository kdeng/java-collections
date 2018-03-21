package nz.net.osnz.app.service

import nz.net.osnz.app.domain.User
import nz.net.osnz.app.model.MyUserDetails
import nz.net.osnz.app.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Service
class MyUserDetailService implements UserDetailsService {

	@Autowired
	UserRepository userRepository

	@Override
	MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
		if (user) {

		}
		return null
	}

}
