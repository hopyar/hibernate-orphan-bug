package test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "child_entity")
@Entity(name = "ChildEntity")
public class ChildEntity extends PersistentEntity {
	
	@ManyToOne
	@JoinColumn(name = "inner_entity")
	private InnerEntity innerEntity;
	
	@Column(name = "child_value")
	private String childValue;

	public InnerEntity getInnerEntity() {
		return innerEntity;
	}

	public void setInnerEntity(InnerEntity innerEntity) {
		this.innerEntity = innerEntity;
	}

	public String getChildValue() {
		return childValue;
	}

	public void setChildValue(String childValue) {
		this.childValue = childValue;
	}

	
	
}
