package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.junit.BeforeClass;

public class Test {
	
	private static EntityManagerFactory entityManagerFactory;
	
	private static ParentEntity parent;
	
	@BeforeClass
	public static void initialize() {
		entityManagerFactory = Persistence.createEntityManagerFactory("test");
		
		InnerEntity inner = new InnerEntity();
		
		ChildEntity child1 = new ChildEntity();
		child1.setChildValue("value1");
		child1.setInnerEntity(inner);
		
		ChildEntity child2 = new ChildEntity();
		child2.setChildValue("value2");
		child2.setInnerEntity(inner);
		
		inner.getChildren().add(child1);
		inner.getChildren().add(child2);
		
		parent = new ParentEntity();
		parent.setInnerEntity(inner);
	}
	
	@org.junit.Test
	public void insertTest() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(parent);
		entityManager.getTransaction().commit();
		entityManager.detach(parent);
		entityManager.close();
		assertNotNull(parent.id);
	}
	
	@org.junit.Test
	public void testOrphan() {
		ParentEntity copyEntity = createNewParent();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Session session = entityManager.unwrap(Session.class);
		session.update(copyEntity);
		entityManager.getTransaction().commit();
		entityManager.close();
		
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		ParentEntity entityInDb = entityManager.find(ParentEntity.class, parent.id);
		assertEquals(0, entityInDb.getInnerEntity().getChildren().size());
	}
	
	private ParentEntity createNewParent() {
		ParentEntity parentEntity = new ParentEntity();
		parentEntity.setId(parent.getId());
		
		InnerEntity inner = new InnerEntity();
		inner.setId(parent.getInnerEntity().getId());
		
		parentEntity.setInnerEntity(inner);
		return parentEntity;
	}
}


