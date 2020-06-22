package test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "inner_entity")
@Entity(name = "InnerEntity")
public class InnerEntity extends PersistentEntity {
	
	@OneToMany(mappedBy = "innerEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ChildEntity> children = new ArrayList<>();

	public List<ChildEntity> getChildren() {
		return children;
	}

	public void setChildren(List<ChildEntity> children) {
		this.children = children;
	}

	
	
	
}
