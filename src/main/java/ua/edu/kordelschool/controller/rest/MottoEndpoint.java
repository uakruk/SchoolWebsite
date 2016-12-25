package ua.edu.kordelschool.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.kordelschool.dto.MottoDto;
import ua.edu.kordelschool.entity.Motto;
import ua.edu.kordelschool.service.MottoService;

/**
 * @author Yaroslav Kruk on 12/25/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@RestController
@RequestMapping("/motto")
public class MottoEndpoint {

    @Autowired
    private MottoService mottoService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public MottoDto createMotto(@RequestBody MottoDto mottoDto) {
        Motto motto = mottoService.createMotto(mottoDto);

        mottoDto.setId(motto.getId());

        return mottoDto;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public MottoDto editMotto(@RequestBody MottoDto mottoDto) {
       Motto motto = mottoService.editMotto(mottoDto);

        return mottoDto;
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeMotto(@PathVariable Long id) {
        mottoService.removeMotto(id);

        return ResponseEntity.ok("removed");
    }
}
