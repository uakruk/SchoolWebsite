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

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
public class DaoTests {

    @Autowired
    private ActivityDao activityDao;

    private Activity activity;

    private void createActivity() {
        activity = new Activity();
        activity.setText("Activity test");
        activity.setIcon("Activity icon");
        activity.setName("Activity name");
    }

    @Before
    public void setUp() {
        createActivity();
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
}
