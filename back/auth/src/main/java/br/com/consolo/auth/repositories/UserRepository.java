package br.com.consolo.auth.repositories;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.consolo.auth.entities.User;

@Repository
public class UserRepository extends BaseRepository<User> {

	public User getByEmailPassword(String email, String password) {
		
		User user = null;
		
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT u ")
			.append(" FROM ").append(User.class.getName()).append(" u ")
			.append(" WHERE 1 = 1")
			.append(" AND u.email = :email ")
			.append(" AND u.password = :password ");
		
		
		Query query = getEntityManager().createQuery(jpql.toString());
		
		query.setParameter("email", email);
		query.setParameter("password", password);
		
		try {
			user = (User) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
		
	}

}
