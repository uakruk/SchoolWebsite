package ua.edu.kordelschool.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.kordelschool.dao.GenericDao;
import ua.edu.kordelschool.dao.MottoDao;
import ua.edu.kordelschool.entity.Motto;

import java.util.List;

/**
 * @author Yaroslav Kruk on 12/25/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@Repository("mottoDao")
public class MottoDaoIml extends GenericDao<Motto> implements MottoDao {

    public MottoDaoIml() {
        super(Motto.class);
    }

    @Transactional
    @SuppressWarnings("unchecked")
    @Override
    public List<Motto> getMottos() {
        Criteria criteria = getSession().createCriteria(Motto.class);

        return criteria.list();
    }
}
