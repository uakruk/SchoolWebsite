package ua.edu.kordelschool.entity;

import javax.persistence.*;

/**
 * @author Yaroslav Kruk on 12/27/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@Entity
@Table(name = "students")
@DiscriminatorValue("STUDENT")
public class Student extends User {

    @ManyToOne(fetch = FetchType.EAGER)
    private ua.edu.kordelschool.entity.Class aClass;

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }
}
