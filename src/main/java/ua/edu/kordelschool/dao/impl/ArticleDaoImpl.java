package ua.edu.kordelschool.dao.impl;

import org.hibernate.Criteria;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.kordelschool.dao.ArticleDao;
import ua.edu.kordelschool.dao.GenericDao;
import ua.edu.kordelschool.entity.Article;

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
    public List<Article> getAll() {
        Criteria criteria = getSession().createCriteria(Article.class);

        return (List<Article>) criteria.list();
    }
}
