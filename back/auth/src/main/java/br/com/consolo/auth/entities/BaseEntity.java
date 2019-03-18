package br.com.consolo.auth.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Base entity who has common methods and attributes
 * 
 * @author Erick
 *
 */
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @Column(name = "id", length = 36, nullable = false)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
