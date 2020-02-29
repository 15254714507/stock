package com.drug.stock.service.impl;


import com.drug.stock.entity.condition.UserCondition;
import com.drug.stock.entity.domain.User;
import com.drug.stock.exception.DaoException;
import com.drug.stock.manager.UserManager;
import com.drug.stock.service.UserService;
import com.github.pagehelper.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lenovo
 */
@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    UserManager userManager;

    @Override
    public User getUser(Long id) throws DaoException {
        return userManager.getUser(id);
    }

    @Override
    public Long insertUser(User user) throws DaoException {
        return userManager.insertUser(user);
    }

    @Override
    public Long updateUser(User user) throws DaoException {
        return userManager.updateUser(user);
    }

    @Override
    public Long updateUserByAccount(User user) throws DaoException {
        return userManager.updateUserByAccount(user);
    }

    @Override
    public Long deleteUser(Long id) throws DaoException {
        return userManager.deleteUser(id);
    }

    @Override
    public User getUserByAccount(String account) throws DaoException {
        return userManager.getUserByAccount(account);
    }

    @Override
    public List<User> listUser(UserCondition userCondition) throws DaoException {
        return userManager.listUser(userCondition);
    }

    @Override
    public Long countUserByAccount(String account) throws DaoException {
        return userManager.countUserByAccount(account);
    }

    @Override
    public Page<User> findUserPage(UserCondition userCondition) throws DaoException {
        return userManager.findUserPage(userCondition);
    }
}
