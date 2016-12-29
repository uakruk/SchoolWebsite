package ua.edu.kordelschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.edu.kordelschool.entity.Article;
import ua.edu.kordelschool.entity.MethodGroup;
import ua.edu.kordelschool.entity.Motto;
import ua.edu.kordelschool.service.ArticleService;
import ua.edu.kordelschool.service.MethodGroupService;
import ua.edu.kordelschool.service.MottoService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * @author Yaroslav Kruk on 12/26/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@Controller
public class DashboardController {

    @Autowired
    private MethodGroupService methodGroupService;

    @Autowired
    private MottoService mottoService;

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashBoard(Model model) {
        List<MethodGroup> methodGroups = methodGroupService.getMethodGroups();
        model.addAttribute("mets", methodGroups);

        List<Motto> slogans = mottoService.getMottos();
        model.addAttribute("slogans", slogans);

        model.addAttribute("title", "Головна");

        List<Article> articles = articleService.getAllArticles();
        model.addAttribute("articles", articles);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM", new Locale("uk", "UA"));

        model.addAttribute("calendar", Calendar.getInstance());
        model.addAttribute("dateFormat", simpleDateFormat);

        return "dashboard";
    }
}
