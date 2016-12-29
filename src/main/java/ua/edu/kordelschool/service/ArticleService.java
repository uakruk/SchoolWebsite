package ua.edu.kordelschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.kordelschool.dao.ArticleDao;
import ua.edu.kordelschool.dto.ArticleDto;
import ua.edu.kordelschool.dto.AttachmentDto;
import ua.edu.kordelschool.entity.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Yaroslav Kruk on 12/11/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    public Article createArticle(ArticleDto articleDto) {
        Article article = new Article();
        article.setAuthor(articleDto.getAuthor());
        article.setCaption(articleDto.getCaption());
        article.setText(articleDto.getText());
        article.setType(ArticleType.valueOf(articleDto.getType()));

        long currentTime = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTime);
        article.setDate(calendar);

        //todo make attachments service
        Set<Attachment> attachments = new HashSet<>();
        article.setAttachments(attachments);

        List<Comment> comments = new ArrayList<>();
        article.setComments(comments);

        articleDto.getAttachments().stream()
                .map(a -> attachments.add(new Attachment(a.getUri(),
                        AttachmentType.valueOf(a.getType()))));
        articleDto.getComments().stream()
                .map(c -> comments.add(new Comment(c.getAuthor(), c.getText())));

        Article response = articleDao.create(article);

        return response;
    }

    public void removeArticle(ArticleDto articleDto) {
        articleDao.delete(articleDto.getId());
    }

    public void removeArticle(Long id) {
        articleDao.delete(id);
    }

    public Article editArticle(ArticleDto articleDto) {
        Article article = articleDao.read(articleDto.getId());

        article.setText(articleDto.getText());
        article.setCaption(articleDto.getCaption());
        article.setAuthor(articleDto.getAuthor());
        article.setType(ArticleType.valueOf(articleDto.getType()));

        Set<Attachment> attachments = article.getAttachments();

        articleDto.getAttachments().stream()
                .map(a -> new Attachment(a.getUri(), AttachmentType.valueOf(a.getType())))
                .map(attachments::add);

        Article response = articleDao.update(article);

        return response;
    }

    public Article addAttachment(Long articleId, AttachmentDto attachmentDto) {
        Article article = articleDao.read(articleId);

        article.getAttachments().add(new Attachment(attachmentDto.getUri(),
                AttachmentType.valueOf(attachmentDto.getType())));

        Article response = articleDao.update(article);

        return response;
    }

    public List<Article> getAllArticles() {

        return articleDao.getAllArticles();
    }

    public List<Article> getAllEvents() {

        return articleDao.getAllEvents();
    }

    public ArticleDto getArticleById(Long id) {
        Article article = articleDao.read(id);

        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(id);
        articleDto.setCaption(article.getCaption());
        if (article.getType() != null)
            articleDto.setType(article.getType().name());
        articleDto.setText(article.getText());
        articleDto.setAuthor(article.getAuthor());

        List<AttachmentDto> attachmentDtoList = new ArrayList<>();

        article.getAttachments().stream()
                .map(a -> new AttachmentDto(a.getAttachmentPath(), a.getType().name()))
                .map(attachmentDtoList::add);

        articleDto.setAttachments(attachmentDtoList);

        return articleDto;
    }
}
