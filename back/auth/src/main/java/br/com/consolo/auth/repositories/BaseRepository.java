package br.com.consolo.auth.repositories;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.core.GenericTypeResolver;

import br.com.consolo.auth.entities.BaseEntity;

public abstract class BaseRepository<T extends BaseEntity> {

	private final Class<T> genericType;

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public BaseRepository() {
        this.genericType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), BaseRepository.class);
    }
    
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public String generateUuid() {
        return UUID.randomUUID().toString();
    }
    
    public String persist(T entity) {
        String uuidPrimaryKey = generateUuid();
        entity.setId(uuidPrimaryKey);
        getEntityManager().persist(entity);
        return uuidPrimaryKey;
    }

    public String merge(T entity) {
        getEntityManager().merge(entity);
        return entity.getId();
    }

    public void remove(T entity) {
        getEntityManager().remove(entity);
    }

    public T find(String id) {
        return getEntityManager().find(genericType, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return getEntityManager().createQuery(" FROM " + genericType.getSimpleName()).getResultList();
    }
    
}
