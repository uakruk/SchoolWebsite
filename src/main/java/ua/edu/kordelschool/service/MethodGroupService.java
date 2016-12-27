package ua.edu.kordelschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.kordelschool.dao.MethodGroupDao;
import ua.edu.kordelschool.entity.MethodGroup;

import java.util.List;

/**
 * @author Yaroslav Kruk on 12/25/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@Service
public class MethodGroupService {

    @Autowired
    private MethodGroupDao methodGroupDao;

    public List<MethodGroup> getMethodGroups() {

        return methodGroupDao.getAllMethodGroups();
    }

    public MethodGroup createMethodGroup(MethodGroup methodGroup) {

        return methodGroupDao.create(methodGroup);
    }

    public MethodGroup getMethodGroup(Long id) {

        return methodGroupDao.read(id);
    }

    public MethodGroup editMethodGroup(MethodGroup methodGroup) {

        return methodGroupDao.update(methodGroup);
    }

    public void removeMethodGroup(Long id) {

        methodGroupDao.delete(id);
    }

    public void removeMethodGroup(MethodGroup methodGroup) {

        methodGroupDao.delete(methodGroup.getId());
    }
}
