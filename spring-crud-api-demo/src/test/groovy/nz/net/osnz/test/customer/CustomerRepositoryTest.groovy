package nz.net.osnz.test.customer

import org.junit.Before
import org.junit.Test

/**
 *
 * @author Kefeng Deng (deng@51any.com)
 */
public class CustomerRepositoryTest {

	CustomerRepository repository

	@Before
	public void init() {
		repository = new CustomerRepository()
	}

	@Test
	public void testGetCustomer() {
		assert repository.getCustomerById(1)
		assert repository.getCustomerById(2)
		assert repository.getCustomerById(3)
		assert repository.getCustomerById(4)

		assert repository.getCustomerById(5) == null
	}

	@Test
	public void testSaveCustomer() {
		repository.saveCustomer(new CustomerEntity())
		assert repository.count() == 5

		repository.saveCustomer([:] as CustomerEntity)
		assert repository.count() == 6
	}

	@Test
	public void testUpdateCustomer() {
		assert repository.updateCustomer(1, new CustomerEntity(name: 'bobo'))
		CustomerEntity entity = repository.getCustomerById(1)
		assert entity.name == 'bobo'

		assert repository.updateCustomer(5, [:] as CustomerEntity) == null
	}

	@Test
	public void testDeleteCustomer() {
		assert repository.deleteCustomer(1)
		assert repository.deleteCustomer(2)
		assert repository.deleteCustomer(3)
		assert repository.deleteCustomer(4)
		assert repository.deleteCustomer(5) == false
	}
}
