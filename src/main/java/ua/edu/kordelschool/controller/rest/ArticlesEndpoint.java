package ua.edu.kordelschool.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.kordelschool.dto.ArticleDto;
import ua.edu.kordelschool.entity.Article;
import ua.edu.kordelschool.service.ArticleService;

/**
 * @author Yaroslav Kruk on 12/11/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@RestController
@RequestMapping("/article")
public class ArticlesEndpoint {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ArticleDto createArticle(@RequestBody ArticleDto articleDto) {
        Article article = articleService.createArticle(articleDto);

        articleDto.setId(article.getId());

        return articleDto;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ArticleDto editArticle(@RequestBody ArticleDto articleDto) {
        Article article = articleService.editArticle(articleDto);

        return articleDto;
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeArticle(@PathVariable Long id) {
        articleService.removeArticle(id);

        return ResponseEntity.ok("removed");
    }
}
