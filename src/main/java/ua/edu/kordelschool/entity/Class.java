package ua.edu.kordelschool.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Yaroslav Kruk on 12/27/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@Entity
@Table(name = "classes")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "class_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "aClass", cascade = CascadeType.ALL)
    private Set<Student> students;

    @JoinColumn(name = "teacher_id_fk")
    @OneToOne
    private Teacher responsible;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Teacher getResponsible() {
        return responsible;
    }

    public void setResponsible(Teacher responsible) {
        this.responsible = responsible;
    }
}
