package ua.edu.kordelschool.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.kordelschool.dto.ActivityDto;
import ua.edu.kordelschool.entity.Activity;
import ua.edu.kordelschool.service.ActivityService;

/**
 * @author Yaroslav Kruk on 12/25/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@RestController
@RequestMapping("/activity")
public class ActivityEndpoint {

    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ActivityDto createActivity(@RequestBody ActivityDto activityDto) {
        Activity activity = activityService.createActivity(activityDto);

        activityDto.setId(activity.getId());

        return activityDto;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ActivityDto editActivity(@RequestBody ActivityDto activityDto) {
        Activity activity = activityService.editActivity(activityDto);

        return activityDto;
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeActivity(@PathVariable Long id) {
        activityService.removeActivity(id);

        return ResponseEntity.ok("removed");
    }
}
