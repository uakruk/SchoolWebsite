package ua.edu.kordelschool.dao;

import ua.edu.kordelschool.entity.Article;

import java.util.List;

/**
 * @author Yaroslav Kruk on 12/11/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
public interface ArticleDao extends CRUD<Article> {

    List<Article> getAllArticles();

    List<Article> getAllEvents();
}
