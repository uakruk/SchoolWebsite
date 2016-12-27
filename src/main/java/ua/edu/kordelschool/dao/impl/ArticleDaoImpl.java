package ua.edu.kordelschool.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.kordelschool.dao.ArticleDao;
import ua.edu.kordelschool.dao.GenericDao;
import ua.edu.kordelschool.entity.Article;
import ua.edu.kordelschool.entity.ArticleType;

import java.util.List;

/**
 * @author Yaroslav Kruk on 12/11/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@Repository("articleDao")
public class ArticleDaoImpl extends GenericDao<Article> implements ArticleDao {

    public ArticleDaoImpl() {
        super(Article.class);
    }

    @Transactional
    @Override
    @SuppressWarnings("unchecked")
    public List<Article> getAllArticles() {
        Criteria criteria = getSession().createCriteria(Article.class)
                .add(Restrictions.eq("type", ArticleType.ARTICLE))
                .addOrder(Order.desc("date"));

        return (List<Article>) criteria.list();
    }

    @Transactional
    @SuppressWarnings("unchecked")
    @Override
    public List<Article> getAllEvents() {
        Criteria criteria = getSession().createCriteria(Article.class)
                .add(Restrictions.eq("type", ArticleType.EVENT))
                .addOrder(Order.asc("date"));
        return criteria.list();
    }
}
