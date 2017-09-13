package nz.net.osnz.practice.domain.hibernate.repository

import nz.net.osnz.practice.domain.hibernate.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

/**
 * @author Kefeng Deng (deng@51any.com)
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Transactional(readOnly = true)
	@Override
	@Query('SELECT OBJECT(p) FROM Product p ORDER BY p.name ASC')
	public List<Product> findAll()


}