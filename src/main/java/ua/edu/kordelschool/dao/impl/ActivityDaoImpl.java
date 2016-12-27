package ua.edu.kordelschool.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.kordelschool.dao.ActivityDao;
import ua.edu.kordelschool.dao.GenericDao;
import ua.edu.kordelschool.entity.Activity;

import java.util.List;

/**
 * @author Yaroslav Kruk on 12/25/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@Repository("activityDao")
public class ActivityDaoImpl extends GenericDao<Activity> implements ActivityDao {

    public ActivityDaoImpl() {
        super(Activity.class);
    }

    @Transactional
    @SuppressWarnings("unchecked")
    @Override
    public List<Activity> getActivities() {
        Criteria criteria = getSession().createCriteria(Activity.class);

        return criteria.list();
    }
}
