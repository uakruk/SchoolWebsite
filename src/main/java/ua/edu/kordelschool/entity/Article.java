package ua.edu.kordelschool.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * @author Yaroslav Kruk on 12/11/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@Entity
@Table(name = "article")
public class Article {

    @Id
    @SequenceGenerator(name = "article_seq_gen", sequenceName = "article_seq", allocationSize = 1)
    @GeneratedValue(generator = "article_seq_gen", strategy = GenerationType.SEQUENCE)
    @Column(name = "article_id")
    private Long id;

    @Column(name = "created")
    private Calendar date;

    @Column(name = "caption")
    @NotNull
    private String caption;

    @Column(name = "text")
    @NotNull
    private String text;

    @Column(name = "author")
    private String author;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ArticleType type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
    private List<Comment> comments;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "article_attachments",
                joinColumns = {@JoinColumn(name = "article_id")},
                inverseJoinColumns = {@JoinColumn(name = "attachment_id")})
    private Set<Attachment> attachments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<Attachment> attachments) {
        this.attachments = attachments;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public ArticleType getType() {
        return type;
    }

    public void setType(ArticleType type) {
        this.type = type;
    }
}
