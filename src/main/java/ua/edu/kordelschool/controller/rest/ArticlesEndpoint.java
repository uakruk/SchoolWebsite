package ua.edu.kordelschool.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.edu.kordelschool.dto.ArticleDto;
import ua.edu.kordelschool.dto.AttachmentDto;
import ua.edu.kordelschool.entity.Article;
import ua.edu.kordelschool.entity.Attachment;
import ua.edu.kordelschool.service.ArticleService;
import ua.edu.kordelschool.service.ImageService;

import java.io.IOException;
import java.util.*;

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

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ArticleDto createArticle(@RequestBody ArticleDto articleDto) {
        Article article = articleService.createArticle(articleDto);

        articleDto.setId(article.getId());

        return articleDto;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ArticleDto createArticleFromAdmin(@RequestParam MultipartFile file, @RequestParam String caption,
                                             @RequestParam String author, @RequestParam String text) throws IOException {
        System.err.println("method called");
        ArticleDto articleDto = new ArticleDto();
        articleDto.setText(text);
        articleDto.setCaption(caption);
        articleDto.setAuthor(author);
        articleDto.setComments(new ArrayList<>());

        List<AttachmentDto> attachments = new ArrayList<>();
        AttachmentDto attachmentDto = new AttachmentDto();
        attachmentDto.setType("IMAGE");

        String path = imageService.uploadStaticImage(file);
        attachmentDto.setUri(path);

        attachments.add(attachmentDto);

        articleDto.setAttachments(attachments);
        articleDto.setType("ARTICLE");

        articleService.createArticle(articleDto);

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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ArticleDto getArticle(@PathVariable Long id) {

        return articleService.getArticleById(id);
    }
}
