package ua.edu.kordelschool.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;
import ua.edu.kordelschool.entity.Activity;
import ua.edu.kordelschool.entity.Article;
import ua.edu.kordelschool.entity.ArticleType;
import ua.edu.kordelschool.entity.MethodGroup;

import javax.transaction.Transactional;
import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
public class DaoTests {

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private MethodGroupDao methodGroupDao;

    private Activity activity;
    private Article article;
    private MethodGroup methodGroup;

    private void createMethodGroup() {
        methodGroup = new MethodGroup();
        methodGroup.setName("group");
        methodGroup.setDescription("-_-_-");
    }

    private void createArticle() {
        article = new Article();
        article.setAuthor("Article author");
        article.setCaption("some caption");
        article.setText("lorem");
        article.setType(ArticleType.ARTICLE);
        article.setDate(Calendar.getInstance());
    }

    private void createActivity() {
        activity = new Activity();
        activity.setText("Activity test");
        activity.setIcon("Activity icon");
        activity.setName("Activity name");
    }

    @Before
    public void setUp() {
        createActivity();
        createArticle();
        createMethodGroup();
    }

    @Test
    @Rollback
    @Transactional
    public void testActivitesCRUD() {
        // create
        Activity response = activityDao.create(activity);

        assertThat(response.getText()).isEqualTo("Activity test");
        assertThat(response.getName()).isEqualTo("Activity name");

        assertThat(response.getId()).isNotNull();
        assertThat(response.getId()).isEqualTo(activity.getId());
        // read
        response = activityDao.read(activity.getId());

        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(activity);

        //update
        activity.setIcon("new icon");
        response = activityDao.update(activity);
        assertThat(response.getIcon()).isEqualTo("new icon");

        //delete
        activityDao.delete(activity.getId());
        assertThat(activityDao.read(activity.getId())).isNull();
    }

    @Test
    @Rollback
    @Transactional
    public void testArticlesCRUD() {
        // create
        Article response = articleDao.create(article);

        assertThat(response.getType()).isEqualTo(ArticleType.ARTICLE);

        assertThat(response.getId()).isNotNull();
        assertThat(response.getId()).isEqualTo(article.getId());
        // read
        response = articleDao.read(article.getId());

        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(article);

        //update
        article.setType(ArticleType.EVENT);
        response = articleDao.update(article);
        assertThat(response.getType()).isEqualTo(ArticleType.EVENT);

        //delete
        articleDao.delete(article.getId());
        assertThat(articleDao.read(article.getId())).isNull();
    }

    @Test
    @Rollback
    @Transactional
    public void testMethodGroupsCRUD() {
        // create
        MethodGroup response = methodGroupDao.create(methodGroup);

        assertThat(response.getId()).isNotNull();
        assertThat(response.getId()).isEqualTo(methodGroup.getId());
        // read
        response = methodGroupDao.read(methodGroup.getId());

        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(methodGroup);

        //update
        methodGroup.setDescription("+");
        response = methodGroupDao.update(methodGroup);
        assertThat(response.getDescription()).isEqualTo("+");

        //delete
        methodGroupDao.delete(methodGroup.getId());
        assertThat(methodGroupDao.read(methodGroup.getId())).isNull();
    }
}
