package nz.net.osnz.test.customer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletResponse

/**
 *
 * @author Kefeng Deng (deng@51any.com)
 */
@RestController
@RequestMapping(value = '/customer')
public class CustomerController {

  @Autowired
  CustomerRepository repository

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public CustomerEntity read(@PathVariable('id') Long id, HttpServletResponse response) {
    CustomerEntity entity = repository.getCustomerById(id)
    if (entity) {
      return entity
    }
    response.setStatus(HttpServletResponse.SC_NOT_FOUND)
    return null
  }

  @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  public CustomerEntity create(@RequestBody CustomerEntity entity, HttpServletResponse response) {
    return repository.saveCustomer(entity)
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public CustomerEntity update(@PathVariable('id') Long id, @RequestBody CustomerEntity entity, HttpServletResponse response) {
    CustomerEntity dirtyEntity = repository.getCustomerById(id)
    if (!dirtyEntity) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND)
      return null
    }
    return repository.updateCustomer(id, entity)
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public Map delete(@PathVariable('id') Long id, HttpServletResponse response) {
    if (repository.deleteCustomer(id)) {
      return [result: true, id: id]
    }

    response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
    return [result: false]
  }

}
