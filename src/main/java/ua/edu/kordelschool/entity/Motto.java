package ua.edu.kordelschool.entity;

import javax.persistence.*;

/**
 * @author Yaroslav Kruk on 12/25/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@Entity
@Table(name = "motto")
public class Motto {

    @Id
    @SequenceGenerator(name = "motto_seq_gen", sequenceName = "motto_seq", allocationSize = 1)
    @GeneratedValue(generator = "motto_seq_gen", strategy = GenerationType.SEQUENCE)
    @Column(name = "motto_id")
    private Long id;

    @Column(name = "caption")
    private String caption;

    @Column(name = "text")
    private String text;

    @Column(name = "end_text")
    private String end;

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

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
