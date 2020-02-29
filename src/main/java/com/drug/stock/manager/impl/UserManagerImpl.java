package com.drug.stock.manager.impl;

import com.alibaba.fastjson.JSON;
import com.drug.stock.dao.UserDao;
import com.drug.stock.entity.condition.UserCondition;
import com.drug.stock.entity.domain.User;
import com.drug.stock.exception.DaoException;
import com.drug.stock.manager.UserManager;
import com.drug.stock.until.TimestampFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lenovo
 */
@Slf4j
@Component("userManager")
public class UserManagerImpl implements UserManager {

    @Resource
    UserDao userDao;

    @Override
    public User getUser(Long id) throws DaoException {
        try {
            return userDao.getUser(id);
        } catch (Exception e) {
            throw new DaoException(e);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insertUser(User user) throws DaoException {
        if (countUserByAccount(user.getAccount()) > 0) {
            throw new DaoException("新添加用户已存在 user：" + JSON.toJSONString(user));
        }
        user.setCreateTime(TimestampFactory.getTimestamp());
        user.setUpdateTime(user.getCreateTime());
        try {
            return userDao.insertUser(user);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateUser(User user) throws DaoException {
        if (getUser(user.getId()) == null) {
            throw new DaoException("修改的用户信息不存在 user:" + JSON.toJSONString(user));
        }
        user.setUpdateTime(TimestampFactory.getTimestamp());
        try {
            return userDao.updateUser(user);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateUserByAccount(User user) throws DaoException {
        if (getUserByAccount(user.getAccount()) == null) {
            throw new DaoException("修改的用户信息不存在 user:" + JSON.toJSONString(user));
        }
        user.setUpdateTime(TimestampFactory.getTimestamp());
        try {
            return userDao.updateUserByAccount(user);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deleteUser(Long id) throws DaoException {
        if (getUser(id) == null) {
            throw new DaoException("删除的用户不存在 id：" + id);
        }
        try {
            return userDao.deleteUser(id);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    public User getUserByAccount(String account) throws DaoException {
        try {
            return userDao.getUserByAccount(account);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<User> listUser(UserCondition userCondition) throws DaoException {
        try {
            return userDao.listUser(userCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Long countUserByAccount(String account) throws DaoException {
        try {
            return userDao.countUserByAccount(account);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
