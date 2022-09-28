package jat.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import jat.dto.Food;





@ApplicationScoped
public class FoodDAO {

	@PersistenceContext
	private EntityManager em;
	
	public List<Food> getAll() {
		return em.createQuery(
				"select a from Food a", Food.class)
				.getResultList();
	}
	
	@Transactional
	public void save(Food a) {
		if(a.getId() == 0) {
			em.persist(a);
		} else {
			a = em.merge(a);
		}
	}
	
	@Transactional
	public void delete(Food a) {
		Food find = find(a.getId());
		em.remove(find);
	}
	
	public Food find(int id) {
		return em.find(Food.class, id);
	}
	
	
	
}
