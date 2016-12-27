package ua.edu.kordelschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.kordelschool.dao.ActivityDao;
import ua.edu.kordelschool.dto.ActivityDto;
import ua.edu.kordelschool.entity.Activity;

import java.util.List;

/**
 * @author Yaroslav Kruk on 12/25/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@Service
public class ActivityService {
    
    @Autowired
    private ActivityDao activityDao;

    public Activity createActivity(ActivityDto activityDto) {

        Activity activity = new Activity();
        activity.setText(activityDto.getText());
        activity.setName(activityDto.getName());
        activity.setIcon(activityDto.getIcon());

        Activity response = activityDao.create(activity);

        return response;
    }

    public Activity getActivity(Long activityId) {

        Activity response = activityDao.read(activityId);

        return response;
    }

    public ActivityDto getActivityDto(Long activityId) {

        Activity activity = getActivity(activityId);

        ActivityDto activityDto = new ActivityDto();
        activityDto.setText(activity.getText());
        activityDto.setName(activity.getName());
        activityDto.setIcon(activity.getIcon());
        activityDto.setId(activityId);

        return activityDto;
    }

    public List<Activity> getActivities() {

        List<Activity> activitys = activityDao.getActivities();

        return activitys;
    }

    public void removeActivity(Long activityId) {
        activityDao.delete(activityId);
    }

    public Activity editActivity(ActivityDto activityDto) {
        Activity activity = activityDao.read(activityDto.getId());

        activity.setText(activityDto.getText());
        activity.setName(activityDto.getName());
        activity.setIcon(activityDto.getIcon());

        Activity response = activityDao.update(activity);

        return response;
    }
}
