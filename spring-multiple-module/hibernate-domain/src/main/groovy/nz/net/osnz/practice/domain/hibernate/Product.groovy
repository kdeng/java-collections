package nz.net.osnz.practice.domain.hibernate

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

/**
 *
 * @author Kefeng Deng (deng@51any.com)
 */
@Entity
@Table(name = "PRODUCT")
public class Product extends AbstractEntity {

	@Column(name = "NAME", length = 100, nullable = false)
	String name

}
