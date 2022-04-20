package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestEMFactory {
	
	private static TestEMFactory instance;
	
	private EntityManagerFactory factory;
	
	private TestEMFactory(){
		this.factory = Persistence.createEntityManagerFactory("hibernate_jpa");
	}
	
	public static TestEMFactory getInstance(){
		if(instance == null) instance = new TestEMFactory();
		return instance;
	}
	
	public EntityManager getEntityManager(){
		return this.factory.createEntityManager();
	}

	public void close() {
		this.factory.close();
	}

}
