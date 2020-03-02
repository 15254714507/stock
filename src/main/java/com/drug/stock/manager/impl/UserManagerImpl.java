package com.drug.stock.manager.impl;

import com.alibaba.fastjson.JSON;
import com.drug.stock.dao.UserDao;
import com.drug.stock.entity.condition.UserCondition;
import com.drug.stock.entity.domain.User;
import com.drug.stock.exception.DaoException;
import com.drug.stock.manager.UserManager;
import com.drug.stock.until.TimestampFactory;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
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
            log.error("新添加用户已存在 user {}", JSON.toJSONString(user));
            return 0L;
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
            log.error("修改的用户信息不存在 user{}", JSON.toJSONString(user));
            return 0L;
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
            log.error("修改的用户信息不存在 user{}", JSON.toJSONString(user));
            return 0L;
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
            log.error("删除的用户不存在 id{}", id);
            return 0L;
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

    @Override
    public PageInfo<User> findUserPage(UserCondition userCondition) throws DaoException {
        //参数第一个是第几页，第二个是条数，通过凭借SQL的方式,必须加判断
        if (userCondition.getPage() != null && userCondition.getRows() != null) {
            PageHelper.startPage(userCondition.getPage(), userCondition.getRows());
        }
        List<User> list = null;
        try {
            list = userDao.listUser(userCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            //虽然不推荐使用，但是上面还是会出现特殊情况的
            PageHelper.clearPage();
        }
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;

    }

    @Override
    public Long countUserBySuperAdmin(Boolean superAdmin) throws DaoException {
        try {
            return userDao.countUserBySuperAdmin(superAdmin);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}
