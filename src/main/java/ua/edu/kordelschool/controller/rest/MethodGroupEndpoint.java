package ua.edu.kordelschool.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.kordelschool.entity.MethodGroup;
import ua.edu.kordelschool.service.MethodGroupService;

/**
 * @author Yaroslav Kruk on 12/25/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@RestController
@RequestMapping("/group")
public class MethodGroupEndpoint {

    @Autowired
    private MethodGroupService methodGroupService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
                   produces = MediaType.APPLICATION_JSON_VALUE)
    public MethodGroup addMethodGroup(@RequestBody MethodGroup methodGroup) {

        return methodGroupService.createMethodGroup(methodGroup);
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public MethodGroup editMethodGroup(@RequestBody MethodGroup methodGroup) {

        return methodGroupService.editMethodGroup(methodGroup);
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeMethodGroup(@PathVariable Long id) {
        methodGroupService.removeMethodGroup(id);

        return ResponseEntity.ok("removed");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public MethodGroup getMethodGroupById(@PathVariable Long id) {

        return methodGroupService.getMethodGroup(id);
    }
}
