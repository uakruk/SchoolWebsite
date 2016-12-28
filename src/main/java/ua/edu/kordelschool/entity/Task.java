package ua.edu.kordelschool.entity;

import javax.persistence.*;
import java.util.List;

/**
 * @author Yaroslav Kruk on 12/27/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id")
    private Long id;

    @Column(name = "description")
    private String description;

    @JoinColumn(name = "home_assignment_id_fk")
    @ManyToOne(cascade = CascadeType.ALL)
    private HomeAssignment homeAssignment;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "task_attachment", joinColumns = {@JoinColumn(name = "task_id")},
                inverseJoinColumns = {@JoinColumn(name = "attachment_id")})
    private List<Attachment> attachments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HomeAssignment getHomeAssignment() {
        return homeAssignment;
    }

    public void setHomeAssignment(HomeAssignment homeAssignment) {
        this.homeAssignment = homeAssignment;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}
