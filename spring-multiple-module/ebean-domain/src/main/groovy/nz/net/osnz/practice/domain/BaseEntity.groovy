package nz.net.osnz.practice.domain

import javax.persistence.*

/**
 *
 * @author Kefeng Deng (deng@51any.com)
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	@Id
	@Column(name = 'id', nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id

}
