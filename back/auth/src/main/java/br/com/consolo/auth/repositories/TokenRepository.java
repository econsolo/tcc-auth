package br.com.consolo.auth.repositories;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.consolo.auth.entities.Token;

@Repository
public class TokenRepository extends BaseRepository<Token> {

    public Token findByUser(String idUser) {
        StringBuilder hql = new StringBuilder(" SELECT t ");
        hql.append(" FROM ").append(Token.class.getName()).append(" t ");
        hql.append(" INNER JOIN FETCH t.user u ");
        hql.append(" INNER JOIN FETCH u.role r ");
        hql.append(" WHERE u.id = :id_user ");

        Query query = getEntityManager().createQuery(hql.toString());
        query.setParameter("id_user", idUser);

        return (Token) query.getSingleResult();
    }
}
