package nz.net.osnz.practice.domain.hibernate

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.transaction.annotation.Transactional

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

/**
 *
 * @author Kefeng Deng (deng@51any.com)
 */
@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration("classpath:simple-spring.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@Transactional
class ProductUnitTest {

	@PersistenceContext
	EntityManager entityManager

	@Before
	public void setUp() {
		assert entityManager
	}

	@Test
	@Transactional
	public void testEverything() {

		testSave()

		testLoad()

		testUpdate()

		testFreshLoad()

	}

	private void testSave() {
		Product product = mockProduct()
		entityManager.persist(product)
	}

	private void testLoad() {
		List<Product> productList = entityManager.createQuery("SELECT OBJECT(p) FROM Product p", Product).resultList
		assert productList.size() == 1
	}

	private void testUpdate() {
		Product product = entityManager.createQuery('FROM Product p', Product).singleResult
		product.name = 'Apple Iphone'
		entityManager.persist(product)
	}

	private void testFreshLoad() {
		List<Product> productList = entityManager.createQuery("SELECT OBJECT(p) FROM Product p", Product).resultList
		assert productList.size() == 1
		assert productList.first().name == 'Apple Iphone'
	}

	private Product mockProduct() {
		return new Product(name: 'Apple MacBook Pro 2015')

	}

}
