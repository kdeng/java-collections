package nz.net.osnz.practice.domain

import nz.net.osnz.practice.domain.hibernate.Product
import org.springframework.stereotype.Component

//import com.avaje.ebean.EbeanServer
//import org.springframework.beans.factory.annotation.Autowired
import javax.annotation.PostConstruct
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

/**
 *
 * @author Kefeng Deng (deng@51any.com)
 */
@Component
class MockDomain {

//
//	@Autowired
//	EbeanServer server

	@PersistenceContext
	EntityManager entityManager

	@PostConstruct
	public void mockDomains() {
//		assert server
		assert entityManager

		mockProduct()
	}


	protected void mockProduct() {
		Product product = new Product()
		product.name = 'Apple Iphone'
		entityManager.persist(product)
	}

}
