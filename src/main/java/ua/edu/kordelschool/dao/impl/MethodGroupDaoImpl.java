package ua.edu.kordelschool.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.kordelschool.dao.GenericDao;
import ua.edu.kordelschool.dao.MethodGroupDao;
import ua.edu.kordelschool.entity.MethodGroup;

import java.util.List;

/**
 * @author Yaroslav Kruk on 12/25/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@Repository("methodGroupDao")
public class MethodGroupDaoImpl extends GenericDao<MethodGroup> implements MethodGroupDao {

    public MethodGroupDaoImpl() {
        super(MethodGroup.class);
    }

    @Transactional
    @SuppressWarnings("unchecked")
    @Override
    public List<MethodGroup> getAllMethodGroups() {
        Criteria criteria = getSession().createCriteria(MethodGroup.class);

        return criteria.list();
    }
}
