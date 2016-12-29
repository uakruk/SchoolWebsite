package ua.edu.kordelschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.edu.kordelschool.entity.*;
import ua.edu.kordelschool.service.ActivityService;
import ua.edu.kordelschool.service.ArticleService;
import ua.edu.kordelschool.service.MethodGroupService;
import ua.edu.kordelschool.service.MottoService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * @author Yaroslav Kruk on 12/8/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@Controller
public class MainPageController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private MottoService mottoService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private Information info;

    @Autowired
    private MethodGroupService methodGroupService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage(Model model) {

        List<Article> articles = articleService.getAllArticles();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM", new Locale("uk", "UA"));

        model.addAttribute("articles", articles);
        model.addAttribute("calendar", Calendar.getInstance());
        model.addAttribute("dateFormat", simpleDateFormat);

        List<Motto> slogans = mottoService.getMottos();
        model.addAttribute("slogans", slogans);

        List<Activity> activities = activityService.getActivities();
        model.addAttribute("activities", activities);

        List<Article> events = articleService.getAllEvents();
        model.addAttribute("events", events);
        model.addAttribute("info", info);

        List<MethodGroup> methodGroups = methodGroupService.getMethodGroups();
        model.addAttribute("mets", methodGroups);

        return "layout";
    }

    @RequestMapping(value = "/gallery", method = RequestMethod.GET)
    public String gallery() {
        return "gallery";
    }
}
