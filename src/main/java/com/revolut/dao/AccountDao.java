package com.revolut.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.revolut.model.Account;

public class AccountDao {

	SessionFactory sessionFactory = null;

	private static AccountDao instance;

	private AccountDao() {

		sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	public static AccountDao getInstance() {
		if (instance == null) {
			instance = new AccountDao();
		}
		return instance;
	}

	public void createAccount(Account a) {

		try {

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(a);
			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			System.out.println();
		}
	}

	public Account getAccountByIban(String iban) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();

		EntityManager em = session.getEntityManagerFactory().createEntityManager();
		Query query = em.createQuery("SELECT e FROM Account e WHERE e.IBAN = :IBAN");
		query.setParameter("IBAN", iban);
		List<Account> resultList = query.getResultList();
		em.close();
		
		if (!resultList.isEmpty())
			return resultList.get(0);
		return null;

	}

	public void updateAccount(Account account) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(account);
		session.getTransaction().commit();
		session.close();
	}

}
