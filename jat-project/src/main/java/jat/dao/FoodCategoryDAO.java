package jat.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


import jat.dto.FoodCategory;



@ApplicationScoped
public class FoodCategoryDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	
	public List<FoodCategory> getAll() {
		return em.createQuery(
				"select a from FoodCategory a", FoodCategory.class)
				.getResultList();
	}
	
	@Transactional
	public void save(FoodCategory a) {
		if(a.getId() == 0) {
			em.persist(a);
		} else {
			a = em.merge(a);
		}
	}
	
	@Transactional
	public void delete(FoodCategory a) {
		FoodCategory find = find(a.getId());
		em.remove(find);
	}
	
	public FoodCategory find(int id) {
		return em.find(FoodCategory.class, id);
	}
	
}
