package nz.net.osnz.test.customer

import org.springframework.stereotype.Component

/**
 *
 * @author Kefeng Deng (deng@51any.com)
 */
@Component
public class CustomerRepository {

	public List<CustomerEntity> customerList = [] as ArrayList<CustomerEntity>

	public CustomerRepository() {
		customerList.addAll([
				new CustomerEntity(id: 1L, name: 'aaa', email: 'aaa@aaa.com', phone: '021111111'),
				new CustomerEntity(id: 2L, name: 'bbb', email: 'bbb@bbb.com', phone: '021222222'),
				new CustomerEntity(id: 3L, name: 'ccc', email: 'ccc@ccc.com', phone: '021333333'),
				new CustomerEntity(id: 4L, name: 'ddd', email: 'ddd@ddd.com', phone: '021444444')
		])
	}

	public int count() {
		return customerList.size()
	}

	public CustomerEntity getCustomerById(Long id) {
		return customerList.find({ CustomerEntity entity ->
			return entity.id == id
		})
	}

	public CustomerEntity saveCustomer(CustomerEntity entity) {
		// Potential Error
		entity.id = customerList.size() + 1
		customerList.add(entity)
		return entity
	}

	public CustomerEntity updateCustomer(Long id, CustomerEntity entity) {
		CustomerEntity freshEntity = null
		customerList.each({ CustomerEntity customerEntity ->
			if (customerEntity.id == id) {
				customerEntity.name = entity.name
				customerEntity.email = entity.email
				customerEntity.phone = entity.phone
				freshEntity = customerEntity
			}
		})
		return freshEntity
	}

	public boolean deleteCustomer(Long id) {
		CustomerEntity entity = customerList.find({ CustomerEntity entity -> return entity.id == id})
		return entity ? customerList.remove(entity) : false
	}

}
