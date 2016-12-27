package ua.edu.kordelschool.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Yaroslav Kruk on 12/25/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@Component
public class Information {

    @Value("Вітання на сайті Корделівської СЗШ!")
    public String greeting;

    @Value("Робимо із кеків піу")
    public String mission;

    @Value("Ми на тебе чекаємо!")
    public String motivation;

    @Value("Живи. Люби. КПИ.")
    public String slogan;

    @Value("Київська вул. 4, Корделівка, Вінницька область, 22445")
    public String street;

    @Value("(04333) 3-33-94")
    public String phone;

    @Value("kordelschool@ukr.net")
    public String email;
}
