package ua.edu.kordelschool.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Yaroslav Kruk on 12/11/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@Entity
@Table(name = "attachment")
public class Attachment {

    @Id
    @SequenceGenerator(name = "attach_seq_gen", sequenceName = "attach_seq", allocationSize = 1)
    @GeneratedValue(generator = "attach_seq_gen", strategy = GenerationType.SEQUENCE)
    @Column(name = "attachment_id")
    private Long id;

    @Column(name = "path")
    @NotNull
    private String attachmentPath;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private AttachmentType type;

    public Attachment(String attachmentPath, AttachmentType type) {
        this.attachmentPath = attachmentPath;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public AttachmentType getType() {
        return type;
    }

    public void setType(AttachmentType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attachment)) return false;

        Attachment that = (Attachment) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (!attachmentPath.equals(that.attachmentPath)) return false;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + attachmentPath.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
