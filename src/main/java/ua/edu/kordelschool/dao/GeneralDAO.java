package ua.edu.kordelschool.dao;

import org.hibernate.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Yaroslav Kruk on 12/11/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
public abstract class GeneralDAO {

    @PersistenceContext
    protected EntityManager entityManager;

    protected Session getSession() {
        return entityManager.unwrap(Session.class);
    }
}
