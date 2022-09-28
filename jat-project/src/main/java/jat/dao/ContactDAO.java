package jat.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import jat.dto.Contact;



@ApplicationScoped
public class ContactDAO {

	@PersistenceContext
	private EntityManager em;
	
	
	public List<Contact> getAll() {
		return em.createQuery(
				"select a from Contact a", Contact.class)
				.getResultList();
	}
	
	@Transactional
	public void save(Contact a) {
		if(a.getId() == 0) {
			em.persist(a);
		} else {
			a = em.merge(a);
		}
	}
}
