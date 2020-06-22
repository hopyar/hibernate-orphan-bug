package test;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "parent_entity")
@Entity(name = "ParentEntity")
public class ParentEntity extends PersistentEntity {
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "inner_entity")
	private InnerEntity innerEntity = new InnerEntity();

	public InnerEntity getInnerEntity() {
		return innerEntity;
	}

	public void setInnerEntity(InnerEntity innerEntity) {
		this.innerEntity = innerEntity;
	}

	
	
	
}
