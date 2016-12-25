package ua.edu.kordelschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.kordelschool.dao.MottoDao;
import ua.edu.kordelschool.dto.MottoDto;
import ua.edu.kordelschool.entity.Motto;

import java.util.List;

/**
 * @author Yaroslav Kruk on 12/25/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@Service
public class MottoService {

    @Autowired
    private MottoDao mottoDao;

    public Motto createMotto(MottoDto mottoDto) {

        Motto motto = new Motto();
        motto.setText(mottoDto.getText());
        motto.setCaption(mottoDto.getCaption());
        motto.setEnd(mottoDto.getEnd());

        Motto response = mottoDao.create(motto);

        return response;
    }

    public Motto getMotto(Long mottoId) {

        Motto response = mottoDao.read(mottoId);

        return response;
    }

    public MottoDto getMottoDto(Long mottoId) {

        Motto motto = getMotto(mottoId);

        MottoDto mottoDto = new MottoDto();
        mottoDto.setText(motto.getText());
        mottoDto.setCaption(motto.getCaption());
        mottoDto.setEnd(motto.getEnd());
        mottoDto.setId(mottoId);

        return mottoDto;
    }

    public List<Motto> getMottos() {

        List<Motto> mottos = mottoDao.getMottos();

        return mottos;
    }

    public void removeMotto(Long mottoId) {
        mottoDao.delete(mottoId);
    }

    public Motto editMotto(MottoDto mottoDto) {
        Motto motto = mottoDao.read(mottoDto.getId());

        motto.setText(mottoDto.getText());
        motto.setCaption(mottoDto.getCaption());
        motto.setEnd(mottoDto.getEnd());

        Motto response = mottoDao.update(motto);

        return response;
    }
}
