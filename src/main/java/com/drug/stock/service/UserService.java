package com.drug.stock.service;

import com.drug.stock.entity.condition.UserCondition;
import com.drug.stock.entity.domain.User;
import com.drug.stock.exception.DaoException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author lenovo
 */
public interface UserService {
    /**
     * 获得用户信息
     *
     * @param id
     * @return User
     * @throws DaoException
     */
    public User getUser(Long id) throws DaoException;

    /**
     * 添加用户
     *
     * @param user 添加的用户的信息
     * @return Long 主键
     * @throws DaoException
     */
    public Long insertUser(User user) throws DaoException;

    /***
     * 修改用户信息
     *
     * @param user
     * @return 成功的个数
     * @throws DaoException
     */
    public Long updateUser(User user) throws DaoException;

    /**
     * 修改用户信息，通过account来做条件
     *
     * @param user
     * @return
     * @throws DaoException
     */
    public Long updateUserByAccount(User user) throws DaoException;

    /**
     * 删除用户信息，逻辑删除
     *
     * @param id
     * @return 成功的个数
     * @throws DaoException
     */
    public Long deleteUser(Long id) throws DaoException;

    /**
     * 根据账号获得用户信息
     *
     * @param account 账号
     * @return User 用户信息
     * @throws DaoException
     */
    public User getUserByAccount(String account) throws DaoException;

    /**
     * 根据条件获得user的集合
     *
     * @param userCondition
     * @return User的集合
     * @throws DaoException
     */
    public List<User> listUser(UserCondition userCondition) throws DaoException;

    /**
     * 根据账号查看是否有此人
     *
     * @param account
     * @return
     * @throws DaoException
     */
    public Long countUserByAccount(String account) throws DaoException;

    /**
     * 获得User的分页数据
     *
     * @param userCondition
     * @return
     * @throws DaoException
     */
    public PageInfo<User> findUserPage(UserCondition userCondition) throws DaoException;

    /**
     * 获得不同身份的数量
     *
     * @param superAdmin
     * @return
     * @throws DaoException
     */
    public Long countUserBySuperAdmin(Boolean superAdmin) throws DaoException;
}
