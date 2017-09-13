package nz.net.osnz.practice.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

/**
 *
 * @author Kefeng Deng (deng@51any.com)
 */
@Entity
@Table(name = "USER")
public class User extends BaseEntity {

	@Column(name = "name", nullable = false, length = 120)
	String name

	@Column(name = "email", nullable = false, length = 120)
	String email

}
