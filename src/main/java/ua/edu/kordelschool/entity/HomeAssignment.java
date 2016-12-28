package ua.edu.kordelschool.entity;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

/**
 * @author Yaroslav Kruk on 12/27/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@Entity
@Table(name = "home_assigments")
public class HomeAssignment {

    @Id
    @SequenceGenerator(name = "homeass_seq_gen", sequenceName = "homeass_seq", allocationSize = 1)
    @GeneratedValue(generator = "homeass_seq_gen", strategy = GenerationType.SEQUENCE)
    @Column(name = "assigmnet_id")
    private Long id;

    @JoinColumn(name = "subject_id_fk")
    @ManyToOne(fetch = FetchType.EAGER)
    private Subject subject;

    @JoinColumn(name = "class_id_fk")
    @ManyToOne
    private ua.edu.kordelschool.entity.Class aClass;

    @JoinColumn(name = "teacher_id_fk")
    @ManyToOne(fetch = FetchType.EAGER)
    private Teacher sender;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "homeAssignment")
    private List<Task> tasks;

    @Column(name = "given_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar givenDate;

    @Column(name = "deadline")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar deadline;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public Teacher getSender() {
        return sender;
    }

    public void setSender(Teacher sender) {
        this.sender = sender;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Calendar getGivenDate() {
        return givenDate;
    }

    public void setGivenDate(Calendar givenDate) {
        this.givenDate = givenDate;
    }

    public Calendar getDeadline() {
        return deadline;
    }

    public void setDeadline(Calendar deadline) {
        this.deadline = deadline;
    }
}
