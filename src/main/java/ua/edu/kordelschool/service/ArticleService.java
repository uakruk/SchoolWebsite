package ua.edu.kordelschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.kordelschool.dao.ArticleDao;
import ua.edu.kordelschool.dto.ArticleDto;
import ua.edu.kordelschool.dto.AttachmentDto;
import ua.edu.kordelschool.entity.Article;
import ua.edu.kordelschool.entity.Attachment;
import ua.edu.kordelschool.entity.AttachmentType;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        long currentTime = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTime);
        article.setDate(calendar);

        //todo make attachments service
        Set<Attachment> attachments = new HashSet<>();
        article.setAttachments(attachments);

        articleDto.getAttachments().stream()
                .map(a -> attachments.add(new Attachment(a.getUri(),
                        AttachmentType.valueOf(a.getType()))));

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

        return articleDao.getAll();
    }
}
