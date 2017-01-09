package ua.edu.kordelschool.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.kordelschool.dao.GenericDao;
import ua.edu.kordelschool.dao.UserDao;
import ua.edu.kordelschool.entity.User;

/**
 * @author Yaroslav Kruk on 12/29/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@Repository("userDao")
public class UserDaoImpl extends GenericDao<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

    @Transactional
    @Override
    public User readByEmail(String email) {
        Criteria criteria = getSession().createCriteria(User.class)
                .add(Restrictions.eq("email", email));
        return (User) criteria.uniqueResult();
    }
}
