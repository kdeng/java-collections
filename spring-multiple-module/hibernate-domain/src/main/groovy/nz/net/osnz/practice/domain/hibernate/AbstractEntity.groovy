package nz.net.osnz.practice.domain.hibernate

import groovy.transform.CompileStatic

import javax.persistence.*

/**
 *
 * @author Kefeng Deng (deng@51any.com)
 */
@MappedSuperclass
@CompileStatic
public abstract class AbstractEntity implements Serializable {

	@Id
	@Column(name = 'id', nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id


}
