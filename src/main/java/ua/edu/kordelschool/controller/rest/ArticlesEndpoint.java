package ua.edu.kordelschool.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.kordelschool.dto.ArticleDto;
import ua.edu.kordelschool.dto.AttachmentDto;
import ua.edu.kordelschool.entity.Article;
import ua.edu.kordelschool.entity.Attachment;
import ua.edu.kordelschool.service.ArticleService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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

    @RequestMapping(value="/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<ArticleDto> getAllArticles() {
        Collection<ArticleDto> articlesDto = new ArrayList<>();
        List<Article> articles = articleService.getAllArticles();
        articles.stream().map(article -> {
            ArticleDto articleDto = new ArticleDto();
            articleDto.setCaption(article.getCaption());
            articleDto.setAuthor(article.getAuthor());
            articleDto.setId(article.getId());
            articleDto.setText(article.getText());
            List<AttachmentDto> attachmentDtos = new ArrayList<>();
            Set<Attachment> attachments = article.getAttachments();
            attachments.stream().map(attachment ->  {
                AttachmentDto attachmentDto = new AttachmentDto();
                attachmentDto.setType(attachment.getType().toString());
                attachmentDto.setUri(attachment.getAttachmentPath());
                return attachmentDtos.add(attachmentDto);
            });
            articleDto.setAttachments(attachmentDtos);
            return articlesDto.add(articleDto);
        });
        return articlesDto;
    }

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
