package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFactory {
	
	private static EMFactory instance;
	
	private EntityManagerFactory factory;
	
	private EMFactory(){
		this.factory = Persistence.createEntityManagerFactory("hibernate_jpa");
	}
	
	public static EMFactory getInstance(){
		if(instance == null) instance = new EMFactory();
		return instance;
	}
	
	public EntityManager getEntityManager(){
		return this.factory.createEntityManager();
	}

	public void close() {
		this.factory.close();
	}

}
