package jat.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


import jat.dto.Account;



@ApplicationScoped
public class AccountDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	
	public List<Account> getAll() {
		return em.createQuery(
				"select a from Account a", Account.class)
				.getResultList();
	}
	
	@Transactional
	public void save(Account a) {
		if(a.getId() == 0) {
			em.persist(a);
		} else {
			a = em.merge(a);
		}
	}
	@Transactional
	public void delete(Account a) {
		Account find = find(a.getId());
		em.remove(find);
	}
	
	public Account findbyUsername(String usename, String password) {
		String query = String.format("select a from Account a where a.username = '%s' and a.password = '%s'", usename, password);
		List<Account> id_account = em.createQuery(query, Account.class).getResultList();
		if(id_account.size() == 0) {
			return null;
		}
		else {
			return id_account.get(0);
		}
		
	}
	public Account find(int id) {
		return em.find(Account.class, id);
	}
}
