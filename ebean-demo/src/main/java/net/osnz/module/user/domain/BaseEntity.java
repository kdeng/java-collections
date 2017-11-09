package net.osnz.module.user.domain;

import io.ebean.annotation.CreatedTimestamp;
import net.osnz.module.user.field.ModelStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@MappedSuperclass
@SuppressWarnings("unused")
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
//  @NotNull
	@Column(name = "ID", unique = true)
//  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OSNZ.OSNZ_SEQUENCE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//  @GeneratedValue(strategy = GenerationType.AUTO)
//  @GeneratedValue(strategy = GenerationType.TABLE)
	protected Long id;

	@CreatedTimestamp
	@Column(name = "CREATED_ON", nullable = true)
	protected Timestamp dateCreated = (new Timestamp((new Date()).getTime()));

	@Version
	@Column(name = "UPDATED_ON", nullable = true)
	protected Timestamp dateUpdated;

	@Column(name = "ACTIVE", nullable = true)
	protected ModelStatus status = ModelStatus.INACTIVATED;


	@SuppressWarnings("unused")
	public Long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	public void setId(Long id) {
		this.id = id;
	}

	@SuppressWarnings("unused")
	public Timestamp getDateCreated() {
		return this.dateCreated;
	}

	@SuppressWarnings("unused")
	public void setDateCreated(Timestamp createdTime) {
		this.dateCreated = createdTime;
	}

	@SuppressWarnings("unused")
	public Timestamp getDateUpdated() {
		return this.dateUpdated;
	}

	@SuppressWarnings("unused")
	public void setDateUpdated(Timestamp updatedTime) {
		this.dateUpdated = updatedTime;
	}

	@SuppressWarnings("unused")
	public void setStatus(ModelStatus status) {
		this.status = status;
	}

	@SuppressWarnings("unused")
	public ModelStatus getStatus() {
		return this.status;
	}


}
